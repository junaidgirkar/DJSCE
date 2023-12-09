import nltk
from nltk.corpus import wordnet as wn

nltk.download("omw-1.4")
nltk.download("wordnet")

synonyms = []
word = "call"
for syn in wn.synsets(word):
    for i in syn.lemmas():
        synonyms.append(i.name())
print(f"Synonyms of {word} are: ")
print(set(synonyms), "\n")

hyponyms = []
print(f"Hyponyms of {word} are: ")
for syn in wn.synsets(word):
    for i in syn.hyponyms():
        hyponyms.append(i.name().split(".")[0])
print(set(hyponyms), "\n")

hypernyms = []
for syn in wn.synsets(word):
    # print(syn.hypernym_distances())
    for i in syn.hypernym_distances():
        hypernyms.append((i[0].name().split(".")[0], i[1]))
print(f"Hypernyms of {word} are: ")
print(set(hypernyms), "\n")

vehicle = wn.synset("call.n.01")
typesOfVehicles = list(set([w for s in vehicle.closure(lambda s: s.hyponyms()) for w in s.lemma_names()]))
print(f"Hyponyms of Synset ID {vehicle} are: ")
print(set(typesOfVehicles))
