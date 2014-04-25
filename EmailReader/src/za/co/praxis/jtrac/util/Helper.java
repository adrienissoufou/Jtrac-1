package za.co.praxis.jtrac.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.StringUtils;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import za.co.praxis.jtrac.domain.Attachments;
import za.co.praxis.jtrac.domain.Config;
import za.co.praxis.jtrac.domain.History;
import za.co.praxis.jtrac.domain.Items;
import za.co.praxis.jtrac.domain.SpaceSequence;
import za.co.praxis.jtrac.domain.Spaces;
import za.co.praxis.jtrac.domain.State;
import za.co.praxis.jtrac.domain.Users;
import za.co.praxis.jtrac.domain.ZCategories;
import za.co.praxis.jtrac.domain.ZSeverities;
import za.co.praxis.jtrac.domain.ZSpaceEmail;
import za.co.praxis.jtrac.domain.ZSpaceSeverityPeriod;

@SuppressWarnings("deprecation")
public class Helper {

	private static final String String = null;
	private Connection con = null;
	private static SessionFactory sessionFactory;
	private SQLQuery query = null;
	private static Configuration configuration = new Configuration();
	private static Transaction transaction = null;
	private static Session session = null;
	String attachFiles = "";
	String fileName = "";
	public static String EMAIL_NOTIFICATION_SUBJECT = "Praxis helpdesk ticket notification";
	public static Logger logger;
	private static FileHandler fh; 
	public static String LOG_FILE = "C:/Log";
	public static String ATTACHMENT_FILE = "C:/attachments_zip";
	
	public Helper() throws InstantiationException, IllegalAccessException,
			ClassNotFoundException {

		/*
		 * try { this.con = getConnection(); } catch (SQLException e) { throw
		 * new RuntimeException(e); }
		 */

	}

	static {
		// configuration.configure("/za/co/praxis/jtrac/domain/hibernate.cfg.xml");
		logger = Logger.getLogger(Helper.class.getName());
		initializeLogger();
		
		//logger.info("Initializing Session...");
		
		configuration.configure("hibernate.cfg.xml");
		sessionFactory = configuration.configure().buildSessionFactory();
		session = sessionFactory.openSession();
		/*session = sessionFactory.getCurrentSession();
		
		session = sessionFactory.getCurrentSession();*/
		getCurrentSession().beginTransaction();
		
		//logger.info("Session Initialized");
	}

	
	
	private static void initializeLogger() {
		
		try {
			
			String fileName = new SimpleDateFormat("dd-MM-yyyy").format(new Date()) + ".txt";
			File f = new File(fileName);
			
			 
			if(!f.exists())
				f.createNewFile();
			
			fh = new FileHandler(System.getProperty("user.dir") + fileName, true);
			logger.addHandler(fh);  
			SimpleFormatter formatter = new SimpleFormatter();  
	        fh.setFormatter(formatter); 
	        logger.info("Logger initialized");
			
			
		} catch (SecurityException e) {
			System.out.println(e.getMessage());
			//logger.info(e.getMessage());
			//throw new RuntimeException(e);
		} catch (IOException e) {
			System.out.println(e.getMessage());
			//logger.info(e.getMessage());
			//throw new RuntimeException(e);
		}
        //logger.setLevel(Level.ALL);  
          
          
	}
	
	/*public static void establishConnection() {
		configuration.configure("hibernate.cfg.xml");
		sessionFactory = configuration.configure().buildSessionFactory();
		session = sessionFactory.openSession();
		getCurrentSession().beginTransaction();
	}*/

	public static void establishConnection() {
		/*sessionFactory = configuration.configure("hibernate.cfg.xml").buildSessionFactory();
		session = sessionFactory.openSession();
		session = sessionFactory.getCurrentSession();
		session.beginTransaction();*/
		session = sessionFactory.openSession();
		getCurrentSession().beginTransaction();
	}
	
	public static Session getSession() throws HibernateException {
		return sessionFactory.openSession();
	}

	public static Session getCurrentSession() throws HibernateException {
		return sessionFactory.getCurrentSession();
	}

	public static void cleanUp() {
		sessionFactory.close();
	}
	
	@SuppressWarnings("unchecked")
	public List<Spaces> getSpace(Spaces space) {

		List<Spaces> spaces = new ArrayList<Spaces>();
		query = getCurrentSession().createSQLQuery(
				"select * from spaces where id = ?");
		query.setParameter(0, space.getId());
		query.addEntity(Spaces.class);
		spaces = query.list();

		return spaces;
	}

