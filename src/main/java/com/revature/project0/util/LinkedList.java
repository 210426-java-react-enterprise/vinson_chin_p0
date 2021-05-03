package com.revature.project0.util;

/**
 * A simple implementation of a doubly linked-list structure that
 * does not accept null data.
 *
 * @param <T>
 */
public class LinkedList<T> implements List<T> {

    private int size;
    private Node<T> head;
    private Node<T> tail;

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
    public T pop() {

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

    @Override
    public T get(int index) {
        return null;
    }

    @Override
    public boolean contains(T data) {
        return false;
    }

    @Override
    public int size() {
        return size;
    }

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
