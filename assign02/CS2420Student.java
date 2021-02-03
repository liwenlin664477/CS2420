package assign02;

/** A class for a CS2420 Student. Will contain firstName, lastName, and uNID from UofUStudent, 
 *  and will also contain an EMAIL ADRESS and scores for tests.
 * @author Carter Edginton & Wenlin Li
 * @version February 2, 2021
 */
public class CS2420Student extends UofUStudent {


	//Private instance variables.
	private EmailAddress email;
	
	private double assignmentScore;
	private int assignmentAmount;
	private double examScore;
	private int examAmount;
	private double labScore;
	private int labAmount;
	private double quizScore;
	private int quizAmount;
	
	
	
	/**Creates a new CS2420Student class. Needs a first name, last name, uNID, and an EMAIL from the EmailAddress class. 
	 * 
	 * @param firstName Student's first name.
	 * @param lastName Student's last name.
	 * @param uNID Student's uNID (cannot be changed!)
	 * @param contactInfo Student's email address. 
	 */
	public CS2420Student(String firstName, String lastName, int uNID, EmailAddress contactInfo) {
		super(firstName, lastName, uNID);
		this.email = contactInfo;
	}
	
	/**Getter method for student's email. 
	 * 
	 * @return returns student's email address. 
	 */
	public EmailAddress getContactInfo() {
		return this.email;
	}

	/**adds a score amount to the assignment, exam, lab, or quiz category. Check category parameter for proper adding. 
	 * 
	 * @param score
	 * @param category MUST BE *EXACTLY* "assignment", "exam", "lab", or "quiz". any misspelling will result in the score not being added.
	 */
	public void addScore(double score, String category) {
		switch (category) {
			case "assignment":
				this.assignmentScore += score;
				this.assignmentAmount++;
				break;
			case "exam":
				this.examScore += score;
				this.examAmount++;
				break;
			case "lab":
				this.labScore += score;
				this.labAmount++;
				break;
			case "quiz":
				this.quizScore += score;
				this.quizAmount++;
				break;
		}
	}
	
	private double calculateWeightedPercentage(double theScore, int theAmount, double thePercent) {
		double weight = (theScore/(theAmount*100.0)) * thePercent; //score / total possible score, then multiplied by percentage.
		return weight;
	}
	
	public double computeFinalScore() {
		if (this.assignmentAmount < 1 || this.examAmount < 1 || this.labAmount < 2 || this.quizAmount < 2) {
			return 0.0; // minimum amount of all types of assignment for class. 
		}
		//calculate the final score. Do this by calculating percentage of type, multiply by weight, add all together.
		double totalScore = 0.0;
		totalScore += calculateWeightedPercentage(this.assignmentScore, this.assignmentAmount, 0.5);
		totalScore += calculateWeightedPercentage(this.examScore, this.examAmount, 0.3);
		totalScore += calculateWeightedPercentage(this.labScore, this.labAmount, 0.1);
		totalScore += calculateWeightedPercentage(this.quizScore, this.quizAmount, 0.1);
		return totalScore;
	}

	public String computeFinalGrade() {
		if (this.assignmentAmount < 1 || this.examAmount < 1 || this.labAmount < 2 || this.quizAmount < 2) {
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
