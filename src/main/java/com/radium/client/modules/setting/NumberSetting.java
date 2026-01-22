package com.radium.client.modules.setting;

import com.radium.client.utils.EncryptedString;

public final class NumberSetting extends Setting<Number> {
    private final double min;
    private final double max;
    private final double increment;

    public NumberSetting(EncryptedString name, double min, double max, double defaultValue, double increment) {
        super(name, defaultValue);
        this.min = min;
        this.max = max;
        this.increment = increment;
    }

    public double getDoubleValue() {
        return getValue().doubleValue();
    }

    public int getIntValue() {
        return (int) getValue().doubleValue();
    }

    public void setValue(Number value) {
        double v = value.doubleValue();
        if (v < min) v = min;
        if (v > max) v = max;
        super.setValue(v);
    }

    public double getMin() {
        return min;
    }

    public double getMax() {
        return max;
    }

    public double getIncrement() {
        return increment;
    }
}
