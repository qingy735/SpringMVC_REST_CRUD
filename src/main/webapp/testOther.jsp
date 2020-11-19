<%--
  Created by IntelliJ IDEA.
  User: Qing_Y
  Date: 2020-11-19
  Time: 23:09
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%
    pageContext.setAttribute("ctp", request.getContextPath());
%>
<html>
<head>
    <title>Other</title>
    <script type="text/javascript" src="scripts/jquery-1.9.1.min.js"></script>

    <script>
        $(function () {
            $("a:first").click(function () {
                // 点击发送ajax请求，请求带的数据是json
                var emp = {lastName: "张三", email: "aaa@qq.com", gender: 0}
                // emp转json
                var empStr = JSON.stringify(emp)
                $.ajax({
                    url: "${ctp}/testRequestBody",
                    type: "post",
                    data: empStr,
                    contentType: "application/json",
                    success: function (data) {
                        alert(data)
                    }
                })
                return false;
            })
        })
    </script>
</head>
<body>
<form action="${ctp}/testRequestBody" method="post" enctype="multipart/form-data">
    <input type="text" name="username" value="tomcat"><br>
    <input type="text" name="password" value="12345"><br>
    <input type="file" name="file"><br>
    <input type="submit" value="提交">
</form>
<a href="${ctp}/testRequestBody">ajax发送json数据</a>
</body>
</html>
