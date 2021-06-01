package co.gov.ids.stationerycontrol.certificate.web.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import co.gov.ids.stationerycontrol.certificate.domain.dto.CertificateType;
import co.gov.ids.stationerycontrol.certificate.domain.dto.CertificateState;
import co.gov.ids.stationerycontrol.certificate.domain.service.StatisticsService;

@RestController
@Api(tags = "statistics")
@RequestMapping("/certificate/count")
public class StatisticsController {

    private final StatisticsService service;

    public StatisticsController(StatisticsService service) {
        this.service = service;
    }

    @GetMapping("/all")
    @ApiOperation(value = "Count certificates", notes = "Service for get the total number of Certificates")
    @ApiResponse(code = 200, message = "Certificates counted successfully")
    public ResponseEntity<Long> countAll() {
        return new ResponseEntity<>(service.countAll(), HttpStatus.OK);
    }

    @GetMapping("/type/{type}")
    @ApiOperation(
            value = "Count certificates by type",
            notes = "Service for get the total number of certificates sorted by type"
    )
    @ApiResponse(code = 200, message = "Certificates counted successfully")
    public ResponseEntity<Long> countByType(@PathVariable("type") CertificateType type) {
        return new ResponseEntity<>(service.countByType(type), HttpStatus.OK);
    }

    @GetMapping("/type/{type}/attendant/{attendant}")
    @ApiOperation(
            value = "Count certificates by type and attendant",
            notes = "Service for get the total number of certificates sorted by type and attendant"
    )
    @ApiResponse(code = 200, message = "Certificates counted successfully")
    public ResponseEntity<Long> countByTypeAndAttendant(@PathVariable("type") CertificateType type,
                                                        @PathVariable("attendant") String attendant) {
        return new ResponseEntity<>(service.countByTypeAndAttendant(type, attendant), HttpStatus.OK);
    }

    @GetMapping("/type/{type}/institution/{institution}")
    @ApiOperation(
            value = "Count certificates by institution and certificate type",
            notes = "Service for get the total number of certificates sorted by institution and type"
    )
    @ApiResponse(code = 200, message = "Certificates counted successfully")
    public ResponseEntity<Long> countByInstitutionAndType(@PathVariable("type") CertificateType type,
                                                          @PathVariable("institution") String institution) {
        return new ResponseEntity<>(service.countByTypeAndInstitution(type, institution), HttpStatus.OK);
    }

    @GetMapping("/state/{state}")
    @ApiOperation(
            value = "Count certificates by state",
            notes = "Service for get the total number of Certificates sorted by state"
    )
    @ApiResponse(code = 200, message = "Certificates counted successfully")
    public ResponseEntity<Long> countByState(@PathVariable("state") CertificateState state) {
        return new ResponseEntity<>(service.countByState(state), HttpStatus.OK);
    }

    @GetMapping("/state/{state}/attendant/{attendant}")
    @ApiOperation(
            value = "Count certificates by state and attendant",
            notes = "Service for get the total number of Certificates sorted by state and attendant"
    )
    @ApiResponse(code = 200, message = "Certificates counted successfully")
    public ResponseEntity<Long> countByStateAndAttendant(@PathVariable("state") CertificateState state,
                                                         @PathVariable("attendant") String attendant) {
        return new ResponseEntity<>(service.countByStateAndAttendant(state, attendant), HttpStatus.OK);
    }

    @GetMapping("/state/{state}/institution/{institution}")
    @ApiOperation(
            value = "Count certificates by institution and certificate state",
            notes = "Service fo get the total number of certificates sorted by institution and type"
    )
    @ApiResponse(code = 200, message = "Certificates counted successfully")
    public ResponseEntity<Long> countByStateAndInstitution(@PathVariable("state") CertificateState state,
                                                           @PathVariable("institution") String institution) {
        return new ResponseEntity<>(service.countByStateAndInstitution(state, institution), HttpStatus.OK);
    }

    @GetMapping("/attendant/{attendant}")
    @ApiOperation(
            value = "Count certificates by attendant",
            notes = "Service for get the total number of Certificates sorted by attendant"
    )
    @ApiResponse(code = 200, message = "Certificates counted successfully")
    public ResponseEntity<Long> countByAttendant(@PathVariable("attendant") String attendant) {
        return new ResponseEntity<>(service.countByAttendant(attendant), HttpStatus.OK);
    }

    @GetMapping("/township/{township}")
    @ApiOperation(
            value = "Count certificates by township",
            notes = "Service for get the total number of certificates sorted by township"
    )
    @ApiResponse(code = 200, message = "Certificates counted successfully")
    public ResponseEntity<Long> countByTownship(@PathVariable("township") String township) {
        return new ResponseEntity<>(service.countByTownship(township), HttpStatus.OK);
    }

    @GetMapping("/institution/{institution}")
    @ApiOperation(
            value = "Count certificates by institution",
            notes = "Service for get the total number of certificates sorted by institution"
    )
    @ApiResponse(code = 200, message = "Certificates counted successfully")
    public ResponseEntity<Long> countByInstitution(@PathVariable("institution") String institution) {
        return new ResponseEntity<>(service.countByInstitution(institution), HttpStatus.OK);
    }

}
