/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repo;

import Db.DBConnect;
import model.MauGong;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author Gumball
 */
public class MauGongRepository {
    DBConnect dbc = new DBConnect();
    
    public ArrayList<MauGong> getTen(){
        String sql = "SELECT * FROM MauGong";
        ArrayList<MauGong> list = new ArrayList<>();
        try(Connection conn = dbc.getConnection()) {
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                String ma = rs.getString("MaMG");
                String ten = rs.getString("TenMG");
                String moTa = rs.getString("MoTa");
                MauGong mg = new MauGong(ma, ten, moTa);
                list.add(mg);
            }
        } catch (Exception e) {
            
        }
        return list;
    }
    
    public Boolean add(MauGong mg){
        String sql = "INSERT INTO MauGong(TenMG, MoTa) VALUES(?,?);";
        try(Connection conn = dbc.getConnection()) {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setObject(1, mg.getTenMG());
            pst.setObject(2, mg.getMoTaMG());
            int kq = pst.executeUpdate();
            return kq > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public Boolean update(String ma, String ten, String moTa){
        String sql = "UPDATE MauGong SET TenMG = N'"+ten+"', MoTa = N'"+moTa+"' WHERE MaMG LIKE '"+ma+"';";
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
        String sql = "DELETE FROM MauGong WHERE MaMG like N'"+ma+"'";
        try(Connection conn = dbc.getConnection()) {
            PreparedStatement pst = conn.prepareStatement(sql);
            int kq = pst.executeUpdate();
            return kq > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public ArrayList<MauGong> searchTheoMa(String maMG){
        String sql = "SELECT * FROM MauGong WHERE MaMG like N'"+maMG+"'";
        ArrayList<MauGong> list = new ArrayList<>();
        try(Connection conn = dbc.getConnection()) {
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                String ma = rs.getString("MaMG");
                String ten = rs.getString("TenMG");
                String moTa = rs.getString("MoTa");
                MauGong mg = new MauGong(ma, ten, moTa);
                list.add(mg);
            }
        } catch (Exception e) {
            
        }
        return list;
    }
    
    public ArrayList<MauGong> searchTheoTen(String tenMG){
        String sql = "SELECT * FROM MauGong WHERE TenMG like N'"+tenMG+"'";
        ArrayList<MauGong> list = new ArrayList<>();
        try(Connection conn = dbc.getConnection()) {
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                String ma = rs.getString("MaMG");
                String ten = rs.getString("TenMG");
                String moTa = rs.getString("MoTa");
                MauGong mg = new MauGong(ma, ten, moTa);
                list.add(mg);
            }
        } catch (Exception e) {
            
        }
        return list;
    }
}
