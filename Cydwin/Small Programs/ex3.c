#! bin/sh

echo "Dati numerele care vreti sa fie adunate: "
s=0

for x in $@
do
s=$(($s+$x))
done

echo "Suma numerelor este: $s."
exit 0