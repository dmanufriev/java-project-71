package hexlet.code;

import org.junit.jupiter.api.Test;

import static com.github.stefanbirkner.systemlambda.SystemLambda.catchSystemExit;
import static org.assertj.core.api.Assertions.assertThat;

class AppTest {

    static String filesPath = "./src/test/resources/";

    @Test
    void mainPositiveTest() throws Exception {
        int statusCode = catchSystemExit(() -> {
            App.main(filesPath + "json/file1.json", filesPath + "json/file2.json");
        });
        assertThat(statusCode).isEqualTo(0);
    }

    @Test
    void mainMismatchedInputExceptionTest() throws Exception {
        int statusCode = catchSystemExit(() -> {
            App.main(filesPath + "json/fileEmpty.json", filesPath + "json/file2.json");
        });
        assertThat(statusCode).isEqualTo(-1);
    }

    @Test
    void mainExceptionTest() throws Exception {
        int statusCode = catchSystemExit(() -> {
            App.main(filesPath + "file1.json", filesPath + "json/file2.json");
        });
        assertThat(statusCode).isEqualTo(-2);
    }

}