	@SuppressWarnings("unchecked")
	public List<Users> getUsersByEmail(String email) {

		//logger.info("Getting User by email...");
		query = getCurrentSession().createSQLQuery(
				"select * from users where email = ?");
		query.setParameter(0, email);
		query.addEntity(Users.class);
		List<Users> users = new ArrayList<Users>();
		users = query.list();

		return users;
	}
	
	@SuppressWarnings("unchecked")
	public List<ZCategories> getSupportCategory() {
		query = getCurrentSession().createSQLQuery("select * from z_categories where description = ?");
		query.setParameter(0, "Support");
		
		query.addEntity(ZCategories.class);
		List<ZCategories> categories = new ArrayList<ZCategories>();
		categories = query.list();
		
		return categories;
	}
	
	@SuppressWarnings("unchecked")
	public List<ZCategories> getAllCategories() {
		query = getCurrentSession().createSQLQuery("select * from z_categories");
				
		query.addEntity(ZCategories.class);
		List<ZCategories> categories = new ArrayList<ZCategories>();
		categories = query.list();
		
		return categories;
	}

	@SuppressWarnings("unchecked")
	public List<SpaceSequence> getNextSequenceNumberForSpace(Spaces space) {

		//logger.info("Getting next sequence number for space...");
		query = getCurrentSession().createSQLQuery(
				"select * from space_sequence where id = ?");
		query.setParameter(0, space.getId());
		query.addEntity(SpaceSequence.class);
		List<SpaceSequence> sequences = new ArrayList<SpaceSequence>();
		sequences = query.list();

		return sequences;
	}

	@SuppressWarnings("unchecked")
	public List<ZSeverities> getMediumSeverity() {
		query = getCurrentSession().createSQLQuery(
				"select * from z_severities where description = 'MEDIUM'");
		query.addEntity(ZSeverities.class);
		List<ZSeverities> severities = new ArrayList<ZSeverities>();
		severities = query.list();
		return severities;
	}

	@SuppressWarnings("unchecked")
	public List<ZSpaceSeverityPeriod> getMediumSpaceSeverityPeriodBySpace(
			Spaces space) {

		List<ZSeverities> severities = new ArrayList<ZSeverities>();
		severities = getMediumSeverity();
		query = getCurrentSession()
				.createSQLQuery(
						"select * from z_space_severity_period where space_id = ? and severity_id = ?");
		query.setParameter(0, space.getId());
		query.setParameter(1, severities.get(0).getId());
		query.addEntity(ZSpaceSeverityPeriod.class);
		List<ZSpaceSeverityPeriod> spaceSeverityPeriods = new ArrayList<ZSpaceSeverityPeriod>();
		spaceSeverityPeriods = query.list();

		return spaceSeverityPeriods;
	}

	public void getUserToAssignToBySpace(Spaces space) {

		List<Users> users = new ArrayList<Users>();
		// query = getCurrentSession().createSQLQuery("select * from ")

	}

	public void updateSpaceSequenceNumber(Spaces space) {
		query = getCurrentSession()
				.createSQLQuery(
						"update space_sequence set next_seq_num = next_seq_num + 1 where id = ?");
		query.setParameter(0, space.getId());
		query.executeUpdate();
	}

	public void insertItem(Items item, Attachments attachment, MimeBodyPart attachmentPart) throws IOException {

		Items saveditem = (Items) getCurrentSession().merge(item);
		updateSpaceSequenceNumber(item.getSpaces());

		History history = new History();
		history.setItems(saveditem);
		history.setTimeStamp(new Date());
		history.setUsersByAssignedTo(item.getUsersByAssignedTo());
		history.setUsersByLoggedBy(item.getUsersByLoggedBy());
		history.setDetail(item.getDetail());
		history.setSummary(item.getSummary());
		history.setStatus(State.OPEN);
		history.setZSpaceSeverityPeriod(item.getZSpaceSeverityPeriod());
		history.setVersion(0);

		if (attachment != null) {
			
			
			/*Attachments savedAttachment = (Attachments) getCurrentSession()
					.merge(attachment);*/
			
			String spacePrefix = history.getItems().getSpaces().getPrefixCode();
			String itemSequenceNum = history.getItems().getSequenceNum().toString();
			
			Attachments savedAttachment = new Attachments();
			savedAttachment = createNewZip(spacePrefix + "-" +  itemSequenceNum);
			//savedAttachment.setFilePrefix(savedAttachment.getId());
			//getCurrentSession().merge(savedAttachment);

			history.setAttachments(savedAttachment);
		} else {
			history.setAttachments(null);
		}

		getCurrentSession().save(history);
		getCurrentSession().getTransaction().commit();
		getCurrentSession().close();
		
		establishConnection();
		
		//establishConnection();
	}

