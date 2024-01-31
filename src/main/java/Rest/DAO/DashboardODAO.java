package Rest.DAO;

public interface DashboardODAO {
	int registeredPets(Long idOwner);
	long countryOriginCount(Long idOwner);
	long doneExaminatons(Long idOwner);
	String lastExaminatons(Long idOwner);
	long upcomingEvents(Long idOwner);
}
