package co.gov.ids.stationerycontrol.certificate.persistence.repositories;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.PageRequest;
import co.gov.ids.stationerycontrol.certificate.domain.dto.Certificate;
import co.gov.ids.stationerycontrol.certificate.domain.dto.CertificateType;
import co.gov.ids.stationerycontrol.certificate.domain.dto.CertificateState;
import co.gov.ids.stationerycontrol.certificate.persistence.mapper.CertificateMapper;
import co.gov.ids.stationerycontrol.certificate.domain.repository.ICertificateRepository;

@Repository
public class CertificateRepository implements ICertificateRepository {

    private final int SIZE_PAGE = 25;
    private final CertificateMapper mapper;
    private final ICertificateJPARepository repository;

    public CertificateRepository(CertificateMapper mapper, ICertificateJPARepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    @Override
    public Certificate create(Certificate certificate) {
        return mapper.toCertificate(repository.save(mapper.toEntity(certificate)));
    }

    @Override
    public boolean createMultiple(List<Certificate> certificates) {
        repository.saveAll(mapper.toEntities(certificates));
        return true;
    }

    @Override
    public Certificate update(Certificate certificate) {
        return mapper.toCertificate(repository.save(mapper.toEntity(certificate)));
    }

    @Override
    public boolean updateMultipleCertificate(List<Certificate> certificates) {
        repository.saveAll(mapper.toEntities(certificates));
        return true;
    }

    @Override
    public Optional<Certificate> findByNumber(int number) {
        return repository.findById(number).map(entity -> mapper.toCertificate(entity));
    }

    @Override
    public Optional<List<Certificate>> findAll(int page) {
        return Optional.of(mapper.toCertificates(repository.findAll(PageRequest.of(page, SIZE_PAGE)).getContent()));
    }

    @Override
    public Optional<List<Certificate>> findByNumberBetween(int startNumber, int endNumber) {
        return repository.findByNumberBetween(startNumber, endNumber)
                .map(entities -> mapper.toCertificates(entities));
    }

    @Override
    public Optional<List<Certificate>> findByType(CertificateType type, int page) {
        return repository.findByType(type, PageRequest.of(page, SIZE_PAGE))
                .map(entities -> mapper.toCertificates(entities.getContent()));
    }

    @Override
    public Optional<List<Certificate>> findByTypeAndAttendant(CertificateType type, String attendant, int page) {
        return repository.findByTypeAndAttendant(type, attendant, PageRequest.of(page, SIZE_PAGE))
                .map(entities -> mapper.toCertificates(entities.getContent()));
    }

    @Override
    public Optional<List<Certificate>> findByTypeAndInstitution(CertificateType type, String institution, int page) {
        return repository.findByTypeAndInstitution(type, institution, PageRequest.of(page, SIZE_PAGE))
                .map(entities -> mapper.toCertificates(entities.getContent()));
    }

    @Override
    public Optional<List<Certificate>> findByState(CertificateState state, int page) {
        return repository.findByState(state, PageRequest.of(page, SIZE_PAGE))
                .map(entities -> mapper.toCertificates(entities.getContent()));
    }

    @Override
    public Optional<List<Certificate>> findByStateAndAttendant(CertificateState state, String attendant, int page) {
        return repository.findByStateAndAttendant(state, attendant, PageRequest.of(page, SIZE_PAGE))
                .map(entities -> mapper.toCertificates(entities.getContent()));
    }

    @Override
    public Optional<List<Certificate>> findByStateAndInstitution(CertificateState state, String institution, int page) {
        return repository.findByStateAndInstitution(state, institution, PageRequest.of(page, SIZE_PAGE))
                .map(entities -> mapper.toCertificates(entities.getContent()));
    }

    @Override
    public Optional<List<Certificate>> findByAttendant(String attendant, int page) {
        return repository.findByAttendant(attendant, PageRequest.of(page, SIZE_PAGE))
                .map(entities -> mapper.toCertificates(entities.getContent()));
    }

    @Override
    public Optional<List<Certificate>> findByTownship(String township, int page) {
        return repository.findByTownship(township, PageRequest.of(page, SIZE_PAGE))
                .map(entities -> mapper.toCertificates(entities.getContent()));
    }

    @Override
    public Optional<List<Certificate>> findByInstitution(String institution, int page) {
        return repository.findByInstitution(institution, PageRequest.of(page, SIZE_PAGE))
                .map(entities -> mapper.toCertificates(entities.getContent()));
    }
}
