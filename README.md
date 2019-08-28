## Re-implemented Lawrence-Style-Checker in Pharo
### "Write Like DH Lawrence"!
## Basic Description
A style checking library, ported to Pharo. The object can be used as a style checker in a similar way spelling or grammar checkers are used. DH Lawrence had a very lean writing style. This program aims to help you write like Lawrence. 

This program is similar to the Hemingway App. It identifies flabby sentences and categorises them into three levels: flabby, needs toning, and good. It is up to a GUI to display the results. The GUI could display flabby as red, and 'needs toning' as orange. 

I used the Lawrence style to to count syllables, using a dictionary method. This is more accurate than just relying on an algorithm to count syllables. Feel free to copy syllables.txt file. 

Feel free to use this program to create a style checker. This program does not include a GUI or an 'on the fly' writing buffer. Yet I will create a GUI for this library in the near future. 

## How to use it
### To use the object, instantiate it first
```Pharo
Metacello new
   baseline: 'ReadabilityChecker';
   repository: 'github://poojaruhal/Lawrence-Style-Checker/src';
   load
```

### Find out whether a word is in the syllable dict
You can query the object directly to see if a word is in the dictionary. Mind you, many common words are not in the dictionary. This is not because the dictionary is limited, but rather because  the words are eliminated and could be figured out with the simple count syllables algorithm. For example, the word elephant is not in the dictionary because the number of vowels 'e', 'e' and 'a' can be counted and that is the syllable count.
```Pharo
commentReadabilityChecker := CommentReadabilityChecker new.
commentReadabilityChecker isWordInDictionary: 'elephant'. "false"
commentReadabilityChecker isWordInDictionary: 'reasonable.' "true"
```

### Get the syllable count of any word
You can ask the program to count the syllables of any word and it will decide whether to use the dictionary method or the algorithm method. As you can see it can count the words elephant and reasonable, even though the former uses the algorithm method and the latter uses the dictionary method. 
```Pharo
commentReadabilityChecker.syllableCount:'elephant'. "3"
commentReadabilityChecker.syllableCount:'reasonable'. "4"
```
### Calculate the readability score of a sentence
You can calculate the readability score of any sentence by just typing this code. The accuracy of this score depends heavily on the accuracy of the syllable counter. So this method call is very valuable and will be more accurate than most readability tests out there. The algorithm is called the Flesch-Kincaid score. 
```Pharo
commentReadabilityChecker.kincaidScoreOf: 'Here is a good sentence.' " output = 100.24000000000002"
```

### Get the status of a sentence
The library is specifically built to tell whether a sentence should be underlined red for bad, orange for semi-bad, and nothing for good. The default upperbound 40 and lowerbound 30. Thus, if a sentence is above 40, the program will not demand that it be underlined. However, if the sentence is between 40 and 30, it'll be labeled 1 for needs toning. But if it is below 30, it'll be labeled 2 for flabby. The recommendation to mark level 2 sentences red and level 1 sentences orange. 
```Pharo
// Get score of sentence and status
// 0 = good; 1 = needs toning; 2 = flabby.
commentReadabilityChecker readabilityOf:'Here is an unbelievably reconstruction longitudinal sentence that you may need to reconfiguration down.'
" score = 11.339285714285722
  output 2.0
  this means got a readability score of 11.34 and a status of 2,
   which means the sentence is very flabby.
  "
```

### You can customize the threshold levels if you want
To customize the thresholds, say if you want to have ultra strict rules, you can do so when you instantiate the object.
```Pharo
commentReadabilityChecker upperBound:50
commentReadabilityChecker lowerBound:20
```

### Testing the syllable counter
The syllable coumter is tested for Java program.
As the syllable counter is a vital part of the algorithm for measuring readability, the code is benchmarked and tested on a dataset uploaded by a user on StackOverflow. 528 syllables are found over the entire document. The strike rate of  99.40% achieved. This means the program only got 2 / 337 words wrong, regarding their syllable counts. 

 The benchmark here: [http://stackoverflow.com/a/32784041/2734752](http://stackoverflow.com/a/32784041/2734752)

The output can be found here: [http://pastebin.com/LyiBTcbb](http://pastebin.com/LyiBTcbb)

### syllables.txt Dictionary available for Developers
The file syllables.txt provided by watson.
The original syllables.txt file was over 120,000 entries long. Thus, I trimmed it down to make this program faster. I did this by removing any entry that could be figured out with a simple vowel count algorithm. This reduced the file to around 50,000 entries. However, if you want the original dictionary file, I included it in the /dict folder. This may be useful for programmers who may wish to use the file for a different application. 













