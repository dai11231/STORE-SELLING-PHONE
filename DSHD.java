import java.util.Scanner;

import java.util.Arrays;
import java.io.*;

public class DSHD {
    private HoaDon[] dshd = new HoaDon[0];
    private int n = 0;
    private DSCTHD dscthd;
    Scanner sc = new Scanner(System.in);
    
    public DSHD() {
        this.dscthd = null;
    }
    
    public DSHD(DSCTHD dscthd) {
        this.dscthd = dscthd;
    }
    
    public void setDSCTHD(DSCTHD dscthd) {
        this.dscthd = dscthd;
    }
    
    public boolean MaHDDuyNhat(String maHD) {
        for(int i = 0; i < n; i++){
            if(dshd[i].getMaHD().equalsIgnoreCase(maHD)) return true;
        }
        return false;
    }

    public void them() {
        System.out.print("Nhap so luong hoa don can them: ");
        int soLuong = sc.nextInt();
        sc.nextLine();
        for(int i = 0; i < soLuong; i++) {
            System.out.println("Nhap thong tin don hang thu " +(i+1)+ ":");
            HoaDon hd = new HoaDon();
            hd.nhap();
            while(MaHDDuyNhat(hd.getMaHD())) {
                System.out.println("Loi: Ma hoa don "+ hd.getMaHD()+" da ton tai!");
                System.out.print("Vui long nhap lai ma hoa don: ");
                String newMaHD = sc.nextLine();
                hd.setMaHD(newMaHD);
            }
            dshd = Arrays.copyOf(dshd, n+1);
            dshd[n] = hd;
            n++; 
            
            // Chuyển sang nhập chi tiết hóa đơn
            System.out.println("\n--- NHAP CHI TIET HOA DON CHO " + hd.getMaHD() + " ---");
            
            if (dscthd == null) {
                System.out.println("Loi: DSCTHD chua duoc khoi tao!");
                continue;
            }
            
            // Nhập số lượng chi tiết hóa đơn
            System.out.print("Nhap so luong chi tiet hoa don: ");
            int soLuongCT = sc.nextInt();
            sc.nextLine();
            
            double tongTienHD = 0;
            
            // Nhập từng chi tiết
            for(int j = 0; j < soLuongCT; j++) {
                System.out.println("Nhap chi tiet thu " + (j + 1) + ":");
                ChiTietHD cthdMoi = new ChiTietHD();
                cthdMoi.setMaHD(hd.getMaHD());
                cthdMoi.nhap();
                
                // Thêm chi tiết vào danh sách chung
                ChiTietHD[] tempCT = java.util.Arrays.copyOf(dscthd.dscthd, dscthd.n + 1);
                tempCT[dscthd.n] = cthdMoi;
                dscthd.dscthd = tempCT;
                dscthd.n++;
                
                // Cộng dồn tổng tiền
                tongTienHD += cthdMoi.thanhTien();
                
                System.out.println("Da them chi tiet thanh cong!");
            }
            
            // Cập nhật tổng tiền cho hóa đơn
            hd.setTongTien(tongTienHD);
            System.out.println("Tong tien hoa don " + hd.getMaHD() + ": " + hd.getTongTien());
            
        
            }
        }
    

    public void them(HoaDon hd) {
        if(!MaHDDuyNhat(hd.getMaHD())) {
            dshd = Arrays.copyOf(dshd, n+1);
            dshd[n] = hd;
            n++;
        }
    }

    public int soLuongHD() {
        return  n;
    }

    public HoaDon getHoaDon(int index) {
        if(index >= 0 && index < n) {
            return dshd[index];
        }
        return null;
    }

    public void hienThiDS() {
        for(int i = 0; i < n; i++) {
            dshd[i].xuat();
        }
    }

