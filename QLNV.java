import java.io.*;
import java.util.Scanner;

public class QLNV {
    Scanner sc = new Scanner(System.in);
    private DSNV dsnv = new DSNV();

    public DSNV getDSNV() {
        return dsnv;
    }

    public void timKiemNV() {
        System.out.println("// TIM KIEM NHAN VIEN //");
        System.out.println("1. Tim kiem theo ma");
        System.out.println("2. Tim kiem theo ho");
        System.out.println("3. Tim kiem theo ten");
        System.out.print("Lua chon: ");

        int t = Integer.parseInt(sc.nextLine());
        if(t==1){
            dsnv.timKiemTheoMaNV();
        }
        else if(t==2){
            dsnv.timKiemTheoHoNV();
        }
        else{
            dsnv.timKiemTheoTenNV();
        }
    }

    public void cacThongKe() {
        System.out.println("// THONG KE //");
        System.out.println("1. Thong ke theo GT");
        System.out.println("2. Thong ke theo tuoi");
        System.out.print("Lua chon: ");
        int k = Integer.parseInt(sc.nextLine());
        if(k==1){
            dsnv.thongKeTheoGT();
        } 
        else{
            dsnv.thongKeTheoTuoi();
        }
    }

    public void docTuFile(String fileName) {
        try {
            dsnv.docFile(fileName);
            System.out.println("Da tai du lieu tu file thanh cong!");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Loi khi doc file: " + e.getMessage());
        }
    }
    
    public void ghiVaoFile(String fileName) {
        try {
            dsnv.ghiFile(fileName);
            System.out.println("Da luu du lieu vao file thanh cong!");
        } catch (IOException e) {
            System.out.println("Loi khi ghi file: " + e.getMessage());
        }
    }
    
    public void menu() {
        while(true){
            System.out.println("\n// QUAN LY NHAN VIEN //");
            System.out.println("1. Hien thi DSNV");
            System.out.println("2. Them NV");
            System.out.println("3. Xoa NV");
            System.out.println("4. Sua thong tin NV");
            System.out.println("5. Tim kiem NV");
            System.out.println("6. Thong ke");
            System.out.println("7. Doc tu file");
            System.out.println("8. Luu vao file");
            System.out.println("9. Thoat");
            System.out.print("Lua chon: ");

            int chon = Integer.parseInt(sc.nextLine());
             switch (chon) {
                    case 1:
                        dsnv.hienThiDS();
                        break;
                    case 2:
                        dsnv.them();
                        break;
                    case 3:
                        dsnv.xoa();
                        break;
                    case 4:
                        dsnv.sua();
                        break;
                    case 5:
                        timKiemNV();
                        break;
                    case 6:
                        cacThongKe();
                        break;
                    case 7:
                        docTuFile("dsnv.txt");
                        break;
                    case 8:
                        ghiVaoFile("dsnv.txt");
                        break;
                    case 9:
                        System.out.println("\n exit");
                        return;
                    default:
                        System.out.println("Lua chon khong hop le!");
                }
        }
    }

    public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    QLNV qlnv = new QLNV();
    qlnv.menu();
    sc.close();
}

}
