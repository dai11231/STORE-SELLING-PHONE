import java.io.*;
import java.io.IOException;

public class NhaCungCap implements IReadWriteFile {
    private String maNCC;
    private String tenNCC;
    private String sdt;
    private String diaChi;

    public NhaCungCap() {
    }

    public NhaCungCap(String maNCC, String tenNCC, String sdt, String diaChi) {
        this.maNCC = maNCC;
        this.tenNCC = tenNCC;
        this.sdt = sdt;
        this.diaChi = diaChi;
    }

    public String getMaNCC() {
        return maNCC;
    }

    public String getTenNCC() {
        return tenNCC;
    }

    public String getSdt() {
        return sdt;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setMaNCC(String maNCC) {
        this.maNCC = maNCC;
    }

    public void setTenNCC(String tenNCC) {
        this.tenNCC = tenNCC;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    // Ham nhap thong tin nha cung cap
    public void nhapThongTin(java.util.Scanner scanner) {
        System.out.print("Nhap ma NCC: ");
        this.maNCC = scanner.nextLine();
        
        System.out.print("Nhap ten NCC: ");
        this.tenNCC = scanner.nextLine();
        
        System.out.print("Nhap SDT: ");
        this.sdt = scanner.nextLine();
        
        System.out.print("Nhap dia chi: ");
        this.diaChi = scanner.nextLine();
    }

    // Ham xuat thong tin nha cung cap
    public void xuat() {
        System.out.println("===== Thong Tin Nha Cung Cap =====");
        System.out.println("MA NCC: " + maNCC);
        System.out.println("TEN NCC: " + tenNCC);
        System.out.println("SDT: " + sdt);
        System.out.println("DIA CHI: " + diaChi);
        System.out.println("----------------------------------");
    }

    @Override
    public String toString() {
        return "Ma NCC: " + maNCC +
                ", Ten NCC: " + tenNCC +
                ", SDT: " + sdt +
                ", Dia chi: " + diaChi;
    }

    // Ham ghi thong tin vao file theo interface
    @Override
    public void ghiFile(String fileName) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName, true))) {
            ghiFile(writer);
        }
    }

    // Ham ghi thong tin vao file
    public void ghiFile(java.io.PrintWriter writer) {
        writer.println(maNCC + ";" + tenNCC + ";" + sdt + ";" + diaChi);
    }

    // Ham doc thong tin tu file
    @Override
    public void docFile(String duLieu) {
        String[] parts = duLieu.split(";");
        if (parts.length >= 4) {
            this.maNCC = parts[0].trim();
            this.tenNCC = parts[1].trim();
            this.sdt = parts[2].trim();
            this.diaChi = parts[3].trim();
        }
    }
}

