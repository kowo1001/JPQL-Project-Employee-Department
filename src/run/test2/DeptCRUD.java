package run.test2;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import model.domain.Dept;
import model.domain.Employee;
import util.PublicCommon;

public class DeptCRUD {
	private static DeptCRUD instance = new DeptCRUD();
	
	public static DeptCRUD getInstance() {
		return instance;
	}
	
//	@Test
	public void CreateDepartment() {
		System.out.println("-----부서 추가-----");
		EntityManager em = PublicCommon.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			Dept department2 = Dept.builder().dname("R&D").location("Houston").employee(new ArrayList<Employee>()).build();
			em.persist(department2);
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		}finally {
			em.close();
		}
	}
	
//	@Test
	public void FindAllDepartments() {
		System.out.println("-----모든 부서 검색-----");
		EntityManager em = PublicCommon.getEntityManager();
		try {
			String jpql ="SELECT d FROM Dept d";
			List<Dept> list = em.createQuery(jpql).getResultList();
			list.forEach(v -> System.out.println(v));	
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			em.close();
		}
	}
	
//	@Test
	public void FindDeptBySalary() {
		System.out.println("-----급여가 3000 이상이고 10000이하인 사원들의 모든 부서 정보 검색-----");
		EntityManager em = PublicCommon.getEntityManager();
		try {
			String jpql = "SELECT d from Dept d JOIN d.employee e WHERE e.salary between 3000 and 10000 ";
			List<Dept> list = em.createQuery(jpql).getResultList();
			list.forEach(v -> System.out.println(v));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
	}
	
//	@Test
	public void FindDeptByAvgSalaries() {
		System.out.println("-----사원급여가 전체 사원급여의 평균보다 작은 부서 검색-----");
		EntityManager em = PublicCommon.getEntityManager();
		try {
			String jpql = "SELECT d from Dept d JOIN d.employee e WHERE e.salary < ALL (select avg(e2.salary) from Employee e2)";
			List<Dept> list = em.createQuery(jpql).getResultList();
			list.forEach(v -> System.out.println(v));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
	}

//	@Test
	public void UpdateDeptNameByDnum() {
		System.out.println("-----부서번호로 부서명 수정 -----");
		EntityManager em = PublicCommon.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			Dept department1 = em.find(Dept.class, 28);
			System.out.println("수정 전 : "+ department1);
			department1.setDname("Personnel");
			System.out.println("수정 후 : "+ department1);
			tx.commit();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			em.close();
		}
	}
	
//	@Test
	public void UpdateDeptLocationByDnum() {
		System.out.println("-----부서번호로 부서 위치 수정-----");
		EntityManager em = PublicCommon.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		//join e.dept d
		try {
			tx.begin();
			Dept department1 = em.find(Dept.class, 10);
			System.out.println("수정 전 : "+ department1);
			department1.setLocation("Los Angeles");
			System.out.println("수정 후 : "+ department1);
			tx.commit();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			em.close();
		}
	}
	
//	@Test
	public void DeleteDepartmentByDnum() {
		System.out.println("----- 부서번호로 특정 부서 삭제 -----");
		EntityManager em = PublicCommon.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			Query query = em.createQuery("DELETE Dept d WHERE d.dnum= :dnum");
			query.setParameter("dnum", 73);
			int rowsDeleted = query.executeUpdate();
			System.out.println("삭제된 부서 수 : " + rowsDeleted);
			tx.commit();
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			em.close();
		}
	}
}
