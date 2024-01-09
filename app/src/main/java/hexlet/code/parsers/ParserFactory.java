package hexlet.code.parsers;

public final class ParserFactory {

    public Parser getParser(String fileExtension) throws Exception {

        Parser parser;
        if (fileExtension.equals("JSON")) {
            parser = new JsonParser();
        } else if (fileExtension.equals("YML")) {
            parser = new YmlParser();
        } else {
            throw new Exception("Extension '" + fileExtension + "' isn't supported");
        }

        return parser;
    }
}
