package org.frusso.worldpay.converter;

import java.text.DecimalFormat;

public class BritishEnglishNumber2Words {

    private static final String AND = " and";
    
    private static final String[] TENS_NAMES = {
            "",
            " ten",
            " twenty",
            " thirty",
            " forty",
            " fifty",
            " sixty",
            " seventy",
            " eighty",
            " ninety"
    };

    private static final String[] NUMBERS_NAMES = {
            "",
            " one",
            " two",
            " three",
            " four",
            " five",
            " six",
            " seven",
            " eight",
            " nine",
            " ten",
            " eleven",
            " twelve",
            " thirteen",
            " fourteen",
            " fifteen",
            " sixteen",
            " seventeen",
            " eighteen",
            " nineteen"
    };

    private static final DecimalFormat decimalFormat = new DecimalFormat("000000000000");

    public static void main(String[] args) {
        System.out.println(BritishEnglishNumber2Words.convert(0));
        System.out.println(BritishEnglishNumber2Words.convert(1));
        System.out.println(BritishEnglishNumber2Words.convert(21));
        System.out.println(BritishEnglishNumber2Words.convert(105));
        System.out.println(BritishEnglishNumber2Words.convert(123));
        System.out.println(BritishEnglishNumber2Words.convert(1005));
        System.out.println(BritishEnglishNumber2Words.convert(1042));
        System.out.println(BritishEnglishNumber2Words.convert(1105));
        System.out.println(BritishEnglishNumber2Words.convert(56945781));
        System.out.println(BritishEnglishNumber2Words.convert(999999999));
    }

    private static String convertCurrentPart(Integer number) {
        String result;
        if (number % 100 < 20) {
            result = NUMBERS_NAMES[number % 100];
            number /= 100;
        } else {
            result = NUMBERS_NAMES[number % 10];
            number /= 10;

            result = TENS_NAMES[number % 10] + result;
            number /= 10;
        }

        if (number == 0) return result;

        return result.isEmpty() ? NUMBERS_NAMES[number] + " hundred" + result : NUMBERS_NAMES[number] + " hundred" + AND + result;
    }

    public static String convert(long number) {
        if (number == 0) return "zero";

        final String stringNumber =  decimalFormat.format(number);

        final int billions = Integer.parseInt(stringNumber.substring(0, 3));
        final int millions = Integer.parseInt(stringNumber.substring(3, 6));
        final int hundredThousands = Integer.parseInt(stringNumber.substring(6, 9));
        final int thousands = Integer.parseInt(stringNumber.substring(9, 12));

        StringBuffer result = new StringBuffer();

        if (billions != 0) result.append(convertCurrentPart(billions) + " billion ");

        if (millions != 0) result.append(convertCurrentPart(millions) + " million ");

        if (hundredThousands != 0) {
            result.append(convertCurrentPart(hundredThousands) + " thousand ");
            result.append(thousands < 100 ? AND : "");
        }

        result.append(convertCurrentPart(thousands));

        return result.toString().replaceAll("^\\s+", "").replaceAll("\\b\\s{2,}\\b", " ");
    }
}

