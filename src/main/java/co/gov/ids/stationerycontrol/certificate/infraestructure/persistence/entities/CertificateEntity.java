package co.gov.ids.stationerycontrol.certificate.infraestructure.persistence.entities;

import lombok.Data;
import java.util.Date;
import javax.persistence.*;

import co.gov.ids.stationerycontrol.certificate.domain.CertificateType;
import co.gov.ids.stationerycontrol.certificate.domain.CertificateState;

/**
 * Class to represent Certificates for persist in DB.
 *
 * @author Sergio Rodriguez
 * @version 0.0.1
 * @since 2020
 */
@Data
@Entity
@Table(name = "certificate")
public class CertificateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "number", nullable = false)
    private int number;

    @Column(name = "verification_code", nullable = false)
    private byte verificationCode;

    @Column(name = "department", nullable = false)
    private String department;

    @Column(name = "township", nullable = true)
    private String township;

    @Column(name = "institution", nullable = true)
    private String institution;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private CertificateType type;

    @Enumerated(EnumType.STRING)
    @Column(name = "state", nullable = false)
    private CertificateState state;

    @Temporal(TemporalType.DATE)
    @Column(name = "state_date", nullable = false)
    private Date stateDate;

    @Column(name = "attendant_id", nullable = true)
    private String attendant;

    @Column(name = "attachment", nullable = true)
    private String attachment;

}
