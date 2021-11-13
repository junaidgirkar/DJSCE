def calculate_cost(list):
    list_copy = list
    cost = 0
    for i in range(len(list_copy)):
        l1=list[(i+1) : len(list)]
        ls = [j for j in l1 if list_copy[i]>j ]
        cost = cost + len(ls)
    return cost

def goal_test(state):
    if calculate_cost(state) == 0 :
        return True
    else:
        return False

def state_generation(current_state, current_state_cost):
    min_cost = current_state_cost
    sli_state = current_state.copy()
    for i in range(len(current_state)-1):
        for j in range(i+1,len(current_state)):
            sli_state[i],sli_state[j] = sli_state[j],sli_state[i]
            iteration_cost = calculate_cost(sli_state)
            if(iteration_cost < min_cost):
                min_cost = iteration_cost
                min_state = sli_state.copy()
            sli_state = current_state.copy()

    if(min_cost < current_state_cost):
        return min_state,min_cost
    else:
        return current_state,None

def main():
    # print("Enter Unsorted Input numbers seperated by a space:", end="")
    state = [12, 46, 29, 34, 12, 4, 1, 5, 7, 278, 48, 234, 78, 354]
    # state = [int(s) for s in input().split()]
    cost = calculate_cost(state)
    while(not goal_test(state)):
        state, cost = state_generation(state, cost)
        print("CURRENT STATE: ",state, " \tCOST: ",cost)
        if cost is None:
            print(state)
            return
    print(state)
    return

main()