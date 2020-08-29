<%@include file="/common/taglib.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<div class="container">
    <!-- <h1 class="form-heading">login Form</h1> -->
    <div class="login-form">
        <div class="main-div">
            <%--@elvariable id="message" type="java.lang.String"--%>
            <%--@elvariable id="alert" type="java.lang.String"--%>
            <c:if test="${not empty message}">
                <div class="alert alert-${alert}">
                        ${message}
                </div>
            </c:if>
            <form action="<c:url value="/dang-nhap"/>" id="formLogin" method="post">
                <div class="form-group">
                    <label for="userName"></label>
                    <input class="form-control" id="userName" name="userName" placeholder="Tên đăng nhập"
                           type="text">
                </div>

                <div class="form-group">
                    <label for="password">
                    </label><input type="password" class="form-control" id="password" name="password"
                                   placeholder="Mật khẩu">
                </div>
                <input type="hidden" value="login" name="action"/>
                <button type="submit" class="btn btn-primary">Đăng nhập</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>
