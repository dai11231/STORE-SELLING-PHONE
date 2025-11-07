import java.io.*;
import java.util.Scanner;

public class KhachHang implements IReadWriteFile, Serializable {
    private String maKH;
    private String hoKH;
    private String sdt;
    private String diaChi;

    public KhachHang() {}

    public KhachHang(String maKH, String hoKH, String sdt, String diaChi) {
        this.maKH = maKH;
        this.hoKH = hoKH;
        this.sdt = sdt;
        this.diaChi = diaChi;
    }

    public KhachHang(KhachHang kh) {
        this.maKH = kh.maKH;
        this.hoKH = kh.hoKH;
        this.sdt = kh.sdt;
        this.diaChi = kh.diaChi;
    }

    // Getters
    public String getMaKH() { return maKH; }
    public String getHoKH() { return hoKH; }
    public String getSDT() { return sdt; }
    public String getDiaChi() { return diaChi; }

    // Setters
    public void setMaKH(String maKH) { this.maKH = maKH; }
    public void setHoKH(String hoKH) { this.hoKH = hoKH; }
    public void setSDT(String sdt) { this.sdt = sdt; }
    public void setDiaChi(String diaChi) { this.diaChi = diaChi; }

    public void nhap(Scanner sc) {
        System.out.print("Nhap ma khach hang: ");
        maKH = sc.nextLine();
        System.out.print("Nhap ho ten khach hang: ");
        hoKH = sc.nextLine();
        System.out.print("Nhap so dien thoai: ");
        sdt = sc.nextLine();
        System.out.print("Nhap dia chi: ");
        diaChi = sc.nextLine();
    }

    public void xuat() {
        System.out.printf("%-10s %-25s %-15s %-30s\n", 
                         maKH, hoKH, sdt, diaChi);
    }
    
    @Override
    public void ghiFile(String fileName) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName, true))) {
            writer.printf("%s;%s;%s;%s\n",
                maKH, hoKH, sdt, diaChi);
        }
    }
    
    @Override
    public void docFile(String fileName) throws IOException, ClassNotFoundException {
        throw new UnsupportedOperationException("Use DSKH class for reading multiple customers");
    }
}