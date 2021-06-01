package co.gov.ids.stationerycontrol.certificate.web.controller;

import java.util.List;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import co.gov.ids.stationerycontrol.certificate.domain.dto.User;
import co.gov.ids.stationerycontrol.certificate.web.feign.IUserFeign;
import co.gov.ids.stationerycontrol.certificate.domain.dto.Certificate;
import co.gov.ids.stationerycontrol.certificate.domain.dto.CertificateType;
import co.gov.ids.stationerycontrol.certificate.domain.dto.CertificateState;
import co.gov.ids.stationerycontrol.certificate.domain.service.CertificateService;

@RestController
@Api(tags = "certificate")
@RequestMapping("/certificate")
public class CertificateController {

    private final IUserFeign userFeign;
    private final CertificateService service;

    public CertificateController(IUserFeign userFeign, CertificateService service) {
        this.service = service;
        this.userFeign = userFeign;
    }

    @PostMapping
    @ApiOperation(value = "Post Certificate", notes = "Service for create a new certificate")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Certificate was created correctly"),
            @ApiResponse(code = 400, message = "Invalid request")
    })
    public ResponseEntity<Certificate> create(@RequestBody Certificate certificate) {
        ResponseEntity<User> usersResponse = userFeign.findByIdentification(certificate.getAttendant());
        if (usersResponse.getStatusCodeValue() == 200) {
            return new ResponseEntity<>(service.create(certificate), HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/multiple")
    @ApiOperation(value = "Post multiple Certificates", notes = "Service for create multiple Certificates")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Certificates were created correctly"),
            @ApiResponse(code = 400, message = "Invalid request")
    })
    public ResponseEntity createMultiple(@RequestBody List<Certificate> certificates) {
        ResponseEntity<User> usersResponse = userFeign.findByIdentification(certificates.get(0).getAttendant());
        if (usersResponse.getStatusCodeValue() == 200) {
            return new ResponseEntity<>(service.createMultiple(certificates), HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/{number}")
    @ApiOperation(value = "Put Certificate", notes = "Service for update a Certificate")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Certificate was updated correctly"),
            @ApiResponse(code = 400, message = "Invalid request"),
            @ApiResponse(code = 404, message = "Certificate was not found")
    })
    public ResponseEntity<Certificate> update(@PathVariable("number") int number, @RequestBody Certificate certificate) {
        if (service.findByNumber(number).isPresent()) {
            ResponseEntity<User> usersResponse = userFeign.findByIdentification(certificate.getAttendant());
            if (usersResponse.getStatusCodeValue() == 200 && number == certificate.getNumber()) {
                return new ResponseEntity<>(service.update(
                        certificate,
                        usersResponse.getBody().getUsername(),
                        usersResponse.getBody().getTownship(),
                        usersResponse.getBody().getInstitution()
                ), HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/multiple/{startNumber}-{endNumber}/attendant/{attendant}")
    @ApiOperation(value = "Put multiple Certificates", notes = "Service for update multiple Certificates")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Certificates were updated correctly"),
            @ApiResponse(code = 400, message = "Invalid request")
    })
    public ResponseEntity updateMultiple(@PathVariable("startNumber") int startNumber,
                                         @PathVariable("endNumber") int endNumber,
                                         @PathVariable("attendant") String attendant) {
        ResponseEntity<User> usersResponse = userFeign.findByIdentification(attendant);
        if (usersResponse.getStatusCodeValue() == 200) {
            return new ResponseEntity<>(
                    service.updateMultipleCertificate(
                            startNumber,
                            endNumber,
                            usersResponse.getBody().getUsername(),
                            usersResponse.getBody().getTownship(),
                            usersResponse.getBody().getInstitution()
                    ),
                    HttpStatus.OK
            );
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/{number}")
    @ApiOperation(value = "Get a Certificate by number", notes = "Service for get a Certificate by number")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Certificate was consulted successfully"),
            @ApiResponse(code = 404, message = "Not found")
    })
    public ResponseEntity<Certificate> findByNumber(@PathVariable("number") int number) {
        return service.findByNumber(number)
                .map(certificate -> new ResponseEntity<>(certificate, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/all/{page}")
    @ApiOperation(value = "Get all Certificates", notes = "Service for get a List of all Certificates")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Certificates were listed successfully"),
            @ApiResponse(code = 404, message = "Not found")
    })
    public ResponseEntity<List<Certificate>> findAll(@PathVariable("page") int page) {
        return service.findAll(page)
                .map(certificates -> new ResponseEntity<>(certificates, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @GetMapping("/between/{startNumber}-{endNumber}")
    @ApiOperation(
            value = "Get a list of Certificates by numbers between start and end numbers",
            notes = "Service for get a list of Certificates between two numbers"
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Certificates were listed successfully"),
            @ApiResponse(code = 404, message = "Not found")
    })
    public ResponseEntity<List<Certificate>> findByNumberBetween(@PathVariable("startNumber") int startNumber,
                                                                 @PathVariable("endNumber") int endNumber) {
        return service.findByNumberBetween(startNumber, endNumber)
                .map(certificates -> new ResponseEntity<>(certificates, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/type/{type}/{page}")
    @ApiOperation(
            value = "Get a list of Certificates by type",
            notes = "Service for get a list of Certificates by type"
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Certificates were listed successfully"),
            @ApiResponse(code = 404, message = "Not found")
    })
    public ResponseEntity<List<Certificate>> findByType(@PathVariable("type") CertificateType type,
                                                        @PathVariable("page") int page) {
        return service.findByType(type, page)
                .map(certificates -> new ResponseEntity<>(certificates, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/type/{type}/attendant/{attendant}/{page}")
    @ApiOperation(
            value = "Get a list of Certificates by type and attendant",
            notes = "Service for get a list of Certificates by type and attendant"
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Certificates were listed successfully"),
            @ApiResponse(code = 404, message = "Not found")
    })
    public ResponseEntity<List<Certificate>> findByTypeAndAttendant(@PathVariable("attendant") String attendant,
                                                                    @PathVariable("type") CertificateType type,
                                                                    @PathVariable("page") int page) {
        return service.findByTypeAndAttendant(type, attendant, page)
                .map(certificates -> new ResponseEntity<>(certificates, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/type/{type}/institution/{institution}/{page}")
    @ApiOperation(
            value = "Get a list of Certificates by institution and type",
            notes = "Service for get a list of Certificates by institution and type of Certificate"
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Certificates were listed successfully"),
            @ApiResponse(code = 404, message = "Not found")})
    public ResponseEntity<List<Certificate>> findByInstitutionAndType(@PathVariable("institution") String institution,
                                                                      @PathVariable("type") CertificateType type,
                                                                      @PathVariable("page") int page) {
        return service.findByTypeAndInstitution(type, institution, page)
                .map(certificates -> new ResponseEntity<>(certificates, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/state/{state}/{page}")
    @ApiOperation(
            value = "Get a list of Certificates by state",
            notes = "Service for get a list of Certificates by state"
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Certificates were listed successfully"),
            @ApiResponse(code = 404, message = "Not found")
    })
    public ResponseEntity<List<Certificate>> findByState(@PathVariable("state") CertificateState state,
                                                         @PathVariable("page") int page) {
        return service.findByState(state, page)
                .map(certificates -> new ResponseEntity<>(certificates, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/state/{state}/attendant/{attendant}/{page}")
    @ApiOperation(
            value = "Get a list of Certificates by state and attendant",
            notes = "Service for get a list of Certificates by state and attendant"
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Certificates were listed successfully"),
            @ApiResponse(code = 404, message = "Not found")
    })
    public ResponseEntity<List<Certificate>> findByStateAndAttendant(@PathVariable("state") CertificateState state,
                                                                     @PathVariable("attendant") String attendant,
                                                                     @PathVariable("page") int page) {
        return service.findByStateAndAttendant(state, attendant, page)
                .map(certificates -> new ResponseEntity<>(certificates, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/state/{state}/institution/{institution}/{page}")
    @ApiOperation(
            value = "Get a list of Certificates by institution and state",
            notes = "Service for get a list of Certificates by institution and state of Certificate"
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Certificates were listed successfully"),
            @ApiResponse(code = 404, message = "Not found")
    })
    public ResponseEntity<List<Certificate>> findByInstitutionAndState(@PathVariable("state") CertificateState state,
                                                                       @PathVariable("institution") String institution,
                                                                       @PathVariable("page") int page) {
        return service.findByStateAndInstitution(state, institution, page)
                .map(certificates -> new ResponseEntity<>(certificates, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/attendant/{attendant}/{page}")
    @ApiOperation(
            value = "Get a list of Certificates by attendant",
            notes = "Service for get a list of Certificates by attendant"
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Certificates were listed successfully"),
            @ApiResponse(code = 404, message = "Not found")
    })
    public ResponseEntity<List<Certificate>> findByAttendant(@PathVariable("attendant") String attendant,
                                                             @PathVariable("page") int page) {
        return service.findByAttendant(attendant, page)
                .map(certificates -> new ResponseEntity<>(certificates, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/township/{township}/{page}")
    @ApiOperation(
            value = "Get a list of Certificates by township",
            notes = "Service for get a list of Certificates by township"
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Certificates were listed successfully"),
            @ApiResponse(code = 404, message = "Not found")
    })
    public ResponseEntity<List<Certificate>> findByTownship(@PathVariable("township") String township,
                                                            @PathVariable("page") int page) {
        return service.findByTownship(township, page)
                .map(certificates -> new ResponseEntity<>(certificates, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/institution/{institution}/{page}")
    @ApiOperation(
            value = "Get a list of certificates by institution",
            notes = "Service for get a list of Certificates by institution"
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Certificates were listed successfully"),
            @ApiResponse(code = 404, message = "Not found")})
    public ResponseEntity<List<Certificate>> findByInstitution(@PathVariable("institution") String institution,
                                                               @PathVariable("page") int page) {
        return service.findByInstitution(institution, page)
                .map(certificates -> new ResponseEntity<>(certificates, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

}
