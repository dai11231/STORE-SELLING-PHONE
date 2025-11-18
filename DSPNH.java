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
    private QLNV qlNV; // Reference to employee management system

    public DSPNH(DSCTPNH dsChiTiet, QLNV qlNV) {
        this.danhSach = new PhieuNhapHang[KICH_THUOC_BAN_DAU];
        this.soLuongHienTai = 0;
        this.dsChiTiet = dsChiTiet; 
        this.qlNV = qlNV; // Store employee reference
        docTatCaTuFile();
    }

    private void tangKichThuoc() {
        if (soLuongHienTai == danhSach.length) {
            int kichThuocMoi = danhSach.length * 2;
            danhSach = Arrays.copyOf(danhSach, kichThuocMoi);
        }
    }

    // --- Check unique purchase order code ---
    public boolean maPhieuDuyNhat(String maPhieu) {
        for (int i = 0; i < soLuongHienTai; i++) {
            if (danhSach[i].getMaPhieu().equalsIgnoreCase(maPhieu)) return true;
        }
        return false;
    }
    
    // --- Check if employee code exists ---
    public boolean maNVTonTai(String maNV) {
        if (qlNV == null) return false;
        
        // Create a temporary string array to check if employee exists
        // We'll use the existing search functionality
        try {
            // Use a simple search through the employee data
            java.util.Scanner tempScanner = new java.util.Scanner(new java.io.File("dsnv.txt"));
            while (tempScanner.hasNextLine()) {
                String line = tempScanner.nextLine();
                String[] parts = line.split(";");
                if (parts.length >= 1 && parts[0].trim().equalsIgnoreCase(maNV.trim())) {
                    tempScanner.close();
                    return true;
                }
            }
            tempScanner.close();
        } catch (Exception e) {
            // If file doesn't exist or error occurs, assume employee doesn't exist
        }
        return false;
    }
    
    // --- Check if supplier code exists ---
    public boolean maNCCTonTai(String maNCC) {
        try {
            java.util.Scanner tempScanner = new java.util.Scanner(new java.io.File("NCC.txt"));
            while (tempScanner.hasNextLine()) {
                String line = tempScanner.nextLine();
                String[] parts = line.split(";");
                if (parts.length >= 1 && parts[0].trim().equalsIgnoreCase(maNCC.trim())) {
                    tempScanner.close();
                    return true;
                }
            }
            tempScanner.close();
        } catch (Exception e) {
            // If file doesn't exist or error occurs, assume supplier doesn't exist
        }
        return false;
    }
    
    // --- 1. THEM (CREATE) ---
    public void themPhieuNhap(Scanner scanner) {
        tangKichThuoc();
        PhieuNhapHang pnh = new PhieuNhapHang();
        pnh.nhapThongTin(scanner);
        
        // Check for unique purchase order code with retry mechanism
        while (maPhieuDuyNhat(pnh.getMaPhieu())) {
            System.out.println("Loi: Ma phieu nhap " + pnh.getMaPhieu() + " da ton tai.");
            System.out.print("Vui long nhap lai ma phieu nhap: ");
            String newMaPhieu = scanner.nextLine().trim();
            pnh.setMaPhieu(newMaPhieu);
        }
        
        // Check if employee code exists with retry mechanism
        while (!maNVTonTai(pnh.getMaNhanVien())) {
            System.out.println("Loi: Ma nhan vien " + pnh.getMaNhanVien() + " khong ton tai trong he thong.");
            System.out.print("Vui long nhap lai ma nhan vien: ");
            String newMaNV = scanner.nextLine().trim();
            pnh.setMaNhanVien(newMaNV);
        }
        
        // Check if supplier code exists with retry mechanism
        while (!maNCCTonTai(pnh.getMaNhaCungCap())) {
            System.out.println("Loi: Ma nha cung cap " + pnh.getMaNhaCungCap() + " khong ton tai trong he thong.");
            System.out.print("Vui long nhap lai ma nha cung cap: ");
            String newMaNCC = scanner.nextLine().trim();
            pnh.setMaNhaCungCap(newMaNCC);
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
        QLNV qlNV = new QLNV(); // Initialize employee system
        DSPNH dsPNH = new DSPNH(dsCT, qlNV); // Pass both parameters
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
