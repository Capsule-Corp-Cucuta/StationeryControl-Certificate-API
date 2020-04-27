package co.gov.ids.stationerycontrol.certificate.domain;

/**
 * Enum of certificate states.
 *
 * @author Sergio Rodriguez
 * @version 0.0.1
 * @since 2020
 */
public enum CertificateState {

    /**
     * Certificate without use.
     */
    IDLE,

    /**
     * Certificate assented to a user.
     */
    ASSIGNED,

    /**
     * Certificate used correctly.
     */
    GUARDED,

    /**
     * Certificate lost.
     */
    STRAY,

    /**
     * Certificated used incorrectly.
     */
    ANNULLED,

    /**
     * Certificate saved like correctly but with failures.
     */
    WITH_INCONGRUENCES;

}
