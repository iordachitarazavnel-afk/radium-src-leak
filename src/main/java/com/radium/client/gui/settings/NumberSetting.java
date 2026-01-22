package com.radium.client.modules.setting;

public class NumberSetting extends Setting<Double> {
    private final double min, max, increment;

    public NumberSetting(String name, double defaultValue, double min, double max, double increment) {
        super(name, defaultValue);
        this.min = min;
        this.max = max;
        this.increment = increment;
    }

    public double getMin() { return min; }
    public double getMax() { return max; }
    public double getIncrement() { return increment; }

    public void setValue(double value) {
        if (value < min) value = min;
        if (value > max) value = max;
        this.value = value;
    }

    public double getValue() {
        return value;
    }
}
