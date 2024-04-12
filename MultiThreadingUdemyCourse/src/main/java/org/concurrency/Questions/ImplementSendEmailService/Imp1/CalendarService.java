package org.concurrency.Questions.ImplementSendEmailService.Imp1;

import java.time.Duration;
import java.time.LocalTime;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/*
9.	Given a blackbox sendEmail() API, implement two APIs to book calendar and send notification to all the invites(email ids) at the same of when the event would occur based on it's time of event and isRecurring flag. If isRecurring == true, repeat the send notification everyday at the same time else only send it once at the scheduled time.

addEvent(time, list of emails, message, isRecurring)
Eg.
["15:00:00", {"rty@dd.com", "wer@gg.com", "rwq@qq.com"}, "message1", true]
["15:00:01", {"rty@dd.com", "wer@gg.com"}, "message2", false]

sendEvent()  (https://leetcode.com/discuss/interview-question/2308719/Rubrik-or-Phone-or-July-2022)

 */


/*
LocalTime is a class in the java.time package introduced in Java 8 as part of the Date and Time API. It represents a time without time zone information, such as 10:15:30. It is immutable and thread-safe, making it suitable for use in concurrent applications.

Here's a brief overview of LocalTime:

It represents a time, typically used for human-based activities.
It does not store or represent a date or time-zone.
It is immutable, meaning once it's created, it cannot be changed.
It provides methods to get hours, minutes, seconds, and nanoseconds.
In the context of the CalendarService example, LocalTime is used to represent the time of an event. We use it to parse the time strings provided when adding events and to calculate the initial delay before sending notifications.
 */

public class CalendarService {
    private ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(2); // careful here, don't use ExecutorService, use ScheduledExecutorService


    public void addEvent(String time, Set<String> emails, String message, boolean isRecurring) {
        LocalTime eventTime = LocalTime.parse(time);

        if(isRecurring) {
            scheduledExecutorService.scheduleAtFixedRate(() -> { sendEvent(emails, message);
                },
                    getInitialDelay(eventTime),24*60*60*1000, TimeUnit.MILLISECONDS); /// Be careful here, initial delay and delay shall be of same UNIT. For 24 hours, convert to milliseconds
        } else {
            scheduledExecutorService.schedule(()-> {
                sendEvent(emails, message);
            }, getInitialDelay(eventTime), TimeUnit.MILLISECONDS);
        }
    }

    public long getInitialDelay(LocalTime eventTime){
        return Duration.between(LocalTime.now(), eventTime).toMillis();
    }

    public void sendEvent(Set<String> emails, String message) {
        for(String email : emails) {
            sendEmail(email, message);
        }
    }

    // Implement the sendEmail() method using the blackbox API
    public void sendEmail(String email, String message) {
        System.out.println("Sending email to " + email + " with message : " + message);
    }

    public static void main(String[] args) {
        CalendarService calendarService = new CalendarService();
        // Add events
        calendarService.addEvent("15:01:00", Set.of("uvsaiswaroop@gmail.com","ganesh.shah@gmail.com"),"Hello", true);

        calendarService.addEvent("15:01:00", Set.of("uvsaiswaroop@gmail.com","ganesh.shah@gmail.com"),"Hi, join to attend Rubrik Webinar", false);

        try{
            Thread.sleep(10000); // Sleep for 24 hours
        } catch (InterruptedException e){
            e.printStackTrace();
        } finally {
            calendarService.scheduledExecutorService.shutdown();
        }

    }
}
