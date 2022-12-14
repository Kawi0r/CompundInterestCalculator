import java.io.File;
import java.io.IOException;

public class IOFilesMethods {
    // in this class I will create methods to create report document to text file or spreadsheet document

    public static void creatingTxtReportFile (String principal, String rate, int period, String contribution) {

        String report = """
                """;

        // creating file
        try {
            File f = new File("Reports/report.txt");

            if (f.createNewFile()) {
                System.out.println("File created!");
            } else {
                System.out.println("File could not be created!");
            }
        } catch (IOException e) {
            System.out.println("Error");
            e.printStackTrace();
        }
    }
}
