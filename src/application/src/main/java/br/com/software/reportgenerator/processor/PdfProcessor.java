package br.com.software.reportgenerator.processor;

import br.com.software.reportgenerator.converter.StringVariablesToMapVariablesConverter;
import br.com.software.reportgenerator.common.component.exceptions.ProcessorPdfException;
import br.com.software.reportgenerator.common.component.pdf.PdfCreator;
import br.com.software.reportgenerator.templates.TemplatesEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.util.Map;

import static java.util.Objects.isNull;
import static org.thymeleaf.util.StringUtils.isEmpty;

@Slf4j
@RequiredArgsConstructor
@Component
public class PdfProcessor {

    private final StringVariablesToMapVariablesConverter stringVariablesToMapVariablesConverter;

    public ByteArrayOutputStream run(TemplatesEnum templatesEnum, String stringVariables) {

        if (isNull(templatesEnum) || isEmpty(stringVariables)) {
            throw new ProcessorPdfException("Erro ao processar o PDF");
        }
        Map<String, Object> variables = stringVariablesToMapVariablesConverter.convert(stringVariables);

        String content = templatesEnum.execute(variables);
        return PdfCreator.create(content);
    }

    public void run(TemplatesEnum templatesEnum, String stringVariables, String target) {

        if (isNull(templatesEnum) || isEmpty(stringVariables) || isEmpty(target)) {
            throw new ProcessorPdfException("Erro ao processar o PDF");
        }

        log.debug(String.format("Será processado o template %s e salvo no diretorio %s", templatesEnum.name(), target));

        Map<String, Object> variables = stringVariablesToMapVariablesConverter.convert(stringVariables);

        log.debug("Variaveis recebidas: " + variables);

        String content = templatesEnum.execute(variables);
        PdfCreator.create(content, target);
    }
}
