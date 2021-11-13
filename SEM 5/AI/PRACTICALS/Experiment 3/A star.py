from collections import deque
 
class Graph:
    def __init__(self, adjac_lis):
        self.adjac_lis = adjac_lis
 
    def get_neighbors(self, v):
        return self.adjac_lis[v]
 
    def heuristic(self, n):
        H = {
            'S': 15,
            '1': 14,
            '2': 10,
            '3':  8,
            '4': 12,
            '5': 10,
            '6': 10,
            '7': 0
        }
 
        return H[n]
 
    def a_star(self, start, stop):

        open_list = set([start])
        closed_list = set([])

        distance = {} # Distance from Start. 
        distance[start] = 0

        adjacent_nodes = {} # Adjacent Mapping of all Nodes
        adjacent_nodes[start] = start
 
        while len(open_list) > 0:
            n = None

            for v in open_list:
                if n == None or distance[v] + self.heuristic(v) < distance[n] + self.heuristic(n):
                    n = v
 
            if n == None:
                print('Path does not exist!')
                return None

            if n == stop: # If current node is stop, we restart
                reconst_path = []
 
                while adjacent_nodes[n] != n:
                    reconst_path.append(n)
                    n = adjacent_nodes[n]
 
                reconst_path.append(start)
 
                reconst_path.reverse()
 
                print('\nPath found: {}\n'.format(reconst_path))
                return reconst_path

            for (m, weight) in self.get_neighbors(n): # Neighbours of current node

                if m not in open_list and m not in closed_list:
                    open_list.add(m)
                    adjacent_nodes[m] = n
                    distance[m] = distance[n] + weight

                else: # Check if its quicker to visit n then m
                    if distance[m] > distance[n] + weight:
                        distance[m] = distance[n] + weight
                        adjacent_nodes[m] = n
 
                        if m in closed_list:
                            closed_list.remove(m)
                            open_list.add(m)

 
            open_list.remove(n) # Since all neighbours are inspected
            closed_list.add(n)
            print("OPEN LIST : ", end="")
            print(open_list)
            print("CLOSED LIST : ", end="")
            print(closed_list)
            print("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -")
 
        print('Path does not exist!')
        return None


adjacent_list2 = { 
    'S': [('1', 3), ('4', 4)],
    '1': [('S', 3), ('2', 4), ('4', 5)],
    '2': [('1', 4), ('3', 4), ('5', 5)],
    '3': [('2', 4)],
    '4': [('S', 4), ('1', 5), ('5', 2)],
    '5': [('4', 2), ('2', 5), ('6', 4)],
    '6': [('5', 4), ('7', 3)],
    '7': [('6', 3)],
}

g = Graph(adjacent_list2)
g.a_star('S', '7')