	private  void zipAttachment(String target) throws MessagingException,
			IOException {
		String saveDirectory = getConfigurationByParam("attachments").get(0).getValue();
		//String attachmentFileName = fileName + "#" + part.getFileName();
		String zipDirectory = getConfigurationByParam("attachments_zip").get(0).getValue();
		
		File root = new File(zipDirectory);
		File[] list = root.listFiles();
		String zipFile = saveDirectory + File.separator + target + ".zip";
		ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipFile));
		for(int i=0; i < list.length; i++){
			ZipEntry zen = new ZipEntry(list[i].getPath());
			out.putNextEntry(zen);
			int len;
			byte[] buf = new byte[1024];
			FileInputStream in = new FileInputStream(list[i]);
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
                
            }
            in.close();	 
            
            
		}
		out.closeEntry();
		out.close();
			
		for(int i=0; i < list.length; i++){
			
			list[i].delete();
			
		}
		
	}

	public Attachments createNewZip(String itemId) throws IOException {
		
		String saveDirectory = getConfigurationByParam("attachments").get(0).getValue();
		//String attachmentFileName = fileName + "#" + part.getFileName();
		String zipDirectory = getConfigurationByParam("attachments_zip").get(0).getValue();
		
		File root = new File(zipDirectory);
		File[] list = root.listFiles();
		
		Attachments attachment = new Attachments();
		attachment.setFileName(itemId + ".zip");
		
		Attachments savedAttachment = (Attachments) getCurrentSession()
		.merge(attachment);
		
		savedAttachment.setFilePrefix(savedAttachment.getId());
		
		Attachments finalAttachment = new Attachments();
		finalAttachment = (Attachments)getCurrentSession().merge(savedAttachment);
		
		ZipUtil.createZip(zipDirectory, saveDirectory + File.separator + finalAttachment.getFilePrefix() + "_" + itemId + ".zip");
		
		for(int i=0; i < list.length; i++){
			list[i].delete();
		}
		
		
		return finalAttachment;
		
	}
	
	@SuppressWarnings("unchecked")
	public List<ZSpaceEmail> getAllSpaceEmails() {
		List<ZSpaceEmail> spaceEmails = new ArrayList<ZSpaceEmail>();
		query = getCurrentSession().createSQLQuery("select * from z_space_email");
		query.addEntity(ZSpaceEmail.class);
		spaceEmails = query.list();
		
		return spaceEmails;
	}
	
	@SuppressWarnings("unchecked")
	public List<Config> getAllConfigurations() {
		List<Config> configs = new ArrayList<Config>();
		query = getCurrentSession().createSQLQuery("select * from config");
		query.addEntity(Config.class);
		configs = query.list();
		return configs;
	}
	@SuppressWarnings("unchecked")
	public List<Config> getConfigurationByParam(String param) {
		List<Config> configs = new ArrayList<Config>();
		query = getCurrentSession().createSQLQuery("select * from config where param = ?");
		query.setParameter(0, param);
		query.addEntity(Config.class);
		configs = query.list();
		return configs;
	}

	public String decode(String s) {
		return StringUtils.newStringUtf8(Base64.decodeBase64(s));
	}

	public String encode(String s) {
		return Base64.encodeBase64String(StringUtils.getBytesUtf8(s));
	}

	public byte[] serialize(Object obj) throws IOException {
		ByteArrayOutputStream b = new ByteArrayOutputStream();
		ObjectOutputStream o = new ObjectOutputStream(b);
		o.writeObject(obj);
		return b.toByteArray();
	}

	/*
	 * public Connection getConnection() throws SQLException,
	 * InstantiationException, IllegalAccessException, ClassNotFoundException {
	 * 
	 * Connection conn = null;
	 * 
	 * // Properties of SQL server Properties connectionProps = new
	 * Properties(); try {
	 * Class.forName("net.sourceforge.jtds.jdbc.Driver").newInstance();
	 * 
	 * connectionProps.load(Helper.class.getClassLoader()
	 * .getResourceAsStream("db.properties")); } catch (IOException ex) { throw
	 * new RuntimeException(ex); }
	 * 
	 * connectionProps .put("username",
	 * connectionProps.getProperty("username")); connectionProps
	 * .put("password", connectionProps.getProperty("password")); //
	 * connectionProps.put("url", // connectionProps.getProperty(
	 * "jdbc:jtds:sqlserver://sql2008vm:1433/jtrac_test_db"));
	 * 
	 * String url = "jdbc:jtds:sqlserver://sql2008vm:1433/jtrac_test_db;user=" +
	 * connectionProps.getProperty("username") + ";password=" +
	 * connectionProps.getProperty("password");
	 * 
	 * conn = DriverManager.getConnection(url);
	 * 
	 * System.out.println("Connected to database"); return conn; }
	 * 
	 * public void saveItem(Object object) {
	 * 
	 * try {
	 * 
	 * Items item = new Items();
	 * 
	 * item = (Items) object; PreparedStatement ps = null; String sql = null;
	 * 
	 * ByteArrayOutputStream bos = new ByteArrayOutputStream();
	 * ObjectOutputStream oos = new ObjectOutputStream(bos);
	 * 
	 * oos.writeObject(object); oos.flush(); oos.close(); bos.close();
	 * 
	 * byte[] data = bos.toByteArray();
	 * 
	 * sql =
	 * "insert into items(version,space_id,sequence_num,type,due_date, date_added, logged_by, assigned_to, summary, detail, originator, originator_contact, status, category) "
	 * + " values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)"; ps = con.prepareStatement(sql);
	 * ps.setInt(1, item.getVersion()); ps.setObject(2, item.getSpaces()); //
	 * ps.setInt(3, item.getSequenceNum()); ps.setInt(4, item.getType()); //
	 * ps.setDate(5, item.getDueDate());
	 * 
	 * ps.setObject(1, data); ps.executeUpdate();
	 * 
	 * } catch (Exception e) { throw new RuntimeException(e); }
	 * 
	 * }
	 * 
	 * public Spaces findNISSpace() throws SQLException {
	 * 
	 * List<Object> objects = new ArrayList<Object>(); Spaces space = new
	 * Spaces();
	 * 
	 * Statement stmt = null; String query = "select * " +
	 * "from spaces where prefix_code = 'DEV'";
	 * 
	 * try { stmt = con.createStatement(); ResultSet rs =
	 * stmt.executeQuery(query); while (rs.next()) {
	 * 
	 * // space.setId(rs.getLong(1)); space.setVersion(rs.getInt("version"));
	 * space.setMetadata(null); space.setType(rs.getInt("type"));
	 * space.setPrefixCode(rs.getString("prefix_code"));
	 * space.setName(rs.getString("name")); //
	 * space.setDescription(rs.getString("description"));
	 * space.setGuestAllowed(rs.getByte("guest_allowed"));
	 * 
	 * System.out.println(rs.getString(5));
	 * 
	 * } } catch (SQLException e) { throw new RuntimeException(e); } finally {
	 * if (stmt != null) { stmt.close(); } }
	 * 
	 * return space;
	 * 
	 * }
	 * 
	 * public ZSpaceSeverityPeriod findSpaceSeverityPeriodBySpace(Spaces space)
	 * throws SQLException {
	 * 
	 * ZSpaceSeverityPeriod spaceSeverityPeriod = new ZSpaceSeverityPeriod();
	 * Statement stmt = null; String query = "select * " +
	 * "from z_space_severity_period where space_id = " + space.getId();
	 * 
	 * try { stmt = con.createStatement(); ResultSet rs =
	 * stmt.executeQuery(query); while (rs.next()) {
	 * 
	 * spaceSeverityPeriod.setId(rs.getInt(1));
	 * spaceSeverityPeriod.setZSeverities(null);
	 * spaceSeverityPeriod.setSpaces(space);
	 * spaceSeverityPeriod.setPeriod(rs.getInt(4));
	 * System.out.println(rs.getString(2));
	 * 
	 * } } catch (SQLException e) { throw new RuntimeException(e); } finally {
	 * if (stmt != null) { stmt.close(); } }
	 * 
	 * return spaceSeverityPeriod; }
	 * 
	 * public Users findUserByEmailAddress(String email) throws SQLException {
	 * 
	 * Users user = new Users();
	 * 
	 * PreparedStatement stmt = null; String query = "select * " +
	 * "from users where email = ?";
	 * 
	 * try { stmt = con.prepareStatement(query); stmt.setString(1, email);
	 * ResultSet rs = stmt.executeQuery(); while (rs.next()) {
	 * 
	 * // user.setId(rs.getLong(1)); user.setType(rs.getInt(2));
	 * user.setLoginName(rs.getString(4)); user.setName(rs.getString(5));
	 * 
	 * user.setCellphone(rs.getString(6));
	 * user.setUseCellPhoneNumber(rs.getByte(7)); user.setEmail(email);
	 * user.setLocale(rs.getString(10)); user.setLocked(rs.getByte(11));
	 * 
	 * System.out.println(rs.getString(2));
	 * 
	 * } } catch (SQLException e) { throw new RuntimeException(e); } finally {
	 * if (stmt != null) { stmt.close(); } }
	 * 
	 * return user; }
	 */
}