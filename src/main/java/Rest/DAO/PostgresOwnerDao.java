package Rest.DAO;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class PostgresOwnerDao implements OwnerDao {

    private JdbcTemplate jdbcTemplate;

    public PostgresOwnerDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Owner save(Owner owner) {

        if (owner.getIdOwner() == null) { // INSERT

            String query = "INSERT INTO owners (first_name, surname, phone_number, email) " + "VALUES (?,?,?,?)";
            GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(con -> {
                PreparedStatement statement = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                statement.setString(1, owner.getFirstName());
                statement.setString(2, owner.getSurname());
                statement.setString(3, owner.getPhoneNumber());
                statement.setString(4, owner.getEmail());

                return statement;
            }, keyHolder);
            Long id = Long.parseLong(String.valueOf(keyHolder.getKeys().get("id_owner")));

            return new Owner(id, owner.getFirstName(), owner.getSurname(), owner.getPhoneNumber(), owner.getEmail());
        } else {
            String query = "UPDATE owners SET  first_name=?, surname=?, phone_number=?, email=? " + "WHERE id_owner=?";
            int count = jdbcTemplate.update(query, owner.getFirstName(), owner.getSurname(), owner.getPhoneNumber(),
                    owner.getEmail(), owner.getIdOwner());

            if (count == 0) {
                throw new EntityNotFoundException("chyba");
            }
            return new Owner(owner.getIdOwner(), owner.getFirstName(), owner.getSurname(), owner.getPhoneNumber(),
                    owner.getEmail());
        }
    }

    @Override
    public Owner getByPhoneNumber(String phoneNumber) {
        Owner result = null;
        String query = "SELECT * FROM owners WHERE phone_number = " + "'" + phoneNumber + "'" + ";";

        try {
            result = jdbcTemplate.queryForObject(query, new RowMapper<Owner>() {
                @Override
                public Owner mapRow(ResultSet rs, int rowNum) throws SQLException {
                    long id = rs.getLong("id_owner");
                    String firstName = rs.getString("first_name");
                    String surname = rs.getString("surname");
                    String phoneNum = rs.getString("phone_number");
                    String email = rs.getString("email");

                    return new Owner(id, firstName, surname, phoneNum, email);
                }
            });
        } catch (Exception EmptyResultDataAccessException) {
            return null;
        }

        return result;
    }

    @Override
    public Owner getById(Long idOwner) {
        Owner result = null;
        String query = "SELECT * FROM owners WHERE id_owner = " + idOwner + ";";

        try {
            result = jdbcTemplate.queryForObject(query, new RowMapper<Owner>() {
                @Override
                public Owner mapRow(ResultSet rs, int rowNum) throws SQLException {
                    long id = rs.getLong("id_owner");
                    String firstName = rs.getString("first_name");
                    String surname = rs.getString("surname");
                    String phoneNum = rs.getString("phone_number");
                    String email = rs.getString("email");

                    return new Owner(id, firstName, surname, phoneNum, email);
                }
            });
        } catch (Exception EmptyResultDataAccessException) {
            return null;
        }

        return result;
    }

    @Override
    public List<Owner> getAll() {
        String query = "SELECT * FROM owners;";
        List<Owner> result = jdbcTemplate.query(query, new RowMapper<Owner>() {
            @Override
            public Owner mapRow(ResultSet rs, int rowNum) throws SQLException {
                long id = rs.getLong("id_owner");
                String firstName = rs.getString("first_name");
                String surname = rs.getString("surname");
                String phoneNumber = rs.getString("phone_number");
                String email = rs.getString("email");
                return new Owner(id, firstName, surname, phoneNumber, email);
            }
        });

        return result;
    }

    @Override
    public Owner getByEmail(String email) {
        Owner result = null;
        String query = "SELECT * FROM owners WHERE email = '" + email + "';";

        try {
            result = jdbcTemplate.queryForObject(query, new RowMapper<Owner>() {
                @Override
                public Owner mapRow(ResultSet rs, int rowNum) throws SQLException {
                    long id = rs.getLong("id_owner");
                    String firstName = rs.getString("first_name");
                    String surname = rs.getString("surname");
                    String phoneNum = rs.getString("phone_number");
                    String email = rs.getString("email");

                    return new Owner(id, firstName, surname, phoneNum, email);
                }
            });
        } catch (Exception EmptyResultDataAccessException) {
            return null;
        }

        return result;
    }

    @Override
    public void delete(Long idOwner) {
        String query = "DELETE FROM owners WHERE id_owner = " + idOwner + ";";
        try {
            jdbcTemplate.update(query);

        } catch (DataIntegrityViolationException e) {

            jdbcTemplate.update(query);
        }
    }
}