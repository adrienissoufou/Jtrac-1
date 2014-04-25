package za.co.praxis.helpdesk.escalation.domain;
// Generated Oct 21, 2013 9:18:53 AM by Hibernate Tools 3.4.0.CR1

import java.math.BigDecimal;
import java.sql.Clob;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

/**
 * Metadata generated by hbm2java
 */
@Entity
@Table(name = "metadata")//, schema = "dbo", catalog = "jtrac_test_db")
public class Metadata implements java.io.Serializable {

	private BigDecimal id;
	private int version;
	private Metadata metadata;
	private Integer type;
	private String name;
	private Clob description;
	private Clob xmlString;
	private Set<Spaces> spaceses = new HashSet<Spaces>(0);
	private Set<Metadata> metadatas = new HashSet<Metadata>(0);
	private Set<Users> userses = new HashSet<Users>(0);

	public Metadata() {
	}

	public Metadata(BigDecimal id) {
		this.id = id;
	}

	public Metadata(BigDecimal id, Metadata metadata, Integer type,
			String name, Clob description, Clob xmlString,
			Set<Spaces> spaceses, Set<Metadata> metadatas, Set<Users> userses) {
		this.id = id;
		this.metadata = metadata;
		this.type = type;
		this.name = name;
		this.description = description;
		this.xmlString = xmlString;
		this.spaceses = spaceses;
		this.metadatas = metadatas;
		this.userses = userses;
	}

	@Id
	@Column(name = "id", unique = true, nullable = false, scale = 0)
	public BigDecimal getId() {
		return this.id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	@Version
	@Column(name = "version", nullable = false)
	public int getVersion() {
		return this.version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parent_id")
	public Metadata getMetadata() {
		return this.metadata;
	}

	public void setMetadata(Metadata metadata) {
		this.metadata = metadata;
	}

	@Column(name = "type")
	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	@Column(name = "name", length = 50)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "description")
	public Clob getDescription() {
		return this.description;
	}

	public void setDescription(Clob description) {
		this.description = description;
	}

	@Column(name = "xml_string")
	public Clob getXmlString() {
		return this.xmlString;
	}

	public void setXmlString(Clob xmlString) {
		this.xmlString = xmlString;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "metadata")
	public Set<Spaces> getSpaceses() {
		return this.spaceses;
	}

	public void setSpaceses(Set<Spaces> spaceses) {
		this.spaceses = spaceses;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "metadata")
	public Set<Metadata> getMetadatas() {
		return this.metadatas;
	}

	public void setMetadatas(Set<Metadata> metadatas) {
		this.metadatas = metadatas;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "metadata")
	public Set<Users> getUserses() {
		return this.userses;
	}

	public void setUserses(Set<Users> userses) {
		this.userses = userses;
	}

}
