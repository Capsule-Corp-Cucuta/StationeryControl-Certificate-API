package co.gov.ids.stationerycontrol.certificate.persistence.mapper;

import java.util.List;
import org.mapstruct.*;
import co.gov.ids.stationerycontrol.certificate.domain.dto.Certificate;
import co.gov.ids.stationerycontrol.certificate.persistence.entities.CertificateEntity;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CertificateMapper {

    @Mappings({
            @Mapping(source = "number", target = "number"),
            @Mapping(source = "verificationCode", target = "verificationCode"),
            @Mapping(source = "department", target = "department"),
            @Mapping(source = "township", target = "township"),
            @Mapping(source = "institution", target = "institution"),
            @Mapping(source = "type", target = "type"),
            @Mapping(source = "state", target = "state"),
            @Mapping(source = "stateDate", target = "stateDate"),
            @Mapping(source = "stateRUAF", target = "stateRUAF"),
            @Mapping(source = "stateDateRUAF", target = "stateDateRUAF"),
            @Mapping(source = "attendant", target = "attendant"),
            @Mapping(source = "attachment", target = "attachment"),
    })
    Certificate toCertificate(CertificateEntity entity);

    List<Certificate> toCertificates(List<CertificateEntity> entities);

    @InheritInverseConfiguration
    CertificateEntity toEntity(Certificate certificate);

    List<CertificateEntity> toEntities(List<Certificate> certificates);

}
