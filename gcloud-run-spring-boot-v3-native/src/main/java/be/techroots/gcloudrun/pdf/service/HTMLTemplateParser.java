package be.techroots.gcloudrun.pdf.service;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.templateresolver.UrlTemplateResolver;

@Component
public class HTMLTemplateParser {

    @Autowired
    private SpringTemplateEngine templateEngine;

    @PostConstruct
    public void init() {
        UrlTemplateResolver resolver = new UrlTemplateResolver();
        templateEngine.addTemplateResolver(resolver);
    }

    public String parseHtmlTemplate(String templateUrl, Object pdfRequest) {
        Context context = new Context();
        context.setVariable("invoice", pdfRequest);
        return templateEngine.process(templateUrl, context);
    }
}
