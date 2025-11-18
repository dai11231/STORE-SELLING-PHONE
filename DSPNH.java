import java.util.Scanner;
import java.util.Arrays;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;

public class DSPNH { 
    private PhieuNhapHang[] danhSach;
    private int soLuongHienTai;
    private final int KICH_THUOC_BAN_DAU = 10;
    private final String TEN_FILE = "PNH.txt";
    
    private DSCTPNH dsChiTiet; 

    public DSPNH(DSCTPNH dsChiTiet) {
        this.danhSach = new PhieuNhapHang[KICH_THUOC_BAN_DAU];
        this.soLuongHienTai = 0;
        this.dsChiTiet = dsChiTiet; 
        docTatCaTuFile();
    }

    private void tangKichThuoc() {
        if (soLuongHienTai == danhSach.length) {
            int kichThuocMoi = danhSach.length * 2;
            danhSach = Arrays.copyOf(danhSach, kichThuocMoi);
        }
    }

    // --- 1. THEM (CREATE) ---
    public void themPhieuNhap(Scanner scanner) {
        tangKichThuoc();
        PhieuNhapHang pnh = new PhieuNhapHang();
        pnh.nhapThongTin(scanner);
        if (timTheoMa(pnh.getMaPhieu()) != null) {
            System.out.println("Loi: Ma Phieu Nhap da ton tai.");
            return;
        }
        this.danhSach[soLuongHienTai] = pnh;
        soLuongHienTai++;
        System.out.println("Them Phieu Nhap thanh cong.");
    }

    // --- 2. TIM KIEM/HIEN THI (READ) ---
    public PhieuNhapHang timTheoMa(String maPhieu) {
        for (int i = 0; i < soLuongHienTai; i++) {
            if (danhSach[i] != null && danhSach[i].getMaPhieu().equalsIgnoreCase(maPhieu)) {
                return danhSach[i];
            }
        }
        return null;
    }
    
    public void hienThiTatCa() {
        if (soLuongHienTai == 0) {
            System.out.println("Danh sach Phieu Nhap trong.");
            return;
        }
        System.out.println("===== DANH SACH PHIEU NHAP HANG (" + soLuongHienTai + " muc) =====");
        for (int i = 0; i < soLuongHienTai; i++) {
            if (danhSach[i] != null) {
                System.out.println(danhSach[i].toString());
                dsChiTiet.hienThiChiTiet(danhSach[i].getMaPhieu()); 
            }
        }
        System.out.println("====================================");
    }

    // --- 3. SUA (UPDATE) ---
    public void suaPhieuNhap(Scanner scanner) {
        System.out.print("Nhap Ma Phieu can sua: ");
        String maPhieu = scanner.nextLine();
        PhieuNhapHang pnhCanSua = timTheoMa(maPhieu);

        if (pnhCanSua == null) {
            System.out.println("Khong tim thay Phieu Nhap voi ma: " + maPhieu);
            return;
        }
        // ... (Logic sua thong tin)
        System.out.println("Sua Phieu Nhap thanh cong.");
    }

    // --- 4. XOA (DELETE) ---
    public void xoaPhieuNhap(Scanner scanner) {
        System.out.print("Nhap Ma Phieu can xoa: ");
        String maPhieu = scanner.nextLine();
        int viTriCanXoa = -1;
        
        for (int i = 0; i < soLuongHienTai; i++) {
            if (danhSach[i] != null && danhSach[i].getMaPhieu().equalsIgnoreCase(maPhieu)) {
                viTriCanXoa = i;
                break;
            }
        }
        
        if (viTriCanXoa != -1) {
            for (int i = viTriCanXoa; i < soLuongHienTai - 1; i++) {
                danhSach[i] = danhSach[i + 1];
            }
            soLuongHienTai--;
            danhSach[soLuongHienTai] = null; 
            
            dsChiTiet.xoaTatCaTheoMaPhieu(maPhieu); 

            System.out.println("Da xoa Phieu Nhap voi ma: " + maPhieu);
        } else {
            System.out.println("Khong tim thay Phieu Nhap voi ma: " + maPhieu);
        }
    }
    
