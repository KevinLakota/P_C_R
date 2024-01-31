package Rest.DAO;

import java.time.LocalDate;
import java.util.List;

public interface ExaminationDao {
	Examination save(Examination examination);

	List<Examination> getByDate(LocalDate date);

	void delete(Long idEmp);

	List<Examination> getAll();

	List<LocalDate> getAllDate(Long idEmpl, Long idOwner);

	Long getUnusedId();

	List<Examination> getByIdEmp(long id);
}
