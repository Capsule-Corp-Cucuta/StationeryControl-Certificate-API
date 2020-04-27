package co.gov.ids.stationerycontrol.certificate.framework.resources;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import co.gov.ids.stationerycontrol.certificate.domain.CertificateType;
import co.gov.ids.stationerycontrol.certificate.domain.CertificateState;
import co.gov.ids.stationerycontrol.certificate.application.services.IStatisticsService;

/**
 * Class to represents the web service of statistics from certificates.
 *
 * @author Sergio Rodriguez
 * @version 0.0.1
 * @since 2020
 */
@RestController
@Api(tags = "statistics")
@RequestMapping("/api/certificate/count")
public class StatisticsResource {

    private IStatisticsService service;

    public StatisticsResource(IStatisticsService service) {
        this.service = service;
    }

    /**
     * GET Method to count all Certificates.
     *
     * @return number of Certificates.
     */
    @GetMapping("/all")
    @ApiOperation(value = "Count certificates", notes = "Service for get the total number of Certificates")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Certificates counted successfully")})
    public ResponseEntity<Long> countAll() {
        return new ResponseEntity<>(service.countAll(), HttpStatus.OK);
    }

    /**
     * GET Method to count Certificates by township.
     *
     * @param township township where Certificates are from.
     * @return number of Certificates by township.
     */
    @GetMapping("/township/{township}")
    @ApiOperation(value = "Count certificates by township",
            notes = "Service for get the total number of certificates sorted by township")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Certificates counted successfully"),
            @ApiResponse(code = 400, message = "Invalid request")})
    public ResponseEntity<Long> countByTownship(@PathVariable("township") String township) {
        return new ResponseEntity<>(service.countByTownship(township), HttpStatus.OK);
    }

    /**
     * GET Method to count Certificates by institution.
     *
     * @param institution institution where Certificates are from.
     * @return number of Certificates by institution.
     */
    @GetMapping("/institution/{institution}")
    @ApiOperation(value = "Count certificates by institution",
            notes = "Service for get the total number of certificates sorted by institution")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Certificates counted successfully"),
            @ApiResponse(code = 400, message = "Invalid request")})
    public ResponseEntity<Long> countByInstitution(@PathVariable("institution") String institution) {
        return new ResponseEntity<>(service.countByInstitution(institution), HttpStatus.OK);
    }

    /**
     * GET Method to count Certificates by institution and Certificate type.
     *
     * @param institution institution where Certificates are from.
     * @param type        type of Certificates.
     * @return number of Certificates by institution and Certificate type.
     */
    @GetMapping("/institution/{institution}/type/{type}")
    @ApiOperation(value = "Count certificates by institution and certificate type",
            notes = "Service for get the total number of certificates sorted by institution and type")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Certificates counted successfully"),
            @ApiResponse(code = 400, message = "Invalid request")})
    public ResponseEntity<Long> countByInstitutionAndType(@PathVariable("institution") String institution,
                                                          @PathVariable("type") CertificateType type) {
        return new ResponseEntity<>(service.countByInstitutionAndType(institution, type), HttpStatus.OK);
    }

    /**
     * GET Method to count Certificates by institution and Certificates state.
     *
     * @param institution institution where Certificates are from.
     * @param state       state of Certificates.
     * @return number of Certificates by institution and Certificate state.
     */
    @GetMapping("/institution/{institution}/state/{state}")
    @ApiOperation(value = "Count certificates by institution and certificate state",
            notes = "Service fo get the total number of certificates sorted by institution and type")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Certificates counted successfully"),
            @ApiResponse(code = 400, message = "Invalid request")})
    public ResponseEntity<Long> countByInstitutionAndState(@PathVariable("institution") String institution,
                                                           @PathVariable("state") CertificateState state) {
        return new ResponseEntity<>(service.countByInstitutionAndState(institution, state), HttpStatus.OK);
    }

    /**
     * GET Method to count Certificates by Certificate type.
     *
     * @param type type of Certificates.
     * @return number of Certificates by Certificate type.
     */
    @GetMapping("/type/{type}")
    @ApiOperation(value = "Count certificates by type",
            notes = "Service for get the total number of certificates sorted by type")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Certificates counted successfully"),
            @ApiResponse(code = 400, message = "Invalid request")})
    public ResponseEntity<Long> countByType(@PathVariable("type") CertificateType type) {
        return new ResponseEntity<>(service.countByType(type), HttpStatus.OK);
    }

    /**
     * GET Method to count Certificates by Certificate type and attendant.
     *
     * @param type      type of Certificates.
     * @param attendant User responsible of the Certificates.
     * @return number of Certificates by Certificate type and attendant.
     */
    @GetMapping("/type/{type}/attendant/{attendant}")
    @ApiOperation(value = "Count certificates by type and attendant",
            notes = "Service for get the total number of certificates sorted by type and attendant")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Certificates counted successfully"),
            @ApiResponse(code = 400, message = "Invalid request")})
    public ResponseEntity<Long> countByTypeAndAttendant(@PathVariable("type") CertificateType type,
                                                        @PathVariable("attendant") String attendant) {
        return new ResponseEntity<>(service.countByTypeAndAttendant(type, attendant), HttpStatus.OK);
    }

    /**
     * GET Method to count Certificates by Certificate state.
     *
     * @param state state of Certificates.
     * @return number of Certificates by Certificate state.
     */
    @GetMapping("/state/{state}")
    @ApiOperation(value = "Count certificates by state",
            notes = "Service for get the total number of Certificates sorted by state")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Certificates counted successfully"),
            @ApiResponse(code = 400, message = "Invalid request")})
    public ResponseEntity<Long> countByState(@PathVariable("state") CertificateState state) {
        return new ResponseEntity<>(service.countByState(state), HttpStatus.OK);
    }

    /**
     * GET Method to count Certificates by Certificate state and attendant.
     *
     * @param state     state of Certificates.
     * @param attendant User responsible of the Certificates.
     * @return number of Certificates by Certificates state and attendant.
     */
    @GetMapping("/state/{state}/attendant/{attendant}")
    @ApiOperation(value = "Count certificates by state and attendant",
            notes = "Service for get the total number of Certificates sorted by state and attendant")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Certificates counted successfully"),
            @ApiResponse(code = 400, message = "Invalid request")})
    public ResponseEntity<Long> countByStateAndAttendant(@PathVariable("state") CertificateState state,
                                                         @PathVariable("attendant") String attendant) {
        return new ResponseEntity<>(service.countByStateAndAttendant(state, attendant), HttpStatus.OK);
    }

    /**
     * GET Method to count Certificates by attendant.
     *
     * @param attendant User responsible of the Certificates.
     * @return number of Certificates by attendant.
     */
    @GetMapping("/attendant/{attendant}")
    @ApiOperation(value = "Count certificates by attendant",
            notes = "Service for get the total number of Certificates sorted by attendant")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Certificates counted successfully"),
            @ApiResponse(code = 400, message = "Invalid request")})
    public ResponseEntity<Long> countByAttendant(@PathVariable("attendant") String attendant) {
        return new ResponseEntity<>(service.countByAttendant(attendant), HttpStatus.OK);
    }

}
