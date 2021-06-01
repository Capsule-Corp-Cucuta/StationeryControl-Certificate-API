package co.gov.ids.stationerycontrol.certificate.persistence.repositories;

import org.springframework.data.repository.CrudRepository;
import co.gov.ids.stationerycontrol.certificate.domain.dto.CertificateType;
import co.gov.ids.stationerycontrol.certificate.domain.dto.CertificateState;
import co.gov.ids.stationerycontrol.certificate.persistence.entities.CertificateEntity;

public interface IStatisticsCrudRepository extends CrudRepository<CertificateEntity, Integer> {

    long countByType(CertificateType type);

    long countByTypeAndAttendant(CertificateType type, String attendant);

    long countByTypeAndInstitution(CertificateType type, String institution);

    long countByState(CertificateState state);

    long countByStateAndAttendant(CertificateState state, String attendant);

    long countByStateAndInstitution(CertificateState state, String institution);

    long countByAttendant(String attendant);

    long countByTownship(String township);

    long countByInstitution(String institution);

}
