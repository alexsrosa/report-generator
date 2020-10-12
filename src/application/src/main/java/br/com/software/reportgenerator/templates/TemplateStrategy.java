package br.com.software.reportgenerator.templates;

import java.util.Map;

public interface TemplateStrategy {

    String parse(Map<String,Object> variables);
}
