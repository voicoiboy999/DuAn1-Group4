/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repo;

import Db.DBConnect;
import model.LoaiMatKinh;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author Gumball
 */
public class LoaiMatKinhRepository {
    DBConnect dbc = new DBConnect();
    
    public ArrayList<LoaiMatKinh> getTen(){
        String sql = "SELECT * FROM LoaiMatKinh";
        ArrayList<LoaiMatKinh> list = new ArrayList<>();
        try(Connection conn = dbc.getConnection()) {
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                String ma = rs.getString("MaLMK");
                String ten = rs.getString("TenLMK");
                String moTa = rs.getString("Mota");
                LoaiMatKinh lmk = new LoaiMatKinh(ma, ten, moTa);
                list.add(lmk);
            }
        } catch (Exception e) {
            
        }
        return list;
    }
    
    public Boolean add(LoaiMatKinh lmk){
        String sql = "INSERT INTO LoaiMatKinh(TenLMK, Mota) VALUES(?,?);";
        try(Connection conn = dbc.getConnection()) {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setObject(1, lmk.getTenLMK());
            pst.setObject(2, lmk.getMoTaLMK());
            int kq = pst.executeUpdate();
            return kq > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public Boolean update(String ma, String ten, String moTa){
        String sql = "UPDATE LoaiMatKinh SET TenLMK = N'"+ten+"', Mota = N'"+moTa+"' WHERE MaLMK LIKE '"+ma+"';";
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
        String sql = "DELETE FROM LoaiMatKinh WHERE MaLMK like N'"+ma+"'";
        try(Connection conn = dbc.getConnection()) {
            PreparedStatement pst = conn.prepareStatement(sql);
            int kq = pst.executeUpdate();
            return kq > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public ArrayList<LoaiMatKinh> searchTheoMa(String maLMK){
        String sql = "SELECT * FROM LoaiMatKinh WHERE MaLMK like N'"+maLMK+"'";
        ArrayList<LoaiMatKinh> list = new ArrayList<>();
        try(Connection conn = dbc.getConnection()) {
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                String ma = rs.getString("MaLMK");
                String ten = rs.getString("TenLMK");
                String moTa = rs.getString("Mota");
                LoaiMatKinh lmk = new LoaiMatKinh(ma, ten, moTa);
                list.add(lmk);
            }
        } catch (Exception e) {
            
        }
        return list;
    }
    
    public ArrayList<LoaiMatKinh> searchTheoTen(String tenLMK){
        String sql = "SELECT * FROM LoaiMatKinh WHERE TenLMK like N'"+tenLMK+"'";
        ArrayList<LoaiMatKinh> list = new ArrayList<>();
        try(Connection conn = dbc.getConnection()) {
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                String ma = rs.getString("MaLMK");
                String ten = rs.getString("TenLMK");
                String moTa = rs.getString("Mota");
                LoaiMatKinh lmk = new LoaiMatKinh(ma, ten, moTa);
                list.add(lmk);
            }
        } catch (Exception e) {
            
        }
        return list;
    }
}
