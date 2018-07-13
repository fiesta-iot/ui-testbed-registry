/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.fiestaiot.portal.testbed.service.dto;

import eu.fiestaiot.portal.testbed.domain.RegisterTestbeds;
import javax.validation.constraints.NotNull;

/**
 *
 * @author nguyendanghung
 */
public class RegisterTestbedsDTO  extends RegisterTestbeds {
    
    @NotNull
    private String resourceDescriptionContentType;
    @NotNull
    private String resourceObservationContentType;
    

    public String getResourceDescriptionContentType() {
        return resourceDescriptionContentType;
    }

    public void setResourceDescriptionContentType(String resourceDescriptionContentType) {
        this.resourceDescriptionContentType = resourceDescriptionContentType;
    }

    public String getResourceObservationContentType() {
        return resourceObservationContentType;
    }

    public void setResourceObservationContentType(String resourceObservationContentType) {
        this.resourceObservationContentType = resourceObservationContentType;
    }
    
    
}
