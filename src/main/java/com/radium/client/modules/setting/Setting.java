package com.radium.client.modules.setting;

import com.radium.client.utils.EncryptedString;

public abstract class Setting<T> {
    protected final EncryptedString name;
    protected T value;
    protected String description = "";

    public Setting(EncryptedString name, T defaultValue) {
        this.name = name;
        this.value = defaultValue;
    }

    public EncryptedString getName() {
        return name;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public Setting<T> setDescription(String description) {
        this.description = description;
        return this;
    }

    public Setting<T> setDescription(EncryptedString description) {
        this.description = description.toString();
        return this;
    }
}
