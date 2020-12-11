package run.test2;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import model.domain.Dept;
import model.domain.Employee;
import util.PublicCommon;

public class EmployeeCRUD {
	private static EmployeeCRUD instance = new EmployeeCRUD();
	
	public static EmployeeCRUD getInstance() {
		return instance;
	}
	
//	@Test
	public void CreateEmployee() {
		System.out.println("-----사원 정보 생성 및 저장-----");
		EntityManager em = PublicCommon.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			Employee employee1 = Employee.builder().ename("ABEL").salary(30000).dept(em.find(Dept.class, 30)).build();
			em.persist(employee1);
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		}finally {
			em.close();
		}
	}
	
//	@Test
	public void FindAllEmployees() {
		System.out.println("-----모든 사원의 정보 검색-----");
		EntityManager em = PublicCommon.getEntityManager();
		try {
			String jpql ="SELECT e FROM Employee e";
			List<Employee> list = em.createQuery(jpql).getResultList();
			list.forEach(v -> System.out.println(v));
					
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			em.close();
		}
	}
	
//	@Test
	public void FindEmployeeByDnum() {
		System.out.println("-----부서번호가 10번인 사원들의 모든 정보 검색-----");
		EntityManager em = PublicCommon.getEntityManager();
		try {
			String jpql = "SELECT e from Employee e JOIN e.dept d WHERE d.dnum=10";
			
			List<Employee> list = em.createQuery(jpql).getResultList();
			list.forEach(v -> System.out.println(v));
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
	}
	
//	@Test
	public void FindMaxSalaryByDnum() {
		System.out.println("-----부서번호가 10번인 사원들 중 가장 높은 급여 검색-----");
		EntityManager em = PublicCommon.getEntityManager();
		try {
			String jpql = "SELECT MAX(e.salary) FROM Employee e JOIN e.dept d WHERE d.dnum=10";
			List<Integer> list = em.createQuery(jpql).getResultList();
			list.forEach(v -> System.out.println(v));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
	}

//	@Test
	public void UpdateEmployeesSalaries() {
		System.out.println("-----사원명을 통해 사원급여 인상 -----");
		EntityManager em = PublicCommon.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			String jpql = "UPDATE Employee e SET e.salary = e.salary + :increment WHERE e.ename= :ename";
			Query query = em.createQuery(jpql);
			query.setParameter("increment", 10);
			query.setParameter("ename","MARTIN");
			int rowsUpdated = query.executeUpdate();
			System.out.println("월급 인상된 사원 수 : "+ rowsUpdated + "명");
			tx.commit();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			em.close();
		}
	}
	
//	@Test
	public void UpdateAllEmployeesSalaries() {
		System.out.println("-----모든 사원 급여 인상(특정 사원 성과급으로 급여 인상률 증가)-----");
		EntityManager em = PublicCommon.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			String jpql = "UPDATE Employee e set e.salary = CASE WHEN e.ename = 'TURNER' THEN (e.salary*1.20) WHEN e.ename = 'SMITH' THEN (e.salary*1.15) ELSE (e.salary*1.10) END";
			Query query = em.createQuery(jpql);
			int rowsUpdated = query.executeUpdate();
			System.out.println("올해 급여가 인상된 사원수 : "+ rowsUpdated +"명");
			tx.commit();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			em.close();
		}
	}
	
//	@Test
	public void DeleteEmployee() {
		System.out.println("----- 사원번호로 특정 사원 삭제 -----");
		EntityManager em = PublicCommon.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			String jpql = "DELETE Employee e WHERE e.eid= :eid";
			Query query = em.createQuery(jpql);
			query.setParameter("eid",22);
			int rowsDeleted = query.executeUpdate();
			System.out.println("명단에서 제외된 사원 수 : " + rowsDeleted +"명");
			tx.commit();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			em.close();
		}
	}
}
