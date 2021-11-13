from bitstring import BitArray

def booth(m, r, x, y):
    # Initialize
    totalLength = x + y + 1
    mA = BitArray(int = m, length = totalLength)
    rA = BitArray(int = r, length = totalLength)
    A = mA << (y+1)
    S = BitArray(int = -m, length = totalLength)  << (y+1)
    P = BitArray(int = r, length = y)
    P.prepend(BitArray(int = 0, length = x))
    P = P << 1
    print("Initial values")
    print("A", A.bin)   # A = -M
    print("S", S.bin)   # S = M
    print("P", P.bin)
    print("Starting calculation")
    for i in range(1,y+1):
        print("\nSTEP ", i)
        if P[-2:] == '0b01':
            print("01")
            P = BitArray(int = P.int + A.int, length = totalLength)
            print("P +  A:", P.bin)
        elif P[-2:] == '0b10':
            print("10")
            P = BitArray(int = P.int +S.int, length = totalLength)
            print("P +  S:", P.bin)
        elif(P.bin[-2:]=="00"):
            print("00")
        elif(P.bin[-2:]=="11"):
            print("11")
        P = arith_shift_right(P, 1)
        print ("P >> 1:", P.bin)

    print("\nFINAL ANSWER")
    P = arith_shift_right(P, 1)
    print("P >> 1:", P.bin)
    return P.int

def arith_shift_right(x, amt):
    l = x.len
    x = BitArray(int = (x.int >> amt), length = l)
    return x

def buildLine(iteration, mcand, product):
    line = "Step: " + str(iteration) + " | A: " \
    + product[0:8] + " | " + "Q: " + product[8:16] + " | " + "Qn-1: " + product[16]
    return line
    
# Sample usage: find 86 * 41
b = booth(6, -3, 8, 8)
print(b)