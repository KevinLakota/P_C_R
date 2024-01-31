package Rest.Rest;
import org.springframework.web.bind.annotation.*;
import Rest.DAO.*;
import java.util.List;

@RestController
public class EmployeeController {
    private final EmployeeDao employeeDao;

    public EmployeeController(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    @PostMapping("/employee/save")
    public Employee save(Employee employee) {
        return employeeDao.save(employee);
    }

    @GetMapping("/employee/getByEmpId/{id}")
    public Employee getByEmpId(@PathVariable Long id) {
        return employeeDao.getByEmpId(id);
    }

    @GetMapping("/employee/getAll")
    public List<Employee> getAll() {
        return employeeDao.getAll();
    }

    @DeleteMapping("/employee/delete/{idEmp}")
    public void delete(@PathVariable Long idEmp) {
        employeeDao.delete(idEmp);
    }

    @PostMapping("/employee/savePhoto")
    public void savePhoto(String base64, Long id) {
        employeeDao.savePhoto(base64, id);
    }
}
