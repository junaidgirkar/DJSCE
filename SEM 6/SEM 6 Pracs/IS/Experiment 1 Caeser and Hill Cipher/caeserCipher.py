# Encryption and decryption of a message using a caeser cipher

def encrypt(message, key):

    encrypted_message = ""
    for letter in message:
        if letter.isupper():
            encrypted_message += chr((ord(letter) + key - 64) % 26 + 65) 
        else:
            encrypted_message += chr((ord(letter) + key - 96) % 26 + 97) 
    return encrypted_message

plain_text = "UkraineIsACountryInEasternEurope"
key = 5

print("PLain text: ", plain_text)
print("Key: ", key)
print("Cipher Text : " + encrypt(plain_text, key))

def decrypt(cipher_text, key):
    decrypted_message = ""
    for letter in cipher_text:
        if letter.isupper():
            decrypted_message += chr((ord(letter) + key - 65) % 26 + 65) 
        else:
            decrypted_message += chr((ord(letter) + key - 97) % 26 + 97) 
    return decrypted_message

def brute_force_decrypt(cipher_text):
    for i in range(26):
        print("Key: ", abs(25 - i))
        print("Decrypted Text: " + decrypt(cipher_text, i))

brute_force_decrypt(encrypt(plain_text, key))