package com.radium.client.gui.settings;

public class NumberSetting extends Setting<Double> {

    private final double min;
    private final double max;
    private final double increment;

    public NumberSetting(String name, double defaultValue, double min, double max, double increment) {
        super(name, defaultValue);
        this.min = min;
        this.max = max;
        this.increment = increment;
    }

    @Override
    public void setValue(Double value) {
        if (value < min) value = min;
        if (value > max) value = max;
        super.setValue(value);
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

    public double getNumber() {
        return getValue();
    }

    public void setNumber(double value) {
        setValue(value);
    }
}

