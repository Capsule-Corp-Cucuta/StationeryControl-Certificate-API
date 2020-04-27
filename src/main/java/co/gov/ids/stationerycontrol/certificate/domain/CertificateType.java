package co.gov.ids.stationerycontrol.certificate.domain;

/**
 * Enum of certificate types.
 *
 * @author Sergio Rodriguez
 * @version 0.0.1
 * @since 2020
 */
public enum CertificateType {

    /**
     * Current certificate of born alive.
     */
    CA_NV,

    /**
     * Born alive.
     */
    NV,

    /**
     * Current certificate of death.
     */
    CA_DEF,

    /**
     * Death.
     */
    DEF;

}
