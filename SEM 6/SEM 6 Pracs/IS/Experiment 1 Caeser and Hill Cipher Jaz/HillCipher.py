import sys
import cv2
import numpy as np


def random_key_matrix(n):
    Mod = 256
    k = 23

    d = np.random.randint(256, size=(int(n / 2), int(n / 2)))
    identity = np.identity(int(n / 2))
    a = np.mod(-d, Mod)

    b = np.mod((k * np.mod(identity - a, Mod)), Mod)
    k = np.mod(np.power(k, 127), Mod)
    c = np.mod((identity + a), Mod)
    c = np.mod(c * k, Mod)

    A1 = np.concatenate((a, b), axis=1)
    A2 = np.concatenate((c, d), axis=1)
    A = np.concatenate((A1, A2), axis=0)

    return A, A


def get_key(size: int) -> np.ndarray:
    key, key_inv = random_key_matrix(size)
    cv2.imwrite("Key.png", key)
    return key, key_inv


def load_image(filename: str) -> np.ndarray:
    base_img = cv2.imread(filename)
    return cv2.cvtColor(base_img, cv2.COLOR_RGB2GRAY)


def encrypt(key: np.ndarray, base_img: np.ndarray) -> np.ndarray:
    encrypted_img = np.matmul(key, base_img) % 256
    cv2.imwrite("encrypted.png", encrypted_img)
    return encrypted_img


def decrypt(key_inv: np.ndarray, encrypted_img: np.ndarray) -> np.ndarray:
    decrypted_img = np.matmul(key_inv, encrypted_img) % 256
    cv2.imwrite("decrypted.png", decrypted_img)
    return decrypted_img


if __name__ == "__main__":
    original = load_image(sys.argv[1])
    key, key_inv = get_key(original.shape[0])

    encrypted_img = encrypt(key, original)
    decrypted_img = decrypt(key_inv, encrypted_img)
