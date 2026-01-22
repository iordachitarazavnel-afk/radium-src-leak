package com.radium.client.gui.settings;

public class NumberSetting extends Setting<Double> {

    private double min;
    private double max;
    private double increment;

    public NumberSetting(String name, double min, double max, double defaultValue, double increment) {
        super(name, defaultValue);
        this.min = min;
        this.max = max;
        this.increment = increment;
    }

    public double getMin() { return min; }
    public double getMax() { return max; }
    public double getIncrement() { return increment; }
}


