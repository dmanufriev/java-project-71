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
    void generateJsonTest() {
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
            actual = Differ.generate(filesPath + "json/file1.json", filesPath + "json/file2.json");
            assertThat(actual).isEqualTo(expected);
        } catch (Exception e) {
            assertThat(actual).isNotNull();
        }
    }

    @Test
    void generateYamlTest() {
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
            actual = Differ.generate(filesPath + "yaml/file1.yml", filesPath + "yaml/file2.yml");
            assertThat(actual).isEqualTo(expected);
        } catch (Exception e) {
            assertThat(actual).isNotNull();
        }
    }

    @Test
    void generateFilesNotFoundTest() {
        var thrown1 = catchThrowable(() -> {
            Differ.generate(filesPath + "json/file3.json", filesPath + "file2.json");
        });
        assertThat(thrown1).isInstanceOf(Exception.class);
        var thrown2 = catchThrowable(() -> {
            Differ.generate(filesPath + "json/file1.json", filesPath + "yaml/file3.yml");
        });
        assertThat(thrown2).isInstanceOf(Exception.class);
    }

    @Test
    void generateEmptyFileTest() {
        String actual = null;
        try {
            actual = Differ.generate(filesPath + "json/fileEmpty.json", filesPath + "yaml/file2.yml");
        } catch (Exception e) {
        }
        assertThat(actual).isNull();
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
            actual = Differ.generate(filesPath + "json/file2.json", filesPath + "json/file2.json");
            assertThat(actual).isEqualTo(expected);
        } catch (Exception e) {
            assertThat(actual).isNotNull();
        }
    }

    @Test
    void generateWrongFileTest() {
        String actual = null;
        try {
            actual = Differ.generate(filesPath + "json/file1.json", filesPath + "json/fileWrong.json");
        } catch (Exception e) {
        }
        assertThat(actual).isNull();
    }

    @Test
    void generateWrongExtensionFileTest() {
        var thrown = catchThrowable(() -> {
            Differ.generate(filesPath + "file1.txt", filesPath + "file1.txt");
        });
        assertThat(thrown).isInstanceOf(Exception.class);
    }

}
