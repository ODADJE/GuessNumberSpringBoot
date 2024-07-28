package sn.esmt.guess_the_number.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import sn.esmt.guess_the_number.models.User;

@Service
public class UserServiceJDBC implements IUserService {

    @Autowired
    private  JdbcTemplate jdbcTemplate;

    @Override
    public User findById(int id) {
        String sql = "SELECT * FROM users WHERE id = ?";
            User user = jdbcTemplate.queryForObject(
                sql,
                new Object[]{id},
                new BeanPropertyRowMapper<>(User.class)
            );
            return user;
    }

    @Override
    public User createUser(User user) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createUser'");
    }

    @Override
    public User findByLogin(String login) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByLogin'");
    }

    // public  User getOneUser(int id) {
    //     String sql = "SELECT * FROM users WHERE id = ?";
    //     User user = jdbcTemplate.queryForObject(
    //         sql,
    //         new Object[]{id},
    //         new BeanPropertyRowMapper<>(User.class)
    //     );
    //     return user;
    // }
}
