package com.radium.client.gui.setting;

import com.radium.client.gui.settings;

public class NumberSetting extends Setting<Double> {

    private final double min, max, step;

    public NumberSetting(String name, double min, double max, double defaultValue, double step) {
        super(name, defaultValue);
        this.min = min;
        this.max = max;
        this.step = step;
    }

    @Override
    public Double getValue() {
        return super.getValue();
    }

    public int getIntValue() {
        return getValue().intValue();
    }

    @Override
    public void setValue(Double value) {
        if (value < min) value = min;
        else if (value > max) value = max;
        super.setValue(value);
    }

    public double getMin() {
        return min;
    }

    public double getMax() {
        return max;
    }

    public double getStep() {
        return step;
    }
}
