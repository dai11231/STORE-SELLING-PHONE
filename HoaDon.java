import java.util.Scanner;
import java.io.*;

public class HoaDon implements IReadWriteFile, Serializable {
    private String maHD;
    private String maNV;
    private String maKH;
    private String ngLap;
    private double tongTien;
    private static int SLHD = 0;  
    
    public HoaDon() {
        SLHD++; 
    }

    public HoaDon(String maHD, String maNV,String maKH, String ngLap, double tongTien){
        this.maHD = maHD;
        this.maNV = maNV;
        this.maKH = maKH;
        this.ngLap = ngLap;
        this.tongTien = tongTien;
    }

    public HoaDon(HoaDon hd){
        this.maHD = hd.maHD;
        this.maNV = hd.maNV;
        this.maKH = hd.maKH;
        this.ngLap = hd.ngLap;
        this.tongTien = hd.tongTien;
    }

    public String getMaHD() { return maHD; }
    public String getMaNV() { return maNV; }
    public String getMaKH() { return maKH; }
    public String getNgLap() { return ngLap; }
    public double getTongTien() { return tongTien; }

    public void setMaHD(String maHD) { this.maHD = maHD; }
    public void setMaNV(String maNV) { this.maNV = maNV; }
    public void setMaKH(String maKH) { this.maKH = maKH; }
    public void setNgLap(String ngLap) { this.ngLap = ngLap; }
    public void setTongTien(double tongTien) { this.tongTien = tongTien; }
    
    public static int getSoLuongHoaDon() {
        return SLHD;
    }
       
    public void nhap() {
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Nhap ma HD: ");
        maHD = sc.nextLine();
        
        //Kiểm tra mã nhân viên
        do {
            System.out.print("Nhap ma NV: ");
            maNV = sc.nextLine();
            if(!QuanLy.qlNV.getDSNV().maNVDuyNhat(maNV)) {
                System.out.println("Ma nhan vien " + maNV + " khong ton tai trong he thong!");
                System.out.print("Vui long nhap lai ma nhan vien: ");
            } else {
                break;
            }
        } while(true);
        
        // Kiểm tra mã khách hàng
        do {
            System.out.print("Nhap ma KH: ");
            maKH =sc.nextLine();
            if(!QuanLy.qlKH.getDSKH().maKHDuyNhat(maKH)) {
                System.out.println("Ma khach hang " + maKH + " khong ton tai trong he thong!");
                System.out.print("Vui long nhap lai ma khach hang: ");
            } else {
                break;
            }
        } while(true);
        
        System.out.print("Nhap ngay lap HD: ");
        ngLap = sc.nextLine();
    }
    
    public void xuat() {
        System.out.printf("%-20s%-20s%-20s%-15s%,-15.0f\n", 
        maHD, maNV, maKH, ngLap, tongTien);
    }

    public void tinhTongTien(ChiTietHD[] dscthd) {
        double tong = 0;
        for(int i = 0; i < dscthd.length; i++) {
            if(dscthd[i] != null && dscthd[i].getMaHD().equals(this.maHD)) {
                tong += dscthd[i].thanhTien();
            }
        }
        this.tongTien = tong;
    }
    
    // Phương thức tính tổng tiền khi thêm chi tiết mới
    public void tinhTongTienTuChiTiet(ChiTietHD cthd) {
        if(cthd != null && cthd.getMaHD().equals(this.maHD)) {
            this.tongTien += cthd.thanhTien();
        }
    }
    
  @Override
    public void ghiFile(String fileName) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName, true))) {
            writer.printf("%s;%s;%s;%s;%.2f\n",
                maHD, maNV, maKH, ngLap, tongTien);
        }
    }
    
    @Override
    public void docFile(String fileName) throws IOException, ClassNotFoundException {
        throw new UnsupportedOperationException("Use DSHD class for reading multiple invoices");
    }

}
