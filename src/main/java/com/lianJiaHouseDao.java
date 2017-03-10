package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class lianJiaHouseDao {

    private static Connection conn = null;
    private Statement stmt = null;

    public lianJiaHouseDao() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/lianjiahouse?user=root&password=root";
            conn = DriverManager.getConnection(url);
            stmt = conn.createStatement();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public int add(lianJiaHouse lianJiaHouse) {
        try {
            String sql = "INSERT INTO `houseinfo` (`houseId`, `title`, `price`, `area`, `longitude`, `createdate`, `category`,`state`) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, lianJiaHouse.getId());
            ps.setString(2, lianJiaHouse.getTitle());
            ps.setInt(3, lianJiaHouse.getPrice());
            ps.setInt(4, lianJiaHouse.getArea());
            ps.setString(5, lianJiaHouse.getLongitude());
            ps.setString(6, lianJiaHouse.getCreatedate());
            ps.setString(7, lianJiaHouse.getCategory());
            ps.setString(8, lianJiaHouse.getState());
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public List<String> getAll() {
        String sql = "select `longitude`,`price` from houseinfo ";
        PreparedStatement pstmt;
        List<String> houselist = new ArrayList<String>();
        try {
            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            int col = rs.getMetaData().getColumnCount();
            while (rs.next()) {
                for (int i = 1; i <= col; i++) {
                    houselist.add(rs.getString(i));
                    // System.out.print(rs.getString(i));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return houselist;
    }

}