<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Danh sách sản phẩm</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body class="bg-light">
<div class="row g-5 m-auto container">

    <div class="d-flex justify-content-between p-3 shadow-sm bg-white align-items-center">
        <div class="d-flex align-items-center">
            <h6 class="text-decoration-none mb-0 ">Tất cả sản phẩm</h6>
        </div>

            <form action="/product/topProduct" method="get" class="my-auto">
                <label>Top sản phẩm được xem nhiều nhất</label>
                <input name="number" type="number" required min="1">
                <button type="submit">Xem</button>
            </form>


        <div class="d-flex align-items-center">
            <a href="/products/create" type="button" class="btn btn-sm btn-primary ms-2">Thêm mới</a>
        </div>
    </div>


    <table class="table table-bordered mt-3">
        <thead>
        <tr>
            <th>STT</th>
            <th>Tên sản phẩm</th>
            <th>Giá</th>
            <th>Giá giảm</th>
            <th>Tồn kho</th>
            <th>Thao tác</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="product" items="${products}" varStatus="status">
            <tr>
                <td class="align-middle">${status.count}</td>
                <td class="align-middle">${product.name}</td>
                <td class="align-middle"><fmt:formatNumber value="${product.price}" type="number"/>$</td>
                <td class="align-middle">${product.discount}%</td>
                <td class="align-middle">${product.inventory}</td>
                <td class="align-middle">
                    <div>
                        <a href="/products/update?id=${product.id}" type="button"
                           class="btn btn-sm btn-outline-warning me-1">Chỉnh sửa</a>
                        <button
                                class="btn btn-sm btn-outline-danger" data-bs-toggle="modal"
                                data-bs-target="#modalDelete${product.id}">Xóa</button>
                    </div>
                    <div class="modal fade" tabindex="-1" id="modalDelete${product.id}">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title">Xóa Sản Phẩm</h5>
                                    <button type="button" class="btn-close" data-bs-dismiss="modal"
                                            aria-label="Close"></button>
                                </div>
                                <div class="modal-body">
                                    <p class="my-auto text-center">Bạn có chắc chắn muốn xóa sản phẩm này!!!</p>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-outline-secondary" data-bs-dismiss="modal"
                                            name="cancel">Hủy
                                    </button>
                                    <form action="/products/delete" method="get">
                                        <button type="submit" class="btn btn-danger">Xác nhận</button>
                                        <input type="hidden" name="id" value="${product.id}">
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<c:if test="${successCreate != null}">
    <script>
        Swal.fire({
            icon: "success",
            title: "${successCreate}",
            showConfirmButton: false,
            timer: 1500
        });
    </script>
</c:if>
<c:if test="${errorCreate != null}">
    <script>
        Swal.fire({
            icon: "error",
            title: "${errorCreate}",
            showConfirmButton: false,
            timer: 1500
        });
    </script>
</c:if>
</body>
</html>
