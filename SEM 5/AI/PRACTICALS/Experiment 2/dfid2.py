vertex = {}


def make_graph():

    vertices = set()
    print("")
    while True:
        x = input("Enter edge: ")

        if x == "END":
            for y in vertices:
                if y not in vertex.keys():
                    vertex[y] = []
            print("\nGraph:", vertex)
            return

        a = int(x.split(" ")[0])
        b = int(x.split(" ")[1])

        vertices.add(a)
        vertices.add(b)

        if a in vertex.keys():
            vertex[a].append(b)
        else:
            vertex[a] = [b]


def DFS(limit: int, start: int, target: int):

    path = []
    queue = [start]
    visited = set()
    depth = 1

    print("\nExecution:")
    print("\nLimit: ", limit)

    while len(queue) != 0 and depth <= limit:
        curr_node = queue.pop(0)

        if curr_node not in visited:

            visited.add(curr_node)
            path.append(curr_node)

            if curr_node == target:
                print("\nFound target: ", target)
                print("\nPath: ", end="")
                [print(str(x)+" -> ", end="") for x in path[:-1]]
                print(target)
                return path

            insert_index = 0
            for node in vertex[curr_node]:
                if node not in visited:
                    queue.insert(insert_index, node)
                    insert_index += 1

            if insert_index > 0:
                depth += 1

        print("Current Node:", curr_node, "\tQueue:", queue, "\t" if len(queue) < 3 else "", "\tPath:", path)


def DFID():

    start = int(input("\nEnter start node: "))
    target = int(input("Enter target node: "))

    limit = int(input("\nEnter depth limit: "))

    for i in range(1, limit+1):
        if DFS(limit=i, start=start, target=target):
            return

    print("\nTarget not found")

make_graph()
DFID()