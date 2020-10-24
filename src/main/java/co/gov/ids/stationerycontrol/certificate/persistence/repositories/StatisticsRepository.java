package co.gov.ids.stationerycontrol.certificate.persistence.repositories;

import org.springframework.stereotype.Repository;
import co.gov.ids.stationerycontrol.certificate.domain.dto.CertificateType;
import co.gov.ids.stationerycontrol.certificate.domain.dto.CertificateState;
import co.gov.ids.stationerycontrol.certificate.domain.repository.IStatisticsRepository;

@Repository
public class StatisticsRepository implements IStatisticsRepository {

    private final IStatisticsCrudRepository repository;

    public StatisticsRepository(IStatisticsCrudRepository repository) {
        this.repository = repository;
    }

    @Override
    public long countAll() {
        return repository.count();
    }

    @Override
    public long countByType(CertificateType type) {
        return repository.countByType(type);
    }

    @Override
    public long countByTypeAndAttendant(CertificateType type, String attendant) {
        return repository.countByTypeAndAttendant(type, attendant);
    }

    @Override
    public long countByTypeAndInstitution(CertificateType type, String institution) {
        return repository.countByTypeAndInstitution(type, institution);
    }

    @Override
    public long countByState(CertificateState state) {
        return repository.countByState(state);
    }

    @Override
    public long countByStateAndAttendant(CertificateState state, String attendant) {
        return repository.countByStateAndAttendant(state, attendant);
    }

    @Override
    public long countByStateAndInstitution(CertificateState state, String institution) {
        return repository.countByStateAndInstitution(state, institution);
    }

    @Override
    public long countByAttendant(String attendant) {
        return repository.countByAttendant(attendant);
    }

    @Override
    public long countByTownship(String township) {
        return repository.countByTownship(township);
    }

    @Override
    public long countByInstitution(String institution) {
        return repository.countByInstitution(institution);
    }
}
