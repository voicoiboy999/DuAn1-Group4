/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author Gumball
 */
public class SanPhamChiTiet {
    private Integer maSPCT;
    private String maSP;
    private String maKD;
    private String maLMK;
    private String maMMK;
    private String maCLMK;
    private String maCLG;
    private String maMG;
    private String maKC;
    private String maTH;
    private Integer soLuong;
    private Double giaBan;
    private Date ngayNhap;
    private Boolean trangThai;

    public SanPhamChiTiet() {
    }

    public SanPhamChiTiet(Integer maSPCT, String maSP, String maKD, String maLMK, String maMMK, String maCLMK, String maCLG, String maMG, String maKC, String maTH, Integer soLuong, Double giaBan, Date ngayNhap, Boolean trangThai) {
        this.maSPCT = maSPCT;
        this.maSP = maSP;
        this.maKD = maKD;
        this.maLMK = maLMK;
        this.maMMK = maMMK;
        this.maCLMK = maCLMK;
        this.maCLG = maCLG;
        this.maMG = maMG;
        this.maKC = maKC;
        this.maTH = maTH;
        this.soLuong = soLuong;
        this.giaBan = giaBan;
        this.ngayNhap = ngayNhap;
        this.trangThai = trangThai;
    }

    public Integer getMaSPCT() {
        return maSPCT;
    }

    public void setMaSPCT(Integer maSPCT) {
        this.maSPCT = maSPCT;
    }

    public String getMaSP() {
        return maSP;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    public String getMaKD() {
        return maKD;
    }

    public void setMaKD(String maKD) {
        this.maKD = maKD;
    }

    public String getMaLMK() {
        return maLMK;
    }

    public void setMaLMK(String maLMK) {
        this.maLMK = maLMK;
    }

    public String getMaMMK() {
        return maMMK;
    }

    public void setMaMMK(String maMMK) {
        this.maMMK = maMMK;
    }

    public String getMaCLMK() {
        return maCLMK;
    }

    public void setMaCLMK(String maCLMK) {
        this.maCLMK = maCLMK;
    }

    public String getMaCLG() {
        return maCLG;
    }

    public void setMaCLG(String maCLG) {
        this.maCLG = maCLG;
    }

    public String getMaMG() {
        return maMG;
    }

    public void setMaMG(String maMG) {
        this.maMG = maMG;
    }

    public String getMaKC() {
        return maKC;
    }

    public void setMaKC(String maKC) {
        this.maKC = maKC;
    }

    public String getMaTH() {
        return maTH;
    }

    public void setMaTH(String maTH) {
        this.maTH = maTH;
    }

    public Integer getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(Integer soLuong) {
        this.soLuong = soLuong;
    }

    public Double getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(Double giaBan) {
        this.giaBan = giaBan;
    }

    public Date getNgayNhap() {
        return ngayNhap;
    }

    public void setNgayNhap(Date ngayNhap) {
        this.ngayNhap = ngayNhap;
    }

    public Boolean getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Boolean trangThai) {
        this.trangThai = trangThai;
    }

    
}
