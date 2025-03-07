/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package view;
import view.viewF.viewKieuDang;
import model.ChatLieuGong;
import model.ChatLieuMatKinh;
import model.KichCo;
import model.KieuDang;
import model.LoaiMatKinh;
import model.MauGong;
import model.MauMatKinh;
import model.SanPham;
import model.SanPhamChiTiet;
import model.ThuongHieu;
import Service.ChatLieuGongService;
import Service.ChatLieuMatKinhService;
import Service.KichCoService;
import Service.KieuDangService;
import Service.LoaiMatKinhService;
import Service.MauGongService;
import Service.MauMatKinhService;
import Service.SanPhamChiTietService;
import Service.SanPhamService;
import Service.ThuongHieuService;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.SanPhamChiTietJoin;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;
import view.viewF.viewCLG;
import view.viewF.viewCLMK;
import view.viewF.viewKC;
import view.viewF.viewLMK;
import view.viewF.viewMG;
import view.viewF.viewMMK;
import view.viewF.viewSanPhamF;
import view.viewF.viewTH;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.videoio.VideoCapture;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.core.CvType;
/**
 *
 * @author Gumball
 */
public class viewSanPham extends javax.swing.JInternalFrame {

    /**
     * Creates new form viewSanPham
     */
    SanPhamChiTietService spcts = new SanPhamChiTietService();
    SanPhamService sps = new SanPhamService();
    KieuDangService kds = new KieuDangService();
    LoaiMatKinhService lmks = new LoaiMatKinhService();
    MauMatKinhService mmks = new MauMatKinhService();
    ChatLieuMatKinhService clmks = new ChatLieuMatKinhService();
    ChatLieuGongService clgs = new ChatLieuGongService();
    MauGongService mgs = new MauGongService();
    KichCoService kcs = new KichCoService();
    ThuongHieuService ths = new ThuongHieuService();
    public viewSanPham() {
        initComponents();
        this.initCBO();
        this.fillTableSPCT(spcts.getAllJ());
        this.fillTableSP(sps.getAll());
        txtMaTT.setEditable(false);
        txtMaSP.setEditable(false);
        txtMaSPCT.setEditable(false);
        rdoKD.setSelected(true);
        this.fillTableKD(kds.getAll());
    }
    
    int indexTT = -1;

    public void fillTableSPCT(ArrayList<SanPhamChiTietJoin> list) {
        DefaultTableModel dtm = (DefaultTableModel) tblSPCT.getModel();
        dtm.setRowCount(0);
        for (SanPhamChiTietJoin spct : list) {
            String trangThai;
            if (!spct.getTrangThai()) {
                trangThai = "Ngừng kinh doanh";
            } else if (spct.getSoLuong() > 0) {
                trangThai = "Còn hàng";
            } else {
                trangThai = "Hết hàng";
            }
            dtm.addRow(new Object[]{
                list.indexOf(spct) + 1,
                spct.getMaSPCT(),
                spct.getTenSP(),
                spct.getTenKD(),
                spct.getTenLMK(),
                spct.getTenMMK(),
                spct.getTenCLMK(),
                spct.getTenCLG(),
                spct.getTenMG(),
                spct.getTenKC(),
                spct.getTenTH(),
                spct.getSoLuong(),
                spct.getGiaBan(),
                spct.getNgayNhap(),
                trangThai
            });
        }
    }

    public void fillTableSP(ArrayList<SanPham> list) {
        DefaultTableModel dtm = (DefaultTableModel) tblSanPham.getModel();
        dtm.setRowCount(0);
        for (SanPham sp : list) {
            int soLuong = 0;
            for (SanPhamChiTiet spct : spcts.searchByMaSP(sp.getMaSP())) {
                soLuong += spct.getSoLuong();
            }
            dtm.addRow(new Object[]{
                list.indexOf(sp) + 1,
                sp.getMaSP(),
                sp.getTenSP(),
                soLuong,});
        }
    }

    public void fillTableKD(ArrayList<KieuDang> list) {
        DefaultTableModel dtm = (DefaultTableModel) tblTT.getModel();
        dtm.setRowCount(0);
        for (KieuDang kd : list) {
            dtm.addRow(new Object[]{
                list.indexOf(kd) + 1,
                kd.getMaKD(),
                kd.getTenKD(),
                kd.getMoTaKD()
            });
        }
    }

    public void fillTableLMK(ArrayList<LoaiMatKinh> list) {
        DefaultTableModel dtm = (DefaultTableModel) tblTT.getModel();
        dtm.setRowCount(0);
        for (LoaiMatKinh kd : list) {
            dtm.addRow(new Object[]{
                list.indexOf(kd) + 1,
                kd.getMaLMK(),
                kd.getTenLMK(),
                kd.getMoTaLMK()
            });
        }
    }

    public void fillTableMMK(ArrayList<MauMatKinh> list) {
        DefaultTableModel dtm = (DefaultTableModel) tblTT.getModel();
        dtm.setRowCount(0);
        for (MauMatKinh kd : list) {
            dtm.addRow(new Object[]{
                list.indexOf(kd) + 1,
                kd.getMaMMK(),
                kd.getTenMMK(),
                kd.getMoTaMMK()
            });
        }
    }

    public void fillTableCLMK(ArrayList<ChatLieuMatKinh> list) {
        DefaultTableModel dtm = (DefaultTableModel) tblTT.getModel();
        dtm.setRowCount(0);
        for (ChatLieuMatKinh kd : list) {
            dtm.addRow(new Object[]{
                list.indexOf(kd) + 1,
                kd.getMaCLMK(),
                kd.getTenCLML(),
                kd.getMoTaCLMK()
            });
        }
    }

    public void fillTableCLG(ArrayList<ChatLieuGong> list) {
        DefaultTableModel dtm = (DefaultTableModel) tblTT.getModel();
        dtm.setRowCount(0);
        for (ChatLieuGong kd : list) {
            dtm.addRow(new Object[]{
                list.indexOf(kd) + 1,
                kd.getMaCLG(),
                kd.getTenCLG(),
                kd.getMoTaCLG()
            });
        }
    }

    public void fillTableMG(ArrayList<MauGong> list) {
        DefaultTableModel dtm = (DefaultTableModel) tblTT.getModel();
        dtm.setRowCount(0);
        for (MauGong kd : list) {
            dtm.addRow(new Object[]{
                list.indexOf(kd) + 1,
                kd.getMaMG(),
                kd.getTenMG(),
                kd.getMoTaMG()
            });
        }
    }

    public void fillTableKC(ArrayList<KichCo> list) {
        DefaultTableModel dtm = (DefaultTableModel) tblTT.getModel();
        dtm.setRowCount(0);
        for (KichCo kd : list) {
            dtm.addRow(new Object[]{
                list.indexOf(kd) + 1,
                kd.getMaKC(),
                kd.getTenKC(),
                kd.getMoTaKC()
            });
        }
    }

    public void fillTableTH(ArrayList<ThuongHieu> list) {
        DefaultTableModel dtm = (DefaultTableModel) tblTT.getModel();
        dtm.setRowCount(0);
        for (ThuongHieu kd : list) {
            dtm.addRow(new Object[]{
                list.indexOf(kd) + 1,
                kd.getMaTH(),
                kd.getTenTH(),
                kd.getMoTaTH()
            });
        }
    }

    public void setTextSP(int row) {
        txtMaSP.setText(tblSanPham.getValueAt(row, 1).toString());
        txtTenSP.setText(tblSanPham.getValueAt(row, 2).toString());
    }

    public void setTextTT(int row) {
        txtMaTT.setText(tblTT.getValueAt(row, 1).toString());
        txtTenTT.setText(tblTT.getValueAt(row, 2).toString());
        txtMoTaTT.setText(tblTT.getValueAt(row, 3).toString());
    }

