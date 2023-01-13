package com.flooringmastery;

import com.flooringmastery.controller.FlooringMasteryController;
import com.flooringmastery.dao.DataPersistenceException;
import com.flooringmastery.service.InvalidOrderNumberException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.scan("com.flooringmastery");
        applicationContext.refresh();
        FlooringMasteryController controller = applicationContext.getBean(FlooringMasteryController.class);
        try {
            controller.run();
        } catch (InvalidOrderNumberException e) {
            e.printStackTrace();
        } catch (DataPersistenceException e) {
            e.printStackTrace();
        }
    }

}
