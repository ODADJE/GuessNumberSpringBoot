package sn.esmt.guess_the_number.configurations;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import sn.esmt.guess_the_number.services.IUserService;
import sn.esmt.guess_the_number.services.UserServiceJDBC;
import sn.esmt.guess_the_number.services.UserServiceJPA;

@Configuration
public class GuessNumberConfiguration {
  @Value("${db.url}")
  private String dbUrl;
  @Value("${db.username}")
  private String dbUser;
  @Value("${db.password}")
  private String dbPassword;
  @Value ("${db.driver}")
  private String dbDriver;

  @Bean
  public DataSource dataSource() {
    DriverManagerDataSource dataSource = new DriverManagerDataSource();
    dataSource.setDriverClassName(dbDriver);
    dataSource.setUrl(dbUrl);
    dataSource.setUsername(dbUser);
    dataSource.setPassword(dbPassword);
    return dataSource;

  }
  
  @Bean
  public JdbcTemplate jdbcTemplate(DataSource dataSource) {
    return new JdbcTemplate(dataSource);
  }

  @Bean 
  public IUserService userServiceWithJDBC() {
    return new UserServiceJDBC();
  }

  @Bean
  @Primary
  public IUserService userServiceWithJPA() {
    return new UserServiceJPA();
  }
}



