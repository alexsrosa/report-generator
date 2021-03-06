package br.com.software.report.generator.usecase;

import br.com.software.report.generator.usecase.usecases.GeneratePdfFromTemplateUseCase;
import br.com.software.report.generator.common.pdf.PdfInterpret;
import br.com.software.report.generator.usecase.templates.TemplatesEnum;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class GeneratePdfFromTemplateUseCaseTest {

    @Autowired
    private GeneratePdfFromTemplateUseCase generatePdfFromTemplateUseCase;

    @Test
    void givenPdfProcessor_whenPayloadIsGood_thenSavePpfOnTarget() {
        String variables = "{\"type\":\"legal\", \"name\":\"fulano\",\"endereco\":\"rua xyw\"}";
        String target = System.getProperty("user.home") + File.separator + "real_teste" + new Random().nextInt() + ".pdf";
        generatePdfFromTemplateUseCase.run(TemplatesEnum.TEMPLATE_UM, variables, target);
        assertTrue(Files.exists(Paths.get(target)));

        String pdfLikeString = PdfInterpret.loadAndReadFile(new File(target));
        assertTrue(pdfLikeString.contains("legal"));
        assertTrue(pdfLikeString.contains("fulano"));
        assertTrue(pdfLikeString.contains("rua xyw"));

//        try {
//            Files.delete(Paths.get(target));
//        } catch (IOException e) {
//           fail();
//        }
//        assertFalse(Files.exists(Paths.get(target)));
    }
}