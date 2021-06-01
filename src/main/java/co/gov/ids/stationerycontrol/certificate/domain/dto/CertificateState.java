package co.gov.ids.stationerycontrol.certificate.domain.dto;

public enum CertificateState {
    IDLE,
    ASSIGNED,
    GUARDED,
    STRAY,
    ANNULLED,
    WITH_INCONGRUENCES;
}
