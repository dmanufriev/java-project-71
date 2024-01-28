package hexlet.code;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

class DataSupplierTest {

    @ParameterizedTest
    @CsvSource({
        "file1.txt, TXT",
        "file1.jSon, JSON"
    })
    void getFileExtensionPositiveTest(String filePath, String expected) throws Exception {
        String actual = DataSupplier.getFileExtension(filePath);
        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @ValueSource(strings = {"file1.", "file1", ""})
    void getFileExtensionNegativeTest(String filePath) {
        var thrown = catchThrowable(() -> DataSupplier.getFileExtension(filePath));
        assertThat(thrown).isInstanceOf(Exception.class);
    }

}
