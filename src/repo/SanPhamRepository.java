/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repo;

import Db.DBConnect;
import model.SanPham;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author Gumball
 */
public class SanPhamRepository {
    DBConnect dbc = new DBConnect();
    
    public ArrayList<SanPham> getTen(){
        String sql = "SELECT * FROM SanPham";
        ArrayList<SanPham> list = new ArrayList<>();
        try(Connection conn = dbc.getConnection()) {
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                String maSP = rs.getString("MaSP");
                String tenSP = rs.getString("TenSP");
                SanPham sp = new SanPham(maSP, tenSP);
                list.add(sp);
            }
        } catch (Exception e) {
            
        }
        return list;
    }
    
    public Boolean add(SanPham sp){
        String sql = "INSERT INTO SanPham(TenSP) VALUES(?);";
        try(Connection conn = dbc.getConnection()) {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setObject(1, sp.getTenSP());
            int kq = pst.executeUpdate();
            return kq > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public Boolean update(String maSP, String tenSP){
        String sql = "UPDATE SanPham SET TenSP =N'"+tenSP+"' WHERE MaSP like N'"+maSP+"'";
        try(Connection conn = dbc.getConnection()) {
            PreparedStatement pst = conn.prepareStatement(sql);
            int kq = pst.executeUpdate();
            return kq > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public Boolean delete(String maSP){
        String sql = "DELETE FROM SanPham WHERE MaSP like '"+maSP+"'";
        try(Connection conn = dbc.getConnection()) {
            PreparedStatement pst = conn.prepareStatement(sql);
            int kq = pst.executeUpdate();
            return kq > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public ArrayList<SanPham> searchTheoMa(String ma){
        String sql = "SELECT * FROM SanPham WHERE MaSP like N'"+ma+"'";
        ArrayList<SanPham> list = new ArrayList<>();
        try(Connection conn = dbc.getConnection()) {
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                String maSP = rs.getString("MaSP");
                String tenSP = rs.getString("TenSP");
                SanPham sp = new SanPham(maSP, tenSP);
                list.add(sp);
            }
        } catch (Exception e) {
            
        }
        return list;
    }
    
    public ArrayList<SanPham> searchTheoTen(String ten){
        String sql = "SELECT * FROM SanPham WHERE TenSP like N'"+ten+"'";
        ArrayList<SanPham> list = new ArrayList<>();
        try(Connection conn = dbc.getConnection()) {
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                String maSP = rs.getString("MaSP");
                String tenSP = rs.getString("TenSP");
                SanPham sp = new SanPham(maSP, tenSP);
                list.add(sp);
            }
        } catch (Exception e) {
            
        }
        return list;
    }
}
