package be.techroots.gcloudrun;

import be.techroots.gcloudrun.pdf.service.PdfGenerationService;
import be.techroots.gcloudrun.pdf.vo.PdfRequestVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportRuntimeHints;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;

@SpringBootApplication
@ImportRuntimeHints(MyRuntimeHints.class)
public class GcloudRunApplication {

    public static void main(String[] args) {
        SpringApplication.run(GcloudRunApplication.class, args);
    }

    @RestController
    public class HelloResource {

        @Autowired
        PdfGenerationService pdfGenerationService;

        @GetMapping("/hello")
        public String hello() {
            return "hello";
        }

        @PostMapping(value = "/generate", produces = "application/octet-stream")
        public ResponseEntity generate(@RequestBody PdfRequestVo pdfRequestVo) throws Exception {
            ByteArrayOutputStream outputStream = pdfGenerationService.generatePdf(pdfRequestVo);
            return ResponseEntity.ok(outputStream.toByteArray());
        }
    }

}
