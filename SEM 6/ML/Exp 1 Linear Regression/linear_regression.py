# Importing Libraries
import numpy as np
import matplotlib.pyplot as plt
# Data for Regression
np.random.seed(0)
X = np.random.rand(50,1)
y = 2*X + np.random.rand(50,1)
# Visualizing Data
plt.scatter(X,y)
plt.xlabel("X")
plt.ylabel("y")
plt.show()
# Initializing Variables
m = X.shape[0]
epochs = 10000
theta = np.random.rand(2,1)
alpha = 0.01
print('Initial theta values', theta)
# Adding Bias
X = np.concatenate((np.ones(X.shape),X),axis=1)
# Cost Function
def computeCost(X,y,theta):
    m = X.shape[0]
    h_x = np.matmul(X,theta)
    J = (1/(2*m))*(sum(np.square(h_x-y)))
    return J
print('Initial Cost', computeCost(X,y,theta))
# Gradient Descent
X_transpose = np.transpose(X)
for i in range(epochs):
    h_x = np.matmul(X,theta)
    theta = theta - ((alpha/m) * np.matmul(X_transpose,h_x-y))
print('Theta from Gradient Descent', theta)
# Plotting the Regression Line
h_x = np.matmul(X,theta)
plt.scatter(X[:,1],y)
plt.plot(X[:,1], h_x,color='r')
plt.xlabel("X")
plt.ylabel("y")
plt.show()