package eu.fiestaiot.portal.testbed.domain;


import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A RegisterTestbeds.
 */
@Entity
@Table(name = "register_testbeds")
public class RegisterTestbeds implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "get_api_key")
    private String getApiKey;

    @Column(name = "get_last_observations_url")
    private String getLastObservationsURL;

    @Column(name = "get_observations_url")
    private String getObservationsURL;

    @NotNull
    @Column(name = "iri", nullable = false, unique = true)
    private String iri;

    @NotNull
    @Column(name = "latitude", nullable = false)
    private String latitude;

    @NotNull
    @Column(name = "longitude", nullable = false)
    private String longitude;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "push_api_key")
    private String pushApiKey;

    @Column(name = "push_last_observations_url")
    private String pushLastObservationsURL;

    @Column(name = "push_observations_url")
    private String pushObservationsURL;

    @NotNull
    @Column(name = "annotated_observation", nullable = false)
    private String annotatedObservation;

    @NotNull
    @Column(name = "annotated_resource_description", nullable = false)
    private String annotatedResourceDescription;

    @Column(name = "resource_id")
    private String resourceID;

    @Column(name = "resource_type")
    private String resourceType;

    @Column(name = "register_id")
    private String registerID;

    @Column(name = "user_id")
    private String userID;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGetApiKey() {
        return getApiKey;
    }

    public RegisterTestbeds getApiKey(String getApiKey) {
        this.getApiKey = getApiKey;
        return this;
    }

    public void setGetApiKey(String getApiKey) {
        this.getApiKey = getApiKey;
    }

    public String getGetLastObservationsURL() {
        return getLastObservationsURL;
    }

    public RegisterTestbeds getLastObservationsURL(String getLastObservationsURL) {
        this.getLastObservationsURL = getLastObservationsURL;
        return this;
    }

    public void setGetLastObservationsURL(String getLastObservationsURL) {
        this.getLastObservationsURL = getLastObservationsURL;
    }

    public String getGetObservationsURL() {
        return getObservationsURL;
    }

    public RegisterTestbeds getObservationsURL(String getObservationsURL) {
        this.getObservationsURL = getObservationsURL;
        return this;
    }

    public void setGetObservationsURL(String getObservationsURL) {
        this.getObservationsURL = getObservationsURL;
    }

    public String getIri() {
        return iri;
    }

    public RegisterTestbeds iri(String iri) {
        this.iri = iri;
        return this;
    }

    public void setIri(String iri) {
        this.iri = iri;
    }

    public String getLatitude() {
        return latitude;
    }

    public RegisterTestbeds latitude(String latitude) {
        this.latitude = latitude;
        return this;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public RegisterTestbeds longitude(String longitude) {
        this.longitude = longitude;
        return this;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public RegisterTestbeds name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPushApiKey() {
        return pushApiKey;
    }

    public RegisterTestbeds pushApiKey(String pushApiKey) {
        this.pushApiKey = pushApiKey;
        return this;
    }

    public void setPushApiKey(String pushApiKey) {
        this.pushApiKey = pushApiKey;
    }

    public String getPushLastObservationsURL() {
        return pushLastObservationsURL;
    }

    public RegisterTestbeds pushLastObservationsURL(String pushLastObservationsURL) {
        this.pushLastObservationsURL = pushLastObservationsURL;
        return this;
    }

    public void setPushLastObservationsURL(String pushLastObservationsURL) {
        this.pushLastObservationsURL = pushLastObservationsURL;
    }

    public String getPushObservationsURL() {
        return pushObservationsURL;
    }

    public RegisterTestbeds pushObservationsURL(String pushObservationsURL) {
        this.pushObservationsURL = pushObservationsURL;
        return this;
    }

    public void setPushObservationsURL(String pushObservationsURL) {
        this.pushObservationsURL = pushObservationsURL;
    }

    public String getAnnotatedObservation() {
        return annotatedObservation;
    }

    public RegisterTestbeds annotatedObservation(String annotatedObservation) {
        this.annotatedObservation = annotatedObservation;
        return this;
    }

    public void setAnnotatedObservation(String annotatedObservation) {
        this.annotatedObservation = annotatedObservation;
    }

    public String getAnnotatedResourceDescription() {
        return annotatedResourceDescription;
    }

    public RegisterTestbeds annotatedResourceDescription(String annotatedResourceDescription) {
        this.annotatedResourceDescription = annotatedResourceDescription;
        return this;
    }

    public void setAnnotatedResourceDescription(String annotatedResourceDescription) {
        this.annotatedResourceDescription = annotatedResourceDescription;
    }

    public String getResourceID() {
        return resourceID;
    }

    public RegisterTestbeds resourceID(String resourceID) {
        this.resourceID = resourceID;
        return this;
    }

    public void setResourceID(String resourceID) {
        this.resourceID = resourceID;
    }

    public String getResourceType() {
        return resourceType;
    }

    public RegisterTestbeds resourceType(String resourceType) {
        this.resourceType = resourceType;
        return this;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public String getRegisterID() {
        return registerID;
    }

    public RegisterTestbeds registerID(String registerID) {
        this.registerID = registerID;
        return this;
    }

    public void setRegisterID(String registerID) {
        this.registerID = registerID;
    }

    public String getUserID() {
        return userID;
    }

    public RegisterTestbeds userID(String userID) {
        this.userID = userID;
        return this;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RegisterTestbeds registerTestbeds = (RegisterTestbeds) o;
        if (registerTestbeds.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, registerTestbeds.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "RegisterTestbeds{" +
            "id=" + id +
            ", getApiKey='" + getApiKey + "'" +
            ", getLastObservationsURL='" + getLastObservationsURL + "'" +
            ", getObservationsURL='" + getObservationsURL + "'" +
            ", iri='" + iri + "'" +
            ", latitude='" + latitude + "'" +
            ", longitude='" + longitude + "'" +
            ", name='" + name + "'" +
            ", pushApiKey='" + pushApiKey + "'" +
            ", pushLastObservationsURL='" + pushLastObservationsURL + "'" +
            ", pushObservationsURL='" + pushObservationsURL + "'" +
            ", annotatedObservation='" + annotatedObservation + "'" +
            ", annotatedResourceDescription='" + annotatedResourceDescription + "'" +
            ", resourceID='" + resourceID + "'" +
            ", resourceType='" + resourceType + "'" +
            ", registerID='" + registerID + "'" +
            ", userID='" + userID + "'" +
            '}';
    }
}
