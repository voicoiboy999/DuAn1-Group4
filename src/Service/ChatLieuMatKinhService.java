/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import model.ChatLieuMatKinh;
import repo.ChatLieuMatKinhRepository;
import java.util.ArrayList;

/**
 *
 * @author Gumball
 */
public class ChatLieuMatKinhService {
    ChatLieuMatKinhRepository clmkr = new ChatLieuMatKinhRepository();
    
    public ArrayList<ChatLieuMatKinh> getAll(){
        ArrayList<ChatLieuMatKinh> list = clmkr.getTen();
        return list;
    }
    
    public String add(ChatLieuMatKinh clmk){
        if(clmkr.add(clmk)){
            return "Them thanh cong";
        }else{
            return "Them that bai";
        }
    }
    
    public String update(String ma, String ten, String moTa){
        if(clmkr.update(ma, ten, moTa)){
            return "Sua thanh cong";
        }else{
            return "Sua that bai";
        }
    }
    
    public String remove(String ma){
        if(clmkr.delete(ma)){
            return "Xoa thanh cong";
        }else{
            return "Xoa that bai";
        }
    }
    
    public ArrayList<ChatLieuMatKinh> searchTheoMa(String ma){
        return clmkr.searchTheoMa(ma);
    }
    
    public ArrayList<ChatLieuMatKinh> searchTheoTen(String ten){
        return clmkr.searchTheoTen(ten);
    }
}
