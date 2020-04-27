package co.gov.ids.stationerycontrol.certificate.application.services;

import java.io.File;
import java.util.Date;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.transaction.annotation.Transactional;
import co.gov.ids.stationerycontrol.certificate.application.exceptions.FileException;
import co.gov.ids.stationerycontrol.certificate.application.exceptions.NotFoundException;
import co.gov.ids.stationerycontrol.certificate.application.exceptions.BadRequestException;
import co.gov.ids.stationerycontrol.certificate.framework.persistence.entities.CertificateEntity;
import co.gov.ids.stationerycontrol.certificate.framework.persistence.repositories.ICertificateRepository;

/**
 * Class that implements ICertificateService.
 *
 * @author Sergio Rodriguez
 * @version 0.0.1
 * @since 2020
 */
@Service
@Transactional(readOnly = true)
public class AttachmentServiceImplementation implements IAttachmentService {

    //private final String CERTIFICATES_ATTACHMENTS_PATH = "/home/spring/";
    private final String CERTIFICATES_ATTACHMENTS_PATH = "D:\\Workspace\\tesis\\data\\";

    private ICertificateRepository repository;

    public AttachmentServiceImplementation(ICertificateRepository repository) {
        this.repository = repository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void saveAttachment(int number, MultipartFile file, UriComponentsBuilder builder) {
        if (number <= 0) {
            throw new BadRequestException("Number is not valid");
        }
        CertificateEntity entity = repository.findByNumber(number); // Consult the certificate by number
        if (entity == null) {
            throw new NotFoundException("Not found");
        }
        if (file.isEmpty()) {    // Verify if the file is not empty
            throw new FileException("Failed to write the file in server");
        }
        if (entity.getAttachment() != null && !entity.getAttachment().isEmpty()) {   // Verify if the certificate has an older attachment
            String fileName = entity.getAttachment(); // Get the name of the old attachment
            File f = Paths.get(fileName).toFile();
            if (f.exists()) {
                f.delete(); // Delete the old attachment
            }
        }
        try {   // Try to write the file in server and update the certificate with the new attachment
            Date date = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
            String dateName = dateFormat.format(date);
            String fileName = entity.getNumber() + "-attachment-" + dateName + "." + file.getContentType().split("/")[1];   // Write the file name

            byte[] bytes = file.getBytes();
            Path path = Paths.get(CERTIFICATES_ATTACHMENTS_PATH + fileName);
            Files.write(path, bytes);   // Save the file in the respective folder and with the respective name

            entity.setAttachment(CERTIFICATES_ATTACHMENTS_PATH + fileName); // Set the file path to the certificate
            repository.save(entity);    // Update the certificate
        } catch (Exception e) {
            e.printStackTrace();
            throw new FileException("Failed to write the file in server");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public byte[] getAttachment(int number) {
        if (number <= 0) {
            throw new BadRequestException("Number is not valid");
        }
        CertificateEntity entity = repository.findByNumber(number);
        if (entity == null || entity.getAttachment() == null || entity.getAttachment().isEmpty()) {
            throw new NotFoundException("Not found");
        }
        try {
            String fileName = entity.getAttachment();
            Path path = Paths.get(fileName);
            File f = path.toFile();
            if (!f.exists()) {
                throw new NotFoundException("Not found");
            }
            return Files.readAllBytes(path);
        } catch (Exception e) {
            e.printStackTrace();
            throw new FileException("Failed to read file");
        }
    }

}
