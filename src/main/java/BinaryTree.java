import java.util.Objects;

public class BinaryTree {

    class Node {

        private int value;
        private Node leftChild;
        private Node rightChild;

        private Node(int value) {
            this.value = value;
            this.leftChild = null;
            this.rightChild = null;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public int getValue() {
            return this.value;
        }

        public void setLeftChild(Node leftChild) {
            this.leftChild = leftChild;
        }

        public Node getLeftChild() {
            return this.leftChild;
        }

        public void setRightChild(Node rightChild) {
            this.rightChild = rightChild;
        }

        public Node getRightChild() {
            return rightChild;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return value == node.value && Objects.equals(leftChild, node.leftChild) && Objects.equals(rightChild, node.rightChild);
        }

        @Override
        public int hashCode() {
            return Objects.hash(value, leftChild, rightChild);
        }

        @Override
        public String toString() {
            return "Node" +
                    " value = " + value;
        }

    }

    private Node root;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BinaryTree tree = (BinaryTree) o;
        return Objects.equals(root, tree.root);
    }

    @Override
    public int hashCode() {
        return Objects.hash(root);
    }

    public BinaryTree() {
        root = null;
    }

    public void addNode(int value) {
        Node node = new Node(value);
        if (root == null) root = node;
        else {
            Node current = root;
            Node parent;
            while (true) {
                parent = current;
                if (value == current.getValue()) {
                    return;
                } else if (value < current.getValue()) {
                    current = current.getLeftChild();
                    if (current == null) {
                        parent.setLeftChild(node);
                        return;
                    }
                } else {
                    current = current.getRightChild();
                    if (current == null) {
                        parent.setRightChild(node);
                        return;
                    }
                }
            }
        }
    }

    public void addArrayOfNodes(int[] values) {
        for (int value : values) {
            addNode(value);
        }
    }

    public void removeNode(int value) {

        Node toRemove = searchNode(value);
        Node parent = findParent(value);

        if (toRemove.getLeftChild() == null && toRemove.getRightChild() == null) {
            if (toRemove == root) root = null;
            else {
                if (parent.getLeftChild().equals(toRemove)) parent.setLeftChild(null);
                else parent.setRightChild(null);
            }

        } else if (toRemove.getLeftChild() != null && toRemove.getRightChild() == null) {
            if (parent.getLeftChild().equals(toRemove)) {
                parent.setLeftChild(toRemove.getLeftChild());
            } else parent.setRightChild(toRemove.getLeftChild());
        } else if (toRemove.getLeftChild() == null && toRemove.getRightChild() != null) {
            if (parent.getLeftChild().equals(toRemove)) {
                parent.setLeftChild(toRemove.getLeftChild());
            } else parent.setRightChild(toRemove.getRightChild());
        } else {
            Node min = min(toRemove.getRightChild());
            findParent(min.getValue()).setLeftChild(min.getRightChild());
            if (parent.getLeftChild().equals(toRemove)) {
                parent.setLeftChild(min);
            } else {
                parent.setRightChild(min);
            }
            min.setLeftChild(toRemove.getLeftChild());
            min.setRightChild(toRemove.getRightChild());
        }
    }

    public Node searchNode(int value) {
        Node current = root;
        while (current.getValue() != value) {
            if (value < current.getValue()) {
                current = current.getLeftChild();
            } else {
                current = current.getRightChild();
            }
            if (current == null) {
                return null;
            }
        }
        return current;
    }

    public Node findParent(int value) {
        Node current = root;
        Node parent = root;

        while (true) {
            if (current.getValue() == value) {
                return parent;
            } else if (value < current.getValue()) {
                parent = current;
                current = current.getLeftChild();
            } else {
                parent = current;
                current = current.getRightChild();
            }
        }
    }

    public Node[] getNeighbours(int value) {
        Node[] neighbours = new Node[3];
        neighbours[0] = findParent(value);
        neighbours[1] = searchNode(value).getLeftChild();
        neighbours[2] = searchNode(value).getRightChild();
        return neighbours;
    }

    private Node min(Node node) {
        while (node.getLeftChild() != null) {
            node = node.getLeftChild();
        }
        return node;
    }

    private Node max(Node node) {
        while (node.getRightChild() != null) {
            node = node.getRightChild();
        }
        return node;
    }

    @Override
    public String toString() {
        return "BinaryTree{" +
                "root=" + root +
                '}';
    }
}
