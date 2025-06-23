package ch7_3_OrderingCollections;

import java.util.Set;
import java.util.TreeSet;
// Class representing a student that implements Comparable
class ComparableStudent implements Comparable<ComparableStudent> {
   private String name;   // Student name
   private long id;       // Student ID
   private double gpa;    // Student GPA
   // Constructor to initialize the student object
   public ComparableStudent(String name, long id, double gpa) {
       this.name = name;
       this.id = id;
       this.gpa = gpa;
   }
   // Getter for name
   public String getName() {
       return this.name;
   }
   // Getter for ID
   public long getId() {
       return this.id;
   }
   // Getter for GPA
   public double getGpa() {
       return this.gpa;
   }
   // Override toString to provide a readable representation of a student
   @Override
   public String toString() {
       return "Name: " + name + ", ID: " + id + ", GPA: " + gpa;
   }
   // Implement compareTo for natural ordering (by name)
   @Override
   public int compareTo(ComparableStudent s) {
       int result = this.name.compareTo(s.getName());
       if (result != 0) {
           return result;
       }
       // If names are the same, compare by ID
       return Long.compare(this.id, s.getId());
   }
}
// Test class for ComparableStudent
public class TestComparable {
   public static void main(String[] args) {
       // TreeSet to store ComparableStudent objects, ensuring natural ordering
       Set<ComparableStudent> studentList = new TreeSet<>();
      
       // Adding students to the TreeSet
       studentList.add(new ComparableStudent("Thomas Jefferson", 1111, 3.8));
       studentList.add(new ComparableStudent("John Adams", 2222, 3.9));
       studentList.add(new ComparableStudent("George Washington", 3333, 3.4));
       studentList.add(new ComparableStudent("John Adams", 4444, 3.6)); // Duplicate name, different ID
       // Iterating and printing students in natural order (by name)
       for (ComparableStudent student : studentList) {
           System.out.println(student);
       }
   }
}

