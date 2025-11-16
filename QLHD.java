import java.util.Scanner;
import java.io.*;

public class QLHD {
    private DSHD dshd;
    private DSCTHD dscthd;
    private Scanner sc = new Scanner(System.in);
    
    public QLHD() {
        this.dscthd = new DSCTHD();
        this.dshd = new DSHD(dscthd);
    }
    
    public QLHD(DSCTHD dscthd) {
        this.dscthd = dscthd;
        this.dshd = new DSHD(dscthd);
    }
    
    public DSCTHD getDSCTHD() {
        return dscthd;
    }
    
    public void menu() {
        int chon;
        do {
            System.out.println("\n=== QUAN LY HOA DON ===");
            System.out.println("1. Them hoa don");
            System.out.println("2. Xuat danh sach hoa don");
            System.out.println("3. Sua hoa don");
            System.out.println("4. Xoa hoa don");
            System.out.println("5. Tim kiem theo ma HD");
            System.out.println("6. Tim kiem theo ma KH");
            System.out.println("7. Tim kiem theo ma NV");
            System.out.println("8. Ghi file");
            System.out.println("0. Thoat");
            System.out.print("Chon: ");
            chon = sc.nextInt();
            sc.nextLine();
            
            switch(chon) {
                case 1:
                    themHoaDon();
                    break;
                case 2:
                    xuatDSHD();
                    break;
                case 3:
                    suaHoaDon();
                    break;
                case 4:
                    xoa();
                    break;
                case 5:
                    timTheoMaHD();
                    break;
                case 6:
                    timTheoMaKH();
                    break;
                case 7:
                    timTheoMaNV();
                    break;
                case 8:
                    ghiFile();
                    break;
                case 0:
                    System.out.println("Tam biet!");
                    break;
                default:
                    System.out.println("Lua chon khong hop le!");
            }
        } while(chon != 0);
    }
    
    public void themHoaDon() {
        dshd.them();
    }
    
    public void xuatDSHD() {
        System.out.println("\n=== DANH SACH HOA DON ===");
        System.out.printf("%-20s%-20s%-20s%-15s%-15s\n", 
                         "MaHD", "MaNV", "MaKH", "NgayLap", "TongTien");
        dshd.hienThiDS();
    }
    
    public void suaHoaDon() {
        dshd.sua();
    }

    public void xoa() {
        dshd.xoa();
    }
    
    public void timTheoMaHD() {
        dshd.timnKiemTheoMaHD();
    }
    
    public void timTheoMaKH() {
        dshd.timKiemTheoMaKH();
    }
    
    public void timTheoMaNV() {
        dshd.timKiemTheoMaNV();
    }
    
    public void ghiFile() {
        try {
            dshd.ghiFile("hoadon.txt");
            System.out.println("Ghi file hoa don thanh cong!");
        } catch(IOException e) {
            System.out.println("Loi khi ghi file: " + e.getMessage());
        }
    }
    
    public void docFile() {
        try {
            dshd.docFile("hoadon.txt");
            System.out.println("Doc file hoa don thanh cong!");
        } catch(IOException | ClassNotFoundException e) {
            System.out.println("Loi khi doc file: " + e.getMessage());
        }
    }
    
    public static void main(String[] args) {
        QLHD qlhd = new QLHD();
        qlhd.menu();
    }
}