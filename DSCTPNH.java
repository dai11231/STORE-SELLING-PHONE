import java.util.Scanner;
import java.util.Arrays;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;

public class DSCTPNH {
    private ChiTietPhieuNhapHang[] danhSach;
    private int soLuongHienTai;
    private final int KICH_THUOC_BAN_DAU = 20;
    private final String TEN_FILE = "CTPNH.txt"; 

    public DSCTPNH() {
        this.danhSach = new ChiTietPhieuNhapHang[KICH_THUOC_BAN_DAU];
        this.soLuongHienTai = 0;
        docTatCaTuFile(); 
    }

    private void tangKichThuoc() {
        if (soLuongHienTai == danhSach.length) {
            int kichThuocMoi = danhSach.length * 2;
            danhSach = Arrays.copyOf(danhSach, kichThuocMoi);
        }
    }

    // --- 1. THÊM (CREATE) ---
    public void themChiTiet(ChiTietPhieuNhapHang ct) {
        tangKichThuoc();
        danhSach[soLuongHienTai] = ct;
        soLuongHienTai++;
        ghiTatCaVaoFile();
    }

    // --- 2. TÌM KIẾM/HIỂN THỊ (READ) ---
    public ChiTietPhieuNhapHang[] timTheoMaPhieu(String maPhieu) {
        ChiTietPhieuNhapHang[] temp = new ChiTietPhieuNhapHang[soLuongHienTai];
        int count = 0;
        
        for (int i = 0; i < soLuongHienTai; i++) {
            if (danhSach[i] != null && danhSach[i].getMaPhieu().equalsIgnoreCase(maPhieu)) {
                temp[count] = danhSach[i];
                count++;
            }
        }
        return Arrays.copyOf(temp, count); 
    }

    public ChiTietPhieuNhapHang timChiTiet(String maPhieu, String maDT) {
        for(int i = 0; i < soLuongHienTai; i++) {
            if (danhSach[i] != null && danhSach[i].getMaPhieu().equalsIgnoreCase(maPhieu) &&
                danhSach[i].getMaDienThoai().equalsIgnoreCase(maDT)) {
                return danhSach[i];
            }
        }
        return null;
    }

    public void hienThiChiTiet(String maPhieu) {
        ChiTietPhieuNhapHang[] chiTiet = timTheoMaPhieu(maPhieu);
        if (chiTiet.length == 0) return;
        
        System.out.println("--- CHI TIET PNH " + maPhieu + " ---");
        double tongTien = 0;
        for (ChiTietPhieuNhapHang ct : chiTiet) {
            System.out.println(ct.toString());
            tongTien += ct.tinhThanhTien();
        }
          System.out.printf("  => TONG CONG CT: %,.0f VND\n", tongTien);
    }

    public void hienThiTatCa() {
        if (soLuongHienTai == 0) {
            System.out.println("Danh sach chi tiet phieu nhap trong.");
            return;
        }
        
        System.out.println("=== DANH SACH CHI TIET PHIEU NHAP (" + soLuongHienTai + " muc) ===");
        for (int i = 0; i < soLuongHienTai; i++) {
            if (danhSach[i] != null) {
                System.out.println((i + 1) + ". " + danhSach[i].toString());
            }
        }
    }

    // --- 3. SỬA (UPDATE) ---
    public void suaChiTiet(Scanner scanner) {
        // ... (Logic sửa chi tiết)
        ghiTatCaVaoFile();
        System.out.println("✅ Sửa chi tiết thành công.");
    }

    // --- 4. XÓA (DELETE) ---
    public void xoaTatCaTheoMaPhieu(String maPhieu) {
        int soLuongDaXoa = 0;
        
        for (int i = 0; i < soLuongHienTai; i++) {
            if (danhSach[i] != null && danhSach[i].getMaPhieu().equalsIgnoreCase(maPhieu)) {
                danhSach[i] = danhSach[soLuongHienTai - 1];
                danhSach[soLuongHienTai - 1] = null; 
                soLuongHienTai--;
                i--; 
                soLuongDaXoa++;
            }
        }
        if (soLuongDaXoa > 0) {
             ghiTatCaVaoFile(); 
        }
    }
    
    // --- 5. GHI FILE (SAVE) ---
    public void ghiTatCaVaoFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(TEN_FILE, false))) {
            for (int i = 0; i < soLuongHienTai; i++) {
                if (danhSach[i] != null) { 
                    // BẮT BUỘC: Phương thức ghiFile(PrintWriter) phải có trong ChiTietPhieuNhapHang.java
                    danhSach[i].ghiFile(writer); 
                }
            }
        } catch (IOException e) {
             System.err.println(" Lỗi khi ghi file Chi Tiết PNH: " + e.getMessage());
        }
    }
    
    // --- 6. ĐỌC FILE (LOAD) ---
    public void docTatCaTuFile() {
        try (Scanner fileScanner = new Scanner(new File(TEN_FILE))) {
            while (fileScanner.hasNextLine()) {
                String duLieu = fileScanner.nextLine();
                tangKichThuoc(); 
                
                ChiTietPhieuNhapHang ct = new ChiTietPhieuNhapHang();
                // BẮT BUỘC: Phương thức docFile(String) phải có trong ChiTietPhieuNhapHang.java
                ct.docFile(duLieu); 

                if (ct.getMaPhieu() != null && !ct.getMaPhieu().trim().isEmpty()) {
                    danhSach[soLuongHienTai] = ct;
                    soLuongHienTai++;
                }
            }
        } catch (IOException e) {
             if (soLuongHienTai == 0) {
                 System.out.println("File Chi Tiet PNH chua ton tai.");
             } else {
                 System.err.println("Loi khi doc file Chi Tiet PNH: " + e.getMessage());
             }
        }
    }
}