package BinarySearchTree;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

public class BSTree {
  Comparator comparator;
  Node root;
  Integer size;

  public BSTree(Object o) {
    this.comparator = new Comparator();
    this.root = new Node(o);
    this.size = 0;
  }

  public BSTree() {
    this.comparator = new Comparator();
    this.root = null;
    this.size = 0;
  }

  Comparator getComparator() {
    return this.comparator;
  }

  Node search(Node node, Object key) {
    Comparator comparator = getComparator();
    int compareRes = comparator.compare(key, node.getElement());

    if (compareRes == 0) {
      return node;
    }

    if (isExternal(node) && node != root) {
      return node;
    }

    if (compareRes < 0 && node.hasLeftChildren()) {
      return search(node.getLeftChildren(), key);
    }
    if (compareRes > 0 && node.hasRightChildren()) {
      return search(node.getRightChildren(), key);
    }

    return node;
  }

  Node insert(Object key) {
    Comparator comparator = getComparator();
    Node newNode = new Node(key);

    if (root == null) {
      this.root = new Node(key);
      size = size + 1;
      return root;
    } else {
      Node returnedNode = search(root, key);

      int comparatorRes = comparator.compare(key, returnedNode.getElement());

      if (comparatorRes > 0) {
        returnedNode.setRightChildren(newNode);
        newNode.setDad(returnedNode);
      } else if (comparatorRes < 0) {
        returnedNode.setLeftChildren(newNode);
        newNode.setDad(returnedNode);
      }
    }
    size = size + 1;
    return newNode;
  }

  Object remove(Object key) {
    Node node = search(root, key);
    System.out.println(node.getElement());

    if (node.getElement() != key || root == null) {
      return null;
    }

    if (root.getElement() == key) {
      // getSuccessor(root);
    }

    if (isExternal(node)) {
      Node dad = node.getDad();
      if (getComparator().compare(node.getElement(), dad.getElement()) < 0) {
        dad.setLeftChildren(null);
      } else {
        dad.setRightChildren(node);
      }
    } else {
      if (node.hasRightChildren()) {
        beforeOrderRemove(node.getRightChildren());
      } else {
        node.getLeftChildren().setDad(node.getDad());
        node.getDad().setLeftChildren(node.getLeftChildren());
      }
    }
    size = size - 1;
    return node.getElement();
  }

  Node getRoot() {
    return root;
  }

  Node getSuccessor(Node node) {
    if (node == null) {
      return null;
    }
    if (node.getRightChildren() != null) {
      Node newNode = node.getRightChildren();
      while (newNode.getLeftChildren() != null) {
        newNode = newNode.getLeftChildren();
      }
      return newNode;
    } else {
      Node dad = node.getDad();
      while (dad != null && node == dad.getRightChildren()) {
        node = dad;
        dad = dad.getDad();
      }
      return dad;
    }
  }

  void beforeOrderRemove(Node node) {
    Comparator comparator = getComparator();

    if (node.hasLeftChildren() == false) {
      Node reallyDad = node.getDad().getDad();
      node.setDad(reallyDad);
      if (comparator.compare(node.getElement(), reallyDad.getElement()) > 0) {
        reallyDad.setRightChildren(node);
      } else {
        reallyDad.setLeftChildren(node);
      }
    }

    while (node.hasLeftChildren() == true) {
      Node next = node.getLeftChildren();
      beforeOrderRemove(next);
    }
  }

  void show() {
    int height = height(root) + 1;

    int size = size();

    int[][] matriz = new int[height][size];

    ArrayList<Node> nodes = new ArrayList<>();
    Iterator<Node> nodesIterator = inOrder(nodes, root);

    while (nodesIterator.hasNext()) {
      Node next = nodesIterator.next();
      int key = (int) next.getElement();

      int depth = depth(next);
      int index = nodes.indexOf(next);

      matriz[depth][index] = key;
    }

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < size; j++) {
        if (matriz[i][j] != 0) {
          System.out.printf("%-4d", matriz[i][j]);
        } else {
          System.out.print("    ");
        }
      }
      System.out.println();
    }
  }

  public Iterator<Node> inOrder(ArrayList<Node> nodes, Node v) {
    if (!isExternal(v) && v.getLeftChildren() != null) {
      inOrder(nodes, v.getLeftChildren());
    }

    nodes.add(v);

    if (!isExternal(v) && v.getRightChildren() != null) {
      inOrder(nodes, v.getRightChildren());
    }

    return nodes.iterator();
  }

  int height(Node node) {
    if (isExternal(node)) {
      return 0;
    } else {
      int h = 0;

      Iterator<Node> childrens = node.children();

      while (childrens.hasNext()) {
        Node next = childrens.next();
        h = max(h, height(next));
      }

      return 1 + h;
    }
  }

  int depth(Node node) {
    if (node == root) {
      return 0;
    } else {
      return 1 + depth(node.getDad());
    }
  }

  public Iterator<Node> nodes(Node n) {
    ArrayList<Node> nodes = new ArrayList<>();

    nodes.add(n);
    Iterator<Node> childrens = root.children();

    while (childrens.hasNext()) {
      nodes(childrens.next());
    }

    return nodes.iterator();
  }

  public ArrayList<Node> arrayOfNodes(Node n) {
    ArrayList<Node> nodes = new ArrayList<>();

    nodes.add(n);
    Iterator<Node> childrens = root.children();

    while (childrens.hasNext()) {
      nodes(childrens.next());
    }

    return nodes;
  }

  int size() {
    return size;
  }

  boolean isEmpty() {
    return size == 0;
  }

  boolean isExternal(Node node) {
    boolean res = false;
    if (node == root)
      res = false;
    if (node.hasLeftChildren() == false && node.hasRightChildren() == false)
      res = true;
    return res;
  }

  public int max(int a, int b) {
    return Math.max(a, b);
  }
}
