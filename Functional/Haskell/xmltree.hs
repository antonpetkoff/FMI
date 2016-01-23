module Main where

import System.IO
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

myAppend :: String -> String -> String
myAppend tail head = head ++ tail

myTails = ["<a", "<b", "<c", "<d"]

{- mapChildren :: list of children -> XPath selector -> function -> list of children
a function which takes the children of a node (list)
takes: list of children, string XPath selector, function
and returns the same list of children but where for each element
which is matched with the given XPath selector the given
function (which can be a partial application) is called with the matched
child element as an argument
-}
-- mapChildren :: [Tree] -> String -> (Tree -> Tree) -> [Tree]

{- create

-}
-- create :: Tree -> [String] -> Tree
-- create tree [] = tree
-- create tree@(Tree tag children attributes) selectors@(head:tail) =
    -- (Tree tag (mapChildren children head (\t -> create t tail)) attributes)

-- matchString :: String -> Bool
-- matchString 

getUserLines :: IO String                      -- optional type signature
getUserLines = go ""
    where go contents = do
        line <- getLine
        if line == "q"
            then return contents
            else go (contents ++ line ++ "\n")     -- add a newline

main = do
    -- writeFile "output.xml" (showTree 0 tree1)
    -- print (myTag =~ "<([a-z]+)(.*)/>" :: [[String]]) 
    -- print (getAllTextMatches $ myTag =~ "\\w+=\"[\\w\\s\\.\\-]+\"" :: [String])
    -- print (foldr myAppend "head<" myTails)
    str <- getUserLines
    putStrLn str