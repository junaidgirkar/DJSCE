from LinkedList import exec as LLexec
from QueueLL import exec as queueExec
from StackLL import exec as stackExec

def main():
    print('Enter 1 for Linked List')
    print('Enter 2 for stack')
    print('Enter 3 for Queue')
    option=int(input('Enter Input: '))
    if option==1:
        LLexec()
    elif option==2:
        stackExec()
    elif option==3:
        queueExec()
    

if __name__=="__main__":
    main()