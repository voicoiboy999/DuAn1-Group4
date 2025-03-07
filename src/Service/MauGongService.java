/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import model.MauGong;
import repo.MauGongRepository;
import java.util.ArrayList;

/**
 *
 * @author Gumball
 */
public class MauGongService {
    MauGongRepository mgr = new MauGongRepository();
    
    public ArrayList<MauGong> getAll(){
        ArrayList<MauGong> list = mgr.getTen();
        return list;
    }
    
    public String add(MauGong mg){
        if(mgr.add(mg)){
            return "Them thanh cong";
        }else{
            return "Them that bai";
        }
    }
    
    public String update(String ma, String ten, String moTa){
        if(mgr.update(ma, ten, moTa)){
            return "Sua thanh cong";
        }else{
            return "Sua that bai";
        }
    }
    
    public String remove(String ma){
        if(mgr.delete(ma)){
            return "Xoa thanh cong";
        }else{
            return "Xoa that bai";
        }
    }
    
    public ArrayList<MauGong> searchTheoMa(String ma){
        return mgr.searchTheoMa(ma);
    }
    
    public ArrayList<MauGong> searchTheoTen(String ten){
        return mgr.searchTheoTen(ten);
    }
}
