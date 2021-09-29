import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BinaryTreeTest {
    BinaryTree tree = new BinaryTree();

    @Test
    void addNodeTest() {
        tree.addArrayOfNodes(new int[]{15, 16, 14});
        assertEquals(15, tree.searchNode(15).getValue());
        assertEquals(16, tree.searchNode(16).getValue());
        assertEquals(14, tree.searchNode(14).getValue());
    }

    @Test
    void removeNodeTest() {
        tree.addArrayOfNodes(new int[]{15, 5, 3, 12, 10, 13, 6, 7, 16, 20, 23, 18});

        assertArrayEquals(new BinaryTree.Node[]{tree.searchNode(5), tree.searchNode(10), tree.searchNode(13)}, tree.getNeighbours(12));
        tree.removeNode(13);
        assertArrayEquals(new BinaryTree.Node[]{tree.searchNode(5), tree.searchNode(10), null}, tree.getNeighbours(12));

        assertArrayEquals(new BinaryTree.Node[]{tree.searchNode(16), tree.searchNode(18), tree.searchNode(23)}, tree.getNeighbours(20));
        tree.removeNode(16);
        assertArrayEquals(new BinaryTree.Node[]{tree.searchNode(15), tree.searchNode(18), tree.searchNode(23)}, tree.getNeighbours(20));

        assertArrayEquals(new BinaryTree.Node[]{tree.searchNode(12), tree.searchNode(6), null}, tree.getNeighbours(10));
        tree.removeNode(6);
        assertArrayEquals(new BinaryTree.Node[]{tree.searchNode(12), tree.searchNode(7), null}, tree.getNeighbours(10));
    }

    @Test
    void searchNodeTest() {
        tree.addNode(15);
        tree.addNode(16);

        assertEquals(15, tree.searchNode(15).getValue());
        assertEquals(16, tree.searchNode(16).getValue());

        assertNull(tree.searchNode(20));
    }

    @Test
    void getNeighboursTest() {
        tree.addArrayOfNodes(new int[]{20, 16, 18, 19, 17});
        assertArrayEquals(new BinaryTree.Node[]{tree.searchNode(16), tree.searchNode(17), tree.searchNode(19)}, tree.getNeighbours(18));
    }

    @Test
    void equalsTest() {
        BinaryTree tree = new BinaryTree();
        tree.addArrayOfNodes(new int[]{15, 20, 18});

        BinaryTree tree1 = new BinaryTree();
        tree1.addArrayOfNodes(new int[]{15, 20, 18});

        BinaryTree tree2 = new BinaryTree();
        tree2.addArrayOfNodes(new int[]{15, 20, 19});

        assertEquals(tree, tree1);
        assertNotEquals(tree, tree2);
    }

    @Test
    void hashCodeTest() {
        BinaryTree tree = new BinaryTree();
        tree.addArrayOfNodes(new int[]{15, 20, 18});

        BinaryTree tree1 = new BinaryTree();
        tree1.addArrayOfNodes(new int[]{15, 20, 18});

        BinaryTree tree2 = new BinaryTree();
        tree2.addArrayOfNodes(new int[]{15, 20, 19});

        assertEquals(tree.hashCode(), tree1.hashCode());
        assertNotEquals(tree.hashCode(), tree2.hashCode());

    }

}