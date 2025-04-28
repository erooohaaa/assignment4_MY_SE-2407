import java.util.Random;

/**
 * Main class to test the MyHashTable implementation.
 * This class generates random keys and values, stores them in the hash table,
 * and then prints the sizes of the buckets in the hash table.
 */
public class Main {
    public static void main(String[] args) {
        // Create a hash table with 20 buckets
        MyHashTable<MyTestingClass, Student> table = new MyHashTable<>(20);

        // Create a random number generator to generate random keys and values
        Random random = new Random();

        // Insert 10,000 random key-value pairs into the hash table
        for (int i = 0; i < 10000; i++) {
            int id = random.nextInt(100000); // Random ID between 0 and 99,999
            String name = "Name" + random.nextInt(1000); // Random name with number suffix
            MyTestingClass key = new MyTestingClass(id, name); // Create a key
            Student value = new Student("Student" + id, random.nextInt(30) + 18); // Create a student value with random age between 18 and 48
            table.put(key, value); // Insert the key-value pair into the hash table
        }

        // Retrieve and print the sizes of each bucket in the hash table
        int[] bucketSizes = table.bucketSizes();
        for (int i = 0; i < bucketSizes.length; i++) {
            System.out.println("Bucket " + i + ": " + bucketSizes[i] + " elements"); // Print bucket index and size
        }
    }
}
