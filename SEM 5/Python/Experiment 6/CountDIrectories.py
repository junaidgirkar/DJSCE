import os

def countDirectories(directoryList): 
    """ A function that lists the directories in a folder.""" 
    print("Directories in the given folder: ") 
    for index, dir in enumerate(directoryList): 
        print(f"{str(index+1)}. {dir}")
        
def countFiles(fileList): 
    """ A function that lists the files in a folder.""" 
    print("Files in the given folder: ") 
    for index, file in enumerate(fileList): 
        print(f"{str(index+1)}. {file}")

def displayFilesAvailableInCurrentDirectory(path="."):
    """ 
    A function that calls 2 functions to separately listing out directories and files. 
    It takes a default argument as cwd(.). We can 
    pass other paths too. 
    """ 
    fileList = list() 
    directoryList = list() 
    try: 
        for f in os.listdir(path): 
            if os.path.isfile(os.path.join(path, f)): 
                fileList.append(f) 
            else: 
                if os.path.isdir(os.path.join(path, f)): 
                    directoryList.append(f) 
    except: 
        print("\nError, please check the path") 
        pass
    print(f"Given folder: {os.path.abspath(path)}") 
    countFiles(fileList) 
    countDirectories(directoryList)

def main(): 
    displayFilesAvailableInCurrentDirectory() 

main() 