package com.revature.vinson_chin_p0.util;

/**
 * A simple implementation of a doubly linked-list structure that
 * does not accept null data.
 * @author Vinson Chin
 *
 * @param <T>
 */
public class LinkedList<T> implements List<T>, Queue<T> {

    private int size;
    private Node<T> head;
    private Node<T> tail;

    /**
     * Add node with data to linked list
     *
     * @param data
     */
    @Override
    public void add(T data) throws IllegalArgumentException {

        if (data == null) {
            throw new IllegalArgumentException("This linked list does not accept null values");
        }

        Node<T> newNode = new Node<T>(data);
        if (head == null) {
            tail = head = newNode; // sets both the head and tail equal to the new node
        } else {
            tail.nextNode = newNode;
            newNode.prevNode = tail;
            tail = newNode;
        }

        size++;

    }

    /**
     * Returns and removes the head node's data or else returns null.
     *
     * @return
     */
    @Override
    public T poll() {

        if (head == null) {
            return null;
        }

        T soughtData = head.data;
        head = head.nextNode;

        if (head != null) {
            head.prevNode = null;
        } else {
            tail = null;
        }

        size--;

        return soughtData;

    }

    /**
     * Look at data in first node
     *
     * @return
     */
    @Override
    public T peek() {

        if (head == null) {
            return null;
        }

        return head.data;
    }

    /**
     * Remove node with that contains data
     *
     * @param data
     * @return
     */
    @Override
    public T remove(T data) {

        if (head == null) {
            return null;
        }

        Node<T> current = head;
        while (current != null) {
            if (current.data == data) {
                if (current == head) {
                    head = head.nextNode;
                    if (head != null) {
                        head.prevNode = null;
                    } else {
                        tail = null;
                    }
                } else if ((current == tail) && (current != head)) {
                    tail = tail.prevNode;
                    tail.nextNode = null;
                } else {
                    Node<T> temp = current.prevNode;
                    current.prevNode.nextNode = current.nextNode;
                    current.nextNode.prevNode = temp;
                }
                size--;
                return null;
            } else {
                current = current.nextNode;
            }
        }

        return null;

    }

    /**
     * Returns data at index
     *
     * @param index
     * @return
     */
    @Override
    public T get(int index) {

        if (index < 0 || index > size) {
            throw new IllegalArgumentException("The provided index would be out of bounds.");
        }

        Node<T> runner = head;
        for (int i = 0; i < size; i++) {
            if (i == index) {
                return runner.data;
            }
            runner = runner.nextNode;
        }

        return null;
    }

    /**
     * Returns true is linked list has a node with data
     *
     * @param data
     * @return
     */
    @Override
    public boolean contains(T data) {

        if (head == null) {
            return false;
        }

        Node<T> current = head;
        while (current != null) {
            if (current.data == data) {
                return true;
            } else {
                current = current.nextNode;
            }
        }
        return false;
    }

    /**
     * Returns size of linked list
     *
     * @return
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Static nested node class for linked list.
     *
     * @param <T>
     */
    private static class Node<T> {

        T data;
        Node<T> nextNode; // defaults to null
        Node<T> prevNode; // defaults to null

        Node(T data) {
            this.data = data;
        }

        Node(T data, Node<T> nextNode, Node<T> prevNode) {
            this.data = data;
            this.nextNode = nextNode;
            this.prevNode = prevNode;
        }

    }

}
