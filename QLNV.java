import java.io.*;
import java.util.Scanner;

public class QLNV {
    private DSNV dsnv = new DSNV();

    public void timKiemNV(Scanner sc){
        System.out.println("// TIM KIEM NHAN VIEN //");
        System.out.println("1. Tim kiem theo ma");
        System.out.println("2. Tim kiem theo ho");
        System.out.println("3. Tim kiem theo ten");
        System.out.print("Lua chon: ");

        int t = Integer.parseInt(sc.nextLine());
        if(t==1){
            dsnv.timKiemTheoMaNV("", sc);
        }
        else if(t==2){
            dsnv.timKiemTheoHoNV("", sc);
        }
        else{
            dsnv.timKiemTheoTenNV("", sc);
        }
    }

    public void cacThongKe(Scanner sc){
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
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            int count = 0;
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    count++;
                }
            }
            br.close();
            
            NhanVien[] danhSachMoi = new NhanVien[count];
            int index = 0;
            
            try (BufferedReader br2 = new BufferedReader(new FileReader(fileName))) {
                while ((line = br2.readLine()) != null) {
                    if (line.trim().isEmpty()) continue;
                    
                    String[] parts = line.split(";");
                    if (parts.length >= 6) {
                        String maNV = parts[0].trim();
                        String hoNV = parts[1].trim();
                        String tenNV = parts[2].trim();
                        String gioiTinh = parts[3].trim();
                        String birth = parts[4].trim();
                        double luong = Double.parseDouble(parts[5].trim());
                        
                        danhSachMoi[index++] = new NhanVien(maNV, hoNV, tenNV, gioiTinh, birth, luong);
                    }
                }
            }
            
            dsnv = new DSNV();
            for (int i = 0; i < index; i++) {
                dsnv.them(danhSachMoi[i]);
            }
            System.out.println("Da tai du lieu tu file thanh cong!");
            
        } catch (IOException e) {
            System.out.println("Loi khi doc file: " + e.getMessage());
        }
    }
    
    public void ghiVaoFile(String fileName) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(fileName))) {
            for (int i = 0; i < dsnv.soLuongNV(); i++) {
                NhanVien nv = dsnv.getNhanVien(i);
                pw.printf("%s;%s;%s;%s;%s;%.2f\n",
                    nv.getMaNV(),
                    nv.getHoNV(),
                    nv.getTenNV(),
                    nv.getGioiTinh(),
                    nv.getBirth(),
                    nv.getLuong());
            }
            System.out.println("Da luu du lieu vao file thanh cong!");
        } catch (IOException e) {
            System.out.println("Loi khi ghi file: " + e.getMessage());
        }
    }
    
    public void menu(Scanner sc){
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
                        dsnv.them(sc);
                        break;
                    case 3:
                        dsnv.xoa("", sc);
                        break;
                    case 4:
                        dsnv.sua("", sc);
                        break;
                    case 5:
                        timKiemNV(sc);
                        break;
                    case 6:
                        cacThongKe(sc);
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
    qlnv.menu(sc);
    sc.close();
}

}
