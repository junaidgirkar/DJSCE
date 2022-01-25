def CountInFile(fname): 
    """Python program to count number of lines, words and characters in  a file.""" 
    with open(fname, 'r', encoding='utf-8') as f: 
        lineCount = 0 
        wordCount = 0 
        characterCount = 0 
        for line in f: 
            line = line.strip("\n")

            words = line.split() 
            lineCount += 1 
            wordCount += len(words) 
            characterCount += len(line) 
        print(f"In the file {fname} we have:") 
        print(f"Lines: {lineCount}") 
        print(f"Words: {wordCount}") 
        print(f"Characters: {characterCount}") 
def main(): 
    CountInFile("file.txt") 
    
main() 