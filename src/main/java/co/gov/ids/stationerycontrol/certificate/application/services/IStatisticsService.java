package co.gov.ids.stationerycontrol.certificate.application.services;

import co.gov.ids.stationerycontrol.certificate.domain.CertificateType;
import co.gov.ids.stationerycontrol.certificate.domain.CertificateState;

/**
 * Interface that represents the use cases of statistics from Certificates.
 *
 * @author Sergio Rodriguez
 * @version 0.0.1
 * @since 2020
 */
public interface IStatisticsService {

    /**
     * Function to count all Certificates.
     *
     * @return a number of count of all Certificates.
     */
    public long countAll();

    /**
     * Function to count Certificates by Township.
     *
     * @param township township where Certificates are from.
     * @return a number of count of Certificates by Township.
     */
    public long countByTownship(String township);

    /**
     * Function to count Certificates by Institution.
     *
     * @param institution institution where Certificates are from.
     * @return a number of count of Certificates by institution.
     */
    public long countByInstitution(String institution);

    /**
     * Function to count Certificates by Institution and Type.
     *
     * @param institution institution where Certificates are from.
     * @param type        type of Certificates.
     * @return a number of count of Certificates by institution and type.
     */
    public long countByInstitutionAndType(String institution, CertificateType type);

    /**
     * Function to count Certificates by Institution and State.
     *
     * @param institution institution where Certificates are from.
     * @param state       state of Certificates.
     * @return a number of count Certificates by institution and type.
     */
    public long countByInstitutionAndState(String institution, CertificateState state);

    /**
     * Function to count Certificates by type.
     *
     * @param type type of Certificates.
     * @return a number of count Certificates by type.
     */
    public long countByType(CertificateType type);

    /**
     * Function to count Certificates by type and Attendant.
     *
     * @param type      type of Certificates.
     * @param attendant attendant of Certificates.
     * @return a number of count of Certificates by type and attendant.
     */
    public long countByTypeAndAttendant(CertificateType type, String attendant);

    /**
     * Function to count Certificates by state.
     *
     * @param state state of Certificates.
     * @return a number of count of Certificates by state.
     */
    public long countByState(CertificateState state);

    /**
     * Function to count Certificates by state and attendant.
     *
     * @param state     state of Certificates.
     * @param attendant attendant of Certificates.
     * @return a number of count of Certificates by state and attendant.
     */
    public long countByStateAndAttendant(CertificateState state, String attendant);

    /**
     * Function to count Certificates by Attendant.
     *
     * @param attendant User Attendant of the Certificates.
     * @return a number of count of Certificates by Attendant.
     */
    public long countByAttendant(String attendant);

}
