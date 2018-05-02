/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SpringConfig;

import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
@ComponentScan(basePackages = {"Spring.Dao"})
public class Konfigurasi {

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        dataSource.setUsername("sa");
        dataSource.setUrl("jdbc:sqlserver://localhost;databaseName=SpringJDBC;instanceName=SQLEXPRESS2017");
        dataSource.setPassword("16agustus1994");
        return dataSource;

    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource());
        jdbcTemplate.setResultsMapCaseInsensitive(true);
        return jdbcTemplate;
    }

    @Bean
    public NamedParameterJdbcTemplate nameJdbcTemplate() {
        NamedParameterJdbcTemplate nameJdbcTemplate = new NamedParameterJdbcTemplate(dataSource());
        
        return nameJdbcTemplate;
    }

}
