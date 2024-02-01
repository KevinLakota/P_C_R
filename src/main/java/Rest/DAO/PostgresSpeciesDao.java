package Rest.DAO;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class PostgresSpeciesDao implements SpeciesDao {

    private JdbcTemplate jdbcTemplate;

    public PostgresSpeciesDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public TreatmentAnimal.Species save(TreatmentAnimal.Species species) {

        /*
         * if (attendance == null) throw new
         * NullPointerException("Cannot save null Session"); if (attendance.getSubject()
         * == null || attendance.getSubject().getId() == null) { throw new
         * NullPointerException("Cannot extract id from subject"); } if
         * (attendance.getWhen() == null) { throw new
         * NullPointerException("Cannot save session without date"); }
         */
        if (species.getIdSpecies() == null) { // INSERT

            String query = "INSERT INTO species (species) VALUES (?)";
            GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(con -> {
                PreparedStatement statement = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                statement.setString(1, species.getSpecies());

                return statement;
            }, keyHolder);
            Long id = Long.parseLong(String.valueOf(keyHolder.getKeys().get("id_species")));

            return new TreatmentAnimal.Species(id, species.getSpecies());
        } else {

            String query = "UPDATE species SET species=? WHERE id_species=?";
            int count = jdbcTemplate.update(query, species.getSpecies(), species.getIdSpecies());

            if (count == 0) {
                throw new EntityNotFoundException("chyba");
            }

            return new TreatmentAnimal.Species(species.getIdSpecies(), species.getSpecies());
        }

    }

    @Override
    public TreatmentAnimal.Species getById(Long id) {
        String query = "SELECT * FROM species WHERE id_species = " + id + ";";
        TreatmentAnimal.Species result = null;

        try {
            result = jdbcTemplate.queryForObject(query, new RowMapper<TreatmentAnimal.Species>() {
                @Override
                public TreatmentAnimal.Species mapRow(ResultSet rs, int rowNum) throws SQLException {
                    Long id = rs.getLong("id_species");
                    String species = rs.getString("species");
                    return new TreatmentAnimal.Species(id, species);
                }
            });
        } catch (EmptyResultDataAccessException e) {
            return null;
        }

        return result;
    }

    @Override
    public List<TreatmentAnimal.Species> getAll() {
        String query = "SELECT * FROM species;";
        List<TreatmentAnimal.Species> result = jdbcTemplate.query(query, new RowMapper<TreatmentAnimal.Species>() {
            @Override
            public TreatmentAnimal.Species mapRow(ResultSet rs, int rowNum) throws SQLException {
                long id = rs.getLong("id_species");
                String name = rs.getString("species");
                return new TreatmentAnimal.Species(id, name);
            }
        });

        return result;
    }

    @Override
    public void delete(Long idEmp) {

        String query = "DELETE FROM species WHERE id_species = " + idEmp + ";";
        try {
            jdbcTemplate.update(query);

        } catch (DataIntegrityViolationException e) {
            System.out.println(e);

        }
    }
}