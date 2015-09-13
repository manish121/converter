package org.frusso.worldpay.converter;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.lang.reflect.Method;
import java.security.InvalidParameterException;

import static org.junit.Assert.assertEquals;

public class BritishEnglishNumber2WordsTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void testCurrentInvalidParameterException() {
        expectedException.expect(InvalidParameterException.class);
        expectedException.expectMessage("The input value must be between 0 and 999999999");
        BritishEnglishNumber2Words.convert(-1);
    }

    @Test
    public void convertCurrentPart() throws Exception {
        Method underTest = BritishEnglishNumber2Words.class.getDeclaredMethod("convertCurrentPart", Integer.class);
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