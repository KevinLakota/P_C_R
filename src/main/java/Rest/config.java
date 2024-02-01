package Rest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import Rest.DAO.*;

import javax.sql.DataSource;


@Configuration
public class config {
    private final JdbcTemplate jdbcTemplate;
    public config(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Bean
    public UserDao getUserDao() {
        return new PostgresUserDao(jdbcTemplate);
    }

    @Bean
    public SpecialtyDao getSpecialtyDao() {
        return new PostgresSpecialtyDao(jdbcTemplate);
    }

    @Bean
    public TreatmentDao getTreatmentDao() {
        return new PostgresTreatmentDao(jdbcTemplate);
    }

    @Bean
    public AnimalDao getAnimalDao() {
        return new PostgresAnimalDao(jdbcTemplate);
    }

    @Bean
    public EmployeeDao getEmployeeDao() {
        return new PostgresEmployeeDao(jdbcTemplate);
    }

    @Bean
    public ExaminationDao getExaminationDao() {
        return new PostgresExaminationDao(jdbcTemplate);
    }

    @Bean
    public TreatmentAnimalDao getTreatmentAnimalDao() {
        return new PostgresTreatmentAnimalDao(jdbcTemplate);
    }

    @Bean
    public SpeciesDao getSpeciesDao() {
        return new PostgresSpeciesDao(jdbcTemplate);
    }

    @Bean
    public OwnerDao getOwnerDao() {
        return new PostgresOwnerDao(jdbcTemplate);
    }

    @Bean
    public DashboardEDAO getDashboardEDAO() {
        return new PostgresDashboardEDAO(jdbcTemplate);
    }

    @Bean
    public DashboardODAO getDashboardTDAO() {
        return new PostgresDashboardODAO(jdbcTemplate);
    }
}
