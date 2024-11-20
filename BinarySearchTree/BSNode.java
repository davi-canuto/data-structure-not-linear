// Source code is decompiled from a .class file using FernFlower decompiler.
package BinarySearchTree;

import java.util.ArrayList;
import java.util.Iterator;

public class BSNode {
  private Object element;
  private BSNode leftChildren;
  private BSNode rightChildren;
  private BSNode dad;

  public BSNode(Object variable) {
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

  public BSNode getLeftChildren() {
    return this.leftChildren;
  }

  public BSNode getRightChildren() {
    return this.rightChildren;
  }

  public BSNode getDad() {
    return this.dad;
  }

  public void setDad(BSNode variable) {
    this.dad = variable;
  }

  public void setLeftChildren(BSNode variable) {
    this.leftChildren = variable;
  }

  public void setRightChildren(BSNode variable) {
    this.rightChildren = variable;
  }

  public boolean hasLeftChildren() {
    return this.getLeftChildren() != null;
  }

  public boolean hasRightChildren() {
    return this.getRightChildren() != null;
  }

  public Iterator<BSNode> children() {
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
