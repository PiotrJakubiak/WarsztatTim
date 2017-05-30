package pl.edu.wat.tim.webstore.repository.impl.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.wat.tim.webstore.model.Customer;
import pl.edu.wat.tim.webstore.repository.CustomerRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Piotr on 22.05.2017.
 */
@Repository
public class CustomerRepositoryImplJdbc implements CustomerRepository{

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Transactional(readOnly=true)
    public List<Customer> getAllCustomers() {
        return jdbcTemplate.query("select * from customer", new CustomerRowMapper());
    }

    @Transactional
    public Customer getCustomerById(int customerId) {
        String sql = "SELECT * FROM customer WHERE customer_id = ?";
        Customer customer = (Customer)jdbcTemplate.queryForObject(sql, new Object[]{ customerId},new CustomerRowMapper());
        return customer;
    }

    @Transactional
    public int addCustomer(final Customer customer) {
        final String sql = "INSERT INTO customer(customer_id,email, name)" +
                " VALUES(?,?,?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sql, new String[] {"customer_id"});
                ps.setInt(1,customer.getId());
                ps.setString(2, customer.getEmail());
                ps.setString(3, customer.getName());

                return ps;
            }
        }, keyHolder);

        return keyHolder.getKey().intValue();
    }

    @Transactional
    public void deleteCustomer(int customerId) {
        String sql = "DELETE FROM customer WHERE customer_id = ?";
        jdbcTemplate.update(sql,new Object[] { customerId });
    }

    class CustomerRowMapper implements RowMapper<Customer>
    {
        @Override
        public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
            Customer cust = new Customer();
            cust.setId(rs.getInt("customer_id"));
            cust.setEmail(rs.getString("email"));
            cust.setName(rs.getString("name"));
            return cust;
        }
    }
}
