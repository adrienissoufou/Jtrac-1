package za.co.praxis.jtrac.domain;
// Generated Oct 18, 2013 2:28:49 PM by Hibernate Tools 3.4.0.CR1

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * ZReports generated by hbm2java
 */
@Entity
@Table(name = "z_reports")//, schema = "dbo", catalog = "jtrac")
public class ZReports implements java.io.Serializable {

	private BigDecimal id;
	private String name;
	private String description;

	public ZReports() {
	}

	public ZReports(BigDecimal id) {
		this.id = id;
	}

	public ZReports(BigDecimal id, String name, String description) {
		this.id = id;
		this.name = name;
		this.description = description;
	}

	@Id
	@Column(name = "id", unique = true, nullable = false, scale = 0)
	public BigDecimal getId() {
		return this.id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	@Column(name = "name", length = 50)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "description", length = 150)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
