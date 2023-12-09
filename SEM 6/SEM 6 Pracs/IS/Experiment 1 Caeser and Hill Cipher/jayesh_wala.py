import cv2
import numpy as np
img = cv2.imread('Hello.jpg',cv2.IMREAD_GRAYSCALE)
cv2.imwrite('gray.jpg',img)
def generate_key_matrix(n): #Creating a Involutory Matrix
    Mod = 256
    k = 23
    d = np.random.randint(256, size = (int(n/2),int(n/2)))
    I = np.identity(int(n/2))
    a = np.mod(-d,Mod)
    b = np.mod((k * np.mod(I - a,Mod)),Mod)
    k = np.mod(np.power(k,127),Mod)
    c = np.mod((I + a),Mod)
    c = np.mod(c * k, Mod)
    A1 = np.concatenate((a,b), axis = 1)
    A2 = np.concatenate((c,d), axis = 1)
    A = np.concatenate((A1,A2), axis = 0)
    return A
key = generate_key_matrix(img.shape[0])
encrypted_image = np.mod(np.matmul(key,img),256) #Encryption
cv2.imwrite('encrypted.jpg',encrypted_image)
key_inv = key # For a involutory matrix the matrix is its own inverse
decrypted_image = np.mod(np.matmul(key_inv,encrypted_image),256) #Decryption
cv2.imwrite('decrypted.jpg',decrypted_image)
