package co.gov.ids.stationerycontrol.certificate.infraestructure.resources;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import co.gov.ids.stationerycontrol.certificate.application.services.IAttachmentService;

/**
 * Class to represents the web service of attachments from certificates.
 *
 * @author Sergio Rodriguez
 * @version 0.0.2
 * @since 2020
 */
@RestController
@Api(tags = "attachment")
@RequestMapping("/api/certificate/{number}/attachment")
public class AttachmentResource {

    private final IAttachmentService service;

    public AttachmentResource(IAttachmentService service) {
        this.service = service;
    }

    /**
     * POST Method for upload an attachment to a Certificate.
     *
     * @param number  number to identify the Certificate.
     * @param file    file to upload
     * @param builder Uri Component Builder.
     * @return code 200.
     */
    @PostMapping(headers = ("content-type=multipart/form-data"))
    @ApiOperation(value = "Upload an attachment to a certificate",
            notes = "Service for upload an attachment to a certificate")
    @ApiResponses(value = {@ApiResponse(code = 201, message = "File was uploaded correctly"),
            @ApiResponse(code = 400, message = "Invalid Request")})
    public ResponseEntity uploadAttachment(@PathVariable("number") int number,
                                           @RequestParam("file") MultipartFile file,
                                           UriComponentsBuilder builder) {
        service.saveAttachment(number, file, builder);
        return new ResponseEntity(HttpStatus.OK);
    }

    /**
     * GET Method for get an attachment from the Certificate.
     *
     * @param number number that identifies the certificate.
     * @return the file to will be downloaded.
     */
    @GetMapping
    @ApiOperation(value = "Download the attachment of a certificate",
            notes = "Service for get the attachment of a certificate")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "File was found"),
            @ApiResponse(code = 404, message = "File was not found")})
    public ResponseEntity<byte[]> getAttachment(@PathVariable("number") int number) {
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_PDF).body(service.getAttachment(number));
    }

}
