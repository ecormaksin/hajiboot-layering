package com.example.port.adapter.persistence;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.customer.Customer;
import com.example.domain.customer.CustomerRepository;

@Repository
@Transactional
public class JdbcTemplateCustomerRepository implements CustomerRepository {

    NamedParameterJdbcTemplate jdbcTemplate;
    
    SimpleJdbcInsert insert;
    
    JdbcTemplateCustomerRepository(NamedParameterJdbcTemplate aJdbcTemplate) {
        this.jdbcTemplate = aJdbcTemplate;
    }
    
    @PostConstruct
    public void init() {
        this.insert = new SimpleJdbcInsert((JdbcTemplate) jdbcTemplate.getJdbcOperations())
                .withTableName("customers")
                .usingGeneratedKeyColumns("id");
    }
    
    private static final RowMapper<Customer> customerRowMapper = (rs, i) -> {
        Integer id = rs.getInt("id");
        String firstName = rs.getString("first_name");
        String lastName = rs.getString("last_name");
        return new Customer(id, firstName, lastName);
    };
    
    @Override
    public List<Customer> findAll() {
        List<Customer> customers = jdbcTemplate.query(
                "SELECT id, first_name, last_name FROM customers ORDER BY id", 
                customerRowMapper);
        return customers;
    }
    
    @Override
    public Customer findOne(Integer id) {
        SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
        return jdbcTemplate.queryForObject(
                "SELECT id, first_name, last_name FROM customers WHERE id=:id", 
                param, 
                customerRowMapper);
    }
    
    @Override
    public Customer save(Customer customer) {
        SqlParameterSource param = new BeanPropertySqlParameterSource(customer);
        if (customer.getId() == null) {
            Number key = this.insert.executeAndReturnKey(param);
            customer.setId(key.intValue());
        } else {
            jdbcTemplate.update("UPDATE customers SET first_name=:firstName, last_name=:lastName WHERE id=:id", 
                    param);
        }
        return customer;
    }
    
    @Override
    public void delete(Integer id) {
        SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
        jdbcTemplate.update("DELETE FROM customers WHERE id=:id", 
                param);
    }
}
