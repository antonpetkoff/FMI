# source=$1, target=$2

dirs=$(find $1 -type f -regextype posix-extended -regex '.*\.[[:alnum:]]+' -print)
a
for file in $dirs; do
    echo $file
done

