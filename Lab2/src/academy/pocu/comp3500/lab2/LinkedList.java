package academy.pocu.comp3500.lab2;

import academy.pocu.comp3500.lab2.datastructure.Node;

import javax.swing.plaf.IconUIResource;

public final class LinkedList {
    private LinkedList() { }

    public static Node append(final Node rootOrNull, final int data) {
        if (rootOrNull == null){
            return null;
        }

        var newNode = new Node(data);
        var tempNode = rootOrNull;
        while (tempNode.getNextOrNull() != null)
            tempNode = tempNode.getNextOrNull();

        tempNode.setNext(newNode);
        return rootOrNull;
    }

    public static Node prepend(final Node rootOrNull, final int data) {
        if (rootOrNull == null)
            return null;

        var newNode = new Node(data);
        newNode.setNext(rootOrNull);
        return newNode;
    }

    public static Node insertAt(final Node rootOrNull, final int index, final int data) {
        if (rootOrNull == null)
            return null;

        if (index == 0)
        {
            Node newNode = new Node(data);
            newNode.setNext(rootOrNull);
        }

        else {
            Node currentNode = rootOrNull;
            var currentIndex = 1;
            while (currentIndex != index) {
                currentNode = currentNode.getNextOrNull();
                if (currentNode == null)
                    return null;
                currentIndex++;
            }

            var newNode = new Node(data);
            newNode.setNext(currentNode.getNextOrNull());
            currentNode.setNext(newNode);
        }

        return rootOrNull;
    }

    public static Node removeAt(final Node rootOrNull, final int index) {
        return null;
    }

    public static int getIndexOf(final Node rootOrNull, final int data) {
        return -1;
    }

    public static Node getOrNull(final Node rootOrNull, final int index) {
        return null;
    }

    public static Node reverse(final Node rootOrNull) {
        return null;
    }

    public static Node interleaveOrNull(final Node root0OrNull, final Node root1OrNull) {
        return null;
    }
}