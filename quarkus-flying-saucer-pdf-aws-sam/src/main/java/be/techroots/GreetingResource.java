package be.techroots;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.ByteArrayOutputStream;

@Path("/hello")
public class GreetingResource {

    @Inject
    FlyingSaucerHtml2PdfConverter pdfConverter;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "hello jaxrs";
    }

    @POST
    @Path("/generate")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response generate() throws Exception {
        ByteArrayOutputStream outputStream = pdfConverter.htmlToPdf("Test");
        return Response.ok(outputStream.toByteArray()).build();
    }
}
