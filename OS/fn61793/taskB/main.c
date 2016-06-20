#include <stdio.h>
#include <inttypes.h>
#include <err.h>
#include <string.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <stdlib.h>
#include <math.h>

int source_fd, aux_fd;

int comparator(const void * l, const void * r) {
    return (*(uint32_t *) l) - (*(uint32_t *) r);
}

void print_integers(uint32_t * array, int size) {
    for (int i = 0; i < size; ++i) {
        printf("%d ", array[i]);
    }
    printf("\n\n");
}

void close_fds() {
    close(source_fd);
    close(aux_fd);
}

int main(int argc, char ** argv) {
    uint32_t * buffer;
    ssize_t read_size;
    uint64_t file_size;
    size_t buffer_size,    // buffer size is the same as the available RAM
           window_size,
           buffers_count,
           windows_count;

    if (argc != 3) {
        err(1, "You must pass 2 arguments: a file for sorting and available RAM in bytes!");
    }

    if ((source_fd = open(argv[1], O_RDONLY)) == -1) {
        err(1, "Couldn't open file for reading!");
    }

    file_size = lseek(source_fd, 0, SEEK_END);
    lseek(source_fd, 0, SEEK_SET);
    if (file_size % sizeof(uint32_t)) {
        warn("Unaligned data is given!");
    }
    printf("File size: %" PRIu64 " bytes\n", file_size);
    buffer_size = atoi(argv[2]);
    printf("Available RAM (memory buffer size): %zu bytes\n", buffer_size);
    buffers_count = ceil(file_size / (double) buffer_size);
    windows_count = buffers_count + 1; 
    window_size = buffer_size / windows_count;
    window_size -= window_size % sizeof(uint32_t);
    printf("(Input) Buffers count: %zu\n", buffers_count);
    printf("Window size: %zu bytes (aligned to 4 bytes (uint32_t))\n", window_size);
    printf("Windows count: %zu\n", windows_count);

    buffer = (uint32_t *) malloc(buffer_size);

    if ((aux_fd = open("aux", O_WRONLY | O_CREAT | O_TRUNC, 0644)) == -1) {
        close(source_fd);
        err(1, "Couldn't open auxiliary file for writing!");
    }

    // sort separately buffers_count chunks from the source_fd
    while ((read_size = read(source_fd, buffer, buffer_size)) > 0) {
        qsort(buffer, read_size / sizeof(uint32_t), sizeof(uint32_t), comparator);
        print_integers(buffer, read_size / sizeof(uint32_t));

        if (read_size != write(aux_fd, buffer, read_size)) {
            close_fds();
            err(1, "Less data was written than it was read!");
        }
    }

    free(buffer);
    close_fds();
}

