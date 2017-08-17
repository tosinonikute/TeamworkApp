package com.teamworkapp.data.model.task;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Tosin Onikute.
 */

public class Recurring implements java.io.Serializable {

    private String frequency;
    private Integer sequenceId;
    private String endOn;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public Integer getSequenceId() {
        return sequenceId;
    }

    public void setSequenceId(Integer sequenceId) {
        this.sequenceId = sequenceId;
    }

    public String getEndOn() {
        return endOn;
    }

    public void setEndOn(String endOn) {
        this.endOn = endOn;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
