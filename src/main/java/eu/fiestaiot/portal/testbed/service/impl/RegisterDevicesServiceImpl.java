package eu.fiestaiot.portal.testbed.service.impl;

import eu.fiestaiot.portal.testbed.service.RegisterDevicesService;
import eu.fiestaiot.portal.testbed.domain.RegisterDevices;
import eu.fiestaiot.portal.testbed.repository.RegisterDevicesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

/**
 * Service Implementation for managing RegisterDevices.
 */
@Service
@Transactional
public class RegisterDevicesServiceImpl implements RegisterDevicesService{

    private final Logger log = LoggerFactory.getLogger(RegisterDevicesServiceImpl.class);
    
    @Inject
    private RegisterDevicesRepository registerDevicesRepository;

    /**
     * Save a registerDevices.
     *
     * @param registerDevices the entity to save
     * @return the persisted entity
     */
    public RegisterDevices save(RegisterDevices registerDevices) {
        log.debug("Request to save RegisterDevices : {}", registerDevices);
        RegisterDevices result = registerDevicesRepository.save(registerDevices);
        return result;
    }

    /**
     *  Get all the registerDevices.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public Page<RegisterDevices> findAll(Pageable pageable) {
        log.debug("Request to get all RegisterDevices");
        Page<RegisterDevices> result = registerDevicesRepository.findAll(pageable);
        return result;
    }

    /**
     *  Get one registerDevices by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true) 
    public RegisterDevices findOne(Long id) {
        log.debug("Request to get RegisterDevices : {}", id);
        RegisterDevices registerDevices = registerDevicesRepository.findOne(id);
        return registerDevices;
    }

    /**
     *  Delete the  registerDevices by id.
     *
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete RegisterDevices : {}", id);
        registerDevicesRepository.delete(id);
    }

    @Override
    public Page<RegisterDevices> findByUserID(Pageable pageable, String userID) {
        return registerDevicesRepository.findByUserID(pageable, userID);
    }
}
