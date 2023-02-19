package logic;

import structures.basic.Card;

public class TheOnelist {


    private int size;
    private Node head;

    private class Node {
        Card data;
        Node next;

        Node(Card data) {
            this.data = data;
            this.next = null;
        }
    }

    public TheOnelist() {
        this.size = 0;
        this.head = null;

    }

    public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public void add(Card card) {
        if (head == null) {
            head = new Node(card);
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = new Node(card);
        }
        size++;
    }

    public Card remove(int index) {
    	if (index >= size)
    	{
    		return null;
    	}
        if (index < 0 ) {
            throw new IndexOutOfBoundsException();
        }
        Node current = head;
        if (index == 0) {
            head = current.next;
            size--;
            return current.data;
        }
        for (int i = 0; i < index - 1; i++) {
            current = current.next;
        }
        Node toRemove = current.next;
        current.next = toRemove.next;
        size--;
        toRemove.next = null;
        return toRemove.data;
    }
    public Card get(int index) {
        if (index < 0 ) {
            throw new IndexOutOfBoundsException();
        }
        else if(index >= size)
        {
        	return null;
        }
        Node current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.data;
    }
    public void clear() {
        head = null;
        size = 0;
    }
	
}
