package eu.fiestaiot.portal.testbed.service.dto;

import javax.validation.constraints.NotNull;



public class RegisterTestbedResourceWithTextDTO {

    @NotNull
    private String annotatedResourceDescription;
    @NotNull
    private String contentType;

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public RegisterTestbedResourceWithTextDTO(){
    }

    public RegisterTestbedResourceWithTextDTO(String annotatedResourceDescription, String contentType) {
        this.annotatedResourceDescription = annotatedResourceDescription;
        this.contentType = contentType;
    }

    
    
    public String getAnnotatedResourceDescription() {
        return annotatedResourceDescription;
    }

    public void setAnnotatedResourceDescription(String annotatedResourceDescription) {
        this.annotatedResourceDescription = annotatedResourceDescription;
    }

   
}
