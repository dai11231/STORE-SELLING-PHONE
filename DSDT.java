import java.util.Scanner;
import java.util.Arrays;

public class DSDT {
    private DienThoai[] dsdt = new DienThoai[0];
    private int n = 0;
    Scanner sc = new Scanner(System.in);

    public boolean maDTDuyNhat(String maDT) {
        for (int i = 0; i < n; i++) {
            if (dsdt[i].getMaDT().equalsIgnoreCase(maDT)) return true;
        }
        return false;
    }
    
    public void them(String loaiDT) {
        System.out.print("Nhap so luong dien thoai can nhap: ");
        int sl = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < sl; i++) {
            System.out.println("Nhap thong tin dien thoai thu " + (i + 1) + ": ");
            DienThoai dt;
            if (loaiDT.equalsIgnoreCase("ThongMinh")) {
                dt = new DienThoaiThongMinh();
            } else {
                dt = new DienThoaiCoDien();
            }
            dt.nhap(sc);
            
            while (maDTDuyNhat(dt.getMaDT())) {
                System.out.println("Loi: Ma dien thoai " + dt.getMaDT() + " da ton tai.");
                System.out.print("Vui long nhap lai ma dien thoai: ");
                String newMaDT = sc.nextLine().trim();
                dt.setMaDT(newMaDT);
            }
            
            dsdt = Arrays.copyOf(dsdt, n + 1);
            dsdt[n] = dt;
            n++;
        }
    }
    
    public void them(DienThoai dt) {
        if (!maDTDuyNhat(dt.getMaDT())) {
            dsdt = Arrays.copyOf(dsdt, n + 1);
            dsdt[n] = dt;
            n++;
        }
    }
    
    public int soLuongDT() {
        return n;
    }
    
    public DienThoai getDienThoai(int index) {
        if (index >= 0 && index < n) {
            return dsdt[index];
        }
        return null;
    }

    public void hienThiDS() {
        if (n == 0) {
            System.out.println("Danh sach trong!");
            return;
        }
        System.out.println("\nDanh sach dien thoai:");
        System.out.println("Ma DT     Ten DT               Hang SX         Don Gia        SL Nhap    Loai         Thong so bo sung");
        System.out.println("--------------------------------------------------------------------------------------------------------");
        for (int i = 0; i < n; i++) {
            dsdt[i].xuat();
        }
    }

    public void xoa(String maDT, Scanner sc) {
        System.out.print("Nhap ma dien thoai can xoa: ");
        maDT = sc.nextLine();
        for (int i = 0; i < n; i++) {
            if (dsdt[i].getMaDT().equalsIgnoreCase(maDT)) {
                for (int j = i; j < n - 1; j++) {
                    dsdt[j] = dsdt[j + 1];
                }
                n--;
                dsdt = Arrays.copyOf(dsdt, n);
                System.out.println("Da xoa dien thoai co ma " + maDT);
                return;
            }
        }
        System.out.println("Khong tim thay dien thoai co ma " + maDT);
    }

    public void sua(String maDT, Scanner sc) {
        System.out.print("Nhap ma dien thoai can sua: ");
        maDT = sc.nextLine();
        
        for (int i = 0; i < n; i++) {
            if (dsdt[i].getMaDT().equalsIgnoreCase(maDT)) {
                System.out.println("\nSua thong tin dien thoai: " + maDT);
                System.out.println("(Nhan Enter de bo qua)");
                
                System.out.print("Ten moi: ");
                String ten = sc.nextLine();
                if (!ten.isEmpty()) dsdt[i].setTenDT(ten);
                
                System.out.print("Hang san xuat moi: ");
                String hang = sc.nextLine();
                if (!hang.isEmpty()) dsdt[i].setHangSX(hang);
                
                System.out.print("Don gia moi: ");
                String giaStr = sc.nextLine();
                if (!giaStr.isEmpty()) {
                    dsdt[i].setDonGia(Double.parseDouble(giaStr));
                }
                
                System.out.print("So luong nhap moi: ");
                String slStr = sc.nextLine();
                if (!slStr.isEmpty()) {
                    dsdt[i].setSoLuongNhap(Integer.parseInt(slStr));
                }
                
                if (dsdt[i] instanceof DienThoaiThongMinh) {
                    DienThoaiThongMinh dtm = (DienThoaiThongMinh) dsdt[i];
                    System.out.print("Tan so quet moi: ");
                    String tsq = sc.nextLine();
                    if (!tsq.isEmpty()) dtm.setTanSoQuet(tsq);
                    
                    System.out.print("Cam bien van tay moi: ");
                    String cbvt = sc.nextLine();
                    if (!cbvt.isEmpty()) dtm.setCamBienVanTay(cbvt);
                    
                    System.out.print("Camera truoc moi: ");
                    String ct = sc.nextLine();
                    if (!ct.isEmpty()) dtm.setCamTruoc(ct);
                } else if (dsdt[i] instanceof DienThoaiCoDien) {
                    DienThoaiCoDien dtcd = (DienThoaiCoDien) dsdt[i];
                    System.out.print("So phim vat ly moi: ");
                    String spvl = sc.nextLine();
                    if (!spvl.isEmpty()) dtcd.setSoPhimVatLy(Integer.parseInt(spvl));
                    
                    System.out.print("Loai phim moi: ");
                    String lp = sc.nextLine();
                    if (!lp.isEmpty()) dtcd.setLoaiPhim(lp);
                }
                
                System.out.println("Da cap nhat thong tin dien thoai: " + maDT);
                return;
            }
        }
        System.out.println("Khong tim thay dien thoai co ma: " + maDT);
    }

    public void timKiem(Scanner sc) {
        System.out.println("1. Tim theo ma");
        System.out.println("2. Tim theo ten");
        System.out.println("3. Tim theo hang");
        System.out.print("Lua chon: ");
        int chon = Integer.parseInt(sc.nextLine());
        
        switch (chon) {
            case 1:
                System.out.print("Nhap ma dien thoai can tim: ");
                String ma = sc.nextLine();
                for (int i = 0; i < n; i++) {
                    if (dsdt[i].getMaDT().equalsIgnoreCase(ma)) {
                        dsdt[i].xuat();
                        return;
                    }
                }
                System.out.println("Khong tim thay dien thoai co ma: " + ma);
                break;
                
            case 2:
                System.out.print("Nhap ten dien thoai can tim: ");
                String ten = sc.nextLine();
                boolean timThay = false;
                for (int i = 0; i < n; i++) {
                    if (dsdt[i].getTenDT().toLowerCase().contains(ten.toLowerCase())) {
                        if (!timThay) {
                            System.out.println("\nKet qua tim kiem:");
                            timThay = true;
                        }
                        dsdt[i].xuat();
                    }
                }
                if (!timThay) {
                    System.out.println("Khong tim thay dien thoai co ten chua: " + ten);
                }
                break;
                
            case 3:
                System.out.print("Nhap hang san xuat can tim: ");
                String hang = sc.nextLine();
                timThay = false;
                for (int i = 0; i < n; i++) {
                    if (dsdt[i].getHangSX().toLowerCase().contains(hang.toLowerCase())) {
                        if (!timThay) {
                            System.out.println("\nKet qua tim kiem:");
                            timThay = true;
                        }
                        dsdt[i].xuat();
                    }
                }
                if (!timThay) {
                    System.out.println("Khong tim thay dien thoai cua hang: " + hang);
                }
                break;
                
            default:
                System.out.println("Lua chon khong hop le!");
        }
    }

    public void thongKe() {
        System.out.println("\n// THONG KE //");
        int slThongMinh = 0, slCoDien = 0;
        double tongGiaTriThongMinh = 0, tongGiaTriCoDien = 0;
        
        for (int i = 0; i < n; i++) {
            if (dsdt[i] instanceof DienThoaiThongMinh) {
                slThongMinh += dsdt[i].getSoLuongNhap();
                tongGiaTriThongMinh += dsdt[i].getDonGia() * dsdt[i].getSoLuongNhap();
            } else {
                slCoDien += dsdt[i].getSoLuongNhap();
                tongGiaTriCoDien += dsdt[i].getDonGia() * dsdt[i].getSoLuongNhap();
            }
        }
        
        System.out.println("1. Theo so luong:");
        System.out.println("   - Dien thoai thong minh: " + slThongMinh + " chiec");
        System.out.println("   - Dien thoai co dien: " + slCoDien + " chiec");
        System.out.println("\n2. Theo gia tri ton kho:");
        System.out.printf("   - Dien thoai thong minh: %.2f VND\n", tongGiaTriThongMinh);
        System.out.printf("   - Dien thoai co dien: %.2f VND\n", tongGiaTriCoDien);
        System.out.printf("   - Tong gia tri: %.2f VND\n", (tongGiaTriThongMinh + tongGiaTriCoDien));
    }
}