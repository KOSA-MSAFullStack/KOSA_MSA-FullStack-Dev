package ch6_3_LambdaExpression;

//Interface for analyzing strings
interface StringAnalyzer5 {
	boolean analyze(String sourceStr, String searchStr);
}

//Class that checks if a string contains another string by implementing StringAnalyzer
class ContainsAnalyzer5 implements StringAnalyzer5 {
	// Override the analyze method to check if target contains searchStr
	@Override
	public boolean analyze(String target, String searchStr) {
		return target.contains(searchStr);
	}
}

//Utility class for analyzing arrays of strings
public class Z06Analyzer {
	// Static method to search an array of strings using a StringAnalyzer
	public static void searchArr(String[] strList, String searchStr, StringAnalyzer5 analyzer) {
		for (String currentStr : strList) {
			if (analyzer.analyze(currentStr, searchStr)) {
				System.out.println("Match: " + currentStr);
			}
		}
	}

//Main class to run the program
	public static void main(String[] args) {
		// Array of strings to search through
		String[] strList01 = { "tomorrow", "toto", "to", "timbukto", "the", "hello", "heat" };
		String searchStr = "to"; 		// String to search for
		
		System.out.println("Searching for: " + searchStr);
		
		// Using short form Lambda expression for 'contains'
		System.out.println("==Contains==");
		Z06Analyzer.searchArr(strList01, searchStr, (t, s) -> t.contains(s));
		
		// Changing logic easily with 'startsWith'
		System.out.println("==Starts With==");
		Z06Analyzer.searchArr(strList01, searchStr, (t, s) -> t.startsWith(s));
	}
}
