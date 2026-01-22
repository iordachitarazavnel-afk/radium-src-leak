package com.radium.client.modules.setting;

public class NumberSetting extends Setting {
    private double value;
    private final double min, max, step;

    public NumberSetting(String name, double min, double max, double defaultValue, double step) {
        super(name);
        this.min = min;
        this.max = max;
        this.step = step;
        this.value = defaultValue;
    }

    public double getValue() {
        return value;
    }

    public int getIntValue() {
        return (int) value;
    }

    public void setValue(double value) {
        if (value < min) this.value = min;
        else if (value > max) this.value = max;
        else this.value = value;
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
