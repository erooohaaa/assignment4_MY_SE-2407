/**
 * Custom hash table implementation using separate chaining.
 *
 * This class provides basic operations such as inserting key-value pairs,
 * retrieving values by key, removing entries, and checking for the existence
 * of a value. It uses separate chaining to handle hash collisions.
 *
 * @param <K> the type of keys
 * @param <V> the type of values
 */
public class MyHashTable<K, V> {
    /**
     * A node class used to store the key-value pair and reference to the next node
     * in case of collision (separate chaining).
     */
    private class HashNode<K, V> {
        private K key;    // The key for this entry
        private V value;  // The value associated with the key
        private HashNode<K, V> next;  // Reference to the next node in the chain

        public HashNode(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return "{" + key + " " + value + "}";  // String representation of the key-value pair
        }
    }

    private HashNode<K, V>[] chainArray;  // Array of linked lists (chains) for handling collisions
    private int M = 11;  // The initial number of buckets (can be modified)
    private int size;  // Number of elements in the hash table

    /**
     * Default constructor that initializes the hash table with 11 buckets.
     */
    public MyHashTable() {
        chainArray = new HashNode[M];
        size = 0;
    }

    /**
     * Constructor that allows specifying the number of buckets for the hash table.
     *
     * @param M the number of buckets
     */
    public MyHashTable(int M) {
        this.M = M;
        chainArray = new HashNode[M];
        size = 0;
    }

    /**
     * Hash function that computes the index for a given key.
     * This function uses the hash code of the key and applies modulo with M
     * to ensure the index is within bounds.
     *
     * @param key the key to hash
     * @return the calculated bucket index
     */
    private int hash(K key) {
        return Math.abs(key.hashCode()) % M;
    }

    /**
     * Inserts a new key-value pair into the hash table.
     * If the key already exists, updates the corresponding value.
     *
     * @param key the key of the entry to insert
     * @param value the value associated with the key
     */
    public void put(K key, V value) {
        int bucketIndex = hash(key);  // Find the bucket index using the hash function
        HashNode<K, V> head = chainArray[bucketIndex];

        // Check if the key already exists, and if so, update its value
        while (head != null) {
            if (head.key.equals(key)) {
                head.value = value;
                return;
            }
            head = head.next;  // Traverse the linked list if key is not found
        }

        // If the key doesn't exist, create a new node and insert it at the beginning of the chain
        size++;
        head = chainArray[bucketIndex];
        HashNode<K, V> newNode = new HashNode<>(key, value);
        newNode.next = head;
        chainArray[bucketIndex] = newNode;
    }

    /**
     * Retrieves the value associated with the specified key.
     *
     * @param key the key to search for
     * @return the value associated with the key, or null if the key is not found
     */
    public V get(K key) {
        int bucketIndex = hash(key);  // Find the bucket index using the hash function
        HashNode<K, V> head = chainArray[bucketIndex];

        // Traverse the linked list to find the key and return its associated value
        while (head != null) {
            if (head.key.equals(key)) {
                return head.value;
            }
            head = head.next;
        }
        return null;  // Return null if the key is not found
    }

    /**
     * Removes a key-value pair from the hash table and returns the associated value.
     *
     * @param key the key to remove
     * @return the value associated with the key, or null if the key is not found
     */
    public V remove(K key) {
        int bucketIndex = hash(key);  // Find the bucket index using the hash function
        HashNode<K, V> head = chainArray[bucketIndex];
        HashNode<K, V> prev = null;

        // Traverse the linked list to find the key
        while (head != null) {
            if (head.key.equals(key)) {
                break;
            }
            prev = head;
            head = head.next;
        }

        // Return null if the key is not found
        if (head == null) return null;

        // If the key is found, remove the node
        size--;
        if (prev != null) {
            prev.next = head.next;  // Remove node by skipping it
        } else {
            chainArray[bucketIndex] = head.next;  // Remove node if it is the first node in the chain
        }
        return head.value;  // Return the removed value
    }

    /**
     * Checks if a particular value exists in the hash table.
     *
     * @param value the value to search for
     * @return true if the value exists in the hash table, false otherwise
     */
    public boolean contains(V value) {
        // Iterate over all the chains in the hash table
        for (int i = 0; i < M; i++) {
            HashNode<K, V> head = chainArray[i];
            while (head != null) {
                if (head.value.equals(value)) {
                    return true;  // Value found, return true
                }
                head = head.next;  // Traverse the linked list
            }
        }
        return false;  // Return false if value is not found
    }

    /**
     * Retrieves the key corresponding to a given value.
     *
     * @param value the value to search for
     * @return the key corresponding to the value, or null if the value is not found
     */
    public K getKey(V value) {
        // Iterate over all the chains in the hash table
        for (int i = 0; i < M; i++) {
            HashNode<K, V> head = chainArray[i];
            while (head != null) {
                if (head.value.equals(value)) {
                    return head.key;  // Return the key if value is found
                }
                head = head.next;  // Traverse the linked list
            }
        }
        return null;  // Return null if value is not found
    }

    /**
     * Returns the sizes of each bucket in the hash table as an array.
     *
     * @return an array with the number of elements in each bucket
     */
    public int[] bucketSizes() {
        int[] sizes = new int[M];  // Array to store bucket sizes
        for (int i = 0; i < M; i++) {
            int count = 0;
            HashNode<K, V> current = chainArray[i];
            while (current != null) {
                count++;
                current = current.next;  // Count the nodes in the current bucket
            }
            sizes[i] = count;  // Store the count in the array
        }
        return sizes;  // Return the array with bucket sizes
    }
}
