[![Build Status](https://travis-ci.org/Suseika/liblexeme.svg?branch=master)](https://travis-ci.org/Suseika/liblexeme)

# liblexeme

A java library for user interface internationalization and localization. 

It allows writing texts that use grammatically appropriate lexemes (word forms). 

Localizations consist of:
- Vocabularies — collections of various forms of a single word having specific [grammatical categories](https://en
.wikipedia.org/wiki/Grammatical_category).
- Text corpuses — texts that have stubs instead of some of the words.
To produce a localized text, liblexeme processes a text in a text corpus replacing stubs with correct lexemes.

This approach is not completely correct in a linguistic sense, but it provides a good abstraction to translate 
applications (particularly games with full-blown ontologies) to multiple languages where usage of correct word forms is crucial (more crucial than in English).
