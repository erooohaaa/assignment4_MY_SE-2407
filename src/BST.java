    import java.util.Iterator;
    import java.util.NoSuchElementException;
    import java.util.Stack;

    public class BST<K extends Comparable<K>, V> implements Iterable<BST<K, V>.NodeEntry> {
        private Node root;
        private int size;

        public class Node {
            private K key;
            private V val;
            private Node left, right;

            public Node(K key, V val) {
                this.key = key;
                this.val = val;
            }
        }

        public class NodeEntry {
            private final K key;
            private final V value;

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

        public void put(K key, V val) {
            root = put(root, key, val);
        }

        private Node put(Node node, K key, V val) {
            if (node == null) {
                size++;
                return new Node(key, val);
            }
            int cmp = key.compareTo(node.key);
            if (cmp < 0) {
                node.left = put(node.left, key, val);
            } else if (cmp > 0) {
                node.right = put(node.right, key, val);
            } else {
                node.val = val;
            }
            return node;
        }

        public V get(K key) {
            Node node = root;
            while (node != null) {
                int cmp = key.compareTo(node.key);
                if (cmp < 0) {
                    node = node.left;
                } else if (cmp > 0) {
                    node = node.right;
                } else {
                    return node.val;
                }
            }
            return null;
        }

        public void delete(K key) {
            root = delete(root, key);
        }

        private Node delete(Node node, K key) {
            if (node == null) return null;
            int cmp = key.compareTo(node.key);
            if (cmp < 0) {
                node.left = delete(node.left, key);
            } else if (cmp > 0) {
                node.right = delete(node.right, key);
            } else {
                size--;
                if (node.right == null) return node.left;
                if (node.left == null) return node.right;
                Node t = node;
                node = min(t.right);
                node.right = deleteMin(t.right);
                node.left = t.left;
            }
            return node;
        }

        private Node min(Node node) {
            if (node.left == null) return node;
            else return min(node.left);
        }

        private Node deleteMin(Node node) {
            if (node.left == null) return node.right;
            node.left = deleteMin(node.left);
            return node;
        }

        public int size() {
            return size;
        }

        @Override
        public Iterator<NodeEntry> iterator() {
            return new BSTIterator();
        }

        private class BSTIterator implements Iterator<NodeEntry> {
            private Stack<Node> stack = new Stack<>();

            public BSTIterator() {
                pushLeft(root);
            }

            private void pushLeft(Node node) {
                while (node != null) {
                    stack.push(node);
                    node = node.left;
                }
            }

            @Override
            public boolean hasNext() {
                return !stack.isEmpty();
            }

            @Override
            public NodeEntry next() {
                if (!hasNext()) throw new NoSuchElementException();
                Node node = stack.pop();
                NodeEntry entry = new NodeEntry(node.key, node.val);
                if (node.right != null) {
                    pushLeft(node.right);
                }
                return entry;
            }
        }
    }
