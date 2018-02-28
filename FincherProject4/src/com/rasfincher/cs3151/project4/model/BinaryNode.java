package com.rasfincher.cs3151.project4.model;

public class BinaryNode<T> {

	private T element;
	private BinaryNode<T> left;
	private BinaryNode<T> right;
	
	public BinaryNode(T element) {
		this.element = element;
		this.left = null;
		this.right = null;
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
}
