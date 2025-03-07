/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import model.KichCo;
import repo.KichCoRepository;
import java.util.ArrayList;

/**
 *
 * @author Gumball
 */
public class KichCoService {
    KichCoRepository kcr = new KichCoRepository();
    
    public ArrayList<KichCo> getAll(){
        ArrayList<KichCo> list = kcr.getTen();
        return list;
    }
    
    public String add(KichCo kc){
        if(kcr.add(kc)){
            return "Them thanh cong";
        }else{
            return "Them that bai";
        }
    }
    
    public String update(String ma, String ten, String moTa){
        if(kcr.update(ma, ten, moTa)){
            return "Sua thanh cong";
        }else{
            return "Sua that bai";
        }
    }
    
    public String remove(String ma){
        if(kcr.delete(ma)){
            return "Xoa thanh cong";
        }else{
            return "Xoa that bai";
        }
    }
    
    public ArrayList<KichCo> searchTheoMa(String ma){
        return kcr.searchTheoMa(ma);
    }
    
    public ArrayList<KichCo> searchTheoTen(String ten){
        return kcr.searchTheoTen(ten);
    }
}
