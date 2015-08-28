[![Build Status](https://travis-ci.org/Suseika/inflectible.svg?branch=master)](https://travis-ci.org/Suseika/inflectible)

# Inflectible

Inflectible is a flexible template engine with [inflection](https://en
.wikipedia.org/wiki/Inflection). It can use correct word forms where other
template engines can't.

# What problem does it solve?

Many languages rely heavily on non-trivial rules of inflection. In order to
construct texts in those languages with variable members of sentences, we
can't always just concatenate strings: we have to know the grammatical
structure of sentences, because words in such languages

In English it is not usually a problem. But even in English, sometimes just
concatenating strings is not enough to produce a grammatically correct sentence.

Consider this example: we need to display a message that
some cutting tool cuts paper well. With something like `printf` function,
we could use a template like this:

```
%s cuts paper well
```

We could pass `"Knife"` or `"Razor"`, but if we pass `"Scissors"`, then it
produces a grammatically incorrect sentence "Scissors cuts paper well".

# How does it work?

*Inflectible* introduces two kinds of markup: vocabularies and templates.

In vocabularies, you put words of a language in all their various forms, and
assign each form a grammatical meaning:

```
WOLF {
    wolf
    wolves <Plur>
}
CHILD {
    child
    children <Plur>
}
SCISSORS <Plur> {
    scissors
}
```

In templatuaries, you put templates. Templates declare arguments and describe
how those arguments are used to fill out the template:

```
actions.bite(subject, object) {
   [Subject] (and [subject]<Plur> are well known for their painful bites!) is biting [object].
}
```


