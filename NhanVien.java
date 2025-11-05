import java.util.Scanner;
import java.io.*;

public class NhanVien implements IReadWriteFile, Serializable {
    private String maNV;
    private String hoNV;
    private String tenNV;
    private String gioiTinh;
    private String birth;
    private double luong;

    public NhanVien() {}

    public NhanVien(String maNV, String hoNV, String tenNV, String gioiTinh, String birth, double luong) {
        this.maNV = maNV;
        this.hoNV = hoNV;
        this.tenNV = tenNV;
        this.gioiTinh = gioiTinh;
        this.birth = birth;
        this.luong = luong;
    }

    public NhanVien(NhanVien nv) {
        this.maNV = nv.maNV;
        this.hoNV = nv.hoNV;
        this.tenNV = nv.tenNV;
        this.gioiTinh = nv.gioiTinh;
        this.birth = nv.birth;
        this.luong = nv.luong;
    }

    // Getters
    public String getMaNV() { return maNV; }
    public String getHoNV() { return hoNV; }
    public String getTenNV() { return tenNV; }
    public String getGioiTinh() { return gioiTinh; }
    public String getBirth() { return birth; }
    public double getLuong() { return luong; }

    // Setters
    public void setMaNV(String maNV) { this.maNV = maNV; }
    public void setHoNV(String hoNV) { this.hoNV = hoNV; }
    public void setTenNV(String tenNV) { this.tenNV = tenNV; }
    public void setGioiTinh(String gioiTinh) { this.gioiTinh = gioiTinh; }
    public void setBirth(String birth) { this.birth = birth; }
    public void setLuong(double luong) { this.luong = luong; }

    public void nhap(Scanner sc){
        System.out.print("Nhap ma nhan vien: ");
        maNV = sc.nextLine();
        System.out.print("Nhap ho: ");
        hoNV = sc.nextLine();
        System.out.print("Nhap ten: ");
        tenNV = sc.nextLine();
        System.out.print("Nhap gioi tinh: ");
        gioiTinh = sc.nextLine();
        System.out.print("Nhap ngay sinh: ");
        birth = sc.nextLine();
        System.out.print("Nhap luong: ");
        luong =sc.nextDouble();
        sc.nextLine();
    }

    public void xuat() {
        System.out.printf("%-10s %-15s %-10s %-10s %-15s %-10.2f\n", 
                         maNV, hoNV, tenNV, gioiTinh, birth, luong);
    }
    
    @Override
    public void ghiFile(String fileName) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName, true))) {
            writer.printf("%s;%s;%s;%s;%s;%.2f\n",
                maNV, hoNV, tenNV, gioiTinh, birth, luong);
        }
    }
    
    @Override
    public void docFile(String fileName) throws IOException, ClassNotFoundException {
        throw new UnsupportedOperationException("Use DSNV class for reading multiple employees");
    }

    public int tinhTuoi() {
        return java.time.Year.now().getValue() - Integer.parseInt(birth.split("/")[2]);
    }
}
