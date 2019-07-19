package com.comtrade.cyrcleList;

import java.awt.Image;

import javax.swing.ImageIcon;

import com.comtrade.constants.PicturesURL;
import com.comtrade.domain.property.Property_Picutre_Album;

public class CyrclularList {
	private Node head;
	private Node tail;
	private Node current;
	
	public Property_Picutre_Album next() {
		current=current.getNext();
		return current.data;
	}
	
	public Property_Picutre_Album previous() {
		current=current.getPrevious();
		return current.data;
	}
	
	public Property_Picutre_Album previousX2() {
		current=current.getPrevious().getPrevious();
		return current.data;
	}
	
	public Property_Picutre_Album nextX2() {
		current=current.next.next;
		return current.data;
	}
	
	public Property_Picutre_Album current() {
		return current.data;
	}
	
	public void append(Property_Picutre_Album property_picture_album) {
		Node newNode=new Node(property_picture_album);
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
	
	public void remove() {
		head=null;
		tail=null;
		current=null;
	}
	
	public class Node {
		
		private Property_Picutre_Album data;
		private Node next;
		private Node previous;
		
		public Node(Property_Picutre_Album data) {
			super();
			this.data = data;
		}
		public Property_Picutre_Album getData() {
			return data;
		}
		public void setData(Property_Picutre_Album data) {
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
