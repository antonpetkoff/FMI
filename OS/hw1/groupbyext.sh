# $1=source, $2=target
pattern='\.[a-z0-9]+$'

find $1 -type f | grep -Ei $pattern | while read f
do
    extension=$(echo $f | grep -Eio $pattern | cut -c2-)
    mkdir -p "$2$extension" && cp "$f" "$2$extension"
done
