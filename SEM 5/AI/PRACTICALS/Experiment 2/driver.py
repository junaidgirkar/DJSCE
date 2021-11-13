from bfs import bfs
from dfs import dfs

graph = {
  '5' : ['3','7'],
  '3' : ['2', '4'],
  '7' : ['8'],
  '2' : [],
  '4' : ['8'],
  '8' : []
}

#         5
#       /   \  
#     3       7
#    /  \      \
#   2    4 ---- 8

lecture_graph = {
    '1' : ['4', '2'],
    '4' : ['3'],
    '3' : ['10', '9', '2'],
    '10' :[],
    '9' : [],
    '2' : ['5', '8'],
    '5' : ['2', '6','7'],
    '7' : ['5', '8'],
    '8' : ['2', '7'],
    '6' : []
}

#     1
#   /   \
# 4       2
#   \   / | \
#     3   5   8
#   / |   | \ |
# 10  9   6   7      
         
# BFS
visited = []
print("Breadth-First Search traversal sequence")
bfs(visited, lecture_graph, '1', goal_state='8')
print("\n")

#DFS
print("Depth-First Search traversal sequence")
path = dfs(lecture_graph, "1")
goal_state = '9'
new_path=[]
for item in path:
  if(item==goal_state):
    break
  else:
    new_path.append(item)
new_path.append("GOAL")
print(" ".join(new_path))