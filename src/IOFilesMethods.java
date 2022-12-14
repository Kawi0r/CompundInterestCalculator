import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class IOFilesMethods {
    // in this class I will create methods to create report document to text file or spreadsheet document

    public static void creatingTxtReport(String principal, String rate, int period, String contribution) {
        String report = """
            test
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

        // checking if file exists to be able to write in to file
        if (!fileToCheck.exists()) {
            System.out.println("File does not exists!");
        } else {
            // writing in to the file String report
            try {
                FileWriter myWriter = new FileWriter("Reports/report.txt");
                myWriter.write(report);
                myWriter.close();
                System.out.println("File writing successful");
            } catch (IOException e) {
                System.out.println("Error");
                e.printStackTrace();
            }
        }



        // checking if file exists to be able to write to it
    }

}
