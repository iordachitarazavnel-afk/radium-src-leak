package com.radium.client.modules.setting;

import com.radium.client.utils.EncryptedString;

public final class BooleanSetting extends Setting<Boolean> {

    public BooleanSetting(EncryptedString name, boolean defaultValue) {
        super(name, defaultValue);
    }

    public boolean getValue() {
        return super.getValue();
    }

    public void setValue(boolean value) {
        super.setValue(value);
    }

    public BooleanSetting toggle() {
        setValue(!getValue());
        return this;
    }
}
