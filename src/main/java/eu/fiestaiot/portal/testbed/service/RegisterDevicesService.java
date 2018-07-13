package eu.fiestaiot.portal.testbed.service;

import eu.fiestaiot.portal.testbed.domain.RegisterDevices;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * Service Interface for managing RegisterDevices.
 */
public interface RegisterDevicesService {

    /**
     * Save a registerDevices.
     *
     * @param registerDevices the entity to save
     * @return the persisted entity
     */
    RegisterDevices save(RegisterDevices registerDevices);

    /**
     *  Get all the registerDevices.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<RegisterDevices> findAll(Pageable pageable);

    /**
     *  Get the "id" registerDevices.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    RegisterDevices findOne(Long id);

    /**
     *  Delete the "id" registerDevices.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);

    public Page<RegisterDevices> findByUserID(Pageable pageable, String userID);
}
