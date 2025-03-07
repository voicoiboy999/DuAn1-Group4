/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import model.ChatLieuGong;
import repo.ChatLieuGongRepository;
import java.util.ArrayList;

/**
 *
 * @author Gumball
 */
public class ChatLieuGongService {
    ChatLieuGongRepository clgr = new ChatLieuGongRepository();
    
    public ArrayList<ChatLieuGong> getAll(){
        ArrayList<ChatLieuGong> list = clgr.getTen();
        return list;
    }
    
    public String add(ChatLieuGong clg){
        if(clgr.add(clg)){
            return "Them thanh cong";
        }else{
            return "Them that bai";
        }
    }
    
    public String update(String ma, String ten, String moTa){
        if(clgr.update(ma, ten, moTa)){
            return "Sua thanh cong";
        }else{
            return "Sua that bai";
        }
    }
    
    public String remove(String ma){
        if(clgr.delete(ma)){
            return "Xoa thanh cong";
        }else{
            return "Xoa that bai";
        }
    }
    
    public ArrayList<ChatLieuGong> searchTheoMa(String ma){
        return clgr.searchTheoMa(ma);
    }
    
    public ArrayList<ChatLieuGong> searchTheoTen(String ten){
        return clgr.searchTheoTen(ten);
    }
}
