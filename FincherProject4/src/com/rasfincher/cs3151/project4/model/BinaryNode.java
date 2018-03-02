package com.rasfincher.cs3151.project4.model;

public class BinaryNode<T> {

  public static final boolean RED = true;
  public static final boolean BLACK = false;
  private boolean color;
  private T element;
  private BinaryNode<T> left;
  private BinaryNode<T> right;
  private BinaryNode<T> parent;
  
  public BinaryNode(T element) {
    this.element = element;
    this.left = null;
    this.right = null;
    this.color = RED;
    this.parent = null;
  }

  public boolean getColor() {
    return color;
  }

  public boolean isRed() {
    return this.getColor();
  }
  
  public void setRed() {
    this.color = RED;
  }
  
  public void setBlack() {
    this.color = BLACK;
  }
  public boolean isBlack() {
    return !this.getColor();
  }
  
  public void setColor(boolean color) {
    this.color = color;
  }

  public BinaryNode<T> getParent() {
    return parent;
  }

  public void setParent(BinaryNode<T> parent) {
    this.parent = parent;
  }

  public T getElement() {
    return element;
  }

  public void setElement(T element) {
    this.element = element;
  }

  public BinaryNode<T> getLeft() {
    return left;
  }

  public void setLeft(BinaryNode<T> left) {
    this.left = left;
  }

  public BinaryNode<T> getRight() {
    return right;
  }

  public void setRight(BinaryNode<T> right) {
    this.right = right;
  }
  
  public boolean hasLeft() {
    return this.left != null;
  }
  
  public boolean hasRight() {
    return this.right != null;
  }
  
  public boolean isLeaf() {
    return !hasLeft() && !hasRight();
  }
  
  public boolean isLeftChild() {
    return (this.parent != null && this == this.getParent().getLeft());
  }
  
  public boolean isRightChild() {
    return (this.parent != null && this == this.getParent().getRight());
  }
  
  public boolean hasParent() {
    return parent != null;
  }
}
