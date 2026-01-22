package com.radium.client.modules.setting;

import com.radium.client.gui.settings.Setting;

public class BooleanSetting extends Setting<Boolean> {

    public BooleanSetting(String name, boolean defaultValue) {
        super(name, defaultValue);
    }

    public boolean getValue() {
        return super.getValue();
    }

    public void setValue(boolean value) {
        super.setValue(value);
    }
}
