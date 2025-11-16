import java.util.Scanner;
import java.io.*;

public class DSCTHD{
    ChiTietHD[] dscthd = new ChiTietHD[0];
    int n = 0;
    Scanner sc = new Scanner(System.in);

    public void them(String maHD) {
        System.out.println("Ma hoa don " + maHD + " chua co chi tiet. Hay nhap chi tiet moi:");
        
        // Tạo chi tiết mới và set maHD
        ChiTietHD cthdMoi = new ChiTietHD();
        cthdMoi.setMaHD(maHD);
        cthdMoi.nhap();
        
        // Thêm chi tiết mới vào mảng
        ChiTietHD[] temp = java.util.Arrays.copyOf(dscthd, n + 1);
        temp[n] = cthdMoi;
        dscthd = temp;
        n++;
        System.out.println("Da them chi tiet moi thanh cong!");
        
        System.out.print("Ban co muon tiep tuc them chi tiet cho hoa don khong? (y/n): ");
        String tiepTuc = sc.nextLine();
        if(tiepTuc.equalsIgnoreCase("y")) {
            them(maHD);
        }
    }
    

    public void xoa() {
        System.out.print("Nhap ma hoa don can xoa chi tiet: ");
        String maHD = sc.nextLine();
        
        for(int i = 0; i < n; i++) {
            if(dscthd[i] != null && dscthd[i].getMaHD().equalsIgnoreCase(maHD)) {
                for(int j = i; j < n - 1; j++) {
                    dscthd[j] = dscthd[j + 1];
                }
                n--;
                dscthd = java.util.Arrays.copyOf(dscthd, n);
                System.out.println("Da xoa chi tiet hoa don co ma " + maHD);
                return;
            }
        }
        System.out.println("Khong tim thay chi tiet hoa don co ma " + maHD);
    }

    public void sua() {
        System.out.print("Nhap ma hoa don can sua chi tiet: ");
        String maHD = sc.nextLine();
        
        for(int i = 0; i < n; i++) {
            if(dscthd[i] != null && dscthd[i].getMaHD().equalsIgnoreCase(maHD)) {
                System.out.println("\nSua chi tiet hoa don: " + maHD);
                System.out.println("(Nhan Enter de bo qua)");
                
                System.out.print("Ma dien thoai moi: ");
                String maDT = sc.nextLine();
                if(!maDT.isEmpty()) dscthd[i].setMaDT(maDT);
                
                System.out.print("So luong moi: ");
                String soLuongStr = sc.nextLine();
                if(!soLuongStr.isEmpty()) {
                    dscthd[i].setSoluong(Integer.parseInt(soLuongStr));
                }
                
                System.out.print("Don gia moi: ");
                String donGiaStr = sc.nextLine();
                if(!donGiaStr.isEmpty()) {
                    dscthd[i].setDonGia(Double.parseDouble(donGiaStr));
                }
                
                System.out.println("Da cap nhat chi tiet hoa don: " + maHD);
                return;
            }
        }
        System.out.println("Khong tim thay chi tiet hoa don co ma: " + maHD);
    }

    public void timKiemTheoMaHD() {
        System.out.print("Nhap ma hoa don can tim: ");
        String maHD = sc.nextLine();
        
        boolean found = false;
        System.out.println("Ket qua tim kiem:");
        System.out.printf("%-10s %-10s %-10s %-15s %-15s\n", 
                         "MaHD", "MaDT", "SL", "DonGia", "ThanhTien");
        
        for(int i = 0; i < n; i++) {
            if(dscthd[i] != null && dscthd[i].getMaHD().equalsIgnoreCase(maHD)) {
                dscthd[i].xuat();
                found = true;
            }
        }
        
        if(!found) {
            System.out.println("Khong tim thay chi tiet hoa don co ma: " + maHD);
        }
    }

    public ChiTietHD[] getDanhSachCTHD() {
        return dscthd;
    }
    
    public void docFile(String fileName) throws IOException, ClassNotFoundException {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            int lineCount = 0;
            BufferedReader counter = new BufferedReader(new FileReader(fileName));
            while ((counter.readLine()) != null) {
                lineCount++;
            }
            counter.close();
            
            dscthd = new ChiTietHD[lineCount];
            n = 0;
            
            BufferedReader dataReader = new BufferedReader(new FileReader(fileName));
            while ((line = dataReader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length == 4) {
                    ChiTietHD cthd = new ChiTietHD(parts[0], parts[1], 
                                                  Integer.parseInt(parts[2]), 
                                                  Double.parseDouble(parts[3]));
                    dscthd[n] = cthd;
                    n++;
                }
            }
            dataReader.close();
        }
    }

    public void ghiFile(String fileName) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (int i = 0; i < n; i++) {
                ChiTietHD cthd = dscthd[i];
                writer.write(cthd.getMaHD() + ";" + cthd.getMaDT() + ";" + 
                           cthd.getSoLuong() + ";" + cthd.getDonGia());
                writer.newLine();
            }
        }
    }

}
