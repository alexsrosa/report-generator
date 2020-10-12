package br.com.software.report.generator.usecase.templates.strategies;

import br.com.software.report.generator.usecase.templates.TemplatesEnum;
import br.com.software.report.generator.common.template.TemplateEngineCreator;
import br.com.software.report.generator.common.template.TemplateStrategy;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Map;

public class TemplateUmStrategy implements TemplateStrategy {

    @Override
    public String parse(Map<String,Object> variables) {
        TemplateEngine templateEngine = TemplateEngineCreator.getTemplateEngine();
        Context context = new Context();
        context.setVariables(variables);
        return templateEngine.process(TemplatesEnum.TEMPLATE_UM.getTemplateFile(), context);
    }
}
