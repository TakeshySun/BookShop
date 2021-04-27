package utils;

import org.apache.commons.text.StringSubstitutor;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

public class ReadJsonAsString {

    public String readFileAsString(String file)throws Exception
    {
        return new String(Files.readAllBytes(Paths.get(file)));
    }

    public String parseJsonTemplate(String filePath, Map<String, Object> values) throws Exception {
        String jsonTxt = new ReadJsonAsString().readFileAsString(filePath);
//        System.out.println(jsonTxt);
        StringSubstitutor stringSubstitutor = new StringSubstitutor(values, "{{", "}}");
        return stringSubstitutor.replace(jsonTxt).replaceAll("\\s", "");
    }
}