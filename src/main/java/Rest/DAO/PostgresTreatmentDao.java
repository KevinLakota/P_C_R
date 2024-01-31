package Rest.DAO;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class PostgresTreatmentDao implements TreatmentDao{

	private JdbcTemplate jdbcTemplate;

	public PostgresTreatmentDao(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public void save(TreatmentAnimal.Treatment treatment) {
		/*if (attendance == null)
		throw new NullPointerException("Cannot save null Session");
	if (attendance.getSubject() == null ||
			attendance.getSubject().getId() == null) {
		throw new NullPointerException("Cannot extract id from subject");
	}
	if (attendance.getWhen() == null) {
		throw new NullPointerException("Cannot save session without date");
	}*/
		if (treatment.getIdTreatment() == null) { // INSERT

			String query = "INSERT INTO treatment (medicine, dosage, activity) VALUES (?,?,?)";
			//GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
			jdbcTemplate.update(con -> {
				PreparedStatement statement = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
				statement.setString(1, treatment.getMedicine());
				statement.setString(2, treatment.getDosage());
				statement.setString(3, treatment.getActivity());

				return statement;
			} /*, keyHolder*/);
			//long id = keyHolder.getKey().longValue();

			//return new User(id, user.getUsername(), user.getPasswd(), user.getSalt(), user.isAdmin(), user.getIdOwner(), user.getIdEmpl());
		}else {


			String query = "UPDATE treatment SET medicine=?, dosage=?, activity=?"
					+ "WHERE id_treatment=?";
			int count = jdbcTemplate.update(query,treatment.getMedicine(), treatment.getDosage(),
					treatment.getActivity(), treatment.getIdTreatment());

			if(count == 0 ) {
				throw new EntityNotFoundException("chyba");
			}

		}

	}

	@Override
	public List<TreatmentAnimal.Treatment> getAll() {
		String query = "SELECT * FROM treatment;";
		List<TreatmentAnimal.Treatment> result = jdbcTemplate.query(query, new RowMapper<TreatmentAnimal.Treatment>() {
			@Override
			public TreatmentAnimal.Treatment mapRow(ResultSet rs, int rowNum) throws SQLException {
				long id = rs.getLong("id_treatment");
				String medicine = rs.getString("medicine");
				String dosage = rs.getString("dosage");
				String activity = rs.getString("activity");
				return new TreatmentAnimal.Treatment(id, medicine, dosage, activity);
			}
		});

		return result;
	}

	@Override
	public void delete(Long idTreatment) {
		String query = "DELETE FROM treatment WHERE id_treatment = " + idTreatment + ";";
		int count = jdbcTemplate.update(query);
		if(count == 0) {
			throw new EntityNotFoundException("chyba");
		}
	}

}
