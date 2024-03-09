package ch.hslu.e01;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BinaryTreeImplTest {
    @Test
    void createExampleTree_searchRightMostItem_true() {
        BinaryTreeImpl<Item> tree = new BinaryTreeImpl<>();
        assertTrue(tree.search(new Item(7)));
    }

    @Test
    void createExampleTree_searchLeftMostItem_true() {
        BinaryTreeImpl<Item> tree = new BinaryTreeImpl<>();
        assertTrue(tree.search(new Item(1)));
    }

    @Test
    void createExampleTree_searchRootItem_true() {
        BinaryTreeImpl<Item> tree = new BinaryTreeImpl<>();
        assertTrue(tree.search(new Item(4)));
    }

    @Test
    void createExampleTree_searchItems_true() {
        BinaryTreeImpl<Item> tree = new BinaryTreeImpl<>();
        assertTrue(tree.search(new Item(5)));
        assertTrue(tree.search(new Item(6)));
        assertTrue(tree.search(new Item(2)));
        assertTrue(tree.search(new Item(3)));
    }

    @Test
    void createExampleTree_searchItems_false() {
        BinaryTreeImpl<Item> tree = new BinaryTreeImpl<>();
        assertFalse(tree.search(new Item(9)));
        assertFalse(tree.search(new Item(14)));
        assertFalse(tree.search(new Item(26)));
        assertFalse(tree.search(new Item(-3)));
    }

    @Test
    void createExampleTree_addNodes_nodeAreAddedAndTreeStructureIsStillCorrect() {
        BinaryTreeImpl<Item> tree = new BinaryTreeImpl<>();
        tree.add(new Item(12));
        tree.add(new Item(-3));
        tree.add(new Item(9));

        // to look at printout of binary tree (from logs)
        tree.inorder();

        assertTrue(tree.search(new Item(9)));
        assertTrue(tree.search(new Item(12)));
        assertTrue(tree.search(new Item(-3)));
    }

    @Test
    void createEmptyTree_addNodes_nodeAreAddedAndTreeStructureIsStillCorrect() {
        BinaryTreeImpl<Item> tree = new BinaryTreeImpl<>(new Item(6));
        tree.add(new Item(12));
        tree.add(new Item(-3));
        tree.add(new Item(9));

        // to look at printout of binary tree (from logs)
        tree.inorder();

        assertTrue(tree.search(new Item(6)));
        assertTrue(tree.search(new Item(9)));
        assertTrue(tree.search(new Item(12)));
        assertTrue(tree.search(new Item(-3)));
    }
}