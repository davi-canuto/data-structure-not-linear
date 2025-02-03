
package Graph;

public class Edge {
  private Object value;
  private Vertice origin;
  private Vertice destiny;

  public Edge(Object value, Vertice origin, Vertice destiny) {
    this.value = value;
    this.origin = origin;
    this.destiny = destiny;
  }
}