    public void setTextSPCT(int row) {
        txtMaSPCT.setText(tblSPCT.getValueAt(row, 1).toString());
        cboTenSP.setSelectedItem(tblSPCT.getValueAt(row, 2));
        cboKieuDang.setSelectedItem(tblSPCT.getValueAt(row, 3));
        cboLoaiMatKinh.setSelectedItem(tblSPCT.getValueAt(row, 4));
        cboMauMatKinh.setSelectedItem(tblSPCT.getValueAt(row, 5));
        cboChatLieuMatKinh.setSelectedItem(tblSPCT.getValueAt(row, 6));
        cboChatLieuGong.setSelectedItem(tblSPCT.getValueAt(row, 7));
        cboMauGongKinh.setSelectedItem(tblSPCT.getValueAt(row, 8));
        cboKichCo.setSelectedItem(tblSPCT.getValueAt(row, 9));
        cboThuongHieu.setSelectedItem(tblSPCT.getValueAt(row, 10));
        txtSoLuong.setText(tblSPCT.getValueAt(row, 11).toString());
        txtGiaBan.setText(tblSPCT.getValueAt(row, 12).toString());
    }

    public void clearFormSP() {
        txtMaSP.setText("###");
        txtTenSP.setText("");
    }

    public void clearFormSPCT() {
        txtMaSPCT.setText("###");
        cboTenSP.setSelectedIndex(0);
        cboKieuDang.setSelectedIndex(0);
        cboLoaiMatKinh.setSelectedIndex(0);
        cboMauMatKinh.setSelectedIndex(0);
        cboChatLieuMatKinh.setSelectedIndex(0);
        cboChatLieuGong.setSelectedIndex(0);
        cboMauGongKinh.setSelectedIndex(0);
        cboKichCo.setSelectedIndex(0);
        cboThuongHieu.setSelectedIndex(0);
        txtSoLuong.setText("");
        txtGiaBan.setText("");
        cboTimKiemTenSP.setSelectedIndex(0);
        cboTimKiemTH.setSelectedIndex(0);
        cboTimKiemGia.setSelectedIndex(0);
    }

    public void clearFormTT() {
        txtMaTT.setText("###");
        txtTenTT.setText("");
        txtMoTaTT.setText("");
    }

    public void initCBO() {
        cboTenSP.removeAllItems();
        cboKieuDang.removeAllItems();
        cboLoaiMatKinh.removeAllItems();
        cboMauMatKinh.removeAllItems();
        cboChatLieuMatKinh.removeAllItems();
        cboChatLieuGong.removeAllItems();
        cboMauGongKinh.removeAllItems();
        cboKichCo.removeAllItems();
        cboThuongHieu.removeAllItems();
        cboTimKiemTenSP.removeAllItems();
        cboTimKiemTH.removeAllItems();
        cboTimKiemGia.removeAllItems();

        ArrayList<SanPham> listSP = sps.getAll();
        for (SanPham sp : listSP) {
            cboTenSP.addItem(sp.getTenSP());
        }

        ArrayList<KieuDang> listKD = kds.getAll();
        for (KieuDang kd : listKD) {
            cboKieuDang.addItem(kd.getTenKD());
        }

        ArrayList<LoaiMatKinh> listLMK = lmks.getAll();
        for (LoaiMatKinh lmk : listLMK) {
            cboLoaiMatKinh.addItem(lmk.getTenLMK());
        }

        ArrayList<MauMatKinh> listMMK = mmks.getAll();
        for (MauMatKinh mmk : listMMK) {
            cboMauMatKinh.addItem(mmk.getTenMMK());
        }

        ArrayList<ChatLieuMatKinh> listCLMK = clmks.getAll();
        for (ChatLieuMatKinh clmk : listCLMK) {
            cboChatLieuMatKinh.addItem(clmk.getTenCLML());
        }

        ArrayList<ChatLieuGong> listCLG = clgs.getAll();
        for (ChatLieuGong clg : listCLG) {
            cboChatLieuGong.addItem(clg.getTenCLG());
        }

        ArrayList<MauGong> listMG = mgs.getAll();
        for (MauGong mg : listMG) {
            cboMauGongKinh.addItem(mg.getTenMG());
        }

        ArrayList<KichCo> listKC = kcs.getAll();
        for (KichCo kc : listKC) {
            cboKichCo.addItem(kc.getTenKC());
        }

        ArrayList<ThuongHieu> listTH = ths.getAll();
        for (ThuongHieu th : listTH) {
            cboThuongHieu.addItem(th.getTenTH());
        }
        
        cboTimKiemTenSP.addItem("Tên sản phẩm");
        for (SanPham sp : listSP){
            cboTimKiemTenSP.addItem(sp.getTenSP());
        }
        
        
        
        cboTimKiemTH.addItem("Thương hiệu");
        for (ThuongHieu sp : listTH){
            cboTimKiemTH.addItem(sp.getTenTH());
        }
        
        cboTimKiemGia.addItem("Giá");
        cboTimKiemGia.addItem("Thấp đến cao");
        cboTimKiemGia.addItem("Cao đến thấp");
    }

    public void timKiem(){
        String tenSP = "";
        String tenKD = "";
        String tenTH = "";
        String typeGia = "ASC";
        if(cboTimKiemTenSP.getSelectedIndex() > 0){
            tenSP = cboTimKiemTenSP.getSelectedItem().toString();
        }
        if(cboTimKiemTH.getSelectedIndex() > 0){
            tenTH = cboTimKiemTH.getSelectedItem().toString();
        }
        if(cboTimKiemGia.getSelectedIndex() == 2){
            typeGia = "DESC";
        }
        fillTableSPCT(spcts.searchNangCao(tenSP, tenKD, tenTH, typeGia));
    }
    
    public SanPhamChiTiet getDataSPCT(){
        String maSP = sps.searchTheoTen(cboTenSP.getSelectedItem().toString()).get(0).getMaSP();
        String maKD = kds.searchTheoTen(cboKieuDang.getSelectedItem().toString()).get(0).getMaKD();
        String maLMK = lmks.searchTheoTen(cboLoaiMatKinh.getSelectedItem().toString()).get(0).getMaLMK();
        String maMMK = mmks.searchTheoTen(cboMauMatKinh.getSelectedItem().toString()).get(0).getMaMMK();
        String maCLMK = clmks.searchTheoTen(cboChatLieuMatKinh.getSelectedItem().toString()).get(0).getMaCLMK();
        String maCLG = clgs.searchTheoTen(cboChatLieuGong.getSelectedItem().toString()).get(0).getMaCLG();
        String maMG = mgs.searchTheoTen(cboMauGongKinh.getSelectedItem().toString()).get(0).getMaMG();
        String maKC = kcs.searchTheoTen(cboKichCo.getSelectedItem().toString()).get(0).getMaKC();
        String maTH = ths.searchTheoTen(cboThuongHieu.getSelectedItem().toString()).get(0).getMaTH();
        Integer soLuong = Integer.parseInt(txtSoLuong.getText());
        Double gia = Double.parseDouble(txtGiaBan.getText());

        Calendar cld = Calendar.getInstance();
        cld.set(Calendar.HOUR_OF_DAY, 0);
        cld.set(Calendar.MINUTE, 0);
        cld.set(Calendar.SECOND, 0);
        cld.set(Calendar.MILLISECOND, 0);
        Date ngayTao = cld.getTime();
        SanPhamChiTiet spct = new SanPhamChiTiet(null, maSP, maKD, maLMK, maMMK, maCLMK, maCLG, maMG, maKC, maTH, soLuong, gia, ngayTao, null);
        return spct;
    }
    static {
        // Load thư viện OpenCV
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }

