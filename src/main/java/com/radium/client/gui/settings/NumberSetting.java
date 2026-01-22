package com.radium.client.gui.settings;

public class NumberSetting {
    private final String name;
    private double value;
    private final double min;
    private final double max;
    private final double increment;

    public NumberSetting(String name, double defaultValue, double min, double max, double increment) {
        this.name = name;
        this.value = defaultValue;
        this.min = min;
        this.max = max;
        this.increment = increment;
    }

    public String getName() { return name; }
    public double getValue() { return value; }
    public void setValue(double value) {
        if (value < min) this.value = min;
        else if (value > max) this.value = max;
        else this.value = value;
    }

    public double getMin() { return min; }
    public double getMax() { return max; }
    public double getIncrement() { return increment; }
}
