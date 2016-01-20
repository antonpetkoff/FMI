module Main where

data Attribute = Attribute {name :: String, value :: String} deriving Show
data Node = Compound {tag :: String, children :: [Node], attributes :: [Attribute]}
          | Leaf {tag :: String, text :: String, attributes :: [Attribute]} deriving Show

attr1 = Attribute "src" "madonna.jpg"
attr2 = Attribute "encoding" "UTF-8"

leaf1 = Leaf "img" "myImgText" [attr1, attr2]
leaf2 = Leaf "p" "myParagraph" [attr2]

compound1 = Compound "div" [leaf1, leaf2] []

showAttributes :: [Attribute] -> String
showAttributes [] = ""
showAttributes attributes = concat [" " ++ name attr ++ "=\"" ++ value attr ++ "\"" | attr <- attributes]

showLeaf :: Node -> String
showLeaf (Leaf tag text attributes) = concat ["<", tag, showAttributes attributes, ">", text, "</", tag, ">"]

main = do
    print [attr1, attr2]
    print leaf1
    print (tag leaf1)
    print (tag compound1)
    print (attributes leaf1)
    print (showAttributes [attr1, attr2])
    print (showLeaf leaf1)