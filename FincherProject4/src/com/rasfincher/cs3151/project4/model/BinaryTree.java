package com.rasfincher.cs3151.project4.model;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Iterator;

public class BinaryTree<T> implements Iterable<T> {
private BinaryNode<T> root;
	
	public BinaryTree() {
		this.root = null;
	}
	
	public BinaryTree(BinaryNode<T> node) {
		this.root = node;
	}
	
	public BinaryTree(T element) {
		this.root = new BinaryNode<T>(element);
	}

	public BinaryNode<T> getRoot() {
		return root;
	}

	public void setRoot(BinaryNode<T> root) {
		this.root = root;
	}
	
	public BinaryTree<T> getLeftSubTree(){
		return new BinaryTree<T>(this.root.getLeft());
	}
	
	public BinaryTree<T> getRightSubTree(){
		return new BinaryTree<T>(this.root.getRight());
	}
	
	public int size() {
		if (this.root == null) {
		  return 0;
		}
		if( this.root.isLeaf()) {
			return 1;
		}
		int size = 1;
		if(this.root.hasLeft()) {
			size += this.getLeftSubTree().size();
		}
		if(this.root.hasRight()) {
			size += this.getRightSubTree().size();
		}
		return size;
	}
	
	public int height() {
		if(this.root == null || this.root.isLeaf()) {
			return 0;
		}
		int l = this.root.hasLeft()?this.getLeftSubTree().height():0;
		int r = this.root.hasRight()?this.getRightSubTree().height():0;

		return 1 + ((l>r)? l : r);
	}

	public int leafCount() {
		if(this.root == null) {
			return 0;
		}
		if( this.root.isLeaf()) {
			return 1;
		}
		return this.getLeftSubTree().leafCount() + this.getRightSubTree().leafCount();
	}
	
	public int explicitLeafCount() {
		//TODO
		
		return 0;
	}

	public List<T> preOrderTraversal(){
		List<T> list = new LinkedList<T>();
		preOrderTraversal(list);
		return list;
	}

	public void preOrderTraversal(List<T> list){

		list.add(root.getElement());
		if(this.root.hasLeft()) {		
			this.getLeftSubTree().preOrderTraversal(list);
		}
		if(this.root.hasRight()) {		
			this.getRightSubTree().preOrderTraversal(list);
		}

	}

	public List<T> inOrderTraversal(){
		List<T> list = new LinkedList<T>();
		inOrderTraversal(list);
		return list;
	}

	public void inOrderTraversal(List<T> list){


		if(this.root.hasLeft()) {		
			this.getLeftSubTree().inOrderTraversal(list);
		}
		list.add(root.getElement());
		if(this.root.hasRight()) {		
			this.getRightSubTree().inOrderTraversal(list);
		}

	}

	public List<T> postOrderTraversal(){
		List<T> list = new LinkedList<T>();
		postOrderTraversal(list);
		return list;
	}

	public void postOrderTraversal(List<T> list){


		if(this.root.hasLeft()) {		
			this.getLeftSubTree().postOrderTraversal(list);
		}
		if(this.root.hasRight()) {		
			this.getRightSubTree().postOrderTraversal(list);
		}
		list.add(root.getElement());

	}
	 public List<T> levelOrderTraversal(){
		 Queue<BinaryNode<T>> process = new LinkedList<BinaryNode<T>>();
		 List<T> list = new LinkedList<T>();
		 process.add(this.root);
		 while(!process.isEmpty()) {
			 BinaryNode<T> node = process.remove();
			 list.add(node.getElement());
			 if(node.hasLeft()) {
				 process.add(node.getLeft());
			 }
			 if(node.hasRight()) {
				 process.add(node.getRight());
			 }
		 }
		 return list;
		 
	 }
	 
	 public Iterator<T> iterator(){
		 
		 return inOrderTraversal().iterator();
	 }
}
