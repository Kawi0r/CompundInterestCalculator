import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;

public class IOFilesMethods {
    // in this class I will create methods to create report document to text file or spreadsheet document

    public static void creatingTxtReport(String principal, String rate, int period, String contribution) throws ParseException {

        // Creating String block for report
        String initialReport = new StringBuilder().append("""
                Investment report:
                ---------------------------------------------------
                Initial investment:\s""").append(principal).append("""
                            
                Annual contribution:\s""").append(contribution).append("""
                            
                Annual rate:\s""").append(rate).append(", ").append(period).append(" years of investment.\n")
                .toString();

        BigDecimal balance = CalculatingMethods.calculate(principal, rate, period, contribution);
        BigDecimal pureContributionBalance = CalculatingMethods.pureContribution(principal, 10, contribution);
        String formatBalance = CalculatingMethods.moneyFormatter.format(balance);
        String formatPureContribution = CalculatingMethods.moneyFormatter.format(pureContributionBalance);
        BigDecimal periodProfit = CalculatingMethods.profitFromPeriod(formatBalance, formatPureContribution);
        String formatPeriodProfit = CalculatingMethods.moneyFormatter.format(periodProfit);

        // creating String block with profit and pure contribution
        String reportProfit = """
                ---------------------------------------------------
                Balance at end of investment:\s""" +
                formatBalance + """
                                
                Pure contribution:\s""" +
                formatPureContribution + """
                                
                Interest profit is:\s""" +
                formatPeriodProfit + "\n" +
                "---------------------------------------------------";


//        String yearByYearReport = new String();
//        for (int i = 0; i < (period + 1); i++) {
//        }

        String report = initialReport +
                reportProfit;

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
    }

}
