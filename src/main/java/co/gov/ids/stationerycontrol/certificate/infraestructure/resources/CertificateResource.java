package co.gov.ids.stationerycontrol.certificate.infraestructure.resources;

import java.util.List;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import co.gov.ids.stationerycontrol.certificate.domain.Certificate;
import co.gov.ids.stationerycontrol.certificate.domain.CertificateType;
import co.gov.ids.stationerycontrol.certificate.domain.CertificateState;
import co.gov.ids.stationerycontrol.certificate.infraestructure.feigns.IUserFeign;
import co.gov.ids.stationerycontrol.certificate.application.services.ICertificateService;
import co.gov.ids.stationerycontrol.certificate.infraestructure.resources.exceptions.FeignException;

/**
 * Class to represents the web service of certificates.
 *
 * @author Sergio Rodriguez
 * @version 0.0.2
 * @since 2020
 */
@RestController
@Api(tags = "certificate")
@RequestMapping("/api/certificate")
public class CertificateResource {

    private final ICertificateService service;
    private final IUserFeign feignClient;

    public CertificateResource(ICertificateService service, IUserFeign feignClient) {
        this.service = service;
        this.feignClient = feignClient;
    }

    /**
     * POST Method to create a Certificate.
     *
     * @param certificate Certificate to will be persist.
     * @return Certificate created, code 201.
     */
    @PostMapping
    @ApiOperation(value = "Create a Certificate", notes = "Service for create a new certificate")
    @ApiResponses(value = {@ApiResponse(code = 201, message = "Certificate was created correctly"),
            @ApiResponse(code = 400, message = "Invalid request")})
    public ResponseEntity<Certificate> create(@RequestBody Certificate certificate) {
        ResponseEntity usersResponse = feignClient.findByIdentification(certificate.getAttendant());
        if (usersResponse.getStatusCodeValue() == 200) {
            return new ResponseEntity<>(service.create(certificate), HttpStatus.CREATED);
        }
        throw new FeignException("Had an unexpected error with Users API");
    }

    /**
     * POST Method to create new multiple certificates.
     *
     * @param certificates List of Certificates to persist.
     * @return code 201.
     */
    @PostMapping("/multiple")
    @ApiOperation(value = "Create multiple Certificates", notes = "Service for create multiple Certificates.")
    @ApiResponses(value = {@ApiResponse(code = 201, message = "Certificates were created correctly"),
            @ApiResponse(code = 400, message = "Invalid request")})
    public ResponseEntity createMultiple(@RequestBody List<Certificate> certificates) {
        ResponseEntity usersResponse = feignClient.findByIdentification(certificates.get(0).getAttendant());
        if (usersResponse.getStatusCodeValue() == 200) {
            service.createMultiple(certificates);
            return new ResponseEntity(HttpStatus.CREATED);
        }
        throw new FeignException("Had an unexpected error with Users API");

    }

