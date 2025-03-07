/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Gumball
 */
public class LoaiMatKinh {
    private String maLMK;
    private String tenLMK;
    private String moTaLMK;

    public LoaiMatKinh() {
    }

    public LoaiMatKinh(String maLMK, String tenLMK, String moTaLMK) {
        this.maLMK = maLMK;
        this.tenLMK = tenLMK;
        this.moTaLMK = moTaLMK;
    }

    public String getMaLMK() {
        return maLMK;
    }

    public void setMaLMK(String maLMK) {
        this.maLMK = maLMK;
    }

    public String getTenLMK() {
        return tenLMK;
    }

    public void setTenLMK(String tenLMK) {
        this.tenLMK = tenLMK;
    }

    public String getMoTaLMK() {
        return moTaLMK;
    }

    public void setMoTaLMK(String moTaLMK) {
        this.moTaLMK = moTaLMK;
    }
    
    
}
