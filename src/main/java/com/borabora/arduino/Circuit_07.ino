/*
SparkFun Inventor's Kit
Example sketch 07

TEMPERATURE SENSOR
  
  The TMP36 is an easy-to-use temperature sensor that outputs
  a voltage that's proportional to the ambient temperature.

  More information on the sensor is available in the datasheet:
  http://dlnmh9ip6v2uc.cloudfront.net/datasheets/Sensors/Temp/TMP35_36_37.pdf
  
Hardware connections:

  Be careful when installing the temperature sensor, as it is
  almost identical to the transistors! The one you want has 
  a triangle logo and "TMP" in very tiny letters. The
  ones you DON'T want will have "222" on them.

  When looking at the flat side of the temperature sensor
  with the pins down, from left to right the pins are:
  5V, SIGNAL, and GND.
  
  Connect the 5V pin to 5 Volts (5V).
  Connect the SIGNAL pin to ANALOG pin 0.
  Connect the GND pin to ground (GND).

Version 2.0 6/2012 MDG
*/

// We'll use analog input 0 to measure the temperature sensor's
// signal pin.

const int temperaturePin = 0;

void setup()
{
  Serial.begin(9600);
}

void loop()
{
  Serial.print(analogRead(temperaturePin)); 
  delay(2000); // repeat once per second (change as you wish!)
}
