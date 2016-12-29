
package com.orange.afis.vo.shine;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "id",
    "serviceMandatorKey",
    "serviceKey",
    "displayName",
    "description",
    "activation",
    "serviceAccountId",
    "serviceIdentifier",
    "serviceStatusKey"
})
public class CustomizedService {

    @JsonProperty("id")
    private String id;
    @JsonProperty("serviceMandatorKey")
    private String serviceMandatorKey;
    @JsonProperty("serviceKey")
    private String serviceKey;
    @JsonProperty("displayName")
    private String displayName;
    @JsonProperty("description")
    private String description;
    @JsonProperty("activation")
    private String activation;
    @JsonProperty("serviceAccountId")
    private String serviceAccountId;
    @JsonProperty("serviceIdentifier")
    private String serviceIdentifier;
    @JsonProperty("serviceStatusKey")
    private String serviceStatusKey;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("serviceMandatorKey")
    public String getServiceMandatorKey() {
        return serviceMandatorKey;
    }

    @JsonProperty("serviceMandatorKey")
    public void setServiceMandatorKey(String serviceMandatorKey) {
        this.serviceMandatorKey = serviceMandatorKey;
    }

    @JsonProperty("serviceKey")
    public String getServiceKey() {
        return serviceKey;
    }

    @JsonProperty("serviceKey")
    public void setServiceKey(String serviceKey) {
        this.serviceKey = serviceKey;
    }

    @JsonProperty("displayName")
    public String getDisplayName() {
        return displayName;
    }

    @JsonProperty("displayName")
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    @JsonProperty("activation")
    public String getActivation() {
        return activation;
    }

    @JsonProperty("activation")
    public void setActivation(String activation) {
        this.activation = activation;
    }

    @JsonProperty("serviceAccountId")
    public String getServiceAccountId() {
        return serviceAccountId;
    }

    @JsonProperty("serviceAccountId")
    public void setServiceAccountId(String serviceAccountId) {
        this.serviceAccountId = serviceAccountId;
    }

    @JsonProperty("serviceIdentifier")
    public String getServiceIdentifier() {
        return serviceIdentifier;
    }

    @JsonProperty("serviceIdentifier")
    public void setServiceIdentifier(String serviceIdentifier) {
        this.serviceIdentifier = serviceIdentifier;
    }

    @JsonProperty("serviceStatusKey")
    public String getServiceStatusKey() {
        return serviceStatusKey;
    }

    @JsonProperty("serviceStatusKey")
    public void setServiceStatusKey(String serviceStatusKey) {
        this.serviceStatusKey = serviceStatusKey;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
