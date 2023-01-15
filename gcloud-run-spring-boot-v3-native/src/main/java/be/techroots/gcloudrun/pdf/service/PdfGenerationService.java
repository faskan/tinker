package be.techroots.gcloudrun.pdf.service;


import be.techroots.gcloudrun.pdf.vo.PdfRequestVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.Optional;

@Service
public class PdfGenerationService {
    private static final String DEFAULT_TEMPLATE = "https://raw.githubusercontent.com/TECHROOTS-BV/public/main/easytix-invoice-thymeleaf-template.html";

    @Autowired
    HTMLTemplateParser htmlTemplateParser;
    @Autowired
    FlyingSaucerHtml2PdfConverter flyingSaucerHtml2PdfConverter;

    public ByteArrayOutputStream generatePdf(PdfRequestVo pdfRequestVo) throws Exception {
        String html = htmlTemplateParser.parseHtmlTemplate(getTemplateName(pdfRequestVo), pdfRequestVo.getPdfRequestBody());
        return flyingSaucerHtml2PdfConverter.htmlToPdf(html);
    }

    private String getTemplateName(PdfRequestVo pdfRequestVo) {
        return Optional.ofNullable(pdfRequestVo.getTemplateName()).orElse(DEFAULT_TEMPLATE);
    }
}
