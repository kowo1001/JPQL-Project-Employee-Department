package runt.test;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.junit.jupiter.api.Test;

import model.domain.Employee;
import util.PublicCommon;

public class RunningTest {

//	@Test
//	public void CreateTest() {
//		EntityManager em = PublicCommon.getEntityManager();
//		
//		try {
//			Dept d1 = Dept.builder().dnum(30).dname("메모리사업부").location("서초").build();
//			Employee e1 = Employee.builder().eid(7000).ename("김주임").salary(1550).dept(d1).build();
//			Employee e2 = Employee.builder().eid(7001).ename("이주임").salary(1550).dept(d1).build();
//			
//			em.persist(d1);
//			em.persist(e1);
//			em.persist(e2);
//			
//		}catch(Exception e) {
//			e.printStackTrace();
//		}finally {
//			em.close();
//		}
//	}
	// 사번으로 사원 월급(salary)정보 수정
	// 사번으로 사원 위치 정보 수정
	
	@Test
	public void UpdateTest() {
		EntityManager em = PublicCommon.getEntityManager();
		
		try {
			em.getTransaction().begin();
			String jpql = "update Employee e set e.salary = e.salary + :increment" +"where e.dept= :dept";
			Query query = em.createQuery(jpql);
			query.setParameter("increment", 500);
			query.setParameter("dept","IT");
			int rowsUpdated = query.executeUpdate();
			System.out.println("entities Updated : "+ rowsUpdated);
			
			em.getTransaction().commit();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			em.close();
		}
	}
	
	
	//NamedQuery Test
//	@Test
//	public void runNamedQueryTest() {
//		EntityManager em = PublicCommon.getEntityManager();
//		
//		try{
//			
//
//			//"Employee.findByEmpno" - 사용하기 위해서는 Employee에 선언시 매핑했던 이름과 사번값
//			Employee e = (Employee)em.createNamedQuery("Employee.findByEmpno").setParameter("eid",7369).getSingleResult();
//			System.out.println(e);
//			
//			//?
//			e = (Employee)em.createNamedQuery("Employee.findByEmpnoAndEname").setParameter("eid",7369).setParameter("ename", "SMITH").getSingleResult();
//			System.out.println(e);
//			
//		}catch(Exception e) {
//			e.printStackTrace();
//		}finally {
//			em.close();
//		}
//	}
	
	//? select 실행 - EntityManager
	/* 개별 메소드로 개발했다 가정
	 * 개중에 max() 로직만 실행 요청
	 * 자바 실행환경상에서는 이 모든 메소드 보유한 byte code는 메모리에 로딩해 둔 상태
	 * 실행 요청하지 않은 sql문장을 보유한 메소드들에 혹여 sql문장 오류가 있다하더라도 오류가 안남
	 * 왜? 호출하지 않으면 실행을 안.하.기. 때. 문!
	 * 
	 */
	
	
	/* 이미 존재하는 영문 이름들을 소문자로 변환해서 검색해야 하는 상황이라 가정
	 * 한 소스상에선 select 즉 데이터가 database에 있다는 가정
	 * ? table 수작업으로 생성
	 * 	 create table employee as select empno, ename, sal, deptno from emp;
	 * 	 select * from employee;
	 * 	 
	 */
//	@Test
//	public void runJPQLTest() {
//		EntityManager em = PublicCommon.getEntityManager();
//		
//		try {
//			//이름 소문자로 변경해서 검색
//			String jpql = "select LOWER(e.ename) from Employee e";
//			List<String> list=em.createQuery(jpql).getResultList();
//			list.forEach(v->System.out.println(v));
//			
//			System.out.println("--- max() ---");
//			//최고 급여 검색
//			jpql = "select max(e.salary) from Employee e";
//			int maxSal = (int)em.createQuery(jpql).getSingleResult();
//			System.out.println(maxSal);
//			
//			/* select e from Employee e
//			 * select e from Employee e where e.salary between 800 and 3000
//			 * Employee 객체들 생성 의미
//			 * 
//			 * sql 문장 실행 후에는 Employee 객체들이 다수 저장된 list객체
//			 * 
//			 */
//			
//			System.out.println("--- between ~ and ---");
//			//between ~ and
//			jpql = "select e from Employee e where e.salary between 800 and 3000";
//			List<Employee> list2 = em.createQuery(jpql).getResultList();
//			list2.forEach(v->System.out.println(v));
//			
//			System.out.println("--- M으로 시작하는 모든 사원의 모든 정보 검색 및 출력 ---");
//			//? like 'M%' - M으로 시작하는 모든 사원의 모든 정보 검색 및 출력
//			jpql = "select e from Employee e where e.ename like 'M%'";
//			list2 = em.createQuery(jpql).getResultList();
//			list2.forEach(v->System.out.println(v));
//			
//			System.out.println("--- 사원명을 오름차순으로 정렬해서 사원명만 검색 및 출력하기 ---");
//			//? 사원명을 오름차순으로 정렬해서 사원명만 검색 및 출력하기
//			jpql = "select e.ename from Employee e order by e.ename asc";
//			list = em.createQuery(jpql).getResultList();
//			list.forEach(v->System.out.println(v));
//			
//			
//		}catch(Exception e) {
//			e.printStackTrace();
//		}finally {
//			em.close();
//		}
//		
//	}
}
