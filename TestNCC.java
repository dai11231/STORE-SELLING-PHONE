import java.util.Scanner;
import java.io.File;

public class TestNCC {
    public static void main(String[] args) {
        // Test reading NCC.txt
        try (Scanner fileScanner = new Scanner(new File("NCC.txt"))) {
            System.out.println("Reading NCC.txt:");
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                System.out.println("Line: " + line);
                
                // Test parsing with semicolon
                String[] parts = line.split(";");
                if (parts.length >= 4) {
                    System.out.println("  Ma NCC: " + parts[0].trim());
                    System.out.println("  Ten NCC: " + parts[1].trim());
                    System.out.println("  SDT: " + parts[2].trim());
                    System.out.println("  Dia Chi: " + parts[3].trim());
                }
                System.out.println();
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
