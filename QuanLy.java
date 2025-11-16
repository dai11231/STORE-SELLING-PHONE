import java.util.Scanner;
import java.io.*;

public class QuanLy {
    private static Scanner sc = new Scanner(System.in);
    public static QLDienThoai qlDT = new QLDienThoai();
    public static QLNV qlNV = new QLNV();
    public static QLKH qlKH = new QLKH();
    public static QLCTHD qlCTHD = new QLCTHD();
    public static QLHD qlHD = new QLHD(qlCTHD.getDSCTHD());
    
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
        
        System.out.println("Da luu xong du lieu!");
    }
}
