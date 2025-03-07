/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author LEGION 5
 */
public class model_PhieuGiamGia {

    private String maPGG;
    private String tenPGG;
    private double dieuKienGiam;
    private double giaGiamToiDa;
    private int soLuong;
    private Date ngayTao;
    private Date ngayBatDau;
    private Date ngayKetThuc;
    private boolean maHTGG;

    public model_PhieuGiamGia() {
    }

    public model_PhieuGiamGia(String maPGG, String tenPGG, double dieuKienGiam, double giaGiamToiDa, int soLuong, Date ngayTao, Date ngayBatDau, Date ngayKetThuc, boolean maHTGG) {
        this.maPGG = maPGG;
        this.tenPGG = tenPGG;
        this.dieuKienGiam = dieuKienGiam;
        this.giaGiamToiDa = giaGiamToiDa;
        this.soLuong = soLuong;
        this.ngayTao = ngayTao;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
        this.maHTGG = maHTGG;
    }

    public String getMaPGG() {
        return maPGG;
    }

    public void setMaPGG(String maPGG) {
        this.maPGG = maPGG;
    }

    public String getTenPGG() {
        return tenPGG;
    }

    public void setTenPGG(String tenPGG) {
        this.tenPGG = tenPGG;
    }

    public double getDieuKienGiam() {
        return dieuKienGiam;
    }

    public void setDieuKienGiam(double dieuKienGiam) {
        this.dieuKienGiam = dieuKienGiam;
    }

    public double getGiaGiamToiDa() {
        return giaGiamToiDa;
    }

    public void setGiaGiamToiDa(double giaGiamToiDa) {
        this.giaGiamToiDa = giaGiamToiDa;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

    public Date getNgayBatDau() {
        return ngayBatDau;
    }

    public void setNgayBatDau(Date ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    public Date getNgayKetThuc() {
        return ngayKetThuc;
    }

    public void setNgayKetThuc(Date ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }

    public boolean isMaHTGG() {
        return maHTGG;
    }

    public void setMaHTGG(boolean maHTGG) {
        this.maHTGG = maHTGG;
    }

    public Object[] toDataRow() {
        return new Object[]{this.getMaPGG(), this.getTenPGG(), this.getDieuKienGiam(), this.getGiaGiamToiDa(),
             this.getSoLuong(), this.getNgayTao(), this.getNgayBatDau(), this.getNgayKetThuc(), this.isMaHTGG()? "Giảm Tiền mặt" : "Giảm phần trăm"};
    }
}
