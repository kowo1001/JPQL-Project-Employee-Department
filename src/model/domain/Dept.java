//JPQLTest & named query test용 Entity
package model.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
//@ToString(exclude="dept")
@Builder
@Entity
public class Dept {
   @Id
   @GeneratedValue(strategy=GenerationType.AUTO)
   @Column(name="deptno")
   private int dnum;
   
   @Column(name="dname",length=20)
   private String dname;
   
   @Column(name="loc",length=20)
   private String location;

   @OneToMany(mappedBy="dept")
	private List<Employee> employee;
   
	@Override
	public String toString() {
		return "Dept [부서번호=" + dnum + ", 부서명=" + dname + ", 위치=" + location + "]";
	}
}
