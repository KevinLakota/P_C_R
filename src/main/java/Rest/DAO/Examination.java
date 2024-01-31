package Rest.DAO;

import java.time.LocalDateTime;

public class Examination {
	private Long idExam;
	private LocalDateTime date;
	private String comment;
	private String photo;
	private Long empl;
	private Long animal;

	public Examination(Long idExam, LocalDateTime date, String comment,
			String photo, Long empl, Long animal) {
		super();
		this.idExam = idExam;
		this.date = date;
		this.comment = comment;
		this.photo = photo;
		this.empl = empl;
		this.animal = animal;
	}

	public Examination(Long idExam, LocalDateTime date, String comment,
			Long empl, Long animal) {
		super();
		this.idExam = idExam;
		this.date = date;
		this.comment = comment;
		this.empl = empl;
		this.animal = animal;
	}

	public Examination(LocalDateTime date, String comment,
			Long empl, Long animal) {
		super();
		this.date = date;
		this.comment = comment;
		this.empl = empl;
		this.animal = animal;
	}

	public Long getIdExam() {
		return idExam;
	}

	public void setIdExam(Long idExam) {
		this.idExam = idExam;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public Long getEmpl() {
		return empl;
	}

	public void setEmpl(Long empl) {
		this.empl = empl;
	}

	public Long getAnimal() {
		return animal;
	}

	public void setAnimal(Long animal) {
		this.animal = animal;
	}
}
