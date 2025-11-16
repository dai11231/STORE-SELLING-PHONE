import java.util.Scanner;
import java.io.*;

public class ChiTietHD implements IReadWriteFile, Serializable{
    private String maHD;
    private String maDT;
    private int soLuong;
    private double donGia;
    Scanner sc = new Scanner(System.in);

    public ChiTietHD() {}

    public ChiTietHD(String maHD, String maDT, int soLuong, double donGia) {
        this.maHD = maHD;
        this.maDT = maDT;
        this.soLuong = soLuong;
        this.donGia = donGia;
    }

    public ChiTietHD (ChiTietHD cthd){
        this.maHD = cthd.maHD;
        this.maDT = cthd.maDT;
        this.soLuong = cthd.soLuong;
        this.donGia = cthd.donGia;
    }

    public String getMaHD() { return maHD; }
    public String getMaDT() { return maDT; }
    public int getSoLuong() { return soLuong; }
    public double getDonGia() { return donGia; }

    public void setMaHD(String maHD) { this.maHD = maHD; }
    public void setMaDT(String maDT) { this.maDT = maDT; }
    public void setSoluong(int soLuong) { this.soLuong = soLuong; }
    public void setDonGia(double donGia) { this.donGia = donGia; }

    public double thanhTien() {
        return soLuong*donGia;
    }

    public void nhap() {
        System.out.print("Nhap ma dien thoai: ");
        maDT = sc.nextLine();
        
        // Kiểm tra và lấy đơn giá từ hệ thống
        boolean found = false;
        for(int j = 0; j < QuanLy.qlDT.getDSDT().soLuongDT(); j++) {
            DienThoai dt = QuanLy.qlDT.getDSDT().getDienThoai(j);
            if(dt != null && dt.getMaDT().equalsIgnoreCase(maDT)) {
                donGia = dt.getDonGia();
                found = true;
                break;
            }
        }
        
        if(!found) {
            System.out.println("Ma dien thoai " + maDT + " khong ton tai trong he thong!");
            // Nhập lại cho đến khi tìm thấy
            while(!found) {
                System.out.print("Vui long nhap lai ma dien thoai: ");
                maDT = sc.nextLine();
                for(int j = 0; j < QuanLy.qlDT.getDSDT().soLuongDT(); j++) {
                    DienThoai dt = QuanLy.qlDT.getDSDT().getDienThoai(j);
                    if(dt != null && dt.getMaDT().equalsIgnoreCase(maDT)) {
                        donGia = dt.getDonGia();
                        found = true;
                        break;
                    }
                }
            }
        }
        
        System.out.print("Nhap so luong: ");
        soLuong = sc.nextInt();
        sc.nextLine();
        
        System.out.println("Don gia: " + donGia + " (lay tu he thong)");
    }
    
    public void xuat() {
        System.out.printf("%-10s %-10s %-10d %,-15.0f %,-15.0f\n",
                                maHD, maDT, soLuong, donGia, thanhTien());
    }
    
    @Override
    public void ghiFile(String fileName) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName, true))) {
            writer.printf("%s;%s;%d;%.2f\n",
                maHD, maDT, soLuong, donGia);
        }
    }
    
    @Override
    public void docFile(String fileName) throws IOException, ClassNotFoundException {
        throw new UnsupportedOperationException("Use DSHD class for reading multiple invoices");
    }
}
