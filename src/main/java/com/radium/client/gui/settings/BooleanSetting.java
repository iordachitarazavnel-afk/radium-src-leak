package com.radium.client.modules.setting;

public class BooleanSetting extends Setting<Boolean> {
    public BooleanSetting(String name, boolean defaultValue) {
        super(name, defaultValue);
    }

    public void setValue(boolean value) {
        this.value = value;
    }

    public boolean isEnabled() {
        return value;
    }
}
