import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

class BellmanFord {

    static class Edge {
        int source, destination, weight;

        Edge(int source, int destination, int weight) {
            this.destination = destination;
            this.source = source;
            this.weight = weight;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter number of edges");
        int numberOfEdges = sc.nextInt();
        System.out.println("Enter number of vertices");
        List<Edge> edges = new ArrayList<>();
        int numberOfVertices = sc.nextInt();
        for (int i = 0; i < numberOfEdges; i++) {
            System.out.println("Enter source destination and weight of edge " + (i));
            System.out.println("Source:");
            int source = sc.nextInt();
            System.out.println("Destination:");
            int destination = sc.nextInt();
            System.out.println("Weight:");
            int weight = sc.nextInt();
            edges.add(new Edge(source, destination, weight));
        }
        bellmanFord(edges, numberOfVertices, numberOfEdges);
        sc.close();
    }

    static void bellmanFord(List<Edge> edges, int numberOfVertices, int numberOfEdges) {
        // Assuming starting point to be node 0
        int parent[] = new int[numberOfVertices];
        int costOfParent[] = new int[numberOfVertices];
        int values[] = new int[numberOfVertices];
        Arrays.fill(values, Integer.MAX_VALUE);
        parent[0] = -1;
        values[0] = 0;
        boolean isCostArrayUpdated = false;
        for (int i = 0; i < numberOfVertices - 1; i++) {
            isCostArrayUpdated = false;
            for (int j = 0; j < numberOfEdges; j++) {
                int edgeU = edges.get(j).source;
                int edgeV = edges.get(j).destination;
                int weightFromEdgeUToEdgeV = edges.get(j).weight;
                if (values[edgeU] != Integer.MAX_VALUE && values[edgeU] + weightFromEdgeUToEdgeV < values[edgeV]) {
                    values[edgeV] = values[edgeU] + weightFromEdgeUToEdgeV;
                    parent[edgeV] = edgeU;
                    costOfParent[edgeV] = values[edgeV];
                    isCostArrayUpdated = true;
                }
            }
            if (!isCostArrayUpdated)
                break;
        }

        // Check for -ve edge cycle
        if (isCostArrayUpdated) {
            for (int i = 0; i < numberOfEdges; i++) {
                int edgeU = edges.get(i).source;
                int edgeV = edges.get(i).destination;
                int weightFromEdgeUToEdgeV = edges.get(i).weight;
                if (values[edgeU] != Integer.MAX_VALUE && values[edgeU] + weightFromEdgeUToEdgeV < values[edgeV]) {
                    System.out.println("The given graph has a -ve edge cycle");
                    return;
                }
            }
        }

        for (int i = 1; i < numberOfVertices; i++) {
            System.out.println(parent[i] + "->" + i + " Cost to reach from source 0 = " + values[i]);
        }
    }

}