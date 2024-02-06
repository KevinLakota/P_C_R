package Rest.DAO;

import java.sql.Date;

public class Animal {
	private Long idAnimal;
	private String name;
	private Date birthDate;
	private int weight;
	private boolean castration;
	private String sex;
	private String originCountryCode;
	private String note;
	private String photo;
	private Date dateLastVisit;
	private Date dateNextVisit;
	private Long idOwner;
	private Long idSpecies;
	private String chipNumber;

	public Animal() {

	}

	public Animal(String name, Date birthDate, int weight, boolean castration, String sex,
			String originCountryCode, String note, String photo, Date dateLastVisit, Date dateNextVisit,
			String chipNumber, Long idOwner, Long idSpecies) {
		super();
		this.name = name;
		this.birthDate = birthDate;
		this.weight = weight;
		this.castration = castration;
		this.sex = sex;
		this.originCountryCode = originCountryCode;
		this.note = note;
		this.photo = photo;
		this.dateLastVisit = dateLastVisit;
		this.dateNextVisit = dateNextVisit;
		this.chipNumber = chipNumber;
		this.idOwner = idOwner;
		this.idSpecies = idSpecies;
	}

	public Animal(Long idAnimal, String name, Date birthDate, int weight, boolean castration, String sex,
			String originCountryCode, String note, String photo, Date dateLastVisit, Date dateNextVisit,
			String chipNumber, Long idOwner, Long idSpecies) {
		super();
		this.idAnimal = idAnimal;
		this.name = name;
		this.birthDate = birthDate;
		this.weight = weight;
		this.castration = castration;
		this.sex = sex;
		this.originCountryCode = originCountryCode;
		this.note = note;
		this.photo = photo;
		this.dateLastVisit = dateLastVisit;
		this.dateNextVisit = dateNextVisit;
		this.chipNumber = chipNumber;
		this.idOwner = idOwner;
		this.idSpecies = idSpecies;
	}

	public Animal(Long idAnimal, String name, Date birthDate, int weight, boolean castration, String sex,
			String originCountryCode, String note, Date dateLastVisit, Date dateNextVisit, String chipNumber,
			Long idOwner, Long idSpecies ) {
		super();
		this.idAnimal = idAnimal;
		this.name = name;
		this.birthDate = birthDate;
		this.weight = weight;
		this.castration = castration;
		this.sex = sex;
		this.originCountryCode = originCountryCode;
		this.note = note;
		this.dateLastVisit = dateLastVisit;
		this.dateNextVisit = dateNextVisit;
		this.chipNumber = chipNumber;
		this.idOwner = idOwner;
		this.idSpecies = idSpecies;
	}

	public Animal(Long idAnimal, String name, Date birthDate, int weight, boolean castration, String sex, String chipNumber) {
		super();
		this.idAnimal = idAnimal;
		this.name = name;
		this.birthDate = birthDate;
		this.weight = weight;
		this.castration = castration;
		this.sex = sex;
		this.chipNumber = chipNumber;
	}

	public Long getIdAnimal() {
		return idAnimal;
	}
	public void setIdAnimal(Long idAnimal) {
		this.idAnimal = idAnimal;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public boolean isCastration() {
		return castration;
	}
	public void setCastration(boolean castration) {
		this.castration = castration;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getOriginCountryCode() {
		return originCountryCode;
	}
	public void setOriginCountryCode(String originCountryCode) {
		this.originCountryCode = originCountryCode;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public Date getDateLastVisit() {
		return dateLastVisit;
	}
	public void setDateLastVisit(Date dateLastVisit) {
		this.dateLastVisit = dateLastVisit;
	}
	public Date getDateNextVisit() {
		return dateNextVisit;
	}
	public void setDateNextVisit(Date dateNextVisit) {
		this.dateNextVisit = dateNextVisit;
	}
	public Long getIdOwner() {
		return idOwner;
	}
	public void setIdOwner(Long idOwner) {
		this.idOwner = idOwner;
	}
	public Long getIdSpecies() {
		return idSpecies;
	}
	public void setIdSpecies(Long idSpecies) {
		this.idSpecies = idSpecies;
	}
	public String getChipNumber() {
		return chipNumber;
	}
	public void setChipNumber(String chipNumber) {
		this.chipNumber = chipNumber;
	}
}
