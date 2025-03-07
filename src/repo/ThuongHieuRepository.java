/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repo;

import Db.DBConnect;
import model.ThuongHieu;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author Gumball
 */
public class ThuongHieuRepository {
    DBConnect dbc = new DBConnect();
    
    public ArrayList<ThuongHieu> getTen(){
        String sql = "SELECT * FROM ThuongHieu";
        ArrayList<ThuongHieu> list = new ArrayList<>();
        try(Connection conn = dbc.getConnection()) {
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                String ma = rs.getString("MaTH");
                String ten = rs.getString("TenTH");
                String moTa = rs.getString("MoTa");
                ThuongHieu th = new ThuongHieu(ma, ten, moTa);
                list.add(th);
            }
        } catch (Exception e) {
            
        }
        return list;
    }
    
    public Boolean add(ThuongHieu th){
        String sql = "INSERT INTO ThuongHieu(TenTH, MoTa) VALUES(?,?);";
        try(Connection conn = dbc.getConnection()) {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setObject(1, th.getTenTH());
            pst.setObject(2, th.getMoTaTH());
            int kq = pst.executeUpdate();
            return kq > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public Boolean update(String ma, String ten, String moTa){
        String sql = "UPDATE ThuongHieu SET TenTH = N'"+ten+"', MoTa = N'"+moTa+"' WHERE MaTH LIKE '"+ma+"';";
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
        String sql = "DELETE FROM ThuongHieu WHERE MaTH like N'"+ma+"'";
        try(Connection conn = dbc.getConnection()) {
            PreparedStatement pst = conn.prepareStatement(sql);
            int kq = pst.executeUpdate();
            return kq > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public ArrayList<ThuongHieu> searchTheoMa(String maTH){
        String sql = "SELECT * FROM ThuongHieu WHERE MaTH like N'"+maTH+"'";
        ArrayList<ThuongHieu> list = new ArrayList<>();
        try(Connection conn = dbc.getConnection()) {
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                String ma = rs.getString("MaTH");
                String ten = rs.getString("TenTH");
                String moTa = rs.getString("MoTa");
                ThuongHieu th = new ThuongHieu(ma, ten, moTa);
                list.add(th);
            }
        } catch (Exception e) {
            
        }
        return list;
    }
    
    public ArrayList<ThuongHieu> searchTheoTen(String tenTH){
        String sql = "SELECT * FROM ThuongHieu WHERE TenTH like N'"+tenTH+"'";
        ArrayList<ThuongHieu> list = new ArrayList<>();
        try(Connection conn = dbc.getConnection()) {
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                String ma = rs.getString("MaTH");
                String ten = rs.getString("TenTH");
                String moTa = rs.getString("MoTa");
                ThuongHieu th = new ThuongHieu(ma, ten, moTa);
                list.add(th);
            }
        } catch (Exception e) {
            
        }
        return list;
    }
}
