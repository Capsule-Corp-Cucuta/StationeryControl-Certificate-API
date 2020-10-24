package co.gov.ids.stationerycontrol.certificate.web.feign;

import org.springframework.http.ResponseEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import co.gov.ids.stationerycontrol.certificate.domain.dto.User;

@FeignClient(name = "STATIONERYCONTROL-USER-API")
public interface IUserFeign {

    @GetMapping("/api/user/{identification}")
    public ResponseEntity<User> findByIdentification(@PathVariable("identification") String identification);

}
