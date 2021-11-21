<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<div class="container my-3">
    <div class="col-md-6 offset-md-3">
        <div class="shadow p-4">
            <form action="/Shopping/admin/postLogin" method="POST" class="form" id="form-1">
                <h3 class="mb-3">Thành viên</h3>

                <div class="form-floating mb-3">
                    <input type="text" name="username" class="form-control" id="username" placeholder="Tên đăng nhập">
                    <label for="username">Tên đăng nhập</label>
                </div>
                <div class="form-floating">
                    <input type="password" name="password" class="form-control" id="password" placeholder="Mật khẩu">
                    <label for="password">Mật khẩu</label>
                </div>
                <button class="btn btn-primary w-100 mt-4 form-submit">Đăng nhập</button>

            </form>
        </div>
    </div>
</div>