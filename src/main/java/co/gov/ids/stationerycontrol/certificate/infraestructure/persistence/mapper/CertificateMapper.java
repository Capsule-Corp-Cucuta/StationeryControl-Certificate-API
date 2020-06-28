package co.gov.ids.stationerycontrol.certificate.infraestructure.persistence.mapper;

import co.gov.ids.stationerycontrol.certificate.domain.Certificate;
import co.gov.ids.stationerycontrol.certificate.infraestructure.persistence.entities.CertificateEntity;

/**
 * Class to map Certificate and CertificateEntity.
 *
 * @author Sergio Rodriguez
 * @version 0.0.1
 * @since 2020
 */
public final class CertificateMapper {

    private CertificateMapper() {
    }

    /**
     * Function to map Entity to Domain.
     *
     * @param entity CertificateEntity to be mapped.
     * @return Certificate mapped.
     */
    public static Certificate toDomain(CertificateEntity entity) {
        Certificate domain = new Certificate();
        domain.setNumber(entity.getNumber());
        domain.setVerificationCode(entity.getVerificationCode());
        domain.setDepartment(entity.getDepartment());
        domain.setTownship(entity.getTownship());
        domain.setInstitution(entity.getInstitution());
        domain.setType(entity.getType());
        domain.setState(entity.getState());
        domain.setStateDate(entity.getStateDate());
        domain.setAttendant(entity.getAttendant());
        domain.setAttachment(entity.getAttachment());
        return domain;
    }

    /**
     * Function to map Domain to Entity.
     *
     * @param domain Certificate to be mapped.
     * @return CertificateEntity mapped.
     */
    public static CertificateEntity toEntity(Certificate domain) {
        CertificateEntity entity = new CertificateEntity();
        entity.setNumber(domain.getNumber());
        entity.setVerificationCode(domain.getVerificationCode());
        entity.setDepartment(domain.getDepartment());
        entity.setTownship(domain.getTownship());
        entity.setInstitution(domain.getInstitution());
        entity.setType(domain.getType());
        entity.setState(domain.getState());
        entity.setStateDate(domain.getStateDate());
        entity.setAttendant(domain.getAttendant());
        entity.setAttachment(domain.getAttachment());
        return entity;
    }

}
