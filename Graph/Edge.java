
package Graph;

public class Edge {
  public Object value;
  public Vertice origin;
  public Vertice destiny;

  public Edge(Object value, Vertice origin, Vertice destiny) {
    this.value = value;
    this.origin = origin;
    this.destiny = destiny;
  }

  public Object getValue() {
    return this.value;
  }

  public void setValue(Object newValue) {
    this.value = newValue;
  }
}
