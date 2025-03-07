/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repo;

import Db.DBConnect;
import model.ChatLieuMatKinh;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author Gumball
 */
public class ChatLieuMatKinhRepository {
    DBConnect dbc = new DBConnect();
    
    public ArrayList<ChatLieuMatKinh> getTen(){
        String sql = "SELECT * FROM ChatLieuMatKinh";
        ArrayList<ChatLieuMatKinh> list = new ArrayList<>();
        try(Connection conn = dbc.getConnection()) {
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                String ma = rs.getString("MaCLMK");
                String ten = rs.getString("TenCLMK");
                String moTa = rs.getString("MoTa");
                ChatLieuMatKinh clmk = new ChatLieuMatKinh(ma, ten, moTa);
                list.add(clmk);
            }
        } catch (Exception e) {
            
        }
        return list;
    }
    
    public Boolean add(ChatLieuMatKinh clmk){
        String sql = "INSERT INTO ChatLieuMatKinh(TenCLMK, MoTa) VALUES(?,?);";
        try(Connection conn = dbc.getConnection()) {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setObject(1, clmk.getTenCLML());
            pst.setObject(2, clmk.getMoTaCLMK());
            int kq = pst.executeUpdate();
            return kq > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public Boolean update(String ma, String ten, String moTa){
        String sql = "UPDATE ChatLieuMatKinh SET TenCLMK = N'"+ten+"', MoTa = N'"+moTa+"' WHERE MaCLMK LIKE '"+ma+"';";
        try(Connection conn = dbc.getConnection()) {
            PreparedStatement pst = conn.prepareStatement(sql);
            int kq = pst.executeUpdate();
            return kq > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public Boolean delete(String ma){
        String sql = "DELETE FROM ChatLieuMatKinh WHERE MaCLMK like N'"+ma+"'";
        try(Connection conn = dbc.getConnection()) {
            PreparedStatement pst = conn.prepareStatement(sql);
            int kq = pst.executeUpdate();
            return kq > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public ArrayList<ChatLieuMatKinh> searchTheoTen(String tenCLMK){
        String sql = "SELECT * FROM ChatLieuMatKinh WHERE TenCLMK LIKE '"+tenCLMK+"';";
        ArrayList<ChatLieuMatKinh> list = new ArrayList<>();
        try(Connection conn = dbc.getConnection()) {
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                String ma = rs.getString("MaCLMK");
                String ten = rs.getString("TenCLMK");
                String moTa = rs.getString("MoTa");
                ChatLieuMatKinh clmk = new ChatLieuMatKinh(ma, ten, moTa);
                list.add(clmk);
            }
        } catch (Exception e) {
            
        }
        return list;
    }
    
    public ArrayList<ChatLieuMatKinh> searchTheoMa(String maCLMK){
        String sql = "SELECT * FROM ChatLieuMatKinh WHERE MaCLMK LIKE '"+maCLMK+"';";
        ArrayList<ChatLieuMatKinh> list = new ArrayList<>();
        try(Connection conn = dbc.getConnection()) {
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                String ma = rs.getString("MaCLMK");
                String ten = rs.getString("TenCLMK");
                String moTa = rs.getString("MoTa");
                ChatLieuMatKinh clmk = new ChatLieuMatKinh(ma, ten, moTa);
                list.add(clmk);
            }
        } catch (Exception e) {
            
        }
        return list;
    }
}
