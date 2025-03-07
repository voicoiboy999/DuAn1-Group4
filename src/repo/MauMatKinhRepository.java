/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repo;

import Db.DBConnect;
import model.MauMatKinh;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author Gumball
 */
public class MauMatKinhRepository {
    DBConnect dbc = new DBConnect();
    
    public ArrayList<MauMatKinh> getTen(){
        String sql = "SELECT * FROM MauMatKinh";
        ArrayList<MauMatKinh> list = new ArrayList<>();
        try(Connection conn = dbc.getConnection()) {
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                String ma = rs.getString("MaMMK");
                String ten = rs.getString("TenMMK");
                String moTa = rs.getString("MoTa");
                MauMatKinh mmk = new MauMatKinh(ma, ten, moTa);
                list.add(mmk);
            }
        } catch (Exception e) {
            
        }
        return list;
    }
    
    public Boolean add(MauMatKinh mmk){
        String sql = "INSERT INTO MauMatKinh(TenMMK, MoTa) VALUES(?,?);";
        try(Connection conn = dbc.getConnection()) {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setObject(1, mmk.getTenMMK());
            pst.setObject(2, mmk.getMoTaMMK());
            int kq = pst.executeUpdate();
            return kq > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public Boolean update(String ma, String ten, String moTa){
        String sql = "UPDATE MauMatKinh SET TenMMK = N'"+ten+"', MoTa = N'"+moTa+"' WHERE MaMMK LIKE '"+ma+"';";
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
        String sql = "DELETE FROM MauMatKinh WHERE MaMMK like N'"+ma+"'";
        try(Connection conn = dbc.getConnection()) {
            PreparedStatement pst = conn.prepareStatement(sql);
            int kq = pst.executeUpdate();
            return kq > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public ArrayList<MauMatKinh> searchTheoMa(String maMMK){
        String sql = "SELECT * FROM MauMatKinh WHERE MaMMK like N'"+maMMK+"'";
        ArrayList<MauMatKinh> list = new ArrayList<>();
        try(Connection conn = dbc.getConnection()) {
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                String ma = rs.getString("MaMMK");
                String ten = rs.getString("TenMMK");
                String moTa = rs.getString("MoTa");
                MauMatKinh mmk = new MauMatKinh(ma, ten, moTa);
                list.add(mmk);
            }
        } catch (Exception e) {
            
        }
        return list;
    }
    
    public ArrayList<MauMatKinh> searchTheoTen(String tenMMK){
        String sql = "SELECT * FROM MauMatKinh WHERE TenMMK like N'"+tenMMK+"'";
        ArrayList<MauMatKinh> list = new ArrayList<>();
        try(Connection conn = dbc.getConnection()) {
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                String ma = rs.getString("MaMMK");
                String ten = rs.getString("TenMMK");
                String moTa = rs.getString("MoTa");
                MauMatKinh mmk = new MauMatKinh(ma, ten, moTa);
                list.add(mmk);
            }
        } catch (Exception e) {
            
        }
        return list;
    }
}
