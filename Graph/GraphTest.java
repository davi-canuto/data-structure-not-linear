package Graph;

public class GraphTest {
  public static void main(String[] args) {
    Graph graph = new Graph();
    Vertice v0 = graph.insertVertice(0);
    Vertice v1 = graph.insertVertice(1);
    Vertice v2 = graph.insertVertice(2);

    Edge e0 = graph.insertEdge(v1, v0, 5);
    Edge e1 = graph.insertEdge(v1, v2, 10);
    Edge e2 = graph.insertEdge(v2, v0, 15);

    graph.vertices.forEach(v -> {
      System.out.println("Vertice: " + v.getId());
      System.out.println("    Adjacents ids: " + v.getAdjacentsIds());
    });

    System.out.println("Removing vertice 1");
    
    graph.removeVertice(v1);

    graph.vertices.forEach(v -> {
      System.out.println("Vertice: " + v.getId());
      System.out.println("    Adjacents ids: " + v.getAdjacentsIds());
    });
  }
}
