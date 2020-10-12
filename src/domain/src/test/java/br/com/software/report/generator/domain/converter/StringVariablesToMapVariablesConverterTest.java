package br.com.software.report.generator.domain.converter;

import br.com.software.report.generator.common.exceptions.ParseVariablesToMapException;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class StringVariablesToMapVariablesConverterTest {

    @Test
    void givenStringVariables_whenValidString_thenConverterRunSuccessfully() {
        String jsonTest = "{\"code\":\"123\",\"name\":\"teste name\",\"age\": 40,\"country_from\":\"Brazil\"}";

        Map<String, Object> mapConverted = new StringVariablesToMapVariablesConverter().convert(jsonTest);

        assertNotNull(mapConverted);
        assertEquals("123", mapConverted.get("code"));
        assertEquals("teste name", mapConverted.get("name"));
        assertEquals(40, mapConverted.get("age"));
        assertEquals("Brazil", mapConverted.get("country_from"));
    }

    @Test
    void givenStringVariables_whenInvalidString_thenThrowException() {
        String jsonTest = "{\"code\":\"123\"\"name\":\"teste name\",\"age\": 40\"country_from\":\"Brazil\"}";

        assertThrows(ParseVariablesToMapException.class, () -> {
            new StringVariablesToMapVariablesConverter().convert(jsonTest);
        });
    }
}