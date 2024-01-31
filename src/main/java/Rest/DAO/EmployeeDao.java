package Rest.DAO;

import java.util.List;

public interface EmployeeDao {
	Employee save(Employee employee);
	Employee getByEmpId(Long id);
	List<Employee> getAll();
	void delete(Long idEmp);
	void savePhoto(String base64, Long id);
}
