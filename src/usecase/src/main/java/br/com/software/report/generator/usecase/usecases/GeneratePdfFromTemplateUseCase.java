package br.com.software.report.generator.usecase.usecases;

import br.com.software.report.generator.usecase.templates.TemplatesEnum;

import java.io.ByteArrayOutputStream;

public interface GeneratePdfFromTemplateUseCase {

    ByteArrayOutputStream run(TemplatesEnum templatesEnum, String stringVariables);

    void run(TemplatesEnum templatesEnum, String stringVariables, String target);
}
