# **ASSIGNMENT 3 - HASHTABLE AND BINARY SEARCH TREE (BST) IMPLEMENTATION**

## **OVERVIEW**  
This project consists of two parts: implementing a custom `MyHashTable` and a `Binary Search Tree (BST)` in Java. The focus is on implementing these data structures manually without using recursion and ensuring proper functionality, including custom hash functions and iterators.

---

## **PART 1 - MYHASHTABLE IMPLEMENTATION**

### **OBJECTIVE:**  
Implement a custom `MyHashTable` class using separate chaining.

The hash table should support basic operations like `put()`, `get()`, and `remove()`.

### **INSTRUCTIONS:**

#### **MyHashTable Class:**
- Implement the `MyHashTable` class that allows for storing key-value pairs.
- Use separate chaining (linked list) to handle collisions.

#### **Testing:**
1. **Create a `MyTestingClass`** that will be used as the key in the `MyHashTable`.
2. **Implement a custom `hashCode()` method** in `MyTestingClass` that avoids using default hashing methods like `Objects.hash()`.
3. **Add 10,000 random elements** to the hash table.
4. **Print the number of elements in each bucket** (chain/linked list) after inserting the elements.
5. **Fine-tune the `hashCode()` method** to ensure uniform distribution and avoid collisions.

### **DELIVERABLES:**
- `MyHashTable.java` file
- `MyTestingClass.java` file (for key implementation)
- Test class with random insertion and bucket size printing.

---

## **PART 2 - BINARY SEARCH TREE (BST) IMPLEMENTATION**

### **OBJECTIVE:**  
Implement a custom `BST` class without recursion.

The tree should support adding key-value pairs, deletion, and size tracking.

### **INSTRUCTIONS:**

#### **BST Class:**
- Implement the `BST` class where each node contains a key-value pair.
- The tree should support `put()`, `get()`, and `delete()` operations.

#### **Additional Functionality:**
1. **Add a method to return the size of the tree (`size()`).**
2. **Implement an in-order traversal method for iteration (`iterator()`),** where both the key and value are accessible during iteration.

### **DELIVERABLES:**
- `BST.java` file
- Test class for BST implementation
