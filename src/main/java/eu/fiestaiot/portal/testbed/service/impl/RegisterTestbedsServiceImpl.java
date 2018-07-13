package eu.fiestaiot.portal.testbed.service.impl;

import eu.fiestaiot.portal.testbed.repository.RegisterTestbedsRepository;
import eu.fiestaiot.portal.testbed.service.RegisterTestbedsService;
import eu.fiestaiot.portal.testbed.service.dto.GetAllTestbedsByUserIDDTO;
import eu.fiestaiot.portal.testbed.service.dto.GetAllTestbedsRegisterByRegisterIDListDTO;
import eu.fiestaiot.portal.testbed.service.dto.GetTestbedByIriAndNameDTO;
import eu.fiestaiot.portal.testbed.service.dto.GetTestbedsByRegisterIDDTO;
import eu.fiestaiot.portal.testbed.domain.RegisterTestbeds;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

import java.util.List;

/**
 * Service Implementation for managing RegisterTestbeds.
 */
@Service
@Transactional
public class RegisterTestbedsServiceImpl implements RegisterTestbedsService{

    private final Logger log = LoggerFactory.getLogger(RegisterTestbedsServiceImpl.class);
    
    @Inject
    private RegisterTestbedsRepository registerTestbedsRepository;

    /**
     * Save a registerTestbeds.
     *
     * @param registerTestbeds the entity to save
     * @return the persisted entity
     */
    public RegisterTestbeds save(RegisterTestbeds registerTestbeds) {
        log.debug("Request to save RegisterTestbeds : {}", registerTestbeds);
        RegisterTestbeds result = registerTestbedsRepository.save(registerTestbeds);
        return result;
    }

    /**
     *  Get all the registerTestbeds.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public Page<RegisterTestbeds> findAll(Pageable pageable) {
        log.debug("Request to get all RegisterTestbeds");
        Page<RegisterTestbeds> result = registerTestbedsRepository.findAll(pageable);
        return result;
    }

    /**
     *  Get one registerTestbeds by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true) 
    public RegisterTestbeds findOne(Long id) {
        log.debug("Request to get RegisterTestbeds : {}", id);
        RegisterTestbeds registerTestbeds = registerTestbedsRepository.findOne(id);
        return registerTestbeds;
    }

    /**
     *  Delete the  registerTestbeds by id.
     *
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete RegisterTestbeds : {}", id);
        registerTestbedsRepository.delete(id);
    }

	@Override
	public RegisterTestbeds findByRegisterID(GetTestbedsByRegisterIDDTO input) {
		RegisterTestbeds registerTestbeds = registerTestbedsRepository.findByRegisterID(input.getRegisterID());
		
		return registerTestbeds;
	}

	@Override
	public RegisterTestbeds findByIriAndName(GetTestbedByIriAndNameDTO input) {
	    RegisterTestbeds registerTestbeds = registerTestbedsRepository.findByIriAndName(input.getIri(), input.getName());
		return registerTestbeds;
	}

	@Override
	public Page<RegisterTestbeds> findByUserID(Pageable pageable,
			GetAllTestbedsByUserIDDTO input) {
		 Page<RegisterTestbeds> result = registerTestbedsRepository.findByUserID(pageable,input.getUserID());
	     return result;
	}

	@Override
	public List<RegisterTestbeds> getAllTestbedsRegisterByRegisterIDList(GetAllTestbedsRegisterByRegisterIDListDTO input) {
		 List<RegisterTestbeds> result = registerTestbedsRepository.findByRegisterIDIn(input.getRegisterIDList());
	     return result;
	}
}