    /**
     * PUT Method to update a Certificate.
     *
     * @param number      number to identify the Certificate.
     * @param certificate Certificate to will be updated.
     * @return Certificate updated, code 200.
     */
    @PutMapping("/{number}")
    @ApiOperation(value = "Update a Certificate", notes = "Service for update a Certificate")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Certificate was updated correctly"),
            @ApiResponse(code = 400, message = "Number does not match with Certificate"),
            @ApiResponse(code = 404, message = "Certificate was not found")})
    public ResponseEntity<Certificate> update(@PathVariable("number") int number, @RequestBody Certificate certificate) {
        ResponseEntity usersResponse = feignClient.findByIdentification(certificate.getAttendant());
        if (usersResponse.getStatusCodeValue() == 200) {
            return new ResponseEntity<>(service.update(number, certificate), HttpStatus.OK);
        }
        throw new FeignException("Had an unexpected error with Users API");
    }

    /**
     * PUT Method to update multiple certificates.
     *
     * @param startNumber first number to start to update.
     * @param endNumber   last number to end to update.
     * @param attendant   new User responsible of Certificates.
     * @return code 200.
     */
    @PutMapping("/multiple/{startNumber}-{endNumber}/attendant/{attendant}")
    @ApiOperation(value = "Update attendant of multiple Certificates",
            notes = "Service for update attendant to multiple Certificates")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Certificates were updated correctly"),
            @ApiResponse(code = 400, message = "Invalid request")})
    public ResponseEntity updateMultiple(@PathVariable("startNumber") int startNumber,
                                         @PathVariable("endNumber") int endNumber,
                                         @PathVariable("attendant") String attendant) {
        ResponseEntity usersResponse = feignClient.findByIdentification(attendant);
        if (usersResponse.getStatusCodeValue() == 200) {
            service.updateMultipleCertificate(startNumber, endNumber, attendant);
            return new ResponseEntity(HttpStatus.OK);
        }
        throw new FeignException("Had an unexpected error with Users API");
    }

    /**
     * GET Method to list all certificates in a page of 20 items.
     *
     * @param page page to list.
     * @return list of Certificates.
     */
    @GetMapping("/all/{page}")
    @ApiOperation(value = "Get all Certificates", notes = "Service for get a List of all Certificates")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Certificates were listed successfully"),
            @ApiResponse(code = 404, message = "Not found")})
    public ResponseEntity<List<Certificate>> findAll(@PathVariable("page") int page) {
        return new ResponseEntity<List<Certificate>>(service.findAll(page), HttpStatus.OK);
    }

    /**
     * GET Method to find a Certificate by number.
     *
     * @param number number to identify a Certificate.
     * @return Certificate identified by number, code 200.
     */
    @GetMapping("/{number}")
    @ApiOperation(value = "Get a Certificate by number", notes = "Service for get a Certificate by number")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Certificate was consulted successfully"),
            @ApiResponse(code = 400, message = "Invalid request"), @ApiResponse(code = 404, message = "Not found")})
    public ResponseEntity<Certificate> findByNumber(@PathVariable("number") int number) {
        return new ResponseEntity<>(service.findByNumber(number), HttpStatus.OK);
    }

    /**
     * GET Method to list Certificates between startNumber and endNumber.
     *
     * @param startNumber number to identify Certificate, the first one to consult.
     * @param endNumber   number to identify Certificate, the last one to consult.
     * @return List of Certificates, code 200.
     */
    @GetMapping("/between/{startNumber}-{endNumber}")
    @ApiOperation(value = "Get a list of Certificates by numbers between start and end numbers",
            notes = "Service for get a list of Certificates between two numbers")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Certificates were listed successfully"),
            @ApiResponse(code = 400, message = "Invalid request"), @ApiResponse(code = 404, message = "Not found")})
    public ResponseEntity<List<Certificate>> findByNumberBetween(@PathVariable("startNumber") int startNumber,
                                                                 @PathVariable("endNumber") int endNumber) {
        return new ResponseEntity<>(service.findByNumberBetween(startNumber, endNumber), HttpStatus.OK);
    }

    /**
     * GET Method to list Certificates by Type.
     *
     * @param type type of Certificate.
     * @param page page to consult.
     * @return List of Certificates, code 200.
     */
    @GetMapping("/type/{type}/{page}")
    @ApiOperation(value = "Get a list of Certificates by type", notes = "Service for get a list of Certificates by type")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Certificates were listed successfully"),
            @ApiResponse(code = 400, message = "Invalid request"), @ApiResponse(code = 404, message = "Not found")})
    public ResponseEntity<List<Certificate>> findByType(@PathVariable("type") CertificateType type,
                                                        @PathVariable("page") int page) {
        return new ResponseEntity<>(service.findByType(type, page), HttpStatus.OK);
    }

    /**
     * GET Method to list Certificates by type and attendant.
     *
     * @param type      type of Certificates.
     * @param attendant User responsible of Certificates.
     * @param page      page to consult.
     * @return List of Certificates, code 200.
     */
    @GetMapping("/type/{type}/attendant/{attendant}/{page}")
    @ApiOperation(value = "Get a list of Certificates by type and attendant",
            notes = "Service for get a list of Certificates by type and attendant")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Certificates were listed successfully"),
            @ApiResponse(code = 400, message = "Invalid request"), @ApiResponse(code = 404, message = "Not found")})
    public ResponseEntity<List<Certificate>> findByTypeAndAttendant(@PathVariable("attendant") String attendant,
                                                                    @PathVariable("type") CertificateType type,
                                                                    @PathVariable("page") int page) {
        return new ResponseEntity<>(service.findByTypeAndAttendant(type, attendant, page), HttpStatus.OK);
    }

    /**
     * GET Method to list Certificates by Institution and Type of Certificate.
     *
     * @param institution institution where Certificates are from.
     * @param type        type of Certificate.
     * @param page        page to consult.
     * @return List of Certificates, code 200.
     */
    @GetMapping("/type/{type}/institution/{institution}/{page}")
    @ApiOperation(value = "Get a list of Certificates by institution and type",
            notes = "Service for get a list of Certificates by institution and type of Certificate")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Certificates were listed successfully"),
            @ApiResponse(code = 400, message = "Invalid request"), @ApiResponse(code = 404, message = "Not found")})
    public ResponseEntity<List<Certificate>> findByInstitutionAndType(@PathVariable("institution") String institution,
                                                                      @PathVariable("type") CertificateType type,
                                                                      @PathVariable("page") int page) {
        return new ResponseEntity<>(service.findByTypeAndInstitution(type, institution, page), HttpStatus.OK);
    }

    /**
     * GET Method to list Certificates by state.
     *
     * @param state state of Certificates.
     * @param page  page to consult.
     * @return List of Certificates, code 200.
     */
    @GetMapping("/state/{state}/{page}")
    @ApiOperation(value = "Get a list of Certificates by state", notes = "Service for get a list of Certificates by state")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Certificates were listed successfully"),
            @ApiResponse(code = 400, message = "Invalid request"), @ApiResponse(code = 404, message = "Not found")})
    public ResponseEntity<List<Certificate>> findByState(@PathVariable("state") CertificateState state,
                                                         @PathVariable("page") int page) {
        return new ResponseEntity<>(service.findByState(state, page), HttpStatus.OK);
    }

    /**
     * GET Method to list Certificates by state and attendant.
     *
     * @param state     state of Certificates.
     * @param attendant User responsible of the Certificates.
     * @param page      page to consult.
     * @return List of Certificates, code 200.
     */
    @GetMapping("/state/{state}/attendant/{attendant}/{page}")
    @ApiOperation(value = "Get a list of Certificates by state and attendant",
            notes = "Service for get a list of Certificates by state and attendant")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Certificates were listed successfully"),
            @ApiResponse(code = 400, message = "Invalid request"), @ApiResponse(code = 404, message = "Not found")})
    public ResponseEntity<List<Certificate>> findByStateAndAttendant(@PathVariable("state") CertificateState state,
                                                                     @PathVariable("attendant") String attendant,
                                                                     @PathVariable("page") int page) {
        return new ResponseEntity<>(service.findByStateAndAttendant(state, attendant, page), HttpStatus.OK);
    }

    /**
     * GET Method to list Certificates by Institution and State of Certificate.
     *
     * @param institution institution where Certificates are from.
     * @param state       state of Certificates.
     * @param page        page to consult.
     * @return List of Certificates, code 200.
     */
    @GetMapping("/state/{state}/institution/{institution}/{page}")
    @ApiOperation(value = "Get a list of Certificates by institution and state",
            notes = "Service for get a list of Certificates by institution and state of Certificate")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Certificates were listed successfully"),
            @ApiResponse(code = 400, message = "Invalid request"), @ApiResponse(code = 404, message = "Not found")})
    public ResponseEntity<List<Certificate>> findByInstitutionAndState(@PathVariable("state") CertificateState state,
                                                                       @PathVariable("institution") String institution,
                                                                       @PathVariable("page") int page) {
        return new ResponseEntity<>(service.findByStateAndInstitution(state, institution, page), HttpStatus.OK);
    }

    /**
     * GET Method to list Certificates by attendant.
     *
     * @param attendant User responsible of Certificates.
     * @param page      page to consult.
     * @return List of Certificates, code 200.
     */
    @GetMapping("/attendant/{attendant}/{page}")
    @ApiOperation(value = "Get a list of Certificates by attendant",
            notes = "Service for get a list of Certificates by attendant")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Certificates were listed successfully"),
            @ApiResponse(code = 400, message = "Invalid request"), @ApiResponse(code = 404, message = "Not found")})
    public ResponseEntity<List<Certificate>> findByAttendant(@PathVariable("attendant") String attendant,
                                                             @PathVariable("page") int page) {
        return new ResponseEntity<>(service.findByAttendant(attendant, page), HttpStatus.OK);
    }

    /**
     * GET Method to list Certificates by township.
     *
     * @param township township where Certificates are from.
     * @param page     page to consult.
     * @return List of Certificates, code 200.
     */
    @GetMapping("/township/{township}/{page}")
    @ApiOperation(value = "Get a list of Certificates by township",
            notes = "Service for get a list of Certificates by township")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Certificates were listed successfully"),
            @ApiResponse(code = 400, message = "Invalid request"), @ApiResponse(code = 404, message = "Not found")})
    public ResponseEntity<List<Certificate>> findByTownship(@PathVariable("township") String township,
                                                            @PathVariable("page") int page) {
        return new ResponseEntity<>(service.findByTownship(township, page), HttpStatus.OK);
    }

    /**
     * GET Method to list Certificates by Institution.
     *
     * @param institution institution where Certificates are from.
     * @param page        page to consult.
     * @return List of Certificates, code 200.
     */
    @GetMapping("/institution/{institution}/{page}")
    @ApiOperation(value = "Get a list of certificates by institution",
            notes = "Service for get a list of Certificates by institution")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Certificates were listed successfully"),
            @ApiResponse(code = 400, message = "Invalid request"), @ApiResponse(code = 404, message = "Not found")})
    public ResponseEntity<List<Certificate>> findByInstitution(@PathVariable("institution") String institution,
                                                               @PathVariable("page") int page) {
        return new ResponseEntity<>(service.findByInstitution(institution, page), HttpStatus.OK);
    }

}
