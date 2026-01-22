package com.radium.client.gui.settings;

public class BooleanSetting extends Setting<Boolean> {

    public BooleanSetting(String name, boolean defaultValue) {
        super(name, defaultValue);
    }

    public boolean getBoolean() {
        return getValue();
    }

    public void setBoolean(boolean value) {
        setValue(value);
    }
}
