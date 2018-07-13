/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.fiestaiot.portal.testbed.web.rest.vm;

/**
 *
 * @author hungnguyen
 */
public class ServiceResponse {

    public ServiceResponse() {
        
    }

    public ServiceResponse(String errorMessage) {
        this.errorMessage = errorMessage;
    }
    
    private String errorMessage;

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
    
}
