package co.gov.ids.stationerycontrol.certificate.domain.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class Certificate {

    private int number;
    private byte verificationCode;
    private String department;
    private String township;
    private String institution;
    private CertificateType type;
    private CertificateState state;
    private LocalDate stateDate;
    private CertificateState stateRUAF;
    private LocalDate stateDateRUAF;
    private String attendant;
    private String attachment;

}
