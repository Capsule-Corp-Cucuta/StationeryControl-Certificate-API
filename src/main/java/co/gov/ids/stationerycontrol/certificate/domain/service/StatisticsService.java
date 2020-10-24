package co.gov.ids.stationerycontrol.certificate.domain.service;

import org.springframework.stereotype.Service;
import co.gov.ids.stationerycontrol.certificate.domain.dto.CertificateType;
import co.gov.ids.stationerycontrol.certificate.domain.dto.CertificateState;
import co.gov.ids.stationerycontrol.certificate.domain.repository.IStatisticsRepository;

@Service
public class StatisticsService {

    private final IStatisticsRepository repository;

    public StatisticsService(IStatisticsRepository repository) {
        this.repository = repository;
    }

    public long countAll() {
        return repository.countAll();
    }

    public long countByType(CertificateType type) {
        return repository.countByType(type);
    }

    public long countByTypeAndAttendant(CertificateType type, String attendant) {
        return repository.countByTypeAndAttendant(type, attendant);
    }

    public long countByTypeAndInstitution(CertificateType type, String institution) {
        return repository.countByTypeAndInstitution(type, institution);
    }

    public long countByState(CertificateState state) {
        return repository.countByState(state);
    }

    public long countByStateAndAttendant(CertificateState state, String attendant) {
        return repository.countByStateAndAttendant(state, attendant);
    }

    public long countByStateAndInstitution(CertificateState state, String institution) {
        return repository.countByStateAndInstitution(state, institution);
    }

    public long countByAttendant(String attendant) {
        return repository.countByAttendant(attendant);
    }

    public long countByTownship(String township) {
        return repository.countByTownship(township);
    }

    public long countByInstitution(String institution) {
        return repository.countByInstitution(institution);
    }

}
