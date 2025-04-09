package PhotoApp;

import java.io.File;

public class LinkedList<T> {
    private Node<T> head;
    private Node<T> current;

    public LinkedList() {
        head = current = null;
    }

    public boolean empty() {
        return head == null;
    }

    public boolean last() {
        return current.next == null;
    }

    public boolean full() {
        return false;
    }

    public void findFirst() {
        current = head;
    }

    public void findNext() {
        current = current.next;
    }

    public T retrieve() {
        return current.data;
    }

    public void update(T e) {
        current.data = e;
    }

    public void insert(T e) {
        if (empty()) {
            current = head = new Node<T>(e);
        } else {
            Node<T> tmp = current.next;
            current.next = new Node<T>(e);
            current = current.next;
            current.next = tmp;
        }
    }

    public void remove() {
        if (current == head) {
            head = head.next;
        } else {
            Node<T> tmp = head;
            while (tmp.next != current) {
                tmp = tmp.next;
            }
            tmp.next = current.next;
        }
        if (current.next == null) {
            current = head;
        } else {
            current = current.next;
        }
    }

    private static class Node<T> {
        private T data;
        private Node<T> next;

        public Node(T data) {
            this.data = data;
            next = null;
        }
    }

    // ? Additions to the LinkedList class

    public boolean contains(T element) {
        Node<T> tmp = head;
        while (tmp != null) {
            if (tmp.data.equals(element)) {
                return true;
            }
            tmp = tmp.next;
        }
        return false;
    }

    // for testing purposes
    public Integer size() {
        int size = 0;
        Node<T> tmp = head;
        while (tmp != null) {
            size++;
            tmp = tmp.next;
        }
        return size;
    }

    public T getFirst() {
        if (head == null) {
            return null; // or throw an exception
        }
        return head.data; // Assuming T is of type File
    }
}
