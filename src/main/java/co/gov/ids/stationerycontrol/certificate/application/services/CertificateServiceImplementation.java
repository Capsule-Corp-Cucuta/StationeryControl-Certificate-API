package co.gov.ids.stationerycontrol.certificate.application.services;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

import co.gov.ids.stationerycontrol.certificate.domain.Certificate;
import co.gov.ids.stationerycontrol.certificate.domain.CertificateType;
import co.gov.ids.stationerycontrol.certificate.domain.CertificateState;
import co.gov.ids.stationerycontrol.certificate.application.exceptions.NotFoundException;
import co.gov.ids.stationerycontrol.certificate.application.exceptions.BadRequestException;
import co.gov.ids.stationerycontrol.certificate.infraestructure.persistence.mapper.CertificateMapper;
import co.gov.ids.stationerycontrol.certificate.infraestructure.persistence.entities.CertificateEntity;
import co.gov.ids.stationerycontrol.certificate.infraestructure.persistence.repositories.ICertificateRepository;

/**
 * Class that implements ICertificateService.
 *
 * @author Sergio Rodriguez
 * @version 0.0.1
 * @since 2020
 */
@Service
@Transactional(readOnly = true)
public class CertificateServiceImplementation implements ICertificateService {

    private final int SIZE_PAGE = 20;
    private final ICertificateRepository repository;

    public CertificateServiceImplementation(ICertificateRepository repository) {
        this.repository = repository;
    }

