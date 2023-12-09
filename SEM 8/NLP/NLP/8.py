import nltk

nltk.download("omw-1.4")
nltk.download("wordnet")
from nltk.corpus import wordnet as wn
from nltk.wsd import lesk

sentence = ["I", "went", "to", "the", "track", "to", "watch", "the", "car", "race", "."]

print(lesk(sentence, "track", "n"), "\n")
print(lesk(sentence, "track"), "\n")

syns = wn.synsets("track")
print(syns, "\n")

# print the word
print(syns[0].lemmas()[0].name(), "\n")

from nltk.corpus import wordnet as wn

for ss in wn.synsets("track"):
    print(ss, ss.definition())

print("\n")

print([(s, s.pos()) for s in wn.synsets("can")], "\n")

sentence = "I can kill seventy monsters in a minute".split()
print(lesk(sentence, "can"), "\n")
print(lesk(sentence, "can", pos="v"), "\n")

print(lesk("Ram adores Site".split(), "adores", synsets=[]))
