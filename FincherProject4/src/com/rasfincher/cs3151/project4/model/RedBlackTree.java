package com.rasfincher.cs3151.project4.model;

public class RedBlackTree<T extends Comparable<T>> extends BinarySearchTree<T> {

  @Override
  public void add(T element) {
    //insert node using BST insertion
  
    BinaryNode<T> node = this.getRoot();
    if(node == null) {
      this.setRoot(new BinaryNode<T>(element));
      this.getRoot().setBlack();
      return;
    }
    BinaryNode<T> newNode = new BinaryNode<T>(element);
    boolean descend = true;
    while(descend) {
      int cmp = element.compareTo(node.getElement());
      if(cmp<0) {
        if(node.hasLeft()) {
          node = node.getLeft();
        }
        else {
          newNode.setParent(node);
          node.setLeft(newNode);
          descend = false;
        }
      }
      else {
        if(node.hasRight()) {
          node = node.getRight();
        }
        else {
          newNode.setParent(node);
          node.setRight(newNode);
          descend = false;
        }
      }
    } //newNode inserted
  
    // now repair red-black structure and rotate if necessary
    // newNode is not root so must have a parent
  
    while(newNode.getParent().isRed()) {
      //parent can not be root so grand must exist
////    System.out.println("IN LOOP");
      BinaryNode<T> grand = newNode.getParent().getParent();
      assert(grand.isBlack());
      if(newNode.getParent().isLeftChild()) {
          node = grand.getRight(); //uncle of newNode
          if(node != null && node.isRed()) {
////            System.out.println("RED RIGHT UNCLE");
            flip_colors(grand);
            newNode = grand;
            if(grand == this.getRoot()) {
              grand.setBlack();
              return;
            }
////            System.out.println("LOOPING");
          }
          else { //uncle is black, maybe null
            if(newNode.isRightChild()) {
              node = newNode.getParent();
////              System.out.println("ROTATE LEFT");
              rotateLeft(node);
              newNode = node;
            }
////            System.out.println("newnode = "+newNode.getElement());
            newNode.getParent().setBlack();
            grand = newNode.getParent().getParent();
////            System.out.println("GRAND="+grand.getElement());
            grand.setRed();
            rotateRight(grand);
////            System.out.println("ROOT="+this.getRoot().getElement());
////            System.out.println("RETURN");
            return;
          }
      }
      else {  //parent of newNode is a right child 
            //switch left and right 
          node = grand.getLeft(); // uncle of newNode
          if(node != null && node.isRed()) {
////            System.out.println("RED LEFT UNCLE");
            flip_colors(grand);
            newNode = grand;
            if(grand == this.getRoot()) {
              grand.setBlack();
              return;
            }
////            System.out.println("LOOPING");
          }
          else { //uncle is black, may be null
            if(newNode.isLeftChild()) {
              node = newNode.getParent();
              rotateRight(node);
              newNode = node;
            }
            newNode.getParent().setBlack();
            grand = newNode.getParent().getParent();
            grand.setRed();
            rotateLeft(grand);
////            System.out.println("RETURN");
            return;
          }
      }
    }
    if(newNode.getParent() == null) {
      System.out.println("NULL PARENT");
    }
  }
  
  private void flip_colors(BinaryNode<T> node) {
    node.setRed();
    node.getLeft().setBlack();
    node.getRight().setBlack();
  }
  private void rotateRight(BinaryNode<T> node) {
    BinaryNode<T> tmp = node.getLeft();
    if(tmp == null) {
      return;
    }
    node.setLeft(tmp.getRight());
    if(node.hasLeft()) {
      node.getLeft().setParent(node);
    }
    tmp.setParent(node.getParent());
    if(node.hasParent()) {
      if(node.isLeftChild()) {
        tmp.getParent().setLeft(tmp);
      }
      else {
        tmp.getParent().setRight(tmp);
      }
    }
    tmp.setRight(node);
    node.setParent(tmp);
    if(!tmp.hasParent()) {
      this.setRoot(tmp);
    }
  }
  
    private void rotateLeft(BinaryNode<T> node) {
      BinaryNode<T> tmp = node.getRight();
    if(tmp == null) {
      return;
    }
    node.setRight(tmp.getLeft());
    if(node.hasRight()) {
      node.getRight().setParent(node);
    }
    tmp.setParent(node.getParent());
    if(node.hasParent()) {
      if(node.isRightChild()) {
        tmp.getParent().setRight(tmp);
      }
      else {
        tmp.getParent().setLeft(tmp);
      }
    }
    tmp.setLeft(node);
    node.setParent(tmp);
    if(!tmp.hasParent()) {
      this.setRoot(tmp);
    }
  }
}
