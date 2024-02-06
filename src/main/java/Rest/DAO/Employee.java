package Rest.DAO;

import java.sql.Date;

public class Employee {
	private Long idEmpl;
	private String namePefix;
	private String firstName;
	private String surname;
	private String nameSuffix;
	private Date birthDate;
	private Date graduationDate;
	private String sex;
	private double hourWage;
	private String phoneNumber;
	private String email;
	private int contractNumber;
	private String photo;
	private Long idSpecialty;

	public Employee() {

	}

	public Employee(Long idEmpl, String namePefix, String firstName, String surname, String nameSuffix,
			Date birthDate, Date graduationDate, String sex, double hourWage, String phoneNumber, String email,
			int contractNumber, String photo, Long idSpecialty) {
		super();
		this.idEmpl = idEmpl;
		this.namePefix = namePefix;
		this.firstName = firstName;
		this.surname = surname;
		this.nameSuffix = nameSuffix;
		this.birthDate = birthDate;
		this.graduationDate = graduationDate;
		this.sex = sex;
		this.hourWage = hourWage;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.contractNumber = contractNumber;
		this.photo = photo;
		this.idSpecialty = idSpecialty;
	}

	public Employee(String namePefix, String firstName, String surname, String nameSuffix,
			Date birthDate, Date graduationDate, String sex, double hourWage, String phoneNumber, String email,
			int contractNumber, String photo, Long idSpecialty) {
		super();
		this.namePefix = namePefix;
		this.firstName = firstName;
		this.surname = surname;
		this.nameSuffix = nameSuffix;
		this.birthDate = birthDate;
		this.graduationDate = graduationDate;
		this.sex = sex;
		this.hourWage = hourWage;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.contractNumber = contractNumber;
		this.photo = photo;
		this.idSpecialty = idSpecialty;
	}

	public Employee(Long idEmpl, String namePefix, String firstName, String surname, String nameSuffix,
			Date birthDate, Date graduationDate, String sex, double hourWage, String phoneNumber, String email,
			int contractNumber, Long idSpecialty) {
		super();
		this.idEmpl = idEmpl;
		this.namePefix = namePefix;
		this.firstName = firstName;
		this.surname = surname;
		this.nameSuffix = nameSuffix;
		this.birthDate = birthDate;
		this.graduationDate = graduationDate;
		this.sex = sex;
		this.hourWage = hourWage;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.contractNumber = contractNumber;
		this.idSpecialty = idSpecialty;
	}

	public Long getIdEmpl() {
		return idEmpl;
	}
	public void setIdEmpl(Long idEmpl) {
		this.idEmpl = idEmpl;
	}
	public String getNamePefix() {
		return namePefix;
	}
	public void setNamePefix(String namePefix) {
		this.namePefix = namePefix;
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
	public String getNameSuffix() {
		return nameSuffix;
	}
	public void setNameSuffix(String nameSuffix) {
		this.nameSuffix = nameSuffix;
	}
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	public Date getGraduationDate() {
		return graduationDate;
	}
	public void setGraduationDate(Date graduationDate) {
		this.graduationDate = graduationDate;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public double getHourWage() {
		return hourWage;
	}
	public void setHourWage(double hourWage) {
		this.hourWage = hourWage;
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

	public int getContractNumber() {
		return contractNumber;
	}
	public void setContractNumber(int contractNumber) {
		this.contractNumber = contractNumber;
	}

	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public Long getIdSpecialty() {
		return idSpecialty;
	}
	public void setIdSpecialty(Long idSpecialty) {
		this.idSpecialty = idSpecialty;
	}
}
