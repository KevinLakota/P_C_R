package Rest.DAO;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class PostgresTreatmentAnimalDao implements TreatmentAnimalDao {

	private JdbcTemplate jdbcTemplate;

	public PostgresTreatmentAnimalDao(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public void save(TreatmentAnimal ta) {
			if(ta.getIdAnimal() == null && ta.getIdTreatment() == null && ta.getWhen() == null) { // INSERT

			String query = "INSERT INTO treatment_animal (id_treatment, id_animal, when) VALUES (?,?,?)";
			//GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
			jdbcTemplate.update(con -> {
				PreparedStatement statement = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
				statement.setLong(1, ta.getIdAnimal());
				statement.setLong(2, ta.getIdTreatment());
				statement.setString(3, ta.getWhen());

				return statement;
			});

		}

	}

	@Override
	public List<TreatmentAnimal> getAll() {
		String query = "SELECT * FROM treatment_animal;";
		List<TreatmentAnimal> result = jdbcTemplate.query(query, new RowMapper<TreatmentAnimal>() {
			@Override
			public TreatmentAnimal mapRow(ResultSet rs, int rowNum) throws SQLException {
				long idTreatment = rs.getLong("id_treatment");
				long idAnimal = rs.getLong("id_animal");
				String when = rs.getString("when");
				return new TreatmentAnimal(idTreatment, idAnimal, when);
			}
		});

		return result;
	}

	@Override
	public void delete(String when) {
		String query = "DELETE FROM treatment_animal WHERE when = " + "'" + when + "'" + ";";
		int count = jdbcTemplate.update(query);
		if(count == 0) {
			throw new EntityNotFoundException("chyba");
		}
	}

}
