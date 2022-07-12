import numpy as np
import matplotlib.pyplot as plt
import pandas as pd
import seaborn as sns
from sklearn.model_selection import train_test_split
from sklearn.preprocessing import StandardScaler
from sklearn.metrics import (
confusion_matrix,
accuracy_score,
precision_score,
recall_score,
)
from sklearn.svm import SVC
from matplotlib.colors import ListedColormap
datasets = pd.read_csv("Social_Network_Ads.csv")
print("**********************************Radial Basis Function**********************************")
print("Dataset used : Social_Networks_Ads.csv")
print("First 5 rows of dataset : ")
print(datasets.head())
x1 = datasets[["Age", "EstimatedSalary"]]
y1 = datasets[["Purchased"]]
X = datasets.iloc[:, [2, 3]].values
Y = datasets.iloc[:, 4].values
print ("Features :", x1.columns.values.tolist())
print ("Labels :", y1.columns.values.tolist())
(X_Train, X_Test, Y_Train, Y_Test) = train_test_split(
X, Y, test_size=0.25, random_state=0
)
sc_X = StandardScaler()
X_Train = sc_X.fit_transform(X_Train)
X_Test = sc_X.transform(X_Test)
classifier1 = SVC(kernel="rbf", random_state=0)
classifier1.fit(X_Train, Y_Train)
Y_Pred1 = classifier1.predict(X_Test)
cm1 = confusion_matrix(Y_Test, Y_Pred1)
print("Radial Basis Function : ")
sns.heatmap(cm1, square=True, annot=True, cmap="PiYG")
plt.xlabel("Predicted label")
plt.ylabel("True label")
plt.title("Radial Basis Function")
plt.show()
print ("Radial Basis Function Accuracy :", accuracy_score(Y_Test, Y_Pred1))
print ("Radial Basis Function Precision :", precision_score(Y_Test, Y_Pred1))
print ("Radial Basis Fynction Recall :", recall_score(Y_Test, Y_Pred1))
(X_Set, Y_Set) = (X_Train, Y_Train)
(X1, X2) = np.meshgrid(
np.arange(start=X_Set[:, 0].min() - 1, stop=X_Set[:, 0].max() + 1, step=0.01),
np.arange(start=X_Set[:, 1].min() - 1, stop=X_Set[:, 1].max() + 1, step=0.01),
)
plt.contourf(
X1,
X2,
classifier1.predict(np.array([X1.ravel(), X2.ravel()]).T).reshape(X1.shape),
alpha=0.75,
cmap=ListedColormap(("red", "orange")),
)
plt.xlim(X1.min(), X1.max())
plt.ylim(X2.min(), X2.max())
for (i, j) in enumerate(np.unique(Y_Set)):
    plt.scatter(
    X_Set[Y_Set == j, 0],
    X_Set[Y_Set == j, 1],
    c=ListedColormap(("red", "orange"))(i),
    label=j,
    )
plt.title("Radial Basis Function (on Training set)")
plt.xlabel("Age")
plt.ylabel("Estimated Salary")
plt.legend()
plt.show()
(X_Set, Y_Set) = (X_Test, Y_Test)
(X1, X2) = np.meshgrid(
np.arange(start=X_Set[:, 0].min() - 1, stop=X_Set[:, 0].max() + 1, step=0.01),
np.arange(start=X_Set[:, 1].min() - 1, stop=X_Set[:, 1].max() + 1, step=0.01),
)
plt.contourf(
X1,
X2,
classifier1.predict(np.array([X1.ravel(), X2.ravel()]).T).reshape(X1.shape),
alpha=0.75,
cmap=ListedColormap(("green", "blue")),
)
plt.xlim(X1.min(), X1.max())
plt.ylim(X2.min(), X2.max())
for (i, j) in enumerate(np.unique(Y_Set)):
    plt.scatter(
    X_Set[Y_Set == j, 0],
    X_Set[Y_Set == j, 1],
    c=ListedColormap(("green", "blue"))(i),
    label=j,
    )
plt.title("Radial Basis Function (on Test set)")
plt.xlabel("Age")
plt.ylabel("Estimated Salary")
plt.legend()
plt.show()