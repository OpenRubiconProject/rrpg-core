package com.openrubicon.core.api.menu.components;

public class Slider extends Component<Slider> {

    private int value = 0;
    private int min = 0;
    private int max = 100;
    private int granularity = 5;

    public Slider(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public Slider setValue(int value) {
        this.value = value;
        return this;
    }

    public int getMin() {
        return min;
    }

    public Slider setMin(int min) {
        this.min = min;
        return this;
    }

    public int getMax() {
        return max;
    }

    public Slider setMax(int max) {
        this.max = max;
        return this;
    }

    public int getGranularity() {
        return granularity;
    }

    public Slider setGranularity(int granularity) {
        this.granularity = granularity;
        return this;
    }
}