    public BufferedImage matToBufferedImage(Mat mat) {
        Mat mat2 = new Mat();
        Imgproc.cvtColor(mat, mat2, Imgproc.COLOR_BGR2RGB);  // Chuyển từ BGR sang RGB
        byte[] data = new byte[(int) (mat2.total() * mat2.channels())];
        mat2.get(0, 0, data);

        BufferedImage image = new BufferedImage(mat2.cols(), mat2.rows(), BufferedImage.TYPE_3BYTE_BGR);
        image.getRaster().setDataElements(0, 0, mat2.cols(), mat2.rows(), data);
        return image;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        txtTenSP = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtMaSP = new javax.swing.JTextField();
        btnThemSP = new javax.swing.JButton();
        btnSuaSP = new javax.swing.JButton();
        btnXoaSP = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblSanPham = new javax.swing.JTable();
        btnLamMoiSP = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblSPCT = new javax.swing.JTable();
        btnThemSPCT = new javax.swing.JButton();
        btnLamMoiSPCT = new javax.swing.JButton();
        btnSuaSPCT = new javax.swing.JButton();
        btnXoaSPCT = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        txtMaSPCT = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtSoLuong = new javax.swing.JTextField();
        cboLoaiMatKinh = new javax.swing.JComboBox<>();
        cboKieuDang = new javax.swing.JComboBox<>();
        cboTenSP = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        cboMauMatKinh = new javax.swing.JComboBox<>();
        cboChatLieuMatKinh = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        cboChatLieuGong = new javax.swing.JComboBox<>();
        txtGiaBan = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        cboThuongHieu = new javax.swing.JComboBox<>();
        cboKichCo = new javax.swing.JComboBox<>();
        cboMauGongKinh = new javax.swing.JComboBox<>();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        cboTimKiemTenSP = new javax.swing.JComboBox<>();
        cboTimKiemTH = new javax.swing.JComboBox<>();
        cboTimKiemGia = new javax.swing.JComboBox<>();
        btnScanQRSPCT = new javax.swing.JButton();
        btnXuatExcel = new javax.swing.JButton();
        btnThemKD = new javax.swing.JButton();
        btnThemLMK = new javax.swing.JButton();
        btnThemMMK = new javax.swing.JButton();
        btnThemCLMK = new javax.swing.JButton();
        btnThemCLG = new javax.swing.JButton();
        btnThemMG = new javax.swing.JButton();
        btnThemKC = new javax.swing.JButton();
        btnThemTH = new javax.swing.JButton();
        btnThemSPF = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        rdoKD = new javax.swing.JRadioButton();
        rdoLMK = new javax.swing.JRadioButton();
        rdoMMK = new javax.swing.JRadioButton();
        rdoTH = new javax.swing.JRadioButton();
        rdoKC = new javax.swing.JRadioButton();
        rdoCLMK = new javax.swing.JRadioButton();
        rdoMG = new javax.swing.JRadioButton();
        rdoCLG = new javax.swing.JRadioButton();
        jLabel5 = new javax.swing.JLabel();
        txtMaTT = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtTenTT = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtMoTaTT = new javax.swing.JTextArea();
        jLabel7 = new javax.swing.JLabel();
        btnThemTT = new javax.swing.JButton();
        btnSuaTT = new javax.swing.JButton();
        btnXoaTT = new javax.swing.JButton();
        btnLamMoiTT = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblTT = new javax.swing.JTable();

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setText("Mã sản phẩm: ");

        jLabel2.setText("Tên sản phẩm: ");

        txtMaSP.setText("###");

        btnThemSP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/new-55-32.png"))); // NOI18N
        btnThemSP.setText("Thêm");
        btnThemSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemSPActionPerformed(evt);
            }
        });

        btnSuaSP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/report-for-repair-5-32.png"))); // NOI18N
        btnSuaSP.setText("Sửa");
        btnSuaSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaSPActionPerformed(evt);
            }
        });

        btnXoaSP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/remove-91-48.png"))); // NOI18N
        btnXoaSP.setText("Xoá");
        btnXoaSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaSPActionPerformed(evt);
            }
        });

        tblSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "#", "Mã sản phẩm", "Tên sản phẩm", "Số lượng"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSanPhamMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblSanPham);

        btnLamMoiSP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/reset-20-32.png"))); // NOI18N
        btnLamMoiSP.setText("Làm mới");
        btnLamMoiSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiSPActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane2)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtMaSP, javax.swing.GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE)
                    .addComponent(txtTenSP))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 199, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnThemSP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnXoaSP, javax.swing.GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE))
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnSuaSP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnLamMoiSP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(283, 283, 283))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addComponent(txtTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtMaSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnThemSP)
                            .addComponent(btnSuaSP))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnLamMoiSP)
                            .addComponent(btnXoaSP, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addComponent(jLabel2))
                    .addComponent(jLabel1))
                .addGap(42, 42, 42)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 373, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(212, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Sản Phẩm", jPanel1);

        tblSPCT.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "#", "Mã SPCT", "Tên SP", "Kiểu dáng", "Loại mắt kính", "Màu mắt kính", "Chất liệu mắt kính", "Chất liệu gọng", "Màu gọng", "Kích cỡ", "Thương hiệu", "Số lượng", "Giá bán", "Ngày nhập", "Trạng Thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblSPCT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSPCTMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblSPCT);

        btnThemSPCT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/new-55-32.png"))); // NOI18N
        btnThemSPCT.setText("Thêm");
        btnThemSPCT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemSPCTActionPerformed(evt);
            }
        });

        btnLamMoiSPCT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/reset-20-32.png"))); // NOI18N
        btnLamMoiSPCT.setText("Làm mới");
        btnLamMoiSPCT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiSPCTActionPerformed(evt);
            }
        });

        btnSuaSPCT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/report-for-repair-5-32.png"))); // NOI18N
        btnSuaSPCT.setText("Sửa");
        btnSuaSPCT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaSPCTActionPerformed(evt);
            }
        });

        btnXoaSPCT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/remove-91-48.png"))); // NOI18N
        btnXoaSPCT.setText("Xoá");
        btnXoaSPCT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaSPCTActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel12.setText("Mã SPCT");

        jLabel3.setText("Tên sản phẩm");

        jLabel8.setText("Kiểu dáng");

        jLabel9.setText("Loại mắt kính");

        jLabel10.setText("Số lượng");

        cboLoaiMatKinh.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cboKieuDang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cboTenSP.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel11.setText("Màu mắt kính");

        cboMauMatKinh.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cboChatLieuMatKinh.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel13.setText("Chất liệu mắt kính");

        jLabel14.setText("Chất liệu gọng");

        cboChatLieuGong.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel15.setText("Giá bán");

        cboThuongHieu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cboKichCo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cboMauGongKinh.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel16.setText("Màu gọng kính");

        jLabel17.setText("Kích cỡ");

        jLabel18.setText("Thương hiệu");

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel19.setText("Tìm kiếm");

        cboTimKiemTenSP.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboTimKiemTenSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboTimKiemTenSPActionPerformed(evt);
            }
        });

        cboTimKiemTH.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboTimKiemTH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboTimKiemTHActionPerformed(evt);
            }
        });

        cboTimKiemGia.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboTimKiemGia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboTimKiemGiaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(cboTimKiemTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(cboTimKiemTH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42)
                        .addComponent(cboTimKiemGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel19))
                .addContainerGap(38, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel19)
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboTimKiemTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboTimKiemTH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboTimKiemGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        btnScanQRSPCT.setText("Quét QR");
        btnScanQRSPCT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnScanQRSPCTActionPerformed(evt);
            }
        });

        btnXuatExcel.setText("Xuất Excel");
        btnXuatExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXuatExcelActionPerformed(evt);
            }
        });

        btnThemKD.setText("+");
        btnThemKD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemKDActionPerformed(evt);
            }
        });

        btnThemLMK.setText("+");
        btnThemLMK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemLMKActionPerformed(evt);
            }
        });

        btnThemMMK.setText("+");
        btnThemMMK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemMMKActionPerformed(evt);
            }
        });

        btnThemCLMK.setText("+");
        btnThemCLMK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemCLMKActionPerformed(evt);
            }
        });

        btnThemCLG.setText("+");
        btnThemCLG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemCLGActionPerformed(evt);
            }
        });

        btnThemMG.setText("+");
        btnThemMG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemMGActionPerformed(evt);
            }
        });

        btnThemKC.setText("+");
        btnThemKC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemKCActionPerformed(evt);
            }
        });

        btnThemTH.setText("+");
        btnThemTH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemTHActionPerformed(evt);
            }
        });

        btnThemSPF.setText("+");
        btnThemSPF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemSPFActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 1097, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(227, 227, 227)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnThemSPCT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnXoaSPCT, javax.swing.GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE))
                .addGap(39, 39, 39)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnLamMoiSPCT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSuaSPCT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnScanQRSPCT, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXuatExcel, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(80, 80, 80))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel8)
                                .addComponent(jLabel3))
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel10)
                                .addComponent(jLabel9)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cboTenSP, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cboKieuDang, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cboLoaiMatKinh, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnThemKD)
                                    .addComponent(btnThemLMK)
                                    .addComponent(btnThemSPF))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel11)
                                            .addComponent(jLabel14))
                                        .addGap(37, 37, 37))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel13)
                                        .addGap(18, 18, 18)))
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cboMauMatKinh, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cboChatLieuMatKinh, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cboChatLieuGong, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnThemMMK)
                                    .addComponent(btnThemCLMK)
                                    .addComponent(btnThemCLG))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel17)
                                    .addComponent(jLabel16)
                                    .addComponent(jLabel18))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cboMauGongKinh, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cboKichCo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cboThuongHieu, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnThemMG)
                                    .addComponent(btnThemKC)
                                    .addComponent(btnThemTH)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(92, 92, 92)
                                .addComponent(jLabel15)
                                .addGap(18, 18, 18)
                                .addComponent(txtGiaBan, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addGap(18, 18, 18)
                        .addComponent(txtMaSPCT, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txtMaSPCT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(cboTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(cboKieuDang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnThemKD))
                                    .addComponent(jLabel8))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cboLoaiMatKinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel9)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(cboMauMatKinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel11)
                                    .addComponent(btnThemMMK)
                                    .addComponent(btnThemSPF))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(cboChatLieuMatKinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnThemCLMK)
                                    .addComponent(jLabel13))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(cboChatLieuGong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel14)
                                    .addComponent(btnThemLMK)
                                    .addComponent(btnThemCLG))))
                        .addGap(32, 32, 32)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15)
                            .addComponent(txtGiaBan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btnThemSPCT)
                                    .addComponent(btnSuaSPCT)
                                    .addComponent(btnScanQRSPCT, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btnXoaSPCT, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnLamMoiSPCT)
                                    .addComponent(btnXuatExcel, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 73, Short.MAX_VALUE)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 344, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cboMauGongKinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16)
                            .addComponent(btnThemMG))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cboKichCo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnThemKC))
                            .addComponent(jLabel17))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cboThuongHieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel18)
                            .addComponent(btnThemTH))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        jTabbedPane1.addTab("Chi tiết sản phẩm", jPanel2);

        buttonGroup1.add(rdoKD);
        rdoKD.setText("Kiểu dáng");
        rdoKD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoKDActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdoLMK);
        rdoLMK.setText("Loại mắt kính");
        rdoLMK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoLMKActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdoMMK);
        rdoMMK.setText("Màu mắt kính");
        rdoMMK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoMMKActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdoTH);
        rdoTH.setText("Thương hiệu");
        rdoTH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoTHActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdoKC);
        rdoKC.setText("Kích cỡ");
        rdoKC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoKCActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdoCLMK);
        rdoCLMK.setText("Chất liệu mắt kính");
        rdoCLMK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoCLMKActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdoMG);
        rdoMG.setText("Màu gọng");
        rdoMG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoMGActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdoCLG);
        rdoCLG.setText("Chất liệu gọng");
        rdoCLG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoCLGActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setText("Mã thuộc tính");

        txtMaTT.setText("###");
        txtMaTT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaTTActionPerformed(evt);
            }
        });

        jLabel6.setText("Tên thuộc tính");

        txtMoTaTT.setColumns(20);
        txtMoTaTT.setRows(5);
        jScrollPane4.setViewportView(txtMoTaTT);

        jLabel7.setText("Mô tả:");

        btnThemTT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/new-55-32.png"))); // NOI18N
        btnThemTT.setText("Thêm");
        btnThemTT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemTTActionPerformed(evt);
            }
        });

        btnSuaTT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/report-for-repair-5-32.png"))); // NOI18N
        btnSuaTT.setText("Sửa");
        btnSuaTT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaTTActionPerformed(evt);
            }
        });

        btnXoaTT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/remove-91-48.png"))); // NOI18N
        btnXoaTT.setText("Xoá");
        btnXoaTT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaTTActionPerformed(evt);
            }
        });

        btnLamMoiTT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/reset-20-32.png"))); // NOI18N
        btnLamMoiTT.setText("Làm mới");
        btnLamMoiTT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiTTActionPerformed(evt);
            }
        });

        tblTT.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "#", "Mã TT", "Tên TT", "Mô tả"
            }
        ));
        tblTT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblTTMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tblTT);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jLabel5)
                    .addComponent(jLabel7))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txtMaTT)
                        .addComponent(txtTenTT, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(128, 128, 128)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rdoMMK)
                            .addComponent(rdoLMK)
                            .addComponent(rdoKD))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rdoMG)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(rdoCLG)
                                    .addComponent(rdoCLMK))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(rdoTH)
                                    .addComponent(rdoKC)))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(168, 168, 168)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnThemTT, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnXoaTT, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(34, 34, 34)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnLamMoiTT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnSuaTT, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(254, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(rdoKD)
                                .addGap(20, 20, 20)
                                .addComponent(rdoLMK)
                                .addGap(18, 18, 18)
                                .addComponent(rdoMMK))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(rdoCLMK)
                                        .addGap(18, 18, 18)
                                        .addComponent(rdoCLG))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(rdoKC)
                                        .addGap(18, 18, 18)
                                        .addComponent(rdoTH)))
                                .addGap(18, 18, 18)
                                .addComponent(rdoMG)))
                        .addGap(31, 31, 31)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnThemTT)
                            .addComponent(btnSuaTT))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnXoaTT, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnLamMoiTT))
                        .addGap(30, 30, 30))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(57, 57, 57)
                                .addComponent(txtTenTT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtMaTT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(60, 60, 60)
                                .addComponent(jLabel6))
                            .addComponent(jLabel5))
                        .addGap(50, 50, 50)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Thuộc tính", jPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemSPActionPerformed
        if (txtTenSP.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không được để trống dữ liệu");
            return;
        }
        for (SanPham sp : sps.getAll()) {
            if (sp.getTenSP().equalsIgnoreCase(txtTenSP.getText())) {
                JOptionPane.showMessageDialog(this, "Sản phẩm đã tồn tại, xin vui lòng thử lại");
                return;
            }
        }
        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn thêm không ?");
        if (confirm != 0) {
            return;
        }
        JOptionPane.showMessageDialog(this, sps.add(new SanPham(null, txtTenSP.getText())));
        this.fillTableSP(sps.getAll());
        this.initCBO();
    }//GEN-LAST:event_btnThemSPActionPerformed

    private void btnSuaSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaSPActionPerformed
        if (txtTenSP.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không được để trống dữ liệu");
            return;
        }
        for (SanPham sp : sps.getAll()) {
            if (sp.getTenSP().equalsIgnoreCase(txtTenSP.getText())) {
                JOptionPane.showMessageDialog(this, "Sản phẩm đã tồn tại, xin vui lòng thử lại");
                return;
            }
        }
        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn sửa không ?");
        if (confirm != 0) {
            return;
        }
        JOptionPane.showMessageDialog(this, sps.update(txtMaSP.getText(), txtTenSP.getText()));
        this.fillTableSP(sps.getAll());
        this.initCBO();
    }//GEN-LAST:event_btnSuaSPActionPerformed

    private void btnXoaSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaSPActionPerformed
        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn xoá không ?");
        if (confirm != 0) {
            return;
        }
        for (SanPhamChiTiet spct : spcts.getAll()) {
            if (spct.getMaSP().equals(txtMaSP.getText())) {
                JOptionPane.showMessageDialog(this, "Sản phẩm này đang được sử dụng");
                return;
            }
        }
        JOptionPane.showMessageDialog(this, sps.remove(txtMaSP.getText()));
        this.fillTableSP(sps.getAll());
        this.initCBO();
    }//GEN-LAST:event_btnXoaSPActionPerformed

    private void tblSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamMouseClicked
        int row = tblSanPham.getSelectedRow();
        if (row == -1) {
            return;
        }
        String tenSP = tblSanPham.getValueAt(row, 2).toString();
        jTabbedPane1.setSelectedIndex(1);
        this.setTextSP(row);
        if (spcts.searchTheoTenSP(tenSP).size() > 0) {
            this.fillTableSPCT(spcts.searchTheoTenSP(tenSP));
            this.setTextSPCT(0);
            tblSPCT.setRowSelectionInterval(0, 0);
            return;
        }
        for(SanPhamChiTietJoin spctj : spcts.getAllJ()){
            if(!spctj.getTenSP().equalsIgnoreCase(tenSP)){
                this.clearFormSPCT();
                this.fillTableSPCT(new ArrayList<>());
                return;
                
            }
        }
    }//GEN-LAST:event_tblSanPhamMouseClicked

    private void btnLamMoiSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiSPActionPerformed
        this.clearFormSP();
        this.fillTableSP(sps.getAll());
    }//GEN-LAST:event_btnLamMoiSPActionPerformed

    private void tblSPCTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSPCTMouseClicked
        int row = tblSPCT.getSelectedRow();
        if (row == -1) {
            return;
        }
        this.setTextSPCT(row);
    }//GEN-LAST:event_tblSPCTMouseClicked

    private void btnThemSPCTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemSPCTActionPerformed
        String regex = "\\d+";
        if (txtSoLuong.getText().trim().isEmpty() || txtGiaBan.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không được để trống dữ liệu");
            return;
        } else if(!txtSoLuong.getText().matches(regex) ){
            JOptionPane.showMessageDialog(this, "Số lượng phải là số");
            return;
        } else if(!txtGiaBan.getText().matches(regex) ){
            JOptionPane.showMessageDialog(this, "Giá tiền phải là số");
            return;
        } else if (Double.parseDouble(txtGiaBan.getText()) <= 0) {
            JOptionPane.showMessageDialog(this, "Giá tiền phải lớn hơn 0");
            return;
        } else if (Integer.parseInt(txtSoLuong.getText()) < 0 || Integer.parseInt(txtSoLuong.getText()) % 1 != 0) {
            JOptionPane.showMessageDialog(this, "Số lượng phải là số nguyên dương");
            return;
        }
        String maSP = sps.searchTheoTen(cboTenSP.getSelectedItem().toString()).get(0).getMaSP();
        String maKD = kds.searchTheoTen(cboKieuDang.getSelectedItem().toString()).get(0).getMaKD();
        String maLMK = lmks.searchTheoTen(cboLoaiMatKinh.getSelectedItem().toString()).get(0).getMaLMK();
        String maMMK = mmks.searchTheoTen(cboMauMatKinh.getSelectedItem().toString()).get(0).getMaMMK();
        String maCLMK = clmks.searchTheoTen(cboChatLieuMatKinh.getSelectedItem().toString()).get(0).getMaCLMK();
        String maCLG = clgs.searchTheoTen(cboChatLieuGong.getSelectedItem().toString()).get(0).getMaCLG();
        String maMG = mgs.searchTheoTen(cboMauGongKinh.getSelectedItem().toString()).get(0).getMaMG();
        String maKC = kcs.searchTheoTen(cboKichCo.getSelectedItem().toString()).get(0).getMaKC();
        String maTH = ths.searchTheoTen(cboThuongHieu.getSelectedItem().toString()).get(0).getMaTH();
        Integer soLuong = Integer.parseInt(txtSoLuong.getText());
        Double gia = Double.parseDouble(txtGiaBan.getText());

        Calendar cld = Calendar.getInstance();
        cld.set(Calendar.HOUR_OF_DAY, 0);
        cld.set(Calendar.MINUTE, 0);
        cld.set(Calendar.SECOND, 0);
        cld.set(Calendar.MILLISECOND, 0);
        Date ngayTao = cld.getTime();

        for (SanPhamChiTiet s : spcts.getAll()) {
            if (s.getMaSP().equalsIgnoreCase(maSP) && s.getMaKD().equalsIgnoreCase(maKD) && s.getMaLMK().equalsIgnoreCase(maLMK)
                && s.getMaMMK().equalsIgnoreCase(maMMK) && s.getMaCLMK().equalsIgnoreCase(maCLMK) && s.getMaCLG().equalsIgnoreCase(maCLG)
                && s.getMaMG().equalsIgnoreCase(maMG) && s.getMaKC().equalsIgnoreCase(maKC) && s.getMaTH().equalsIgnoreCase(maTH)) {
                JOptionPane.showMessageDialog(this, "Sản phẩm đã tồn tại");
                return;
            }
        }
        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn thêm không ?");
        if (confirm != 0) {
            return;
        }
        JOptionPane.showMessageDialog(this, spcts.add(new SanPhamChiTiet(null, maSP, maKD, maLMK, maMMK, maCLMK, maCLG, maMG, maKC, maTH, soLuong, gia, ngayTao, null)));
        this.fillTableSPCT(spcts.getAllJ());
    }//GEN-LAST:event_btnThemSPCTActionPerformed

    private void btnLamMoiSPCTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiSPCTActionPerformed
        this.clearFormSPCT();
        this.fillTableSPCT(spcts.getAllJ());
    }//GEN-LAST:event_btnLamMoiSPCTActionPerformed

    private void btnSuaSPCTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaSPCTActionPerformed
        String regex = "\\d+";
        if (txtSoLuong.getText().trim().isEmpty() || txtGiaBan.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không được để trống dữ liệu");
            return;
        } else if(!txtSoLuong.getText().matches(regex) ){
            JOptionPane.showMessageDialog(this, "Số lượng phải là số");
            return;
        } else if(!txtGiaBan.getText().matches(regex) ){
            JOptionPane.showMessageDialog(this, "Giá tiền phải là số");
            return;
        } else if (Double.parseDouble(txtGiaBan.getText()) < 0) {
            JOptionPane.showMessageDialog(this, "Giá tiền phải lớn hơn 0");
            return;
        } else if (Integer.parseInt(txtSoLuong.getText()) < 0 || Integer.parseInt(txtSoLuong.getText()) % 1 != 0) {
            JOptionPane.showMessageDialog(this, "Số lượng phải là số nguyên dương");
            return;
        }
        String maSP = sps.searchTheoTen(cboTenSP.getSelectedItem().toString()).get(0).getMaSP();
        String maKD = kds.searchTheoTen(cboKieuDang.getSelectedItem().toString()).get(0).getMaKD();
        String maLMK = lmks.searchTheoTen(cboLoaiMatKinh.getSelectedItem().toString()).get(0).getMaLMK();
        String maMMK = mmks.searchTheoTen(cboMauMatKinh.getSelectedItem().toString()).get(0).getMaMMK();
        String maCLMK = clmks.searchTheoTen(cboChatLieuMatKinh.getSelectedItem().toString()).get(0).getMaCLMK();
        String maCLG = clgs.searchTheoTen(cboChatLieuGong.getSelectedItem().toString()).get(0).getMaCLG();
        String maMG = mgs.searchTheoTen(cboMauGongKinh.getSelectedItem().toString()).get(0).getMaMG();
        String maKC = kcs.searchTheoTen(cboKichCo.getSelectedItem().toString()).get(0).getMaKC();
        String maTH = ths.searchTheoTen(cboThuongHieu.getSelectedItem().toString()).get(0).getMaTH();
        Integer soLuong = Integer.parseInt(txtSoLuong.getText());
        Double gia = Double.parseDouble(txtGiaBan.getText());

        Calendar cld = Calendar.getInstance();
        cld.set(Calendar.HOUR_OF_DAY, 0);
        cld.set(Calendar.MINUTE, 0);
        cld.set(Calendar.SECOND, 0);
        cld.set(Calendar.MILLISECOND, 0);
        Date ngayTao = cld.getTime();

        for (SanPhamChiTiet s : spcts.getAll()) {
            if (s.getMaSP().equalsIgnoreCase(maSP) && s.getMaKD().equalsIgnoreCase(maKD) && s.getMaLMK().equalsIgnoreCase(maLMK)
                && s.getMaMMK().equalsIgnoreCase(maMMK) && s.getMaCLMK().equalsIgnoreCase(maCLMK) && s.getMaCLG().equalsIgnoreCase(maCLG)
                && s.getMaMG().equalsIgnoreCase(maMG) && s.getMaKC().equalsIgnoreCase(maKC) && s.getMaTH().equalsIgnoreCase(maTH)
                && s.getSoLuong() == soLuong && s.getGiaBan() == gia) {
                JOptionPane.showMessageDialog(this, "Sản phẩm đã tồn tại");
                return;
            }
        }
        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn sửa không ?");
        if (confirm != 0) {
            return;
        }

        JOptionPane.showMessageDialog(this, spcts.update(Integer.parseInt(txtMaSPCT.getText()), new SanPhamChiTiet(null, maSP, maKD, maLMK, maMMK, maCLMK, maCLG, maMG, maKC, maTH, soLuong, gia, null, null)));
        this.fillTableSPCT(spcts.getAllJ());
    }//GEN-LAST:event_btnSuaSPCTActionPerformed

    private void btnXoaSPCTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaSPCTActionPerformed
        if(!spcts.searchByMaSPCT(Integer.parseInt(txtMaSPCT.getText())).get(0).getTrangThai()){
            JOptionPane.showMessageDialog(this, "Sản phẩm đã ngừng kinh doanh");
            return;
        }
        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn xoá không ?");
        if (confirm != 0) {
            return;
        }
        JOptionPane.showMessageDialog(this, spcts.updateTrangThai(Integer.parseInt(txtMaSPCT.getText())));
        this.fillTableSPCT(spcts.getAllJ());
    }//GEN-LAST:event_btnXoaSPCTActionPerformed

    private void cboTimKiemTenSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboTimKiemTenSPActionPerformed
        this.timKiem();
    }//GEN-LAST:event_cboTimKiemTenSPActionPerformed

    private void cboTimKiemTHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboTimKiemTHActionPerformed
        this.timKiem();
    }//GEN-LAST:event_cboTimKiemTHActionPerformed

    private void cboTimKiemGiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboTimKiemGiaActionPerformed
        this.timKiem();
    }//GEN-LAST:event_cboTimKiemGiaActionPerformed

    private void rdoKDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoKDActionPerformed
        this.fillTableKD(kds.getAll());
        indexTT = 1;
    }//GEN-LAST:event_rdoKDActionPerformed

    private void rdoLMKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoLMKActionPerformed
        this.fillTableLMK(lmks.getAll());
        indexTT = 2;
    }//GEN-LAST:event_rdoLMKActionPerformed

    private void rdoMMKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoMMKActionPerformed
        this.fillTableMMK(mmks.getAll());
        indexTT = 3;
    }//GEN-LAST:event_rdoMMKActionPerformed

    private void rdoTHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoTHActionPerformed
        this.fillTableTH(ths.getAll());
        indexTT = 8;
    }//GEN-LAST:event_rdoTHActionPerformed

    private void rdoKCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoKCActionPerformed
        this.fillTableKC(kcs.getAll());
        indexTT = 7;
    }//GEN-LAST:event_rdoKCActionPerformed

    private void rdoCLMKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoCLMKActionPerformed
        this.fillTableCLMK(clmks.getAll());
        indexTT = 4;
    }//GEN-LAST:event_rdoCLMKActionPerformed

    private void rdoMGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoMGActionPerformed
        this.fillTableMG(mgs.getAll());
        indexTT = 6;
    }//GEN-LAST:event_rdoMGActionPerformed

    private void rdoCLGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoCLGActionPerformed
        this.fillTableCLG(clgs.getAll());
        indexTT = 5;
    }//GEN-LAST:event_rdoCLGActionPerformed

    private void txtMaTTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaTTActionPerformed

    }//GEN-LAST:event_txtMaTTActionPerformed

    private void btnThemTTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemTTActionPerformed
        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn thêm không ?");
        if (confirm != 0) {
            return;
        }
        if (txtTenTT.getText().trim().isEmpty() || txtMoTaTT.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không được để trống dữ liệu");
            return;
        }
        if (indexTT == 1) {
            for (KieuDang kd : kds.getAll()) {
                if (kd.getTenKD().equalsIgnoreCase(txtTenTT.getText())) {
                    JOptionPane.showMessageDialog(this, "Thuộc tính này đã tồn tại");
                    return;
                }
            }
            kds.add(new KieuDang(null, txtTenTT.getText(), txtMoTaTT.getText()));
            this.fillTableKD(kds.getAll());
        } else if (indexTT == 2) {
            for (LoaiMatKinh kd : lmks.getAll()) {
                if (kd.getTenLMK().equalsIgnoreCase(txtTenTT.getText())) {
                    JOptionPane.showMessageDialog(this, "Thuộc tính này đã tồn tại");
                    return;
                }
            }
            lmks.add(new LoaiMatKinh(null, txtTenTT.getText(), txtMoTaTT.getText()));
            this.fillTableLMK(lmks.getAll());
        } else if (indexTT == 3) {
            for (MauMatKinh kd : mmks.getAll()) {
                if (kd.getTenMMK().equalsIgnoreCase(txtTenTT.getText())) {
                    JOptionPane.showMessageDialog(this, "Thuộc tính này đã tồn tại");
                    return;
                }
            }
            mmks.add(new MauMatKinh(null, txtTenTT.getText(), txtMoTaTT.getText()));
            this.fillTableMMK(mmks.getAll());
        } else if (indexTT == 4) {
            for (ChatLieuMatKinh kd : clmks.getAll()) {
                if (kd.getTenCLML().equalsIgnoreCase(txtTenTT.getText())) {
                    JOptionPane.showMessageDialog(this, "Thuộc tính này đã tồn tại");
                    return;
                }
            }
            clmks.add(new ChatLieuMatKinh(null, txtTenTT.getText(), txtMoTaTT.getText()));
            this.fillTableCLMK(clmks.getAll());
        } else if (indexTT == 5) {
            for (ChatLieuGong kd : clgs.getAll()) {
                if (kd.getTenCLG().equalsIgnoreCase(txtTenTT.getText())) {
                    JOptionPane.showMessageDialog(this, "Thuộc tính này đã tồn tại");
                    return;
                }
            }
            clgs.add(new ChatLieuGong(null, txtTenTT.getText(), txtMoTaTT.getText()));
            this.fillTableCLG(clgs.getAll());
        } else if (indexTT == 6) {
            for (MauGong kd : mgs.getAll()) {
                if (kd.getTenMG().equalsIgnoreCase(txtTenTT.getText())) {
                    JOptionPane.showMessageDialog(this, "Thuộc tính này đã tồn tại");
                    return;
                }
            }
            mgs.add(new MauGong(null, txtTenTT.getText(), txtMoTaTT.getText()));
            this.fillTableMG(mgs.getAll());
        } else if (indexTT == 7) {
            for (KichCo kd : kcs.getAll()) {
                if (kd.getTenKC().equalsIgnoreCase(txtTenTT.getText())) {
                    JOptionPane.showMessageDialog(this, "Thuộc tính này đã tồn tại");
                    return;
                }
            }
            kcs.add(new KichCo(null, txtTenTT.getText(), txtMoTaTT.getText()));
            this.fillTableKC(kcs.getAll());
        } else if (indexTT == 8) {
            for (ThuongHieu kd : ths.getAll()) {
                if (kd.getTenTH().equalsIgnoreCase(txtTenTT.getText())) {
                    JOptionPane.showMessageDialog(this, "Thuộc tính này đã tồn tại");
                    return;
                }
            }
            ths.add(new ThuongHieu(null, txtTenTT.getText(), txtMoTaTT.getText()));
            this.fillTableTH(ths.getAll());
        } else {
            return;
        }
        this.initCBO();
    }//GEN-LAST:event_btnThemTTActionPerformed

    private void btnSuaTTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaTTActionPerformed
        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn sửa không ?");
        if (confirm != 0) {
            return;
        }
        if (txtTenTT.getText().trim().isEmpty() || txtMoTaTT.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không được để trống dữ liệu");
            return;
        }
        if (indexTT == 1) {
            for (KieuDang kd : kds.getAll()) {
                if (kd.getTenKD().equalsIgnoreCase(txtTenTT.getText())) {
                    JOptionPane.showMessageDialog(this, "Thuộc tính này đã tồn tại");
                    return;
                }
            }
            kds.update(txtMaTT.getText(), txtTenTT.getText(), txtMoTaTT.getText());
            this.fillTableKD(kds.getAll());
        } else if (indexTT == 2) {
            for (LoaiMatKinh kd : lmks.getAll()) {
                if (kd.getTenLMK().equalsIgnoreCase(txtTenTT.getText())) {
                    JOptionPane.showMessageDialog(this, "Thuộc tính này đã tồn tại");
                    return;
                }
            }
            lmks.update(txtMaTT.getText(), txtTenTT.getText(), txtMoTaTT.getText());
            this.fillTableLMK(lmks.getAll());
        } else if (indexTT == 3) {
            for (MauMatKinh kd : mmks.getAll()) {
                if (kd.getTenMMK().equalsIgnoreCase(txtTenTT.getText())) {
                    JOptionPane.showMessageDialog(this, "Thuộc tính này đã tồn tại");
                    return;
                }
            }
            mmks.update(txtMaTT.getText(), txtTenTT.getText(), txtMoTaTT.getText());
            this.fillTableMMK(mmks.getAll());
        } else if (indexTT == 4) {
            for (ChatLieuMatKinh kd : clmks.getAll()) {
                if (kd.getTenCLML().equalsIgnoreCase(txtTenTT.getText())) {
                    JOptionPane.showMessageDialog(this, "Thuộc tính này đã tồn tại");
                    return;
                }
            }
            clmks.update(txtMaTT.getText(), txtTenTT.getText(), txtMoTaTT.getText());
            this.fillTableCLMK(clmks.getAll());
        } else if (indexTT == 5) {
            for (ChatLieuGong kd : clgs.getAll()) {
                if (kd.getTenCLG().equalsIgnoreCase(txtTenTT.getText())) {
                    JOptionPane.showMessageDialog(this, "Thuộc tính này đã tồn tại");
                    return;
                }
            }
            clgs.update(txtMaTT.getText(), txtTenTT.getText(), txtMoTaTT.getText());
            this.fillTableCLG(clgs.getAll());
        } else if (indexTT == 6) {
            for (MauGong kd : mgs.getAll()) {
                if (kd.getTenMG().equalsIgnoreCase(txtTenTT.getText())) {
                    JOptionPane.showMessageDialog(this, "Thuộc tính này đã tồn tại");
                    return;
                }
            }
            mgs.update(txtMaTT.getText(), txtTenTT.getText(), txtMoTaTT.getText());
            this.fillTableMG(mgs.getAll());
        } else if (indexTT == 7) {
            for (KichCo kd : kcs.getAll()) {
                if (kd.getTenKC().equalsIgnoreCase(txtTenTT.getText())) {
                    JOptionPane.showMessageDialog(this, "Thuộc tính này đã tồn tại");
                    return;
                }
            }
            kcs.update(txtMaTT.getText(), txtTenTT.getText(), txtMoTaTT.getText());
            this.fillTableKC(kcs.getAll());
        } else if (indexTT == 8) {
            for (ThuongHieu kd : ths.getAll()) {
                if (kd.getTenTH().equalsIgnoreCase(txtTenTT.getText())) {
                    JOptionPane.showMessageDialog(this, "Thuộc tính này đã tồn tại");
                    return;
                }
            }
            ths.update(txtMaTT.getText(), txtTenTT.getText(), txtMoTaTT.getText());
            this.fillTableTH(ths.getAll());
        } else {
            return;
        }
        this.initCBO();
    }//GEN-LAST:event_btnSuaTTActionPerformed

    private void btnXoaTTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaTTActionPerformed
        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn xoá không ?");
        if (confirm != 0) {
            return;
        }
        if (indexTT == 1) {
            for (SanPhamChiTiet spct : spcts.getAll()) {
                if (spct.getMaKD().equals(txtMaTT.getText())) {
                    JOptionPane.showMessageDialog(this, "Thuộc tính này đang được sử dụng");
                    return;
                }
            }
            kds.remove(txtMaTT.getText());
            this.fillTableKD(kds.getAll());
        } else if (indexTT == 2) {
            for (SanPhamChiTiet spct : spcts.getAll()) {
                if (spct.getMaLMK().equals(txtMaTT.getText())) {
                    JOptionPane.showMessageDialog(this, "Thuộc tính này đang được sử dụng");
                    return;
                }
            }
            lmks.remove(txtMaTT.getText());
            this.fillTableLMK(lmks.getAll());
        } else if (indexTT == 3) {
            for (SanPhamChiTiet spct : spcts.getAll()) {
                if (spct.getMaMMK().equals(txtMaTT.getText())) {
                    JOptionPane.showMessageDialog(this, "Thuộc tính này đang được sử dụng");
                    return;
                }
            }
            mmks.remove(txtMaTT.getText());
            this.fillTableMMK(mmks.getAll());
        } else if (indexTT == 4) {
            for (SanPhamChiTiet spct : spcts.getAll()) {
                if (spct.getMaCLMK().equals(txtMaTT.getText())) {
                    JOptionPane.showMessageDialog(this, "Thuộc tính này đang được sử dụng");
                    return;
                }
            }
            clmks.remove(txtMaTT.getText());
            this.fillTableCLMK(clmks.getAll());
        } else if (indexTT == 5) {
            for (SanPhamChiTiet spct : spcts.getAll()) {
                if (spct.getMaCLG().equals(txtMaTT.getText())) {
                    JOptionPane.showMessageDialog(this, "Thuộc tính này đang được sử dụng");
                    return;
                }
            }
            clgs.remove(txtMaTT.getText());
            this.fillTableCLG(clgs.getAll());
        } else if (indexTT == 6) {
            for (SanPhamChiTiet spct : spcts.getAll()) {
                if (spct.getMaMG().equals(txtMaTT.getText())) {
                    JOptionPane.showMessageDialog(this, "Thuộc tính này đang được sử dụng");
                    return;
                }
            }
            mgs.remove(txtMaTT.getText());
            this.fillTableMG(mgs.getAll());
        } else if (indexTT == 7) {
            for (SanPhamChiTiet spct : spcts.getAll()) {
                if (spct.getMaKC().equals(txtMaTT.getText())) {
                    JOptionPane.showMessageDialog(this, "Thuộc tính này đang được sử dụng");
                    return;
                }
            }
            kcs.remove(txtMaTT.getText());
            this.fillTableKC(kcs.getAll());
        } else if (indexTT == 8) {
            for (SanPhamChiTiet spct : spcts.getAll()) {
                if (spct.getMaTH().equals(txtMaTT.getText())) {
                    JOptionPane.showMessageDialog(this, "Thuộc tính này đang được sử dụng");
                    return;
                }
            }
            ths.remove(txtMaTT.getText());
            this.fillTableTH(ths.getAll());
        } else {
            return;
        }
        this.initCBO();
    }//GEN-LAST:event_btnXoaTTActionPerformed

    private void btnLamMoiTTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiTTActionPerformed
        this.clearFormTT();
        rdoKD.setSelected(true);
        indexTT = 1;
        this.fillTableKD(kds.getAll());
        this.initCBO();
    }//GEN-LAST:event_btnLamMoiTTActionPerformed

    private void tblTTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTTMouseClicked
        int row = tblTT.getSelectedRow();
        if (row == -1) {
            return;
        }
        this.setTextTT(row);
    }//GEN-LAST:event_tblTTMouseClicked

    private void btnScanQRSPCTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnScanQRSPCTActionPerformed
        VideoCapture capture = new VideoCapture(0);  // Mở webcam

        if (!capture.isOpened()) {
            JOptionPane.showMessageDialog(this, "Không thể mở webcam", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Mat frame = new Mat();
        while (capture.read(frame)) {
            BufferedImage bufferedImage = matToBufferedImage(frame);

            try {
                // Chuyển BufferedImage thành BinaryBitmap để ZXing xử lý
                BufferedImageLuminanceSource luminanceSource = new BufferedImageLuminanceSource(bufferedImage);
                BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(luminanceSource));

                // Quét mã QR
                Result rs = new MultiFormatReader().decode(binaryBitmap);
                JOptionPane.showMessageDialog(this, "Quét thành công");
                this.fillTableSPCT(spcts.searchTheoMaSPCTJ(Integer.parseInt(rs.getText())));
                System.out.println(rs);

                break;  // Thoát sau khi quét thành công

            } catch (Exception e) {
                // Không tìm thấy mã QR, tiếp tục quét
            }
        }

        capture.release();  // Giải phóng tài nguyên webcam
    }//GEN-LAST:event_btnScanQRSPCTActionPerformed

    private void btnXuatExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXuatExcelActionPerformed
        try {
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("danhsach");
            XSSFRow row = null;
            Cell cell = null;
            row = sheet.createRow(3);
            
            cell = row.createCell(0, CellType.STRING);
            cell.setCellValue("STT");
            
            cell = row.createCell(1, CellType.STRING);
            cell.setCellValue("Mã SPCT");
            
            cell = row.createCell(2, CellType.STRING);
            cell.setCellValue("Tên SP");
            
            cell = row.createCell(3, CellType.STRING);
            cell.setCellValue("Kiểu dáng");
            
            cell = row.createCell(4, CellType.STRING);
            cell.setCellValue("Loại mắt kính");
            
            cell = row.createCell(5, CellType.STRING);
            cell.setCellValue("Màu mắt kính");
            
            cell = row.createCell(6, CellType.STRING);
            cell.setCellValue("Chất liệu mắt kính");
            
            cell = row.createCell(7, CellType.STRING);
            cell.setCellValue("Chất liệu gọng");
            
            cell = row.createCell(8, CellType.STRING);
            cell.setCellValue("Màu gọng");
            
            cell = row.createCell(9, CellType.STRING);
            cell.setCellValue("Kích cỡ");
            
            cell = row.createCell(10, CellType.STRING);
            cell.setCellValue("Thương hiệu");
            
            cell = row.createCell(11, CellType.STRING);
            cell.setCellValue("Số lượng");
            
            cell = row.createCell(12, CellType.STRING);
            cell.setCellValue("Giá bán");
            
            cell = row.createCell(13, CellType.STRING);
            cell.setCellValue("Ngày tạo");
            
            cell = row.createCell(14, CellType.STRING);
            cell.setCellValue("Trạng thái");
            
            for (int i = 0; i < spcts.getAllJ().size(); i++) {
//                SanPhamChiTietJoin spctj = spcts.getAllJ().get(i);
                row = sheet.createRow(4 + i);
                
                cell = row.createCell(0,CellType.NUMERIC);
                cell.setCellValue(i+1);
                
                cell = row.createCell(1,CellType.STRING);
                cell.setCellValue(spcts.getAllJ().get(i).getMaSPCT());
                
                cell = row.createCell(2,CellType.STRING);
                cell.setCellValue(spcts.getAllJ().get(i).getTenSP());
                
                cell = row.createCell(3,CellType.STRING);
                cell.setCellValue(spcts.getAllJ().get(i).getTenKD());
                
                cell = row.createCell(4,CellType.STRING);
                cell.setCellValue(spcts.getAllJ().get(i).getTenLMK());
                
                cell = row.createCell(5,CellType.STRING);
                cell.setCellValue(spcts.getAllJ().get(i).getTenMMK());
                
                cell = row.createCell(6,CellType.STRING);
                cell.setCellValue(spcts.getAllJ().get(i).getTenCLMK());
                
                cell = row.createCell(7,CellType.STRING);
                cell.setCellValue(spcts.getAllJ().get(i).getTenCLG());
                
                cell = row.createCell(8,CellType.STRING);
                cell.setCellValue(spcts.getAllJ().get(i).getTenMG());
                
                cell = row.createCell(9,CellType.STRING);
                cell.setCellValue(spcts.getAllJ().get(i).getTenKC());
                
                cell = row.createCell(10,CellType.STRING);
                cell.setCellValue(spcts.getAllJ().get(i).getTenTH());
                
                cell = row.createCell(11,CellType.STRING);
                cell.setCellValue(spcts.getAllJ().get(i).getSoLuong());
                
                cell = row.createCell(12,CellType.STRING);
                cell.setCellValue(spcts.getAllJ().get(i).getGiaBan());
                
                cell = row.createCell(13,CellType.STRING);
                cell.setCellValue(spcts.getAllJ().get(i).getNgayNhap());
                
                cell = row.createCell(14,CellType.STRING);
                cell.setCellValue(spcts.getAllJ().get(i).getTrangThai().booleanValue() ? "Kinh doanh" : "Ngừng kinh doanh");
            }
            File f = new File("D:/danhsach.xlsx");
            try {
                FileOutputStream fos = new FileOutputStream(f);
                workbook.write(fos);
                fos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        JOptionPane.showMessageDialog(this, "Xuất file thành công");
    }//GEN-LAST:event_btnXuatExcelActionPerformed

    private void btnThemKDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemKDActionPerformed
        viewKieuDang viewKD = new viewKieuDang();
        viewKD.setVisible(true);
    }//GEN-LAST:event_btnThemKDActionPerformed

    private void btnThemLMKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemLMKActionPerformed
        viewLMK viewLMK = new viewLMK();
        viewLMK.setVisible(true);
    }//GEN-LAST:event_btnThemLMKActionPerformed

    private void btnThemMMKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemMMKActionPerformed
        viewMMK viewMMK = new viewMMK();
        viewMMK.setVisible(true);
    }//GEN-LAST:event_btnThemMMKActionPerformed

    private void btnThemCLMKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemCLMKActionPerformed
        viewCLMK viewCLMK = new viewCLMK();
        viewCLMK.setVisible(true);
    }//GEN-LAST:event_btnThemCLMKActionPerformed

    private void btnThemCLGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemCLGActionPerformed
        viewCLG viewCLG = new viewCLG();
        viewCLG.setVisible(true);
    }//GEN-LAST:event_btnThemCLGActionPerformed

    private void btnThemMGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemMGActionPerformed
        viewMG viewMG = new viewMG();
        viewMG.setVisible(true);
    }//GEN-LAST:event_btnThemMGActionPerformed

    private void btnThemKCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemKCActionPerformed
        viewKC viewKC = new viewKC();
        viewKC.setVisible(true);
    }//GEN-LAST:event_btnThemKCActionPerformed

    private void btnThemTHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemTHActionPerformed
        viewTH viewTH = new viewTH();
        viewTH.setVisible(true);
    }//GEN-LAST:event_btnThemTHActionPerformed

    private void btnThemSPFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemSPFActionPerformed
        viewSanPhamF viewSP = new viewSanPhamF();
        viewSP.setVisible(true);
    }//GEN-LAST:event_btnThemSPFActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLamMoiSP;
    private javax.swing.JButton btnLamMoiSPCT;
    private javax.swing.JButton btnLamMoiTT;
    private javax.swing.JButton btnScanQRSPCT;
    private javax.swing.JButton btnSuaSP;
    private javax.swing.JButton btnSuaSPCT;
    private javax.swing.JButton btnSuaTT;
    private javax.swing.JButton btnThemCLG;
    private javax.swing.JButton btnThemCLMK;
    private javax.swing.JButton btnThemKC;
    private javax.swing.JButton btnThemKD;
    private javax.swing.JButton btnThemLMK;
    private javax.swing.JButton btnThemMG;
    private javax.swing.JButton btnThemMMK;
    private javax.swing.JButton btnThemSP;
    private javax.swing.JButton btnThemSPCT;
    private javax.swing.JButton btnThemSPF;
    private javax.swing.JButton btnThemTH;
    private javax.swing.JButton btnThemTT;
    private javax.swing.JButton btnXoaSP;
    private javax.swing.JButton btnXoaSPCT;
    private javax.swing.JButton btnXoaTT;
    private javax.swing.JButton btnXuatExcel;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cboChatLieuGong;
    private javax.swing.JComboBox<String> cboChatLieuMatKinh;
    private javax.swing.JComboBox<String> cboKichCo;
    private javax.swing.JComboBox<String> cboKieuDang;
    private javax.swing.JComboBox<String> cboLoaiMatKinh;
    private javax.swing.JComboBox<String> cboMauGongKinh;
    private javax.swing.JComboBox<String> cboMauMatKinh;
    private javax.swing.JComboBox<String> cboTenSP;
    private javax.swing.JComboBox<String> cboThuongHieu;
    private javax.swing.JComboBox<String> cboTimKiemGia;
    private javax.swing.JComboBox<String> cboTimKiemTH;
    private javax.swing.JComboBox<String> cboTimKiemTenSP;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JRadioButton rdoCLG;
    private javax.swing.JRadioButton rdoCLMK;
    private javax.swing.JRadioButton rdoKC;
    private javax.swing.JRadioButton rdoKD;
    private javax.swing.JRadioButton rdoLMK;
    private javax.swing.JRadioButton rdoMG;
    private javax.swing.JRadioButton rdoMMK;
    private javax.swing.JRadioButton rdoTH;
    private javax.swing.JTable tblSPCT;
    private javax.swing.JTable tblSanPham;
    private javax.swing.JTable tblTT;
    private javax.swing.JTextField txtGiaBan;
    private javax.swing.JTextField txtMaSP;
    private javax.swing.JTextField txtMaSPCT;
    private javax.swing.JTextField txtMaTT;
    private javax.swing.JTextArea txtMoTaTT;
    private javax.swing.JTextField txtSoLuong;
    private javax.swing.JTextField txtTenSP;
    private javax.swing.JTextField txtTenTT;
    // End of variables declaration//GEN-END:variables
}
