import nltk
nltk.download('punkt')
text = "(eos) You book a flight (eos) I read a book (eos) You read (eos)"

unigram = text.split()
bigrm = list(nltk.bigrams(text.split()))
trigrm = list(nltk.trigrams(text.split()))

bigram = []
for element in bigrm:
  lst = ' '.join(map(str, element))
  bigram.append(lst)
bigram

fdist = nltk.FreqDist(bigram)
freq2 = []
for k,v in fdist.items():
    freq2.append((k,v))
freq2

fdist = nltk.FreqDist(unigram)
freq = []
for k,v in fdist.items():
    freq.append((k,v))

freq

txt = "(eos) You book a flight (eos)"
output = list(nltk.bigrams(txt.split()))

oput = []
for element in output:
  lst = ' '.join(map(str, element))
  oput.append(lst)

oput

prob = 1
for item in oput:
  print(item)
  word = item.split()[0]
  for element in freq2:
    if(item == element[0]):
      num = element[1]
      break
  for element in freq:
    if(word == element[0]):
      den = element[1]
      break
  print(item)
  print(num/den)
  prob = prob * num / den
prob
