package eu.fiestaiot.portal.testbed.repository;

import eu.fiestaiot.portal.testbed.domain.RegisterDevices;

import org.springframework.data.jpa.repository.*;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Spring Data JPA repository for the RegisterDevices entity.
 */
@SuppressWarnings("unused")
public interface RegisterDevicesRepository extends JpaRepository<RegisterDevices,Long> {

    public Page<RegisterDevices> findByUserID(Pageable pageable, String userID);

}
