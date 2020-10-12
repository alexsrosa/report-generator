package br.com.software.report.generator.usecase.templates;

import br.com.software.report.generator.usecase.templates.strategies.TemplateUmStrategy;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;

@AllArgsConstructor
@Getter
public enum TemplatesEnum {

    TEMPLATE_UM("templates/template_um.html") {
        @Override
        public String execute(Map<String, Object> variables) {
            return new TemplateUmStrategy().parse(variables);
        }
    };

    private final String templateFile;

    public abstract String execute(Map<String, Object> variables);
}
