package co.gov.ids.stationerycontrol.certificate.application.services;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * Interface that represents the use cases of attachments from Certificates.
 *
 * @author Sergio Rodriguez
 * @version 0.0.1
 * @since 2020
 */
public interface IAttachmentService {

    /**
     * Function to save an Attachment of a Certificate.
     *
     * @param number  number to identify a Certificate.
     * @param file    Attachment to will be stored.
     * @param builder Builder of URI to save Attachment.
     */
    public void saveAttachment(int number, MultipartFile file, UriComponentsBuilder builder);

    /**
     * Function to get an Attachment of a Certificate identified by number.
     *
     * @param number number to identify a Certificate.
     * @return Attachment in a byte array.
     */
    public byte[] getAttachment(int number);

}
