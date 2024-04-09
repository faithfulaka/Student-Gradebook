import java.util.Arrays;
import java.util.Scanner;

public class XX007 {
	public static void main(String[] args) {
		// Open a scanner to get user input
		try (Scanner scanner = new Scanner(System.in)) {
				//Get a number of students taking the module (entered by the user)
				System.out.print ("No of students in module? ");
				int numberofstudentsobject = scanner.nextInt();
					
				// Prints how many students in module
				System.out.println("No of students in module: " + numberofstudentsobject);
				
				// Create an empty array of students to store the relevant information for all studentsobject entered above
				Student[] studentsobject = new Student[numberofstudentsobject];

				// Loop through the number of students entered and get their information
				for (int i = 0; i< numberofstudentsobject; i++) {
					System.out.println( "Enter the information of student " + (i+1) + ":");
					System.out.print ("First Name: ");
					String FIRSTNAME = scanner.next(); // Ask user for first name
					System.out.print ("Surname: ");
					String SURNAME = scanner.next(); // Ask user for the surname
					int[] HOMEWORKMark = new int[5]; // Loop through homework marks, asking user each time
					for (int j = 0; j < 5; j++) { 
						System.out.print ("Marks For The Homework " + (j+1) + ": ");
						HOMEWORKMark[j] = scanner.nextInt();
					}
					System.out.print ("Marks For The Interim Test: "); 
					int INTERIM = scanner.nextInt(); // Ask user for the Interim mark
					System.out.print ("Marks For The Exam: ");
					double EXAMMark = scanner.nextDouble(); // Ask user for the Exam mark
					
					// Create objects for studentsobject
					studentsobject[i] = new Student (FIRSTNAME, SURNAME, HOMEWORKMark, INTERIM, EXAMMark);
				}
				
				System.out.println("\nNumber of studentsobject: " + numberofstudentsobject); // Print sentence plus no of studentsobject
				System.out.println("ID\tName\tSurname\tCoursework\tExam\tFinalMark"); // Print a table top row
		        
		        // Loop through eachstudentsobject and calculate their grades
				for (Student student : studentsobject) {
		        	//Get best three homework marks
		        	int[] THREEHOMEWORK = getBestThreeMarks (student.getHOMEWORKMark());
					//calculate the coursework mark (average of the best three homework marks * 0.6
		        	double COURSEWORKMARK = (Arrays.stream (THREEHOMEWORK).average().orElse(0) * 0.6) + (student.getINTERIM() * 0.4);        
		        	// Calculate the final mark (coursework mark * 0.4 4 exam mark • 0.6)
		        	double FINALMARK = COURSEWORKMARK * 0.4 + student.getEXAMMark() * 0.6;
		        	
		        	//Print the student's information and grades
		        	System.out.printf("%-8s %-8s %-8s %-8.2f%% %-8.2f%% %.2f%%%n", student.getId(), student.getFIRSTNAME(), student.getSURNAME(), COURSEWORKMARK, student.getEXAMMark(), FINALMARK);
				}
		}
}
	// Get the best three marks
	public static int[] getBestThreeMarks(int[] HOMEWORKMark) {
		Arrays.sort(HOMEWORKMark);
		return new int[] {HOMEWORKMark[2], HOMEWORKMark[3], HOMEWORKMark[4]};
	}
	// class to store info on each student
	public static class Student {
		private static int count = 1; // variable to store studentsobject
		private String id; //  ID
		private String FIRSTNAME; //  Name
		private String SURNAME; //  Surname
		private int[] HOMEWORKMark; //  Homework
		private int INTERIM; //  Interim
		private double EXAMMark; //  Exammark
			
		public Student (String FIRSTNAME, String SURNAME, int[] HOMEWORKMark, int INTERIM, double EXAMMark) {
			this.id = String.valueOf(count);
			count++;
			this.FIRSTNAME = FIRSTNAME;
			this.SURNAME = SURNAME;
			this.HOMEWORKMark = HOMEWORKMark;
			this.INTERIM = INTERIM;
			this.EXAMMark = EXAMMark;
		}
		public String getId() {
			return id;
		}
		public String getFIRSTNAME() {
			return FIRSTNAME;
		}
		public String getSURNAME() {
			return SURNAME;
		}
        public int[] getHOMEWORKMark() {
            return HOMEWORKMark;
        }
        public int getINTERIM() {
            return INTERIM;
        }
        public double getEXAMMark() {
            return EXAMMark;
        }
    }
} 
