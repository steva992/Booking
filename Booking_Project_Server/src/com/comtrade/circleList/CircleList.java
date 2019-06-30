package com.comtrade.circleList;

import java.awt.Image;

import javax.swing.ImageIcon;

public class CircleList {
	private Node head;
	private Node tail;
	private Node current;
	
	public ImageIcon next() {
		current=current.getNext();
		return new ImageIcon(current.data);
	}
	
	public ImageIcon previous() {
		current=current.getPrevious();
		return new ImageIcon(current.data);
	}
	
	public void append(String img) {
		Node newNode=new Node(new ImageIcon(this.getClass().getResource(img)).getImage());
		if(head==null) {
			head=newNode;
			tail=newNode;
			tail.next=head;
			head.previous=tail;
		}else {
			tail.next=newNode;
			newNode.previous=tail;
			tail=newNode;
			tail.next=head;
			head.previous=tail;
		}
		
		current=head;
		
	}
	
	public class Node {
		
		private Image data;
		private Node next;
		private Node previous;
		
		public Node(Image data) {
			super();
			this.data = data;
		}
		public Image getData() {
			return data;
		}
		public void setData(Image data) {
			this.data = data;
		}
		public Node getNext() {
			return next;
		}
		public void setNext(Node next) {
			this.next = next;
		}
		public Node getPrevious() {
			return previous;
		}
		public void setPrevious(Node previous) {
			this.previous = previous;
		}
		
		
	}
}
