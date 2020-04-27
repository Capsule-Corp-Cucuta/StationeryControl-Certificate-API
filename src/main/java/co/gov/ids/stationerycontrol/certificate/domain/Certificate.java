package co.gov.ids.stationerycontrol.certificate.domain;

import lombok.Data;
import java.util.Date;

/**
 * Class to represent Certificates.
 *
 * @author Sergio Rodriguez
 * @version 0.0.1
 * @since 2020
 */
@Data
public class Certificate {

    private int number;
    private byte verificationCode;
    private String department;
    private String township;
    private String institution;
    private CertificateType type;
    private CertificateState state;
    private Date stateDate;
    private String attendant;
    private String attachment;

}
