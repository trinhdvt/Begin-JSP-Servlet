<%@include file="/common/taglib.jsp" %>
<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Danh sách bài viết</title>
</head>

<body>
<div class="main-content">
    <form action="<c:url value='/admin-new'/>" id="formSubmit" method="get">
        <div class="main-content-inner">
            <div class="breadcrumbs ace-save-state" id="breadcrumbs">
                <ul class="breadcrumb">
                    <li>
                        <i class="ace-icon fa fa-home home-icon"></i>
                        <a href="#">Trang chủ</a>
                    </li>
                </ul>
                <!-- /.breadcrumb -->
            </div>
            <div class="page-content">
                <div class="row">
                    <div class="col-xs-12">
                        <div class="row">
                            <div class="col-xs-12">
                                <div class="table-responsive">
                                    <table class="table table-bordered">
                                        <thead>
                                        <tr>
                                            <th>Tên bài viết</th>
                                            <th>Mô tả ngắn</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <%--@elvariable id="model" type="com.test.model.NewModel"--%>
                                        <c:forEach var="item" items="${model.listData}">
                                            <tr>
                                                <td>${item.title}</td>
                                                <td>${item.shortDescription}</td>
                                            </tr>
                                        </c:forEach>
                                        </tbody>
                                    </table>
                                    <ul class="pagination" id="pagination"></ul>
                                    <input type="hidden" value="" id="currentPage" name="currentPage">
                                    <input type="hidden" value="" id="maxPageItems" name="maxPageItems">
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </form>
</div>
<!-- /.main-content -->
<script type="text/javascript">
    let totalPages = ${model.totalPages};
    let currentPage = ${model.currentPage};
    let limit = 2;
    $(function () {
        window.pagObj = $('#pagination').twbsPagination({
            totalPages: totalPages,
            visiblePages: 10,
            startPage: currentPage,
            onPageClick: function (event, page) {
                if (currentPage !== page) {
                    $('#maxPageItems').val(limit);
                    $('#currentPage').val(page);
                    $('#formSubmit').submit();
                }
            }
        });
    });
</script>
</body>

</html>