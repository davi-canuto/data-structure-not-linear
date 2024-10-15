// Source code is decompiled from a .class file using FernFlower decompiler.
package BinarySearchTree;

import java.util.ArrayList;
import java.util.Iterator;

public class Node {
  private Object element;
  private Node leftChildren;
  private Node rightChildren;
  private Node dad;

  public Node(Object variable) {
    this.element = variable;
    this.leftChildren = null;
    this.rightChildren = null;
    this.dad = null;
  }

  public Object getElement() {
    return this.element;
  }

  public void setElement(Object variable) {
    this.element = variable;
  }

  public Node getLeftChildren() {
    return this.leftChildren;
  }

  public Node getRightChildren() {
    return this.rightChildren;
  }

  public Node getDad() {
    return this.dad;
  }

  public void setDad(Node variable) {
    this.dad = variable;
  }

  public void setLeftChildren(Node variable) {
    this.leftChildren = variable;
  }

  public void setRightChildren(Node variable) {
    this.rightChildren = variable;
  }

  public boolean hasLeftChildren() {
    return this.getLeftChildren() != null;
  }

  public boolean hasRightChildren() {
    return this.getRightChildren() != null;
  }

  public Iterator<Node> children() {
    ArrayList variable = new ArrayList();
    if (this.hasRightChildren()) {
      variable.add(this.getRightChildren());
    }

    if (this.hasLeftChildren()) {
      variable.add(this.getLeftChildren());
    }

    return variable.iterator();
  }
}
