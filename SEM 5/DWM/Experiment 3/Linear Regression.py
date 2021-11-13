import warnings
import numpy as np
import pandas as pd
import seaborn as sns
import matplotlib.pyplot as plt

from sklearn.metrics import mean_squared_error
from sklearn.linear_model import LinearRegression
from sklearn.preprocessing import LabelEncoder
from sklearn.metrics import accuracy_score
import matplotlib.pyplot as plb
from sklearn.model_selection import train_test_split

sns.set()
warnings.simplefilter("ignore")

df = pd.read_csv("StudentsPerformance.csv")
df.head()

print(df.info())

df['final score'] = df.apply(lambda x : (x['math score'] + x['reading score'] + x['writing score']) / 3, axis=1)

df.head()



data2 = df.drop('final score', axis=1)
plt.figure(figsize=(16, 6))
sns.boxplot(data=data2)


df = df.apply(LabelEncoder().fit_transform)

# MULTIVARIATE

X = df.drop('final score', axis=1)
y = df['final score']

X_train, X_test, y_train, y_test = train_test_split(X,y,test_size = 0.2)

lr = LinearRegression()

lr.fit(X_train, y_train)

pred = lr.predict(X_test)

lr.score(X_test, y_test)


accuracy = mean_squared_error(y_test, pred)
print('Mean Squared Error: ', accuracy)

# UNIVARIATE
sns.scatterplot(df["writing score"],df["final score"])
plt.savefig('scp-1', dpi=500)
m, b = np.polyfit(df["writing score"], df["final score"], 1)

plt.plot(df["writing score"], m*df["writing score"] + b)

X_uni = df['writing score']
y_uni = df['final score']

X_uni_train, X_uni_test, y_uni_train, y_uni_test = train_test_split(X_uni,y_uni,test_size = 0.2)

lr2 = LinearRegression()


X_uni_train = X_uni_train.reshape(-1,1)
X_uni_test = X_uni_test.values.reshape(-1,1)


lr2.fit(X_uni_train, y_uni_train)

pred_uni = lr2.predict(X_uni_test)

lr2.score(X_uni_test, y_uni_test)

accuracy_uni = mean_squared_error(y_uni_test, pred_uni)
print('Mean Squared Error: ', accuracy_uni)