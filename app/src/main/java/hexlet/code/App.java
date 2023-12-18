package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import java.util.concurrent.Callable;
import java.util.List;

@Command(name = "gendiff", mixinStandardHelpOptions = true, version = "gendiff 1.0",
        description = "Compares two configuration files and shows a difference.")
public class App implements Callable<Integer> {
    @Option(names = { "-f", "--format" }, paramLabel = "format", description = "output format [default: stylish]")
    String format;
    @Parameters(paramLabel = "filepath1", description = "path to first file")
    String filePath1;
    @Parameters(paramLabel = "filepath2", description = "path to second file")
    String filePath2;
    @Override
    public Integer call() throws Exception {
        System.out.println("Hello world!");
        return 0;
    }
    public static void main(String... args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }

}
