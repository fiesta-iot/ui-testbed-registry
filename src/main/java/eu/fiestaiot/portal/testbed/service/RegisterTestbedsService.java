package eu.fiestaiot.portal.testbed.service;

import eu.fiestaiot.portal.testbed.domain.RegisterTestbeds;
import eu.fiestaiot.portal.testbed.service.dto.GetAllTestbedsByUserIDDTO;
import eu.fiestaiot.portal.testbed.service.dto.GetAllTestbedsRegisterByRegisterIDListDTO;
import eu.fiestaiot.portal.testbed.service.dto.GetTestbedByIriAndNameDTO;
import eu.fiestaiot.portal.testbed.service.dto.GetTestbedsByRegisterIDDTO;
import eu.fiestaiot.portal.testbed.service.dto.TestbedRegisterInputDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Service Interface for managing RegisterTestbeds.
 */
public interface RegisterTestbedsService {

    /**
     * Save a registerTestbeds.
     *
     * @param registerTestbeds the entity to save
     * @return the persisted entity
     */
    RegisterTestbeds save(RegisterTestbeds registerTestbeds);

    /**
     *  Get all the registerTestbeds.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<RegisterTestbeds> findAll(Pageable pageable);

    /**
     *  Get the "id" registerTestbeds.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    RegisterTestbeds findOne(Long id);

    /**
     *  Delete the "id" registerTestbeds.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);

	RegisterTestbeds findByRegisterID(GetTestbedsByRegisterIDDTO input);

	RegisterTestbeds findByIriAndName(GetTestbedByIriAndNameDTO input);

	Page<RegisterTestbeds> findByUserID(Pageable pageable,
			GetAllTestbedsByUserIDDTO input);

	List<RegisterTestbeds> getAllTestbedsRegisterByRegisterIDList(GetAllTestbedsRegisterByRegisterIDListDTO input);
}
