package ch5_3_NestedClasses;

//Top-level class (not nested)
class TopLevelClass {
// Method to access members of OuterClass
void accessMembers(OuterClass outer) {    
    // Accessing non-static field through an instance of OuterClass
    System.out.println(outer.outerField);
   
    // Accessing static field directly using the class name
    System.out.println(OuterClass.staticOuterField);
} 
}
public class OuterClass {
// Non-static (instance) field
String outerField = "Outer field";

// Static field
static String staticOuterField = "Static outer field";
// Non-static nested class (Inner Class)
class InnerClass {
    // Method to access OuterClass members
    void accessMembers() {
        // Direct access to both instance and static fields
        System.out.println(outerField);
        System.out.println(staticOuterField);
    }
}
// Static nested class
static class StaticNestedClass {
    // Method to access OuterClass members
    void accessMembers(OuterClass outer) {
        // Cannot access non-static field directly, must use an instance
        System.out.println(outer.outerField);
       
        // Can access static field directly
        System.out.println(staticOuterField);
    }
}
// Main method to run the program
public static void main(String[] args) {
    System.out.println("Inner class:");
    System.out.println("------------");
   
    // Creating an instance of OuterClass
    OuterClass outerObject = new OuterClass();
   
    // Creating an instance of InnerClass
    OuterClass.InnerClass innerObject = outerObject.new InnerClass();
    innerObject.accessMembers();
    System.out.println("\nStatic nested class:");
    System.out.println("--------------------");
   
    // Creating an instance of StaticNestedClass
    StaticNestedClass staticNestedObject = new StaticNestedClass();       
    staticNestedObject.accessMembers(outerObject);
   
    System.out.println("\nTop-level class:");
    System.out.println("--------------------");
   
    // Creating an instance of TopLevelClass
    TopLevelClass topLevelObject = new TopLevelClass();       
    topLevelObject.accessMembers(outerObject);               
}
}

