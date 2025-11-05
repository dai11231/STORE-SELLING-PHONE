
import java.io.*;

public interface IReadWriteFile {
    void ghiFile(String fileName) throws IOException;
    void docFile(String fileName) throws FileNotFoundException, IOException, ClassNotFoundException;
}
