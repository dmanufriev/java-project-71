package hexlet.code;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.nio.file.Files;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.catchThrowable;
import static org.assertj.core.api.Assertions.assertThat;

class DifferTest {
    private static String filesPath = "./src/test/resources/";

    @Test
    void generateDefaultTest() {
        String actual = null;
        try {
            String expected = Files.readString(Paths.get(filesPath + "output/stylish.txt"));
            actual = Differ.generate(filesPath + "json/file1.json", filesPath + "json/file2.json");
            assertThat(actual).isEqualTo(expected);
        } catch (Exception e) {
            assertThat(actual).isNotNull();
        }
    }

    @ParameterizedTest
    @CsvSource({
        "json/file1.json, json/file2.json, plain, output/plain.txt",
        "json/file1.json, json/file2.json, json, output/json.txt",
        "json/file1.json, json/file2.json, stylish, output/stylish.txt",
        "yaml/file1.yml, yaml/file2.yml, plain, output/plain.txt",
        "yaml/file1.yml, yaml/file2.yml, json, output/json.txt",
        "yaml/file1.yml, yaml/file2.yml, stylish, output/stylish.txt"
    })
    void generateFormatsTest(String fileFrom, String fileTo, String format, String fileOut) {
        String actual = null;
        try {
            String expected = Files.readString(Paths.get(filesPath + fileOut));
            actual = Differ.generate(filesPath + fileFrom, filesPath + fileTo, format);
            assertThat(actual).isEqualTo(expected);
        } catch (Exception e) {
            assertThat(actual).isNotNull();
        }
    }

    @ParameterizedTest
    @CsvSource({
        "json/file1.json",
        "yaml/file1.yml"
    })
    void generateSameFileTest(String sameFile) {
        String actual = null;
        try {
            String expected = Files.readString(Paths.get(filesPath + "output/stylishSame.txt"));
            actual = Differ.generate(filesPath + sameFile, filesPath + sameFile);
            assertThat(actual).isEqualTo(expected);
        } catch (Exception e) {
            assertThat(actual).isNotNull();
        }
    }

    @ParameterizedTest
    @CsvSource({
        "json/file1.json, json/fileWrong.json",
        "yaml/fileWrong.yml, yaml/file1.yml",
        "json/fileEmpty.json, json/file1.json",
        "yaml/file1.yml, yaml/fileEmpty.yml"
    })
    void generateWrongOrEmptyFileTest() {
        String actual = null;
        try {
            actual = Differ.generate(filesPath + "json/file1.json", filesPath + "json/fileWrong.json");
        } catch (Exception e) {
        }
        assertThat(actual).isNull();
    }

    @ParameterizedTest
    @CsvSource({
        "json/file10.json, file2.json",
        "json/file1.json, yaml/file10.yml"
    })
    void generateFilesNotFoundTest(String fileFrom, String fileTo) {
        var thrown1 = catchThrowable(() -> {
            Differ.generate(filesPath + fileFrom, filesPath + fileTo);
        });
        assertThat(thrown1).isInstanceOf(Exception.class);
    }

    @Test
    void generateWrongExtensionFileTest() {
        var thrown = catchThrowable(() -> {
            Differ.generate(filesPath + "file1.txt", filesPath + "file1.txt");
        });
        assertThat(thrown).isInstanceOf(Exception.class);
    }
}
