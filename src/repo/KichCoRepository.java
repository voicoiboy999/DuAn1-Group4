/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repo;

import Db.DBConnect;
import model.KichCo;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author Gumball
 */
public class KichCoRepository {
    DBConnect dbc = new DBConnect();
    
    public ArrayList<KichCo> getTen(){
        String sql = "SELECT * FROM KichCo";
        ArrayList<KichCo> list = new ArrayList<>();
        try(Connection conn = dbc.getConnection()) {
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                String ma = rs.getString("MaKC");
                String ten = rs.getString("TenKC");
                String moTa = rs.getString("MoTa");
                KichCo kc = new KichCo(ma, ten, moTa);
                list.add(kc);
            }
        } catch (Exception e) {
            
        }
        return list;
    }
    
    public Boolean add(KichCo kc){
        String sql = "INSERT INTO KichCo(TenKC, MoTa) VALUES(?,?);";
        try(Connection conn = dbc.getConnection()) {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setObject(1, kc.getTenKC());
            pst.setObject(2, kc.getMoTaKC());
            int kq = pst.executeUpdate();
            return kq > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public Boolean update(String ma, String ten, String moTa){
        String sql = "UPDATE KichCo SET TenKC = N'"+ten+"', MoTa = N'"+moTa+"' WHERE MaKC LIKE '"+ma+"';";
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
        String sql = "DELETE FROM KichCo WHERE MaKC like N'"+ma+"'";
        try(Connection conn = dbc.getConnection()) {
            PreparedStatement pst = conn.prepareStatement(sql);
            int kq = pst.executeUpdate();
            return kq > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public ArrayList<KichCo> searchTheoTen(String tenKC){
        String sql = "SELECT * FROM KichCo WHERE TenKC like N'"+tenKC+"'";
        ArrayList<KichCo> list = new ArrayList<>();
        try(Connection conn = dbc.getConnection()) {
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                String ma = rs.getString("MaKC");
                String ten = rs.getString("TenKC");
                String moTa = rs.getString("MoTa");
                KichCo kc = new KichCo(ma, ten, moTa);
                list.add(kc);
            }
        } catch (Exception e) {
            
        }
        return list;
    }
    
    public ArrayList<KichCo> searchTheoMa(String maKC){
        String sql = "SELECT * FROM KichCo WHERE MaKC like N'"+maKC+"'";
        ArrayList<KichCo> list = new ArrayList<>();
        try(Connection conn = dbc.getConnection()) {
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                String ma = rs.getString("MaKC");
                String ten = rs.getString("TenKC");
                String moTa = rs.getString("MoTa");
                KichCo kc = new KichCo(ma, ten, moTa);
                list.add(kc);
            }
        } catch (Exception e) {
            
        }
        return list;
    }
}
