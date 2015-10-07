[![Build Status](https://travis-ci.org/Suseika/inflectible.svg?branch=master)](https://travis-ci.org/Suseika/inflectible)
[![Coverage Status](https://coveralls.io/repos/Suseika/inflectible/badge.svg?branch=195&service=github)](https://coveralls.io/github/Suseika/inflectible?branch=195)

# Inflectible

Inflectible is a flexible template engine with 
[inflection](https://en.wikipedia.org/wiki/Inflection). 
It can use correct word forms where other template engines can't.

# Maven dependency

```xml
<dependency>
    <groupId>org.tendiwa</groupId>
    <artifactId>inflectible</groupId>
    <version>0.2.0</version>
</dependency>
```

# What problem does it solve?

Many natural languages rely heavily on non-trivial rules of inflection. In order to
construct texts in those languages with variable members of sentences, we
can't always just concatenate strings: generally we have to know the grammatical
structure of sentences we're constructing, and we have to know how words in
particular form are spelled. For example, in Russian, a typical noun can have
up to a dozen forms that are written differently in different sentences, and
there is no simple "cram-it-in-printf" rule for how those forms are derived
from the dictionary form of a word.

In English it is not usually a problem. But even in English, sometimes just
concatenating strings is not enough to produce a grammatically correct sentence.

Consider this example: we need to display a message that
some cutting tool cuts paper well. With something like `printf` function,
we could use a template like this:

```
%s cuts paper well
```

We could pass `"Knife"` or `"Razor"`, but if we pass `"Scissors"`, then it
produces a grammatically incorrect sentence "Scissors cuts paper well". This
is just the most basic example how properly constructed sentences require the
template engine to be aware of inflection rules.

# How does it work?

*Inflectible* introduces two kinds of markup: vocabularies and templates.

In vocabularies, you put words of a language in all their various forms, and
assign each form a grammatical meaning:

```
WOLF (Noun) {
    wolf
    wolves <Plur>
}
CHILD (Noun) {
    child
    children <Plur>
}
SCISSORS (Noun) <Plur> {
    scissors
}
```

In templatuaries, you put templates. Templates declare arguments and describe
how those arguments are used to fill out the template:

```
actions.bite(subject, object) {
   [Subject] (and [subject]<Plur> are well known for their painful bites!) is biting a [object].
}
```

In your application, you have classes to represents the same concept that the
words of a language represent. Those classes would implement `Concept`
interface that require them to return the identifier of their lexeme:

```java
class Wolf implements Concept {
    @Override
    public String identifier() {
        return "WOLF";
    }
}
```

With those classes, you construct a `NativeSpeaker` that knows how to speak a
 particular language using proper inflection rules, and ask him to fill out a
  particular template with particular concepts:

```java
Wolf wolf = new Wolf();
Human girl = new Human("GIRL");
System.out.printf(
    nativeSpeaker.fillOut("actions.bite", wolf, girl);
);
// -> Output: Wolf (and wolves are known for their painful bites!) is biting a girl.
```

This may seem not very useful for English, but it makes a lot of sense e.g.
in Russian, where a lexeme for НОЖ (KNIFE) would look like this:
```
НОЖ (Сущ) <Муж Неодуш> {
    нож
    ножа   <Ед Р>
    ножу   <Ед Д>
    нож    <Ед В>
    ножом  <Ед Т>
    ноже   <Ед П>
    ножи   <Мн И>
    ножей  <Мн Р>
    ножам  <Мн Д>
    ножи   <Мн В>
    ножами <Мн Т>
    ноже   <Мн П>
}
```
There are 12 different forms a word НОЖ can assume under different
grammatical meanings, so choosing the correct one can become crucial.

Of course, it would be a pain to type all these words manually in a vocabulary
markup. But the good news are that a machine can often guess with very high
accuracy what would a particular word form would be, if we know the persistent
grammatical meaning of a word and its dictionary form. *Inflectible* can
generate those word forms for you, all you need to do is:

```
НОЖ (Сущ) <Муж Неодуш> {
    нож
    ...
}
```

That's the actual markup, and if template engine sees it, it can
automatically produce a lexeme equivalent to the previous tediously written
example. It even supports
[suppletion](https://en.wikipedia.org/wiki/Suppletion)!

```
ЧЕЛОВЕК (Сущ) <Муж Одуш> {
    человек
    люди   <Мн>
    людьми <Мн Т>
    ...
}
```

# What features is it going to provide?

The goals for version 1.0.0 are:

- Full automated word form generation support for every part of speech in
Russian and English;
- Flexible design that allows allows automating inflection in any flective
language;
- Agreement with numbers (двух коней, два коня, две лошади, пять коней, один
конь, миллион и двадцать один конь — that is the Russian for two horses, two
male horses, two female horses, five horses, one horse, million and twenty one
horses. Just look at all the different endings);
- Phonetic "agreement" (indefinite article "a"/"an" in English and "de/d'" in
French depend not on grammatical features of another word, but on its phonetical
features);
- Complete basic vocabularies for English and Russian — built-in vocabularies
with the most common words, such as articles, pronouns and numbers. It wouldn't
make sense to ask every user of the template engine to compose or copy their own
vocabulary for the most basic words of a language.
- [Multipart templates](https://github.com/Suseika/inflectible/issues/138)
for the cases when you want to split the result of filling a template into
logical parts;
- IntelliJ IDEA plugin for markup editing;
- Maven plugin for generating explicit lexemes from partially defined ones at
build time.
