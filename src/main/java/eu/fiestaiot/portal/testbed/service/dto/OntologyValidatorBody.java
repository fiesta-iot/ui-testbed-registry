package eu.fiestaiot.portal.testbed.service.dto;

public class OntologyValidatorBody {

    private String annotatedObservation;
    private String annotatedResourceDescription;
    private String contentType;
    
    public OntologyValidatorBody() {

    }

    public OntologyValidatorBody(String annotatedObservation, String annotatedResourceDescription, String contentType) {
        this.annotatedObservation = annotatedObservation;
        this.annotatedResourceDescription = annotatedResourceDescription;
        this.contentType = contentType;
    }
    

    public String getAnnotatedObservation() {
        return annotatedObservation;
    }

    public void setAnnotatedObservation(String annotatedObservation) {
        this.annotatedObservation = annotatedObservation;
    }

    public String getAnnotatedResourceDescription() {
        return annotatedResourceDescription;
    }

    public void setAnnotatedResourceDescription(
            String annotatedResourceDescription) {
        this.annotatedResourceDescription = annotatedResourceDescription;
    }
    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }


}
