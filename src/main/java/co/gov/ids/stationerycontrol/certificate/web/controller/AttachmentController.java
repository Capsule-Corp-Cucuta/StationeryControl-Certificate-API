package co.gov.ids.stationerycontrol.certificate.web.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;
import co.gov.ids.stationerycontrol.certificate.domain.service.AttachmentService;

@RestController
@Api(tags = "attachment")
@RequestMapping("/certificate/{number}/attachment")
public class AttachmentController {

    private final AttachmentService service;

    public AttachmentController(AttachmentService service) {
        this.service = service;
    }

    @PostMapping(headers = ("content-type=multipart/form-data"))
    @ApiOperation(
            value = "Upload an attachment to a certificate",
            notes = "Service for upload an attachment to a certificate"
    )
    @ApiResponse(code = 201, message = "File was uploaded correctly")
    public ResponseEntity uploadAttachment(@PathVariable("number") int number,
                                           @RequestParam("file") MultipartFile file,
                                           UriComponentsBuilder builder) {
        return new ResponseEntity<>(service.saveAttachment(number, file, builder), HttpStatus.OK);
    }

    @GetMapping
    @ApiOperation(
            value = "Download the attachment of a certificate",
            notes = "Service for get the attachment of a certificate"
    )
    @ApiResponse(code = 200, message = "File was found")
    public ResponseEntity<byte[]> getAttachment(@PathVariable("number") int number) {
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_PDF).body(service.getAttachment(number));
    }

}
