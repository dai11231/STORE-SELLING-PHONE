import java.util.Scanner;
import java.io.*;

public abstract class DienThoai implements IReadWriteFile, Serializable {
    protected String maDT;
    protected String tenDT;
    protected String hangSX;
    protected double donGia;
    protected int soLuongNhap;

    public DienThoai() {}

    public DienThoai(String maDT, String tenDT, String hangSX, double donGia, int soLuongNhap) {
        this.maDT = maDT;
        this.tenDT = tenDT;
        this.hangSX = hangSX;
        this.donGia = donGia;
        this.soLuongNhap = soLuongNhap;
    }

    // Getters
    public String getMaDT() { return maDT; }
    public String getTenDT() { return tenDT; }
    public String getHangSX() { return hangSX; }
    public double getDonGia() { return donGia; }
    public int getSoLuongNhap() { return soLuongNhap; }

    // Setters
    public void setMaDT(String maDT) { this.maDT = maDT; }
    public void setTenDT(String tenDT) { this.tenDT = tenDT; }
    public void setHangSX(String hangSX) { this.hangSX = hangSX; }
    public void setDonGia(double donGia) { this.donGia = donGia; }
    public void setSoLuongNhap(int soLuongNhap) { this.soLuongNhap = soLuongNhap; }

    public abstract void nhap(Scanner sc);
    public abstract void xuat();
    public abstract String getLoaiDT();

    @Override
    public void ghiFile(String fileName) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName, true))) {
            writer.printf("%s;%s;%s;%.2f;%d;%s\n",
                maDT, tenDT, hangSX, donGia, soLuongNhap, getLoaiDT());
        } 
    }

    protected void nhapThongTinCoBan(Scanner sc) {
        System.out.print("Nhap ma dien thoai: ");
        maDT = sc.nextLine();
        System.out.print("Nhap ten dien thoai: ");
        tenDT = sc.nextLine();
        System.out.print("Nhap hang san xuat: ");
        hangSX = sc.nextLine();
        System.out.print("Nhap don gia (VND): ");
        donGia = Double.parseDouble(sc.nextLine());
        System.out.print("Nhap so luong nhap: ");
        soLuongNhap = Integer.parseInt(sc.nextLine());
    }
}