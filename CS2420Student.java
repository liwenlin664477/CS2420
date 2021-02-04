package assign02;

import java.util.ArrayList;

/**
 * A class for a CS2420 Student. Will contain firstName, lastName, and uNID from
 * UofUStudent, and will also contain an EMAIL ADRESS and scores for tests.
 * 
 * @author Carter Edginton & Wenlin Li
 * @version February 2, 2021
 */
public class CS2420Student extends UofUStudent {

	// Private instance variables.
	private EmailAddress email;

	private ArrayList<Double> assignments = new ArrayList<Double>();
	private ArrayList<Double> exams = new ArrayList<Double>();
	private ArrayList<Double> labs = new ArrayList<Double>();
	private ArrayList<Double> quizzes = new ArrayList<Double>();

	/**
	 * Creates a new CS2420Student class. Needs a first name, last name, uNID, and
	 * an EMAIL from the EmailAddress class.
	 * 
	 * @param firstName   Student's first name.
	 * @param lastName    Student's last name.
	 * @param uNID        Student's uNID (cannot be changed!)
	 * @param contactInfo Student's email address.
	 */
	public CS2420Student(String firstName, String lastName, int uNID, EmailAddress contactInfo) {
		super(firstName, lastName, uNID);
		this.email = contactInfo;
	}

	/**
	 * Getter method for student's email.
	 * 
	 * @return returns student's email address.
	 */
	public EmailAddress getContactInfo() {
		return this.email;
	}

	/**
	 * adds a score amount to the assignment, exam, lab, or quiz category. Check
	 * category parameter for proper adding.
	 * 
	 * @param score
	 * @param category MUST BE *EXACTLY* "assignment", "exam", "lab", or "quiz". any
	 *                 misspelling will result in the score not being added.
	 */
	public void addScore(double score, String category) {
		
		switch (category) {
		case "assignment":
			this.assignments.add(Double.valueOf(score));
			break;
		case "exam":	
			this.exams.add(Double.valueOf(score));
			break;
		case "lab":
			this.labs.add(Double.valueOf(score));
			break;
		case "quiz":
			this.quizzes.add(Double.valueOf(score));
			break;
	}
}
	

	/**Helper method for computeFinalScore()
	 * 
	 * @param theScore
	 * @param thePercent
	 * @param dropLowest if you want to drop the lowest score, make this true.
	 * @return returns the weighted score. add these 
	 */
	private double calculateWeightedPercentage(ArrayList<Double> theScore, double thePercent, Boolean dropLowest) {
		//if dropLowest is true, then find the lowest score to exclude. 
		if (dropLowest) {
			ArrayList<Double> droppedList = new ArrayList<Double>();
			double lowest = theScore.get(0);
			int lowestIdx = 0;
			for (int idx = 0; idx < theScore.size(); idx++) {
				if (theScore.get(idx) < lowest) {
					lowest = theScore.get(idx);
					lowestIdx = idx;
				}
			}//at this point we will have found the lowest score and its index. 
			for (int idx = 0; idx < lowestIdx; idx++) {
				droppedList.add(theScore.get(idx));
			}
			for (int idx = lowestIdx + 1; idx < theScore.size(); idx++) {
				droppedList.add(theScore.get(idx)); 
			}
			return calculateWeightedPercentage(droppedList, thePercent, false); //saves on code by pretending its not dropped.
			
		}else { //this is the code for if a score is NOT dropped.
			double totalScore = 0.0;
			for (Double scores: theScore) {
				totalScore += scores;
			}
			return (totalScore/(100.0*theScore.size())) * thePercent ;
			
		}
		
		
		
	}
	
	/** Computes the final percentage of all scores for a student. Will not function properly without 
	 * 1 score in assignments and exams,
	 * 2 scores in labs and quizzes. 
	 * 
	 * @return 0.0 if not enough scores or student receives 0 on everything. 
	 */
	public double computeFinalScore() {
		if (this.assignments.size() < 1 || this.exams.size() < 1 || this.labs.size() < 2 || this.quizzes.size() < 2) {
			return 0.0; // minimum amount of all types of assignment for class. 
		}
		//calculate the final score. Do this by calculating percentage of type, multiply by weight, add all together.
		double totalScore = 0.0;
		totalScore += calculateWeightedPercentage(this.assignments, .5, false);
		totalScore += calculateWeightedPercentage(this.exams, 0.3, false);
		totalScore += calculateWeightedPercentage(this.labs, 0.1, true);
		totalScore += calculateWeightedPercentage(this.quizzes, 0.1, true);
		return totalScore * 100; //the 100 is for bringing to what the number looks as a % eg 99.9% rather than .999
	}

	/** Calculates what the final letter grade of all your different scores will be. uses computeFinalScore().
	 * 
	 * @return
	 */
	public String computeFinalGrade() {
		if (this.assignments.size() < 1 || this.exams.size() < 1 || this.labs.size() < 2 || this.quizzes.size() < 2) {
			return "N/A"; // minimum amount of all types of assignment for class. 
		}
			double myScore = this.computeFinalScore();
			if (myScore >= 93.0) {
				return "A";
			}else if (myScore >= 90.0) {
				return "A-";
			}else if (myScore >= 87.0) {
				return "B+";
			}else if (myScore >= 83.0) {
				return "B";
			}else if (myScore >= 80.0) {
				return "B-";
			}else if (myScore >= 77.0) {
				return "C+";
			}else if (myScore >= 73.0) {
				return "C";
			}else if (myScore >= 70.0) {
				return "C-";
			}else if (myScore >= 67.0) {
				return "D+";
			}else if (myScore >= 63.0) {
				return "D";
			}else if (myScore >= 60.0) {
				return "D-";
			}else {
				return "E";
			}
	}



}