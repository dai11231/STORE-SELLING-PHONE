import java.util.Scanner;
import java.io.*;

public class HoaDon implements IReadWriteFile, Serializable {
    private String maHD;
    private String maNV;
    private String maKH;
    private String ngLap;
    private double tongTien;
    private static int SLHD = 0;  
    
    public HoaDon() {
        SLHD++; 
    }

    public HoaDon(String maHD, String maNV,String maKH, String ngLap, double tongTien){
        this.maHD = maHD;
        this.maNV = maNV;
        this.maKH = maKH;
        this.ngLap = ngLap;
        this.tongTien = tongTien;
    }

    public HoaDon(HoaDon hd){
        this.maHD = hd.maHD;
        this.maNV = hd.maNV;
        this.maKH = hd.maKH;
        this.ngLap = hd.ngLap;
        this.tongTien = hd.tongTien;
    }

    public String getMaHD() { return maHD; }
    public String getMaNV() { return maNV; }
    public String getMaKH() { return maKH; }
    public String getNgLap() { return ngLap; }
    public double getTongTien() { return tongTien; }

    public void setMaHD(String maHD) { this.maHD = maHD; }
    public void setMaNV(String maNV) { this.maNV = maNV; }
    public void setMaKH(String maKH) { this.maKH = maKH; }
    public void setNgLap(String ngLap) { this.ngLap = ngLap; }
    public void setTongTien(double tongTien) { this.tongTien = tongTien; }
    
    public static int getSoLuongHoaDon() {
        return SLHD;
    }
       
    public void nhap() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap ma HD: ");
        maHD = sc.nextLine();
        System.out.print("Nhap ma NV: ");
        maNV = sc.nextLine();
        System.out.print("Nhap ma KH: ");
        maKH =sc.nextLine();
        System.out.print("Nhap ngay lap HD: ");
        ngLap = sc.nextLine();
    }
    
    public void xuat() {
        System.out.printf("%-20s%-20s%-20s%-15s%-15.2f\n", 
        maHD, maNV, maKH, ngLap, tongTien);
    }

    public void tinhTongTien() {

    }

    @Override
    public void ghiFile(String fileName) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName, true))) {
            writer.printf("%s;%s;%s;%.2f;%.2f\n",
                maHD, maNV, maKH, ngLap, tongTien);
        }
    }

    @Override
    public void docFile(String fileName) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length == 5) {
                    maHD = parts[0];
                    maNV = parts[1];
                    maKH = parts[2];
                    ngLap = parts[3];
                    tongTien = Double.parseDouble(parts[4]);
                }
            }
        }
    }
}
