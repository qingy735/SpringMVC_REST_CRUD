<%--
  Created by IntelliJ IDEA.
  User: Qing_Y
  Date: 2020-11-17
  Time: 17:19
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
    pageContext.setAttribute("ctp", request.getContextPath());
%>

<html>
<head>
    <title>员工列表页面</title>

    <script type="text/javascript" src="${ctp}/scripts/jquery-1.9.1.min.js"></script>

</head>
<body>
<h1>员工列表</h1>
<table border="1" cellpadding="5px" cellspacing="0">
    <tr>
        <th>ID</th>
        <th>LastName</th>
        <th>Email</th>
        <th>Gender</th>
        <th>DepartmentName</th>
        <th>Edit</th>
        <th>Delete</th>
    </tr>
    <c:forEach items="${employees}" var="employee">
        <tr>
            <td>${employee.id}</td>
            <td>${employee.lastName}</td>
            <td>${employee.email}</td>
            <td>${employee.gender == 0 ? "女" : "男"}</td>
            <td>${employee.department.departmentName}</td>
            <td><a href="${ctp}/emp/${employee.id}">编辑</a></td>
            <td><a href="${ctp}/emp/${employee.id}" class="delBtn">删除</a></td>
        </tr>
    </c:forEach>
</table>
<a href="${ctp}/toaddpage">添加员工</a>
<form action="${ctp}/quickadd">
    <input type="text" name="empInfo" value="empAdmin-admin@qq.com-1-101">
    <input type="submit" value="快速添加">
</form>

<form action="" method="post" id="delForm">
    <input type="hidden" name="_method" value="delete">
</form>

<script type="text/javascript">
    $(function () {
        $(".delBtn").click(function () {
            // 改变表单action指向
            $("#delForm").attr("action", this.href)
            // 提交表单
            $("#delForm").submit();
            return false;
        })
    })
</script>

</body>
</html>
