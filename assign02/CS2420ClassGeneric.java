package assign02;

import java.util.ArrayList;

/**
 * This Java class represents an unordered collection of University of Utah students enrolled in CS 2420.
 * 
 * NOTE: The word "Class" in the name of this Java class means a collection of students and should not 
 *       be confused with the Java term class, which is a blueprint for making objects.
 * 
 * @author Erin Parker and Wenlin Li and Carter Edginton
 * @version January 27, 2021
 */
public class CS2420ClassGeneric <Type> {

	private ArrayList<CS2420StudentGeneric<Type>> studentList;
	
	/**
	 * Creates an empty CS 2420 class.
	 */
	public CS2420ClassGeneric() {
		this.studentList = new ArrayList<CS2420StudentGeneric<Type>>();
	}
	
	/**
	 * Adds the given student to the collection of students in CS 2420, avoiding duplicates.
	 * 
	 * @param student - student to be added to this CS 2420 class
	 * @return true if the student was added, 
	 *         false if the student was not added because they already exist in the collection
	 */
	public boolean addStudent(CS2420StudentGeneric<Type> student) {
		// FILL IN -- do not return false unless appropriate
		if (lookup(student.getUNID()) == null) {
			this.studentList.add(student);
			return true;
		}
		return false;
	}
	
	/**
	 * Retrieves the CS 2420 student with the given uNID.
	 * 
	 * @param uNID - uNID of student to be retrieved
	 * @return the CS 2420 student with the given uNID, or null if no such student exists in the collection
	 */
	public CS2420StudentGeneric<Type> lookup(int uNID) {
//		for (CS2420Student student: studentList) {
		for (int idx = 0; idx < studentList.size(); idx++) {
			if (studentList.get(idx).getUNID() == uNID) {
				return studentList.get(idx);
			}
		}
		return null;
	}
	
	/**
	 * Retrieves the CS 2420 student(s) with the given contact information.
	 * 
	 * @param contactInfo - contact information of student(s) to be retrieved
	 * @return a list of the CS 2420 student(s) with the given contact information (in any order), 
	 * 	     or an empty list if no such students exist in the collection
	 */
	public ArrayList<CS2420StudentGeneric<Type>> lookup(Type contactInfo) {
		ArrayList<CS2420StudentGeneric<Type>> allWithInfo = new ArrayList<CS2420StudentGeneric<Type>>();
		for (int idx = 0; idx < this.studentList.size(); idx++) {
			if (this.studentList.get(idx).getContactInfo().equals(contactInfo)) {
				allWithInfo.add(this.studentList.get(idx));
			}
		}
		return allWithInfo;
	}
	
	/**
	 * Adds an assignment, exam, lab, or quiz score for the CS 2420 student with the given uNID.
	 * 
	 * NOTE: If the category string is not one of "assignment", "exam", "lab", or "quiz", or
	 *       if no student with the uNID exists in the collection, then this method has no effect.
	 * 
	 * @param uNID - uNID of student whose score is to be added
	 * @param score - the score to be added
	 * @param category - the category in which to add the score
	 */
	public void addScore(int uNID, double score, String category) {
		CS2420StudentGeneric<Type> testStudent = lookup(uNID);
		if (testStudent != null) {
			testStudent.addScore(score, category);
		}		
	}
	
	/**
	 * Computes the average score of all CS 2420 students in the collection.
	 * 
	 * @return the average score, or 0 if there are no students in the collection
	 */
	public double computeClassAverage() {
		if(studentList.size() == 0) {
			return 0.0;
		}
		double totalScore = 0.0;
		for (CS2420StudentGeneric<Type> student: studentList) {
			totalScore += student.computeFinalScore();
		}
		return totalScore/studentList.size();
	}
	
	/**
	 * Creates a list of contact information for all CS 2420 students in the collection.
	 *
	 * @return the duplicate-free list of contact information, in any order
	 */
	public ArrayList<Type> getContactList() {
		ArrayList<Type> allInfo = new ArrayList<Type>();
		for (CS2420StudentGeneric<Type> student: studentList) {
			if (!(allInfo.contains(student.getContactInfo()))) {
				allInfo.add(student.getContactInfo());
			}
		}
		return allInfo;
	}
}
