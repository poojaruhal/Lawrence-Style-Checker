"
Calculate readability score of a comment, inspired from 'Lawrence-style-checker'.

Scientific text generally have complex and average legnth sentences, an average of 25-30 words according to article ""The long sentence: A disservice to science in the Internet age"".

However general english sentence are suggested to be mix of short and lengthy sentences with an averag length 20-25. Short sentence are there to grab the attention of reader where lengthy sentences are written to make a reading flow.
"
Class {
	#name : #CommentReadabilityChecker,
	#superclass : #Object,
	#instVars : [
		'upperBound',
		'lowerBound'
	],
	#category : #ReadabilityChecker
}

{ #category : #'as yet unclassified' }
CommentReadabilityChecker >> countSyallablesOf: aSentence [
	"calculate syllables of words "
	^ aSentence sum: [ :aWord | self syllableCount: aWord].	
]

{ #category : #processing }
CommentReadabilityChecker >> countSyllablesLight: aWord [
	"A simple syllable counter.
	Use this as the plan B for when the dictionary method fails"

	| syllables |
	syllables := aWord asString size
		- (aWord asLowercase copyWithoutAll: 'aeiou') size.
	syllables < 1
		ifTrue: [ ^ 1 ]
		ifFalse: [ ^ syllables ]
]

{ #category : #processing }
CommentReadabilityChecker >> fleschKincaid: aSentence with: totalSyllableCount [
	"Calculates Flesch-Kincaid algorithm. This is the raw score not the grade version. For single sentences only"

	^ (206.835 - ((1.015 * (aSentence size)) / 1)) - ((84.6 * totalSyllableCount) / (aSentence size)).
]

{ #category : #'as yet unclassified' }
CommentReadabilityChecker >> initialize [
	super initialize.
	self lowerBound: 30.
	self upperBound: 40.
]

{ #category : #processing }
CommentReadabilityChecker >> isWordInDictionary: aWord [
	^ self syllableDictionary includesKey: aWord
	
]

{ #category : #processing }
CommentReadabilityChecker >> kincaidScoreOf: aSentence [
	^ self fleschKincaid: aSentence with: (self countSyallablesOf: aSentence).
	
]

{ #category : #accessing }
CommentReadabilityChecker >> lowerBound [
	^ lowerBound
]

{ #category : #accessing }
CommentReadabilityChecker >> lowerBound: aObject [
	lowerBound := aObject
]

{ #category : #processing }
CommentReadabilityChecker >> readabilityOf: aSentence [
	| kincaidScore |
	" 0 = good; 1 = needs toning; 2 = flabby."
	kincaidScore := self kincaidScoreOf: aSentence.
	kincaidScore > self upperBound 
	ifTrue: [ ^ 0 ]
	ifFalse: [ kincaidScore < self lowerBound ifTrue: [ ^ 1 ]
		ifFalse: [ ^ 2 ]
		 ]
]

{ #category : #'as yet unclassified' }
CommentReadabilityChecker >> syllableCount: aWord [
	"calculate syllables of words "
	|syllableCount|
	syllableCount := 0.
				(self isWordInDictionary: aWord)
				ifTrue: [  syllableCount := self syllableDictionary at: aWord ]
				ifFalse: [ syllableCount := self countSyllablesLight: aWord ].
			
	^ syllableCount
]

{ #category : #'accessing - data' }
CommentReadabilityChecker >> syllableDictionary [
	|rawData syllables|
	rawData := (NeoCSVReader on: ('/Users/poojarani/Downloads/Phd/Projects/Comment-Analysis/syllables.txt'
		asFileReference) readStream)
		addField;
		addIntegerField;
		upToEnd.
	syllables := (rawData collect: [ :e | e first -> e second ]) asOrderedDictionary.
	^ syllables.
]

{ #category : #accessing }
CommentReadabilityChecker >> upperBound [
	^ upperBound 
]

{ #category : #accessing }
CommentReadabilityChecker >> upperBound: aObject [
	upperBound := aObject.
]
