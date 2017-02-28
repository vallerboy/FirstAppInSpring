package pl.oskarpolak.tasks;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by OskarPraca on 2017-02-28.
 */

@Component
@EnableScheduling
public class TaskConsole {


    //sekundy - minuty - godziny - dni - miesiace - dni tygodnia
    // */10 - co każde 10          */X - co każde X

    @Scheduled(cron = "0 0 6 * * MON-FRI")
    public void printLog(){

    }

}
