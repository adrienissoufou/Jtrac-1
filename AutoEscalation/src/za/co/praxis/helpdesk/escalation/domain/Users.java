package za.co.praxis.helpdesk.escalation.domain;
// Generated Oct 21, 2013 9:18:53 AM by Hibernate Tools 3.4.0.CR1

import java.math.BigDecimal;
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
import javax.persistence.UniqueConstraint;

/**
 * Users generated by hbm2java
 */
@Entity
@Table(name = "users"/*, schema = "dbo", catalog = "jtrac_test_db"*/, uniqueConstraints = @UniqueConstraint(columnNames = "login_name"))
public class Users implements java.io.Serializable {

	private BigDecimal id;
	private Users users;
	private Metadata metadata;
	private Integer type;
	private String loginName;
	private String name;
	private String cellphone;
	private Byte useCellPhoneNumber;
	private String password;
	private String email;
	private String locale;
	private Byte locked;
	private Set<ItemUsers> itemUserses = new HashSet<ItemUsers>(0);
	private Set<ZSpaceEmail> ZSpaceEmails = new HashSet<ZSpaceEmail>(0);
	private Set<History> historiesForLoggedBy = new HashSet<History>(0);
	private Set<SpaceUserLevel> spaceUserLevels = new HashSet<SpaceUserLevel>(0);
	private Set<Items> itemsesForAssignedTo = new HashSet<Items>(0);
	private Set<ZEscalationLogs> ZEscalationLogsesForAssignedTo = new HashSet<ZEscalationLogs>(
			0);
	private Set<History> historiesForAssignedTo = new HashSet<History>(0);
	private Set<SpaceUserLevel> spaceUserLevels_1 = new HashSet<SpaceUserLevel>(
			0);
	private Set<UserSpaceRoles> userSpaceRoleses = new HashSet<UserSpaceRoles>(
			0);
	private Set<Items> itemsesForLoggedBy = new HashSet<Items>(0);
	private Set<ZEscalationLogs> ZEscalationLogsesForEscalatedTo = new HashSet<ZEscalationLogs>(
			0);
	private Set<Users> userses = new HashSet<Users>(0);

	public Users() {
	}

	public Users(BigDecimal id, String loginName) {
		this.id = id;
		this.loginName = loginName;
	}

