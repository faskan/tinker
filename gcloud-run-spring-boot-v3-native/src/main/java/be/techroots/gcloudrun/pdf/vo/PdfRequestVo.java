package be.techroots.gcloudrun.pdf.vo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PdfRequestVo {
    public Object pdfRequestBody;
    public String templateName;
    public String fileName;
}
