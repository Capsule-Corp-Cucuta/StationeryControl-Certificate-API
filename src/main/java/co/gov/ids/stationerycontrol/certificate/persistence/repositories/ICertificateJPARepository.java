package co.gov.ids.stationerycontrol.certificate.persistence.repositories;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import co.gov.ids.stationerycontrol.certificate.domain.dto.CertificateType;
import co.gov.ids.stationerycontrol.certificate.domain.dto.CertificateState;
import co.gov.ids.stationerycontrol.certificate.persistence.entities.CertificateEntity;

public interface ICertificateJPARepository extends PagingAndSortingRepository<CertificateEntity, Integer> {

    Optional<List<CertificateEntity>> findByNumberBetween(int startNumber, int endNumber);

    Optional<Page<CertificateEntity>> findByType(CertificateType type, Pageable page);

    Optional<Page<CertificateEntity>> findByTypeAndAttendant(CertificateType type, String attendant, Pageable page);

    Optional<Page<CertificateEntity>> findByTypeAndInstitution(CertificateType type, String institution, Pageable page);

    Optional<Page<CertificateEntity>> findByState(CertificateState state, Pageable page);

    Optional<Page<CertificateEntity>> findByStateAndAttendant(CertificateState state, String attendant, Pageable page);

    Optional<Page<CertificateEntity>> findByStateAndInstitution(CertificateState state, String institution, Pageable page);

    Optional<Page<CertificateEntity>> findByAttendant(String attendant, Pageable page);

    Optional<Page<CertificateEntity>> findByTownship(String township, Pageable page);

    Optional<Page<CertificateEntity>> findByInstitution(String institution, Pageable page);

}
