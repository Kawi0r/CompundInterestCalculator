import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

public class CompoundInterestCalc {

    private static final NumberFormat moneyFormatter = NumberFormat.getCurrencyInstance();
    private static final NumberFormat percentFormatter = NumberFormat.getPercentInstance();

    private static BigDecimal calculate(String principal, String rate, int period, String contribution) throws ParseException {
        // Balance(Y) = principal(1 + rate)^period + contribution[ ((1 + rate)^period - 1)) / rate]
        // additions are made at the end of each compounding period

        String rateAsPercent = percentFormatter.parse(rate).toString();
        BigDecimal a = BigDecimal.ONE.add(new BigDecimal(rateAsPercent)).pow(period); // (1 + rate)^period
        BigDecimal c = a.subtract(BigDecimal.ONE); // (1 + rate)^period - 1)
        BigDecimal d = c.divide(new BigDecimal(rateAsPercent)); // ((1 + rate)^period - 1)) / rate
        BigDecimal e = d.multiply(new BigDecimal(moneyFormatter.parse(contribution).toString())); // contribution[ ((1 + rate)^period - 1)) / rate]
        BigDecimal f = a.multiply(new BigDecimal(moneyFormatter.parse(principal).toString())); // principal(1 + rate)^period
        BigDecimal g = f.add(e); // e + f
        return g;
    }

    private static BigDecimal pureContribution(String principal, int period, String contribution) throws ParseException {
        // Balance = principal + period * contribution
        BigDecimal a = new BigDecimal(moneyFormatter.parse(contribution).toString()).multiply(BigDecimal.valueOf(period)); // period * contribution
        BigDecimal b = a.add(new BigDecimal(moneyFormatter.parse(principal).toString())); // principal + period * contribution
        return b;
    }

    private static BigDecimal profitFromPeriod(String balance, String pureContribution) throws ParseException {
        BigDecimal a = new BigDecimal(moneyFormatter.parse(balance).toString()).subtract(new BigDecimal(moneyFormatter.parse(pureContribution).toString()));
        return a;
    }

    private static void detailedBalance(String principal, String rate, int period, String contribution) throws ParseException {
        for (int i = 0; i < (period + 1); i++) {
            BigDecimal balance = CompoundInterestCalc.calculate(principal, rate, i, contribution);
            System.out.println("Year " + i + ", balance: " + moneyFormatter.format(balance));
        }
    }

    public static void main(String[] args) throws ParseException {


        // inputs
        String principal = "£10,000.00";
        String rate = "8%";
        int period = 10;
        String contribution = "£1,000";

        // calculations
        BigDecimal balance = CompoundInterestCalc.calculate(principal, rate, period, contribution);
        BigDecimal pureContributionBalance = CompoundInterestCalc.pureContribution(principal, 10, contribution);
        BigDecimal periodProfit = CompoundInterestCalc.profitFromPeriod(moneyFormatter.format(balance), moneyFormatter.format(pureContributionBalance));

        // print outs
        System.out.println("Your Balance after " + period + " years, is: " + moneyFormatter.format(balance));
        System.out.println("Your pure contribution is: " + moneyFormatter.format(pureContributionBalance));
        System.out.println("Your interest profit is: " + moneyFormatter.format(periodProfit));

        // detailed balance, on every year of investment
        CompoundInterestCalc.detailedBalance(principal, rate, period, contribution);
    }
}
