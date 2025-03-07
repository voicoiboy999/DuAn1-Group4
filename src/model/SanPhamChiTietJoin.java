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
public class SanPhamChiTietJoin {
    private Integer maSPCT;
    private String tenSP;
    private String tenKD;
    private String tenLMK;
    private String tenMMK;
    private String tenCLMK;
    private String tenCLG;
    private String tenMG;
    private String tenKC;
    private String tenTH;
    private Integer soLuong;
    private Double giaBan;
    private Date ngayNhap;
    private Boolean trangThai;

    public SanPhamChiTietJoin() {
    }

    public SanPhamChiTietJoin(Integer maSPCT, String tenSP, String tenKD, String tenLMK, String tenMMK, String tenCLMK, String tenCLG, String tenMG, String tenKC, String tenTH, Integer soLuong, Double giaBan, Date ngayNhap, Boolean trangThai) {
        this.maSPCT = maSPCT;
        this.tenSP = tenSP;
        this.tenKD = tenKD;
        this.tenLMK = tenLMK;
        this.tenMMK = tenMMK;
        this.tenCLMK = tenCLMK;
        this.tenCLG = tenCLG;
        this.tenMG = tenMG;
        this.tenKC = tenKC;
        this.tenTH = tenTH;
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

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public String getTenKD() {
        return tenKD;
    }

    public void setTenKD(String tenKD) {
        this.tenKD = tenKD;
    }

    public String getTenLMK() {
        return tenLMK;
    }

    public void setTenLMK(String tenLMK) {
        this.tenLMK = tenLMK;
    }

    public String getTenMMK() {
        return tenMMK;
    }

    public void setTenMMK(String tenMMK) {
        this.tenMMK = tenMMK;
    }

    public String getTenCLMK() {
        return tenCLMK;
    }

    public void setTenCLMK(String tenCLMK) {
        this.tenCLMK = tenCLMK;
    }

    public String getTenCLG() {
        return tenCLG;
    }

    public void setTenCLG(String tenCLG) {
        this.tenCLG = tenCLG;
    }

    public String getTenMG() {
        return tenMG;
    }

    public void setTenMG(String tenMG) {
        this.tenMG = tenMG;
    }

    public String getTenKC() {
        return tenKC;
    }

    public void setTenKC(String tenKC) {
        this.tenKC = tenKC;
    }

    public String getTenTH() {
        return tenTH;
    }

    public void setTenTH(String tenTH) {
        this.tenTH = tenTH;
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
