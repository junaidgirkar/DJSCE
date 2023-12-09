from scipy import misc
import imageio
import numpy as np
import matplotlib.pyplot as plt
import os.path
import pickle
from numpy.linalg import inv, det
import sys
import scipy.misc

#   IMAGE SECTION 
def read_image(image_path):
    """ Read an image and return a one hot vector of the image"""
    img = imageio.imread(image_path)
    reshape_value = 1

    for i in img.shape:
        reshape_value *= i

    return img.reshape((1, reshape_value)), img.shape


def show_image(image):
    """ Show a single image"""
    plt.imshow(image)
    plt.show()


def show_images(a, b):
    """ Show two images side by side"""
    plot_image = np.concatenate((a, b), axis=1)
    plt.imshow(plot_image)
    plt.show()

# HILL CLIMB SECTION

class HillClimb:
    def __init__(self, data, file_name, key_path=None):

        self.data = data

        # Computet the chunk
        self.chunk = self.computer_chunk()

        if key_path:
            # Load the key if it exist in the current dir
            self._key = pickle.load(open( key_path, "rb" ))
            print('Usigng the args -k ' + key_path)
        else:
            file_name = file_name + '.key'

            if os.path.isfile(file_name):
                # Load the key if it exist in the current dir
                self._key = pickle.load(open( file_name, "rb" ))
                print('Usigng the ' + file_name)
            else:
                # Generate a random key
                self._key = np.random.random_integers(0, 100, (self.chunk, self.chunk))
                
                # If determinat is equal to zero regenrate another key
                if det(self._key) == 0:
                    self._key = np.random.random_integers(0, 100, (self.chunk, self.chunk))

                # Save the key in a pickle
                pickle.dump( self._key, open( file_name, "wb" ) )

        print(self._key.dtype)
        print(self._key.shape)
        print(self._key)

        # Get the inverse of the key
        self.reversed_key = np.matrix(self._key).I.A

        print(self.reversed_key.dtype)
        print(self.reversed_key.shape)
        print(self.reversed_key)

    def computer_chunk(self):
        max_chunk = 100
        data_shape = self.data.shape[1]
        print(data_shape)

        for i in range(max_chunk, 0, -1):
            if data_shape % i == 0:
                return i


    @property
    def key(self):
        return self._key

    def encode(self, data):
        """ Encode function """
        crypted = []
        chunk = self.chunk
        key = self._key

        for i in range(0, len(data), chunk):

            temp = list(np.dot(key, data[i:i + chunk]))
            crypted.append(temp)

        crypted = (np.array(crypted)).reshape((1, len(data)))
        return crypted[0]


    def decode(self, data):
        """ Decode function """
        uncrypted = []
        chunk = self.chunk
        reversed_key = self.reversed_key

        for i in range(0, len(data), chunk):
            temp = list(np.dot(reversed_key, data[i:i + chunk]))
            uncrypted.append(temp)

        uncrypted = (np.array(uncrypted)).reshape((1, len(data)))

        return uncrypted[0]