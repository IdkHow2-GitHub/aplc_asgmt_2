import java.sql.Date;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.text.View;

public class APLC_A2P2 {

	//this is the srudent object for when we add/remove/update/view a student's profile
	public static class Student {
		private String f_name = null;
		private String l_name = null;
		private LocalDate dob = null;
		private char sex = 'x';
		private double prev_gpa = 0;
		private int cur_semester = 0;
		private String program = null;
		private int course_count = 0;

		public Student(String f_name, String l_name, LocalDate dob, char sex, double prev_gpa, int cur_semester, String program, int course_count) {
			this.f_name = f_name;
			this.l_name = l_name;
			this.dob = dob;
			this.sex = sex;
			this.prev_gpa = prev_gpa;
			this.cur_semester = cur_semester;
			this.program = program;
			this.course_count = course_count;
		}

		public String getFirstName() { return f_name; }
		public void setFirstName(String f_name) { this.f_name = f_name.substring(0, 1).toUpperCase() + f_name.substring(1); }
		public String getLastName() { return l_name; }
		public void setLastName(String l_name) { this.l_name = l_name.substring(0, 1).toUpperCase() + l_name.substring(1); }
		public LocalDate getDateOfBirth() { return dob; }
		public void setDateOfBirth(LocalDate dob) { this.dob = dob; }
		public char getSex() { return sex; }
		public void setSex(char sex) { this.sex = sex; }
		public double getPreviousGPA() { return prev_gpa; }
		public void setPreviousGPA(double prev_gpa) { this.prev_gpa = prev_gpa; }
		public int getCurrentSemester() { return cur_semester; }
		public void setCurrentSemester(int cur_semester) { this.cur_semester = cur_semester; }
		public String getProgram() { return program; }
		public void setProgram(String program) { this.program = program.substring(0, 1).toUpperCase() + program.substring(1); }
		public int getCourseCount() { return course_count; }
		public void setCourseCount(int course_count) { this.course_count = course_count; }
	}
	//
	//
	//
	//
	//
	public static ArrayList<Student> student_list = new ArrayList<Student>();
	static boolean found_student = false;

	//mock data
	public static void createStudentList() {
		//dob is in yyyy/mm/dd
		student_list.add(new Student("Benjamin", "Simms", LocalDate.of(2005, 12, 20), 'm', 3.75, 1, "Software Development", 5));

		student_list.add(new Student("Wasim", "Borhot", LocalDate.of(2005, 12, 20), 'm', 3.75, 1, "Software Development", 5));
		student_list.add(new Student("Michael", "Fortier", LocalDate.of(1993, 10, 5), 'm', 3.8, 1, "Software Development", 5));
		student_list.add(new Student("Kiru", "Hankebo", LocalDate.of(2004, 10, 20), 'm', 3.75, 1, "Software Development", 5));
		student_list.add(new Student("Andrew", "Dung Le", LocalDate.of(2005, 12, 20), 'm', 3.75, 1, "Software Development", 5));

		student_list.add(new Student("Eunice", "Olugbemiro", LocalDate.of(2007, 1, 31), 'f', 3.9, 1, "Natural Science", 4));

		student_list.add(new Student("Michael", "Myers", LocalDate.of(1957, 10, 19), 'm', 3.05, 3, "Culinary Arts", 3));
		student_list.add(new Student("Jason", "Voorhees", LocalDate.of(1946, 6, 13), 'm', 0, 0, "Kinesiology", 6));
		student_list.add(new Student("Freddy", "Kruger", LocalDate.of(1942, 5, 13), 'm', 1.36, 13, "Sleep Medicine", 4));
		student_list.add(new Student("Ghost", "Face", LocalDate.of(1978, 4, 7), 'm', 3.75, 1, "Psychology", 4));
	}

	public static void main(String[] args) {
		createStudentList();
		adminView();
	}

	public static void adminView() {
		//this will be for accessing the student list
		System.out.println("=".repeat(30));
		System.out.println("WELCOME TO ADMIN VIEW");
		System.out.println("=".repeat(30));
		System.out.println(
				"SELECT ADMIN OPTION\n"
						+ "1: view specific student\n"
						+ "2: view all students\n"
						+ "3: add student\n"
						+ "4: modify student\n"
						+ "5: remove student\n"
						+ "9: log out");
		System.out.println("=".repeat(30));
		System.out.print("Enter number: ");
		String user_input = getUserInput();
		switch (user_input) {
		case "1":
		case "4":
		case "5":
			searchStudent(user_input);
			break;
		case "2":
			viewAllStudents();
			break;
		case "3":
			addStudent();
			break;
		case "9":
			System.out.println("\n[!] Logging out, goodbye!\n");
			System.exit(0);
			break;
		default:
			System.err.println("\nERR: Invalid option, try again!\n");
			adminView();
			break;
		}
	}
	public static String getUserInput() {
		Scanner scanner = new Scanner(System.in);
		return scanner.nextLine();
	}

