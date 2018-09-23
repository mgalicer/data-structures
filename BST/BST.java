import java.lang.Math;

public class BST {
    private Node root;

    private static class Node {
        Node left;
        Node right;
        int data;

        Node(int newData) {
            left = null;
            right = null;
            data = newData;
        }
    }

    public BST() {
        root = null;
    }

    public boolean lookup(int data) {
        return lookup(root, data);
    }

    private boolean lookup(Node node, int data) {
        if(node == null) return false;
        if(node.data == data) return true;
        if(data < node.data) {
            return lookup(node.left, data);
        } else {
            return lookup(node.right, data);
        }
    }

    public void insert(int data) {
        root = insert(root, data);
    }

    private Node insert(Node node, int data) {
        if(node == null) {
            node = new Node(data);
        } else {
            if(data < node.data) {
                node.left = insert(node.left, data);
            } else {
                node.right = insert(node.right, data);
            }
        }
        return node;
    }

    public int size() {
        return size(root);
    }

    private int size(Node node) {
        if(node == null) return 0;
        return 1 + size(node.left) + size(node.right);
    }

    public int maxDepth() {
        return maxDepth(root);
    }

    private int maxDepth(Node node) {
        if(node == null) return 0;
        return Math.max(1 + maxDepth(node.left), 1 + maxDepth(node.right));
    }

    public int minValue() {
        return minValue(root);
    }

    private int minValue(Node node) {
        if(node.left == null) return node.data;
        return minValue(node.left);
    }



    public static void main(String args[]) {
        BST binSearchTree = new BST();
        binSearchTree.insert(5);
        binSearchTree.insert(10);
        binSearchTree.insert(1);
        binSearchTree.insert(8);
        binSearchTree.insert(4);



        System.out.println(binSearchTree.minValue());

    }

}