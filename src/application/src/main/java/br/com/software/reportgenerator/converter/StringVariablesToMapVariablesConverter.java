package br.com.software.reportgenerator.converter;

import br.com.software.reportgenerator.common.component.exceptions.ParseVariablesToMapException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class StringVariablesToMapVariablesConverter implements Converter<String, Map<String, Object>> {

    @Override
    public Map<String, Object> convert(String source) {
        Map<String, Object> variables;
        try {
            variables = new ObjectMapper().readValue(
                    source, new TypeReference<>() {
                    });
        } catch (JsonProcessingException e) {
            throw new ParseVariablesToMapException(e.getMessage());
        }
        return variables;
    }
}