    public void xoa() {
        System.out.print("Nhap ma hoa don can xoa: ");
        String maHD = sc.nextLine();
        for(int i = 0; i < n; i++) {
            if(dshd[i].getMaHD().equalsIgnoreCase(maHD)){
                // Xóa hóa đơn khỏi danh sách
                for(int j = i; j < n - 1; j++) {
                    dshd[j] = dshd[j + 1];
                }
                n--;
                dshd = Arrays.copyOf(dshd, n);
                
                // Xóa các chi tiết hóa đơn liên quan
                if(dscthd != null) {
                    for(int k = 0; k < dscthd.n; k++) {
                        if(dscthd.dscthd[k] != null && dscthd.dscthd[k].getMaHD().equalsIgnoreCase(maHD)) {
                            // Xóa chi tiết khỏi danh sách chi tiết
                            for(int l = k; l < dscthd.n - 1; l++) {
                                dscthd.dscthd[l] = dscthd.dscthd[l + 1];
                            }
                            dscthd.n--;
                            dscthd.dscthd = Arrays.copyOf(dscthd.dscthd, dscthd.n);
                            k--; // Kiểm tra lại vị trí hiện tại vì đã dịch chuyển
                        }
                    }
                }
                
                System.out.println("Da xoa hoa don va cac chi tiet lien quan co ma "+ maHD);
                return;
            }
        }
        System.out.println("Khong tim thay hoa don co ma "+ maHD);
    }

    public void sua() {
        System.out.print("Nhap ma don hang can sua: ");
        String  maHD = sc.nextLine();
        boolean found = false;
        
        for(int i = 0; i < n; i++) {
            if(dshd[i].getMaHD().equalsIgnoreCase(maHD)) {
                found = true;
                System.out.println("\nSua thong tin HD: "+ maHD);

                // Sửa mã nhân viên
                System.out.print("Ma nhan vien moi (hien tai: " + dshd[i].getMaNV() + "): ");
                String maNV = sc.nextLine();
                if (!maNV.isEmpty()) {
                    // Kiểm tra mã nhân viên có tồn tại không
                    boolean nvTonTai = false;
                    for(int j = 0; j < QuanLy.qlNV.getDSNV().soLuongNV(); j++) {
                        if(QuanLy.qlNV.getDSNV().getNhanVien(j) != null && 
                           QuanLy.qlNV.getDSNV().getNhanVien(j).getMaNV().equalsIgnoreCase(maNV)) {
                            nvTonTai = true;
                            break;
                        }
                    }
                    if(nvTonTai) {
                        dshd[i].setMaNV(maNV);
                        System.out.println("Da cap nhat ma nhan vien thanh cong!");
                    } else {
                        System.out.println("Ma nhan vien " + maNV + " khong ton tai trong he thong!");
                    }
                }

                // Sửa mã khách hàng
                System.out.print("Ma khach hang moi (hien tai: " + dshd[i].getMaKH() + "): ");
                String maKH = sc.nextLine();
                if(!maKH.isEmpty()) {
                    // Kiểm tra mã khách hàng có tồn tại không
                    boolean khTonTai = false;
                    for(int j = 0; j < QuanLy.qlKH.getDSKH().soLuongKH(); j++) {
                        if(QuanLy.qlKH.getDSKH().getKhachHang(j) != null && 
                           QuanLy.qlKH.getDSKH().getKhachHang(j).getMaKH().equalsIgnoreCase(maKH)) {
                            khTonTai = true;
                            break;
                        }
                    }
                    if(khTonTai) {
                        dshd[i].setMaKH(maKH);
                        System.out.println("Da cap nhat ma khach hang thanh cong!");
                    } else {
                        System.out.println("Ma khach hang " + maKH + " khong ton tai trong he thong!");
                    }
                }

                // Sửa ngày lập
                System.out.print("Ngay lap moi (hien tai: " + dshd[i].getNgLap() + "): ");
                String ngLap = sc.nextLine();
                if(!ngLap.isEmpty()) {
                    dshd[i].setNgLap(ngLap);
                    System.out.println("Da cap nhat ngay lap thanh cong!");
                }
                
                break;
            }     
        }
        
        if(!found) {
            System.out.println("Khong tim thay hoa don co ma: " + maHD);
        } else {
            System.out.println("Da cap nhat hoa don co ma " + maHD);
        }
    }

    public void timnKiemTheoMaHD() {
        System.out.print("Nhap ma hoa don: ");
        String maHD = sc.nextLine();
        for(int i = 0; i < n; i++) {
            if(dshd[i].getMaHD().equalsIgnoreCase(maHD)){
                dshd[i].xuat();
                return;
            }
        } 
        System.out.println("Khong tim thay hoa don co ma: " + maHD);

    }

    public void timKiemTheoMaKH() {
        System.out.print("Nhap ma khach hang: ");
        String maKH = sc.nextLine();
        boolean found = false;
        System.out.println("\n=== KET QUA TIM KIEM HOA DON THEO MA KHACH HANG " + maKH + " ===");
        for(int i = 0; i < n; i++) {
            if(dshd[i].getMaKH().equalsIgnoreCase(maKH)) {  
                dshd[i].xuat();
                found = true;
            }   
        }
        if(!found){
            System.out.println("Khong tim thay hoa don nao cho khach hang co ma: " + maKH);
        }
    }
    
