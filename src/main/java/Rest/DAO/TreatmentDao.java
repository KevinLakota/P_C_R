package Rest.DAO;

import java.util.List;
public interface TreatmentDao {
	void save(TreatmentAnimal.Treatment treatment);

	List<TreatmentAnimal.Treatment> getAll();

	void delete(Long idTreatment);
}
