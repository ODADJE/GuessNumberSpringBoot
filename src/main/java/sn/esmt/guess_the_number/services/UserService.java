package sn.esmt.guess_the_number.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import sn.esmt.guess_the_number.models.User;

@Service
public class UserService {

    @Autowired
    private  JdbcTemplate jdbcTemplate;
    

    public  User getOneUser(int id) {
        String sql = "SELECT * FROM users WHERE id = ?";
        User user = jdbcTemplate.queryForObject(
            sql,
            new Object[]{id},
            new BeanPropertyRowMapper<>(User.class)
        );
        return user;
    }
}