    public void timKiemTheoMaNV() {
        System.out.print("Nhap ma nhan vien: ");
        String maNV = sc.nextLine();
        boolean found = false;
        System.out.println("\n=== KET QUA TIM KIEM HOA DON THEO MA NHAN VIEN " + maNV + " ===");
        for(int i = 0; i < n; i++) {
            if(dshd[i].getMaNV().equalsIgnoreCase(maNV)) {
                dshd[i].xuat();
                found = true;
            }
        }
        if(!found) {
            System.out.println("Khong tim thay hoa don nao cho nhan vien co ma: " + maNV);
        }
    }

    //thống kê ở đây

    public void theoNgay(){
        System.out.print("Nhap ngay bat dau (dd/MM/yyyy): ");
        String ngayBatDau = sc.nextLine();
        System.out.print("Nhap ngay ket thuc (dd/MM/yyyy): ");
        String ngayKetThuc = sc.nextLine();
        
        boolean found = false;
        double tongTien = 0;
        
        System.out.println("\n=== DANH SACH HOA DON TU " + ngayBatDau + " DEN " + ngayKetThuc + " ===");
        System.out.printf("%-10s %-10s %-10s %-15s %-15s\n", 
                         "MaHD", "MaNV", "MaKH", "NgayLap", "TongTien");
        System.out.println("------------------------------------------------------------");
        
        for(int i = 0; i < n; i++) {
            if(dshd[i] != null) {
                String ngayHD = dshd[i].getNgLap();
                // So sánh ngày (đơn giản hóa, có thể cần cải thiện với Date)
                if(compareDate(ngayBatDau, ngayHD) <= 0 && compareDate(ngayHD, ngayKetThuc) <= 0) {
                    dshd[i].xuat();
                    tongTien += dshd[i].getTongTien();
                    found = true;
                }
            }
        }
        
        if(!found) {
            System.out.println("Khong tim thay hoa don nao trong khoang thoi gian nay!");
        } else {
            System.out.println("------------------------------------------------------------");
            System.out.printf("TONG TIEN CUA TAT CA HOA DON: %.2f VND\n", tongTien);
        }
    }
    
    // Phương thức phụ để so sánh ngày
    private int compareDate(String date1, String date2) {
        try {
            String[] parts1 = date1.split("/");
            String[] parts2 = date2.split("/");
            
            int day1 = Integer.parseInt(parts1[0]);
            int month1 = Integer.parseInt(parts1[1]);
            int year1 = Integer.parseInt(parts1[2]);
            
            int day2 = Integer.parseInt(parts2[0]);
            int month2 = Integer.parseInt(parts2[1]);
            int year2 = Integer.parseInt(parts2[2]);
            
            if (year1 != year2) return year1 - year2;
            if (month1 != month2) return month1 - month2;
            return day1 - day2;
        } catch (Exception e) {
            return 0;
        }
    }



    
    public void docFile(String fileName) throws IOException, ClassNotFoundException {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            // First, count the number of lines to determine array size
            int lineCount = 0;
            BufferedReader counter = new BufferedReader(new FileReader(fileName));
            while ((counter.readLine()) != null) {
                lineCount++;
            }
            counter.close();
            
            // Create array with appropriate size
            dshd = new HoaDon[lineCount];
            n = 0;
            
            // Reset reader and read data
            BufferedReader dataReader = new BufferedReader(new FileReader(fileName));
            while ((line = dataReader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length == 5) {
                    HoaDon hd = new HoaDon();
                    hd.setMaHD(parts[0]);
                    hd.setMaNV(parts[1]);
                    hd.setMaKH(parts[2]);
                    hd.setNgLap(parts[3]);
                    hd.setTongTien(Double.parseDouble(parts[4]));
                    dshd[n] = hd;
                    n++;
                }
            }
            dataReader.close();
        }
    }
    
    public void ghiFile(String fileName) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (int i = 0; i < n; i++) {
                HoaDon hd = dshd[i];
                writer.write(hd.getMaHD() + ";" + hd.getMaNV() + ";" + 
                           hd.getMaKH() + ";" + hd.getNgLap() + ";" + hd.getTongTien());
                writer.newLine();
            }
        }
    }

}
