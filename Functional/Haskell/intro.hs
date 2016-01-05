quickSort [] = []
quickSort (x:xs) = quickSort less ++ [x] ++ quickSort more
  where less = filter (<=x) xs
        more = filter (>x) xs

factorial x = if x > 1 then x * factorial(x - 1) else 1

l = [1, 2, 3]

myMap f l = [f x | x <- l]  -- list comprehension
myFilter p l = [x | x <- l, p x]
triple = (1, "Pesho", 3.14)
(a,b,c) = triple

mat = [[1,2,3],[4,5,6],[7,8,9],[10,11,12],[13,14,15],[16,17,18]]

zad1 ll x i = elem x (ll !! (quot i 3))

fibs = 0 : 1 : zipWith (+) fibs (tail fibs)

main :: IO Int
main = do
        -- print (factorial 5)
        -- print (l !! 0)
        -- print (myMap (*2) [1..10])
        -- print ([x * 2 | x <- [0..10000], x `mod` 85 == 23]) -- remember the ``
        -- print (myFilter odd [1..10])
        -- print (zad1 mat 15 15)
        print (take 10 fibs)
        return 10