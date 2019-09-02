"
Readability checker for a sentence
"
Class {
	#name : #BaselineOfReadabilityChecker,
	#superclass : #BaselineOf,
	#category : #BaselineOfReadabilityChecker
}

{ #category : #baselines }
BaselineOfReadabilityChecker >> baseline: spec [
	<baseline>
	
	spec for: #'common' do: [
		spec
			"GT-Examples is examples and testing framework"
			baseline: 'GToolkitExamplesEngine' with: [ spec repository: 'github://feenkcom/gtoolkit-examples/src' ];
			baseline: 'Camel' with: [ spec repository: 'github://poojaruhal/Moose-Camel-Comments/src' ];
			baseline: 'NeoJSON' with: [ spec repository: 'github://svenvc/NeoJSON:master/repository' ].

		spec package: #'ReadabilityChecker' with: [ spec requires: #(GToolkitExamplesEngine Camel NeoJSON Atlas) ].
		spec group: 'default' with: #('ReadabilityChecker') ]

]
