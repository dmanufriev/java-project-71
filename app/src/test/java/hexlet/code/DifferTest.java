package hexlet.code;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.assertj.core.api.Assertions.assertThat;

class DifferTest {
    String filesPath;

    @BeforeEach
    void beforeEach() {
        filesPath = "./src/test/resources/";
    }

    @Test
    void generateTest() {
        String actual = null;
        String expected = "{\n"
                            + "    - follow: false\n"
                            + "      host: hexlet.io\n"
                            + "    - proxy: 123.234.53.22\n"
                            + "    - timeout: 50\n"
                            + "    + timeout: 20\n"
                            + "    + verbose: true\n"
                            + "}";
        try {
            actual = Differ.generate(filesPath + "file1.json", filesPath + "file2.json");
            assertThat(actual).isEqualTo(expected);
        } catch (Exception e) {
            assertThat(actual).isNotNull();
        }
    }

    @Test
    void generateFilesNotFoundTest() {
        var thrown1 = catchThrowable(() -> {
            Differ.generate(filesPath + "file3.json", filesPath + "file2.json");
        });
        assertThat(thrown1).isInstanceOf(Exception.class);
        var thrown2 = catchThrowable(() -> {
            Differ.generate(filesPath + "file1.json", filesPath + "file4.json");
        });
        assertThat(thrown2).isInstanceOf(Exception.class);
    }

    @Test
    void generateEmptyFileTest() {
        String actual = null;
        String expected = "{\n"
                            + "}";
        try {
            actual = Differ.generate(filesPath + "fileEmpty.json", filesPath + "fileEmpty.json");
            assertThat(actual).isEqualTo(expected);
        } catch (Exception e) {
            assertThat(actual).isNotNull();
        }
    }

    @Test
    void generateSameFileTest() {
        String actual = null;
        String expected = "{\n"
                + "      host: hexlet.io\n"
                + "      timeout: 20\n"
                + "      verbose: true\n"
                + "}";
        try {
            actual = Differ.generate(filesPath + "file2.json", filesPath + "file2.json");
            assertThat(actual).isEqualTo(expected);
        } catch (Exception e) {
            assertThat(actual).isNotNull();
        }
    }

    @Test
    void generateWrongFileTest() {
        String actual = null;
        try {
            actual = Differ.generate(filesPath + "file1.json", filesPath + "fileWrong.json");
        } catch (Exception e) {
        }
        assertThat(actual).isNull();
    }

}
