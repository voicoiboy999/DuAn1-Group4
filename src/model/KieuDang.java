/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Gumball
 */
public class KieuDang {
    private String maKD;
    private String tenKD;
    private String moTaKD;

    public KieuDang() {
    }

    public KieuDang(String maKD, String tenKD, String moTaKD) {
        this.maKD = maKD;
        this.tenKD = tenKD;
        this.moTaKD = moTaKD;
    }

    public String getMaKD() {
        return maKD;
    }

    public void setMaKD(String maKD) {
        this.maKD = maKD;
    }

    public String getTenKD() {
        return tenKD;
    }

    public void setTenKD(String tenKD) {
        this.tenKD = tenKD;
    }

    public String getMoTaKD() {
        return moTaKD;
    }

    public void setMoTaKD(String moTaKD) {
        this.moTaKD = moTaKD;
    }
    
}
