package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.Getter;
import lombok.Setter;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import java.util.concurrent.Callable;

@Command(name = "gendiff", mixinStandardHelpOptions = true, version = "gendiff 1.0",
        description = "Compares two configuration files and shows a difference.")
@Getter
@Setter
public final class App implements Callable<Integer> {
    @Option(names = { "-f", "--format" },
            paramLabel = "format",
            defaultValue = "stylish",
            description = "output format [default: stylish]")
    private String format;
    @Parameters(paramLabel = "filepath1", description = "path to first file")
    private static String filePath1;
    @Parameters(paramLabel = "filepath2", description = "path to second file")
    private static String filePath2;

    public static final int OK_EXIT_CODE = 0;
    public static final int JSON_EXCEPTION_EXIT_CODE = 1;
    public static final int EXCEPTION_EXIT_CODE = 2;

    @Override
    public Integer call() {
        try {
            System.out.println(Differ.generate(filePath1, filePath2, format));
        } catch (JsonProcessingException e) {
            System.out.println(e.getMessage());
            return JSON_EXCEPTION_EXIT_CODE;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return EXCEPTION_EXIT_CODE;
        }
        return OK_EXIT_CODE;
    }
    public static void main(String... args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }
}
