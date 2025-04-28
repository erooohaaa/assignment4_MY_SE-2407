/**
 * A class used as a key in the hash table for testing purposes.
 */
public class MyTestingClass {
    private int id;
    private String name;

    public MyTestingClass(int id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Overriding hashCode to generate a unique hash based on id and name.
     * @return unique hash value.
     */
    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + id;
        for (char c : name.toCharArray()) {
            result = 31 * result + c;
        }
        return result;
    }

    /**
     * Overriding equals to compare objects properly.
     * @param obj object to compare with
     * @return true if both objects are equal.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        MyTestingClass other = (MyTestingClass) obj;
        return id == other.id && name.equals(other.name);
    }

    /**
     * Overriding toString to represent MyTestingClass objects as a string.
     * @return string representation of the object.
     */
    @Override
    public String toString() {
        return "(" + id + ", " + name + ")";
    }
}
