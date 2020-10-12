package br.com.software.reportgenerator.templates.strategies;

import br.com.software.reportgenerator.common.component.template.TemplateEngineCreator;
import br.com.software.reportgenerator.templates.TemplateStrategy;
import br.com.software.reportgenerator.templates.TemplatesEnum;
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
