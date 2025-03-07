/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import model.SanPhamChiTiet;
import repo.SanPhamChiTietRepository;
import java.util.ArrayList;
import model.SanPhamChiTietJoin;

/**
 *
 * @author Gumball
 */
public class SanPhamChiTietService {
    SanPhamChiTietRepository spctr = new SanPhamChiTietRepository();
    
    public ArrayList<SanPhamChiTiet> getAll(){
        return spctr.getTen();
    }
    
    public ArrayList<SanPhamChiTietJoin> getAllJ(){
        return spctr.getAll();
    }
    
    public String add(SanPhamChiTiet spct){
        if(spctr.add(spct)){
            return "Them thanh cong";
        }else{
            return "Them that bai";
        }
    }
    
    public String update(Integer ma,SanPhamChiTiet spct){
        if(spctr.update(ma, spct)){
            return "Sua thanh cong";
        }else{
            return "Sua that bai";
        }
    }
    
    public String deleteByMaSPCT(Integer ma){
        if(spctr.deleteTheoMaSPCT(ma)){
            return "Xoa thanh cong";
        }else{
            return "Xoa that bai";
        }
    }
    
    
    public ArrayList<SanPhamChiTiet> searchByMaSP(String maSP){
        return spctr.searchTheoMaSP(maSP);
    }
    
    public ArrayList<SanPhamChiTiet> searchByMaSPCT(Integer maSPCT){
        return spctr.searchTheoMaSPCT(maSPCT);
    }
    
    public String updateTrangThai(Integer ma){
        if(spctr.updateTrangThai(ma)){
            return "Xoá thành công";
        }else{
            return "Xoá thất bại";
        }
    }
    
    public ArrayList<SanPhamChiTietJoin> searchNangCao(String tenSP, String tenKD,String tenTH,String typeGia){
        return spctr.searchNangCao(tenSP, tenKD, tenTH, typeGia);
    }
    
    public ArrayList<SanPhamChiTietJoin> searchTheoTenSP(String ten){
        return spctr.searchTheoTenSP(ten);
    }
    
    public ArrayList<SanPhamChiTietJoin> searchTheoMaSPCTJ(Integer maSPCT){
        return spctr.searchByMaSPCT(maSPCT);
    }
}
