package com.example.bai_thi_thuc_hanh.repositories;

import com.example.bai_thi_thuc_hanh.database.ConnectDB;
import com.example.bai_thi_thuc_hanh.models.Product;
import com.example.bai_thi_thuc_hanh.models.ProductDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductRepo {
    public List<ProductDTO> selectAll() {
        Connection connection = new ConnectDB().getConnection();
        List<ProductDTO> products = new ArrayList<>();
        String sql = "select p.id, p.name, p.price, d.discount, p.inventory from products p " +
                "join discounts d on p.discount_id = d.id";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ProductDTO product = new ProductDTO();
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setPrice(rs.getDouble("price"));
                product.setDiscount(rs.getInt("discount"));
                product.setInventory(rs.getInt("inventory"));
                products.add(product);
            }
            return products;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean createProduct(Product product) {
        Connection connection = new ConnectDB().getConnection();
        String sql = "insert into products (name, price, discount_id, inventory) values (?,?,?,?)";
        try(PreparedStatement ps = connection.prepareStatement(sql);
        ) {
            ps.setString(1, product.getName());
            ps.setDouble(2, product.getPrice());
            ps.setInt(3, product.getDiscountId());
            ps.setInt(4, product.getInventory());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public List<ProductDTO> selectTopProduct(int numberTop) {
        Connection connection = new ConnectDB().getConnection();
        List<ProductDTO> products = new ArrayList<>();
        String sql = "SELECT p.id, p.name, p.price, d.discount, p.inventory SUM(p.id) as sum_product " +
                "FROM products p " +
                "join discounts d on p.discount_id = d.id " +
                "JOIN orders o ON p.id = o.product_id " +
                "GROUP BY p.id, p.name " +
                "ORDER BY sum_product DESC " +
                "LIMIT ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, numberTop);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ProductDTO product = new ProductDTO();
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setPrice(rs.getDouble("price"));
                product.setDiscount(rs.getInt("discount"));
                product.setInventory(rs.getInt("inventory"));
                products.add(product);
            }
            return products;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
