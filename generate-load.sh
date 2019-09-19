#!/bin/bash
repeats="5"
i="0"
maxcall="100"

while [ $i -lt $repeats ]
do
  noOfAPICalls=$((32767 / ($RANDOM + 1)))
  noOfAPICalls=$(($noOfAPICalls>$maxcall? $maxcall:$noOfAPICalls))
  j="0"
  echo API CALL ITERATION $i
  while [ $j -lt $noOfAPICalls ]
  do
    curl http://fuse-implementation-beer-demo-prod.18.184.165.137.nip.io/rest/beer/aa
    echo
    j=$[$j+1]
  done
  sleep 1
  i=$[$i+1]
done

