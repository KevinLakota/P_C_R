package Rest.Rest;
import Rest.DAO.Examination;
import Rest.DAO.ExaminationDao;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
public class ExaminationController {

    private final ExaminationDao examinationDao;

    public ExaminationController(ExaminationDao examinationDao) {
        this.examinationDao = examinationDao;
    }

    @PostMapping("/examination/save")
    public Examination save(Examination examination) {
        return examinationDao.save(examination);
    }

    @GetMapping("/examination/getByDate/{date}")
    public List<Examination> getByDate(@PathVariable String date) {
        return examinationDao.getByDate(LocalDate.parse(date));
    }

    @DeleteMapping("/examination/delete/{idEmp}")
    public void delete(@PathVariable Long idEmp) {
        examinationDao.delete(idEmp);
    }

    @GetMapping("/examination/getAll")
    public List<Examination> getAll() {
        return examinationDao.getAll();
    }

    @GetMapping("/examination/getAllDate/{idEmpl}/{idOwner}")
    public List<LocalDate> getAllDate(@PathVariable Long idEmpl, @PathVariable Long idOwner) {
        return examinationDao.getAllDate(idEmpl, idOwner);
    }

    @GetMapping("/examination/getUnusedId")
    public Long getUnusedId() {
        return examinationDao.getUnusedId();
    }

    @GetMapping("/examination/getByIdEmp/{id}")
    public List<Examination> getByIdEmp(@PathVariable Long id) {
        return examinationDao.getByIdEmp(id);
    }
}
