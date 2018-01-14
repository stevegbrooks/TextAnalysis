# Textual Analysis - 11.21.17

Overall Design:

My design is essentially a FileReader, a BookReader, a TextAnalysis, and then a UserInterface class. I create distinct classes for Word, Letter, and Quote. I used mostly ArrayLists to store data, and in the methods where I needed to find largest values, etc, I used HashMaps. 

I also used a couple Utility classes to support the TextAnalysis and BookReader classes.

##1. Letter Frequency

Returns the top N ranks of most frequently appearing letters in the .txt file (where N is supplied by the user).

##2. Word Frequency

This method was very similar to above. I definitely allowed some letter combinations that wouldn't be found in an Oxford English Dictionary, but I considered them to be words anyway (e.g. vernacular/slang). 

After that the same methods as in #1 were applied to generate the top N ranks.

##3. Word Frequency with Stop List

Returns top N ranks as supplied by the user with the 'Stop List' words removed. This allows for a .txt file's distinct vocabulary to come through.

##4. Quotes

I spent more time on this than on any other part of the program. The method for getting the top N quotes by length is very straightforward, and I wont really go into it. 

The regex to actually find a quote was really where the work was. It was only after I realized that you can set options like multiline and learned the difference between greedy and lazy matching that i was able to capture single quotation quotes reliably without too many false negatives or false positives. 

I dont think that my ArrayList<Quote> has the correct number of quotes based on the .txt file its reading, but i do think it reliably prints out the top N quotes in terms of character length without any false positives/false negatives.

##5. Wildcard

I chose to do something that doesn't have a lot of utility, but I thought would be an interesting project - randomize the letters in each word without replacement, such that each original letter is in a random position, and no original letters are repeated. I wanted to do this because of that thing where people can read text even if its scrambled - they can pick up context and fill in the gaps when a word's letters are randomized. It turns out that it's true, they really can!



