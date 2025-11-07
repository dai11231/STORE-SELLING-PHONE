import java.util.Scanner;
import java.util.Arrays;

public class DSNV {
    private NhanVien[] dsnv = new NhanVien[0];
    private int n = 0;
    Scanner sc = new Scanner(System.in);
    
    public boolean maNVDuyNhat(String maNV) {
        for (int i = 0; i < n; i++) {
            if (dsnv[i].getMaNV().equalsIgnoreCase(maNV)) return true;
        }
        return false;
    }
    
    public void them() {
        System.out.print("Nhap so luong nhan vien can nhap: ");
        int sl = sc.nextInt();
        sc.nextLine();
        for (int i = 0; i < sl; i++) {
            System.out.println("Nhap thong tin nhan vien thu " + (i + 1) + ": ");
            NhanVien nv = new NhanVien();
            nv.nhap();
            while (maNVDuyNhat(nv.getMaNV())) {
                System.out.println("Loi: Ma nhan vien " + nv.getMaNV() + " da ton tai.");
                System.out.print("Vui long nhap lai ma nhan vien: ");
                String newMaNV = sc.nextLine().trim();
                nv.setMaNV(newMaNV);
            }
            dsnv = Arrays.copyOf(dsnv, n + 1);
            dsnv[n] = nv;
            n++;
        }
    }
    
    public void them(NhanVien nv) {
        if (!maNVDuyNhat(nv.getMaNV())) {
            dsnv = Arrays.copyOf(dsnv, n + 1);
            dsnv[n] = nv;
            n++;
        }
    }
    
    public int soLuongNV() {
        return n;
    }
    
    public NhanVien getNhanVien(int index) {
        if (index >= 0 && index < n) {
            return dsnv[index];
        }
        return null;
    }

    public void hienThiDS() {
        System.out.println("Danh sach NV:");
        for (int i = 0; i < n; i++) {
            dsnv[i].xuat();
        }
    }

    public void xoa() {
        System.out.println("Nhap ma nhan vien can xoa: ");
        String maNV = sc.nextLine();
        for(int i=0;i<n;i++){
            if(dsnv[i].getMaNV().equalsIgnoreCase(maNV)){
                for (int j = i; j < n - 1; j++) dsnv[j] = dsnv[j + 1];
                n--;
                dsnv = Arrays.copyOf(dsnv, n);
                System.out.println("Da xoa NV co ma " + maNV);
                return;
            }
        }
        System.out.println("Khong tim thay SV co ma " + maNV);
    }

    public void sua() {
    System.out.print("Nhap ma NV can sua: ");
    String maNV = sc.nextLine();
    
    for (int i = 0; i < n; i++) {
        if (dsnv[i].getMaNV().equalsIgnoreCase(maNV)) {
            System.out.println("\nSua thong tin NV: " + maNV);
            System.out.println("(Nhan Enter de bo qua)");
            
            System.out.print("Ho moi: ");
            String ho = sc.nextLine();
            if (!ho.isEmpty()) dsnv[i].setHoNV(ho);
            
            System.out.print("Ten moi: ");
            String ten = sc.nextLine();
            if (!ten.isEmpty()) dsnv[i].setTenNV(ten);
            
            System.out.print("Gioi tinh (Nam/Nu): ");
            String gioiTinh = sc.nextLine();
            if (!gioiTinh.isEmpty()) dsnv[i].setGioiTinh(gioiTinh);
            
            System.out.print("Luong moi: ");
            String luongStr = sc.nextLine();
            if (!luongStr.isEmpty()) {
                dsnv[i].setLuong(Double.parseDouble(luongStr));
            }
            
            System.out.println("Da cap nhat thong tin NV: " + maNV);
            return;
        }
    }
    System.out.println("Khong tim thay NV co ma: " + maNV);
}

    public void timKiemTheoMaNV(){
        System.out.print("Nhap ma NV can tim: ");
        String maNV = sc.nextLine();
        for(int i=0; i<n; i++){
            if(dsnv[i].getMaNV().equalsIgnoreCase(maNV)){
                dsnv[i].xuat();
                return;
            }  
        }
        System.out.println("Khong tim thay ma NV:" + maNV);
    }

    public void timKiemTheoHoNV(){
        System.out.print("Nhap ho NV can tim: ");
        String hoNV = sc.nextLine();
        for(int i=0; i<n; i++ ){
            if(dsnv[i].getHoNV().equalsIgnoreCase(hoNV)){
                dsnv[i].xuat();
                return;
            }
        }
        System.out.println("Khong tim thay nhan vien co ho: "+ hoNV);
    }
    
    public void timKiemTheoTenNV() {
        System.out.print("Nhap ten NV can tim: ");
        String TenNV = sc.nextLine();
        for(int i=0; i<n; i++ ){
            if(dsnv[i].getTenNV().equalsIgnoreCase(TenNV)){
                dsnv[i].xuat();
                return;
            }
        }
        System.out.println("Khong tim thay nhan vien co ten: "+ TenNV);
    }

    public void thongKeTheoGT(){
        int nam = 0, nu = 0;
        for(int i=0; i<n; i++){
            if(dsnv[i].getGioiTinh().equalsIgnoreCase("Nam")){
                nam++;
            } 
            else if(dsnv[i].getGioiTinh().equalsIgnoreCase("Nu")){
                nu++;
            }
        }
        System.out.println("// THONG KE THEO GIOI TINH //");
        System.out.println("So nhan vien nam: " + nam);
        System.out.println("So nhan vien nu: "+ nu);
    }

    public void thongKeTheoTuoi(){
        int a = 0, b=0;
        for(int i = 0; i< n; i++){
            if(dsnv[i].tinhTuoi() >= 20 && dsnv[i].tinhTuoi() < 30){
                a++;
            }
            else if(dsnv[i].tinhTuoi() >= 30){
                b++;
            }
        } 
        System.out.println("// THONG KE THEO TUOI //");
        System.out.println("Nhan vien tu 20 den 30 tuoi: "+ a);
        System.out.println("Nhan vien lon hon 30 tuoi: "+ b);
    }



}
    
