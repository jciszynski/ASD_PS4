package zadanie.BST;

import java.util.Comparator;

public abstract class BST<T> implements Comparator<T> {
    Node root;

    public BST() {

    }

    public class Node<T> {
        T data;
        Node left;
        Node right;

        Node(T data, Node left, Node right) {
            this.data = data;
            this.left = left;
            this.right = right;

        }

        Node(T data) {
            this.data = data;
            left = null;
            right = null;
        }
    }


    private void insert(T data, Node node) throws bstException {
        if (root == null) {
            root = new Node(data);
            return;
        }
        if (compare((T) node.data, data) == 1) {
            if (node.left == null) {
                node.left = new Node(data);
                return;
            }
            insert(data, node.left);
            return;
        }

        if (compare((T) node.data, data) == -1) {
            if (node.right == null) {
                node.right = new Node(data);
                return;
            }
            insert(data, node.right);
            return;
        }

        throw new bstException("Element " + data + " istnieje już w drzewie!!!");
    }

    public void insert(T data) throws bstException {

        insert(data, root);
    }

    private Node delete(T data, Node node) throws bstException {
        if (node == null)
            throw new bstException("Brak elementu w drzewie!!!");

        if (compare((T) node.data, data) == 1) {
            node.left = delete(data, node.left);
            return node;
        }

        if (compare((T) node.data, data) == -1) {
            node.right = delete(data, node.right);
            return node;
        }

        if (node.left == null)
            return node.right;
        if (node.right == null)
            return node.left;

        Node tmp = node.left;
        Node tmpParent = node;

        while (tmp.right != null) {
            tmpParent = tmp;
            tmp = tmp.right;
        }

        node.data = tmp.data;
        if (tmpParent != node)
            tmpParent.right = null;
        else
            tmpParent.left = null;

        return node;
    }

    public void delete(T data) throws bstException {
        root = delete(data, root);
    }

    private String printKLP(Node node) {
        String s = "";
        if (node != null)
            s = node.data + ", ";
        if (node.left != null) s += printKLP(node.left);
        if (node.right != null) s += printKLP(node.right);
        return s;
    }

    private Node search(T data, Node node) {
        if (node == null) return null;
        if (compare((T) node.data, data) == 1)
            return search(data, node.left);
        if (compare((T) node.data, data) == -1)
            return search(data, node.right);

        return node;
    }

    public Node search(T data) {
        return search(data, root);
    }

    public String printKLP() {
        return printKLP(root);
    }

}

