// Source code is decompiled from a .class file using FernFlower decompiler.
package AVL;

import java.util.ArrayList;
import java.util.Iterator;

public class Node {
  private Object element;
  private Node leftChildren;
  private Node rightChildren;
  private Node dad;
  private int fbc;

  public Node(Object variable) {
    this.element = variable;
    this.leftChildren = null;
    this.rightChildren = null;
    this.dad = null;
    this.fbc = 0;
  }

  public Object getElement() {
    return this.element;
  }

  public void setElement(Object variable) {
    this.element = variable;
  }
  
  public Integer getFbc() {
    return this.fbc;
  }

  public Integer setFbc(Integer variable) {
    this.fbc = variable;
    return this.fbc;
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
