package Rest.Rest;
import org.springframework.web.bind.annotation.*;
import Rest.DAO.*;
import java.util.List;

@RestController
public class DashboardEController {
    private final DashboardEDAO dashboardEDAO;

    public DashboardEController(DashboardEDAO dashboardEDAO) {
        this.dashboardEDAO = dashboardEDAO;
    }

    @GetMapping("/dashboardE/registeredPets")
    public int registeredPets() {
        return dashboardEDAO.registeredPets();
    }

    @GetMapping("/dashboardE/countryOriginCount")
    public long countryOriginCount() {
        return dashboardEDAO.countryOriginCount();
    }

    @GetMapping("/dashboardE/doneExaminatons")
    public long doneExaminatons() {
        return dashboardEDAO.doneExaminatons();
    }

    @GetMapping("/dashboardE/lastExaminatons")
    public String lastExaminatons() {
        return dashboardEDAO.lastExaminatons();
    }

    @GetMapping("/dashboardE/upcomingEvents")
    public long upcomingEvents() {
        return dashboardEDAO.upcomingEvents();
    }
}
