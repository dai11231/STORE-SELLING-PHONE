import java.util.Scanner;
import java.io.*;

public class QLCTHD {
    private DSCTHD dscthd = new DSCTHD();
    private Scanner sc = new Scanner(System.in);
    
    public DSCTHD getDSCTHD() {
        return dscthd;
    }
    
    public void menu() {  
        int chon;
        do {
            System.out.println("\n=== QUAN LY CHI TIET HOA DON ===");
            System.out.println("1. Xuat danh sach chi tiet hoa don");
            System.out.println("2. Sua chi tiet hoa don");
            System.out.println("3. Xoa chi tiet hoa don");
            System.out.println("4. Tim kiem theo ma HD");
            System.out.println("5. Ghi file");
            System.out.println("0. Thoat");
            System.out.print("Chon: ");
            chon = sc.nextInt();
            sc.nextLine();
            
            switch(chon) {
                case 1:
                    xuatDSCTHD();
                    break;
                case 2:
                    suaChiTietHD();
                    break;
                case 3:
                    xoaChiTietHD();
                    break;
                case 4:
                    timKiemTheoMaHD();
                    break;
                case 5:
                    ghiFile();
                    break;
                case 0:
                    // Tự động ghi file khi thoát
                    ghiFile();
                    System.out.println("Tam biet!");
                    break;
                default:
                    System.out.println("Lua chon khong hop le!");
            }
        } while (chon != 0);
    }
    
    public void xuatDSCTHD() {
        System.out.println("\n--- DANH SACH CHI TIET HOA DON ---");
        System.out.printf("%-10s %-10s %-10s %-15s %-15s\n", 
                         "MaHD", "MaDT", "SL", "DonGia", "ThanhTien");
        for(int i = 0; i < dscthd.n; i++) {
            if(dscthd.dscthd[i] != null) {
                dscthd.dscthd[i].xuat();
            }
        }
    }
    
    public void suaChiTietHD() {
        System.out.println("\n--- SUA CHI TIET HOA DON ---");
        dscthd.sua();
    }
    
    public void xoaChiTietHD() {
        System.out.println("\n--- XOA CHI TIET HOA DON ---");
        dscthd.xoa();
    }
    
    public void timKiemTheoMaHD() {
        System.out.println("\n--- TIM KIEM CHI TIET THEO MA HOA DON ---");
        dscthd.timKiemTheoMaHD();
    }
    
    public void ghiFile() {
        try {
            dscthd.ghiFile("cthd.txt");
            System.out.println("Ghi file hoa don thanh cong!");
        } catch(IOException e) {
            System.out.println("Loi khi ghi file: " + e.getMessage());
        }
    }
    
    public void docFile() {
        try {
            dscthd.docFile("cthd.txt");
            System.out.println("Doc file hoa don thanh cong!");
        } catch(IOException | ClassNotFoundException e) {
            System.out.println("Loi khi doc file: " + e.getMessage());
        }
    }
    
    public static void main(String[] args) {
        QLCTHD qlcthd = new QLCTHD();
        qlcthd.docFile();
        qlcthd.menu();
    }
}
