/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Gumball
 */
public class ChatLieuMatKinh {
    private String maCLMK;
    private String tenCLML;
    private String moTaCLMK;

    public ChatLieuMatKinh() {
    }

    public ChatLieuMatKinh(String maCLMK, String tenCLML, String moTaCLMK) {
        this.maCLMK = maCLMK;
        this.tenCLML = tenCLML;
        this.moTaCLMK = moTaCLMK;
    }

    public String getMaCLMK() {
        return maCLMK;
    }

    public void setMaCLMK(String maCLMK) {
        this.maCLMK = maCLMK;
    }

    public String getTenCLML() {
        return tenCLML;
    }

    public void setTenCLML(String tenCLML) {
        this.tenCLML = tenCLML;
    }

    public String getMoTaCLMK() {
        return moTaCLMK;
    }

    public void setMoTaCLMK(String moTaCLMK) {
        this.moTaCLMK = moTaCLMK;
    }
    
    
}
