package ch6_2_AnonymousInnerClass;

//Class that provides a method to check if a string contains another string
class AnalyzerTool {
//Method to check if sourceStr contains searchStr
	public boolean arrContains(String sourceStr, String searchStr) {
		return sourceStr.contains(searchStr);
	}
}

//Main class to run the program
public class Z01Analyzer {
	public static void main(String[] args) {
		// Array of strings to search through
		String[] strList = { "tomorrow", "toto", "to", "timbukto", "the", "hello", "heat" };
		String searchStr = "to"; 		// String to search for
		
		System.out.println("Searching for: " + searchStr);
		
		// Create an instance of AnalyzerTool
		AnalyzerTool analyzeTool = new AnalyzerTool();
		System.out.println("===Contains===");
		
		// Iterate through each string in the list
		for (String currentStr : strList) {
			// Check if the current string contains the search string
			if (analyzeTool.arrContains(currentStr, searchStr)) {
				System.out.println("Match: " + currentStr);
			}
		}
	}
}
