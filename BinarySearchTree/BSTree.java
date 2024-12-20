package BinarySearchTree;

import java.util.ArrayList;
import java.util.Iterator;

public class BSTree {
  Comparator comparator;
  BSNode root;
  Integer size;

  public BSTree(Object o) {
    this.comparator = new Comparator();
    this.root = new BSNode(o);
    this.size = 0;
  }

  public BSTree() {
    this.comparator = new Comparator();
    this.root = null;
    this.size = 0;
  }

  public BSNode search(BSNode node, Object key) {
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

  public BSNode insert(Object key) {
    Comparator comparator = this.comparator;
    BSNode newNode = new BSNode(key);

    if (root == null) {
      this.root = new BSNode(key);
      size = size + 1;
      return root;
    } else {
      BSNode returnedNode = search(root, key);

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

  public Object remove(Object key) {
    BSNode node = search(root, key);

    if (node.getElement() != key || root == null) {
      return null;
    }
    
    if (isExternal(node)) {
      BSNode dad = node.getDad();
      
      if (this.comparator.compare(node.getElement(), dad.getElement()) < 0) {
        dad.setLeftChildren(null);
      } else {
        dad.setRightChildren(null);
      }
    } 

    else if (node.hasLeftChildren() && !node.hasRightChildren()) {
      BSNode dad = node.getDad();
      if (dad.getLeftChildren() == node) {
        dad.setLeftChildren(node.getLeftChildren());
      } else {
        dad.setRightChildren(node.getLeftChildren());
      }
      node.getLeftChildren().setDad(dad);
    } else if (!node.hasLeftChildren() && node.hasRightChildren()) {
      BSNode dad = node.getDad();
      if (dad.getRightChildren() == node) {
        dad.setRightChildren(node.getRightChildren());
      } else {
        dad.setLeftChildren(node.getRightChildren());
      }
      node.getRightChildren().setDad(dad);
    } else if (node.hasLeftChildren() && node.hasRightChildren()) {
      BSNode successor = getSuccessor(node);
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

  public BSNode getRoot() {
    return root;
  }

  public BSNode getSuccessor(BSNode node) {
    if (node == null) {
      return null;
    }
    if (node.getRightChildren() != null) {
      BSNode newNode = node.getRightChildren();
      while (newNode.getLeftChildren() != null) {
        newNode = newNode.getLeftChildren();
      }
      return newNode;
    } else {
      BSNode dad = node.getDad();
      while (dad != null && node == dad.getRightChildren()) {
        node = dad;
        dad = dad.getDad();
      }
      return dad;
    }
  }

  public void beforeOrderRemove(BSNode node) {
    Comparator comparator = this.comparator;

    if (node.hasLeftChildren() == false) {
      BSNode reallyDad = node.getDad().getDad();
      node.setDad(reallyDad);
      if (comparator.compare(node.getElement(), reallyDad.getElement()) > 0) {
        reallyDad.setRightChildren(node);
      } else {
        reallyDad.setLeftChildren(node);
      }
    }

    while (node.hasLeftChildren() == true) {
      BSNode next = node.getLeftChildren();
      beforeOrderRemove(next);
    }
  }

  public void show() {
    int height = height(root) + 1;

    int size = size();

    int[][] matriz = new int[height][size];

    ArrayList<BSNode> nodes = new ArrayList<>();
    Iterator<BSNode> nodesIterator = inOrder(nodes, root);

    while (nodesIterator.hasNext()) {
      BSNode next = nodesIterator.next();
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

  public Iterator<BSNode> inOrder(ArrayList<BSNode> nodes, BSNode v) {
    if (!isExternal(v) && v.getLeftChildren() != null) {
      inOrder(nodes, v.getLeftChildren());
    }

    nodes.add(v);

    if (!isExternal(v) && v.getRightChildren() != null) {
      inOrder(nodes, v.getRightChildren());
    }

    return nodes.iterator();
  }

  public int height(BSNode node) {
    if (isExternal(node)) {
      return 0;
    } else {
      int h = 0;

      Iterator<BSNode> childrens = node.children();

      while (childrens.hasNext()) {
        BSNode next = childrens.next();
        h = max(h, height(next));
      }

      return 1 + h;
    }
  }

  public int depth(BSNode node) {
    if (node == root) {
      return 0;
    } else {
      return 1 + depth(node.getDad());
    }
  }

  public Iterator<BSNode> nodes(BSNode n) {
    ArrayList<BSNode> nodes = new ArrayList<>();

    nodes.add(n);
    Iterator<BSNode> childrens = root.children();

    while (childrens.hasNext()) {
      nodes(childrens.next());
    }

    return nodes.iterator();
  }

  public ArrayList<BSNode> arrayOfNodes(BSNode n) {
    ArrayList<BSNode> nodes = new ArrayList<>();

    nodes.add(n);
    Iterator<BSNode> childrens = root.children();

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

  public boolean isExternal(BSNode node) {
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
