package co.gov.ids.stationerycontrol.certificate.domain.service;

import java.io.File;
import java.util.Date;
import java.util.Optional;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Value;
import co.gov.ids.stationerycontrol.certificate.domain.dto.Certificate;

@Service
public class AttachmentService {

    private final CertificateService service;

    @Value("${certificate.attachment.path}")
    private String CERTIFICATE_ATTACHMENT_PATH;

    public AttachmentService(CertificateService service) {
        this.service = service;
    }

    public boolean saveAttachment(int number, MultipartFile file, UriComponentsBuilder builder) {
        Optional<Certificate> certificate = service.findByNumber(number); // Consult the certificate by number
        if (certificate.isPresent()) {
            if (file != null && !file.isEmpty()) {    // Verify if the file is not empty
                if (certificate.get().getAttachment() != null && !certificate.get().getAttachment().isEmpty()) {   // Verify if the certificate has an older attachment
                    String fileName = certificate.get().getAttachment(); // Get the name of the old attachment
                    File f = Paths.get(fileName).toFile();
                    if (f.exists()) {
                        f.delete(); // Delete the old attachment
                    }
                }
                try {   // Try to write the file in server and update the certificate with the new attachment
                    Date date = new Date();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
                    String dateName = dateFormat.format(date);
                    String fileName = certificate.get().getNumber() + "-attachment-" + dateName + "." + file.getContentType().split("/")[1];   // Write the file name

                    byte[] bytes = file.getBytes();
                    Path path = Paths.get(CERTIFICATE_ATTACHMENT_PATH + fileName);
                    Files.write(path, bytes);   // Save the file in the respective folder and with the respective name

                    certificate.get().setAttachment(CERTIFICATE_ATTACHMENT_PATH + fileName); // Set the file path to the certificate
                    service.update(    // Update the certificate
                            certificate.get(),
                            certificate.get().getAttendant(),
                            certificate.get().getTownship(),
                            certificate.get().getInstitution()
                    );
                    return true;
                } catch (Exception e) {
                    e.printStackTrace();
                    return false;
                }
            }
        }
        return false;
    }

    public byte[] getAttachment(int number) {
        Optional<Certificate> certificate = service.findByNumber(number);
        if (certificate.isPresent() && certificate.get().getAttachment() != null && !certificate.get().getAttachment().isEmpty()) {
            try {
                String fileName = certificate.get().getAttachment();
                Path path = Paths.get(fileName);
                File f = path.toFile();
                if (!f.exists()) {
                    System.err.println("File not found");
                }
                return Files.readAllBytes(path);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }

}
