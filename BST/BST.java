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

    private int size() {

    }

    public static void main(String args[]) {
        BST binSearchTree = new BST();
        binSearchTree.insert(5);
        binSearchTree.insert(10);
        binSearchTree.insert(3);
        System.out.println(binSearchTree);
    }

}