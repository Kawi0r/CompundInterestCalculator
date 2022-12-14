import java.io.File;
import java.io.IOException;

public class IOFilesMethods {
    // in this class I will create methods to create report document to text file or spreadsheet document

    public static void creatingTxtReport(String principal, String rate, int period, String contribution) {
        String report = """
            """;

        File fileToCheck = new File("Reports/report.txt");
        if (!fileToCheck.exists()) {
            // file does not exist, new file can be created
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
        } else {
            System.out.println("File already exists!");
        }



        // checking if file exists to be able to write to it
    }

}
