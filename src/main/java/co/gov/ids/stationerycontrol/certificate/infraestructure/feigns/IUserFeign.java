package co.gov.ids.stationerycontrol.certificate.infraestructure.feigns;

import org.springframework.http.ResponseEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Interface that represents the feign client of Users.
 *
 * @author Sergio Rodriguez
 * @version 0.0.1
 * @since 2020
 */
@FeignClient(name = "STATIONERYCONTROL-USER-API")
public interface IUserFeign {

    /**
     * Function to find an User by identification card number.
     *
     * @param identification number of the document that identified the user.
     * @return status code of resource.
     */
    @GetMapping("/api/user/{identification}")
    public ResponseEntity findByIdentification(@PathVariable("identification") String identification);

}
