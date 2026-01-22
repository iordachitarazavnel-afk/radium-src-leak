package com.radium.client.gui.settings;

import com.radium.client.modules.Module;

public class BooleanSetting {

    private final String name;
    private boolean enabled;

    private final Module parent;

    public BooleanSetting(String name, boolean defaultValue) {
        this.name = name;
        this.enabled = defaultValue;
        this.parent = null;
    }

    public BooleanSetting(Module parent, String name, boolean defaultValue) {
        this.name = name;
        this.enabled = defaultValue;
        this.parent = parent;
    }

    public String getName() {
        return name;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Module getParent() {
        return parent;
    }
}
