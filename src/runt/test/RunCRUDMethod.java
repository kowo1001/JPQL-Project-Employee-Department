package runt.test;

import run.test2.DeptCRUD;
import run.test2.EmployeeCRUD;

public class RunCRUDMethod {
	public static DeptCRUD deptinstance = DeptCRUD.getInstance();
	public static EmployeeCRUD employeeinstance = EmployeeCRUD.getInstance();
	
	public static void main(String[] args) {
		//Department CRUD
		deptinstance.CreateDepartment();
		deptinstance.FindAllDepartments();
		deptinstance.FindDeptByAvgSalaries();
		deptinstance.FindDeptBySalary();
		deptinstance.UpdateDeptLocationByDnum();
		deptinstance.UpdateDeptNameByDnum();
		deptinstance.DeleteDepartmentByDnum();
		
		//Employee CRUD
		employeeinstance.CreateEmployee();
		employeeinstance.FindAllEmployees();
		employeeinstance.FindEmployeeByDnum();
		employeeinstance.FindMaxSalaryByDnum();
		employeeinstance.UpdateAllEmployeesSalaries();
		employeeinstance.UpdateEmployeesSalaries();
		employeeinstance.DeleteEmployee();
	}
}
