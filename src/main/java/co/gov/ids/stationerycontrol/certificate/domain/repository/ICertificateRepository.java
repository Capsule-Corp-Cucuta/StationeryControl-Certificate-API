package co.gov.ids.stationerycontrol.certificate.domain.repository;

import java.util.List;
import java.util.Optional;
import co.gov.ids.stationerycontrol.certificate.domain.dto.Certificate;
import co.gov.ids.stationerycontrol.certificate.domain.dto.CertificateType;
import co.gov.ids.stationerycontrol.certificate.domain.dto.CertificateState;

public interface ICertificateRepository {

    Certificate create(Certificate certificate);

    boolean createMultiple(List<Certificate> certificates);

    Certificate update(Certificate certificate);

    boolean updateMultipleCertificate(List<Certificate> certificates);

    Optional<Certificate> findByNumber(int number);

    Optional<List<Certificate>> findAll(int page);

    Optional<List<Certificate>> findByNumberBetween(int startNumber, int endNumber);

    Optional<List<Certificate>> findByType(CertificateType type, int page);

    Optional<List<Certificate>> findByTypeAndAttendant(CertificateType type, String attendant, int page);

    Optional<List<Certificate>> findByTypeAndInstitution(CertificateType type, String institution, int page);

    Optional<List<Certificate>> findByState(CertificateState state, int page);

    Optional<List<Certificate>> findByStateAndAttendant(CertificateState state, String attendant, int page);

    Optional<List<Certificate>> findByStateAndInstitution(CertificateState state, String institution, int page);

    Optional<List<Certificate>> findByAttendant(String attendant, int page);

    Optional<List<Certificate>> findByTownship(String township, int page);

    Optional<List<Certificate>> findByInstitution(String institution, int page);

}
