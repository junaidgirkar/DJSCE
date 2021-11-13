import java.util.*;

public class DistanceVectorRouting {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Number of Nodes: ");
        int noOfNodes = sc.nextInt();

        System.out.print("Enter Node which requires new routing table: ");
        int selectedNode = sc.nextInt();

        System.out.print("Enter Number of Neighbouring Nodes: ");
        int neighbourCount = sc.nextInt();
        int[] neighbourArr = new int[neighbourCount];
        for (int i = 0; i < neighbourCount; i++) {
            System.out.print("Enter Neighbour Node: ");
            neighbourArr[i] = sc.nextInt();
        }

        int[][] neighbourDistance = new int[neighbourCount][2];
        for (int i = 0; i < neighbourCount; i++) {
            System.out.print("Enter " + neighbourArr[i] + " Node to " + selectedNode + " Distance: ");
            neighbourDistance[i][0] = sc.nextInt();
            neighbourDistance[i][1] = neighbourArr[i];
        }

        int counter = 0;
        int[][] table = new int[noOfNodes][neighbourCount];
        for (int i = 0; i < noOfNodes; i++) {
            for (int j = 0; j < neighbourCount; j++) {
                if (i == j) {
                    table[i][j] = 0;
                } else if (i == selectedNode) {
                    table[i][j] = neighbourDistance[counter][0];
                    counter++;
                } else {
                    Random random = new Random();
                    int randomInt = 2 + (random.nextInt(8));
                    table[i][j] = randomInt;
                }
            }
        }

        System.out.print("\nNode\t");
        for (int i = 0; i < neighbourCount; i++) {
            System.out.print(i + "\t");
        }
        System.out.print("\n");

        for (int i = 0; i < noOfNodes; i++) {
            System.out.print(i + "\t");
            for (int j = 0; j < neighbourCount; j++) {
                System.out.print(table[i][j] + "\t");
            }
            System.out.println("");
        }

        System.out.println("");
        counter = 0;
        int[][] selectedNodeTable = new int[noOfNodes][2];
        for (int i = 0; i < noOfNodes; i++) {
            for (int j = 0; j < 2; j++) {
                if (i == neighbourDistance[counter][0]) {
                    selectedNodeTable[i][0] = neighbourDistance[counter][0];
                    selectedNodeTable[i][1] = counter;
                    counter++;
                } else if (i == selectedNode) {
                    selectedNodeTable[i][0] = 0;
                    selectedNodeTable[i][1] = selectedNode;
                } else {
                    int shortest = 999, parent = 0;
                    for (int k = 0; k < neighbourCount; k++) {
                        if (table[i][k] < shortest) {
                            shortest = table[i][k];
                            parent = k;
                        }
                    }
                    selectedNodeTable[i][0] = (shortest + neighbourDistance[parent][0]);
                    selectedNodeTable[i][1] = parent;
                }
            }
        }

        System.out.println("Node\tCost\tParent");
        for (int i = 0; i < noOfNodes; i++) {
            System.out.print(i + "\t");
            for (int j = 0; j < 2; j++) {
                System.out.print(selectedNodeTable[i][j] + "\t");
            }
            System.out.println("");
        }
    }
}
