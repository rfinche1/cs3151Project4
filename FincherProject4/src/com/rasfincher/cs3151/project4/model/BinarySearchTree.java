package com.rasfincher.cs3151.project4.model;

public class BinarySearchTree<T extends Comparable<T>> extends BinaryTree<T> {
public void add(T element) {
		
		if(getRoot() == null) {
			this.setRoot(new BinaryNode<T>(element));
			return;
		}
		BinaryNode<T> node = this.getRoot();
		while(true) {
			if(element.compareTo(node.getElement()) < 0) {
				if(node.hasLeft()) {
					node = node.getLeft();
				}else {
					node.setLeft(new BinaryNode<T>(element)); 
					return;
				}
			}
			else {
				if(node.hasRight()) {
					node = node.getRight();
				}
				else {
					node.setRight(new BinaryNode<T>(element));
					return;
				}
			}
		}
	}
}
