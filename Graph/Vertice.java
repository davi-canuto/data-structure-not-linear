package Graph;

import java.util.ArrayList;

public class Vertice {
  public Object id;
  public ArrayList<Vertice> adjacentsVertices;
  public ArrayList<Edge> edges;

  public Vertice(Object id) {
    this.id = id;
    this.adjacentsVertices = new ArrayList<Vertice>();
    this.edges = new ArrayList<Edge>();
  }

  public Object getId() {
    return this.id;
  }
  
  public void setId(Object newId) {
    this.id = newId;
  }

  public ArrayList<Object> getAdjacentsIds() {
    ArrayList<Object> adjacentsIds = new ArrayList<Object>();

    if (this.adjacentsVertices.isEmpty()) {
      return adjacentsIds;
    }

    for (Vertice v : this.adjacentsVertices) {
      adjacentsIds.add(v.getId());
    }
    return adjacentsIds;
  }
}
