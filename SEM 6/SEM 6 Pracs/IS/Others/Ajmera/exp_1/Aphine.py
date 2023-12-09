import cv2
import numpy as np

def encrypt(img_name: str, key1, key2):
    # read the image provided
    img = cv2.imread(img_name, cv2.IMREAD_COLOR)
    # Convert image to grey-scale
    gray_image = cv2.cvtColor(img, cv2.COLOR_BGR2GRAY)
    
    # Convert to numpy array for mathematical calculations
    grey_arr = np.asarray(gray_image)
    temp = (grey_arr * key1) % 256
    cipher = (temp + key2) % 256
    cv2.imwrite('encrypted.png',cipher)

def decrypt(img_name: str, key1, key2):
    # Read the encrypted image
    img = cv2.imread(img_name, cv2.IMREAD_COLOR)

    # Calculate multiplicative inverse of key1
    m_inverse = pow(key1, -1, 256)
    decrypt_temp = (img - key2) % 256
    decrypt = (decrypt_temp * m_inverse) % 256

    # Write the decrypted image
    cv2.imwrite('decrypt.jpg',decrypt)



if __name__ == '__main__':

    # Picking 2 Co-prime keys
    key1 = 11
    key2 = 17
    image_name = 'img1.png'
    encrypt(img_name=image_name, key1=key1, key2=key2)
    decrypt(img_name='encrypted.png', key1=key1, key2=key2)