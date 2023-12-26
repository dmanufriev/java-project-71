package hexlet.code;

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

    @Override
    public Integer call() {
        try {
            System.out.println(Differ.generate(filePath1, filePath2, format));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return -1;
        }
        return 0;
    }
    public static void main(String... args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }
}
