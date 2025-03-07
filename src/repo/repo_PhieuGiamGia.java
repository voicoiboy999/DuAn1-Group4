/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repo;

import Db.DBConnect;
import java.sql.*;
import java.util.ArrayList;
import model.model_PhieuGiamGia;
import java.util.Date;

public class repo_PhieuGiamGia {

    private Connection con = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    public ArrayList<model_PhieuGiamGia> getAll() {
        ArrayList<model_PhieuGiamGia> listPGG = new ArrayList<>();
        String sql = """
                     select MaPGG, TenPGG,DieuKienGiam,GiaGiamToiDa,SoLuong,NgayTao,NgayBatDau,NgayKetThuc,MaHTGG from PhieuGiamGia
                     """;
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                String maPGG;
                String tenPGG;
                double dieuKienGiam;
                double giaGiamToiDa;
                int soLuong;
                Date ngayTao;
                Date ngayBatDau;
                Date ngayKetThuc;
                boolean maHTGG;

                maPGG = rs.getString(1);
                tenPGG = rs.getString(2);
                dieuKienGiam = rs.getDouble(3);
                giaGiamToiDa = rs.getDouble(4);
                soLuong = rs.getInt(5);
                ngayTao = rs.getDate(6);
                ngayBatDau = rs.getDate(7);
                ngayKetThuc = rs.getDate(8);
                maHTGG = rs.getBoolean(9);

                model_PhieuGiamGia PGG = new model_PhieuGiamGia(maPGG, tenPGG, dieuKienGiam, giaGiamToiDa, soLuong, ngayTao, ngayBatDau, ngayKetThuc, maHTGG);

                listPGG.add(PGG);
            }
            return listPGG;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public int them(model_PhieuGiamGia pgg) {
        String sql = """
                        insert into PhieuGiamGia values (?,?,?,?,?,?,?,?,?)
                     """;
        try {

            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);

            ps.setObject(1, pgg.getMaPGG());
            ps.setObject(2, pgg.getTenPGG());
            ps.setObject(3, pgg.getDieuKienGiam());
            ps.setObject(4, pgg.getGiaGiamToiDa());
            ps.setObject(5, pgg.getSoLuong());
            ps.setObject(6, pgg.getNgayTao());
            ps.setObject(7, pgg.getNgayBatDau());
            ps.setObject(8, pgg.getNgayKetThuc());
            ps.setObject(9, pgg.isMaHTGG());

            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public model_PhieuGiamGia check(String ma) {
        model_PhieuGiamGia PhieuGiamGiaa = null;
        String sql = """
                     select MaPGG, TenPGG,DieuKienGiam,GiaGiamToiDa,SoLuong,NgayTao,NgayBatDau,NgayKetThuc,MaHTGG from PhieuGiamGia where MaPGG = ?
                     """;
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, ma);
            rs = ps.executeQuery();
            rs.next();

            String maPGG;
            String tenPGG;
            double dieuKienGiam;
            double giaGiamToiDa;
            int soLuong;
            Date ngayTao;
            Date ngayBatDau;
            Date ngayKetThuc;
            boolean maHTGG;

            maPGG = rs.getString(1);
            tenPGG = rs.getString(2);
            dieuKienGiam = rs.getDouble(3);
            giaGiamToiDa = rs.getDouble(4);
            soLuong = rs.getInt(5);
            ngayTao = rs.getDate(6);
            ngayBatDau = rs.getDate(7);
            ngayKetThuc = rs.getDate(8);
            maHTGG = rs.getBoolean(9);

            PhieuGiamGiaa = new model_PhieuGiamGia(maPGG, tenPGG, dieuKienGiam, giaGiamToiDa, soLuong, ngayTao, ngayBatDau, ngayKetThuc, maHTGG);

            return PhieuGiamGiaa;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public int xoa(String ma) {
        String sql = """
                      delete from PhieuGiamGia where MaPGG = ?
                      """;
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, ma);
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }

    }

    public int sua(model_PhieuGiamGia mdpgg, String ma) {
        String sql = """
                    update PhieuGiamGia 
                    set MaPGG = ?, TenPGG = ?, DieuKienGiam = ?, GiaGiamToiDa = ?, SoLuong = ?, NgayTao = ?, NgayBatDau = ?, NgayKetThuc = ?, MaHTGG = ?
                    where MaPGG = ?
                    """;

        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);

            ps.setObject(1, mdpgg.getMaPGG());
            ps.setObject(2, mdpgg.getTenPGG());
            ps.setObject(3, mdpgg.getDieuKienGiam());
            ps.setObject(4, mdpgg.getGiaGiamToiDa());
            ps.setObject(5, mdpgg.getSoLuong());
            ps.setObject(6, mdpgg.getNgayTao());
            ps.setObject(7, mdpgg.getNgayBatDau());
            ps.setObject(8, mdpgg.getNgayKetThuc());
            ps.setObject(9, mdpgg.isMaHTGG());
            ps.setObject(10, ma);

            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public ArrayList<model_PhieuGiamGia> timKiem(String ma) {
        ArrayList<model_PhieuGiamGia> listPGGTK = new ArrayList<>();
        String sql = """
                     select MaPGG, TenPGG,DieuKienGiam,GiaGiamToiDa,SoLuong,NgayTao,NgayBatDau,NgayKetThuc,MaHTGG from PhieuGiamGia where MaPGG = ?
                     """;
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, ma);
            rs = ps.executeQuery();
            while (rs.next()) {
                String maPGG;
                String tenPGG;
                double dieuKienGiam;
                double giaGiamToiDa;
                int soLuong;
                Date ngayTao;
                Date ngayBatDau;
                Date ngayKetThuc;
                boolean maHTGG;

                maPGG = rs.getString(1);
                tenPGG = rs.getString(2);
                dieuKienGiam = rs.getDouble(3);
                giaGiamToiDa = rs.getDouble(4);
                soLuong = rs.getInt(5);
                ngayTao = rs.getDate(6);
                ngayBatDau = rs.getDate(7);
                ngayKetThuc = rs.getDate(8);
                maHTGG = rs.getBoolean(9);

                model_PhieuGiamGia phieuGG = new model_PhieuGiamGia(maPGG, tenPGG, dieuKienGiam, giaGiamToiDa, soLuong, ngayTao, ngayBatDau, ngayKetThuc, maHTGG);

                listPGGTK.add(phieuGG);
            }
            return listPGGTK;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        
    }
        
        
                

    
    public ArrayList<model_PhieuGiamGia> timKiemNT(Date ngayBD, Date ngayKT){
        ArrayList<model_PhieuGiamGia> listTKNT = new ArrayList<>();
        String sql = """
                     select MaPGG, TenPGG,DieuKienGiam,GiaGiamToiDa,SoLuong,NgayTao,NgayBatDau,NgayKetThuc,MaHTGG from PhieuGiamGia 
                      where  NgayBatDau >= ? 
                     AND NgayKetThuc <= ?;
                     """;
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, ngayBD);
            ps.setObject(2, ngayKT);
            rs = ps.executeQuery();
            while (rs.next()) {
                String maPGG;
                String tenPGG;
                double dieuKienGiam;
                double giaGiamToiDa;
                int soLuong;
                Date ngayTao;
                Date ngayBatDau;
                Date ngayKetThuc;
                boolean maHTGG;

                maPGG = rs.getString(1);
                tenPGG = rs.getString(2);
                dieuKienGiam = rs.getDouble(3);
                giaGiamToiDa = rs.getDouble(4);
                soLuong = rs.getInt(5);
                ngayTao = rs.getDate(6);
                ngayBatDau = rs.getDate(7);
                ngayKetThuc = rs.getDate(8);
                maHTGG = rs.getBoolean(9);

                model_PhieuGiamGia phieuGGTK = new model_PhieuGiamGia(maPGG, tenPGG, dieuKienGiam, giaGiamToiDa, soLuong, ngayTao, ngayBatDau, ngayKetThuc, maHTGG);

                listTKNT.add(phieuGGTK);
            }
            return listTKNT;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    
    public ArrayList<model_PhieuGiamGia> locHinhThuc(int loai) {
        ArrayList<model_PhieuGiamGia> listLoc = new ArrayList<>();
        String sql = """
                     select MaPGG, TenPGG,DieuKienGiam,GiaGiamToiDa,SoLuong,NgayTao,NgayBatDau,NgayKetThuc,MaHTGG from PhieuGiamGia where MaHTGG = ?
                     """;
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, loai);
            rs = ps.executeQuery();
            while (rs.next()) {
                String maPGG;
                String tenPGG;
                double dieuKienGiam;
                double giaGiamToiDa;
                int soLuong;
                Date ngayTao;
                Date ngayBatDau;
                Date ngayKetThuc;
                boolean maHTGG;

                maPGG = rs.getString(1);
                tenPGG = rs.getString(2);
                dieuKienGiam = rs.getDouble(3);
                giaGiamToiDa = rs.getDouble(4);
                soLuong = rs.getInt(5);
                ngayTao = rs.getDate(6);
                ngayBatDau = rs.getDate(7);
                ngayKetThuc = rs.getDate(8);
                maHTGG = rs.getBoolean(9);

                model_PhieuGiamGia phieuGG = new model_PhieuGiamGia(maPGG, tenPGG, dieuKienGiam, giaGiamToiDa, soLuong, ngayTao, ngayBatDau, ngayKetThuc, maHTGG);

                listLoc.add(phieuGG);
            }
            return listLoc;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        
    }
}
