//JPQLTest & named query test용 Entity
package model.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
//@ToString(exclude="dept")
@Entity
public class Employee {

	 @Id
	 @GeneratedValue(strategy=GenerationType.AUTO)
	 @Column(name="empno")
	 private int eid;
	 
	 @Column(name="ename",length=20)
	 private String ename;
	 
	 @Column(name="sal")
	 private int salary;

	// @PrimaryKeyJoinColumn(name="deptno")
	 
	 @ManyToOne(cascade=CascadeType.MERGE)
	 @JoinColumn(name="deptno")
	 private Dept dept;

	 public static Employee create(int eid, String ename, int salary, Dept dept) {
		 Employee e = new Employee();
		 e.setEid(eid);
		 e.setEname(ename);
		 e.setSalary(salary);
		 e.setDept(dept);
		 return e;
	 }
	 
	 @Override
	 public String toString() {
	    return "사원 Employee [사원 아이디 =" + eid + ", 사원명 =" + ename + ", 급여 =" + salary + ", 부서 " + dept + "]";
	 }
	}
