import java.math.BigDecimal;
import java.text.ParseException;

public class CompoundInterestCalc {
    public static void main(String[] args) throws ParseException {
        // inputs
        String principal = "£10,000.00";
        String rate = "8%";
        int period = 10;
        String contribution = "£1,000";

        // calculations
        BigDecimal balance = CalculatingMethods.calculate(principal, rate, period, contribution);
        BigDecimal pureContributionBalance = CalculatingMethods.pureContribution(principal, 10, contribution);

        // creating format variable for balance and contribution
        String formatBalance = CalculatingMethods.moneyFormatter.format(balance);
        String formatPureContribution = CalculatingMethods.moneyFormatter.format(pureContributionBalance);

        // calculations continue
        BigDecimal periodProfit = CalculatingMethods.profitFromPeriod(formatBalance, formatPureContribution);

        // print outs
        System.out.println("Your Balance after " + period + " years, is: " + formatBalance);
        System.out.println("Your pure contribution is: " + formatPureContribution);

        // creating format variable for period profit
        String formatPeriodProfit = CalculatingMethods.moneyFormatter.format(periodProfit);

        // print out continue
        System.out.println("Your interest profit is: " + formatPeriodProfit);

        // detailed balance, on every year of investment
        CalculatingMethods.detailedBalance(principal, rate, period, contribution);
    }
}
