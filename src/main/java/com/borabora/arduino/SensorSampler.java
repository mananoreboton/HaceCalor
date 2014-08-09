package com.borabora.arduino;

import java.util.Map;

/**
 * Created by mrbueno on 3/29/14.
 */
public interface SensorSampler {
    public void sample();
    public Map getData();
}
