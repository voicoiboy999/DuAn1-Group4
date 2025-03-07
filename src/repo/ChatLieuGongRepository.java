/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repo;

import Db.DBConnect;
import model.ChatLieuGong;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author Gumball
 */
public class ChatLieuGongRepository {
    DBConnect dbc = new DBConnect();
    
    public ArrayList<ChatLieuGong> getTen(){
        String sql = "SELECT * FROM ChatLieuGong";
        ArrayList<ChatLieuGong> list = new ArrayList<>();
        try(Connection conn = dbc.getConnection()) {
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                String maCLG = rs.getString("MaCLG");
                String tenCLG = rs.getString("TenCLG");
                String moTaCLG = rs.getString("MoTa");
                ChatLieuGong clg = new ChatLieuGong(maCLG, tenCLG, moTaCLG);
                list.add(clg);
            }
        } catch (Exception e) {
            
        }
        return list;
    }
    
    public Boolean add(ChatLieuGong clg){
        String sql = "INSERT INTO ChatLieuGong(TenCLG, MoTa) VALUES(?,?);";
        try(Connection conn = dbc.getConnection()) {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setObject(1, clg.getTenCLG());
            pst.setObject(2, clg.getMoTaCLG());
            int kq = pst.executeUpdate();
            return kq > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public Boolean update(String maCLG, String tenCLG, String moTa){
        String sql = "UPDATE ChatLieuGong SET TenCLG = N'"+tenCLG+"', MoTa = N'"+moTa+"' WHERE MaCLG LIKE '"+maCLG+"';";
        try(Connection conn = dbc.getConnection()) {
            PreparedStatement pst = conn.prepareStatement(sql);
            int kq = pst.executeUpdate();
            return kq > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public Boolean delete(String maCLG){
        String sql = "DELETE FROM ChatLieuGong WHERE MaCLG like N'"+maCLG+"'";
        try(Connection conn = dbc.getConnection()) {
            PreparedStatement pst = conn.prepareStatement(sql);
            int kq = pst.executeUpdate();
            return kq > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public ArrayList<ChatLieuGong> searchTheoTen(String ten){
        String sql = "SELECT * FROM ChatLieuGong WHERE TenCLG LIKE N'"+ten+"'";
        ArrayList<ChatLieuGong> list = new ArrayList<>();
        try(Connection conn = dbc.getConnection()) {
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                String maCLG = rs.getString("MaCLG");
                String tenCLG = rs.getString("TenCLG");
                String moTaCLG = rs.getString("MoTa");
                ChatLieuGong clg = new ChatLieuGong(maCLG, tenCLG, moTaCLG);
                list.add(clg);
            }
        } catch (Exception e) {
            
        }
        return list;
    }
    
    public ArrayList<ChatLieuGong> searchTheoMa(String ma){
        String sql = "SELECT * FROM ChatLieuGong WHERE MaCLG LIKE N'"+ma+"'";
        ArrayList<ChatLieuGong> list = new ArrayList<>();
        try(Connection conn = dbc.getConnection()) {
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                String maCLG = rs.getString("MaCLG");
                String tenCLG = rs.getString("TenCLG");
                String moTaCLG = rs.getString("MoTa");
                ChatLieuGong clg = new ChatLieuGong(maCLG, tenCLG, moTaCLG);
                list.add(clg);
            }
        } catch (Exception e) {
            
        }
        return list;
    }
    
}
