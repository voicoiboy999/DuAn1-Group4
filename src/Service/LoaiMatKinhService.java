/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import model.LoaiMatKinh;
import repo.LoaiMatKinhRepository;
import java.util.ArrayList;

/**
 *
 * @author Gumball
 */
public class LoaiMatKinhService {

    LoaiMatKinhRepository lmkr = new LoaiMatKinhRepository();

    public ArrayList<LoaiMatKinh> getAll() {
        ArrayList<LoaiMatKinh> list = lmkr.getTen();
        return list;
    }

    public String add(LoaiMatKinh lmk) {
        if (lmkr.add(lmk)) {
            return "Them thanh cong";
        } else {
            return "Them that bai";
        }
    }

    public String update(String ma, String ten, String moTa) {
        if (lmkr.update(ma, ten, moTa)) {
            return "Sua thanh cong";
        } else {
            return "Sua that bai";
        }
    }

    public String remove(String ma) {
        if (lmkr.delete(ma)) {
            return "Xoa thanh cong";
        } else {
            return "Xoa that bai";
        }
    }

    public ArrayList<LoaiMatKinh> searchTheoMa(String ma) {
        return lmkr.searchTheoMa(ma);
    }

    public ArrayList<LoaiMatKinh> searchTheoTen(String ten) {
        return lmkr.searchTheoTen(ten);
    }
}
