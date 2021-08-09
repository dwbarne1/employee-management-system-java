package employeeManagementSystem;

public class Employee {
	private String name;
	private String department;
	
	public Employee(String name, String department) {
		this.name = name;
		this.department = department;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	
	public String printNameAndDepartment() {
		return this.getName() + " from the " + this.getDepartment() + " Department.";
	}
}
