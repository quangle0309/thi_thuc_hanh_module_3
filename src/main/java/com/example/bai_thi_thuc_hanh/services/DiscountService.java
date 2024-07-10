package com.example.bai_thi_thuc_hanh.services;

import com.example.bai_thi_thuc_hanh.models.Discount;
import com.example.bai_thi_thuc_hanh.repositories.DiscountRepo;

import java.util.List;

public class DiscountService {
    DiscountRepo discountRepo = new DiscountRepo();

    public List<Discount> selectAll() {
        return discountRepo.selectAll();
    }
}
