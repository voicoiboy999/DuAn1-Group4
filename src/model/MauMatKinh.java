/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Gumball
 */
public class MauMatKinh {
    private String maMMK;
    private String tenMMK;
    private String moTaMMK;

    public MauMatKinh() {
    }

    public MauMatKinh(String maMMK, String tenMMK, String moTaMMK) {
        this.maMMK = maMMK;
        this.tenMMK = tenMMK;
        this.moTaMMK = moTaMMK;
    }

    public String getMaMMK() {
        return maMMK;
    }

    public void setMaMMK(String maMMK) {
        this.maMMK = maMMK;
    }

    public String getTenMMK() {
        return tenMMK;
    }

    public void setTenMMK(String tenMMK) {
        this.tenMMK = tenMMK;
    }

    public String getMoTaMMK() {
        return moTaMMK;
    }

    public void setMoTaMMK(String moTaMMK) {
        this.moTaMMK = moTaMMK;
    }
    
    
}
