package eu.fiestaiot.portal.testbed.domain;


import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A RegisterDevices.
 */
@Entity
@Table(name = "register_devices")
public class RegisterDevices implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "service_response")
    private String serviceResponse;

    @Lob
    @Size(min = 10, max = 1000000)
    @Column(name = "upload_content")
    private byte[] uploadContent;

    @Column(name = "upload_content_content_type")
    private String uploadContentContentType;

    @Column(name = "user_id")
    private String userID;

    @Column(name = "register_id")
    private String registerID;

    @Column(name = "annotated_resource_description")
    private String annotatedResourceDescription;

    //@ManyToOne
    //@NotNull
    //private RegisterTestbeds registerTestbeds;
    
    @Column(name = "register_testbeds_id")
    private Long testbedId;
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getServiceResponse() {
        return serviceResponse;
    }

    public RegisterDevices serviceResponse(String serviceResponse) {
        this.serviceResponse = serviceResponse;
        return this;
    }

    public void setServiceResponse(String serviceResponse) {
        this.serviceResponse = serviceResponse;
    }

    public byte[] getUploadContent() {
        return uploadContent;
    }

    public RegisterDevices uploadContent(byte[] uploadContent) {
        this.uploadContent = uploadContent;
        return this;
    }

    public void setUploadContent(byte[] uploadContent) {
        this.uploadContent = uploadContent;
    }

    public String getUploadContentContentType() {
        return uploadContentContentType;
    }

    public RegisterDevices uploadContentContentType(String uploadContentContentType) {
        this.uploadContentContentType = uploadContentContentType;
        return this;
    }

    public void setUploadContentContentType(String uploadContentContentType) {
        this.uploadContentContentType = uploadContentContentType;
    }

    public String getUserID() {
        return userID;
    }

    public RegisterDevices userID(String userID) {
        this.userID = userID;
        return this;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getRegisterID() {
        return registerID;
    }

    public RegisterDevices registerID(String registerID) {
        this.registerID = registerID;
        return this;
    }

    public void setRegisterID(String registerID) {
        this.registerID = registerID;
    }

    public String getAnnotatedResourceDescription() {
        return annotatedResourceDescription;
    }

    public RegisterDevices annotatedResourceDescription(String annotatedResourceDescription) {
        this.annotatedResourceDescription = annotatedResourceDescription;
        return this;
    }

    public void setAnnotatedResourceDescription(String annotatedResourceDescription) {
        this.annotatedResourceDescription = annotatedResourceDescription;
    }

//    public RegisterTestbeds getRegisterTestbeds() {
//        return registerTestbeds;
//    }
//
//    public RegisterDevices registerTestbeds(RegisterTestbeds registerTestbeds) {
//        this.registerTestbeds = registerTestbeds;
//        return this;
//    }
//
//    public void setRegisterTestbeds(RegisterTestbeds registerTestbeds) {
//        this.registerTestbeds = registerTestbeds;
//    }

    public Long getTestbedId() {
        return testbedId;
    }

    public void setTestbedId(Long testbedId) {
        this.testbedId = testbedId;
    }

    
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RegisterDevices registerDevices = (RegisterDevices) o;
        if (registerDevices.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, registerDevices.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "RegisterDevices{" +
            "id=" + id +
            ", serviceResponse='" + serviceResponse + "'" +
            ", uploadContent='" + uploadContent + "'" +
            ", uploadContentContentType='" + uploadContentContentType + "'" +
            ", userID='" + userID + "'" +
            ", registerID='" + registerID + "'" +
            ", annotatedResourceDescription='" + annotatedResourceDescription + "'" +
            '}';
    }
}
