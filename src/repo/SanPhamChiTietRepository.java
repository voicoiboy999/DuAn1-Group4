/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repo;

import Db.DBConnect;
import model.SanPhamChiTiet;
import java.sql.*;
import java.util.ArrayList;
import model.SanPhamChiTietJoin;

/**
 *
 * @author Gumball
 */
public class SanPhamChiTietRepository {

    DBConnect dbc = new DBConnect();

    public ArrayList<SanPhamChiTiet> getTen() {
        String sql = "SELECT * FROM SanPhamChiTiet";
        ArrayList<SanPhamChiTiet> list = new ArrayList<>();
        try (Connection conn = dbc.getConnection()) {
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Integer MaSPCT = rs.getInt("MaSPCT");
                String MaSP = rs.getString("MaSP");
                String MaKD = rs.getString("MaKD");
                String MaLMK = rs.getString("MaLMK");
                String MaMMK = rs.getString("MaMMK");
                String MaCLMK = rs.getString("MaCLMK");
                String MaCLG = rs.getString("MaCLG");
                String MaMG = rs.getString("MaMG");
                String MaKC = rs.getString("MaKC");
                String MaTH = rs.getString("MaTH");
                Integer soLuong = rs.getInt("SoLuong");
                Double giaBan = rs.getDouble("GiaBan");
                Date ngayNhap = rs.getDate("NgayNhap");
                Boolean trangThai = rs.getBoolean("TrangThai");
                SanPhamChiTiet spct = new SanPhamChiTiet(MaSPCT, MaSP, MaKD, MaLMK, MaMMK, MaCLMK, MaCLG, MaMG, MaKC, MaTH, soLuong, giaBan, ngayNhap, trangThai);
                list.add(spct);
            }
        } catch (Exception e) {

        }
        return list;
    }

    public ArrayList<SanPhamChiTietJoin> getAll() {
        String sql = "SELECT spct.MaSPCT,sp.TenSP,kd.TenKD,lmk.TenLMK,mmk.TenMMK,clmk.TenCLMK,clg.TenCLG,mg.TenMG,kc.TenKC,th.TenTH,spct.SoLuong,spct.GiaBan,spct.NgayNhap,spct.TrangThai FROM SanPhamChiTiet spct \n"
                + "JOIN SanPham  sp ON spct.MaSP = sp.MaSP\n"
                + "JOIN KieuDang  kd ON spct.MaKD = kd.MaKD\n"
                + "JOIN LoaiMatKinh  lmk ON spct.MaLMK = lmk.MaLMK\n"
                + "JOIN MauMatKinh  mmk ON spct.MaMMK = mmk.MaMMK\n"
                + "JOIN ChatLieuMatKinh  clmk ON spct.MaCLMK = clmk.MaCLMK\n"
                + "JOIN ChatLieuGong  clg ON spct.MaCLG = clg.MaCLG\n"
                + "JOIN MauGong  mg ON spct.MaMG = mg.MaMG\n"
                + "JOIN KichCo  kc ON spct.MaKC = kc.MaKC\n"
                + "JOIN ThuongHieu  th ON spct.MaTH = th.MaTH";
        ArrayList<SanPhamChiTietJoin> list = new ArrayList<>();
        try (Connection conn = dbc.getConnection()) {
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Integer MaSPCT = rs.getInt("MaSPCT");
                String MaSP = rs.getString("TenSP");
                String MaKD = rs.getString("TenKD");
                String MaLMK = rs.getString("TenLMK");
                String MaMMK = rs.getString("TenMMK");
                String MaCLMK = rs.getString("TenCLMK");
                String MaCLG = rs.getString("TenCLG");
                String MaMG = rs.getString("TenMG");
                String MaKC = rs.getString("TenKC");
                String MaTH = rs.getString("TenTH");
                Integer soLuong = rs.getInt("SoLuong");
                Double giaBan = rs.getDouble("GiaBan");
                Date ngayNhap = rs.getDate("NgayNhap");
                Boolean trangThai = rs.getBoolean("TrangThai");
                SanPhamChiTietJoin spctj = new SanPhamChiTietJoin(MaSPCT, MaSP, MaKD, MaCLMK, MaMMK, MaCLMK, MaCLG, MaMG, MaKC, MaTH, soLuong, giaBan, ngayNhap, trangThai);
                list.add(spctj);
            }
        } catch (Exception e) {

        }
        return list;
    }

    public Boolean add(SanPhamChiTiet spct) {
        String sql = "INSERT INTO SanPhamChiTiet(MaSP,MaKD,MaLMK,MaMMK,MaCLMK,MaCLG,MaMG,MaKC,MaTH,SoLuong,GiaBan,NgayNhap) VALUES(?,?,?,?,?,?,?,?,?,?,?,?);";
        try (Connection conn = dbc.getConnection()) {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setObject(1, spct.getMaSP());
            pst.setObject(2, spct.getMaKD());
            pst.setObject(3, spct.getMaLMK());
            pst.setObject(4, spct.getMaMMK());
            pst.setObject(5, spct.getMaCLMK());
            pst.setObject(6, spct.getMaCLG());
            pst.setObject(7, spct.getMaMG());
            pst.setObject(8, spct.getMaKC());
            pst.setObject(9, spct.getMaTH());
            pst.setObject(10, spct.getSoLuong());
            pst.setObject(11, spct.getGiaBan());
            pst.setObject(12, spct.getNgayNhap());
            int kq = pst.executeUpdate();
            return kq > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public Boolean update(Integer ma, SanPhamChiTiet spct) {
        String sql = "UPDATE SanPhamChiTiet SET MaSP = N'" + spct.getMaSP() + "',"
                + "MaKD = N'" + spct.getMaKD() + "',"
                + "MaLMK = N'" + spct.getMaLMK() + "',"
                + "MaMMK = N'" + spct.getMaMMK() + "',"
                + "MaCLMK = N'" + spct.getMaCLMK() + "',"
                + "MaCLG = N'" + spct.getMaCLG() + "',"
                + "MaMG = N'" + spct.getMaMG() + "',"
                + "MaKC = N'" + spct.getMaKC() + "',"
                + "MaTH = N'" + spct.getMaTH() + "',"
                + "SoLuong = N'" + spct.getSoLuong() + "',"
                + "GiaBan = N'" + spct.getGiaBan() + "'"
                + " WHERE MaSPCT = " + ma + ";";
        try (Connection conn = dbc.getConnection()) {
            PreparedStatement pst = conn.prepareStatement(sql);
            int kq = pst.executeUpdate();
            return kq > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public Boolean deleteTheoMaSPCT(Integer ma) {
        String sql = "DELETE FROM SanPhamChiTiet WHERE MaSPCT = " + ma + ";";
        try (Connection conn = dbc.getConnection()) {
            PreparedStatement pst = conn.prepareStatement(sql);
            int kq = pst.executeUpdate();
            return kq > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public ArrayList<SanPhamChiTiet> searchTheoMaSP(String maSP) {
        String sql = "SELECT * FROM SanPhamChiTiet WHERE MaSP LIKE '" + maSP + "'";
        ArrayList<SanPhamChiTiet> list = new ArrayList<>();
        try (Connection conn = dbc.getConnection()) {
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Integer MaSPCT = rs.getInt("MaSPCT");
                String MaSP = rs.getString("MaSP");
                String MaKD = rs.getString("MaKD");
                String MaLMK = rs.getString("MaLMK");
                String MaMMK = rs.getString("MaMMK");
                String MaCLMK = rs.getString("MaCLMK");
                String MaCLG = rs.getString("MaCLG");
                String MaMG = rs.getString("MaMG");
                String MaKC = rs.getString("MaKC");
                String MaTH = rs.getString("MaTH");
                Integer soLuong = rs.getInt("SoLuong");
                Double giaBan = rs.getDouble("GiaBan");
                Date ngayNhap = rs.getDate("NgayNhap");
                Boolean trangThai = rs.getBoolean("TrangThai");
                SanPhamChiTiet spct = new SanPhamChiTiet(MaSPCT, MaSP, MaKD, MaLMK, MaMMK, MaCLMK, MaCLG, MaMG, MaKC, MaTH, soLuong, giaBan, ngayNhap, trangThai);
                list.add(spct);
            }
        } catch (Exception e) {

        }
        return list;
    }

    public ArrayList<SanPhamChiTiet> searchTheoMaSPCT(Integer maSPCT) {
        String sql = "SELECT * FROM SanPhamChiTiet WHERE MaSPCT = " + maSPCT + "";
        ArrayList<SanPhamChiTiet> list = new ArrayList<>();
        try (Connection conn = dbc.getConnection()) {
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Integer MaSPCT = rs.getInt("MaSPCT");
                String MaSP = rs.getString("MaSP");
                String MaKD = rs.getString("MaKD");
                String MaLMK = rs.getString("MaLMK");
                String MaMMK = rs.getString("MaMMK");
                String MaCLMK = rs.getString("MaCLMK");
                String MaCLG = rs.getString("MaCLG");
                String MaMG = rs.getString("MaMG");
                String MaKC = rs.getString("MaKC");
                String MaTH = rs.getString("MaTH");
                Integer soLuong = rs.getInt("SoLuong");
                Double giaBan = rs.getDouble("GiaBan");
                Date ngayNhap = rs.getDate("NgayNhap");
                Boolean trangThai = rs.getBoolean("TrangThai");
                SanPhamChiTiet spct = new SanPhamChiTiet(MaSPCT, MaSP, MaKD, MaLMK, MaMMK, MaCLMK, MaCLG, MaMG, MaKC, MaTH, soLuong, giaBan, ngayNhap, trangThai);
                list.add(spct);
            }
        } catch (Exception e) {

        }
        return list;
    }

    public Boolean updateTrangThai(Integer ma) {
        String sql = "UPDATE SanPhamChiTiet SET TrangThai = 0 WHERE MaSPCT = " + ma + ";";
        try (Connection conn = dbc.getConnection()) {
            PreparedStatement pst = conn.prepareStatement(sql);
            int kq = pst.executeUpdate();
            return kq > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public ArrayList<SanPhamChiTietJoin> searchNangCao(String tenSP, String tenKD, String tenTH, String typeGia) {
        String sql = "SELECT spct.MaSPCT,sp.TenSP,kd.TenKD,lmk.TenLMK,mmk.TenMMK,clmk.TenCLMK,clg.TenCLG,mg.TenMG,kc.TenKC,th.TenTH,spct.SoLuong,spct.GiaBan,spct.NgayNhap,spct.TrangThai FROM SanPhamChiTiet spct \n"
                + "JOIN SanPham  sp ON spct.MaSP = sp.MaSP\n"
                + "JOIN KieuDang  kd ON spct.MaKD = kd.MaKD\n"
                + "JOIN LoaiMatKinh  lmk ON spct.MaLMK = lmk.MaLMK\n"
                + "JOIN MauMatKinh  mmk ON spct.MaMMK = mmk.MaMMK\n"
                + "JOIN ChatLieuMatKinh  clmk ON spct.MaCLMK = clmk.MaCLMK\n"
                + "JOIN ChatLieuGong  clg ON spct.MaCLG = clg.MaCLG\n"
                + "JOIN MauGong  mg ON spct.MaMG = mg.MaMG\n"
                + "JOIN KichCo  kc ON spct.MaKC = kc.MaKC\n"
                + "JOIN ThuongHieu  th ON spct.MaTH = th.MaTH\n"
                + "WHERE sp.TenSP LIKE '%" + tenSP + "%' AND kd.TenKD LIKE '%" + tenKD + "%' AND th.TenTH LIKE '%" + tenTH + "%' ORDER BY GiaBan " + typeGia + "";
        ArrayList<SanPhamChiTietJoin> list = new ArrayList<>();
        try (Connection conn = dbc.getConnection()) {
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Integer MaSPCT = rs.getInt("MaSPCT");
                String MaSP = rs.getString("TenSP");
                String MaKD = rs.getString("TenKD");
                String MaLMK = rs.getString("TenLMK");
                String MaMMK = rs.getString("TenMMK");
                String MaCLMK = rs.getString("TenCLMK");
                String MaCLG = rs.getString("TenCLG");
                String MaMG = rs.getString("TenMG");
                String MaKC = rs.getString("TenKC");
                String MaTH = rs.getString("TenTH");
                Integer soLuong = rs.getInt("SoLuong");
                Double giaBan = rs.getDouble("GiaBan");
                Date ngayNhap = rs.getDate("NgayNhap");
                Boolean trangThai = rs.getBoolean("TrangThai");
                SanPhamChiTietJoin spctj = new SanPhamChiTietJoin(MaSPCT, MaSP, MaKD, MaCLMK, MaMMK, MaCLMK, MaCLG, MaMG, MaKC, MaTH, soLuong, giaBan, ngayNhap, trangThai);
                list.add(spctj);
            }
        } catch (Exception e) {

        }
        return list;
    }

    public ArrayList<SanPhamChiTietJoin> searchTheoTenSP(String ten) {
        String sql = "SELECT spct.MaSPCT,sp.TenSP,kd.TenKD,lmk.TenLMK,mmk.TenMMK,clmk.TenCLMK,clg.TenCLG,mg.TenMG,kc.TenKC,th.TenTH,spct.SoLuong,spct.GiaBan,spct.NgayNhap,spct.TrangThai FROM SanPhamChiTiet spct \n"
                + "JOIN SanPham  sp ON spct.MaSP = sp.MaSP\n"
                + "JOIN KieuDang  kd ON spct.MaKD = kd.MaKD\n"
                + "JOIN LoaiMatKinh  lmk ON spct.MaLMK = lmk.MaLMK\n"
                + "JOIN MauMatKinh  mmk ON spct.MaMMK = mmk.MaMMK\n"
                + "JOIN ChatLieuMatKinh  clmk ON spct.MaCLMK = clmk.MaCLMK\n"
                + "JOIN ChatLieuGong  clg ON spct.MaCLG = clg.MaCLG\n"
                + "JOIN MauGong  mg ON spct.MaMG = mg.MaMG\n"
                + "JOIN KichCo  kc ON spct.MaKC = kc.MaKC\n"
                + "JOIN ThuongHieu  th ON spct.MaTH = th.MaTH\n"
                + "WHERE sp.TenSP LIKE N'" + ten + "'";
        ArrayList<SanPhamChiTietJoin> list = new ArrayList<>();
        try (Connection conn = dbc.getConnection()) {
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Integer MaSPCT = rs.getInt("MaSPCT");
                String MaSP = rs.getString("TenSP");
                String MaKD = rs.getString("TenKD");
                String MaLMK = rs.getString("TenLMK");
                String MaMMK = rs.getString("TenMMK");
                String MaCLMK = rs.getString("TenCLMK");
                String MaCLG = rs.getString("TenCLG");
                String MaMG = rs.getString("TenMG");
                String MaKC = rs.getString("TenKC");
                String MaTH = rs.getString("TenTH");
                Integer soLuong = rs.getInt("SoLuong");
                Double giaBan = rs.getDouble("GiaBan");
                Date ngayNhap = rs.getDate("NgayNhap");
                Boolean trangThai = rs.getBoolean("TrangThai");
                SanPhamChiTietJoin spctj = new SanPhamChiTietJoin(MaSPCT, MaSP, MaKD, MaCLMK, MaMMK, MaCLMK, MaCLG, MaMG, MaKC, MaTH, soLuong, giaBan, ngayNhap, trangThai);
                list.add(spctj);
            }
        } catch (Exception e) {

        }
        return list;
    }
    
    public ArrayList<SanPhamChiTietJoin> searchByMaSPCT(Integer maSPCT) {
        String sql = "SELECT spct.MaSPCT,sp.TenSP,kd.TenKD,lmk.TenLMK,mmk.TenMMK,clmk.TenCLMK,clg.TenCLG,mg.TenMG,kc.TenKC,th.TenTH,spct.SoLuong,spct.GiaBan,spct.NgayNhap,spct.TrangThai FROM SanPhamChiTiet spct \n"
                + "JOIN SanPham  sp ON spct.MaSP = sp.MaSP\n"
                + "JOIN KieuDang  kd ON spct.MaKD = kd.MaKD\n"
                + "JOIN LoaiMatKinh  lmk ON spct.MaLMK = lmk.MaLMK\n"
                + "JOIN MauMatKinh  mmk ON spct.MaMMK = mmk.MaMMK\n"
                + "JOIN ChatLieuMatKinh  clmk ON spct.MaCLMK = clmk.MaCLMK\n"
                + "JOIN ChatLieuGong  clg ON spct.MaCLG = clg.MaCLG\n"
                + "JOIN MauGong  mg ON spct.MaMG = mg.MaMG\n"
                + "JOIN KichCo  kc ON spct.MaKC = kc.MaKC\n"
                + "JOIN ThuongHieu  th ON spct.MaTH = th.MaTH\n"
                + "WHERE spct.MaSPCT = "+maSPCT+"";
        ArrayList<SanPhamChiTietJoin> list = new ArrayList<>();
        try (Connection conn = dbc.getConnection()) {
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Integer MaSPCT = rs.getInt("MaSPCT");
                String MaSP = rs.getString("TenSP");
                String MaKD = rs.getString("TenKD");
                String MaLMK = rs.getString("TenLMK");
                String MaMMK = rs.getString("TenMMK");
                String MaCLMK = rs.getString("TenCLMK");
                String MaCLG = rs.getString("TenCLG");
                String MaMG = rs.getString("TenMG");
                String MaKC = rs.getString("TenKC");
                String MaTH = rs.getString("TenTH");
                Integer soLuong = rs.getInt("SoLuong");
                Double giaBan = rs.getDouble("GiaBan");
                Date ngayNhap = rs.getDate("NgayNhap");
                Boolean trangThai = rs.getBoolean("TrangThai");
                SanPhamChiTietJoin spctj = new SanPhamChiTietJoin(MaSPCT, MaSP, MaKD, MaCLMK, MaMMK, MaCLMK, MaCLG, MaMG, MaKC, MaTH, soLuong, giaBan, ngayNhap, trangThai);
                list.add(spctj);
            }
        } catch (Exception e) {

        }
        return list;
    }
}
