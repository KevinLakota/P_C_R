package Rest.Rest;
import org.springframework.web.bind.annotation.*;
import Rest.DAO.*;
import java.util.List;

@RestController
public class DashboardOController {

    private final DashboardODAO dashboardODAO;

    public DashboardOController(DashboardODAO dashboardODAO) {
        this.dashboardODAO = dashboardODAO;
    }

    @GetMapping("/dashboardO/registeredPets/{idOwner}")
    public int registeredPets(@PathVariable Long idOwner) {
        return dashboardODAO.registeredPets(idOwner);
    }

    @GetMapping("/dashboardO/countryOriginCount/{idOwner}")
    public long countryOriginCount(@PathVariable Long idOwner) {
        return dashboardODAO.countryOriginCount(idOwner);
    }

    @GetMapping("/dashboardO/doneExaminatons/{idOwner}")
    public long doneExaminatons(@PathVariable Long idOwner) {
        return dashboardODAO.doneExaminatons(idOwner);
    }

    @GetMapping("/dashboardO/lastExaminatons/{idOwner}")
    public String lastExaminatons(@PathVariable Long idOwner) {
        return dashboardODAO.lastExaminatons(idOwner);
    }

    @GetMapping("/dashboardO/upcomingEvents/{idOwner}")
    public long upcomingEvents(@PathVariable Long idOwner) {
        return dashboardODAO.upcomingEvents(idOwner);
    }

}
