# Project 3: Hash Table Experiment

* Author: Aria Strasser
* Class: CS321 Section 002
* Semester: Spring

## Overview

The Hashtable Experiement program loads the user's chosen type of data
into two hashtables, one run by linear probing, and one by double
hashing. It then compares the two and prints out data such as the amount
of insertions, the amount of duplicates, average amount of probes, and the
table size.

## Reflection

I had a lot of trouble with this assignment, although most of it ended up
stemming from one small error. I also kept rewriting my `HashTable` and
`HashObject` classes a lot because I had some issues getting them to work
together, and figuring out what data needed to be in which file. It was
definitely interesting starting from 0 and working up to a fully functional
program.

My main issue came from after I built the `HashtableExperiment` class. Everything
appeared to be working fine, except the amount of probes was incorrect for each
entry, and I had 44 extra insertions/duplicates. I tried working around this on
my own for a while, but couldn't figure it out. I asked a TA for help, and they
had me change a few things, such as using a reader instead of a scanner to read
the words in, and change where I accidentally incremented the probes of the
object in the table rather than the one I was inserting. The big issue though,
took us an hour to find, and it was just that I accidentally had a <= instead of
a < when inserting objects!

## Compiling and Using

To compile the code, use the command `javac HashtableExperiment.java`

The program takes 2-3 arguments. The first should be an integer 
that represents the data type you would like to fill the tables with, 1 being
integers, 2 being longs (date), and 3 being strings from the word file. The second
argument is a double that determines the load factor for the table. The last
optional argument is what type of debug info you would like printed, with 0 (the
default) being a summary, 1 being a summary and dump files, and 2 being a print of
each attempted insertion and whether it was inserted or was a duplicate. The 
command should look something like `java HashtableExperiment 1 0.5 0`

## Results 

Integers
| Insertions    | Load Factor   | Avg.Probes Linear  | Avg.Probes Double  |
| ------------: | -------------:| ------------------:| ------------------:|
| 47897         | 0.50          | 1.49               | 1.38               |
| 57475         | 0.60          | 1.75               | 1.52               |
| 67055         | 0.70          | 2.17               | 1.72               |
| 76633         | 0.80          | 2.98               | 2.01               |
| 86212         | 0.90          | 5.42               | 2.55               |
| 91003         | 0.95          | 11.81              | 3.18               |
| 94836         | 0.99          | 49.97              | 4.69               |


Longs
| Insertions    | Load Factor   | Avg.Probes Linear  | Avg.Probes Double  |
| ------------: | -------------:| ------------------:| ------------------:|
| 47896         | 0.50          | 1.28               | 1.38               |
| 57475         | 0.60          | 1.44               | 1.67               |
| 67054         | 0.70          | 1.60               | 1.98               |
| 76633         | 0.80          | 1.82               | 2.53               |
| 86212         | 0.90          | 2.18               | 3.14               |
| 91002         | 0.95          | 2.70               | 3.80               |
| 94834         | 0.99          | 5.41               | 5.33               |


Strings
| Insertions    | Load Factor   | Avg.Probes Linear  | Avg.Probes Double  |
| ------------: | -------------:| ------------------:| ------------------:|
| 1305930       | 0.50          | 1.60               | 1.39               |
| 1587659       | 0.60          | 2.15               | 1.53               |
| 1869206       | 0.70          | 3.60               | 1.72               |
| 2147748       | 0.80          | 6.71               | 2.02               |
| 2840050       | 0.90          | 19.81              | 2.57               |
| 3013622       | 0.95          | 110.59             | 3.19               |
| 3024134       | 0.99          | 471.67             | 4.70               |

## Sources used

Other than the lecture notes, class lab files, text book, TA, and some of
my old Java projects from previous CS classes, I used no outside sources.