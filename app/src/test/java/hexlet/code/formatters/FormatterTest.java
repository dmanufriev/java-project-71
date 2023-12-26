package hexlet.code.formatters;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import java.util.ArrayList;
import static org.assertj.core.api.Assertions.assertThat;

class FormatterTest {

    @Test
    void wrongFormatTest() {
        String expected = "Format wrong isn't supported";
        String actual = null;

        try {
            actual = Formatter.toString(new ArrayList<>(), "wrong");
            assertThat(actual).isEqualTo(expected);
        } catch (Exception e) {
            assertThat(actual).isNotNull();
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {Formatter.PLAIN_FORMAT, Formatter.JSON_FORMAT, Formatter.STYLISH_FORMAT})
    void nullListTest(String format) {
        String expected = "";
        String actual = null;

        try {
            actual = Formatter.toString(null, format);
            assertThat(actual).isEqualTo(expected);
        } catch (Exception e) {
            assertThat(actual).isNotNull();
        }
    }

    @ParameterizedTest
    @CsvSource({
        "plain, ''",
        "json, []"
    })
    void emptyListTest(String format, String expected) {
        String actual = null;
        try {
            actual = Formatter.toString(new ArrayList<>(), format);
            assertThat(actual).isEqualTo(expected);
        } catch (Exception e) {
            assertThat(actual).isNotNull();
        }
    }

    @Test
    void stylishEmptyListTest() {
        String actual = null;
        try {
            actual = Formatter.toString(new ArrayList<>(), Formatter.STYLISH_FORMAT);
            assertThat(actual).isEqualTo("{\n}");
        } catch (Exception e) {
            assertThat(actual).isNotNull();
        }
    }
}
