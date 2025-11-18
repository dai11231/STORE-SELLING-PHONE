import java.util.Scanner;
import java.io.File;

public class TestPNH {
    public static void main(String[] args) {
        // Test reading PNH.txt
        try (Scanner fileScanner = new Scanner(new File("PNH.txt"))) {
            System.out.println("Reading PNH.txt:");
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                System.out.println("Line: " + line);
                
                // Test parsing with semicolon
                String[] parts = line.split(";");
                if (parts.length >= 5) {
                    System.out.println("  Ma Phieu: " + parts[0].trim());
                    System.out.println("  Ma NV: " + parts[1].trim());
                    System.out.println("  Ma NCC: " + parts[2].trim());
                    System.out.println("  Ngay: " + parts[3].trim());
                    System.out.println("  Tong Tien: " + parts[4].trim());
                }
                System.out.println();
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
