/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import model.SanPham;
import repo.SanPhamRepository;
import java.util.ArrayList;

/**
 *
 * @author Gumball
 */
public class SanPhamService {
    SanPhamRepository spr = new SanPhamRepository();
    
    public ArrayList<SanPham> getAll(){
        ArrayList<SanPham> list = spr.getTen();
        return list;
    }
    
    public String add(SanPham sp){
        if(spr.add(sp)){
            return "Them thanh cong";
        }else{
            return "Them that bai";
        }
    }
    
    public String update(String ma, String ten){
        if(spr.update(ma, ten)){
            return "Sua thanh cong";
        }else{
            return "Sua that bai";
        }
    }
    
    public String remove(String ma){
        if(spr.delete(ma)){
            return "Xoa thanh cong";
        }else{
            return "Xoa that bai";
        }
    }
    
    public ArrayList<SanPham> searchTheoMa(String ma){
        return spr.searchTheoMa(ma);
    }
    
    public ArrayList<SanPham> searchTheoTen(String ten){
        return spr.searchTheoTen(ten);
    }
    
    
}
