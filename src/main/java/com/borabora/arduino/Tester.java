package com.borabora.arduino;

/**
 * Created by mrbueno on 3/29/14.
 */
public class Tester {

    public static void main(String[] args) {
        SensorSampler sampler = new TemperatureSampler();
        sampler.sample();
    }
}
