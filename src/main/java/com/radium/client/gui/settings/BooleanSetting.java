package com.radium.client.gui.settings;

public class BooleanSetting extends Setting<Boolean> {

    private boolean value;

    public BooleanSetting(String name, boolean defaultValue) {
        super(name);
        this.value = defaultValue;
    }

    @Override
    public Boolean getValue() {
        return value;
    }

    @Override
    public void setValue(Boolean value) {
        this.value = value;
    }

    public boolean getBooleanValue() {
        return value;
    }

    public void setBooleanValue(boolean value) {
        this.value = value;
    }
}
