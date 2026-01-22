package com.radium.client.gui.settings;

public class NumberSetting extends Setting<Double> {

    private double value;
    private final double min;
    private final double max;
    private final double increment;

    public NumberSetting(String name, double defaultValue, double min, double max, double increment) {
        super(name);
        this.value = defaultValue;
        this.min = min;
        this.max = max;
        this.increment = increment;
    }

    @Override
    public Double getValue() {
        return value;
    }

    @Override
    public void setValue(Double value) {
        if (value < min) value = min;
        if (value > max) value = max;
        this.value = value;
    }

    public double getDoubleValue() {
        return value;
    }

    public void setDoubleValue(double value) {
        setValue(value);
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
