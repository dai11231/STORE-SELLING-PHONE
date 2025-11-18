import java.util.Scanner;
import java.util.Arrays;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;

public class DSNCC {
    private NhaCungCap[] danhSach;
    private int soLuongHienTai;
    private final int KICH_THUOC_BAN_DAU = 10;
    private final String TEN_FILE = "NCC.txt"; 

    public DSNCC() {
        this.danhSach = new NhaCungCap[KICH_THUOC_BAN_DAU];
        this.soLuongHienTai = 0;
        docTatCaTuFile(); 
    }

    private void tangKichThuoc() {
        if (soLuongHienTai == danhSach.length) {
            int kichThuocMoi = danhSach.length * 2;
            danhSach = Arrays.copyOf(danhSach, kichThuocMoi);
        }
    }

    // --- Check unique supplier code ---
    public boolean maNCCDuyNhat(String maNCC) {
        for (int i = 0; i < soLuongHienTai; i++) {
            if (danhSach[i].getMaNCC().equalsIgnoreCase(maNCC)) return true;
        }
        return false;
    }
    
    // --- 1. THÊM (CREATE) ---
    public void themNhaCungCap(Scanner scanner) {
        tangKichThuoc();
        NhaCungCap ncc = new NhaCungCap();
        ncc.nhapThongTin(scanner);
        
        // Check for unique supplier code with retry mechanism
        while (maNCCDuyNhat(ncc.getMaNCC())) {
            System.out.println("Loi: Ma nha cung cap " + ncc.getMaNCC() + " da ton tai.");
            System.out.print("Vui long nhap lai ma nha cung cap: ");
            String newMaNCC = scanner.nextLine().trim();
            ncc.setMaNCC(newMaNCC);
        }
        
        danhSach[soLuongHienTai] = ncc;
        soLuongHienTai++;
        System.out.println("Them Nha Cung Cap thanh cong.");
        ghiTatCaVaoFile(); 
    }
    
    // --- 2. TÌM KIẾM/HIỂN THỊ (READ) ---
    public NhaCungCap timTheoMa(String maNCC) {
        for (int i = 0; i < soLuongHienTai; i++) {
            if (danhSach[i] != null && danhSach[i].getMaNCC().equalsIgnoreCase(maNCC)) {
                return danhSach[i];
            }
        }
        return null;
    }
    
    public void hienThiTatCa() {
        if (soLuongHienTai == 0) {
            System.out.println("Danh sach Nha Cung Cap trong.");
            return;
        }
        System.out.println("===== DANH SACH NHA CUNG CAP (" + soLuongHienTai + " muc) =====");
        for (int i = 0; i < soLuongHienTai; i++) {
            if (danhSach[i] != null)
                System.out.println(danhSach[i].toString());
        }
        System.out.println("===================================");
    }

    // --- 3. SỬA (UPDATE) ---
    public void suaNhaCungCap(Scanner scanner) {
        System.out.print("Nhap Ma NCC can sua: ");
        String maNCC = scanner.nextLine();
        NhaCungCap nccCanSua = timTheoMa(maNCC);

        if (nccCanSua == null) {
            System.out.println("Khong tim thay Nha Cung Cap voi ma: " + maNCC);
            return;
        }

        System.out.println("Tim thay: " + nccCanSua.toString());
        
        System.out.print("Nhap Ten moi (Enter de giu nguyen: " + nccCanSua.getTenNCC() + "): ");
        String tenMoi = scanner.nextLine();
        if (!tenMoi.trim().isEmpty()) {
            nccCanSua.setTenNCC(tenMoi);
        }
        
        System.out.print("Nhap SDT moi (Enter de giu nguyen: " + nccCanSua.getSdt() + "): ");
        String sdtMoi = scanner.nextLine();
        if (!sdtMoi.trim().isEmpty()) {
            // ĐÃ SỬA LỖI ĐỎ: Dùng nccCanSua
            nccCanSua.setSdt(sdtMoi); 
        }
        
        System.out.println("Sua Nha Cung Cap thanh cong.");
        ghiTatCaVaoFile(); 
    }
    
    // --- 4. XÓA (DELETE) ---
    public void xoaNhaCungCap(Scanner scanner) {
        System.out.print("Nhap Ma NCC can xoa: ");
        String maNCC = scanner.nextLine();
        
        int viTriCanXoa = -1;
        for (int i = 0; i < soLuongHienTai; i++) {
            if (danhSach[i] != null && danhSach[i].getMaNCC().equalsIgnoreCase(maNCC)) {
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

            System.out.println("Da xoa Nha Cung Cap voi ma: " + maNCC);
            ghiTatCaVaoFile(); 
        } else {
            System.out.println("Khong tim thay Nha Cung Cap voi ma: " + maNCC);
        }
    }
    
    // --- 5. GHI FILE (SAVE) ---
    public void ghiTatCaVaoFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(TEN_FILE, false))) {
            for (int i = 0; i < soLuongHienTai; i++) {
                if (danhSach[i] != null) // Kiểm tra null trước khi gọi hàm
                    danhSach[i].ghiFile(writer); 
            }
        } catch (IOException e) {
             System.err.println("❌ Lỗi khi ghi file NCC: " + e.getMessage());
        }
    }
    
    // --- 6. ĐỌC FILE (LOAD) ---
    public void docTatCaTuFile() {
        try (Scanner fileScanner = new Scanner(new File(TEN_FILE))) {
            while (fileScanner.hasNextLine()) {
                String duLieu = fileScanner.nextLine();
                tangKichThuoc(); 
                
                NhaCungCap ncc = new NhaCungCap();
                ncc.docFile(duLieu); 

                if (ncc.getMaNCC() != null && !ncc.getMaNCC().trim().isEmpty() && timTheoMa(ncc.getMaNCC()) == null) {
                    danhSach[soLuongHienTai] = ncc;
                    soLuongHienTai++;
                }
            }
        } catch (IOException e) {
             if (soLuongHienTai == 0) {
                 System.out.println("File NCC chua ton tai. Bat dau voi danh sach trong.");
             } else {
                 System.err.println("Loi khi doc file NCC: " + e.getMessage());
             }
        }
    }
}