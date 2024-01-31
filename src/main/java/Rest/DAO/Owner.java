package Rest.DAO;

public class Owner {
	private Long idOwner;
	private String firstName;
	private String surname;
	private String phoneNumber;
	private String email;

	public Owner(Long idOwner, String firstName, String surname, String phoneNumber, String email) {
		super();
		this.idOwner = idOwner;
		this.firstName = firstName;
		this.surname = surname;
		this.phoneNumber = phoneNumber;
		this.email = email;
	}

	public Owner( String firstName, String surname, String phoneNumber, String email) {
		super();
		this.firstName = firstName;
		this.surname = surname;
		this.phoneNumber = phoneNumber;
		this.email = email;
	}

	public Owner( String firstName, String surname, String phoneNumber) {
		super();
		this.firstName = firstName;
		this.surname = surname;
		this.phoneNumber = phoneNumber;
	}

	public Long getIdOwner() {
		return idOwner;
	}

	public void setIdOwner(Long idOwner) {
		this.idOwner = idOwner;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
