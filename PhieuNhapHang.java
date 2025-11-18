import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

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

    // Ham nhap thong tin phieu nhap
    public void nhapThongTin(Scanner scanner) {
        System.out.print("Nhap ma phieu: ");
        this.maPhieu = scanner.nextLine();
        
        System.out.print("Nhap ma nhan vien: ");
        this.maNhanVien = scanner.nextLine();
        
        System.out.print("Nhap ma nha cung cap: ");
        this.maNhaCungCap = scanner.nextLine();
        
        System.out.print("Nhap ngay nhap (dd/mm/yyyy): ");
        this.ngayNhap = scanner.nextLine();
        
        System.out.print("Nhap tong tien nhap: ");
        try {
            this.tongTienNhap = scanner.nextDouble();
            scanner.nextLine(); // Consume newline
        } catch (java.util.InputMismatchException e) {
            System.out.println("Gia tri khong hop le, dat mac dinh la 0.");
            this.tongTienNhap = 0;
            scanner.nextLine(); // Clear invalid input
        }
    }

    @Override
    public String toString() {
        return "Ma Phieu: " + maPhieu + 
               ", Ma NV: " + maNhanVien + 
               ", Ma NCC: " + maNhaCungCap + 
               ", Ngay nhap: " + ngayNhap + 
               ", Tong tien nhap: " + tongTienNhap;
    }

    public void docFile(String duLieu) {
        // Format: PNH001,NV001,NCC001,19012025,349900000
        String[] parts = duLieu.split(",");
        if (parts.length >= 5) {
            this.maPhieu = parts[0].trim();
            this.maNhanVien = parts[1].trim();
            this.maNhaCungCap = parts[2].trim();
            this.ngayNhap = parts[3].trim();
            try {
                this.tongTienNhap = Double.parseDouble(parts[4].trim());
            } catch (NumberFormatException e) {
                this.tongTienNhap = 0.0;
            }
        }
    }

    public void ghiFile(String tenFile) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(tenFile, true))) {
            // Dinh dang: PHIEU,maPhieu,maNhanVien,maNCC,ngayNhap,tongTienNhap
            writer.println(String.format("PHIEU,%s,%s,%s,%s,%.0f", 
                                                maPhieu, maNhanVien, maNhaCungCap, ngayNhap, tongTienNhap));
            
            System.out.println("Ghi Header Phieu " + maPhieu + " vao file thanh cong.");
        } catch (IOException e) {
            System.err.println("Loi khi ghi file PhieuNhapHang: " + e.getMessage());
        }
    }
}
