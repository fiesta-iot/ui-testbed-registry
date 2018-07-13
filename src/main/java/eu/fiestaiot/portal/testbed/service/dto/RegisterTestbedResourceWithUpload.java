package eu.fiestaiot.portal.testbed.service.dto;

import javax.persistence.Column;
import javax.persistence.Lob;
import javax.validation.constraints.NotNull;

import eu.fiestaiot.portal.testbed.domain.RegisterTestbeds;
import javax.validation.constraints.Size;

public class RegisterTestbedResourceWithUpload {

    @NotNull
    @Size(min = 10, max = 16777216) // medium text ~ 16mb ~ 16777216 bytes
    @Lob
    private byte[] uploadContent;

    @NotNull
    private String contentType;

    public RegisterTestbedResourceWithUpload() {

    }

    public RegisterTestbedResourceWithUpload(byte[] uploadContent, String contentType) {
        this.uploadContent = uploadContent;
        this.contentType = contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getContentType() {
        return contentType;
    }

    public byte[] getUploadContent() {
        return uploadContent;
    }

    public void setUploadContent(byte[] uploadContent) {
        this.uploadContent = uploadContent;
    }

}
