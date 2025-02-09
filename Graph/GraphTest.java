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
    Edge e3 = graph.insertEdge(v0, v1, 20);
    
    graph.vertices.forEach(v -> {
      System.out.println("Vertice: " + v.getId());
      System.out.println("    Adjacents ids: " + v.getAdjacentsIds());
    });

    System.out.println("");
    System.out.println("");
    System.out.println("Removing vertice  v1");
    
    graph.removeVertice(v0);

    graph.vertices.forEach(v -> {
      System.out.println("Vertice: " + v.getId());
      System.out.println("    Adjacents ids: " + v.getAdjacentsIds());
    });

    System.out.println("");
    try {
      System.out.println(graph.opposite(v0, e3).getId());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
