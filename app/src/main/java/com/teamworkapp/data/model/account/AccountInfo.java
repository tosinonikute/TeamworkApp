package com.teamworkapp.data.model.account;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Tosin Onikute.
 */

public class AccountInfo {

    private String STATUS;
    private Account account;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public String getSTATUS() {
        return STATUS;
    }

    public void setSTATUS(String STATUS) {
        this.STATUS = STATUS;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }
}
