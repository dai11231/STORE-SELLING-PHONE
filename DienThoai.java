import java.io.*;
import java.util.Scanner;

public class DienThoai implements IReadWriteFile, Serializable {
    protected String maDT;
    protected String tenDT;
    protected String hangSX;
    protected double donGia;
    protected int soLuongKho;
    
    public DienThoai() {}

    public DienThoai(String maDT, String tenDT, String hangSX, double donGia, int soLuongKho) {
        this.maDT = maDT;
        this.tenDT = tenDT;
        this.hangSX = hangSX;
        this.donGia = donGia;
        this.soLuongKho = soLuongKho;
    }

    // Getters
    public String getMaDT() { return maDT; }
    public String getTenDT() { return tenDT; }
    public String getHangSX() { return hangSX; }
    public double getDonGia() { return donGia; }
    public int getsoLuongKho() { return soLuongKho; }

    // Setters
    public void setMaDT(String maDT) { this.maDT = maDT; }
    public void setTenDT(String tenDT) { this.tenDT = tenDT; }
    public void setHangSX(String hangSX) { this.hangSX = hangSX; }
    public void setDonGia(double donGia) { this.donGia = donGia; }
    public void setsoLuongKho(int soLuongKho) { this.soLuongKho = soLuongKho; }

<<<<<<< HEAD
    public abstract void nhap(Scanner sc);
    public abstract void xuat();
    public abstract String getLoaiDT();

    @Override
    public void ghiFile(String fileName) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName, true))) {
            writer.printf("%s;%s;%s;%.2f;%d;%s\n",
                maDT, tenDT, hangSX, donGia, soLuongNhap, getLoaiDT());
        } 
=======
    public String getLoaiDT() {
        return "DienThoai";
>>>>>>> 35bd3f37d42ebeafb9578c6de882360bc03adb62
    }

    public void nhap() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap ma dien thoai: ");
        maDT = sc.nextLine();
        System.out.print("Nhap ten dien thoai: ");
        tenDT = sc.nextLine();
        System.out.print("Nhap hang san xuat: ");
        hangSX = sc.nextLine();
        System.out.print("Nhap don gia (VND): ");
        donGia = Double.parseDouble(sc.nextLine());
        System.out.print("Nhap so luong nhap: ");
        soLuongKho = Integer.parseInt(sc.nextLine());
    }

    public void xuat() {
        System.out.printf("%-10s %-20s %-15s %-15.2f %-10d\n",
                maDT, tenDT, hangSX, donGia, soLuongKho);
    }

    @Override
    public void ghiFile(String fileName) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName, true))) {
            writer.printf("%s;%s;%s;%.2f;%d\n",
                maDT, tenDT, hangSX, donGia, soLuongKho);
        }
    }

    @Override
    public void docFile(String fileName) throws IOException, ClassNotFoundException {
        throw new UnsupportedOperationException("Use DSDT class for reading multiple phones");
    }
}