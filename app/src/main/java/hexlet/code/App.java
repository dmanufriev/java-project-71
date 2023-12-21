package hexlet.code;

import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import java.util.concurrent.Callable;

@Command(name = "gendiff", mixinStandardHelpOptions = true, version = "gendiff 1.0",
        description = "Compares two configuration files and shows a difference.")
public class App implements Callable<Integer> {
    @Option(names = { "-f", "--format" },
            paramLabel = "format",
            defaultValue = "stylish",
            description = "output format [default: stylish]")
    String format;
    @Parameters(paramLabel = "filepath1", description = "path to first file")
    static String filePath1;
    @Parameters(paramLabel = "filepath2", description = "path to second file")
    static String filePath2;
    @Override
    public Integer call() {
        try {
            System.out.println(Differ.generate(filePath1, filePath2, format));
        } catch (MismatchedInputException e) {
            System.out.println(e.getMessage());
            return -1;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return -2;
        }
        return 0;
    }
    public static void main(String... args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }
}
