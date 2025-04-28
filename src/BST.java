import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

/**
 * A generic Binary Search Tree (BST) implementation that supports operations
 * like insertion, deletion, and searching for key-value pairs.
 *
 * @param <K> the type of keys (must be Comparable)
 * @param <V> the type of values
 */
public class BST<K extends Comparable<K>, V> implements Iterable<BST<K, V>.NodeEntry> {

    private Node root;  // Root of the binary search tree
    private int size;   // Size of the tree (number of nodes)

    /**
     * Node class that represents each element in the BST.
     */
    public class Node {
        private K key;    // Key of the node
        private V val;    // Value associated with the key
        private Node left, right;  // Left and right child nodes

        public Node(K key, V val) {
            this.key = key;
            this.val = val;
        }
    }

    /**
     * NodeEntry class used to return key-value pairs while iterating over the tree.
     */
    public class NodeEntry {
        private final K key;  // Key of the entry
        private final V value; // Value of the entry

        public NodeEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }
    }

    /**
     * Inserts a key-value pair into the BST.
     * If the key already exists, updates the associated value.
     *
     * @param key the key to insert
     * @param val the value associated with the key
     */
    public void put(K key, V val) {
        root = put(root, key, val);
    }

    /**
     * Helper method for inserting a key-value pair recursively.
     *
     * @param node the current node
     * @param key the key to insert
     * @param val the value to associate with the key
     * @return the updated node
     */
    private Node put(Node node, K key, V val) {
        if (node == null) {
            size++;
            return new Node(key, val);  // Insert a new node if the tree is empty at this position
        }
        int cmp = key.compareTo(node.key);  // Compare keys to navigate the tree
        if (cmp < 0) {
            node.left = put(node.left, key, val);  // Insert in the left subtree
        } else if (cmp > 0) {
            node.right = put(node.right, key, val);  // Insert in the right subtree
        } else {
            node.val = val;  // Update the value if the key already exists
        }
        return node;
    }

    /**
     * Retrieves the value associated with the given key.
     *
     * @param key the key to search for
     * @return the value associated with the key, or null if not found
     */
    public V get(K key) {
        Node node = root;
        while (node != null) {
            int cmp = key.compareTo(node.key);
            if (cmp < 0) {
                node = node.left;  // Move to the left subtree
            } else if (cmp > 0) {
                node = node.right;  // Move to the right subtree
            } else {
                return node.val;  // Return the value if the key is found
            }
        }
        return null;  // Return null if the key is not found
    }

    /**
     * Deletes the key-value pair associated with the given key.
     *
     * @param key the key to delete
     */
    public void delete(K key) {
        root = delete(root, key);
    }

    /**
     * Helper method for deleting a key-value pair recursively.
     *
     * @param node the current node
     * @param key the key to delete
     * @return the updated node after deletion
     */
    private Node delete(Node node, K key) {
        if (node == null) return null;  // Key not found, return null
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = delete(node.left, key);  // Search in the left subtree
        } else if (cmp > 0) {
            node.right = delete(node.right, key);  // Search in the right subtree
        } else {
            size--;  // Decrease the size of the tree as we are deleting a node
            if (node.right == null) return node.left;  // No right child, return the left child
            if (node.left == null) return node.right;  // No left child, return the right child
            Node t = node;
            node = min(t.right);  // Find the minimum node in the right subtree
            node.right = deleteMin(t.right);  // Delete the minimum node from the right subtree
            node.left = t.left;  // Link the left child of the deleted node to the new node
        }
        return node;
    }

    /**
     * Finds the minimum node in the given subtree.
     *
     * @param node the root node of the subtree
     * @return the minimum node
     */
    private Node min(Node node) {
        if (node.left == null) return node;  // Minimum node is the leftmost node
        else return min(node.left);
    }

    /**
     * Deletes the minimum node in the given subtree.
     *
     * @param node the root node of the subtree
     * @return the updated subtree with the minimum node removed
     */
    private Node deleteMin(Node node) {
        if (node.left == null) return node.right;  // If left child is null, return the right child
        node.left = deleteMin(node.left);  // Recursively delete the minimum node from the left subtree
        return node;
    }

    /**
     * Returns the size of the tree (number of nodes).
     *
     * @return the size of the tree
     */
    public int size() {
        return size;
    }

    /**
     * Returns an iterator to iterate through the BST in order.
     *
     * @return an iterator
     */
    @Override
    public Iterator<NodeEntry> iterator() {
        return new BSTIterator();
    }

    /**
     * BSTIterator class to traverse the tree in order (in-order traversal).
     */
    private class BSTIterator implements Iterator<NodeEntry> {
        private Stack<Node> stack = new Stack<>();  // Stack to hold the nodes for in-order traversal

        public BSTIterator() {
            pushLeft(root);  // Push all the left nodes from the root
        }

        /**
         * Helper method to push all left nodes onto the stack.
         *
         * @param node the node to start pushing from
         */
        private void pushLeft(Node node) {
            while (node != null) {
                stack.push(node);
                node = node.left;  // Move to the left child
            }
        }

        /**
         * Checks if there are more elements to iterate over.
         *
         * @return true if there are more nodes to visit, false otherwise
         */
        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        /**
         * Returns the next node in the in-order traversal.
         *
         * @return the next NodeEntry in the traversal
         * @throws NoSuchElementException if there are no more elements
         */
        @Override
        public NodeEntry next() {
            if (!hasNext()) throw new NoSuchElementException();
            Node node = stack.pop();  // Pop the top node from the stack
            NodeEntry entry = new NodeEntry(node.key, node.val);  // Create a NodeEntry for the current node
            if (node.right != null) {
                pushLeft(node.right);  // Push all left nodes of the right child onto the stack
            }
            return entry;
        }
    }
}