	public Users(BigDecimal id, Users users, Metadata metadata, Integer type,
			String loginName, String name, String cellphone,
			Byte useCellPhoneNumber, String password, String email,
			String locale, Byte locked, Set<ItemUsers> itemUserses,
			Set<ZSpaceEmail> ZSpaceEmails, Set<History> historiesForLoggedBy,
			Set<SpaceUserLevel> spaceUserLevels,
			Set<Items> itemsesForAssignedTo,
			Set<ZEscalationLogs> ZEscalationLogsesForAssignedTo,
			Set<History> historiesForAssignedTo,
			Set<SpaceUserLevel> spaceUserLevels_1,
			Set<UserSpaceRoles> userSpaceRoleses,
			Set<Items> itemsesForLoggedBy,
			Set<ZEscalationLogs> ZEscalationLogsesForEscalatedTo,
			Set<Users> userses) {
		this.id = id;
		this.users = users;
		this.metadata = metadata;
		this.type = type;
		this.loginName = loginName;
		this.name = name;
		this.cellphone = cellphone;
		this.useCellPhoneNumber = useCellPhoneNumber;
		this.password = password;
		this.email = email;
		this.locale = locale;
		this.locked = locked;
		this.itemUserses = itemUserses;
		this.ZSpaceEmails = ZSpaceEmails;
		this.historiesForLoggedBy = historiesForLoggedBy;
		this.spaceUserLevels = spaceUserLevels;
		this.itemsesForAssignedTo = itemsesForAssignedTo;
		this.ZEscalationLogsesForAssignedTo = ZEscalationLogsesForAssignedTo;
		this.historiesForAssignedTo = historiesForAssignedTo;
		this.spaceUserLevels_1 = spaceUserLevels_1;
		this.userSpaceRoleses = userSpaceRoleses;
		this.itemsesForLoggedBy = itemsesForLoggedBy;
		this.ZEscalationLogsesForEscalatedTo = ZEscalationLogsesForEscalatedTo;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parent")
	public Users getUsers() {
		return this.users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "metadata_id")
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

	@Column(name = "login_name", unique = true, nullable = false, length = 50)
	public String getLoginName() {
		return this.loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	@Column(name = "name", length = 50)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "cellphone", length = 10)
	public String getCellphone() {
		return this.cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	@Column(name = "useCellPhoneNumber")
	public Byte getUseCellPhoneNumber() {
		return this.useCellPhoneNumber;
	}

	public void setUseCellPhoneNumber(Byte useCellPhoneNumber) {
		this.useCellPhoneNumber = useCellPhoneNumber;
	}

	@Column(name = "password")
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "email", length = 50)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "locale", length = 20)
	public String getLocale() {
		return this.locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	@Column(name = "locked")
	public Byte getLocked() {
		return this.locked;
	}

	public void setLocked(Byte locked) {
		this.locked = locked;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "users")
	public Set<ItemUsers> getItemUserses() {
		return this.itemUserses;
	}

	public void setItemUserses(Set<ItemUsers> itemUserses) {
		this.itemUserses = itemUserses;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "users")
	public Set<ZSpaceEmail> getZSpaceEmails() {
		return this.ZSpaceEmails;
	}

	public void setZSpaceEmails(Set<ZSpaceEmail> ZSpaceEmails) {
		this.ZSpaceEmails = ZSpaceEmails;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "usersByLoggedBy")
	public Set<History> getHistoriesForLoggedBy() {
		return this.historiesForLoggedBy;
	}

	public void setHistoriesForLoggedBy(Set<History> historiesForLoggedBy) {
		this.historiesForLoggedBy = historiesForLoggedBy;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "users")
	public Set<SpaceUserLevel> getSpaceUserLevels() {
		return this.spaceUserLevels;
	}

	public void setSpaceUserLevels(Set<SpaceUserLevel> spaceUserLevels) {
		this.spaceUserLevels = spaceUserLevels;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "usersByAssignedTo")
	public Set<Items> getItemsesForAssignedTo() {
		return this.itemsesForAssignedTo;
	}

	public void setItemsesForAssignedTo(Set<Items> itemsesForAssignedTo) {
		this.itemsesForAssignedTo = itemsesForAssignedTo;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "usersByAssignedTo")
	public Set<ZEscalationLogs> getZEscalationLogsesForAssignedTo() {
		return this.ZEscalationLogsesForAssignedTo;
	}

	public void setZEscalationLogsesForAssignedTo(
			Set<ZEscalationLogs> ZEscalationLogsesForAssignedTo) {
		this.ZEscalationLogsesForAssignedTo = ZEscalationLogsesForAssignedTo;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "usersByAssignedTo")
	public Set<History> getHistoriesForAssignedTo() {
		return this.historiesForAssignedTo;
	}

	public void setHistoriesForAssignedTo(Set<History> historiesForAssignedTo) {
		this.historiesForAssignedTo = historiesForAssignedTo;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "users")
	public Set<SpaceUserLevel> getSpaceUserLevels_1() {
		return this.spaceUserLevels_1;
	}

	public void setSpaceUserLevels_1(Set<SpaceUserLevel> spaceUserLevels_1) {
		this.spaceUserLevels_1 = spaceUserLevels_1;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "users")
	public Set<UserSpaceRoles> getUserSpaceRoleses() {
		return this.userSpaceRoleses;
	}

	public void setUserSpaceRoleses(Set<UserSpaceRoles> userSpaceRoleses) {
		this.userSpaceRoleses = userSpaceRoleses;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "usersByLoggedBy")
	public Set<Items> getItemsesForLoggedBy() {
		return this.itemsesForLoggedBy;
	}

	public void setItemsesForLoggedBy(Set<Items> itemsesForLoggedBy) {
		this.itemsesForLoggedBy = itemsesForLoggedBy;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "usersByEscalatedTo")
	public Set<ZEscalationLogs> getZEscalationLogsesForEscalatedTo() {
		return this.ZEscalationLogsesForEscalatedTo;
	}

	public void setZEscalationLogsesForEscalatedTo(
			Set<ZEscalationLogs> ZEscalationLogsesForEscalatedTo) {
		this.ZEscalationLogsesForEscalatedTo = ZEscalationLogsesForEscalatedTo;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "users")
	public Set<Users> getUserses() {
		return this.userses;
	}

	public void setUserses(Set<Users> userses) {
		this.userses = userses;
	}

}
