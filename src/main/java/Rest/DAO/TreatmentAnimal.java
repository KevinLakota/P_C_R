package Rest.DAO;

public class TreatmentAnimal {
	private Long idTreatment;
	private Long idAnimal;
	private String when;

	public TreatmentAnimal() {

	}

	public TreatmentAnimal(Long idTreatment, Long idAnimal, String when) {
		super();
		this.idTreatment = idTreatment;
		this.idAnimal = idAnimal;
		this.when = when;
	}

	public Long getIdTreatment() {
		return idTreatment;
	}
	public void setIdTreatment(Long idTreatment) {
		this.idTreatment = idTreatment;
	}
	public Long getIdAnimal() {
		return idAnimal;
	}
	public void setIdAnimal(Long idAnimal) {
		this.idAnimal = idAnimal;
	}
	public String getWhen() {
		return when;
	}
	public void setWhen(String when) {
		this.when = when;
	}

	public static class Treatment {
		private Long idTreatment;
		private String medicine;
		private String dosage;
		private String activity;

		public Treatment(Long idTreatment, String medicine, String dosage, String activity) {
			super();
			this.idTreatment = idTreatment;
			this.medicine = medicine;
			this.dosage = dosage;
			this.activity = activity;
		}

		public Long getIdTreatment() {
			return idTreatment;
		}

		public void setIdTreatment(Long idTreatment) {
			this.idTreatment = idTreatment;
		}

		public String getMedicine() {
			return medicine;
		}

		public void setMedicine(String medicine) {
			this.medicine = medicine;
		}

		public String getDosage() {
			return dosage;
		}

		public void setDosage(String dosage) {
			this.dosage = dosage;
		}

		public String getActivity() {
			return activity;
		}

		public void setActivity(String activity) {
			this.activity = activity;
		}
	}

	public static class Species {
		private Long idSpecies;
		private String species;

		public Species(Long idSpecies, String species) {
			super();
			this.idSpecies = idSpecies;
			this.species = species;
		}

		public Species(String species) {
			super();
			this.species = species;
		}

		public Long getIdSpecies() {
			return idSpecies;
		}

		public void setIdSpecies(Long idSpecies) {
			this.idSpecies = idSpecies;
		}

		public String getSpecies() {
			return species;
		}

		public void setSpecies(String species) {
			this.species = species;
		}


	}
}
