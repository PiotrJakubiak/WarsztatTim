package pl.edu.wat.tim.webstore.jms;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.util.ErrorHandler;



@Service
public class ListenerErrorHandler implements ErrorHandler {

    private Logger logger = Logger.getLogger(ListenerErrorHandler.class.getName());

    @Override
    public void handleError(Throwable t) {
        logger.error("Error in listener", t);
    }
}
