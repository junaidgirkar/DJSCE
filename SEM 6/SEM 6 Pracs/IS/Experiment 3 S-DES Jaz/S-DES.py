import numpy as np

# Constants

P10 = np.array([3, 5, 2, 7, 4, 10, 1, 9, 8, 6]) - 1
P8 = np.array([6, 3, 7, 4, 8, 5, 10, 9]) - 1
P4 = np.array([2, 4, 3, 1]) - 1

IP = np.array([2, 6, 3, 1, 4, 8, 5, 7]) - 1
IP_inv = np.array([4, 1, 3, 5, 7, 2, 8, 6]) - 1
EP = np.array([4, 1, 2, 3, 2, 3, 4, 1]) - 1

S0 = np.array([[1, 0, 3, 2], [3, 2, 1, 0], [0, 2, 1, 3], [3, 1, 3, 2]])
S1 = np.array([[0, 1, 2, 3], [2, 0, 1, 3], [3, 0, 1, 0], [2, 1, 0, 3]])


# Internal Functions


def _process_input(input_text: str) -> np.ndarray:
    binary_string: str = format(int(input_text, 16), "08b")
    binary_array: np.ndarray = np.array(list(binary_string), "uint8")
    return binary_array


def _process_output(input_array: np.ndarray) -> str:
    output_binary_text: str = "".join(str(x) for x in input_array.tolist())
    output_hex_text: str = format(int(output_binary_text, 2), "02x").upper()
    return output_hex_text


def _process_key(input_key: str) -> np.ndarray:
    binary_array: np.ndarray = np.array(list(input_key), "uint8")
    return binary_array


def _generate_key(initial_key: np.ndarray, verbose: bool = False) -> tuple:
    key = initial_key
    key = key[P10]

    left_key = np.roll(key[:5], -1)
    right_key = np.roll(key[5:], -1)

    key1 = np.concatenate((left_key, right_key))[P8]

    left_key = np.roll(left_key, -2)
    right_key = np.roll(right_key, -2)

    key2 = np.concatenate((left_key, right_key))[P8]

    if verbose:
        print("Key 1: ", key1, "\nKey 2: ", key2)

    return key1, key2


def _substitute(input_text: np.ndarray) -> np.ndarray:
    left_text_inside: np.ndarray = input_text[:4]
    right_text_inside: np.ndarray = input_text[4:]

    left_row: np.ndarray = left_text_inside[[0, 3]]
    left_row = left_row.dot(1 << np.arange(left_row.shape[-1] - 1, -1, -1))

    left_column: np.ndarray = left_text_inside[[1, 2]]
    left_column = left_column.dot(1 << np.arange(left_column.shape[-1] - 1, -1, -1))

    right_row: np.ndarray = right_text_inside[[0, 3]]
    right_row = right_row.dot(1 << np.arange(right_row.shape[-1] - 1, -1, -1))

    right_column: np.ndarray = right_text_inside[[1, 2]]
    right_column = right_column.dot(1 << np.arange(right_column.shape[-1] - 1, -1, -1))

    left_text_inside = np.unpackbits(np.array([S0[left_row][left_column]], dtype=np.uint8))[-2:]
    right_text_inside = np.unpackbits(np.array([S1[right_row][right_column]], dtype=np.uint8))[-2:]

    inprocess_text = np.concatenate((left_text_inside, right_text_inside))

    return inprocess_text


def _stage_1(input_text: np.ndarray, key1: np.ndarray) -> np.ndarray:
    inprocess_text: np.ndarray = input_text
    inprocess_text = inprocess_text[IP]
    left_text: np.ndarray = inprocess_text[:4]
    right_text: np.ndarray = inprocess_text[4:]

    inprocess_text = right_text[EP]
    inprocess_text = np.bitwise_xor(inprocess_text, key1)
    inprocess_text = _substitute(inprocess_text)
    inprocess_text = inprocess_text[P4]
    inprocess_text = np.bitwise_xor(inprocess_text, left_text)
    inprocess_text = np.concatenate((right_text, inprocess_text))

    return inprocess_text


def _stage_2(input_text: np.ndarray, key2: np.ndarray) -> np.ndarray:
    inprocess_text: np.ndarray = input_text
    left_text: np.ndarray = inprocess_text[:4]
    right_text: np.ndarray = inprocess_text[4:]

    inprocess_text = right_text[EP]
    inprocess_text = np.bitwise_xor(inprocess_text, key2)
    inprocess_text = _substitute(inprocess_text)
    inprocess_text = inprocess_text[P4]
    inprocess_text = np.bitwise_xor(inprocess_text, left_text)
    inprocess_text = np.concatenate((inprocess_text, right_text))
    inprocess_text = inprocess_text[IP_inv]

    return inprocess_text


def _process(input_text: str, initial_key: str, method: str, verbose: bool = False) -> str:
    initial_key_array = _process_key(input_key=initial_key)
    input_text_array = _process_input(input_text=input_text)

    if method == "encrypt":
        key1, key2 = _generate_key(initial_key=initial_key_array, verbose=verbose)
    elif method == "decrypt":
        key2, key1 = _generate_key(initial_key=initial_key_array, verbose=verbose)
    else:
        raise ValueError("Invalid Method")

    stage_1_cipher_text = _stage_1(input_text=input_text_array, key1=key1)
    stage_2_cipher_text = _stage_2(input_text=stage_1_cipher_text, key2=key2)

    output_text = _process_output(input_array=stage_2_cipher_text)

    return output_text


# External Functions


def encrypt(plain_text: str, initial_key: str, verbose: bool = False) -> str:
    return _process(
        input_text=plain_text,
        initial_key=initial_key,
        method="encrypt",
        verbose=verbose,
    )


def decrypt(cipher_text: str, initial_key: str, verbose: bool = False) -> str:
    return _process(
        input_text=cipher_text,
        initial_key=initial_key,
        method="decrypt",
        verbose=verbose,
    )


# Driver Function

if __name__ == "__main__":
    plain_text = input("\nEnter plain text: ")
    initial_key = input("Enter initial Key: ")

    print("\nEncrypting...")
    cipher_text = encrypt(plain_text=plain_text, initial_key=initial_key, verbose=True)
    print("\nCipher Text: ", cipher_text)

    print("\nDecrypting...")
    deciphered_text = decrypt(cipher_text=cipher_text, initial_key=initial_key, verbose=True)
    print("\nDeciphered Text: ", deciphered_text)
