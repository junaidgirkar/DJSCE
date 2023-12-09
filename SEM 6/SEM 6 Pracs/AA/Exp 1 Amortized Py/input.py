tack  = []

def top():
    global stack
    if len(stack) > 1:    
        return stack[-1]
    return -1

def pop():
    global stack
    popped = stack[-1]
    stack = stack[:len(stack)-1]
    return popped

def push(a: int):
    global stack
    stack.append(a)

def multi_pop(num: int):
    global stack
    for i in range(min(len(stack), num)):
        pop()


if __name__ == '__main__':
    print("Hello World")
    push(1)
    push(2)
    pop()
    push(24)
    pop()
    multi_pop(3)
    push(345)
    push(2)
    pop()
    push(2)
    push(2)
    push(2)
    push(2)
    multi_pop(100)