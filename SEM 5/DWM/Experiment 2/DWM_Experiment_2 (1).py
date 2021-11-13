#!/usr/bin/env python
# coding: utf-8

# NAIVE BAYERS CLASSIFICATION FOR DWM EXPERIEMNT 2

# In[5]:


import numpy as np
import pandas as pd #FOR DATA PROCESSING
import matplotlib.pyplot as plt # FOR DISPLAYING CHARTS
import seaborn as sns


# In[6]:


iris = pd.read_csv('iris.data')


# In[7]:


iris.head(10)


# In[8]:


headerList = ["SepalLengthCm", "SepalWidthCm", "PetalLengthCm", "PetalWidthCm", "Species"]


# In[9]:


iris.to_csv("header_iris.csv", header=headerList, index=False)


# In[10]:


iris = pd.read_csv("header_iris.csv")


# In[11]:


iris.head()


# In[12]:


len(iris['Species'].unique())


# In[13]:


iris['Species'].unique()


# In[14]:


iris.describe(include="all")


# In[15]:


iris.info()


# In[16]:


iris.isnull().sum()


# In[19]:


import missingno as msno
msno.bar(iris, figsize=(6,6), color="red")
plt.show()


# In[20]:


scatter = sns.relplot(x="SepalLengthCm", y="SepalWidthCm", data=iris, hue="Species", style="Species")
scatter.fig.set_size_inches(10,6)
plt.show()


# In[21]:


plt.subplots(figsize = (8,8))
sns.heatmap(iris.corr(), annot=True, fmt="f").set_title("Correlation of attributes")
plt.show()


# In[24]:


from sklearn.model_selection import train_test_split


# In[25]:


X = iris.iloc[:,0:4].values
y = iris.iloc[:,4].values


# In[26]:


from sklearn.preprocessing import LabelEncoder
le = LabelEncoder()
y = le.fit_transform(y)


# With test_size = 0.3, efficiency = 89%

# In[27]:


X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.5, random_state=0)


# In[28]:


from sklearn.preprocessing import StandardScaler
sc = StandardScaler()
X_train = sc.fit_transform(X_train)
X_test = sc.transform(X_test)


# NAIVE BAYERS
# 

# In[29]:


from sklearn.naive_bayes import GaussianNB


# In[30]:


nvclassifier = GaussianNB()
nvclassifier.fit(X_train, y_train)


# In[31]:


y_pred = nvclassifier.predict(X_test)
print(y_pred)

from sklearn.metrics import accuracy_score
accuracy = accuracy_score(y_test,y_pred)
print(accuracy)


# In[42]:


#Metrics
from sklearn.metrics import make_scorer, accuracy_score,precision_score
from sklearn.metrics import classification_report
from sklearn.metrics import confusion_matrix
from sklearn.metrics import accuracy_score ,precision_score,recall_score,f1_score

#Model Select
from sklearn.model_selection import KFold,train_test_split,cross_val_score
from sklearn.ensemble import RandomForestClassifier
from sklearn.model_selection import train_test_split
from sklearn.linear_model import  LogisticRegression
from sklearn.ensemble import RandomForestClassifier
from sklearn import linear_model
from sklearn.linear_model import SGDClassifier
from sklearn.tree import DecisionTreeClassifier
from sklearn.neighbors import KNeighborsClassifier
from sklearn.svm import SVC, LinearSVC
from sklearn.naive_bayes import GaussianNB


# In[43]:


gaussian = GaussianNB()
gaussian.fit(X_train, y_train)
Y_pred = gaussian.predict(X_test) 
accuracy_nb=round(accuracy_score(y_test,Y_pred)* 100, 2)
acc_gaussian = round(gaussian.score(X_train, y_train) * 100, 2)

cm = confusion_matrix(y_test, Y_pred)
accuracy = accuracy_score(y_test,Y_pred)
precision =precision_score(y_test, Y_pred,average='micro')
recall =  recall_score(y_test, Y_pred,average='micro')
f1 = f1_score(y_test,Y_pred,average='micro')
print('Confusion matrix for Naive Bayes\n',cm)
print('accuracy_Naive Bayes: %.3f' %accuracy)
print('precision_Naive Bayes: %.3f' %precision)
print('recall_Naive Bayes: %.3f' %recall)
print('f1-score_Naive Bayes : %.3f' %f1)


# DECISION TREE

# In[45]:


decision_tree = DecisionTreeClassifier() 
decision_tree.fit(X_train, y_train)  
Y_pred = decision_tree.predict(X_test) 
accuracy_dt=round(accuracy_score(y_test,Y_pred)* 100, 2)
acc_decision_tree = round(decision_tree.score(X_train, y_train) * 100, 2)

cm = confusion_matrix(y_test, Y_pred)
accuracy = accuracy_score(y_test,Y_pred)
precision =precision_score(y_test, Y_pred,average='micro')
recall =  recall_score(y_test, Y_pred,average='micro')
f1 = f1_score(y_test,Y_pred,average='micro')
print('Confusion matrix for DecisionTree\n',cm)
print('accuracy_DecisionTree: %.3f' %accuracy)
print('precision_DecisionTree: %.3f' %precision)
print('recall_DecisionTree: %.3f' %recall)
print('f1-score_DecisionTree : %.3f' %f1)

