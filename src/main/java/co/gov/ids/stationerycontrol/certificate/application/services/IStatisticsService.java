package co.gov.ids.stationerycontrol.certificate.application.services;

import co.gov.ids.stationerycontrol.certificate.domain.CertificateType;
import co.gov.ids.stationerycontrol.certificate.domain.CertificateState;

/**
 * Interface that represents the use cases of statistics from Certificates.
 *
 * @author Sergio Rodriguez
 * @version 0.0.2
 * @since 2020
 */
public interface IStatisticsService {

    /**
     * Function to count all Certificates.
     *
     * @return a number of count of all Certificates.
     */
    long countAll();

    /**
     * Function to count Certificates by type.
     *
     * @param type type of Certificates.
     * @return a number of count Certificates by type.
     */
    long countByType(CertificateType type);

    /**
     * Function to count Certificates by type and Attendant.
     *
     * @param type      type of Certificates.
     * @param attendant attendant of Certificates.
     * @return a number of count of Certificates by type and attendant.
     */
    long countByTypeAndAttendant(CertificateType type, String attendant);

    /**
     * Function to count Certificates by Institution and Type.
     *
     * @param type        type of Certificates.
     * @param institution institution where Certificates are from.
     * @return a number of count of Certificates by institution and type.
     */
    long countByTypeAndInstitution(CertificateType type, String institution);

    /**
     * Function to count Certificates by state.
     *
     * @param state state of Certificates.
     * @return a number of count of Certificates by state.
     */
    long countByState(CertificateState state);

    /**
     * Function to count Certificates by state and attendant.
     *
     * @param state     state of Certificates.
     * @param attendant attendant of Certificates.
     * @return a number of count of Certificates by state and attendant.
     */
    long countByStateAndAttendant(CertificateState state, String attendant);

    /**
     * Function to count Certificates by Institution and State.
     *
     * @param state       state of Certificates.
     * @param institution institution where Certificates are from.
     * @return a number of count Certificates by institution and type.
     */
    long countByStateAndInstitution(CertificateState state, String institution);

    /**
     * Function to count Certificates by Attendant.
     *
     * @param attendant User Attendant of the Certificates.
     * @return a number of count of Certificates by Attendant.
     */
    long countByAttendant(String attendant);

    /**
     * Function to count Certificates by Township.
     *
     * @param township township where Certificates are from.
     * @return a number of count of Certificates by Township.
     */
    long countByTownship(String township);

    /**
     * Function to count Certificates by Institution.
     *
     * @param institution institution where Certificates are from.
     * @return a number of count of Certificates by institution.
     */
    long countByInstitution(String institution);

}
