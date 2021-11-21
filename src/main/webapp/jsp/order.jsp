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
        <title>Đơn hàng</title>
    </head>
    <body>
        <div class="header">
            <jsp:include page="header.jsp"></jsp:include>
            </div>

            <div class="main container my-3">
                <table class="table table-bordered table-responsive align-middle">
                    <thead>
                        <tr>
                            <th>Tên sản phẩm</th>
                            <th>Số lượng</th>
                            <th>Thành tiền</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>iPhone 13 Pro Max</td>
                            <td>
                                1
                            </td>
                            <td>100.000đ</td>
                        </tr>
                        <tr>
                            <td>iPhone 13 Pro Max</td>
                            <td>
                                1
                            </td>
                            <td>100.000đ</td>
                        </tr>
                        <tr>
                            <td>iPhone 13 Pro Max</td>
                            <td>
                                1
                            </td>
                            <td>100.000đ</td>
                        </tr>
                        <tr>
                            <td>iPhone 13 Pro Max</td>
                            <td>
                                1
                            </td>
                            <td>100.000đ</td>
                        </tr>
                    </tbody>
                </table>

                <div class="row">
                    <div class="col-md-6 my-3">
                        <label>Phương thức thanh toán</label>
                        <select class="form-select">
                            <option value="0">Tiền mặt</option>
                        </select>
                    </div>
                    <div class="col-md-6 my-3">
                        <label>Phương thức giao hàng</label>
                        <select class="form-select">
                            <option value="0">Giao hàng nhanh</option>
                            <option value="1">Giao hàng chậm</option>
                        </select>
                    </div>
                </div>
                <a href="/Shopping/paymentResult" class="btn btn-success">Thanh toán</a>
            </div>

            <div class="footer">
            <jsp:include page="footer.jsp"></jsp:include>
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
    </body>
</html>