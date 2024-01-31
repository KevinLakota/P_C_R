package Rest.DAO;

import java.util.List;

public interface SpeciesDao {
	TreatmentAnimal.Species save(TreatmentAnimal.Species species);
	TreatmentAnimal.Species getById(Long id);
	List<TreatmentAnimal.Species> getAll();
	void delete(Long idEmp);
}
