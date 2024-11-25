package AVL;

import java.util.ArrayList;
import java.util.Iterator;

import BinarySearchTree.Comparator;

public class Tree {
  Comparator comparator;
  Node root;
  Integer size;

  public Tree(Object o) {
    this.comparator = new Comparator();
    this.root = new Node(o);
    this.size = 0;
  }

  public Tree() {
    this.comparator = new Comparator();
    this.root = null;
    this.size = 0;
  }

  public Node search(Node node, Object key) {
    Comparator comparator = this.comparator;
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

  public Node insert(Object key) {
    Comparator comparator = this.comparator;
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

    updateFbcInsert(newNode);

    size = size + 1;
    return newNode;
  }

  public Object remove(Object key) {
    Node node = search(root, key);

    if (node.getElement() != key || root == null) {
      return null;
    }

    if (isExternal(node)) {
      Node dad = node.getDad();

      if (this.comparator.compare(node.getElement(), dad.getElement()) < 0) {
        dad.setLeftChildren(null);
      } else {
        dad.setRightChildren(null);
      }
    }

    else if (node.hasLeftChildren() && !node.hasRightChildren()) {
      Node dad = node.getDad();
      if (dad.getLeftChildren() == node) {
        dad.setLeftChildren(node.getLeftChildren());
      } else {
        dad.setRightChildren(node.getLeftChildren());
      }
      node.getLeftChildren().setDad(dad);
    } else if (!node.hasLeftChildren() && node.hasRightChildren()) {
      Node dad = node.getDad();
      if (dad.getRightChildren() == node) {
        dad.setRightChildren(node.getRightChildren());
      } else {
        dad.setLeftChildren(node.getRightChildren());
      }
      node.getRightChildren().setDad(dad);
    } else if (node.hasLeftChildren() && node.hasRightChildren()) {
      Node successor = getSuccessor(node);
      if (successor != null) {
        Object temp = successor.getElement();
        remove(successor.getElement());
        node.setElement(temp);
        return key;
      }
    }

    size = size - 1;
    return node.getElement();
  }

  public Node getRoot() {
    return root;
  }

  public Node getSuccessor(Node node) {
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

  public void beforeOrderRemove(Node node) {
    Comparator comparator = this.comparator;

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

  public void show() {
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
          Node node = nodes.get(j);
          int fbc = node.getFbc(); // Supondo que getFbc() seja o método para obter o valor de fbc
          System.out.printf("%-4d", node.getElement());
          System.out.printf("[%s]", fbc);
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

  public int height(Node node) {
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

  public int depth(Node node) {
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

  public int size() {
    return size;
  }

  public boolean isEmpty() {
    return size == 0;
  }

  public boolean isExternal(Node node) {
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
  
  public int min(int a, int b) {
    return Math.min(a, b);
  }

  public void leftRotation(Node node) {
    Node rightChild = node.getRightChildren();
    Node parent = node.getDad();

    node.setRightChildren(rightChild.getLeftChildren());
    if (rightChild.getLeftChildren() != null) {
      rightChild.getLeftChildren().setDad(node);
    }

    rightChild.setLeftChildren(node);
    node.setDad(rightChild);

    if (parent == null) {
      root = rightChild;
    } else if (parent.getRightChildren() == node) {
      parent.setRightChildren(rightChild);
    } else {
      parent.setLeftChildren(rightChild);
    }
    rightChild.setDad(parent);
    Integer fbBNew = (node.getFbc() + 1) - min(rightChild.getFbc(), 0);
    Integer fbANew = (rightChild.getFbc() + 1) + max(fbBNew, 0);

    node.setFbc(fbBNew);
    rightChild.setFbc(fbANew);
  }

  public void rightRotation(Node node) {
    Node leftChild = node.getLeftChildren();
    Node parent = node.getDad();

    node.setLeftChildren(leftChild.getRightChildren());
    if (leftChild.getRightChildren() != null) {
      leftChild.getRightChildren().setDad(node);
    }

    leftChild.setRightChildren(node);
    node.setDad(leftChild);

    if (parent == null) {
      root = leftChild;
    } else if (parent.getLeftChildren() == node) {
      parent.setLeftChildren(leftChild);
    } else {
      parent.setRightChildren(leftChild);
    }
    leftChild.setDad(parent);

    Integer fbBNew = (node.getFbc() - 1) - max(leftChild.getFbc(), 0);
    Integer fbANew = leftChild.getFbc() - 1 + min(fbBNew, 0);

    node.setFbc(fbBNew);
    leftChild.setFbc(fbANew);
  }

  public void balance(Node node) {
    if (node.getFbc() > 1) {
      if (node.getLeftChildren().getFbc() < 0) {
        leftRotation(node.getLeftChildren());
        rightRotation(node);
        return;
      }
      rightRotation(node);
    } else if (node.getFbc() < -1) {
      if (node.getRightChildren().getFbc() > 0) {
        rightRotation(node.getRightChildren());
        leftRotation(node);
        return;
      }
      leftRotation(node);
    }
  } 

  public void updateFbcInsert(Node node) {
    Comparator comparator = this.comparator;
    node.setFbc(0);
    Node dad = node.getDad();

    while (dad != null) {
      Integer res = comparator.compare(node.getElement(), dad.getElement());

      if (res < 0) {
        dad.setFbc(dad.getFbc() + 1);
      } else {
        dad.setFbc(dad.getFbc() - 1);
      }

      if(dad.getFbc() > 1 || dad.getFbc() < -1) {
        balance(dad);
      }

      node = dad;
      dad = node.getDad();

      if(node.getFbc() == 0) {
        break;
      }
    }
  }
}
