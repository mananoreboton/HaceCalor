package com.borabora.arduino;

/**
 * Created by mrbueno on 3/29/14.
 */

import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TemperatureSampler implements SensorSampler {

    public static final String DEGREES_F = "voltage";
    public static final String VOLTAGE = "degreesC";
    public static final String DEGREES_C = "degreesF";

    private SerialPort serialPort;
    private Map data = new HashMap();
    private Logger logger = Logger.getLogger(TemperatureSampler.class .getName());

    public void sample() {
        //for Windows
        // serialPort = new SerialPort("COM1");
        //for Linux
        serialPort = new SerialPort("/dev/ttyACM0");
        try {
            serialPort.openPort();//Open port
            serialPort.setParams(9600, 8, 1, 0);//Set params
            int mask = SerialPort.MASK_RXCHAR + SerialPort.MASK_CTS + SerialPort.MASK_DSR;//Prepare mask
            serialPort.setEventsMask(mask);//Set mask
            SerialPortReader serialPortReader = new SerialPortReader();
            serialPort.addEventListener(serialPortReader);//Add SerialPortEventListener
        } catch (SerialPortException ex) {
            logger.log(Level.INFO, ex.getMessage());
        }

    }

    @Override
    public Map getData() {
        return data;
    }


    class SerialPortReader implements SerialPortEventListener {

        private final int byteCount = 3;

        @Override
        public void serialEvent(SerialPortEvent event) {
            if (event.isRXCHAR()) {//If data is available
                if (event.getEventValue() == byteCount) {//Check bytes count in the input buffer
                    //Read data, if 10 bytes available
                    try {
                        String valueAsString = serialPort.readString(byteCount);
                        double voltage = getVoltage(valueAsString);
                        double degreesC = (voltage - 0.5) * 100.0;
                        double degreesF = degreesC * (9.0 / 5.0) + 32.0;
                        data.put(VOLTAGE, voltage);
                        data.put(DEGREES_C, degreesC);
                        data.put(DEGREES_F, degreesF);
                        logger.log(Level.INFO,"Voltage = " + voltage);
                        logger.log(Level.INFO, degreesC + " °C");
                        logger.log(Level.INFO, degreesF + " °F");
                        logger.log(Level.INFO, "----------------------------------------------------");

                    } catch (SerialPortException ex) {
                        logger.log(Level.INFO, ex.getMessage());
                    }
                }
                try {
                    serialPort.purgePort(SerialPort.PURGE_RXCLEAR);
                } catch (SerialPortException ex) {
                    logger.log(Level.INFO, ex.getMessage());
                }


            } else if (event.isCTS()) {//If CTS line has changed state
                if (event.getEventValue() == 1) {//If line is ON
                    System.out.println("CTS - ON");
                } else {
                    System.out.println("CTS - OFF");
                }
            } else if (event.isDSR()) {///If DSR line has changed state
                if (event.getEventValue() == 1) {//If line is ON
                    System.out.println("DSR - ON");
                } else {
                    System.out.println("DSR - OFF");
                }
            }
        }

        double getVoltage(String valueAsString) {
            int i = Integer.parseInt(valueAsString);
            //System.out.println("i = " + i);
            return i * 0.004882814;

        }

    }


}