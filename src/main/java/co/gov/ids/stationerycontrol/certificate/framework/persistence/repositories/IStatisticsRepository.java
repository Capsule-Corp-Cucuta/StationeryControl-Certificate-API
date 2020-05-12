package co.gov.ids.stationerycontrol.certificate.framework.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import co.gov.ids.stationerycontrol.certificate.domain.CertificateType;
import co.gov.ids.stationerycontrol.certificate.domain.CertificateState;
import co.gov.ids.stationerycontrol.certificate.framework.persistence.entities.CertificateEntity;

/**
 * Interface that define the available operations to DB.
 *
 * @author Sergio Rodriguez
 * @version 0.0.2
 * @since 2020
 */
public interface IStatisticsRepository extends JpaRepository<CertificateEntity, Long> {

    /**
     * Function to count Certificates by Type of Certificate.
     *
     * @param type type of Certificate.
     * @return number of Certificates by type.
     */
    long countByType(CertificateType type);

    /**
     * Function to count Certificates by Type of Certificate and Attendant.
     *
     * @param type      type of Certificate.
     * @param attendant User responsible of the Certificate.
     * @return number of Certificates by type and attendant.
     */
    long countByTypeAndAttendant(CertificateType type, String attendant);

    /**
     * Function to count Certificates by Institution and Type of Certificate.
     *
     * @param type        type of Certificate.
     * @param institution institution where Certificates are from.
     * @return number of Certificates by institution and type.
     */
    long countByTypeAndInstitution(CertificateType type, String institution);

    /**
     * Function to count Certificates by State of Certificate.
     *
     * @param state state of Certificate.
     * @return number of Certificates by state.
     */
    long countByState(CertificateState state);

    /**
     * Function to count Certificates by State of Certificate and Attendant.
     *
     * @param state     state of certificate.
     * @param attendant User responsible of the Certificate.
     * @return number of Certificates by state and attendant.
     */
    long countByStateAndAttendant(CertificateState state, String attendant);

    /**
     * Function to count Certificates by Institution and State of Certificate.
     *
     * @param state       state of Certificate.
     * @param institution institution where Certificate are from.
     * @return number of Certificates by institution and state.
     */
    long countByStateAndInstitution(CertificateState state, String institution);

    /**
     * Function to count Certificates by Attendant.
     *
     * @param attendant User responsible of the Certificate.
     * @return number of Certificates by attendant.
     */
    long countByAttendant(String attendant);

    /**
     * Function to count Certificates by Township.
     *
     * @param township township where Certificates are from.
     * @return number of Certificates by township.
     */
    long countByTownship(String township);

    /**
     * Function to count Certificates by Institution.
     *
     * @param institution institution where Certificates are from.
     * @return number of Certificates by institution.
     */
    long countByInstitution(String institution);

}
