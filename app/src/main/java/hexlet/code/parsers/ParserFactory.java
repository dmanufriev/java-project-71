package hexlet.code.parsers;

public final class ParserFactory {
    private static final String JSON_EXTENSION = "JSON";
    private static final String YML_EXTENSION = "YML";

    public Parser getParser(String fileExtension) throws Exception {
        switch (fileExtension) {
            case JSON_EXTENSION:
                return new JsonParser();
            case YML_EXTENSION:
                return new YmlParser();
            default:
                throw new Exception("Extension '" + fileExtension + "' isn't supported");
        }
    }
}
