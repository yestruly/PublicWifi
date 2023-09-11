package Dao;

import Dto.HistoryDto;
import org.sqlite.JDBC;
import org.sqlite.core.DB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HistoryDao extends JDBC {
    public void insert(HistoryDto dto){
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
            String sql = "INSERT INTO HISTORY (LAT, LNT, INQUIRY_DATE) VALUES (?,?,datetime('now','localtime'));";
            pr = con.prepareStatement(sql);
            pr.setDouble(1,dto.getLat());
            pr.setDouble(2,dto.getLnt());
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

    public int delete(int id){
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Connection con = null;
        PreparedStatement pr = null;
        int result = 0;
        String url = "jdbc:sqlite:/Users/yes_truly/IdeaProjects/PublicWifi/identifier.sqlite";

        try {
            con = DriverManager.getConnection(url);
            String sql = "DELETE FROM HISTORY WHERE HISTORY_ID = ?;";
            pr = con.prepareStatement(sql);
            pr.setInt(1,id);

            result = pr.executeUpdate();
            if (result > 0){
                System.out.println("성공");
            }else{
                System.out.println("실패");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
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
        return result;
    }

    public List<HistoryDto> select(){
        List<HistoryDto> list = new ArrayList<HistoryDto>();

        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Connection con = null;
        PreparedStatement pr = null;
        String url = "jdbc:sqlite:/Users/yes_truly/IdeaProjects/PublicWifi/identifier.sqlite";
        ResultSet result = null;

        try{
            con = DriverManager.getConnection(url);
            String sql = "SELECT * FROM HISTORY ORDER BY HISTORY_ID DESC;";
            pr = con.prepareStatement(sql);
            result = pr.executeQuery();


            while (result.next()){
                int id = result.getInt("HISTORY_ID");
                double lat = result.getDouble("LAT");
                double lnt = result.getDouble("LNT");
                Date date = result.getDate("INQUIRY_DATE");

                HistoryDto dto = new HistoryDto();
                dto.setID(id);
                dto.setLat(lat);
                dto.setLnt(lnt);
                dto.setDate(date);

                list.add(dto);
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                if (result!= null && !result.isClosed()) {
                    result.close();
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

    public int count(){
        int cnt = 0;
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Connection con = null;
        PreparedStatement pr = null;
        String url = "jdbc:sqlite:/Users/yes_truly/IdeaProjects/PublicWifi/identifier.sqlite";
        ResultSet result = null;

        try{
            con = DriverManager.getConnection(url);
            String sql = "SELECT COUNT(*) FROM HISTORY;";
            pr = con.prepareStatement(sql);
            result = pr.executeQuery();


            while (result.next()){
                cnt = result.getInt(1);
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                if (result!= null && !result.isClosed()) {
                    result.close();
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


        return cnt;

    }


}
