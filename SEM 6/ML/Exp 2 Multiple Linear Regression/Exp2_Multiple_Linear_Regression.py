# Importing Libraries
import numpy as np
import matplotlib.pyplot as plt
import seaborn as sns
import pandas as pd
# Extracting and Visualizing Data
data = pd.read_csv('Advertising.csv')
sns.pairplot(data)
data
# Classifying and Normalizing data into X(input) and Y(target)
X = data.iloc[:,2:]
X=(X-X.min())/(X.max()-X.min())
y = data.iloc[:,1]
y =(y-y.min())/(y.max()-y.min())
y = y.to_numpy()
yList = list()
for i in range(X.shape[0]):
    temp = list()
    temp.append(y[i])
    yList.append(temp)
yList
y = yList
# Initializing Variables
m = X.shape[0]
epochs = 10000
theta = np.random.rand(X.shape[1] + 1,1)
alpha = 0.01
print('Initial theta values', theta)
# Adding Bias
X = np.concatenate((np.ones([m,1]),X),axis=1)
# Cost Function
def computeCost(X,y,theta):
    m = X.shape[0]
    h_x = np.matmul(X,theta)
    J = (1/(2*m))*(sum(np.square(h_x-y)))
    return J
J = computeCost(X,y,theta)
J[0]
# Gradient Descent
X_transpose = np.transpose(X)
print(theta.shape)
for i in range(epochs):
    h_x = np.matmul(X,theta)
    theta = theta - ((alpha/m) * np.matmul(X_transpose,h_x-y))
print('Theta from Gradient Descent', theta)
# Checking Accuracy of Model by Mean Squared Error
from sklearn import metrics
y_pred = np.matmul(X,theta)
print("MEAN ABSOLUTE ERROR : ",metrics.mean_absolute_error(y,y_pred))
print("MEAN SQUARED ERROR : ", metrics.mean_squared_error(y,y_pred))
print("MEAN ERROR : ",np.sqrt(metrics.mean_squared_error(y,y_pred)))