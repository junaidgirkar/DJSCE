# visited = []
queue = [] 

def bfs(visited, graph, node, goal_state):
  visited.append(node)
  queue.append(node)

  while queue:          # Creating loop to visit each node
    m = queue.pop(0) 
    print (m, end = " ")
    if(m==goal_state):
      print("GOAL", end= " ")
      break 

    for neighbour in graph[m]:
      if neighbour not in visited:
        visited.append(neighbour)
        queue.append(neighbour)

# graph = {
#   '5' : ['3','7'],
#   '3' : ['2', '4'],
#   '7' : ['8'],
#   '2' : [],
#   '4' : ['8'],
#   '8' : []
# }

# print("Following is the Breadth-First Search")
# bfs(visited, graph, '5')