    // --- 5. GHI FILE (SAVE) ---
    public void ghiTatCaVaoFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(TEN_FILE, false))) {
            for (int i = 0; i < soLuongHienTai; i++) {
                if (danhSach[i] != null) {
                    // Format: PNH001;NV001;Apple;1902025;349900000
                    writer.println(danhSach[i].getMaPhieu() + ";" + 
                                  danhSach[i].getMaNhanVien() + ";" + 
                                  danhSach[i].getMaNhaCungCap() + ";" + 
                                  danhSach[i].getNgayNhap() + ";" + 
                                  danhSach[i].getTongTienNhap());
                }
            }
        } catch (IOException e) {
            System.err.println("Loi khi ghi file PNH: " + e.getMessage());
        }
    }
    
    // --- 6. DOC FILE (LOAD) ---
    public void docTatCaTuFile() {
        try (Scanner fileScanner = new Scanner(new File(TEN_FILE))) {
            while (fileScanner.hasNextLine()) {
                String duLieu = fileScanner.nextLine();
                tangKichThuoc(); 
                
                PhieuNhapHang pnh = new PhieuNhapHang();
                pnh.docFile(duLieu);
                danhSach[soLuongHienTai] = pnh;
                soLuongHienTai++;
            }
        } catch (Exception e) {
            System.out.println("File PNH chua ton tai. Bat dau voi danh sach trong.");
        }
    }
    
    // --- MENU VA HAM MAIN ---
    private static void hienThiMenu() {
        System.out.println("\n--- MENU QUAN LY TONG HOP ---");
        System.out.println("=== NHA CUNG CAP (NCC) ===");
        System.out.println("1. Them NCC        | 2. Hien thi NCC | 3. Sua NCC | 4. Xoa NCC");
        System.out.println("=== PHIEU NHAP (PNH) ===");
        System.out.println("5. Them PNH        | 6. Hien thi PNH | 7. Sua PNH | 8. Xoa PNH");
        System.out.println("=== CHI TIET ===");
        System.out.println("9. Sua Chi Tiet PNH (SL/DG)");
        System.out.println("10. Hien thi tat ca Chi Tiet PNH");
        System.out.println("0. Thoat chuong trinh");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Khoi tao cac doi tuong quan ly dung ten class hien co
        DSCTPNH dsCT = new DSCTPNH();
        DSPNH dsPNH = new DSPNH(dsCT); 
        DSNCC dsNCC = new DSNCC();

        int luaChon = -1;
        
        do {
            hienThiMenu();
            System.out.print("Nhap lua chon cua ban: ");
            try {
                luaChon = scanner.nextInt();
                scanner.nextLine();
            } catch (java.util.InputMismatchException e) {
                System.out.println("Lua chon phai la so. Vui long thu lai.");
                scanner.nextLine();
                luaChon = -1; 
            }

            switch (luaChon) {
                // NHA CUNG CAP
                case 1: dsNCC.themNhaCungCap(scanner); break;
                case 2: dsNCC.hienThiTatCa(); break;
                case 3: dsNCC.suaNhaCungCap(scanner); break;
                case 4: dsNCC.xoaNhaCungCap(scanner); break;
                
                // PHIEU NHAP HANG
                case 5: dsPNH.themPhieuNhap(scanner); break;
                case 6: dsPNH.hienThiTatCa(); break;
                case 7: dsPNH.suaPhieuNhap(scanner); break;
                case 8: dsPNH.xoaPhieuNhap(scanner); break;

                // CHI TIET PHIEU NHAP
                case 9: dsCT.suaChiTiet(scanner); break; 
                case 10: dsCT.hienThiTatCa(); break; 
                
                case 0:
                    System.out.println("Ket thuc chuong trinh. Tam biet!");
                    break;
                default:
                    if (luaChon != -1) {
                         System.out.println("Lua chon khong hop le. Vui long chon lai.");
                    }
            }
            System.out.println("--------------------------------------------------");
        } while (luaChon != 0);
        scanner.close();
    }
}