	//this one will be used to find and remove students
	public static void searchStudent(String user_input) {
		System.out.print("Enter student first name: ");
		String selected_f_name = getUserInput().toUpperCase();
		System.out.print("Enter student last name: ");
		String selected_l_name = getUserInput().toUpperCase();

		int student_id = 0;

		//no students? no list
		if (student_list.size() <= 0) {
			System.out.println("\n[!] There are no students! Returning to Admin View.\n");
			adminView();
		}
		else {
			//find the student first
			//always display the student no matter the option
			//gotta get all their info before removing them to make sure its the right one
			System.out.println("=".repeat(30));
			for (int i = 0; i < student_list.size(); i++) {
				if (student_list.get(i).getFirstName().toUpperCase().equals(selected_f_name) && student_list.get(i).getLastName().toUpperCase().equals(selected_l_name)) {
					found_student = true;
					student_id = i;
					System.out.println("1: FIRST NAME: " + student_list.get(i).getFirstName());
					System.out.println("2: LAST NAME: " + student_list.get(i).getLastName());
					System.out.println("3: DOB: " + student_list.get(i).getDateOfBirth());
					System.out.println("4: SEX: " + student_list.get(i).getSex());
					System.out.println("5: PREV GPA: " + student_list.get(i).getPreviousGPA());
					System.out.println("6: SEMESTER: " + student_list.get(i).getCurrentSemester());
					System.out.println("7: PROGRAM: " + student_list.get(i).getProgram());
					System.out.println("8: COURSE COUNT: " + student_list.get(i).getCourseCount());
				}
			}
			//if it cant find the student, then error
			if (!found_student) {
				System.err.println("\nERR: Student \"" + selected_f_name + " " + selected_l_name + "\" does not exist! Returing to Admin View\n");
				adminView();
			}
			//for finding a student
			else if (user_input.equals("1")) {
				System.out.println("=".repeat(30));
				System.out.print("> Search for another student? (y/n): ");
				String next_option = getUserInput();
				found_student = false;
				if (next_option.toUpperCase().equals("Y")) {
					searchStudent(user_input);
				}
				else if (next_option.toUpperCase().equals("N")) {
					adminView();
				}
				else {
					System.err.println("\nERR: Invalid input! Returning to Admin View.\n");
					adminView();
				}
			}
			else if (user_input.equals("4")) {
				System.out.print("> Modify this student? (y/n): ");
				String next_option = getUserInput();
				found_student = false;
				if (next_option.toUpperCase().equals("Y")) {
					modifyStudent(student_id);
				}
				else if (next_option.toUpperCase().equals("N")) {
					adminView();
				}
				else {
					System.err.println("\nERR: Invalid input! Returning to Admin View.\n");
					adminView();
				}
			}
			else if (user_input.equals("5")) removeStudent(student_id);
		}
	}
	public static void viewAllStudents() {
		if (student_list.size() <= 0) {
			System.out.println("\n[!] There are no students! Returning to Admin View.\n");
			adminView();
		}
		else {
			System.out.println("=".repeat(30));
			for (int i = 0; i < student_list.size(); i++) {
				System.out.println("STUDENT: " + student_list.get(i).getLastName() + ", " + student_list.get(i).getFirstName());
			}
			System.out.println("=".repeat(30));
			System.out.println("\n[!] All students shown. Returning to Admin View.\n");
			adminView();
		}
	}
	public static void addStudent() {
		//naming
		System.out.print("> Enter new student first name: ");
		String new_f_name = getUserInput();
		new_f_name = new_f_name.substring(0, 1).toUpperCase() + new_f_name.substring(1);
		System.out.print("> Enter new student last name: ");
		String new_l_name = getUserInput();
		new_l_name = new_l_name.substring(0, 1).toUpperCase() + new_l_name.substring(1);

		boolean duplicate_student = false;

		for (int i = 0; i < student_list.size(); i++) {
			if (student_list.get(i).getFirstName().toUpperCase().equals(new_f_name.toUpperCase()) && 
					student_list.get(i).getLastName().toUpperCase().equals(new_l_name.toUpperCase())) {
				duplicate_student = true;
			}
		}
		if (!duplicate_student) {
			LocalDate new_dob = null;

			//facts
			System.out.print("> Enter new student birth year (yyyy): ");
			String new_dob_year = getUserInput();
			System.out.print("> Enter new student birth month (mm): ");
			String new_dob_month = getUserInput();
			System.out.print("> Enter new student birth day (dd): ");
			String new_dob_day = getUserInput();

			try {
				new_dob = LocalDate.of(Integer.parseInt(new_dob_year), Integer.parseInt(new_dob_month), Integer.parseInt(new_dob_day));
				System.out.println("[!] New student date of birth is: " + new_dob_year + "-" + new_dob_month + "-" + new_dob_day);
			} catch (DateTimeException e) {
				System.err.println("\nERR: Invalid date of birth. Returning to Admin View.\n");
				adminView();
			}

			System.out.print("> Enter new student sex: ");
			String new_sex = getUserInput();

			//school
			System.out.print("> Enter new student previous GPA: ");
			String new_prev_gpa = getUserInput();
			System.out.print("> Enter new student current semester: ");
			String new_cur_semester = getUserInput();
			new_cur_semester = new_cur_semester.substring(0, 1).toUpperCase() + new_cur_semester.substring(1);

			System.out.print("> Enter new student program: ");
			String new_program = getUserInput();
			new_program = new_program.substring(0, 1).toUpperCase() + new_program.substring(1);

			System.out.print("> Enter new student course count: ");
			String new_course_count = getUserInput();

			Student new_student = new Student(new_f_name, new_l_name, new_dob, new_sex.charAt(0), Double.parseDouble(new_prev_gpa), Integer.parseInt(new_cur_semester), new_program, Integer.parseInt(new_course_count));

			System.out.println("1: FIRST NAME: " + new_student.getFirstName());
			System.out.println("2: LAST NAME: " + new_student.getLastName());
			System.out.println("3: DOB: " + new_student.getDateOfBirth());
			System.out.println("4: SEX: " + new_student.getSex());
			System.out.println("5: PREV GPA: " + new_student.getPreviousGPA());
			System.out.println("6: SEMESTER: " + new_student.getCurrentSemester());
			System.out.println("7: PROGRAM: " + new_student.getProgram());
			System.out.println("8: COURSE COUNT: " + new_student.getCourseCount());

			student_list.add(new_student);

			System.out.println("\n[!] New student added! Returning to Admin View.\n");
			adminView();
		}
		else {
			System.err.println("\nERR: " + new_f_name + " " + new_l_name +" already exists! Returning to Admin View.\n");
			adminView();
		}
	}
	public static void modifyStudent(int student_id) {
		System.out.print("> Select unit to modify (enter \'x\' to cancel): ");
		String next_option = getUserInput();
		if (next_option.toUpperCase().equals("X")) {
			
		}
		else if (next_option.equals("1") ||
				next_option.equals("2") ||
				next_option.equals("3") ||
				next_option.equals("4") ||
				next_option.equals("5") ||
				next_option.equals("6") ||
				next_option.equals("7") ||
				next_option.equals("8")) {
			switch (next_option) {
			case "1":
				System.out.print("> Enter new data: ");
				student_list.get(student_id).setFirstName(getUserInput());
				break;
			case "2":
				System.out.print("> Enter new data: ");
				student_list.get(student_id).setLastName(getUserInput());
				break;
			case "3":
				System.out.print("> Enter new birth year (yyyy): ");
				String new_dob_year = getUserInput();
				System.out.print("> Enter new birth month (mm): ");
				String new_dob_month = getUserInput();
				System.out.print("> Enter new birth day (dd): ");
				String new_dob_day = getUserInput();
				try {
					LocalDate new_dob = LocalDate.of(Integer.parseInt(new_dob_year), Integer.parseInt(new_dob_month), Integer.parseInt(new_dob_day));
					System.out.println("New student date of birth is: " + new_dob_year + "-" + new_dob_month + "-" + new_dob_day);
					student_list.get(student_id).setDateOfBirth(new_dob);
				} catch (DateTimeException e) {
					System.err.println("\nERR: Invalid date of birth. Returning to Admin View.\n");
					adminView();
				}
				break;
			case "4":
				System.out.print("> Enter new data: ");
				student_list.get(student_id).setSex(getUserInput().toLowerCase().charAt(0));
				break;
			case "5":
				System.out.print("> Enter new data: ");
				student_list.get(student_id).setPreviousGPA(Double.parseDouble(getUserInput()));
				break;
			case "6":
				System.out.print("> Enter new data: ");
				student_list.get(student_id).setCurrentSemester(Integer.parseInt(getUserInput()));
				break;
			case "7":
				System.out.print("> Enter new data: ");
				student_list.get(student_id).setProgram(getUserInput());
				break;
			case "8":
				System.out.print("> Enter new data: ");
				student_list.get(student_id).setCourseCount(Integer.parseInt(getUserInput()));
				break;
			case "x":
				adminView();
				break;
			default:
				found_student = false;
				System.err.println("\nERR: Invalid input! Returning to Admin View.\n");
				adminView();
				break;
			}
			System.out.println("\n[!] Data modified! Returning to Admin View.\n");
			adminView();
		}
		else {
			found_student = false;
			System.err.println("\nERR: Invalid input! Returning to Admin View.\n");
			adminView();
		}
	}
	public static void removeStudent(int student_id) {
		System.out.print("> Remove this student? (y/n): ");
		String next_option = getUserInput();
		if (next_option.toUpperCase().equals("Y")) {
			found_student = false;
			student_list.remove(student_id);

			System.out.println("\n[!] Student removed! Returning to Admin View.\n");
			adminView();
		}
		else if (next_option.toUpperCase().equals("N")) {
			found_student = false;
			adminView();
		}
		else {
			found_student = false;
			System.err.println("\nERR: Invalid input! Returning to Admin View.\n");
			adminView();
		}
	}
}
