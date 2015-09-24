# Lawrence-Style-Checker
## Basic Description
A style checking library, built in Java. The object can be used as a style checker in a similar way spelling or grammar checkers are used. 

This program is similar to the Hemingway App. It identifies flabby sentences and categorizes them into three levels: flabby, needs toning, and good. It is up to a GUI to display the results. The GUI could display flabby as red, and 'needs toning' as orange. 

I created a new way to count syllables, using a dictionary method. This is more accurate than just relying on an algorithm to count syllables. Feel free to copy my syllables.txt file. 

Feel free to use this program to create a style checker. This program does not include a GUI or an 'on the fly' writing buffer. Yet I will create a GUI for this library in the near future. 
## UML Diagram
![UML diagram of Lawrence class](/LawrenceUML.png?raw=true "Lawrence App UML Diagram")

## How to use it
### To use the object, instantiate it first
```java
import com.org.watsonwrite.lawrence.Lawrence;
Lawrence lawrence = new Lawrence();
```

### Find out whether a word is in the syllable dict
You can query the object directly to see if a word is in the dictionary. Mind you, many common words are not in the dictionary. This is not because the dictionary is limited, but rather because I illiminated words that could be figured out with the simple count syllables algorithm. For example, the word elephant is not in the dictionary because I can count the number of vowels 'e', 'e' and 'a' and that is the syllable count.
```java
System.out.println(lawrence.isWordInDict("elephant")); // false
System.out.println(lawrence.isWordInDict("reasonable")); // true
```

### Get the syllable count of any word
You can ask the program to count the syllables of any word and it will decide whether to use the dictionary method or the algorithm method. As you can see it can count the words elephant and reasonable, even though the former uses the algorithm method and the latter uses the dictionary method. 
```java
System.out.println(lawrence.getSyllable("elephant")); // 3
System.out.println(lawrence.getSyllable("reasonable")); // 4
```
### Calculate the readability score of a sentence
You can calculate the readability score of any sentence by just typing this code. The accuracy of this score depends heavily on the accuracy of the syllable counter. So this method call is very valueable and will be more accurate than most readability tests out there. The algorithm is called the Flesch-Kincaid score. 
```java
System.out.println(lawrence.fleschKincaidSentence("Here is a good sentence.")); // output =circa 100
```

### Get the status of a sentence
I have specifically built this library to tell you whether a sentence should be underlined red for bad, orange for semi-bad, and nothing for good. I have made the default upperbound 40 and lowerbound 30. Thus, if a sentence is above 40, the program will not demand that it be underlined. However, if the sentence is between 40 and 30, it'll be labeled 1 for needs toning. But if it is below 30, it'll be labeled 2 for flabby. I recommend level 2 sentences be underlined red and level 1 sentences be underlined orange. 
```java
// Get score of sentence and status
// 0 = good; 1 = needs toning; 2 = flabby.
double[] scoreStatus = lawrence.getScoreAndStatus("Here is an unbelievably reconstruction longitudinal sentence that you may need to reconfiguration down.");
System.out.println(scoreStatus[0] + " " + scoreStatus[1]);
```

### You can customize the threshold levels if you want
To customize the thresholds, say if you want to have ultra strict rules, you can do so when you instantiate the object.
```java
Lawrence lawrence = new Lawrence(60, 50) // sets upperbound to 60 and lowerboundd to 50
```

### JAR library released and ready to go!
If you want to use this library quickly, I reccommend downloading the JAR file from the 'releases' link above. Import this library into your Java project and then import and instantiate the object as shown above. 













