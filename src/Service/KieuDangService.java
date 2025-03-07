/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import model.KieuDang;
import repo.KieuDangRepository;
import java.util.ArrayList;

/**
 *
 * @author Gumball
 */
public class KieuDangService {
    KieuDangRepository kdr = new KieuDangRepository();
    
    public ArrayList<KieuDang> getAll(){
        ArrayList<KieuDang> list = kdr.getTen();
        return list;
    }
    
    public String add(KieuDang kd){
        if(kdr.add(kd)){
            return "Them thanh cong";
        }else{
            return "Them that bai";
        }
    }
    
    public String update(String ma, String ten, String moTa){
        if(kdr.update(ma, ten, moTa)){
            return "Sua thanh cong";
        }else{
            return "Sua that bai";
        }
    }
    
    public String remove(String ma){
        if(kdr.delete(ma)){
            return "Xoa thanh cong";
        }else{
            return "Xoa that bai";
        }
    }
    
    public ArrayList<KieuDang> searchTheoMa(String ma){
        return kdr.searchTheoMa(ma);
    }
    
    public ArrayList<KieuDang> searchTheoTen(String ten){
        return kdr.searchTheoTen(ten);
    }
}
