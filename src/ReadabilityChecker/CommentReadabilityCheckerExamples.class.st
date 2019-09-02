Class {
	#name : #CommentReadabilityCheckerExamples,
	#superclass : #GtExample,
	#category : #ReadabilityChecker
}

{ #category : #'as yet unclassified' }
CommentReadabilityCheckerExamples >> commentReadabilityChecker [
	<gtExample>
	^ CommentReadabilityChecker new.
]

{ #category : #'as yet unclassified' }
CommentReadabilityCheckerExamples >> kincaidScore [
	<gtExample>
	|actual expected aSentence|
	aSentence := #(Hello My name is Pharo).
	actual :=  self commentReadabilityChecker kincaidScoreOf: aSentence.
	expected := 83.32. "according to the formula"
	self assert: (actual truncated) equals: (expected truncated).
	 ^ actual
]

{ #category : #'as yet unclassified' }
CommentReadabilityCheckerExamples >> readabilityScore [
	<gtExample>
	|actual expected aSentence|
	aSentence := #(Hello My name is Pharo).
	actual :=  self commentReadabilityChecker readabilityOf:  aSentence.
	expected := 0. "means the sentence is good."
	self assert: actual equals: expected.
	^ actual.
]

{ #category : #'as yet unclassified' }
CommentReadabilityCheckerExamples >> syllableOfSentence [
	<gtExample>
	|actual expected aSentence|
	aSentence := #(Hello My name is Pharo).
	actual :=  self commentReadabilityChecker countSyallablesOf: aSentence.
	expected := 7. "total syllable count"
	self assert: actual equals: expected.
	^ actual.
]

{ #category : #'as yet unclassified' }
CommentReadabilityCheckerExamples >> syllableOfWord [
	<gtExample>
	|actual expected|
	actual :=  self commentReadabilityChecker syllableCount: 'hyphenation'.
	expected := 4. "syllable in hyphenation = 4 according to several dicitionary"
	self assert: actual equals: expected.
	^ actual.
]
