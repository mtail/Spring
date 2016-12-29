
package com.orange.afis.vo.ope;

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
    "developerFirstName",
    "developerLastName",
    "developerId",
    "developerEmail",
    "productName",
    "productId",
    "externalProductId",
    "applicationId",
    "applicationKey",
    "applicationName",
    "event",
    "parameterValues",
    "externalParameterValues",
    "externalAccountId",
    "externalAccountPlatformId",
    "developerNotification"
})
public class OpeRequest {

    @JsonProperty("developerFirstName")
    private String developerFirstName;
    @JsonProperty("developerLastName")
    private String developerLastName;
    @JsonProperty("developerId")
    private String developerId;
    @JsonProperty("developerEmail")
    private String developerEmail;
    @JsonProperty("productName")
    private String productName;
    @JsonProperty("productId")
    private String productId;
    @JsonProperty("externalProductId")
    private String externalProductId;
    @JsonProperty("applicationId")
    private String applicationId;
    @JsonProperty("applicationKey")
    private String applicationKey;
    @JsonProperty("applicationName")
    private String applicationName;
    @JsonProperty("event")
    private String event;
    @JsonProperty("parameterValues")
    private ParameterValues parameterValues;
    @JsonProperty("externalParameterValues")
    private ExternalParameterValues externalParameterValues;
    @JsonProperty("externalAccountId")
    private String externalAccountId;
    @JsonProperty("externalAccountPlatformId")
    private String externalAccountPlatformId;
    @JsonProperty("developerNotification")
    private String developerNotification;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("developerFirstName")
    public String getDeveloperFirstName() {
        return developerFirstName;
    }

    @JsonProperty("developerFirstName")
    public void setDeveloperFirstName(String developerFirstName) {
        this.developerFirstName = developerFirstName;
    }

    @JsonProperty("developerLastName")
    public String getDeveloperLastName() {
        return developerLastName;
    }

    @JsonProperty("developerLastName")
    public void setDeveloperLastName(String developerLastName) {
        this.developerLastName = developerLastName;
    }

    @JsonProperty("developerId")
    public String getDeveloperId() {
        return developerId;
    }

    @JsonProperty("developerId")
    public void setDeveloperId(String developerId) {
        this.developerId = developerId;
    }

    @JsonProperty("developerEmail")
    public String getDeveloperEmail() {
        return developerEmail;
    }

    @JsonProperty("developerEmail")
    public void setDeveloperEmail(String developerEmail) {
        this.developerEmail = developerEmail;
    }

    @JsonProperty("productName")
    public String getProductName() {
        return productName;
    }

    @JsonProperty("productName")
    public void setProductName(String productName) {
        this.productName = productName;
    }

    @JsonProperty("productId")
    public String getProductId() {
        return productId;
    }

    @JsonProperty("productId")
    public void setProductId(String productId) {
        this.productId = productId;
    }

    @JsonProperty("externalProductId")
    public String getExternalProductId() {
        return externalProductId;
    }

    @JsonProperty("externalProductId")
    public void setExternalProductId(String externalProductId) {
        this.externalProductId = externalProductId;
    }

    @JsonProperty("applicationId")
    public String getApplicationId() {
        return applicationId;
    }

    @JsonProperty("applicationId")
    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    @JsonProperty("applicationKey")
    public String getApplicationKey() {
        return applicationKey;
    }

    @JsonProperty("applicationKey")
    public void setApplicationKey(String applicationKey) {
        this.applicationKey = applicationKey;
    }

    @JsonProperty("applicationName")
    public String getApplicationName() {
        return applicationName;
    }

    @JsonProperty("applicationName")
    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    @JsonProperty("event")
    public String getEvent() {
        return event;
    }

    @JsonProperty("event")
    public void setEvent(String event) {
        this.event = event;
    }

    @JsonProperty("parameterValues")
    public ParameterValues getParameterValues() {
        return parameterValues;
    }

    @JsonProperty("parameterValues")
    public void setParameterValues(ParameterValues parameterValues) {
        this.parameterValues = parameterValues;
    }

    @JsonProperty("externalParameterValues")
    public ExternalParameterValues getExternalParameterValues() {
        return externalParameterValues;
    }

    @JsonProperty("externalParameterValues")
    public void setExternalParameterValues(ExternalParameterValues externalParameterValues) {
        this.externalParameterValues = externalParameterValues;
    }

    @JsonProperty("externalAccountId")
    public String getExternalAccountId() {
        return externalAccountId;
    }

    @JsonProperty("externalAccountId")
    public void setExternalAccountId(String externalAccountId) {
        this.externalAccountId = externalAccountId;
    }

    @JsonProperty("externalAccountPlatformId")
    public String getExternalAccountPlatformId() {
        return externalAccountPlatformId;
    }

    @JsonProperty("externalAccountPlatformId")
    public void setExternalAccountPlatformId(String externalAccountPlatformId) {
        this.externalAccountPlatformId = externalAccountPlatformId;
    }

    @JsonProperty("developerNotification")
    public String getDeveloperNotification() {
        return developerNotification;
    }

    @JsonProperty("developerNotification")
    public void setDeveloperNotification(String developerNotification) {
        this.developerNotification = developerNotification;
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
