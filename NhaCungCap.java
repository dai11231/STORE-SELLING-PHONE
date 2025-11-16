public class NhaCungCap {
    private String maNCC;
    private String tenNCC;
    private String sdt;
    private String diaChi;

    public NhaCungCap() {
    }

    public NhaCungCap(String maNCC, String tenNCC, String sdt, String diaChi) {
        this.maNCC = maNCC;
        this.tenNCC = tenNCC;
        this.sdt = sdt;
        this.diaChi = diaChi;
    }

    public String getMaNCC() {
        return maNCC;
    }

    public String getTenNCC() {
        return tenNCC;
    }

    public String getSdt() {
        return sdt;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setMaNCC(String maNCC) {
        this.maNCC = maNCC;
    }

    public void setTenNCC(String tenNCC) {
        this.tenNCC = tenNCC;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    // Hàm xuất thông tin nhà cung cấp
    public void xuat() {
        System.out.println("===== Thông Tin Nhà Cung Cấp =====");
        System.out.println("Mã NCC: " + maNCC);
        System.out.println("Tên NCC: " + tenNCC);
        System.out.println("SĐT: " + sdt);
        System.out.println("Địa chỉ: " + diaChi);
        System.out.println("----------------------------------");
    }

    @Override
    public String toString() {
        return "Mã NCC: " + maNCC +
                ", Tên NCC: " + tenNCC +
                ", SĐT: " + sdt +
                ", Địa Chỉ: " + diaChi;
    }
}

