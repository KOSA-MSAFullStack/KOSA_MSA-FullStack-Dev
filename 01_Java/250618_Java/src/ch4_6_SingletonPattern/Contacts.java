package ch4_6_SingletonPattern;

public final class Contacts {
   // Final fields for first and last names to make them immutable
   private final String firstName;
   private final String lastName;
   // Constructor to initialize first and last names
   public Contacts(String fname, String lname) {
       this.firstName = fname;
       this.lastName = lname;
   }
   // Getter method for first name
   public String getFirstName() {
       return firstName;
   }
   // Getter method for last name
   public String getLastName() {
       return lastName;
   }
   // Override toString method to provide a string representation of the object
   public String toString() {
       return firstName + " - " + lastName + " - " + lastName;
   }
   // Main method to execute the program
   public static void main(String[] args) {
       // Create a new Contacts object
       Contacts contact = new Contacts("John", "Doe");
      
       // Print the contact information
       System.out.println(contact);
   }
}

