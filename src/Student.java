/**
 * Class representing a student with name and age.
 */
public class Student {
    private String name;
    private int age;

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    /**
     * Overriding toString to print student details.
     * @return string representation of the student.
     */
    @Override
    public String toString() {
        return name + " (" + age + ")";
    }
}
