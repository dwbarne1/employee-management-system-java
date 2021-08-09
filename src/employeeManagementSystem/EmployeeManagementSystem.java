package employeeManagementSystem;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class EmployeeManagementSystem extends Employee {
	
	public EmployeeManagementSystem(String name, String department) {
		super(name, department);
	}

	// Initialize File I/O and Scanner, and declare class variables
	private static FileWriter fw = null;
	private static File file = new File("resources/employee.csv");
	
	private static Scanner scan = new Scanner(System.in);
	
	private static ArrayList<Employee> employeeList = new ArrayList<>();
	private static int i = 0;
	private static boolean programRunning = true;

	public static void main(String[] args) {
		
//		File file = new File("resources/employee.txt");
		
		// Read employee.csv, put the values of each line into new objects in employeeList, and sort employeeList by department
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			String line = "";
			while ((line = br.readLine()) != null) {
				String[] temp = line.split(",");
				String name = temp[0];
				String department = temp[1];
				employeeList.add(new Employee(name, department));
			}
			br.close();
			employeeList.sort(Comparator.comparing(e -> e.getDepartment()));
		} catch(Exception e) {
			
		}
		
		// Show welcome screen with options
		System.out.println("Welcome to the Employee Management System! Please type one of the options below.\n");
		System.out.println("1. List Employee Information");
		System.out.println("2. Add a New Employee");
		System.out.println("3. Change Employee Information");
		System.out.println("4. Delete an Employee");
		System.out.println("5. Exit");
		
		// Keep running the program with various options until the user types 5 to exit
		while(programRunning) {
			int choice = scan.nextInt();
			System.out.println();
			try {
				switch(choice) {
				case 1:
					listEmployeeInformation();
					break;
				case 2:
					addNewEmployee();
					break;
				case 3:
					editEmployee();
					break;
				case 4:
					deleteEmployee();
					break;
				case 5:
					saveFileAndExit();
					break;
				default:
					break;
				}
				
				if (programRunning) {
					System.out.println("\nWelcome to the Employee Management System! Please type one of the options below.\n");
					System.out.println("1. List Employee Information");
					System.out.println("2. Add a New Employee");
					System.out.println("3. Change Employee Information");
					System.out.println("4. Delete an Employee");
					System.out.println("5. Exit");
				}
			}
			catch(Exception e) {
				
			}
		}
	}
	
	// List information of each employee (number in list, name, and department)
	public static void listEmployeeInformation() {
		i = 0;
		for(Employee e : employeeList) {
			i++;
			System.out.println(i + ".\tName: " + e.getName() + "\t\t\tDepartment: " + e.getDepartment() + "\n\n");
		};
	}
	
	// Add a new employee by prompting the user to enter a name and department and adding a new object to employeeList
	public static void addNewEmployee() {
		String name = "";
		String department = "";
		
		System.out.print("Please enter the new employee's name: ");
		scan.nextLine();
		name = scan.nextLine();
		
		System.out.print("Please enter the new employee's department: ");
		department = scan.nextLine();
		
		Employee temp = new Employee(name, department);
		System.out.println("Successfully added " + temp.printNameAndDepartment());
		
		employeeList.add(temp);
	}
	
	// Edit an employee by entering the number in list and reentering the name and department
	public static void editEmployee() {
		try {
			i = 0;
			for(Employee e : employeeList) {
				i++;
				System.out.println(i + ".\tName: " + e.getName() + "\t\t\tDepartment: " + e.getDepartment() + "\n\n");
			};
			
			System.out.print("Please enter the number of the employee you would like to edit: ");
			int num = scan.nextInt();
			
			System.out.print("\nPlease enter the new employee name: ");
			scan.nextLine();
			String name = scan.nextLine();
			
			System.out.print("\nPlease enter the new employee department: ");
			String department = scan.nextLine();
			
			Employee temp = new Employee(name, department);
			
			employeeList.set(num-1, temp);
		} catch(Exception e) {
			
		}
	}
	
	// Delete an employee by entering the number in list
	public static void deleteEmployee() {
		i = 0;
		for(Employee e : employeeList) {
			i++;
			System.out.println(i + ".\tName: " + e.getName() + "\t\t\tDepartment: " + e.getDepartment() + "\n\n");
		};
		
		System.out.print("Please enter the number of the employee you would like to delete: ");
		int num = scan.nextInt();
		employeeList.remove(num-1);
		
		System.out.println("Successfully deleted employee.");
	}
	
	// Use FileWriter to write all employee objects to employee.csv, and then close the FileWriter and the program
	public static void saveFileAndExit() throws IOException {
		fw = new FileWriter(file);
		for(int i = 0; i < employeeList.size(); i++) {
			String[] temp = {employeeList.get(i).getName(), employeeList.get(i).getDepartment()};
			List<String> tempList = Arrays.asList(temp);
			String collect = tempList.stream().map(Object::toString).collect(Collectors.joining(","));
			fw.write(collect + "\n");
		}
		fw.close();
		programRunning = false;
	}
		
}