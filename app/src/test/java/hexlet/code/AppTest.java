package hexlet.code;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static com.github.stefanbirkner.systemlambda.SystemLambda.catchSystemExit;
import static org.assertj.core.api.Assertions.assertThat;

class AppTest {

    private static String filesPath = "./src/test/resources/";

    @Test
    void mainPositiveTest() throws Exception {
        int statusCode = catchSystemExit(() -> {
            App.main(filesPath + "json/file1.json", filesPath + "json/file2.json");
        });
        assertThat(statusCode).isEqualTo(0);
    }

    @ParameterizedTest
    @CsvSource({
        "json/fileEmpty.json, json/file2.json",
        "file1.json, json/file2.json"
    })
    void mainMismatchedInputExceptionTest(String fileFrom, String fileTo) throws Exception {
        int statusCode = catchSystemExit(() -> {
            App.main(filesPath + fileFrom, filesPath + fileTo);
        });
        assertThat(statusCode).isNotEqualTo(0);
    }
}
