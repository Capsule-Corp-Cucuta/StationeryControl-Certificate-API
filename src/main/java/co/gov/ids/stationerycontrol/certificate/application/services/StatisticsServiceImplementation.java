package co.gov.ids.stationerycontrol.certificate.application.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import co.gov.ids.stationerycontrol.certificate.domain.CertificateType;
import co.gov.ids.stationerycontrol.certificate.domain.CertificateState;
import co.gov.ids.stationerycontrol.certificate.application.exceptions.BadRequestException;
import co.gov.ids.stationerycontrol.certificate.framework.persistence.repositories.IStatisticsRepository;

/**
 * Class that implements IStatisticsService.
 *
 * @author Sergio Rodriguez
 * @version 0.0.1
 * @since 2020
 */
@Service
@Transactional(readOnly = true)
public class StatisticsServiceImplementation implements IStatisticsService {

    private final IStatisticsRepository repository;

    public StatisticsServiceImplementation(IStatisticsRepository repository) {
        this.repository = repository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public long countAll() {
        return repository.count();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public long countByType(CertificateType type) {
        return repository.countByType(type);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public long countByTypeAndAttendant(CertificateType type, String attendant) {
        if (attendant.isEmpty()) {
            throw new BadRequestException("Fields should not be empty");
        }
        return repository.countByTypeAndAttendant(type, attendant);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public long countByTypeAndInstitution(CertificateType type, String institution) {
        if (institution.isEmpty()) {
            throw new BadRequestException("Fields should not be empty");
        }
        return repository.countByTypeAndInstitution(type, institution);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public long countByState(CertificateState state) {
        return repository.countByState(state);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public long countByStateAndAttendant(CertificateState state, String attendant) {
        if (attendant.isEmpty()) {
            throw new BadRequestException("Fields should not be empty");
        }
        return repository.countByStateAndAttendant(state, attendant);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public long countByStateAndInstitution(CertificateState state, String institution) {
        if (institution.isEmpty()) {
            throw new BadRequestException("Fields should not be empty");
        }
        return repository.countByStateAndInstitution(state, institution);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public long countByAttendant(String attendant) {
        if (attendant.isEmpty()) {
            throw new BadRequestException("Fields should not be empty");
        }
        return repository.countByAttendant(attendant);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public long countByTownship(String township) {
        if (township.isEmpty()) {
            throw new BadRequestException("Township field should not be empty");
        }
        return repository.countByTownship(township);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public long countByInstitution(String institution) {
        if (institution.isEmpty()) {
            throw new BadRequestException("Institution field should not be empty");
        }
        return repository.countByInstitution(institution);
    }

}
