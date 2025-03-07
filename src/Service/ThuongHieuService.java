/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import model.ThuongHieu;
import repo.ThuongHieuRepository;
import java.util.ArrayList;

/**
 *
 * @author Gumball
 */
public class ThuongHieuService {
    ThuongHieuRepository thr = new ThuongHieuRepository();
    
    public ArrayList<ThuongHieu> getAll(){
        ArrayList<ThuongHieu> list = thr.getTen();
        return list;
    }
    
    public String add(ThuongHieu th){
        if(thr.add(th)){
            return "Them thanh cong";
        }else{
            return "Them that bai";
        }
    }
    
    public String update(String ma, String ten, String moTa){
        if(thr.update(ma, ten, moTa)){
            return "Sua thanh cong";
        }else{
            return "Sua that bai";
        }
    }
    
    public String remove(String ma){
        if(thr.delete(ma)){
            return "Xoa thanh cong";
        }else{
            return "Xoa that bai";
        }
    }
    
    public ArrayList<ThuongHieu> searchTheoMa(String ma){
        return thr.searchTheoMa(ma);
    }
    
    public ArrayList<ThuongHieu> searchTheoTen(String ten){
        return thr.searchTheoTen(ten);
    }
}
