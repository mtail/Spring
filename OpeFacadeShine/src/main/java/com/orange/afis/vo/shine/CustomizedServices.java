
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
    "customizedService",
    "xmlns"
})
public class CustomizedServices {

    @JsonProperty("customizedService")
    private CustomizedService customizedService;
    @JsonProperty("xmlns")
    private String xmlns;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("customizedService")
    public CustomizedService getCustomizedService() {
        return customizedService;
    }

    @JsonProperty("customizedService")
    public void setCustomizedService(CustomizedService customizedService) {
        this.customizedService = customizedService;
    }

    @JsonProperty("xmlns")
    public String getXmlns() {
        return xmlns;
    }

    @JsonProperty("xmlns")
    public void setXmlns(String xmlns) {
        this.xmlns = xmlns;
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
