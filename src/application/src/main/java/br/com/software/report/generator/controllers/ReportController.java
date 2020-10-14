package br.com.software.report.generator.controllers;

import br.com.software.report.generator.usecase.templates.TemplatesEnum;
import br.com.software.report.generator.usecase.usecases.GeneratePdfFromTemplateUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.*;

import java.io.*;

@RestController
@RequestMapping("/report")
@RequiredArgsConstructor
public class ReportController {

    private final GeneratePdfFromTemplateUseCase generatePdfFromTemplateUseCase;

    @GetMapping(path = "/{templateName}")
    public ResponseEntity<Resource> reportByTemplate(@RequestBody String stringVariables, @PathVariable TemplatesEnum templateName) throws IOException {

        ByteArrayOutputStream result = generatePdfFromTemplateUseCase.run(templateName, stringVariables);
        InputStreamResource resource = new InputStreamResource(new ByteArrayInputStream(result.toByteArray()));

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=file.pdf");
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(result.size())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }
}
