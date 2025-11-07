import java.util.Arrays;
import java.util.Scanner;

public class DSKH {
    private KhachHang[] dskh = new KhachHang[0];
    private int n = 0;

    public boolean maKHDuyNhat(String maKH) {
        for (int i = 0; i < n; i++) {
            if (dskh[i].getMaKH().equalsIgnoreCase(maKH)) return true;
        }
        return false;
    }
    
    public void them(Scanner sc) {
        System.out.print("Nhap so luong khach hang can nhap: ");
        int sl = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < sl; i++) {
            System.out.println("Nhap thong tin khach hang thu " + (i + 1) + ": ");
            KhachHang kh = new KhachHang();
            kh.nhap(sc);
            while (maKHDuyNhat(kh.getMaKH())) {
                System.out.println("Loi: Ma khach hang " + kh.getMaKH() + " da ton tai.");
                System.out.print("Vui long nhap lai ma khach hang: ");
                String newMaKH = sc.nextLine().trim();
                kh.setMaKH(newMaKH);
            }
            dskh = Arrays.copyOf(dskh, n + 1);
            dskh[n] = kh;
            n++;
        }
    }
    
    public void them(KhachHang kh) {
        if (!maKHDuyNhat(kh.getMaKH())) {
            dskh = Arrays.copyOf(dskh, n + 1);
            dskh[n] = kh;
            n++;
        }
    }
    
    public int soLuongKH() {
        return n;
    }
    
    public KhachHang getKhachHang(int index) {
        if (index >= 0 && index < n) {
            return dskh[index];
        }
        return null;
    }

    public void hienThiDS() {
        if (n == 0) {
            System.out.println("Danh sach trong!");
            return;
        }
        System.out.println("\nDanh sach khach hang:");
        System.out.println("Ma KH     Ho ten                    SDT            Dia chi");
        System.out.println("----------------------------------------------------------------");
        for (int i = 0; i < n; i++) {
            dskh[i].xuat();
        }
    }

    public void xoa(String maKH, Scanner sc) {
        System.out.print("Nhap ma khach hang can xoa: ");
        maKH = sc.nextLine();
        for (int i = 0; i < n; i++) {
            if (dskh[i].getMaKH().equalsIgnoreCase(maKH)) {
                for (int j = i; j < n - 1; j++) {
                    dskh[j] = dskh[j + 1];
                }
                n--;
                dskh = Arrays.copyOf(dskh, n);
                System.out.println("Da xoa khach hang co ma " + maKH);
                return;
            }
        }
        System.out.println("Khong tim thay khach hang co ma " + maKH);
    }

    public void sua(String maKH, Scanner sc) {
        System.out.print("Nhap ma khach hang can sua: ");
        maKH = sc.nextLine();
        
        for (int i = 0; i < n; i++) {
            if (dskh[i].getMaKH().equalsIgnoreCase(maKH)) {
                System.out.println("\nSua thong tin khach hang: " + maKH);
                System.out.println("(Nhan Enter de bo qua)");
                
                System.out.print("Ho ten moi: ");
                String hoTen = sc.nextLine();
                if (!hoTen.isEmpty()) dskh[i].setHoKH(hoTen);
                
                System.out.print("So dien thoai moi: ");
                String sdt = sc.nextLine();
                if (!sdt.isEmpty()) dskh[i].setSDT(sdt);
                
                System.out.print("Dia chi moi: ");
                String diaChi = sc.nextLine();
                if (!diaChi.isEmpty()) dskh[i].setDiaChi(diaChi);
                
                System.out.println("Da cap nhat thong tin khach hang: " + maKH);
                return;
            }
        }
        System.out.println("Khong tim thay khach hang co ma: " + maKH);
    }

    public void timKiem(Scanner sc) {
        System.out.println("1. Tim theo ma KH");
        System.out.println("2. Tim theo ho ten");
        System.out.println("3. Tim theo SDT");
        System.out.print("Lua chon: ");
        int chon = Integer.parseInt(sc.nextLine());
        
        switch (chon) {
            case 1:
                System.out.print("Nhap ma KH can tim: ");
                String ma = sc.nextLine();
                for (int i = 0; i < n; i++) {
                    if (dskh[i].getMaKH().equalsIgnoreCase(ma)) {
                        System.out.println("\nThong tin khach hang:");
                        System.out.println("Ma KH     Ho ten                    SDT            Dia chi");
                        System.out.println("----------------------------------------------------------------");
                        dskh[i].xuat();
                        return;
                    }
                }
                System.out.println("Khong tim thay khach hang co ma: " + ma);
                break;
                
            case 2:
                System.out.print("Nhap ho ten can tim: ");
                String ten = sc.nextLine();
                boolean timThay = false;
                for (int i = 0; i < n; i++) {
                    if (dskh[i].getHoKH().toLowerCase().contains(ten.toLowerCase())) {
                        if (!timThay) {
                            System.out.println("\nKet qua tim kiem:");
                            System.out.println("Ma KH     Ho ten                    SDT            Dia chi");
                            System.out.println("----------------------------------------------------------------");
                            timThay = true;
                        }
                        dskh[i].xuat();
                    }
                }
                if (!timThay) {
                    System.out.println("Khong tim thay khach hang co ten chua: " + ten);
                }
                break;
                
            case 3:
                System.out.print("Nhap SDT can tim: ");
                String sdt = sc.nextLine();
                timThay = false;
                for (int i = 0; i < n; i++) {
                    if (dskh[i].getSDT().contains(sdt)) {
                        if (!timThay) {
                            System.out.println("\nKet qua tim kiem:");
                            System.out.println("Ma KH     Ho ten                    SDT            Dia chi");
                            System.out.println("----------------------------------------------------------------");
                            timThay = true;
                        }
                        dskh[i].xuat();
                    }
                }
                if (!timThay) {
                    System.out.println("Khong tim thay khach hang co SDT chua: " + sdt);
                }
                break;
                
            default:
                System.out.println("Lua chon khong hop le!");
        }
    }

    public void thongKe() {
        if (n == 0) {
            System.out.println("Danh sach trong!");
            return;
        }

        System.out.println("\n// THONG KE KHACH HANG //");
        
        // Thống kê theo khu vực (dựa vào địa chỉ)
        int hn = 0, hcm = 0, khac = 0;
        for (int i = 0; i < n; i++) {
            String diaChi = dskh[i].getDiaChi().toLowerCase();
            if (diaChi.contains("ha noi") || diaChi.contains("hanoi")) {
                hn++;
            } else if (diaChi.contains("ho chi minh") || diaChi.contains("hcm") || diaChi.contains("saigon")) {
                hcm++;
            } else {
                khac++;
            }
        }
        
        System.out.println("1. Theo khu vuc:");
        System.out.println("   - Ha Noi: " + hn + " khach hang");
        System.out.println("   - TP.HCM: " + hcm + " khach hang");
        System.out.println("   - Khu vuc khac: " + khac + " khach hang");
        
        // Thống kê theo đầu số điện thoại
        int viettel = 0, mobi = 0, vina = 0, khacSo = 0;
        for (int i = 0; i < n; i++) {
            String sdt = dskh[i].getSDT();
            if (sdt.startsWith("086") || sdt.startsWith("096") || sdt.startsWith("097") || 
                sdt.startsWith("098") || sdt.startsWith("032") || sdt.startsWith("033") || 
                sdt.startsWith("034") || sdt.startsWith("035") || sdt.startsWith("036") || 
                sdt.startsWith("037") || sdt.startsWith("038") || sdt.startsWith("039")) {
                viettel++;
            } else if (sdt.startsWith("089") || sdt.startsWith("090") || sdt.startsWith("093") || 
                      sdt.startsWith("070") || sdt.startsWith("079") || sdt.startsWith("077") || 
                      sdt.startsWith("076") || sdt.startsWith("078")) {
                mobi++;
            } else if (sdt.startsWith("088") || sdt.startsWith("091") || sdt.startsWith("094") || 
                      sdt.startsWith("083") || sdt.startsWith("084") || sdt.startsWith("085") || 
                      sdt.startsWith("081") || sdt.startsWith("082")) {
                vina++;
            } else {
                khacSo++;
            }
        }
        
        System.out.println("\n2. Theo nha mang:");
        System.out.println("   - Viettel: " + viettel + " khach hang");
        System.out.println("   - Mobifone: " + mobi + " khach hang");
        System.out.println("   - Vinaphone: " + vina + " khach hang");
        System.out.println("   - Khac: " + khacSo + " khach hang");
    }
}