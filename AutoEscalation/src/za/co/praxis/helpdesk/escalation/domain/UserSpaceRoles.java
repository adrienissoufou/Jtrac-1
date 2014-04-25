package za.co.praxis.helpdesk.escalation.domain;
// Generated Oct 21, 2013 9:18:53 AM by Hibernate Tools 3.4.0.CR1

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * UserSpaceRoles generated by hbm2java
 */
@Entity
@Table(name = "user_space_roles")//, schema = "dbo", catalog = "jtrac_test_db")
public class UserSpaceRoles implements java.io.Serializable {

	private BigDecimal id;
	private Users users;
	private Spaces spaces;
	private String roleKey;

	public UserSpaceRoles() {
	}

	public UserSpaceRoles(BigDecimal id, String roleKey) {
		this.id = id;
		this.roleKey = roleKey;
	}

	public UserSpaceRoles(BigDecimal id, Users users, Spaces spaces,
			String roleKey) {
		this.id = id;
		this.users = users;
		this.spaces = spaces;
		this.roleKey = roleKey;
	}

	@Id
	@Column(name = "id", unique = true, nullable = false, scale = 0)
	public BigDecimal getId() {
		return this.id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	public Users getUsers() {
		return this.users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "space_id")
	public Spaces getSpaces() {
		return this.spaces;
	}

	public void setSpaces(Spaces spaces) {
		this.spaces = spaces;
	}

	@Column(name = "role_key", nullable = false)
	public String getRoleKey() {
		return this.roleKey;
	}

	public void setRoleKey(String roleKey) {
		this.roleKey = roleKey;
	}

}
