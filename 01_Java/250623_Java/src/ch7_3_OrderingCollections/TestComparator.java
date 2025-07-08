package ch7_3_OrderingCollections;

import java.util.*;

//Class representing a student
class Student {
	private String name; // Student name
	private long id; // Student ID
	private double gpa; // Student GPA
// Constructor to initialize the student object

	public Student(String name, long id, double gpa) {
		this.name = name;
		this.id = id;
		this.gpa = gpa;
	}

// Getters
	public String getName() {
		return name;
	}

	public long getId() {
		return id;
	}

	public double getGpa() {
		return gpa;
	}

// Override toString to provide a readable representation of a student
	@Override
	public String toString() {
		return "Name: " + name + ", ID: " + id + ", GPA: " + gpa;
	}
}

//Comparator to sort students by name
class StudentSortName implements Comparator<Student> {
	@Override
	public int compare(Student s1, Student s2) {
		int result = s1.getName().compareTo(s2.getName());
		return result;
	}
}

//Comparator to sort students by GPA (descending order)
class StudentSortGpa implements Comparator<Student> {
	@Override
	public int compare(Student s1, Student s2) {
		return Double.compare(s2.getGpa(), s1.getGpa()); // Descending order
	}
}

//Test class to demonstrate sorting with comparators
public class TestComparator {
	public static void main(String[] args) {
		// Create a list of students
		List<Student> studentList = new ArrayList<>();
		studentList.add(new Student("Thomas Jefferson", 1111, 3.8));
		studentList.add(new Student("John Adams", 2222, 3.9));
		studentList.add(new Student("George Washington", 3333, 3.4));
		// Create comparators
		Comparator<Student> sortName = new StudentSortName();
		Comparator<Student> sortGpa = new StudentSortGpa();
		// Sort by name
		System.out.println("=== Sorted by Name ===");
		Collections.sort(studentList, sortName);
		for (Student student : studentList) {
			System.out.println(student);
		}
		// Sort by GPA
		System.out.println("\n=== Sorted by GPA ===");
		Collections.sort(studentList, sortGpa);
		for (Student student : studentList) {
			System.out.println(student);
		}
	}
}
