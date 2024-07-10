package com.example.bai_thi_thuc_hanh.repositories;

import com.example.bai_thi_thuc_hanh.database.ConnectDB;
import com.example.bai_thi_thuc_hanh.models.Discount;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DiscountRepo {
    public List<Discount> selectAll() {
        String sql = "select * from discounts";
        List<Discount> discounts = new ArrayList<>();
        try (Connection connection = new ConnectDB().getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery())
        {
            while (rs.next()) {
                Discount discount = new Discount();
                discount.setId(rs.getInt("id"));
                discount.setDiscount(rs.getInt("discount"));
                discounts.add(discount);
            }
            return discounts;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
