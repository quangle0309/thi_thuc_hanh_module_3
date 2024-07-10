package com.example.bai_thi_thuc_hanh.services;

import com.example.bai_thi_thuc_hanh.models.Product;
import com.example.bai_thi_thuc_hanh.models.ProductDTO;
import com.example.bai_thi_thuc_hanh.repositories.ProductRepo;

import java.util.List;

public class ProductService {
    ProductRepo productRepo = new ProductRepo();
    public List<ProductDTO> selectAll() {
        return productRepo.selectAll();
    }

    public boolean createProduct(Product product) {
        return productRepo.createProduct(product);
    }

    public List<ProductDTO> selectTopProduct(int numberTop) {
        return productRepo.selectTopProduct(numberTop);
    }
}
