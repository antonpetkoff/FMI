#!/bin/bash

echo "Enter a string of length 5:"
read s

while [ ${#s} -le 5 ]
do
    echo "Enter a string of length 5:"
    read s
done

echo $(echo $s | grep -o . | head -n 3 | tail -n 1)
