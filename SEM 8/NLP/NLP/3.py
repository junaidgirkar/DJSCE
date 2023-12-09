from pprint import pprint

import en_core_web_sm

nlp = en_core_web_sm.load()

import nltk

ex = "The bilateral trade between the two countries is expected to rise from existing 27 billion USD to 45 billion USD in the next five years, union minister Piyush Goyal said as he signed the deal with Australian trade minister Dan Tehan."


def preprocess(sent):
    sent = nltk.word_tokenize(sent)
    sent = nltk.pos_tag(sent)
    return sent


sent = preprocess(ex)
print(sent)
pattern = "NP: {<DT>?<JJ>*<NN>}"
cp = nltk.RegexpParser(pattern)
tree = cp.parse(sent)
for subtree in tree.subtrees():
    print(subtree)

doc = nlp(ex)
pprint([(X.text, X.label_) for X in doc.ents])
tree.draw()
