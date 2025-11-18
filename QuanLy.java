import java.util.Scanner;
import java.io.*;

public class QuanLy {
    private static Scanner sc = new Scanner(System.in);
    public static QLDienThoai qlDT = new QLDienThoai();
    public static QLNV qlNV = new QLNV();
    public static QLKH qlKH = new QLKH();
    public static QLCTHD qlCTHD = new QLCTHD();
    public static QLHD qlHD = new QLHD(qlCTHD.getDSCTHD());
    
    // Management modules for suppliers, purchase orders, and purchase order details
    public static DSNCC qlNCC = new DSNCC();
    public static DSCTPNH qlCTPNH = new DSCTPNH();
    public static DSPNH qlPNH = new DSPNH(qlCTPNH);
    
    public static void main(String[] args) {
        // Tải dữ liệu từ các file
        loadData();
        
        // Menu chính
        while(true) {
            System.out.println("\n=== HE THONG QUAN LY CUA HANG DIEN THOAI ===");
            System.out.println("1. Quan ly Dien Thoai");
            System.out.println("2. Quan ly Nhan Vien");
            System.out.println("3. Quan ly Khach Hang");
            System.out.println("4. Quan ly Hoa Don");
            System.out.println("5. Quan ly Chi Tiet Hoa Don");
            System.out.println("6. Quan ly Nha Cung Cap");
            System.out.println("7. Quan ly Phieu Nhap Hang");
            System.out.println("8. Quan ly Chi Tiet Phieu Nhap");
            System.out.println("0. Thoat");
            System.out.print("Chon: ");
            
            try {
                int chon = Integer.parseInt(sc.nextLine());
                switch(chon) {
                    case 1:
                        qlDT.menu();
                        break;
                    case 2:
                        qlNV.menu();
                        break;
                    case 3:
                        qlKH.menu(sc);
                        break;
                    case 4:
                        qlHD.menu();
                        break;
                    case 5:
                        qlCTHD.menu();
                        break;
                    case 6:
                        menuNhaCungCap();
                        break;
                    case 7:
                        menuPhieuNhapHang();
                        break;
                    case 8:
                        menuChiTietPhieuNhap();
                        break;
                    case 0:
                        // Lưu dữ liệu trước khi thoát
                        saveData();
                        System.out.println("Tam biet!");
                        System.exit(0);
                    default:
                        System.out.println("Lua chon khong hop le!");
                }
            } catch(NumberFormatException e) {
                System.out.println("Vui long nhap so!");
            }
        }
    }
    
    private static void loadData() {
        System.out.println("=== TAI DU LIEU TU FILE ===");
        
        // Tải dữ liệu điện thoại
        try {
            qlDT.docTuFile("dsdt.txt");
        } catch(Exception e) {
            System.out.println("Khong the tai file dsdt.txt: " + e.getMessage());
        }
        
        // Tải dữ liệu nhân viên
        try {
            qlNV.docTuFile("dsnv.txt");
        } catch(Exception e) {
            System.out.println("Khong the tai file dsnv.txt: " + e.getMessage());
        }
        
        // Tải dữ liệu khách hàng
        try {
            qlKH.docTuFile("dskh.txt");
        } catch(Exception e) {
            System.out.println("Khong the tai file dskh.txt: " + e.getMessage());
        }
        
        // Tải dữ liệu hóa đơn
        try {
            qlHD.docFile();
        } catch(Exception e) {
            System.out.println("Khong the tai file hoadon.txt: " + e.getMessage());
        }
        
        // Tải dữ liệu chi tiết hóa đơn
        try {
            qlCTHD.docFile();
        } catch(Exception e) {
            System.out.println("Khong the tai file cthd.txt: " + e.getMessage());
        }
        
        // Tải dữ liệu nhà cung cấp
        try {
            qlNCC.docTatCaTuFile();
        } catch(Exception e) {
            System.out.println("Khong the tai du lieu NCC: " + e.getMessage());
        }
        
        // DSPNH và DSCTPNH sẽ tự động tải trong constructor
        
        System.out.println("Da tai xong du lieu!");
    }
    
    private static void saveData() {
        System.out.println("\n=== LUU DU LIEU XUONG FILE ===");
        
        // Lưu dữ liệu điện thoại
        try {
            qlDT.ghiVaoFile("dsdt.txt");
        } catch(Exception e) {
            System.out.println("Loi khi luu file dsdt.txt: " + e.getMessage());
        }
        
        // Lưu dữ liệu nhân viên
        try {
            qlNV.ghiVaoFile("dsnv.txt");
        } catch(Exception e) {
            System.out.println("Loi khi luu file dsnv.txt: " + e.getMessage());
        }
        
        // Lưu dữ liệu khách hàng
        try {
            qlKH.ghiVaoFile("dskh.txt");
        } catch(Exception e) {
            System.out.println("Loi khi luu file dskh.txt: " + e.getMessage());
        }
        
        // Lưu dữ liệu hóa đơn
        try {
            qlHD.ghiFile();
        } catch(Exception e) {
            System.out.println("Loi khi luu file hoadon.txt: " + e.getMessage());
        }
        
        // Lưu dữ liệu chi tiết hóa đơn
        try {
            qlCTHD.ghiFile();
        } catch(Exception e) {
            System.out.println("Loi khi luu file cthd.txt: " + e.getMessage());
        }
        
        // Lưu dữ liệu nhà cung cấp
        try {
            qlNCC.ghiTatCaVaoFile();
        } catch(Exception e) {
            System.out.println("Loi khi luu file NCC.txt: " + e.getMessage());
        }
        
        // Lưu dữ liệu chi tiết phiếu nhập
        try {
            qlCTPNH.ghiTatCaVaoFile();
        } catch(Exception e) {
            System.out.println("Loi khi luu file CTPNH.txt: " + e.getMessage());
        }
        
        System.out.println("Da luu xong du lieu!");
    }
    
