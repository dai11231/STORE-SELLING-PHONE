import java.util.Scanner;

public class DienThoaiCoDien extends DienThoai {
    private int soPhimVatLy;
    private String loaiPhim;

    public DienThoaiCoDien() {}

    public DienThoaiCoDien(String maDT, String tenDT, String hangSX, double donGia, 
                          int soLuongNhap, int soPhimVatLy, String loaiPhim) {
        super(maDT, tenDT, hangSX, donGia, soLuongNhap);
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
    public void nhap(Scanner sc) {
        nhapThongTinCoBan(sc);
        System.out.print("Nhap so phim vat ly: ");
        soPhimVatLy = Integer.parseInt(sc.nextLine());
        System.out.print("Nhap loai phim: ");
        loaiPhim = sc.nextLine();
    }

    @Override
    public void xuat() {
        System.out.printf("%-10s %-20s %-15s %-15.2f %-10d %-15s %-10d %-15s\n",
                maDT, tenDT, hangSX, donGia, soLuongNhap, "Co dien",
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