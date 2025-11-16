import java.io.*;
import java.util.Scanner;

public class QLKH {
    private DSKH dskh = new DSKH();

    public DSKH getDSKH() {
        return dskh;
    }

    public void docTuFile(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                
                String[] parts = line.split(";");
                if (parts.length >= 4) {
                    String maKH = parts[0].trim();
                    String hoKH = parts[1].trim();
                    String sdt = parts[2].trim();
                    String diaChi = parts[3].trim();
                    
                    KhachHang kh = new KhachHang(maKH, hoKH, sdt, diaChi);
                    dskh.them(kh);
                }
            }
            System.out.println("Da tai du lieu tu file thanh cong!");
        } catch (IOException e) {
            System.out.println("Loi khi doc file: " + e.getMessage());
        }
    }
    
    public void ghiVaoFile(String fileName) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(fileName))) {
            for (int i = 0; i < dskh.soLuongKH(); i++) {
                KhachHang kh = dskh.getKhachHang(i);
                pw.printf("%s;%s;%s;%s\n",
                    kh.getMaKH(), kh.getHoKH(), kh.getSDT(), kh.getDiaChi());
            }
            System.out.println("Da luu du lieu vao file thanh cong!");
        } catch (IOException e) {
            System.out.println("Loi khi ghi file: " + e.getMessage());
        }
    }

    public void menu(Scanner sc) {
        while (true) {
            System.out.println("\n// QUAN LY KHACH HANG //");
            System.out.println("1. Hien thi danh sach khach hang");
            System.out.println("2. Them khach hang");
            System.out.println("3. Xoa khach hang");
            System.out.println("4. Sua thong tin khach hang");
            System.out.println("5. Tim kiem khach hang");
            System.out.println("6. Thong ke khach hang");
            System.out.println("7. Luu vao file");
            System.out.println("8. Thoat");
            System.out.print("Lua chon: ");

            try {
                int chon = Integer.parseInt(sc.nextLine());
                switch (chon) {
                    case 1:
                        dskh.hienThiDS();
                        break;
                    case 2:
                        dskh.them(sc);
                        break;
                    case 3:
                        dskh.xoa("", sc);
                        break;
                    case 4:
                        dskh.sua("", sc);
                        break;
                    case 5:
                        dskh.timKiem(sc);
                        break;
                    case 6:
                        dskh.thongKe();
                        break;
                    case 7:
                        ghiVaoFile("dskh.txt");
                        break;
                    case 8:
                        System.out.println("\nCam on ban da su dung chuong trinh!");
                        return;
                    default:
                        System.out.println("Lua chon khong hop le!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Vui long nhap so!");
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        QLKH qlkh = new QLKH();
        qlkh.docTuFile("dskh.txt");
        qlkh.menu(sc);
        sc.close();
    }
}