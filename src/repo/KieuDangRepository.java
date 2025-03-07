/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repo;

import Db.DBConnect;
import java.sql.*;
import model.KieuDang;
import java.util.ArrayList;

/**
 *
 * @author Gumball
 */
public class KieuDangRepository {
    DBConnect dbc = new DBConnect();
    
    public ArrayList<KieuDang> getTen(){
        String sql = "SELECT * FROM KieuDang";
        ArrayList<KieuDang> list = new ArrayList<>();
        try(Connection conn = dbc.getConnection()) {
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                String ma = rs.getString("MaKD");
                String ten = rs.getString("TenKD");
                String moTa = rs.getString("MoTa");
                KieuDang kd = new KieuDang(ma, ten, moTa);
                list.add(kd);
            }
        } catch (Exception e) {
            
        }
        return list;
    }
    
    public Boolean add(KieuDang kd){
        String sql = "INSERT INTO KieuDang(TenKD, MoTa) VALUES(?,?);";
        try(Connection conn = dbc.getConnection()) {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setObject(1, kd.getTenKD());
            pst.setObject(2, kd.getMoTaKD());
            int kq = pst.executeUpdate();
            return kq > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public Boolean update(String ma, String ten, String moTa){
        String sql = "UPDATE KieuDang SET TenKD = N'"+ten+"', MoTa = N'"+moTa+"' WHERE MaKD LIKE '"+ma+"';";
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
        String sql = "DELETE FROM KieuDang WHERE MaKD like N'"+ma+"'";
        try(Connection conn = dbc.getConnection()) {
            PreparedStatement pst = conn.prepareStatement(sql);
            int kq = pst.executeUpdate();
            return kq > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public ArrayList<KieuDang> searchTheoMa(String maKD){
        String sql = "SELECT * FROM KieuDang WHERE MaKD like N'"+maKD+"'";
        ArrayList<KieuDang> list = new ArrayList<>();
        try(Connection conn = dbc.getConnection()) {
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                String ma = rs.getString("MaKD");
                String ten = rs.getString("TenKD");
                String moTa = rs.getString("MoTa");
                KieuDang kd = new KieuDang(ma, ten, moTa);
                list.add(kd);
            }
        } catch (Exception e) {
            
        }
        return list;
    }
    
    public ArrayList<KieuDang> searchTheoTen(String tenKD){
        String sql = "SELECT * FROM KieuDang WHERE TenKD like N'"+tenKD+"'";
        ArrayList<KieuDang> list = new ArrayList<>();
        try(Connection conn = dbc.getConnection()) {
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                String ma = rs.getString("MaKD");
                String ten = rs.getString("TenKD");
                String moTa = rs.getString("MoTa");
                KieuDang kd = new KieuDang(ma, ten, moTa);
                list.add(kd);
            }
        } catch (Exception e) {
            
        }
        return list;
    }
}
