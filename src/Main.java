import java.util.Random;

public class Main {
    public static void main(String[] args) {
        MyHashTable<MyTestingClass, Student> table = new MyHashTable<>(20);
        Random random = new Random();

        for (int i = 0; i < 10000; i++) {
            int id = random.nextInt(100000);
            String name = "Name" + random.nextInt(1000);
            MyTestingClass key = new MyTestingClass(id, name);
            Student value = new Student("Student" + id, random.nextInt(30) + 18);
            table.put(key, value);
        }


        int[] bucketSizes = table.bucketSizes();
        for (int i = 0; i < bucketSizes.length; i++) {
            System.out.println("Bucket " + i + ": " + bucketSizes[i] + " elements");
        }
    }
}