    // Menu for Supplier Management
    private static void menuNhaCungCap() {
        while(true) {
            System.out.println("\n=== QUAN LY NHA CUNG CAP ===");
            System.out.println("1. Them Nha Cung Cap");
            System.out.println("2. Hien thi danh sach Nha Cung Cap");
            System.out.println("3. Sua thong tin Nha Cung Cap");
            System.out.println("4. Xoa Nha Cung Cap");
            System.out.println("0. Quay lai menu chinh");
            System.out.print("Chon: ");
            
            try {
                int chon = Integer.parseInt(sc.nextLine());
                switch(chon) {
                    case 1:
                        qlNCC.themNhaCungCap(sc);
                        break;
                    case 2:
                        qlNCC.hienThiTatCa();
                        break;
                    case 3:
                        qlNCC.suaNhaCungCap(sc);
                        break;
                    case 4:
                        qlNCC.xoaNhaCungCap(sc);
                        break;
                    case 0:
                        return;
                    default:
                        System.out.println("Lua chon khong hop le!");
                }
            } catch(NumberFormatException e) {
                System.out.println("Vui long nhap so!");
            }
        }
    }
    
    // Menu for Purchase Order Management
    private static void menuPhieuNhapHang() {
        while(true) {
            System.out.println("\n=== QUAN LY PHIEU NHAP HANG ===");
            System.out.println("1. Them Phieu Nhap");
            System.out.println("2. Hien thi danh sach Phieu Nhap");
            System.out.println("3. Sua thong tin Phieu Nhap");
            System.out.println("4. Xoa Phieu Nhap");
            System.out.println("0. Quay lai menu chinh");
            System.out.print("Chon: ");
            
            try {
                int chon = Integer.parseInt(sc.nextLine());
                switch(chon) {
                    case 1:
                        qlPNH.themPhieuNhap(sc);
                        break;
                    case 2:
                        qlPNH.hienThiTatCa();
                        break;
                    case 3:
                        qlPNH.suaPhieuNhap(sc);
                        break;
                    case 4:
                        qlPNH.xoaPhieuNhap(sc);
                        break;
                    case 0:
                        return;
                    default:
                        System.out.println("Lua chon khong hop le!");
                }
            } catch(NumberFormatException e) {
                System.out.println("Vui long nhap so!");
            }
        }
    }
    
    // Menu for Purchase Order Detail Management
    private static void menuChiTietPhieuNhap() {
        while(true) {
            System.out.println("\n=== QUAN LY CHI TIET PHIEU NHAP ===");
            System.out.println("1. Them Chi Tiet Phieu Nhap");
            System.out.println("2. Hien thi chi tiet theo ma phieu");
            System.out.println("3. Sua Chi Tiet Phieu Nhap");
            System.out.println("4. Xoa Chi Tiet Phieu Nhap");
            System.out.println("5. Hien thi tat ca chi tiet");
            System.out.println("0. Quay lai menu chinh");
            System.out.print("Chon: ");
            
            try {
                int chon = Integer.parseInt(sc.nextLine());
                switch(chon) {
                    case 1:
                        // Create a new ChiTietPhieuNhapHang object first
                        ChiTietPhieuNhapHang ct = new ChiTietPhieuNhapHang();
                        ct.nhapThongTin(sc);
                        qlCTPNH.themChiTiet(ct);
                        break;
                    case 2:
                        System.out.print("Nhap ma phieu: ");
                        String maPhieu = sc.nextLine();
                        qlCTPNH.hienThiChiTiet(maPhieu);
                        break;
                    case 3:
                        qlCTPNH.suaChiTiet(sc);
                        break;
                    case 4:
                        System.out.print("Nhap ma phieu can xoa tat ca chi tiet: ");
                        String maPhieuXoa = sc.nextLine();
                        qlCTPNH.xoaTatCaTheoMaPhieu(maPhieuXoa);
                        break;
                    case 5:
                        qlCTPNH.hienThiTatCa();
                        break;
                    case 0:
                        return;
                    default:
                        System.out.println("Lua chon khong hop le!");
                }
            } catch(NumberFormatException e) {
                System.out.println("Vui long nhap so!");
            }
        }
    }
}
