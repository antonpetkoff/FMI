#include <sys/types.h>
#include <sys/stat.h>
#include <unistd.h>
#include <fcntl.h>
#include <err.h>
#include <stdio.h>
#include <string.h>

int main(int argc, char** argv) {
    int fd;
    unsigned char c;
    const int SIZE = 1<<8;
    unsigned long long chars[SIZE];
    memset(chars, 0, SIZE * sizeof(unsigned long long));

    if (argc != 2) {
        err(1, "Exactly one argument must be passed!");
    }

    if ((fd = open(argv[1], O_RDONLY)) == -1) {
        err(1, "Could not open file for reading");
    }

    while (read(fd, &c, 1) > 0) {
        chars[(int) c]++;
    }
    close(fd);

    printf("%s", "BYTE\tCOUNT\n");
    for (int i = 0; i < SIZE; ++i) {
        // the byte with decimal value i occurs chars[i] times
        // the output should be comfortable for pipes
        printf("%d\t%llu\n", i, chars[i]);
    }
}

