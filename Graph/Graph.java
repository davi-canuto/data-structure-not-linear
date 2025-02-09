package Graph;

import java.util.*;

public class Graph {
  ArrayList<Vertice> vertices;

  public Graph() {
    this.vertices = new ArrayList<Vertice>();
  }

  // public Vertice[] endVertice(Edge e) {
  // }

  // public Vertice opposite(Vertice v, Edge e) {
  // }

  public boolean isAdjacent(Vertice v, Vertice w) {
    if (v == null || w == null) {
      return false;
    }
    if (v.adjacentsVertices.contains(w)) {
      return true;
    }
    if (w.adjacentsVertices.contains(v)) {
      return true;
    }
    return false;
  }

  public void replace(Vertice v, Object x) {
    v.setId(x);
  }

  public void replace(Edge e, Object x) {
    e.setValue(x);
  }

  public Vertice insertVertice(Object o) {
    if (o == null) {
      return null;
    } 

    Vertice newVertice = new Vertice(o); 

    this.vertices.add(newVertice);
    
    return newVertice;
  }

  public Edge insertEdge(Vertice origin, Vertice destiny, Object value) {
    if (origin == null || destiny == null || value == null) {
      return null;
    }

    Edge newEdge = new Edge(0, origin, destiny);

    origin.edges.add(newEdge);
    destiny.edges.add(newEdge);

    origin.adjacentsVertices.add(destiny);

    return newEdge;
  }

  public Object removeVertice(Vertice v) {
    if (v == null) {
      return null;
    }
    
    var id = v.getId();
    
    for (int i = 0; i < v.edges.size(); i++) {
      Edge e = v.edges.get(i);

      if (e.origin.id == id) {
        e.destiny.adjacentsVertices.remove(v);
        e.destiny.edges.remove(e);
      }
      if (e.destiny.id == id) {
        e.origin.adjacentsVertices.remove(v);
        e.origin.edges.remove(e);
      }
    } 
    
    this.vertices.remove(v);

    return id;
  }

  public Object removeEdge(Edge e) {
    if (e == null) {
      return null;
    }

    e.origin.edges.remove(e);
    e.destiny.edges.remove(e);

    e.origin.adjacentsVertices.remove(e.destiny);

    return e.getValue();
  }
}
