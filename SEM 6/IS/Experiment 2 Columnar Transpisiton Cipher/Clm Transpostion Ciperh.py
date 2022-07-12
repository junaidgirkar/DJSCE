import math

key = "DJSCE"

# Encryption
def encryptMessage(msg):
    cipher = ""
    key_index = 0

    msg_len = float(len(msg))
    msg_lst = list(msg)
    key_lst = sorted(list(key))

    col = len(key)

    row = int(math.ceil(msg_len / col))

    fill_null = int((row * col) - msg_len)
    msg_lst.extend("_" * fill_null)

    matrix = [msg_lst[i : i + col] for i in range(0, len(msg_lst), col)]

    for _ in range(col):
        curr_idx = key.index(key_lst[key_index])
        cipher += "".join([row[curr_idx] for row in matrix])
        key_index += 1

    return cipher


# Decryption
def decryptMessage(cipher):
    decrypted_message = ""

    key_index = 0
    msg_indx = 0
    msg_len = float(len(cipher))
    msg_lst = list(cipher)

    col = len(key)

    row = int(math.ceil(msg_len / col))
    key_lst = sorted(list(key))

    deciphered_cipher_message = []
    for _ in range(row):
        deciphered_cipher_message += [[None] * col]

    for _ in range(col):
        curr_idx = key.index(key_lst[key_index])

        for j in range(row):
            deciphered_cipher_message[j][curr_idx] = msg_lst[msg_indx]
            msg_indx += 1
        key_index += 1

    try:
        decrypted_message = "".join(sum(deciphered_cipher_message, []))
    except TypeError:
        raise TypeError("This program cannot", "handle repeating words.")

    null_count = decrypted_message.count("_")

    if null_count > 0:
        return decrypted_message[:-null_count]

    return decrypted_message


msg = "Junaid Girkar"
print("\nPlaintext Message:", msg)

cipher = encryptMessage(msg)
print("\nCiphertext Message: {}".format(cipher))

decrypted_message = decryptMessage(cipher)
print("\nDecryped Message: {}\n".format(decrypted_message))
