package com.borabora.arduino.sik.temperature;

import com.borabora.arduino.SensorSampler;
import org.springframework.web.HttpRequestHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by mrbueno on 3/30/14.
 */
public class TemperatureServlet implements HttpRequestHandler {

    private TemperatureDAO temperatureDAO;

    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String c = temperatureDAO.getTemperature("C");
        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();
        pw.println("<html>");
        pw.println("<head><title>La Temperatura</title></title>");
        pw.println("<body bgcolor=\"#01DF74\" text=\"#ffffff\">");
        pw.println("<center>");
        pw.println("<p style=\"font-size: 2000%;\">" + c + " °C</p>");
        pw.println("</center>");
        pw.println("</body></html>");
    }


    public TemperatureDAO getTemperatureDAO() {
        return temperatureDAO;
    }

    public void setTemperatureDAO(TemperatureDAO temperatureDAO) {
        this.temperatureDAO = temperatureDAO;
    }
}
