import java.io.*;
import java.util.Scanner;

public class DienThoaiThongMinh extends DienThoai {
    private String tanSoQuet;
    private String camBienVanTay;
    private String camTruoc;

    public DienThoaiThongMinh() {}

    public DienThoaiThongMinh(String maDT, String tenDT, String hangSX, double donGia, 
                             int soLuongKho, String tanSoQuet, String camBienVanTay, String camTruoc) {
        super(maDT, tenDT, hangSX, donGia, soLuongKho);
        this.tanSoQuet = tanSoQuet;
        this.camBienVanTay = camBienVanTay;
        this.camTruoc = camTruoc;
    }

    // Getters
    public String getTanSoQuet() { return tanSoQuet; }
    public String getCamBienVanTay() { return camBienVanTay; }
    public String getCamTruoc() { return camTruoc; }

    // Setters
    public void setTanSoQuet(String tanSoQuet) { this.tanSoQuet = tanSoQuet; }
    public void setCamBienVanTay(String camBienVanTay) { this.camBienVanTay = camBienVanTay; }
    public void setCamTruoc(String camTruoc) { this.camTruoc = camTruoc; }

    @Override
    public void nhap() {
        super.nhap();
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap tan so quet: ");
        tanSoQuet = sc.nextLine();
        System.out.print("Nhap thong tin cam bien van tay: ");
        camBienVanTay = sc.nextLine();
        System.out.print("Nhap thong tin camera truoc: ");
        camTruoc = sc.nextLine();
    }

    @Override
    public void xuat() {
        super.xuat();
        System.out.printf("%-15s %-20s %-15s\n",
                tanSoQuet, camBienVanTay, camTruoc);
    }

    @Override
    public String getLoaiDT() {
        return "ThongMinh";
    }

    @Override
    public void docFile(String fileName) throws FileNotFoundException, IOException, ClassNotFoundException {
        throw new UnsupportedOperationException("Unimplemented method 'docFile'");
    }
}