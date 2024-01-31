package Rest.DAO;

import java.util.List;

public interface TreatmentAnimalDao {
	void save(TreatmentAnimal ta);
	void delete(String when);
	List<TreatmentAnimal> getAll();
}
