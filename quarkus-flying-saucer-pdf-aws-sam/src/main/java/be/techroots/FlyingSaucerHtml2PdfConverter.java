package be.techroots;

import com.lowagie.text.DocumentException;
import org.xhtmlrenderer.pdf.ITextRenderer;

import javax.enterprise.context.ApplicationScoped;
import java.io.ByteArrayOutputStream;

@ApplicationScoped
public class FlyingSaucerHtml2PdfConverter {

        public ByteArrayOutputStream htmlToPdf(String html) throws DocumentException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocumentFromString(html);
        renderer.layout();
        renderer.createPDF(outputStream);
        return outputStream;
    }

}
