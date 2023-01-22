package be.techroots.gcloudrun.pdf.service;


import be.techroots.gcloudrun.pdf.vo.PdfRequestVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;

@Service
public class PdfGenerationService {

    @Autowired
    HTMLTemplateParser htmlTemplateParser;
    @Autowired
    FlyingSaucerHtml2PdfConverter flyingSaucerHtml2PdfConverter;

    public ByteArrayOutputStream generatePdf(PdfRequestVo pdfRequestVo) throws Exception {
        String html = htmlTemplateParser.parseHtmlTemplate(pdfRequestVo.getTemplateName(), pdfRequestVo.getPdfRequestBody());
        return flyingSaucerHtml2PdfConverter.htmlToPdf(html);
    }

}
