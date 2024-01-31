package Rest.DAO;

public class Specialty {
	private Long idSpecialty;
	private String specialty;

	public Specialty(String specialty) {
		this.specialty = specialty;
	}

	public Specialty(long id, String specialty) {
		idSpecialty = id;
		this.specialty = specialty;
	}

	public Long getIdSpecialty() {
		return idSpecialty;
	}
	public void setIdSpecialty(Long idSpecialty) {
		this.idSpecialty = idSpecialty;
	}
	public String getSpecialty() {
		return specialty;
	}
	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}
}
