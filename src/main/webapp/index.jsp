<%@ page import="Dto.HistoryDto" %>
<%@ page import="Dao.HistoryDao" %>
<%@ page import="Dto.WifiDto" %>
<%@ page import="Dao.WifiDao" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<% response.setCharacterEncoding("UTF-8"); %>
<meta charset="UTF-8">

<html>
<head>
    <meta charset="UTF-8">
    <title>와이파이 정보 구하기</title>
    <style>
        #wifi_list {
            font-family: Arial, Helvetica, sans-serif;
            border-collapse: collapse;
            width: 100%;
        }

        #wifi_list td, #wifi_list th {
            border: 1px solid #ddd;
            text-align: center;
            font-size: 14px;
            padding: 8px;
        }

        #wifi_list tr:nth-child(odd) {
            background-color: #f2f2f2;
        }

        #wifi_list tr:hover {
            background-color: #ddd;
        }

        #wifi_list th {
            padding-top: 12px;
            padding-bottom: 12px;
            background-color: #04AA6D;
            color: white;
        }
    </style>
</head>

<body>
<h1>와이파이 정보 구하기</h1>
<div id = "select">
    <a href="index.jsp">홈</a> |
    <a href="history.jsp">위치 히스토리 목록</a> |
    <a href="getWifi.jsp">OpenApi 와이파이 정보 가져오기</a> |

</div>

<form action = "index.jsp" method="get">
    LAT : <input type = "text" name = "lat" id="lat" />
    LNT : <input type = "text" name = "lnt" id="lnt" />
    <input type = "button" value = "내 위치 가져오기" onclick = "loca();">
    <input type = "submit" value = "근처 WIFI 정보 보기">
</form>

<table id="wifi_list">
    <thead>
    <tr>
        <th>거리(Km) | </th>
        <th>관리번호</th>
        <th>자치구</th>
        <th>와이파이명</th>
        <th>도로명주소</th>
        <th>상세주소</th>
        <th>설치위치(층)</th>
        <th>설치유형</th>
        <th>설치기관</th>
        <th>서비스구분</th>
        <th>망종류</th>
        <th>설치년도</th>
        <th>실내외구분</th>
        <th>WIFI접속환경</th>
        <th>X좌표</th>
        <th>Y좌표</th>
        <th>작업일자</th>
    </tr>
    </thead>
    <tbody>
    <%
        String lat = request.getParameter("lat");
        String lnt = request.getParameter("lnt");

        double latVal = 0.0;
        double lntVal = 0.0;

        if(lat != null && !lat.isEmpty()){
            try {
                latVal = Double.parseDouble(lat);
            }catch (NumberFormatException e){
                e.printStackTrace();
            }
        }

        if(lnt != null && !lnt.isEmpty()){
            try {
                lntVal = Double.parseDouble(lnt);
            }catch (NumberFormatException e){
                e.printStackTrace();
            }
        }
        if(latVal == 0.0 && lntVal == 0.0){
    %>
    <tr>
        <td colspan="17">위치 정보를 입력한 후에 조회해 주세요.</td>
    </tr>
    <%
    } else {
        HistoryDto historyDto = new HistoryDto();
        historyDto.setLat(latVal);
        historyDto.setLnt(lntVal);

        HistoryDao historyDao = new HistoryDao();
        historyDao.insert(historyDto);

        WifiDao wifiDao = new WifiDao();
        List<WifiDto> wifiDaoList = wifiDao.select(lntVal, latVal);

        for (WifiDto item : wifiDaoList) {
    %>
    <tr>
        <td>
            <%= item.getDistance() %>
        </td>
        <td>
            <%= item.getWifi_number() %>
        </td>
        <td>
            <a href="detail.jsp?mgrNo=<%= item.getWifi_number() %>&dist=<%= item.getDistance() %>">
                <%= item.getBorough() %>
            </a>
        </td>
        <td>
            <%= item.getWifiName() %>
        </td>
        <td>
            <%= item.getStreet() %>
        </td>
        <td>
            <%= item.getDetailAddress() %>
        </td>
        <td>
            <%= item.getInstallFloar() %>
        </td>
        <td>
            <%= item.getInstallType() %>
        </td>
        <td>
            <%= item.getInstallAgancy() %>
        </td>
        <td>
            <%= item.getService() %>
        </td>
        <td>
            <%= item.getNet() %>
        </td>
        <td>
            <%= item.getYear() %>
        </td>
        <td>
            <%= item.getInOut() %>
        </td>
        <td>
            <%= item.getConnect() %>
        </td>
        <td>
            <%= item.getLnt() %>
        </td>
        <td>
            <%= item.getLat() %>
        </td>
        <td>
            <%= item.getWorkingDate() %>
        </td>
    </tr>
    <%
            }
        }
    %>

    </tbody>
</table>
<script>
    const urlParameter = new URLSearchParams(window.location.search)
    const lnt = urlParameter.get("lnt")
    const lat = urlParameter.get("lat")

    if(lnt){
        const lntTmp = document.getElementById("lnt")
        lntTmp.setAttribute("value", lnt)

    }

    if(lat){
        const lntTmp = document.getElementById("lat")
        lntTmp.setAttribute("value", lat)
    }

    function loca(){
        if(navigator.geolocation){
            navigator.geolocation.getCurrentPosition(show);
        }else{
            alert("FAIL");
        }
    }

    function show(position){
        const lat = position.coords.latitude;
        const lnt = position.coords.longitude;

        document.getElementById("lat").value = lat;
        document.getElementById("lnt").value = lnt;
    }


</script>
</body>

</html>

