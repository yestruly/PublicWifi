
<%@ page import="java.util.List" %>
<%@ page import="Dao.WifiDao" %>
<%@ page import="Dto.WifiDto" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>와이파이 정보 구하기</title>

    <style>
        #link-list {
            margin-bottom: 20px;
        }

        #bookmark-list {
            margin-bottom: 5px;
        }

        #bookmark-list select {
            float: left;
            margin-right: 5px;
        }

        #table-list {
            font-family: Arial, Helvetica, sans-serif;
            border-collapse: collapse;
            width: 100%;
        }

        #table-list td, #table-list th {
            border: 1px solid #ddd;
            font-size: 14px;
            padding: 8px;
        }

        #table-list tr:nth-child(odd) {
            background-color: #f2f2f2;
        }

        #table-list th {
            padding-top: 12px;
            padding-bottom: 12px;
            background-color: #04AA6D;
            text-align: center;
            color: white;
            width: 20%;
        }
    </style>
</head>
<body>
<h1>와이파이 정보 구하기</h1>

<div id="link-list">
    <a href="index.jsp">홈</a>
    &#124;
    <a href="history.jsp">위치 히스토리 목록</a>
    &#124;
    <a href="load-wifi.jsp">Open API 와이파이 정보 가져오기</a>
    &#124;

</div>

<%
    String mgrNo = request.getParameter("mgrNo");
    String dist = request.getParameter("dist");

    WifiDao wifiDao = new WifiDao();
    WifiDto wifiDto = wifiDao.select(mgrNo);

%>



<table id="table-list">
    <tr>
        <th>거리(Km)</th>
        <td><%= dist %>
        </td>
    </tr>
    <tr>
        <th>관리번호</th>
        <td><%= wifiDto.getWifi_number() %>
        </td>
    </tr>
    <tr>
        <th>자치구</th>
        <td><%= wifiDto.getBorough() %>
        </td>
    </tr>
    <tr>
        <th>와이파이명</th>
        <td><%= wifiDto.getWifiName() %>
        </td>
    </tr>
    <tr>
        <th>도로명주소</th>
        <td><%= wifiDto.getStreet() %>
        </td>
    </tr>
    <tr>
        <th>상세주소</th>
        <td><%= wifiDto.getDetailAddress() %>
        </td>
    </tr>
    <tr>
        <th>설치위치(층)</th>
        <td><%= wifiDto.getInstallFloar() %>
        </td>
    </tr>
    <tr>
        <th>설치유형</th>
        <td><%= wifiDto.getInstallType() %>
        </td>
    </tr>
    <tr>
        <th>설치기관</th>
        <td><%= wifiDto.getInstallAgancy() %>
        </td>
    </tr>
    <tr>
        <th>서비스구분</th>
        <td><%= wifiDto.getService() %>
        </td>
    </tr>
    <tr>
        <th>망종류</th>
        <td><%= wifiDto.getNet() %>
        </td>
    </tr>
    <tr>
        <th>설치년도</th>
        <td><%= wifiDto.getYear() %>
        </td>
    </tr>
    <tr>
        <th>실내외구분</th>
        <td><%= wifiDto.getInOut() %>
        </td>
    </tr>
    <tr>
        <th>WIFI접속환경</th>
        <td><%= wifiDto.getConnect() %>
        </td>
    </tr>
    <tr>
        <th>X좌표</th>
        <td><%= wifiDto.getLnt() %>
        </td>
    </tr>
    <tr>
        <th>Y좌표</th>
        <td><%= wifiDto.getLat() %>
        </td>
    </tr>
    <tr>
        <th>작업일자</th>
        <td><%= wifiDto.getWorkingDate() %>
        </td>
    </tr>
</table>
</body>
</html>