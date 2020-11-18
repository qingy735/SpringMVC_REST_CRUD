<%--
  Created by IntelliJ IDEA.
  User: Qing_Y
  Date: 2020-11-17
  Time: 17:36
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Add</title>
</head>
<body>
<h1>员工添加</h1>
<!--
        表单标签:
            通过SpringMVC的表单标签可以实现将模型数据中的属性和HTML表单元素绑定以实现表单数据回显
-->

<%
    pageContext.setAttribute("ctp", request.getContextPath());
%>

<form:form action="${ctp}/emp" method="post" modelAttribute="employee">
    <!--path就是原来html-input的name项
    path:
    当作原生的name项
    自动回显隐含模型中某个对象对应的这个属性的值
    -->
    lastName：<form:input path="lastName"/><br>
    email：<form:input path="email"/><br>
    gender：
    男：<form:radiobutton path="gender" value="1"/><br>
    女：<form:radiobutton path="gender" value="0"/><br>
    department:
    <!--items：指定要遍历的集合-->
    <form:select path="department.id"
                 items="${departments}"
                 itemLabel="departmentName"
                 itemValue="id"/><br>
    <input type="submit" value="保存">
</form:form>

<%--<form action="toaddpage">
    lastName：<input type="text" name="lastName"><br>
    email：<input type="text" name="email"><br>
    gender：<br>
    男：<input type="radio" name="gender" value="1"><br>
    女：<input type="radio" name="gender" value="0"><br>
    部门:
    <select name="department.id">
        <c:forEach items="${departments}" var="department">
            <!--value中为提交信息-->
            <option value="${department.id}">${department.departmentName}</option>
        </c:forEach>
    </select>
    <input type="submit" value="提交">
</form>--%>
</body>
</html>
