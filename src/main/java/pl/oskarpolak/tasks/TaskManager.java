package pl.oskarpolak.tasks;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;

/**
 * Created by OskarPraca on 2017-02-28.
 */
@Component
@EnableScheduling
public class TaskManager {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(fixedRate = 1000, initialDelay = 500)
     public void textToConsole() {
        // System.out.println("Witaj świecie! " + dateFormat.format(new Date()));
     }



}
