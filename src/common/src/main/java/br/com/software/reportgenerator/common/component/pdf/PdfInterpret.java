package br.com.software.reportgenerator.common.component.pdf;

import br.com.software.reportgenerator.common.component.exceptions.InterpretPdfException;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.io.RandomAccessBuffer;
import org.apache.pdfbox.io.RandomAccessFile;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

@Slf4j
@UtilityClass
public class PdfInterpret {

    public String loadAndReadFile(File file) {
        String parsedText;

        try {
            PDFParser parser = new PDFParser(new RandomAccessFile(file, "r"));
            parser.parse();

            PDDocument pdDoc = new PDDocument(parser.getDocument());
            parsedText = new PDFTextStripper().getText(pdDoc);
        } catch (IOException e) {
            log.error("Erro ao efetuar parse", e);
            throw new InterpretPdfException("Erro ao efetuar parse");
        }
        return parsedText;
    }

    public String loadAndReadFile(InputStream inputStream) {
        String parsedText;

        try {
            PDFParser parser = new PDFParser(new RandomAccessBuffer(inputStream));
            parser.parse();
            PDDocument pdDoc = new PDDocument(parser.getDocument());
            parsedText = new PDFTextStripper().getText(pdDoc);
            pdDoc.close();
        } catch (IOException e) {
            log.error("Error parsing PDF file", e);
            throw new InterpretPdfException("Error parsing PDF file");
        }

        return parsedText;
    }
}
