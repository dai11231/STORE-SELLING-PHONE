import java.io.*;
import java.util.Scanner;

public class QLDienThoai {
    private DSDT dsdt = new DSDT();
    private Scanner sc = new Scanner(System.in);


      public DSDT getDSDT() {
        return dsdt;
    }
    public void docTuFile(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                
                String[] parts = line.split(";");
                if (parts.length >= 6) {
                    String maDT = parts[0].trim();
                    String tenDT = parts[1].trim();
                    String hangSX = parts[2].trim();
                    double donGia = Double.parseDouble(parts[3].trim());
                    int soLuongKho = Integer.parseInt(parts[4].trim());
                    String loaiDT = parts[5].trim();
                    
                    DienThoai dt;
                    if (loaiDT.equals("ThongMinh") && parts.length >= 9) {
                        String tanSoQuet = parts[6].trim();
                        String camBienVanTay = parts[7].trim();
                        String camTruoc = parts[8].trim();
                        dt = new DienThoaiThongMinh(maDT, tenDT, hangSX, donGia, soLuongKho,
                                                  tanSoQuet, camBienVanTay, camTruoc);
                    } else if (loaiDT.equals("CoDien") && parts.length >= 8) {
                        int soPhimVatLy = Integer.parseInt(parts[6].trim());
                        String loaiPhim = parts[7].trim();
                        dt = new DienThoaiCoDien(maDT, tenDT, hangSX, donGia, soLuongKho,
                                               soPhimVatLy, loaiPhim);
                    } else {
                        continue;
                    }
                    dsdt.them(dt);
                }
            }
            System.out.println("Da tai du lieu tu file thanh cong!");
            
        } catch (IOException e) {
            System.out.println("Loi khi doc file: " + e.getMessage());
        }
    }
    
    public void ghiVaoFile(String fileName) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(fileName))) {
            for (int i = 0; i < dsdt.soLuongDT(); i++) {
                DienThoai dt = dsdt.getDienThoai(i);
                if (dt instanceof DienThoaiThongMinh) {
                    DienThoaiThongMinh dtm = (DienThoaiThongMinh) dt;
                    pw.printf("%s;%s;%s;%.2f;%d;%s;%s;%s;%s\n",
                        dt.getMaDT(), dt.getTenDT(), dt.getHangSX(), dt.getDonGia(),
                        dt.getsoLuongKho(), dt.getLoaiDT(),
                        dtm.getTanSoQuet(), dtm.getCamBienVanTay(), dtm.getCamTruoc());
                } else if (dt instanceof DienThoaiCoDien) {
                    DienThoaiCoDien dtcd = (DienThoaiCoDien) dt;
                    pw.printf("%s;%s;%s;%.2f;%d;%s;%d;%s\n",
                        dt.getMaDT(), dt.getTenDT(), dt.getHangSX(), dt.getDonGia(),
                        dt.getsoLuongKho(), dt.getLoaiDT(),
                        dtcd.getSoPhimVatLy(), dtcd.getLoaiPhim());
                }
            }
            System.out.println("Da luu du lieu vao file thanh cong!");
        } catch (IOException e) {
            System.out.println("Loi khi ghi file: " + e.getMessage());
        }
    }
    
    private void menuLoaiDT() {
        while (true) {
            System.out.println("\n// CHON LOAI DIEN THOAI //");
            System.out.println("1. Dien thoai thong minh");
            System.out.println("2. Dien thoai co dien");
            System.out.println("3. Quay lai");
            System.out.print("Lua chon: ");
            
            int chon = Integer.parseInt(sc.nextLine());
            switch (chon) {
                case 1:
                    dsdt.them("ThongMinh");
                    return;
                case 2:
                    dsdt.them("CoDien");
                    return;
                case 3:
                    return;
                default:
                    System.out.println("Lua chon khong hop le!");
            }
        }
    }

    public void menu() {
        while (true) {
            System.out.println("\n// QUAN LY DIEN THOAI //");
            System.out.println("1. Hien thi danh sach dien thoai");
            System.out.println("2. Them dien thoai moi");
            System.out.println("3. Xoa dien thoai");
            System.out.println("4. Sua thong tin dien thoai");
            System.out.println("5. Tim kiem dien thoai");
            System.out.println("6. Thong ke");
            System.out.println("7. Doc tu file");
            System.out.println("8. Luu vao file");
            System.out.println("9. Thoat");
            System.out.print("Lua chon: ");

            try {
                int chon = Integer.parseInt(sc.nextLine());
                switch (chon) {
                    case 1:
                        dsdt.hienThiDS();
                        break;
                    case 2:
                        menuLoaiDT();
                        break;
                    case 3:
                        dsdt.xoa();
                        break;
                    case 4:
                        dsdt.sua();
                        break;
                    case 5:
                        dsdt.timKiem();
                        break;
                    case 6:
                        dsdt.thongKe();
                        break;
                    case 7:
                        docTuFile("dsdt.txt");
                        break;
                    case 8:
                        ghiVaoFile("dsdt.txt");
                        break;
                    case 9:
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
        QLDienThoai qldt = new QLDienThoai();
        qldt.menu();
    }
}