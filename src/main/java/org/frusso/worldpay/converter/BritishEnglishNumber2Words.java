package org.frusso.worldpay.converter;

import java.security.InvalidParameterException;
import java.text.DecimalFormat;
import java.util.LinkedList;

public class BritishEnglishNumber2Words {

    private static final String[] TENS = {
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

    private static final String[] NUMBERS = {
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

    private static final DecimalFormat decimalFormat = new DecimalFormat("000000000");

    public static void main(String[] args) {
        System.out.println(BritishEnglishNumber2Words.convert(0));
        System.out.println(BritishEnglishNumber2Words.convert(1));
        System.out.println(BritishEnglishNumber2Words.convert(21));
        System.out.println(BritishEnglishNumber2Words.convert(105));
        System.out.println(BritishEnglishNumber2Words.convert(123));
        System.out.println(BritishEnglishNumber2Words.convert(1105));
        System.out.println(BritishEnglishNumber2Words.convert(1005));
        System.out.println(BritishEnglishNumber2Words.convert(1042));
        System.out.println(BritishEnglishNumber2Words.convert(1105));
        System.out.println(BritishEnglishNumber2Words.convert(56945781));
        System.out.println(BritishEnglishNumber2Words.convert(999999999));
    }

    private static String parse(Integer value) {
        String result = "";
        if (value % 100 < 20) {
            result = NUMBERS[value % 100];
            value /= 100;
        } else {
            result = NUMBERS[value % 10];
            value /= 10;

            result = TENS[value % 10] + result;
            value /= 10;
        }

        if (value != 0) {
            result = NUMBERS[value] + " hundred" + (result.isEmpty() ? "" : " and") + result;
        }

        return result;
    }

    public static String convert(int number) {
        if (number < 0 || number > 999999999)
            throw new InvalidParameterException("The input value must be between 0 and 999999999");

        if (number == 0) return "zero";

        final String stringNumber = decimalFormat.format(number);

        LinkedList<String> values = new LinkedList<>();
        for (int i = 0, k = 0; i < stringNumber.length(); i = i + 3, k++) {
            int value = Integer.parseInt(stringNumber.substring(i, i + 3));
            String current = "";
            if (value != 0) {
                current = parse(value);
                if (i == 0)
                    current = current + " million";

                if (i == 3)
                    current = current + " thousand";

                if (i == 6 && value < 100) {
                    if (!values.peekLast().isEmpty()) {
                        current = " and" + current;
                    }
                }
            }

            values.add(current);
        }

        return String.join("", values).replaceFirst(" ", "");
    }
}

