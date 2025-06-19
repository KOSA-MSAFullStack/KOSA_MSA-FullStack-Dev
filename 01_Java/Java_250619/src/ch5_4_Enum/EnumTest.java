package ch5_4_Enum;

//Enum to represent the days of the week
enum Day {
SUNDAY, MONDAY, TUESDAY, WEDNESDAY,
THURSDAY, FRIDAY, SATURDAY
}
public class EnumTest {
// Instance variable to hold the day
Day day;

// Constructor to initialize the day
public EnumTest(Day day) {
    this.day = day;
}

// Method to print messages based on the day of the week
public void tellItLikeItIs() {
    switch (day) {
        case MONDAY:
            System.out.println("Mondays are bad.");
            break;
               
        case FRIDAY:
            System.out.println("Fridays are better.");
            break;
                    
        case SATURDAY:
        case SUNDAY:
            System.out.println("Weekends are best.");
            break;
                   
        default:
            System.out.println("Midweek days are so-so.");
            break;
    }
}

// Main method to run the program
public static void main(String[] args) {
    // Creating EnumTest objects for different days and printing messages
    EnumTest firstDay = new EnumTest(Day.MONDAY);
    firstDay.tellItLikeItIs();
   
    EnumTest thirdDay = new EnumTest(Day.WEDNESDAY);
    thirdDay.tellItLikeItIs();
   
    EnumTest fifthDay = new EnumTest(Day.FRIDAY);
    fifthDay.tellItLikeItIs();
   
    EnumTest sixthDay = new EnumTest(Day.SATURDAY);
    sixthDay.tellItLikeItIs();
   
    EnumTest seventhDay = new EnumTest(Day.SUNDAY);
    seventhDay.tellItLikeItIs();
}
}
