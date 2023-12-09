import sys


def shift_character(c: str, bits: int) -> str:
    if ord(c) < 65:
        # Numbers
        return chr(((ord(c) - 48 + bits) % 10) + 48)
    else:
        # Uppercase Alphabets
        return chr(((ord(c) - 65 + bits) % 26) + 65)


def encrypt(plain_text: str, key: int) -> str:
    return "".join(shift_character(x, key) for x in plain_text)


def decrypt(cipher_text: str, key: int) -> str:
    return "".join(shift_character(x, -key) for x in cipher_text)


def brute_force(cipher_text: str) -> None:
    for i in range(26):
        print(f"Key: {i+1} | PT: {decrypt(cipher_text, i+1)}")


if __name__ == "__main__":
    plain_text = sys.argv[1].upper()
    n = int(sys.argv[2])

    print("\n> Caesar's Cipher\n")
    print(f"Plain Text: {plain_text}", end="\n\n")
    print(f"Key: {n}", end="\n\n")

    cipher_text = encrypt(plain_text, n)
    print(f"Cipher Text: {cipher_text}", end="\n\n")

    print("Brute Force: ")
    brute_force(cipher_text)
    print("")

    deciphered_text = decrypt(cipher_text, n)
    print(f"Deciphered Text: {deciphered_text}")
