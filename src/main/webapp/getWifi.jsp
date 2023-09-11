<%@ page import="Service.UseWifi" %><%--
  Created by IntelliJ IDEA.
  User: yes_truly
  Date: 2023-09-02
  Time: 오후 10:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>와이파이 정보 구하기</title>
</head>
<style>
    *{
        text-align: center;
    }
</style>
<body>
<% UseWifi useWifi = new UseWifi();
useWifi.save();

%>
<h1><%= useWifi.count() %>개의 Wifi 정보를 저장했습니다.</h1>
<p><a href="index.jsp">홈으로 돌아가기</a></p>
</body>
</html>
