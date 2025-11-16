import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
public class PhieuNhapHang {
    private String maPhieu;
    private String maNhanVien;
    private String maNhaCungCap;
    private String ngayNhap; 
    private double tongTienNhap;
public PhieuNhapHang() {
}
public PhieuNhapHang(String maPhieu, String maNhanVien, String maNhaCungCap, String ngayNhap, double tongTienNhap) {
        this.maPhieu = maPhieu;
        this.maNhanVien = maNhanVien;
        this.maNhaCungCap = maNhaCungCap;
        this.ngayNhap = ngayNhap;
        this.tongTienNhap = tongTienNhap;
     }
public String getMaPhieu() {
        return maPhieu;
    }
public void setMaPhieu(String maPhieu) {
        this.maPhieu = maPhieu;
    }
public String getMaNhanVien() {
        return maNhanVien;
    }
public void setMaNhanVien(String maNhanVien) {
        this.maNhanVien = maNhanVien;
    }
public String getMaNhaCungCap() {
        return maNhaCungCap;
    }
public void setMaNhaCungCap(String maNhaCungCap) {
        this.maNhaCungCap = maNhaCungCap;
    }
public String getNgayNhap() {
        return ngayNhap;
    }
public void setNgayNhap(String ngayNhap) {
        this.ngayNhap = ngayNhap;
    }
public double getTongTienNhap() {
        return tongTienNhap;
    }
public void setTongTienNhap(double tongTienNhap) {
        this.tongTienNhap = tongTienNhap;
    }
public void ghiFile(String tenFile) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(tenFile, true))) {
            // Định dạng: PHIEU,maPhieu,maNhanVien,maNCC,ngayNhap,tongTienNhap
            writer.println(String.format("PHIEU,%s,%s,%s,%s,%.0f", 
                                                maPhieu, maNhanVien, maNhaCungCap, ngayNhap, tongTienNhap));
            
            System.out.println("Ghi Header Phieu " + maPhieu + " vao file thanh cong.");
        } catch (IOException e) {
            System.err.println("Loi khi ghi file PhieuNhapHang: " + e.getMessage());
        }
    }
}