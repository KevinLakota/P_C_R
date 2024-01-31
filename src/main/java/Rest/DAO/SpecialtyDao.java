package Rest.DAO;

import java.util.List;

public interface SpecialtyDao {
	Specialty save(Specialty specialty);
	Specialty getByOwnerId(Long id);
	List<Specialty> getAll();
	void delete(Long idEmp);
}
