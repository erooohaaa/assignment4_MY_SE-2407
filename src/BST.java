import java.util.*;

public class BST<K extends Comparable<K>, V> {
    private Node root;
    private int size;

    private class Node {
        private K key;
        private V val;
        private Node left, right;

        public Node(K key, V val) {
            this.key = key;
            this.val = val;
        }
    }

    public int size() {
        return size;
    }

    public void put(K key, V val) {
        Node newNode = new Node(key, val);
        if (root == null) {
            root = newNode;
            size++;
            return;
        }

        Node current = root;
        Node parent = null;
        while (current != null) {
            parent = current;
            int cmp = key.compareTo(current.key);
            if (cmp < 0) current = current.left;
            else if (cmp > 0) current = current.right;
            else {
                current.val = val;
                return;
            }
        }

        if (key.compareTo(parent.key) < 0) parent.left = newNode;
        else parent.right = newNode;
        size++;
    }

    public V get(K key) {
        Node current = root;
        while (current != null) {
            int cmp = key.compareTo(current.key);
            if (cmp < 0) current = current.left;
            else if (cmp > 0) current = current.right;
            else return current.val;
        }
        return null;
    }

    public void delete(K key) {
        Node parent = null;
        Node current = root;
        while (current != null && !current.key.equals(key)) {
            parent = current;
            int cmp = key.compareTo(current.key);
            if (cmp < 0) current = current.left;
            else current = current.right;
        }

        if (current == null) return;

        if (current.left == null && current.right == null) replaceNode(parent, current, null);
        else if (current.left == null) replaceNode(parent, current, current.right);
        else if (current.right == null) replaceNode(parent, current, current.left);
        else {
            Node successorParent = current;
            Node successor = current.right;
            while (successor.left != null) {
                successorParent = successor;
                successor = successor.left;
            }
            current.key = successor.key;
            current.val = successor.val;
            replaceNode(successorParent, successor, successor.right);
        }
        size--;
    }

    private void replaceNode(Node parent, Node oldNode, Node newNode) {
        if (parent == null) root = newNode;
        else if (parent.left == oldNode) parent.left = newNode;
        else parent.right = newNode;
    }

    public Iterable<Entry<K, V>> iterator() {
        List<Entry<K, V>> entries = new ArrayList<>();
        Stack<Node> stack = new Stack<>();
        Node current = root;

        while (current != null || !stack.isEmpty()) {
            while (current != null) {
                stack.push(current);
                current = current.left;
            }
            current = stack.pop();
            entries.add(new Entry<>(current.key, current.val));
            current = current.right;
        }
        return entries;
    }

    public static class Entry<K, V> {
        private K key;
        private V value;

        public Entry(K key, V value) {
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
}
