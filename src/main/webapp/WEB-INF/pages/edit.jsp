<%--
  Created by IntelliJ IDEA.
  User: Qing_Y
  Date: 2020-11-18
  Time: 16:06
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Edit</title>
</head>
<body>

<%
    pageContext.setAttribute("ctp", request.getContextPath());
%>

<h1>员工修改页面</h1>

<!--modelAttribute:这个表单所有内容显示绑定的是请求域中的employee-->
<form:form action="${ctp}/emp/${employee.id}" method="post" modelAttribute="employee">
    <input type="hidden" name="_method" value="put">
    <input type="hidden" name="id" value="${employee.id}">
    email:<form:input path="email"/><br>
    gender:&nbsp;&nbsp;&nbsp;
    男:<form:radiobutton path="gender" value="1"/>&nbsp;&nbsp;&nbsp;
    女:<form:radiobutton path="gender" value="0"/><br>
    department:<form:select path="department.id"
                            items="${departments}"
                            itemValue="id"
                            itemLabel="departmentName"/><br>
    <input type="submit" value="修改"/>
</form:form>

</body>
</html>
