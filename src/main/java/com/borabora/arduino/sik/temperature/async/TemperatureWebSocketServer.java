package com.borabora.arduino.sik.temperature.async;

import com.borabora.arduino.sik.temperature.TemperatureDAO;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by mrbueno on 3/30/14.
 */

@ServerEndpoint(value = "/temperature2")
public class TemperatureWebSocketServer {


    private static final Logger LOGGER = Logger.getLogger(TemperatureWebSocketServer.class.getName());
    private String temperature = "23.1";
    private TemperatureDAO temperatureDAO;

    @OnOpen
    public void onOpen(Session session) {
        LOGGER.log(Level.INFO, "New connection with client: {0}",
                session.getId());
        try {

            session.getBasicRemote().sendObject(temperature);
        } catch (IOException e) {
            LOGGER.log(Level.INFO, e.getMessage());
        } catch (EncodeException e) {
            LOGGER.log(Level.INFO, e.getMessage());
        }
    }

    @OnMessage
    public String onMessage(String message, Session session) {
        LOGGER.log(Level.INFO, "New message from Client [{0}]: {1}",
                new Object[]{session.getId(), message});
        temperature = getTemperatureDAO().getTemperature("C");
        return temperature;
    }

    @OnClose
    public void onClose(Session session) {
        LOGGER.log(Level.INFO, "Close connection for client: {0}",
                session.getId());
    }

    @OnError
    public void onError(Throwable exception, Session session) {
        LOGGER.log(Level.INFO, "Error for client: {0}", session.getId());
    }

    public TemperatureDAO getTemperatureDAO() {
        if (temperatureDAO == null) {
            ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
            temperatureDAO = (TemperatureDAO) ctx.getBean("temperatureDAO");
        }
        return temperatureDAO;
    }

    public void setTemperatureDAO(TemperatureDAO temperatureDAO) {
        this.temperatureDAO = temperatureDAO;
    }
}
