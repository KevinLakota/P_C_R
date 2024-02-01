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

public class PostgresSpecialtyDao implements SpecialtyDao {

	private JdbcTemplate jdbcTemplate;

	public PostgresSpecialtyDao(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public Specialty save(Specialty specialty) {
		/*
		 * if (attendance == null) throw new
		 * NullPointerException("Cannot save null Session"); if (attendance.getSubject()
		 * == null || attendance.getSubject().getId() == null) { throw new
		 * NullPointerException("Cannot extract id from subject"); } if
		 * (attendance.getWhen() == null) { throw new
		 * NullPointerException("Cannot save session without date"); }
		 */
		if (specialty.getIdSpecialty() == null) { // INSERT

			String query = "INSERT INTO specialty (specialty) VALUES (?)";
			GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
			jdbcTemplate.update(con -> {
				PreparedStatement statement = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
				statement.setString(1, specialty.getSpecialty());

				return statement;
			}, keyHolder);
			Long id = Long.parseLong(String.valueOf(keyHolder.getKeys().get("id_specialty")));
			return new Specialty(id,specialty.getSpecialty());
		} else {
			String query = "UPDATE specialty SET specialty=? WHERE id_specialty=?";
			int count = jdbcTemplate.update(query, specialty.getSpecialty(), specialty.getIdSpecialty());
			if (count == 0) {
				throw new EntityNotFoundException("chyba");
			}

			return new Specialty(specialty.getIdSpecialty(),specialty.getSpecialty());
		}
	}

	@Override
	public Specialty getByOwnerId(Long id) {
		String query = "SELECT * FROM specialty WHERE id_specialty = " + id + ";";
		Specialty result;
		try {
			result = jdbcTemplate.queryForObject(query, new RowMapper<Specialty>() {
				@Override
				public Specialty mapRow(ResultSet rs, int rowNum) throws SQLException {
					Long id = rs.getLong("id_specialty");
					String specialty = rs.getString("specialty");
					return new Specialty(id, specialty);
				}
			});
		}catch (EmptyResultDataAccessException e) {
			return null;
		}

		return result;
	}

	@Override
	public List<Specialty> getAll() {
		String query = "SELECT * FROM specialty;";
		List<Specialty> result = jdbcTemplate.query(query, new RowMapper<Specialty>() {
			@Override
			public Specialty mapRow(ResultSet rs, int rowNum) throws SQLException {
				Long id = rs.getLong("id_specialty");
				String specialty = rs.getString("specialty");
				return new Specialty(id, specialty);
			}
		});

		return result;
	}

	@Override
	public void delete(Long idEmp) {
		String query = "DELETE FROM specialty WHERE id_specialty = " + idEmp + ";";
		try {
			jdbcTemplate.update(query);

		} catch (DataIntegrityViolationException e) {
			System.out.println(e);
		}
	}

}
