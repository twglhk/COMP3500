package academy.pocu.comp3500.lab2;

import academy.pocu.comp3500.lab2.datastructure.Node;

import javax.swing.plaf.IconUIResource;

public final class LinkedList {
    private LinkedList() {
    }

    public static Node append(final Node rootOrNull, final int data) {
        var newNode = new Node(data);
        if (rootOrNull == null)
            return newNode;

        var tempNode = rootOrNull;
        while (tempNode.getNextOrNull() != null)
            tempNode = tempNode.getNextOrNull();

        tempNode.setNext(newNode);
        return rootOrNull;
    }

    public static Node prepend(final Node rootOrNull, final int data) {
        var newNode = new Node(data);
        if (rootOrNull != null)
            newNode.setNext(rootOrNull);
        return newNode;
    }

    public static Node insertAt(final Node rootOrNull, final int index, final int data) {
        var newNode = new Node(data);

        if (rootOrNull == null)
            return newNode;

        if (index < 0)
            return rootOrNull;

        if (index == 0) {
            newNode.setNext(rootOrNull);
            return newNode;
        } else {
            var beforeNode = rootOrNull;
            var currentNode = rootOrNull.getNextOrNull();
            var currentIndex = 1;
            while (currentIndex < index && currentNode != null) {
                beforeNode = currentNode;
                currentNode = currentNode.getNextOrNull();
                currentIndex++;
            }

            if (currentIndex != index)
                return rootOrNull;

            newNode.setNext(currentNode);
            beforeNode.setNext(newNode);

            return rootOrNull;
        }
    }

    public static Node removeAt(final Node rootOrNull, final int index) {
        if (index < 0)
            return rootOrNull;

        if (rootOrNull == null)
            return null;

        var beforeNode = rootOrNull;
        var currentNode = rootOrNull.getNextOrNull();

        if (index == 0) {
            if (currentNode == null)
                return null;
            else
                return currentNode;
        }

        var currentIndex = 1;
        while (currentIndex < index && currentNode != null) {
            beforeNode = currentNode;
            currentNode = currentNode.getNextOrNull();
            currentIndex++;
        }

        if (currentNode == null)
            beforeNode.setNext(null);

        else
            beforeNode.setNext(currentNode.getNextOrNull());

        return rootOrNull;
    }

    public static int getIndexOf(final Node rootOrNull, final int data) {

        var currentNode = rootOrNull;
        var currentIndex = 0;
        while (currentNode != null) {
            if (currentNode.getData() == data)
                return currentIndex;
            currentNode = currentNode.getNextOrNull();
            currentIndex++;
        }
        return -1;
    }

    public static Node getOrNull(final Node rootOrNull, final int index) {
        var currentNode = rootOrNull;
        var currentIndex = 0;
        while (currentNode != null) {
            if (currentIndex == index)
                return currentNode;
            currentNode = currentNode.getNextOrNull();
            currentIndex++;
        }
        return null;
    }

    public static Node reverse(final Node rootOrNull) {
        if (rootOrNull == null)
            return null;

        Node tempNode = null;
        var currentNode = rootOrNull;
        var nextNode = rootOrNull.getNextOrNull();
        currentNode.setNext(null);

        while (nextNode != null) {
            tempNode = nextNode.getNextOrNull();
            nextNode.setNext(currentNode);
            currentNode = nextNode;
            nextNode = tempNode;
        }

        return currentNode;
    }

    public static Node interleaveOrNull(final Node root0OrNull, final Node root1OrNull) {
        Node root;
        Node currentNode;
        Node firstListCurrentNode = root0OrNull;
        Node secondListCurrentNode = root1OrNull;

        if (root0OrNull != null) {
            root = firstListCurrentNode;
            firstListCurrentNode = firstListCurrentNode.getNextOrNull();
        } else if (root1OrNull != null) {
            root = secondListCurrentNode;
            secondListCurrentNode = secondListCurrentNode.getNextOrNull();
        } else {
            return null;
        }

        currentNode = root;
        while (firstListCurrentNode != null || secondListCurrentNode != null) {
            if (secondListCurrentNode != null) {
                currentNode.setNext(secondListCurrentNode);
                currentNode = secondListCurrentNode;
                secondListCurrentNode = secondListCurrentNode.getNextOrNull();
            }
            if (firstListCurrentNode != null) {
                currentNode.setNext(firstListCurrentNode);
                currentNode = firstListCurrentNode;
                firstListCurrentNode = firstListCurrentNode.getNextOrNull();
            }
        }

        return root;
    }
}