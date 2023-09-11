package Dao;

import Dto.WifiDto;
import org.sqlite.JDBC;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WifiDao{
    public void insert(WifiDto dto) {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Connection con = null;
        PreparedStatement pr = null;
        ResultSet rs = null;
        String url = "jdbc:sqlite:/Users/yes_truly/IdeaProjects/PublicWifi/identifier.sqlite";


        try {
            con = DriverManager.getConnection(url);
            String sql = "INSERT INTO WIFI VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
            pr = con.prepareStatement(sql);
            pr.setString(1, dto.getWifi_number());
            pr.setString(2, dto.getBorough());
            pr.setString(3, dto.getWifiName());
            pr.setString(4, dto.getStreet());
            pr.setString(5, dto.getDetailAddress());
            pr.setString(6, dto.getInstallFloar());
            pr.setString(7, dto.getInstallType());
            pr.setString(8, dto.getInstallAgancy());
            pr.setString(9, dto.getService());
            pr.setString(10, dto.getNet());
            pr.setInt(11, dto.getYear());
            pr.setString(12, dto.getInOut());
            pr.setString(13, dto.getConnect());
            pr.setDouble(14, dto.getLat());
            pr.setDouble(15, dto.getLnt());
            pr.setString(16, dto.getWorkingDate());

            int affected = pr.executeUpdate();
            if (affected > 0) {
                System.out.println("저장 성공");
            } else {
                System.out.println("저장 실패");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (rs != null && !rs.isClosed()) {
                    rs.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException();
            }
            try {
                if (pr != null && !pr.isClosed()) {
                    pr.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException();
            }
            try {
                if (con != null && !con.isClosed()) {
                    con.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException();
            }
        }
    }

    public List<WifiDto> select(double lat, double lnt){
        List<WifiDto> list = new ArrayList<WifiDto>();

        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Connection con = null;
        PreparedStatement pr = null;
        ResultSet rs = null;
        String url = "jdbc:sqlite:/Users/yes_truly/IdeaProjects/PublicWifi/identifier.sqlite";


        try {
            con = DriverManager.getConnection(url);
            String sql = "SELECT *, " +
                    "round(6371*acos(cos(radians(?))*cos(radians(LAT))*cos(radians(LNT)" +
                    "-radians(?))+sin(radians(?))*sin(radians(LAT))), 4) " +
                    "AS DISTANCE " +
                    "FROM WIFI " +
                    "ORDER BY DISTANCE " +
                    "LIMIT 20;";


            pr = con.prepareStatement(sql);
            pr.setDouble(1,lat);
            pr.setDouble(2, lnt);
            pr.setDouble(3,lat);

            rs = pr.executeQuery();

            while (rs.next()) {
                double distance = rs.getDouble("DISTANCE");
                String wifi_number = rs.getString("X_SWIFI_MGR_NO");
                String borough = rs.getString("X_SWIFI_WRDOFC");
                String wifiName = rs.getString("X_SWIFI_MAIN_NM");
                String street = rs.getString("X_SWIFI_ADRESS1");
                String detailAddress = rs.getString("X_SWIFI_ADRESS2");
                String installFloar = rs.getString("X_SWIFI_INSTL_FLOOR");
                String installType = rs.getString("X_SWIFI_INSTL_TY");
                String installAgency = rs.getString("X_SWIFI_INSTL_MBY");
                String service = rs.getString("X_SWIFI_SVC_SE");
                String net = rs.getString("X_SWIFI_CMCWR");
                int year = rs.getInt("X_SWIFI_CNSTC_YEAR");
                String inOut = rs.getString("X_SWIFI_INOUT_DOOR");
                String connect = rs.getString("X_SWIFI_REMARS3");
                double lat2 = rs.getDouble("LAT");
                double lnt2 = rs.getDouble("LNT");
                String workingDate = rs.getString("WORK_DTTM");

                WifiDto wifiDto = new WifiDto();
                wifiDto.setDistance(distance);
                wifiDto.setWifi_number(wifi_number);
                wifiDto.setBorough(borough);
                wifiDto.setWifiName(wifiName);
                wifiDto.setStreet(street);
                wifiDto.setDetailAddress(detailAddress);
                wifiDto.setInstallFloar(installFloar);
                wifiDto.setInstallType(installType);
                wifiDto.setInstallAgancy(installAgency);
                wifiDto.setService(service);
                wifiDto.setNet(net);
                wifiDto.setYear(year);
                wifiDto.setInOut(inOut);
                wifiDto.setConnect(connect);
                wifiDto.setLat(lat2);
                wifiDto.setLnt(lnt2);
                wifiDto.setWorkingDate(workingDate);

                list.add(wifiDto);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (rs != null && !rs.isClosed()) {
                    rs.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException();
            }
            try {
                if (pr != null && !pr.isClosed()) {
                    pr.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException();
            }
            try {
                if (con != null && !con.isClosed()) {
                    con.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException();
            }
        }
        return list;
    }

    public WifiDto select(String wifiNumber) {
        WifiDto dto = new WifiDto();

        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Connection con = null;
        PreparedStatement pr = null;
        ResultSet rs = null; // ResultSet 초기화

        String url = "jdbc:sqlite:/Users/yes_truly/IdeaProjects/PublicWifi/identifier.sqlite";

        try {
            con = DriverManager.getConnection(url);
            String sql = "SELECT * FROM WIFI WHERE X_SWIFI_MGR_NO=?;";
            pr = con.prepareStatement(sql);
            pr.setString(1, wifiNumber);
            rs = pr.executeQuery(); // ResultSet 초기화

            if (rs.next()) { // ResultSet가 비어있지 않으면 진행
                String wifi_number = rs.getString("X_SWIFI_MGR_NO");
                String borough = rs.getString("X_SWIFI_WRDOFC");
                String wifiName = rs.getString("X_SWIFI_MAIN_NM");
                String street = rs.getString("X_SWIFI_ADRESS1");
                String detailAddress = rs.getString("X_SWIFI_ADRESS2");
                String installFloar = rs.getString("X_SWIFI_INSTL_FLOOR");
                String installType = rs.getString("X_SWIFI_INSTL_TY");
                String installAgency = rs.getString("X_SWIFI_INSTL_MBY");
                String service = rs.getString("X_SWIFI_SVC_SE");
                String net = rs.getString("X_SWIFI_CMCWR");
                int year = rs.getInt("X_SWIFI_CNSTC_YEAR");
                String inOut = rs.getString("X_SWIFI_INOUT_DOOR");
                String connect = rs.getString("X_SWIFI_REMARS3");
                double lat2 = rs.getDouble("LAT");
                double lnt2 = rs.getDouble("LNT");
                String workingDate = rs.getString("WORK_DTTM");

                dto.setWifi_number(wifi_number);
                dto.setBorough(borough);
                dto.setWifiName(wifiName);
                dto.setStreet(street);
                dto.setDetailAddress(detailAddress);
                dto.setInstallFloar(installFloar);
                dto.setInstallType(installType);
                dto.setInstallAgancy(installAgency);
                dto.setService(service);
                dto.setNet(net);
                dto.setYear(year);
                dto.setInOut(inOut);
                dto.setConnect(connect);
                dto.setLat(lat2);
                dto.setLnt(lnt2);
                dto.setWorkingDate(workingDate);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (rs != null && !rs.isClosed()) {
                    rs.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException();
            }
            try {
                if (pr != null && !pr.isClosed()) {
                    pr.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException();
            }
            try {
                if (con != null && !con.isClosed()) {
                    con.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException();
            }
        }
        return dto;
    }


}
