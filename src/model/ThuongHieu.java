/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Gumball
 */
public class ThuongHieu {
    private String maTH;
    private String tenTH;
    private String moTaTH;

    public ThuongHieu() {
    }

    public ThuongHieu(String maTH, String tenTH, String moTaTH) {
        this.maTH = maTH;
        this.tenTH = tenTH;
        this.moTaTH = moTaTH;
    }

    public String getMaTH() {
        return maTH;
    }

    public void setMaTH(String maTH) {
        this.maTH = maTH;
    }

    public String getTenTH() {
        return tenTH;
    }

    public void setTenTH(String tenTH) {
        this.tenTH = tenTH;
    }

    public String getMoTaTH() {
        return moTaTH;
    }

    public void setMoTaTH(String moTaTH) {
        this.moTaTH = moTaTH;
    }
    
    
}
