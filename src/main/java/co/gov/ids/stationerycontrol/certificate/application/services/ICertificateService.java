package co.gov.ids.stationerycontrol.certificate.application.services;

import java.util.List;

import co.gov.ids.stationerycontrol.certificate.domain.Certificate;
import co.gov.ids.stationerycontrol.certificate.domain.CertificateType;
import co.gov.ids.stationerycontrol.certificate.domain.CertificateState;

/**
 * Interface that represents the use cases of Certificates.
 *
 * @author Sergio Rodriguez
 * @version 0.0.2
 * @since 2020
 */
public interface ICertificateService {

    /**
     * Function to create a new Certificate.
     *
     * @param certificate Certificate to will be created.
     * @return Certificate created.
     */
    Certificate create(Certificate certificate);

    /**
     * Function to create multiple Certificates.
     *
     * @param certificates List of Certificates to will be created.
     */
    void createMultiple(List<Certificate> certificates);

    /**
     * Function to update a certificate.
     *
     * @param number      number to identify a Certificate.
     * @param certificate Certificate to will be updated.
     * @return Certificate updated.
     */
    Certificate update(int number, Certificate certificate);

    /**
     * Function to update attendant of a list of Certificates identified between startNumber and endNumber.
     *
     * @param startNumber start number of Certificate to update.
     * @param endNumber   end number of certificate to update.
     * @param attendant   new attendant of the Certificates.
     */
    void updateMultipleCertificate(int startNumber, int endNumber, String attendant);

    /**
     * Function to List all Certificates.
     *
     * @param page number of page to list.
     * @return List of 20 Certificates by page.
     */
    List<Certificate> findAll(int page);

    /**
     * Function to find a Certificate by number.
     *
     * @param number number to identify a Certificate.
     * @return Certificate identified by param.
     */
    Certificate findByNumber(int number);

    /**
     * Function to find a list of Certificates identified by numbers between start and end.
     *
     * @param startNumber start number from the certificates will be listed.
     * @param endNumber   end number up to where the certificates will be listed.
     * @return List of 20 Certificates from start to end by page.
     */
    List<Certificate> findByNumberBetween(int startNumber, int endNumber);

    /**
     * Function to find a List of Certificates by type.
     *
     * @param type type of Certificate.
     * @param page number of page to list.
     * @return List of 20 Certificates by page.
     */
    List<Certificate> findByType(CertificateType type, int page);

    /**
     * Function to find a List of Certificates by type and Attendant.
     *
     * @param type      type of Certificate.
     * @param attendant Attendant of the Certificate.
     * @param page      number of page to list.
     * @return List of 20 Certificates by page.
     */
    List<Certificate> findByTypeAndAttendant(CertificateType type, String attendant, int page);

    /**
     * Function to find a List of Certificates by Institution and Type.
     *
     * @param type        type of Certificate.
     * @param institution institution where the Certificates are from.
     * @param page        number of page to list.
     * @return List of 20 Certificates by page.
     */
    List<Certificate> findByTypeAndInstitution(CertificateType type, String institution, int page);

    /**
     * Function to find a List of Certificates by state.
     *
     * @param state state of Certificate.
     * @param page  number of page to list.
     * @return List of 20 Certificates by page.
     */
    List<Certificate> findByState(CertificateState state, int page);

    /**
     * Function to find a List of Certificates by state and Attendant.
     *
     * @param state     state of Certificate.
     * @param attendant Attendant of the Certificate.
     * @param page      number of page to list.
     * @return List of 20 Certificates by page.
     */
    List<Certificate> findByStateAndAttendant(CertificateState state, String attendant, int page);

    /**
     * Function to find a List of Certificates by Institution and State.
     *
     * @param state       state of Certificate.
     * @param institution institution where the Certificates are from.
     * @param page        number of page to list.
     * @return List of 20 Certificates by page.
     */
    List<Certificate> findByStateAndInstitution(CertificateState state, String institution, int page);

    /**
     * Function to find a list of Certificates by Attendant.
     *
     * @param attendant User Attendant of the Certificate.
     * @param page      number of page to list.
     * @return List of 20 Certificates by page.
     */
    List<Certificate> findByAttendant(String attendant, int page);

    /**
     * Function to find a List of Certificates by Township.
     *
     * @param township township where the Certificate are from.
     * @param page     number of page to list.
     * @return List of 20 Certificates by page.
     */
    List<Certificate> findByTownship(String township, int page);

    /**
     * Function to find a List of Certificates by Institution.
     *
     * @param institution institution where the Certificates are from.
     * @param page        number of page to list.
     * @return List of 20 Certificates by page.
     */
    List<Certificate> findByInstitution(String institution, int page);

}
