<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body>
<div class="container mt-5">

    <form class="w-50 mx-auto mt-4 shadow-lg rounded-3 p-5 " action="/products/create" method="post">
        <h2 class="text-center mb-3 text-secondary">Thêm sản phẩm mới</h2>
        <div class="mb-3">
            <label for="name" class="form-label">Tên sản phẩm</label>
            <input type="text" class="form-control" id="name" name="name" required>
        </div>
        <div class="mb-3">
            <label for="price" class="form-label">Giá</label>
            <input type="number" class="form-control" id="price" name="price" required min="100" max="2000000000">
        </div>

        <div class="row">
            <div class="mb-3 col-6">
                <label for="discount" class="form-label">Giảm giá</label>
                <select class="form-select" aria-label="Default select example" name="discountId" id="discount">
                    <c:forEach var="discount" items="${discounts}">
                        <option value="${discount.id}">${discount.discount} %</option>
                    </c:forEach>
                </select>
            </div>

            <div class="mb-3 col-6">
                <label for="inventory" class="form-label">Số lượng</label>
                <input type="number" class="form-control" id="inventory" name="inventory" min="10" max="2000000000" required>
            </div>
        </div>


        <div class="mt-4 d-flex justify-content-end">
            <button type="submit" class="btn btn-primary text-white me-2">Thêm</button>
            <a href="/products/list" type="submit" class="btn btn-danger">Hủy</a>
        </div>

    </form>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>
</body>
</html>
