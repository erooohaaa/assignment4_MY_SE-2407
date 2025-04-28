/**
 * Custom hash table implementation using separate chaining.
 *
 * @param <K> the type of keys
 * @param <V> the type of values
 */
public class MyHashTable<K, V> {
    private class HashNode<K, V> {
        private K key;
        private V value;
        private HashNode<K, V> next;

        public HashNode(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return "{" + key + " " + value + "}";
        }
    }

    private HashNode<K, V>[] chainArray;
    private int M = 11;
    private int size;

    public MyHashTable() {
        chainArray = new HashNode[M];
        size = 0;
    }

    public MyHashTable(int M) {
        this.M = M;
        chainArray = new HashNode[M];
        size = 0;
    }

    /**
     * Hash function to calculate bucket index.
     * @param key the key to hash
     * @return the calculated bucket index
     */
    private int hash(K key) {
        return Math.abs(key.hashCode()) % M;
    }

    /**
     * Adds a key-value pair to the hash table.
     * @param key key of the entry to insert
     * @param value value to insert
     */
    public void put(K key, V value) {
        int bucketIndex = hash(key);
        HashNode<K, V> head = chainArray[bucketIndex];

        while (head != null) {
            if (head.key.equals(key)) {
                head.value = value;
                return;
            }
            head = head.next;
        }

        size++;
        head = chainArray[bucketIndex];
        HashNode<K, V> newNode = new HashNode<>(key, value);
        newNode.next = head;
        chainArray[bucketIndex] = newNode;
    }

    /**
     * Retrieves a value for the given key.
     * @param key the key to look up
     * @return the value associated with the key, or null if the key is not found
     */
    public V get(K key) {
        int bucketIndex = hash(key);
        HashNode<K, V> head = chainArray[bucketIndex];

        while (head != null) {
            if (head.key.equals(key)) {
                return head.value;
            }
            head = head.next;
        }
        return null;
    }

    /**
     * Removes a key-value pair and returns the removed value.
     * @param key the key to remove
     * @return the removed value, or null if the key is not found
     */
    public V remove(K key) {
        int bucketIndex = hash(key);
        HashNode<K, V> head = chainArray[bucketIndex];
        HashNode<K, V> prev = null;

        while (head != null) {
            if (head.key.equals(key)) {
                break;
            }
            prev = head;
            head = head.next;
        }

        if (head == null) return null;

        size--;
        if (prev != null) {
            prev.next = head.next;
        } else {
            chainArray[bucketIndex] = head.next;
        }
        return head.value;
    }

    /**
     * Checks if a value exists in the table.
     * @param value value to search for
     * @return true if the value is present, false otherwise
     */
    public boolean contains(V value) {
        for (int i = 0; i < M; i++) {
            HashNode<K, V> head = chainArray[i];
            while (head != null) {
                if (head.value.equals(value)) {
                    return true;
                }
                head = head.next;
            }
        }
        return false;
    }

    /**
     * Retrieves the key corresponding to a given value.
     * @param value value to search for
     * @return the key corresponding to the value, or null if the value is not found
     */
    public K getKey(V value) {
        for (int i = 0; i < M; i++) {
            HashNode<K, V> head = chainArray[i];
            while (head != null) {
                if (head.value.equals(value)) {
                    return head.key;
                }
                head = head.next;
            }
        }
        return null;
    }

    /**
     * Returns an array with sizes of each bucket.
     * @return an array with bucket sizes
     */
    public int[] bucketSizes() {
        int[] sizes = new int[M];
        for (int i = 0; i < M; i++) {
            int count = 0;
            HashNode<K, V> current = chainArray[i];
            while (current != null) {
                count++;
                current = current.next;
            }
            sizes[i] = count;
        }
        return sizes;
    }
}
