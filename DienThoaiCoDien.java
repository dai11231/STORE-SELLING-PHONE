import java.io.*;
import java.util.Scanner;

public class DienThoaiCoDien extends DienThoai {
    private int soPhimVatLy;
    private String loaiPhim;

    public DienThoaiCoDien() {}

    public DienThoaiCoDien(String maDT, String tenDT, String hangSX, double donGia, 
                          int soLuongKho, int soPhimVatLy, String loaiPhim) {
        super(maDT, tenDT, hangSX, donGia, soLuongKho);
        this.soPhimVatLy = soPhimVatLy;
        this.loaiPhim = loaiPhim;
    }

    // Getters
    public int getSoPhimVatLy() { return soPhimVatLy; }
    public String getLoaiPhim() { return loaiPhim; }

    // Setters
    public void setSoPhimVatLy(int soPhimVatLy) { this.soPhimVatLy = soPhimVatLy; }
    public void setLoaiPhim(String loaiPhim) { this.loaiPhim = loaiPhim; }

    @Override
    public void nhap() {
        super.nhap();
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap so phim vat ly: ");
        soPhimVatLy = Integer.parseInt(sc.nextLine());
        System.out.print("Nhap loai phim: ");
        loaiPhim = sc.nextLine();
    }

    @Override
    public void xuat() {
        super.xuat();
        System.out.printf("%-10d %-15s\n",
                soPhimVatLy, loaiPhim);
    }

    @Override
    public String getLoaiDT() {
        return "CoDien";
    }

    @Override
    public void docFile(String fileName) throws IOException, ClassNotFoundException {
        throw new UnsupportedOperationException("Use DSDT class for reading multiple phones");
    }
}