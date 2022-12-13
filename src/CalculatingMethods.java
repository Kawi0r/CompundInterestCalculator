import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;

public class CalculatingMethods {
    public static final NumberFormat moneyFormatter = NumberFormat.getCurrencyInstance();
    public static final NumberFormat percentFormatter = NumberFormat.getPercentInstance();

    public static BigDecimal calculate(String principal, String rate, int period, String contribution) throws ParseException {
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

    public static BigDecimal pureContribution(String principal, int period, String contribution) throws ParseException {
        // Balance = principal + period * contribution
        BigDecimal a = new BigDecimal(moneyFormatter.parse(contribution).toString()).multiply(BigDecimal.valueOf(period)); // period * contribution
        BigDecimal b = a.add(new BigDecimal(moneyFormatter.parse(principal).toString())); // principal + period * contribution
        return b;
    }

    public static BigDecimal profitFromPeriod(String balance, String pureContribution) throws ParseException {
        BigDecimal a = new BigDecimal(moneyFormatter.parse(balance).toString()).subtract(new BigDecimal(moneyFormatter.parse(pureContribution).toString()));
        return a;
    }

    public static void detailedBalance(String principal, String rate, int period, String contribution) throws ParseException {
        System.out.println("Your investment from year to year: ");
        for (int i = 0; i < (period + 1); i++) {
            BigDecimal balance = CalculatingMethods.calculate(principal, rate, i, contribution);
            System.out.println("Year " + i + ", balance: " + moneyFormatter.format(balance));
        }
        System.out.println("\n");
    }
}
