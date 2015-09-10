package org.frusso.worldpay.converter;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.lang.reflect.Method;
import java.security.InvalidParameterException;

import static org.junit.Assert.assertEquals;

public class BritishEnglishNumber2WordsTest {

    private final BritishEnglishNumber2Words UNDERLYING_OBJECT = new BritishEnglishNumber2Words();

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
        Method privateMethod = BritishEnglishNumber2Words.class.getDeclaredMethod("convertCurrentPart", Integer.class);
        privateMethod.setAccessible(true);

        assertEquals(" one", privateMethod.invoke(UNDERLYING_OBJECT, 1));
        assertEquals(" one hundred", privateMethod.invoke(UNDERLYING_OBJECT, 100));
        assertEquals(" two hundred", privateMethod.invoke(UNDERLYING_OBJECT, 200));
        assertEquals(" ten", privateMethod.invoke(UNDERLYING_OBJECT, 10));
        assertEquals("", privateMethod.invoke(UNDERLYING_OBJECT, 0));
        assertEquals(" nine hundred and ninety nine", privateMethod.invoke(UNDERLYING_OBJECT, 999));
        assertEquals(" nine hundred and nine", privateMethod.invoke(UNDERLYING_OBJECT, 909));
        assertEquals(" one hundred and nineteen", privateMethod.invoke(UNDERLYING_OBJECT, 119));
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