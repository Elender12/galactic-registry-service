package com.galactic.first.registry.monitoring;


import com.movilizer.microservices.commons.models.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Calendar;

@Service
public class Monitoring {

    // LOGGER
    private static final Logger LOGGER = LoggerFactory.getLogger(Monitoring.class);

    @Value("${microservice.name}")
    public String SERVICE;

    // TYPES OF LOGS
    public static final String INFO = "INFO";
    public static final String CRITICAL = "CRITICAL";
    public static final String OK = "OK";
    public static final String WARNING = "Warning";
    public static final String ERROR = "Error";

    // LOG METHOD
    public void log( String severity, String message )
    {

        Log log = new Log(this.getDate(), SERVICE, severity, message, this.getCurrentUsername());

        // ALWAYS LOGS LOCALLY
        LOGGER.info(log.toString());

    }
    // GETS THE CURRENT YEAR, MONTH, DAY, HOUR, MINUTE, SECOND
    private String getDate()
    {
        return Calendar.getInstance().get(Calendar.YEAR) + "/" +
            Calendar.getInstance().get(Calendar.MONTH) + "/" +
            Calendar.getInstance().get(Calendar.DAY_OF_MONTH) + " - " +
            Calendar.getInstance().get(Calendar.HOUR) + ":" +
            Calendar.getInstance().get(Calendar.MINUTE) + ":" +
            Calendar.getInstance().get(Calendar.SECOND);
    }

    // GETS THE CURRENT USERNAME MAKING THE CALL
    private String getCurrentUsername()
    {
        String username;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if( principal instanceof UserDetails )
        {
            username = ((UserDetails)principal).getUsername();
        }
        else
        {
            username = principal.toString();
        }
        return username;
    }
}
