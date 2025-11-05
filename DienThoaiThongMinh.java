import java.util.Scanner;

public class DienThoaiThongMinh extends DienThoai {
    private String tanSoQuet;
    private String camBienVanTay;
    private String camTruoc;

    public DienThoaiThongMinh() {}

    public DienThoaiThongMinh(String maDT, String tenDT, String hangSX, double donGia, 
                             int soLuongNhap, String tanSoQuet, String camBienVanTay, String camTruoc) {
        super(maDT, tenDT, hangSX, donGia, soLuongNhap);
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
    public void nhap(Scanner sc) {
        nhapThongTinCoBan(sc);
        System.out.print("Nhap tan so quet: ");
        tanSoQuet = sc.nextLine();
        System.out.print("Nhap thong tin cam bien van tay: ");
        camBienVanTay = sc.nextLine();
        System.out.print("Nhap thong tin camera truoc: ");
        camTruoc = sc.nextLine();
    }

    @Override
    public void xuat() {
        System.out.printf("%-10s %-20s %-15s %-15.2f %-10d %-15s %-20s %-15s %-15s\n",
                maDT, tenDT, hangSX, donGia, soLuongNhap, "Thong minh",
                tanSoQuet, camBienVanTay, camTruoc);
    }

    @Override
    public String getLoaiDT() {
        return "ThongMinh";
    }
}