<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
    <head>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <!-- Jquery -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

        <!-- Bootstrap CSS -->
        <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
            rel="stylesheet"
            integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
            crossorigin="anonymous">
        <link rel="stylesheet" href="./css/header.css">
        <link rel="stylesheet" href="./css/footer.css">
        <title>Danh sách sách</title>
        <style>
            .ratio-1 {
                aspect-ratio: 1;
            }
        </style>
    </head>
    <body>
        <div class="header">
            <jsp:include page="../header.jsp"></jsp:include>
            </div>

            <div class="main container my-3">
                <div class="row">
                    <div class="col-md-3 mt-3">
                        <div class="list-group">
                            <a href="#" class="list-group-item list-group-item-action active" aria-current="true">
                                The current link item
                            </a>
                            <a href="#" class="list-group-item list-group-item-action">A second link item</a>
                            <a href="#" class="list-group-item list-group-item-action">A third link item</a>
                            <a href="#" class="list-group-item list-group-item-action">A fourth link item</a>
                            <a class="list-group-item list-group-item-action disabled">A disabled link item</a>
                        </div>
                    </div>
                    <div class="col-md-9">
                        <div class="row">
                        <c:forEach var="book" items="${bookPag.items}">
                            <div class="col-md-4 mt-3">
                                <div class="border rounded-3 d-flex flex-column justify-content-between">
                                    <img src="https://cf.shopee.vn/file/565f9c6b5b3c901d56dd877738ca51d2" class="w-100 ratio-1" alt="${book.book.title}">
                                    <div class="p-3 d-flex flex-column justify-content-end">
                                        <div class="text-center"><c:out value="${book.book.title}" /></div>
                                        <div class="d-flex align-items-center justify-content-between">
                                            <div class="fs-5 fw-bold text-danger"><c:out value="${book.price}" />đ</div>
                                            <div class="text-decoration-line-through"></div>
                                        </div>
                                        <button data-barcode="${book.barCode}" class="btn btn-success mt-3 add-to-cart">Thêm vào giỏ hàng</button>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>

        <div class="footer">
            <jsp:include page="../footer.jsp"></jsp:include>
            </div>

            <!-- Optional JavaScript; choose one of the two! -->

            <!-- Option 1: Bootstrap Bundle with Popper -->
            <script
                src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
            crossorigin="anonymous"></script>

            <!-- Option 2: Separate Popper and Bootstrap JS -->
            <!--
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>
            -->
            <script>
            <jsp:include page="../js/program.js"/>
        </script>

        <script>
            $(function () {
                $('.add-to-cart').click(function () {
                    let barCode = $(this).attr('data-barcode');
                    fetch(`/Shopping/book/addToCart?barCode=${barCode}&quantity=1`, {
                        method: 'POST'
                    })
                            .then(res => res.json());
                });
            });
        </script>
    </body>
</html>