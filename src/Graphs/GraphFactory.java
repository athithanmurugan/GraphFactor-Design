package Graphs;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
public class GraphFactory {
	
    public static Digraph createDiGraphfromTextFile(String path) {
        Digraph digraph = new Digraph();    
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] vertices = line.split(" ");
                if (vertices.length == 2) {
                    int source = Integer.parseInt(vertices[0]);
                    int target = Integer.parseInt(vertices[1]);
                    digraph.addEdge(source, target);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return digraph;
    }
    public static Wigraph createWDiGraphFromTextFile(String path) {
        Wigraph wigraph = new Wigraph();
        
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] vertices = line.split(" ");
                if (vertices.length == 3) {
                    int source = Integer.parseInt(vertices[0]);
                    int target = Integer.parseInt(vertices[1]);
                    double weight = Double.parseDouble(vertices[2]);
                    wigraph.addEdge(source, target, weight);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return wigraph;
    }
    public static void main(String[] args) {
        String filePath = "/Users/athi_macbookair/Desktop/graph-WDG.txt";
        
        Digraph digraph = createDiGraphfromTextFile(filePath);
        System.out.println("Directed Graph:");
        System.out.println(digraph);
        
        Wigraph wigraph = createWDiGraphFromTextFile(filePath);
        System.out.println("Weighted Directed Graph:");
        System.out.println(wigraph);
    }
}
class Digraph {
    private List<List<Integer>> adjacencyList;

    public Digraph() {
        adjacencyList = new ArrayList<>();
    }
    public void addEdge(int source, int target) {
        while (adjacencyList.size() <= source || adjacencyList.size() <= target) {
            adjacencyList.add(new ArrayList<>());
        }
        adjacencyList.get(source).add(target);
    }
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < adjacencyList.size(); i++) {
            sb.append(i).append(": ").append(adjacencyList.get(i)).append("\n");
        }
        return sb.toString();
    }
}
class Wigraph {
    private List<List<Edge>> adjacencyList;

    public Wigraph() {
        adjacencyList = new ArrayList<>();
    }

    public void addEdge(int source, int target, double weight) {
        while (adjacencyList.size() <= source || adjacencyList.size() <= target) {
            adjacencyList.add(new ArrayList<>());
        }
        adjacencyList.get(source).add(new Edge(target, weight));
    }
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < adjacencyList.size(); i++) {
            sb.append(i).append(": ");
            List<Edge> edges = adjacencyList.get(i);
            for (Edge edge : edges) {
                sb.append("(").append(edge.getTarget()).append(", ").append(edge.getWeight()).append(") ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
class Edge {
    private int target;
    private double weight;

    public Edge(int target, double weight) {
        this.target = target;
        this.weight = weight;
    }

    public int getTarget() {
        return target;
    }

    public double getWeight() {
        return weight;
    }
}
