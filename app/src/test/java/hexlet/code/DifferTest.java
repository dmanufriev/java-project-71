package hexlet.code;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.assertj.core.api.Assertions.assertThat;

class DifferTest {
    static String filesPath;
    static String expectedPositive1;
    static String expectedPositive2;

    @BeforeAll
    static void beforeAll() {
        filesPath = "./src/test/resources/";
        expectedPositive1 = "{\n"
                            + "      chars1: [a, b, c]\n"
                            + "    - chars2: [d, e, f]\n"
                            + "    + chars2: false\n"
                            + "    - checked: false\n"
                            + "    + checked: true\n"
                            + "    - default: null\n"
                            + "    + default: [value1, value2]\n"
                            + "    - id: 45\n"
                            + "    + id: null\n"
                            + "    - key1: value1\n"
                            + "    + key2: value2\n"
                            + "      numbers1: [1, 2, 3, 4]\n"
                            + "    - numbers2: [2, 3, 4, 5]\n"
                            + "    + numbers2: [22, 33, 44, 55]\n"
                            + "    - numbers3: [3, 4, 5]\n"
                            + "    + numbers4: [4, 5, 6]\n"
                            + "    + obj1: {nestedKey=value, isNested=true}\n"
                            + "    - setting1: Some value\n"
                            + "    + setting1: Another value\n"
                            + "    - setting2: 200\n"
                            + "    + setting2: 300\n"
                            + "    - setting3: true\n"
                            + "    + setting3: none\n"
                            + "}";
        expectedPositive2 = "{\n"
                            + "    - follow: false\n"
                            + "      host: hexlet.io\n"
                            + "    - proxy: 123.234.53.22\n"
                            + "    - timeout: 50\n"
                            + "    + timeout: 20\n"
                            + "    + verbose: true\n"
                            + "}";
    }

    @Test
    void generateJsonTest() {
        String actual = null;
        try {
            actual = Differ.generate(filesPath + "json/file1.json", filesPath + "json/file2.json");
            assertThat(actual).isEqualTo(expectedPositive1);
        } catch (Exception e) {
            assertThat(actual).isNotNull();
        }
    }

    @Test
    void generateYamlTest() {
        String actual = null;
        try {
            actual = Differ.generate(filesPath + "yaml/file3.yml", filesPath + "yaml/file4.yml");
            assertThat(actual).isEqualTo(expectedPositive1);
        } catch (Exception e) {
            assertThat(actual).isNotNull();
        }
    }

    @Test
    void generateFilesNotFoundTest() {
        var thrown1 = catchThrowable(() -> {
            Differ.generate(filesPath + "json/file10.json", filesPath + "file2.json");
        });
        assertThat(thrown1).isInstanceOf(Exception.class);
        var thrown2 = catchThrowable(() -> {
            Differ.generate(filesPath + "json/file1.json", filesPath + "yaml/file10.yml");
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
            actual = Differ.generate(filesPath + "yaml/file2.yml", filesPath + "yaml/file2.yml");
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
