/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import model.MauMatKinh;
import repo.MauMatKinhRepository;
import java.util.ArrayList;

/**
 *
 * @author Gumball
 */
public class MauMatKinhService {
    MauMatKinhRepository mmkr = new MauMatKinhRepository();
    
    public ArrayList<MauMatKinh> getAll(){
        ArrayList<MauMatKinh> list = mmkr.getTen();
        return list;
    }
    
    public String add(MauMatKinh mmk){
        if(mmkr.add(mmk)){
            return "Them thanh cong";
        }else{
            return "Them that bai";
        }
    }
    
    public String update(String ma, String ten, String moTa){
        if(mmkr.update(ma, ten, moTa)){
            return "Sua thanh cong";
        }else{
            return "Sua that bai";
        }
    }
    
    public String remove(String ma){
        if(mmkr.delete(ma)){
            return "Xoa thanh cong";
        }else{
            return "Xoa that bai";
        }
    }
    
    public ArrayList<MauMatKinh> searchTheoMa(String ma){
        return mmkr.searchTheoMa(ma);
    }
    
    public ArrayList<MauMatKinh> searchTheoTen(String ten){
        return mmkr.searchTheoTen(ten);
    }
}
