def AppendData(fname): 
    """Python program to append data to existing file and then display  the entire file. """ 
    """Before appending to "file.txt" its contents of the file were as  follows:""" 
    beforeAppending = open(fname) 
    print("Before appending to 'file.txt' its contents were as  follows:") 
    print(beforeAppending.read()) 
    beforeAppending.close() 
    with open(fname, 'a', encoding='utf-8') as f: 
        f.write("\nThis is the First Line.\n") 
        f.write("This is the Second one\n") 
        f.write("This is the final line.\n") 
        print("*"*80) 
    """Before appending to "file.txt" its contents were as follows:""" 
    afterAppending = open(fname) 
    print("After appending to file.txt' its contents were as  follows:") 
    print(afterAppending.read()) 
    afterAppending.close() 
def main(): 
    AppendData("file.txt") 
if __name__ == '__main__': 
    main() 