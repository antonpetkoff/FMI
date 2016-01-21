module Main where

import Text.Regex.Posix

data Attribute = Attribute {name :: String, value :: String} deriving Show
data Tree = Tree {tag :: String, children :: [Tree], attributes :: [Attribute]}
          | Leaf {tag :: String, text :: String, attributes :: [Attribute]} deriving Show
-- if Leaf has no text, it is a self-closing tag
-- assert :: Tree always has at least 1 child

attr1 = Attribute "src" "madonna.jpg"
attr2 = Attribute "encoding" "UTF-8"

leaf1 = Leaf "img" "" [attr1, attr2]
leaf2 = Leaf "p" "myParagraph" [attr2]
leaf3 = Leaf "p" "myParagraph2" [attr2]

tree1 = Tree "div" [leaf1, leaf2, leaf3] []

showAttributes :: [Attribute] -> String
showAttributes [] = ""
showAttributes attributes = concat [" " ++ name attr ++ "=\"" ++ value attr ++ "\"" | attr <- attributes]

showLeaf :: Tree -> String
showLeaf (Leaf tag text attributes)
    | text == ""    = concat ["<", tag, showAttributes attributes, "/>"]
    | otherwise     = concat ["<", tag, showAttributes attributes, ">", text, "</", tag, ">"]

tabs :: Int -> String
tabs n = concat $ replicate n "\t"

showTree :: Int -> Tree -> String
showTree level leaf@(Leaf _ _ _) = concat [tabs level, showLeaf leaf]
showTree level (Tree tag children attributes) = concat [openingTag, content, closingTag]
    where
        openingTag = concat [tabs level, "<", tag, showAttributes attributes, ">\n"]
        content = unlines $ map (showTree (level + 1)) children
        closingTag = concat ["</", tag, ">"]


-- parseAttribute :: String -> Attribute


-- parses a leaf node which can be self-closing too
-- parseLeaf :: String -> Tree
-- parseLeaf line = (Tree tag, text, attributes)
--     where
--         matched = line =~ "<([a-z]+)(.*)/>" :: [[String]]
--         tag = matched !! 0 !! 1
--         attributes = parseAttributes $ words matched !! 0 !! 2

-- match

myTag = "<img src=\"madonna.jpg\" encoding=\"UTF-8\"/>"

main = do
    writeFile "output.xml" (showTree 0 tree1)
    print (myTag =~ "<([a-z]+)(.*)/>" :: [[String]]) 
    print (getAllTextMatches $ myTag =~ "\\w+=\"[\\w\\s\\.\\-]+\"" :: [String])
