<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglib.jsp" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="main-container">
    <div class="main-content-inner">
        <div class="breadcrumbs ace-save-state" id="breadcrumbs">
            <ul class="breadcrumb">
                <li>
                    <i class="ace-icon fa fa-home home-icon"></i>
                    <a href="#">Trang chu</a>
                </li>
            </ul>
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
                                        <th>Title</th>
                                        <th>ShortDescription</th>
                                        <th>Content</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <%--@elvariable id="model" type="com.test.model.NewModel"--%>
                                    <c:forEach var="item" items="${model.listData}">
                                        <tr>
                                            <td>${item.title}</td>
                                            <td>${item.shortDescription}</td>
                                            <td>${item.content}</td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                                <%--                                <ul class="pagination" id="pagination"></ul>--%>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<%--<script type="text/javascript">
    $(function () {
        window.pagObj = $('#pagination').twbsPagination({
            totalPages: 10,
            visiblePages: 5,
            onPageClick: function (event, page) {
                console.info(page + ' (from options)');
            }
        }).on('page', function (event, page) {
            console.info(page + ' (from event listening)');
        });
    });

</script>--%>
</body>
</html>
