package co.gov.ids.stationerycontrol.certificate.persistence.entities;

import lombok.Data;
import javax.persistence.*;
import java.time.LocalDate;
import org.hibernate.validator.constraints.Range;
import co.gov.ids.stationerycontrol.certificate.domain.dto.CertificateType;
import co.gov.ids.stationerycontrol.certificate.domain.dto.CertificateState;

@Data
@Entity
@Table(name = "CERTIFICATES")
public class CertificateEntity {

    @Id
    @Range(min = 10000000, max = 99999999)
    private Integer number;

    @Column(nullable = false)
    private Byte verificationCode;

    @Column(nullable = false)
    private String department;

    private String township;

    private String institution;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CertificateType type;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CertificateState state;

    @Column(name = "state_date", nullable = false)
    private LocalDate stateDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "state_ruaf")
    private CertificateState stateRUAF;

    @Column(name = "state_date_ruaf")
    private LocalDate stateDateRUAF;

    private String attendant;

    private String attachment;

}
