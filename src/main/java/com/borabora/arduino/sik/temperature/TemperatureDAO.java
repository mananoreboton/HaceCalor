package com.borabora.arduino.sik.temperature;

import com.borabora.arduino.SensorSampler;
import com.borabora.arduino.TemperatureSampler;

import java.text.DecimalFormat;
import java.util.Map;

/**
 * Created by mrbueno on 3/29/14.
 */
public class TemperatureDAO {

    private SensorSampler temperatureSampler;

    public String getTemperature(String scale) {
        Map data = temperatureSampler.getData();
        Double grades = null;
        DecimalFormat df = new DecimalFormat("##.#");
        String gradesAsString = "";
        if("C".equals(scale)) {
            Object o = data.get(TemperatureSampler.DEGREES_C);
            if (o != null) {
                grades = (Double) o;
                gradesAsString = df.format(grades);
            }
        }
        else if("F".equals(scale)) {
            Object o = data.get(TemperatureSampler.DEGREES_F);
            if (o != null) {
                grades = (Double) o;
                gradesAsString = df.format(grades);
            }
        }
        return gradesAsString;
    }

    public SensorSampler getTemperatureSampler() {
        return temperatureSampler;
    }

    public void setTemperatureSampler(SensorSampler temperatureSampler) {
        this.temperatureSampler = temperatureSampler;
    }
}
