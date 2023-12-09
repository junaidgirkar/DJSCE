import nltk

# !pip install stanza
# !pip install indic-nlp-library
# !pip install indic-nlp-datasets
# !pip install inltk
# !pip install spacy
# !pip install contractions
# !pip install word2number
# nltk.download('punkt')
# nltk.download('wordnet')
# nltk.download('stopwords')
# nltk.download('indian')

from nltk.corpus import indian

nltk.corpus.indian.words("hindi.pos")
from indicnlp.normalize.indic_normalize import IndicNormalizerFactory

from idatasets import load_devdas
from nltk.stem import PorterStemmer
from nltk.corpus import stopwords
from nltk.tokenize import word_tokenize
from nltk.stem import WordNetLemmatizer
from nltk.stem import PorterStemmer
import spacy
import pandas as pd

# import stanfordnlp

from bs4 import BeautifulSoup
import unicodedata
import contractions
from word2number import w2n
import re
import stanza

from inltk.inltk import setup
from inltk.inltk import tokenize

# setup('hi')
# stanfordnlp.download('hi')
# stanfordnlp.download('en')
# stanza.download('hi')


def remove_html(text):
    soup = BeautifulSoup(text, "lxml")
    text = soup.get_text()
    return str(text)


def remove_whitespace(text):
    text = " ".join(text.split())
    return text


def accented_to_ascii(text):
    try:
        text = unicode(text, "utf-8")
    except (TypeError, NameError):  # unicode is a default on python 3
        pass
    text = unicodedata.normalize("NFD", text)
    text = text.encode("ascii", "ignore")
    text = text.decode("utf-8")
    return str(text)


def expand_contractions(text):
    # N/A for Hindi
    expanded_words = []
    for word in text.split():
        expanded_words.append(contractions.fix(word))
    expanded_text = " ".join(expanded_words)
    return expanded_text


def remove_special(text):
    text = text.split()
    text = " ".join(x for x in text if not x.isalnum())
    text = text.split()
    special_char_list = ["$", "@", "#", "&", "%"]
    text = " ".join([k for k in text if k not in special_char_list])
    text = " ".join(text.split())
    return text


def text_to_lowercase(text):
    # N/A for Hindi
    text = text.lower()
    return text


def number_word_to_numeric(text):
    text = text.split()
    output = ""
    for i in text:
        try:
            res = w2n.word_to_num(i)
        except:
            res = i
        output += str(res) + " "
    output = output.rstrip()
    return output


def remove_number(text):
    res = " ".join([i for i in text if not i.isdigit()])
    return res


def remove_stop_words(text):
    stop1 = open("drive/My Drive/College/stopwords_1.txt")
    stop2 = open("drive/My Drive/College/stopwords_2.txt")

    stop_words1 = []
    stop_words2 = []

    for x in stop1:
        stop_words1.append(x)

    for x in stop2:
        stop_words2.append(x)

    stop_words = stop_words1 + stop_words2
    stop_words = list(set(stop_words))

    word_tokens = word_tokenize(text)
    filtered_sentence = []

    for w in word_tokens:
        if w not in stop_words:
            filtered_sentence.append(w)

    filtered_sentence = " ".join(filtered_sentence)
    return filtered_sentence


def lemmatization(text):
    nlp = stanza.Pipeline(lang="hi", processors="tokenize, pos, lemma")
    doc = nlp(text)
    parsed_text = {"word": [], "lemma": []}
    for sent in doc.sentences:
        for wrd in sent.words:
            parsed_text["word"].append(wrd.text)
            parsed_text["lemma"].append(wrd.lemma)
    return pd.DataFrame(parsed_text)


def tokenization(text):
    tokenized_text = tokenize(text, "hi")
    return tokenized_text


def stemming(text):
    ps = PorterStemmer()
    text = text.split()
    output = ""
    for i in text:
        res = ps.stem(i)
        output += str(res) + " "
    return output


def text_normalization(text):
    # remove_nuktas = False
    factory = IndicNormalizerFactory()
    normalizer = factory.get_normalizer("hi")
    text = normalizer.normalize(text)
    return text


def pos(text):
    nlp = stanza.Pipeline(lang="hi", processors="tokenize, pos, lemma")
    doc = nlp(text)
    parsed_text = {"word": [], "upos": [], "xpos": []}
    for sent in doc.sentences:
        for wrd in sent.words:
            parsed_text["word"].append(wrd.text)
            parsed_text["upos"].append(wrd.upos)
            parsed_text["xpos"].append(wrd.xpos)
    return pd.DataFrame(parsed_text)


text = load_devdas()
paragraphs = list(text.data)
text = " ".join(paragraphs)
text
