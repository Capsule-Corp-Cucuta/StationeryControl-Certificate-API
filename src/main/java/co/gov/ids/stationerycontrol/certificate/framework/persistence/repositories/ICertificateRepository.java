package co.gov.ids.stationerycontrol.certificate.framework.persistence.repositories;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import co.gov.ids.stationerycontrol.certificate.domain.CertificateType;
import co.gov.ids.stationerycontrol.certificate.domain.CertificateState;
import co.gov.ids.stationerycontrol.certificate.framework.persistence.entities.CertificateEntity;

/**
 * Interface that define the available operations to DB.
 *
 * @author Sergio Rodriguez
 * @version 0.0.1
 * @since 2020
 */
public interface ICertificateRepository extends JpaRepository<CertificateEntity, Long> {

    /**
     * Function to find a Certificate by number.
     *
     * @param number number to identify the Certificate.
     * @return Certificate identified by number.
     */
    public CertificateEntity findByNumber(int number);

    /**
     * Function to list Certificates by startNumber and endNumber.
     *
     * @param startNumber number to identify the Certificate, the first one to consult.
     * @param endNumber   number to identify the Certificate, the last one to consult.
     * @return List of Certificates between startNumber and endNumber.
     */
    public List<CertificateEntity> findByNumberBetween(int startNumber, int endNumber);

    /**
     * Function to list Certificates by township.
     *
     * @param township township where Certificates are from.
     * @param page     page to consult.
     * @return List of Certificates by page.
     */
    public Page<CertificateEntity> findByTownship(String township, Pageable page);

    /**
     * Function to list Certificates by Institution.
     *
     * @param institution institution where Certificates are from.
     * @param page        page to consult.
     * @return List of Certificates by page.
     */
    public Page<CertificateEntity> findByInstitution(String institution, Pageable page);

    /**
     * Function to list Certificates by Institution and Type.
     *
     * @param institution institution where Certificates are from.
     * @param type        type of Certificate.
     * @param page        page to consult.
     * @return List of Certificates by page.
     */
    public Page<CertificateEntity> findByInstitutionAndType(String institution, CertificateType type, Pageable page);

    /**
     * Function to list Certificates by Institution and State.
     *
     * @param institution institution where Certificates are from.
     * @param state       state pf Certificate.
     * @param page        page to consult.
     * @return List of Certificates by page.
     */
    public Page<CertificateEntity> findByInstitutionAndState(String institution, CertificateState state, Pageable page);

    /**
     * Function to list Certificates by Type of Certificate.
     *
     * @param type type of Certificate.
     * @param page page to consult.
     * @return List of Certificates by page.
     */
    public Page<CertificateEntity> findByType(CertificateType type, Pageable page);

    /**
     * Function to list Certificates by Type and Attendant.
     *
     * @param type      type of Certificate.
     * @param attendant User responsible of the Certificate.
     * @param page      page to consult.
     * @return List of Certificates by page.
     */
    public Page<CertificateEntity> findByTypeAndAttendant(CertificateType type, String attendant, Pageable page);

    /**
     * Function to list Certificates by State.
     *
     * @param state state of Certificate.
     * @param page  page to consult.
     * @return List of Certificates by page.
     */
    public Page<CertificateEntity> findByState(CertificateState state, Pageable page);

    /**
     * Function to list Certificates by State and Attendant.
     *
     * @param state     state of Certificate.
     * @param attendant User responsible of the Certificate.
     * @param page      page to consult.
     * @return List of Certificates by page.
     */
    public Page<CertificateEntity> findByStateAndAttendant(CertificateState state, String attendant, Pageable page);

    /**
     * Function to list Certificates by Attendant.
     *
     * @param attendant User responsible of the Certificate.
     * @param page      page to consult.
     * @return List of Certificates by page.
     */
    public Page<CertificateEntity> findByAttendant(String attendant, Pageable page);

}
