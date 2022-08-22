package net.lucypoulton.kyorify;

import org.junit.jupiter.api.Test;

import static net.lucypoulton.kyorify.Kyorifier.kyorify;
import static org.junit.jupiter.api.Assertions.*;

/**
 * let's unit test the procrastination spaghetti too, why not
 */
public class KyorifierTests {
    @Test
    public void givenNoFormatters_expectSameInput() {
        assertEquals("hello", kyorify("hello"));
    }

    @Test
    public void givenALegacyColor_expectTranslated() {
        assertEquals("<red>hello", kyorify("§chello"));
    }

    @Test
    public void givenALegacyFormatter_expectTranslated() {
        assertEquals("<italic>hello", kyorify("§ohello"));
    }

    @Test
    public void givenALegacyFormatterAndColour_expectColourToReset() {
        assertEquals("<italic>hello </italic><green>world", kyorify("§ohello §aworld"));
    }

    @Test
    public void givenColours_expectNoRedundantReset() {
        assertEquals("<red>hello <green>world", kyorify("§chello §aworld"));
    }

    @Test
    public void givenColoursAndReset_expectClosedFormatters() {
        assertEquals("<red>red <italic>italic </italic>reset", kyorify("§cred §oitalic §rreset"));
    }

    @Test
    public void givenVariousHexFormats_expectTranslated() {
        assertEquals(
                "<#abcdef>cmi <#abcdef>kyori <#abcdef>minimessage <#123456>bungee",
                kyorify("{#abcdef}cmi §#abcdefkyori <#abcdef>minimessage &x&1&2&3&4&5&6bungee"));
    }

    @Test
    public void givenMixedCase_expectTranslated() {
        assertEquals("<italic>Capitalisation </italic><green>Test", kyorify("§OCapitalisation §ATest"));
    }
}
