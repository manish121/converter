package org.frusso.benumber2wordsconverter;

import org.junit.Test;

import java.lang.reflect.Method;
import java.security.InvalidParameterException;

import static org.junit.Assert.assertEquals;

public class BritishEnglishNumber2WordsTest {

    @Test
    public void testParse() throws Exception {
        Method underTest = BritishEnglishNumber2Words.class.getDeclaredMethod("parse", Integer.class);
        underTest.setAccessible(true);

        assertEquals(" one", underTest.invoke(null, 1));
        assertEquals(" one hundred", underTest.invoke(null, 100));
        assertEquals(" two hundred", underTest.invoke(null, 200));
        assertEquals(" ten", underTest.invoke(null, 10));
        assertEquals("", underTest.invoke(null, 0));
        assertEquals(" nine hundred and ninety nine", underTest.invoke(null, 999));
        assertEquals(" nine hundred and nine", underTest.invoke(null, 909));
        assertEquals(" one hundred and nineteen", underTest.invoke(null, 119));
    }

    @Test
    public void testConvert() {
        try{
            BritishEnglishNumber2Words.convert(-1);
        } catch (InvalidParameterException e) {
            assertEquals("The input value must be between 0 and 999999999", e.getMessage());
        }

        try{
            BritishEnglishNumber2Words.convert(Integer.MAX_VALUE);
        } catch (InvalidParameterException e) {
            assertEquals("The input value must be between 0 and 999999999", e.getMessage());
        }

        assertEquals("zero", BritishEnglishNumber2Words.convert(0));
        assertEquals("one", BritishEnglishNumber2Words.convert(1));
        assertEquals("twenty one", BritishEnglishNumber2Words.convert(21));
        assertEquals("one hundred and five", BritishEnglishNumber2Words.convert(105));
        assertEquals("one hundred and twenty three", BritishEnglishNumber2Words.convert(123));
        assertEquals("one thousand and five", BritishEnglishNumber2Words.convert(1005));
        assertEquals("one thousand and forty two", BritishEnglishNumber2Words.convert(1042));
        assertEquals("one thousand one hundred and five", BritishEnglishNumber2Words.convert(1105));
        assertEquals("fifty six million nine hundred and forty five thousand seven hundred and eighty one", BritishEnglishNumber2Words.convert(56945781));
        assertEquals("nine hundred and ninety nine million nine hundred and ninety nine thousand nine hundred and ninety nine", BritishEnglishNumber2Words.convert(999999999));
    }
}