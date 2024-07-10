package com.example.bai_thi_thuc_hanh.controllers;


import com.example.bai_thi_thuc_hanh.models.Discount;
import com.example.bai_thi_thuc_hanh.models.Product;
import com.example.bai_thi_thuc_hanh.models.ProductDTO;
import com.example.bai_thi_thuc_hanh.services.DiscountService;
import com.example.bai_thi_thuc_hanh.services.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet (name = "ProductServlet", urlPatterns = "/products/*")
public class ProductServlet extends HttpServlet {
    ProductService productService = new ProductService();
    DiscountService discountService = new DiscountService();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String url = req.getPathInfo();
        switch (url) {
            case "/list":
                showList(req,resp);
                break;
            case "/topProduct":
                showTopProduct(req, resp);
                break;
            case "/create":
                showFormCreate(req, resp);
                break;

        }
    }

    private void showTopProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int numberTop = Integer.parseInt(req.getParameter("number"));
        List<ProductDTO> products = productService.selectTopProduct(numberTop);
        req.setAttribute("products", products);
        req.getRequestDispatcher("/product/lis.jsp").forward(req,resp);
    }

    private void showFormCreate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Discount> discounts = discountService.selectAll();
        req.setAttribute("discounts", discounts);
        req.getRequestDispatcher("/product/create.jsp").forward(req, resp);
    }

    private void showList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<ProductDTO> products = productService.selectAll();
        req.setAttribute("products", products);
        req.getRequestDispatcher("/product/list.jsp").forward(req, resp);
    }


    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String url = req.getPathInfo();
        switch (url) {
            case "/create":
                createProduct(req, resp);
                break;
            default:
                break;
        }
    }

    private void createProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Product product = new Product();
        product.setName(req.getParameter("name"));
        product.setPrice(Double.parseDouble(req.getParameter("price")));
        product.setDiscountId(Integer.parseInt(req.getParameter("discountId")));
        product.setInventory(Integer.parseInt(req.getParameter("inventory")));
        boolean result = productService.createProduct(product);
        if (result) {
            req.setAttribute("successCreate", "Thêm mới thành công!");
        } else {
            req.setAttribute("errorCreate", "Thêm mới thất bại!");
        }
        List<ProductDTO> products = productService.selectAll();
        req.setAttribute("products", products);
        req.getRequestDispatcher("/product/list.jsp").forward(req, resp);
    }
}
