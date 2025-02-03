package Graph;

import java.util.*;
import java.util.AbstractList;

public class Graph {
  ArrayList<Vertice> vertices;

  public Graph() {
    this.vertices = new ArrayList<Vertice>();
  }

  // public Vertice[] endVertice(Edge e) {
  // }

  // public Vertice opposite(Vertice v, Edge e) {
  // }

  // public boolean isAdjacent(Vertice v, Vertice w) {
  // }

  // public void replace(Vertice v, Object x) {
  // }

  // public void replace(Edge e, Object x) {
  // }

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

  // public Vertice removeVertice(Vertice v) {
  // }

  // public Edge removeEdge(Edge e) {
  // }
}
