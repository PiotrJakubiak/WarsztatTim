package pl.edu.wat.tim.webstore.repository.impl.jdbc;

import pl.edu.wat.tim.webstore.model.Product;
import pl.edu.wat.tim.webstore.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Piotr on 09.05.2017.
 */
@Repository
public class ProductRepositoryImplJdbc implements ProductRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Transactional(readOnly=true)
    public List<Product> getAllProducts() {
        return jdbcTemplate.query("select * from product", new ProductRowMapper());
    }

    @Transactional
    public Product getProductById(int productID) {
        String sql = "SELECT * FROM product WHERE productId = ?";
        Product product = (Product)jdbcTemplate.queryForObject(sql, new Object[]{ productID},new ProductRowMapper());
        return product;
    }

    @Transactional
    public int addProduct(final Product product) {
        final String sql = "INSERT INTO product(productId, name, unitPrice, description,manufacturer,category,unitsInOrder,unitsInStock)" +
                " VALUES(?,?,?,?,?,?,?,?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sql, new String[] {"productId"});
                ps.setInt(1,product.getProductId());
                ps.setString(2, product.getName());
                ps.setBigDecimal(3, product.getUnitPrice());
                ps.setString(4,product.getDescription());
                ps.setString(5,product.getManufacturer());
                ps.setString(6,product.getCategory());
                ps.setLong(7,product.getUnitsInOrder());
                ps.setLong(8,product.getUnitsInStock());

                return ps;
            }
        }, keyHolder);

        return keyHolder.getKey().intValue();
    }


    @Transactional
    public void deleteProduct(int productId) {
        String sql = "DELETE FROM product WHERE productId = ?";
        jdbcTemplate.update(sql,new Object[] { productId });

    }

}
class ProductRowMapper implements RowMapper<Product>
{
    @Override
    public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
        Product prod = new Product();
        prod.setProductId(rs.getInt("productId"));
        prod.setName(rs.getString("name"));
        prod.setUnitPrice(rs.getBigDecimal("unitPrice"));
        prod.setDescription(rs.getString("description"));
        prod.setManufacturer(rs.getString("manufacturer"));
        prod.setCategory(rs.getString("category"));
        prod.setUnitsInOrder(rs.getLong("unitsInOrder"));
        prod.setUnitsInStock(rs.getLong("unitsInStock"));

        return prod;
    }
}