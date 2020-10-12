package br.com.software.report.generator.common.template;

import java.util.Map;

public interface TemplateStrategy {

    String parse(Map<String,Object> variables);
}