    /**
     * Function for prepare a Certificate to be saved.
     *
     * @param certificate Certificate to will be save.
     * @param date        date of moment of save.
     * @return CertificateEntity to will be persist.
     */
    private CertificateEntity preparedEntityForSave(Certificate certificate, Date date) {
        CertificateEntity temp = CertificateMapper.toEntity(certificate);
        CertificateEntity consulted = repository.findByNumber(certificate.getNumber());
        if (consulted != null) {
            temp.setId(consulted.getId());
        }
        temp.setStateDate(date);
        return temp;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public Certificate create(Certificate certificate) {
        if (certificate == null) {
            throw new BadRequestException("Certificate is null");
        }
        if (certificate.getNumber() <= 0) {
            throw new BadRequestException("Number is not valid");
        }
        Date date = new Date();
        return CertificateMapper.toDomain(repository.save(preparedEntityForSave(certificate, date)));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void createMultiple(List<Certificate> certificates) {
        if (certificates.isEmpty()) {
            throw new BadRequestException("List of certificates are empty");
        }
        Date date = new Date();
        List<CertificateEntity> entities = new ArrayList<>();
        for (Certificate certificate : certificates) {
            if (certificate.getNumber() <= 0) {
                continue;
            }
            entities.add(preparedEntityForSave(certificate, date));
        }
        repository.saveAll(entities);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public Certificate update(int number, Certificate certificate) {
        if (certificate == null) {
            throw new BadRequestException("Certificate is null");
        }
        if (number <= 0 || number != certificate.getNumber()) {
            throw new BadRequestException("Number does not match with Certificate");
        }
        CertificateEntity temp = CertificateMapper.toEntity(certificate);
        CertificateEntity consulted = repository.findByNumber(certificate.getNumber());
        if (consulted == null) {
            throw new NotFoundException("Not found");
        }
        temp.setId(consulted.getId());
        temp.setStateDate(new Date());
        return CertificateMapper.toDomain(repository.save(temp));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void updateMultipleCertificate(int startNumber, int endNumber, String attendant) {
        if (startNumber <= 0 || endNumber <= startNumber) {
            throw new BadRequestException("EndNumber should be bigger than StartNumber " +
                    "and StartNumber should be bigger than zero");
        }
        if (attendant.isEmpty()) {
            throw new BadRequestException("Field attendant could not be empty");
        }
        List<CertificateEntity> entities = repository.findByNumberBetween(startNumber, endNumber);
        for (CertificateEntity entity : entities) {
            entity.setAttendant(attendant);
            if (entity.getState().equals(CertificateState.IDLE)) {
                entity.setState(CertificateState.ASSIGNED);
            }
        }
        repository.saveAll(entities);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public List<Certificate> findAll(int page) {
        if (page < 0) {
            throw new BadRequestException("Field page should be bigger than zero");
        }
        List<Certificate> certificates = new ArrayList<>();
        for (CertificateEntity entity : repository.findAll(PageRequest.of(page, SIZE_PAGE)).getContent()) {
            certificates.add(CertificateMapper.toDomain(entity));
        }
        if (certificates.isEmpty()) {
            throw new NotFoundException("Not found");
        }
        return certificates;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public Certificate findByNumber(int number) {
        if (number <= 0) {
            throw new BadRequestException("Number is not valid");
        }
        CertificateEntity entity = repository.findByNumber(number);
        if (entity == null) {
            throw new NotFoundException("Not found");
        }
        return CertificateMapper.toDomain(entity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public List<Certificate> findByNumberBetween(int startNumber, int endNumber) {
        if (startNumber <= 0 || endNumber <= startNumber) {
            throw new BadRequestException("EndNumber should be bigger than StartNumber " +
                    "and StartNumber should be bigger than zero");
        }
        List<Certificate> certificates = new ArrayList<>();
        for (CertificateEntity entity : repository.findByNumberBetween(startNumber, endNumber)) {
            certificates.add(CertificateMapper.toDomain(entity));
        }
        if (certificates.isEmpty()) {
            throw new NotFoundException("Not found");
        }
        return certificates;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public List<Certificate> findByType(CertificateType type, int page) {
        if (page < 0) {
            throw new BadRequestException("Field page should be bigger than zero");
        }
        List<Certificate> certificates = new ArrayList<>();
        for (CertificateEntity entity : repository.findByType(type, PageRequest.of(page, SIZE_PAGE)).getContent()) {
            certificates.add(CertificateMapper.toDomain(entity));
        }
        if (certificates.isEmpty()) {
            throw new NotFoundException("Not found");
        }
        return certificates;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public List<Certificate> findByTypeAndAttendant(CertificateType type, String attendant, int page) {
        if (page < 0) {
            throw new BadRequestException("Field page should be bigger than zero");
        }
        if (attendant.isEmpty()) {
            throw new BadRequestException("Fields should not be empty");
        }
        List<Certificate> certificates = new ArrayList<>();
        for (CertificateEntity entity : repository.findByTypeAndAttendant(type, attendant,
                PageRequest.of(page, SIZE_PAGE)).getContent()) {
            certificates.add(CertificateMapper.toDomain(entity));
        }
        if (certificates.isEmpty()) {
            throw new NotFoundException("Not found");
        }
        return certificates;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public List<Certificate> findByTypeAndInstitution(CertificateType type, String institution, int page) {
        if (page < 0) {
            throw new BadRequestException("Field page should be bigger than zero");
        }
        if (institution.isEmpty()) {
            throw new BadRequestException("Fields should not be empty");
        }
        List<Certificate> certificates = new ArrayList<>();
        for (CertificateEntity entity : repository.findByTypeAndInstitution(type, institution,
                PageRequest.of(page, SIZE_PAGE)).getContent()) {
            certificates.add(CertificateMapper.toDomain(entity));
        }
        if (certificates.isEmpty()) {
            throw new NotFoundException("Not found");
        }
        return certificates;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public List<Certificate> findByState(CertificateState state, int page) {
        if (page < 0) {
            throw new BadRequestException("Field page should be bigger than zero");
        }
        List<Certificate> certificates = new ArrayList<>();
        for (CertificateEntity entity : repository.findByState(state, PageRequest.of(page, SIZE_PAGE)).getContent()) {
            certificates.add(CertificateMapper.toDomain(entity));
        }
        if (certificates.isEmpty()) {
            throw new NotFoundException("Not found");
        }
        return certificates;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public List<Certificate> findByStateAndAttendant(CertificateState state, String attendant, int page) {
        if (page < 0) {
            throw new BadRequestException("Field page should be bigger than zero");
        }
        if (attendant.isEmpty()) {
            throw new BadRequestException("Fields should not be empty");
        }
        List<Certificate> certificates = new ArrayList<>();
        for (CertificateEntity entity : repository.findByStateAndAttendant(state, attendant,
                PageRequest.of(page, SIZE_PAGE)).getContent()) {
            certificates.add(CertificateMapper.toDomain(entity));
        }
        if (certificates.isEmpty()) {
            throw new NotFoundException("Not found");
        }
        return certificates;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public List<Certificate> findByStateAndInstitution(CertificateState state, String institution, int page) {
        if (page < 0) {
            throw new BadRequestException("Field page should be bigger than zero");
        }
        if (institution.isEmpty()) {
            throw new BadRequestException("Fields should not be empty");
        }
        List<Certificate> certificates = new ArrayList<>();
        for (CertificateEntity entity : repository.findByStateAndInstitution(state, institution,
                PageRequest.of(page, SIZE_PAGE)).getContent()) {
            certificates.add(CertificateMapper.toDomain(entity));
        }
        if (certificates.isEmpty()) {
            throw new NotFoundException("Not found");
        }
        return certificates;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public List<Certificate> findByAttendant(String attendant, int page) {
        if (page < 0) {
            throw new BadRequestException("Field page should be bigger than zero");
        }
        if (attendant.isEmpty()) {
            throw new BadRequestException("Fields should not be empty");
        }
        List<Certificate> certificates = new ArrayList<>();
        for (CertificateEntity entity : repository.findByAttendant(attendant,
                PageRequest.of(page, SIZE_PAGE)).getContent()) {
            certificates.add(CertificateMapper.toDomain(entity));
        }
        if (certificates.isEmpty()) {
            throw new NotFoundException("Not found");
        }
        return certificates;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public List<Certificate> findByTownship(String township, int page) {
        if (page < 0) {
            throw new BadRequestException("Field page should be bigger than zero");
        }
        if (township.isEmpty()) {
            throw new BadRequestException("Township field should not be empty");
        }
        List<Certificate> certificates = new ArrayList<>();
        for (CertificateEntity entity : repository.findByTownship(township,
                PageRequest.of(page, SIZE_PAGE)).getContent()) {
            certificates.add(CertificateMapper.toDomain(entity));
        }
        if (certificates.isEmpty()) {
            throw new NotFoundException("Not found");
        }
        return certificates;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public List<Certificate> findByInstitution(String institution, int page) {
        if (page < 0) {
            throw new BadRequestException("Field page should be bigger than zero");
        }
        if (institution.isEmpty()) {
            throw new BadRequestException("Institution field should not be empty");
        }
        List<Certificate> certificates = new ArrayList<>();
        for (CertificateEntity entity : repository.findByInstitution(institution,
                PageRequest.of(page, SIZE_PAGE)).getContent()) {
            certificates.add(CertificateMapper.toDomain(entity));
        }
        if (certificates.isEmpty()) {
            throw new NotFoundException("Not found");
        }
        return certificates;
    }

}
