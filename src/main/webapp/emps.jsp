<%@ page import="java.util.Date" %><%--
  Created by IntelliJ IDEA.
  User: Qing_Y
  Date: 2020-11-19
  Time: 22:52
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>

<%
    pageContext.setAttribute("ctp", request.getContextPath());
%>

<html>
<head>
    <title>Emps</title>
    <script type="text/javascript" src="scripts/jquery-1.9.1.min.js"></script>

    <script>
        $(function () {
            $("a:first").click(function () {
                // 发送ajax请求获取所有员工
                $.ajax({
                    url: "${ctp}/ajaxgetall",
                    type: "post",
                    success: function (data) {
                        // console.log(data)
                        $.each(data, function () {
                            var empInfo = this.lastName + "-->" + this.birth + "-->" + this.gender
                            $("#showDiv").append(empInfo + "<br>")
                        })
                    }
                })
                return false;
            })
        })
    </script>

</head>
<body>
<%=new Date()%>
<a href="${ctp}/ajaxgetall">获取所有员工</a>
<div id="showDiv"></div>
</body>
</html>
