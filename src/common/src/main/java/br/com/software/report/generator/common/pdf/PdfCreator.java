package br.com.software.report.generator.common.pdf;

import br.com.software.report.generator.common.exceptions.CreatorPdfException;
import com.lowagie.text.DocumentException;
import lombok.experimental.UtilityClass;
import org.thymeleaf.util.StringUtils;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.*;

@UtilityClass
public class PdfCreator {

    public ByteArrayOutputStream create(String content) {

        if (StringUtils.isEmpty(content)) {
            throw new CreatorPdfException("Erro ao criar um novo PDF");
        }

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocumentFromString(content);
        renderer.layout();

        try {
            renderer.createPDF(outputStream);
        } catch (DocumentException e) {
            throw new CreatorPdfException(e.getMessage());
        }

        return outputStream;
    }

    public void create(String content, String output) {

        if (StringUtils.isEmpty(content) || StringUtils.isEmpty(output)) {
            throw new CreatorPdfException("Erro ao criar um novo PDF");
        }

        OutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(output);
        } catch (FileNotFoundException e) {
            throw new CreatorPdfException(e.getMessage());
        }

        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocumentFromString(content);
        renderer.layout();

        try {
            renderer.createPDF(outputStream);
            outputStream.close();
        } catch (DocumentException | IOException e) {
            throw new CreatorPdfException(e.getMessage());
        }
    }
}
