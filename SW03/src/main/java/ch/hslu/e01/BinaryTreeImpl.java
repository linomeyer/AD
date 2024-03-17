package ch.hslu.e01;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BinaryTreeImpl<E extends Comparable<E>> implements BinaryTree<E> {
    private final Logger LOG = LoggerFactory.getLogger(getClass());
    Node<E> root;

    public BinaryTreeImpl(E root) {
        this.root = new Node<>(root);
    }

    public BinaryTreeImpl() {
        createExampleTree();
    }

    @SuppressWarnings("unchecked")
    private void createExampleTree() {
        root = (Node<E>) new Node<>(new Item(4));

        // set left side
        root.setLeftChild((Node<E>) new Node<>(new Item(2)));
        Node<E> leftChild = root.getLeftChild();
        leftChild.setLeftChild((Node<E>) new Node<>(new Item(1)));
        leftChild.setRightChild((Node<E>) new Node<>(new Item(3)));

        // set right side
        root.setRightChild((Node<E>) new Node<>(new Item(6)));
        Node<E> rightChild = root.getRightChild();
        rightChild.setLeftChild((Node<E>) new Node<>(new Item(5)));
        rightChild.setRightChild((Node<E>) new Node<>(new Item(7)));
    }

    @Override
    public boolean search(E item) {
        return containsNode(root, item);
    }

    @Override
    public void add(E item) {
        root = add(root, item);
    }

    @Override
    public boolean remove(E item) {
        return false;
    }

    public void inorder() {
        traverseInorder(root);
    }

    public void preorder() {
        traversePreorder(root);
    }

    public void postorder() {
        traversePostorder(root);
    }

    private void traversePreorder(Node<E> currentNode) {
        if (currentNode != null) {
            LOG.info("Traversal -- visited value: " + currentNode.getValue());
            traverseInorder(currentNode.getLeftChild());
            traverseInorder(currentNode.getRightChild());
        }
    }

    private void traversePostorder(Node<E> currentNode) {
        if (currentNode != null) {
            traverseInorder(currentNode.getLeftChild());
            traverseInorder(currentNode.getRightChild());
            LOG.info("Traversal -- visited value: " + currentNode.getValue());
        }
    }

    private void traverseInorder(Node<E> currentNode) {
        if (currentNode != null) {
            traverseInorder(currentNode.getLeftChild());
            LOG.info("Traversal -- visited value: " + currentNode.getValue());
            traverseInorder(currentNode.getRightChild());
        }
    }

    private Node<E> add(Node<E> currentNode, E value) {
        if (currentNode == null) {
            return new Node<>(value);
        }

        if (value.compareTo(currentNode.getValue()) < 0) {
            currentNode.setLeftChild(add(currentNode.getLeftChild(), value));
        } else if (value.compareTo(currentNode.getValue()) > 0) {
            currentNode.setRightChild(add(currentNode.getRightChild(), value));
        } else {
            // value already exists
            return currentNode;
        }

        return currentNode;
    }

    private boolean containsNode(Node<E> node, E value) {
        if (node == null) {
            return false;
        }
        LOG.info("visited value: " + node.getValue().toString());
        if (value.compareTo(node.getValue()) == 0) {
            return true;
        }

        return value.compareTo(node.getValue()) < 0 //
                ? containsNode(node.getLeftChild(), value) //
                : containsNode(node.getRightChild(), value);
    }

    private static class Node<E extends Comparable<E>> {
        private Node<E> leftChild, rightChild;
        private final E value;

        public Node(E value) {
            this.value = value;
            leftChild = rightChild = null;
        }

        public E getValue() {
            return value;
        }

        public Node<E> getLeftChild() {
            return leftChild;
        }

        public void setLeftChild(Node<E> leftChild) {
            this.leftChild = leftChild;
        }

        public Node<E> getRightChild() {
            return rightChild;
        }

        public void setRightChild(Node<E> rightChild) {
            this.rightChild = rightChild;
        }
    }
}