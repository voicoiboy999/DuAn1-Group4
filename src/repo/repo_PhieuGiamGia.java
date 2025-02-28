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
                     SELECT ROW_NUMBER() OVER (ORDER BY maPGG) AS STT, *
                     FROM PhieuGiamGia
                     """;
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                int stt;
                String maPGG;
                String tenPGG;
                double dieuKienGiam;
                double giaGiamToiDa;
                int soLuong;
                Date ngayTao;
                Date ngayBatDau;
                Date ngayKetThuc;
                boolean maHTGG;
                String trangThai;

                stt = rs.getInt(1);
                maPGG = rs.getString(2);
                tenPGG = rs.getString(3);
                dieuKienGiam = rs.getDouble(4);
                giaGiamToiDa = rs.getDouble(5);
                soLuong = rs.getInt(6);
                ngayTao = rs.getDate(7);
                ngayBatDau = rs.getDate(8);
                ngayKetThuc = rs.getDate(9);
                maHTGG = rs.getBoolean(10);
                trangThai = rs.getString(11);

                model_PhieuGiamGia PGG = new model_PhieuGiamGia(stt, maPGG, tenPGG, dieuKienGiam, giaGiamToiDa, soLuong, ngayTao, ngayBatDau, ngayKetThuc, maHTGG, trangThai);

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
                        insert into PhieuGiamGia values (?,?,?,?,?,?,?,?,?,?)
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
            ps.setObject(10, pgg.getTrangThai());

            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public model_PhieuGiamGia check(String ma) {
        model_PhieuGiamGia PhieuGiamGiaa = null;
        String sql = """
		 SELECT ROW_NUMBER() OVER (ORDER BY maPGG) AS STT, *
                     FROM PhieuGiamGia where MaPGG = ?
                     """;
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, ma);
            rs = ps.executeQuery();
          
            while (rs.next()) {
                int stt;
                String maPGG;
                String tenPGG;
                double dieuKienGiam;
                double giaGiamToiDa;
                int soLuong;
                Date ngayTao;
                Date ngayBatDau;
                Date ngayKetThuc;
                boolean maHTGG;
                String trangThai;

                stt = rs.getInt(1);
                maPGG = rs.getString(2);
                tenPGG = rs.getString(3);
                dieuKienGiam = rs.getDouble(4);
                giaGiamToiDa = rs.getDouble(5);
                soLuong = rs.getInt(6);
                ngayTao = rs.getDate(7);
                ngayBatDau = rs.getDate(8);
                ngayKetThuc = rs.getDate(9);
                maHTGG = rs.getBoolean(10);
                trangThai = rs.getString(11);
                PhieuGiamGiaa = new model_PhieuGiamGia(stt, maPGG, tenPGG, dieuKienGiam, giaGiamToiDa, soLuong, ngayTao, ngayBatDau, ngayKetThuc, maHTGG, trangThai);
            }

            return PhieuGiamGiaa;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public int sua(model_PhieuGiamGia mdpgg, String ma) {
        String sql = """
                    update PhieuGiamGia 
                    set MaPGG = ?, TenPGG = ?, DieuKienGiam = ?, GiaGiamToiDa = ?, SoLuong = ?, NgayTao = ?, NgayBatDau = ?, NgayKetThuc = ?, MaHTGG = ?, TrangThai = ?
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
            ps.setObject(10, mdpgg.getTrangThai());
            ps.setObject(11, ma);

            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int SuaTrangThai(String ma, String trangThai) {
        String sql = """
 		update PhieuGiamGia 
                set TrangThai = ?
                where MaPGG = ?                    
                    """;

        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);

            ps.setObject(1, trangThai);
            ps.setObject(2, ma);

            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
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

    public ArrayList<model_PhieuGiamGia> timKiem(String ten, String trangThaiTK, Date ngayBD, Date ngayKT) {
        ArrayList<model_PhieuGiamGia> listTK = new ArrayList<>();
        String sql = """
        SELECT ROW_NUMBER() OVER (ORDER BY maPGG) AS STT, * 
        FROM PhieuGiamGia
        WHERE TenPGG LIKE ?
        AND TrangThai LIKE ?
        AND NgayBatDau >= ?
        AND NgayKetThuc <= ?
                     """;
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, "%" + ten + "%");
            ps.setObject(2, "%" + trangThaiTK + "%");
            ps.setObject(3, ngayBD);
            ps.setObject(4, ngayKT);

            rs = ps.executeQuery();
            while (rs.next()) {
                int stt;
                String maPGG;
                String tenPGG;
                double dieuKienGiam;
                double giaGiamToiDa;
                int soLuong;
                Date ngayTao;
                Date ngayBatDau;
                Date ngayKetThuc;
                boolean maHTGG;
                String trangThai;

                stt = rs.getInt(1);
                maPGG = rs.getString(2);
                tenPGG = rs.getString(3);
                dieuKienGiam = rs.getDouble(4);
                giaGiamToiDa = rs.getDouble(5);
                soLuong = rs.getInt(6);
                ngayTao = rs.getDate(7);
                ngayBatDau = rs.getDate(8);
                ngayKetThuc = rs.getDate(9);
                maHTGG = rs.getBoolean(10);
                trangThai = rs.getString(11);

                model_PhieuGiamGia PGGTK = new model_PhieuGiamGia(stt, maPGG, tenPGG, dieuKienGiam, giaGiamToiDa,
                        soLuong, ngayTao, ngayBatDau, ngayKetThuc, maHTGG, trangThai);

                listTK.add(PGGTK);
            }
            return listTK;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

}
