package Rest.DAO;

public class User {
	private Long idUser;
	private String username;
	private String passwd;
	private byte[] salt;
	private boolean admin;
	private Long idOwner;
	private Long idEmpl;
	public User() {

	}
	public User(Long idUser, String username, String passwd, byte[] salt, boolean admin, Long idOwner, Long idEmpl) {
		this.idUser = idUser;
		this.username = username;
		this.passwd = passwd;
		this.salt = salt;
		this.admin = admin;
		this.idOwner = idOwner;
		this.idEmpl = idEmpl;
	}

	public User(String username, String passwd, byte[] salt, boolean admin, Long idOwner, Long idEmpl) {
		this.username = username;
		this.passwd = passwd;
		this.salt = salt;
		this.admin = admin;
		this.idOwner = idOwner;
		this.idEmpl = idEmpl;
	}

	public User(String username, String passwd, byte[] salt) {
		this.username = username;
		this.passwd = passwd;
		this.salt = salt;
	}

	public User(String passwd, byte[] salt) {
		this.passwd = passwd;
		this.salt = salt;
	}

	public Long getIdUser() {
		return idUser;
	}
	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public byte[] getSalt() {
		return salt;
	}
	public void setSalt(byte[] salt) {
		this.salt = salt;
	}
	public boolean isAdmin() {
		return admin;
	}
	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
	public Long getIdOwner() {
		return idOwner;
	}
	public void setIdOwner(Long idOwner) {
		this.idOwner = idOwner;
	}
	public Long getIdEmpl() {
		return idEmpl;
	}
	public void setIdEmpl(Long idEmpl) {
		this.idEmpl = idEmpl;
	}
}