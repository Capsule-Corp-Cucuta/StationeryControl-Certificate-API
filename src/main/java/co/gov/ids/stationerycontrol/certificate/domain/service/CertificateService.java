package co.gov.ids.stationerycontrol.certificate.domain.service;

import java.util.List;
import java.util.Optional;
import java.time.LocalDate;
import org.springframework.stereotype.Service;
import co.gov.ids.stationerycontrol.certificate.domain.dto.Certificate;
import co.gov.ids.stationerycontrol.certificate.domain.dto.CertificateType;
import co.gov.ids.stationerycontrol.certificate.domain.dto.CertificateState;
import co.gov.ids.stationerycontrol.certificate.domain.repository.ICertificateRepository;

@Service
public class CertificateService {

    private final ICertificateRepository repository;

    public CertificateService(ICertificateRepository repository) {
        this.repository = repository;
    }

    public Certificate create(Certificate certificate) {
        if (certificate.getState() == null) {
            certificate.setState(CertificateState.IDLE);
        }
        certificate.setStateDate(LocalDate.now());
        return repository.create(certificate);
    }

    public boolean createMultiple(List<Certificate> certificates) {
        certificates.forEach(certificate -> {
            if (certificate.getState() == null) {
                certificate.setState(CertificateState.IDLE);
            }
            certificate.setStateDate(LocalDate.now());
        });
        return repository.createMultiple(certificates);
    }

    public Certificate update(Certificate certificate, String attendant, String township, String institution) {
        certificate.setStateDate(LocalDate.now());
        certificate.setAttendant(attendant);
        certificate.setTownship(township);
        certificate.setInstitution(institution);
        if (certificate.getState().equals(CertificateState.IDLE)) {
            certificate.setState(CertificateState.ASSIGNED);
        }
        return repository.update(certificate);
    }

    public boolean updateMultipleCertificate(int startNumber, int endNumber, String attendant, String township, String institution) {
        return findByNumberBetween(startNumber, endNumber).map(certificates -> {
            certificates.forEach(certificate -> {
                certificate.setStateDate(LocalDate.now());
                certificate.setAttendant(attendant);
                certificate.setTownship(township);
                certificate.setInstitution(institution);
                if (certificate.getState().equals(CertificateState.IDLE)) {
                    certificate.setState(CertificateState.ASSIGNED);
                }
            });
            return repository.createMultiple(certificates);
        }).orElse(false);
    }

    public Optional<Certificate> findByNumber(int number) {
        return repository.findByNumber(number);
    }

    public Optional<List<Certificate>> findAll(int page) {
        return repository.findAll(page);
    }


    public Optional<List<Certificate>> findByNumberBetween(int startNumber, int endNumber) {
        return repository.findByNumberBetween(startNumber, endNumber);
    }

    public Optional<List<Certificate>> findByType(CertificateType type, int page) {
        return repository.findByType(type, page);
    }

    public Optional<List<Certificate>> findByTypeAndAttendant(CertificateType type, String attendant, int page) {
        return repository.findByTypeAndAttendant(type, attendant, page);
    }

    public Optional<List<Certificate>> findByTypeAndInstitution(CertificateType type, String institution, int page) {
        return repository.findByTypeAndInstitution(type, institution, page);
    }

    public Optional<List<Certificate>> findByState(CertificateState state, int page) {
        return repository.findByState(state, page);
    }

    public Optional<List<Certificate>> findByStateAndAttendant(CertificateState state, String attendant, int page) {
        return repository.findByStateAndAttendant(state, attendant, page);
    }

    public Optional<List<Certificate>> findByStateAndInstitution(CertificateState state, String institution, int page) {
        return repository.findByStateAndInstitution(state, institution, page);
    }

    public Optional<List<Certificate>> findByAttendant(String attendant, int page) {
        return repository.findByAttendant(attendant, page);
    }

    public Optional<List<Certificate>> findByTownship(String township, int page) {
        return repository.findByTownship(township, page);
    }

    public Optional<List<Certificate>> findByInstitution(String institution, int page) {
        return repository.findByInstitution(institution, page);
    }

}
