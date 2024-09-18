package com.example.demo.service;

import com.example.demo.entity.ErrorAlert;
import com.example.demo.entity.Role;
import com.example.demo.request.ErrorRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class ErrorService {
    private Integer count = 0;
    Map<Integer, Integer> errorTimeCount = new LinkedHashMap<>();
    public void sendErrorAlert() {
        count++;
        Boolean flag1 = Boolean.FALSE;
        Boolean flag2 = Boolean.FALSE;
        //this function is to make unique key
        Integer key = this.getKey(LocalDateTime.now());
        errorTimeCount.put(key, count);
        Role roles = new Role();
        for (Map.Entry<Integer, Integer> entry : errorTimeCount.entrySet()) {
            if (key - entry.getKey() + 1 <= ErrorAlert.adminTimeWindow  && count - entry.getValue() + 1 >= ErrorAlert.adminErrorCount) {
                log.info(roles.getAdminErrorMessage().getValue() + "with time window :{} and error count diff :{}" ,key - entry.getKey(), count - entry.getValue() + 1);
                flag1 = Boolean.TRUE;
                break;
            }
        }
        if (!flag1)
            log.info("no error to admin");

        for (Map.Entry<Integer, Integer> entry : errorTimeCount.entrySet()) {
            if (key - entry.getKey() + 1 <= ErrorAlert.developerTimeWindow && count - entry.getValue() + 1>= ErrorAlert.developerErrorCount) {
                log.info(roles.getDeveloperErrorMessage().getValue() + "with time window :{} and total error in that window :{}" ,key - entry.getKey(), count - entry.getValue());
                flag2 = Boolean.TRUE;
                break;
            }
        }
        if (!flag2)
            log.info("no error to developer");
    }

    private Integer getKey(final LocalDateTime currentDateTime) {
        // unique key will be integer and i defined that integer in  the format
        //ddMMyy + HHmmss example: if your date and time is "12-12-2024 T14:45:39"
        // then  your ddMMyyyy = 12122024 and HHmmss = 144539
        // unique key will be ddMMyyyy + HHmmss = 12122024 + 144539 -> this will be unique.
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("ddMMyyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmmss");
        String formattedTime = currentDateTime.format(timeFormatter);
        String formattedDate = currentDateTime.format(dateFormatter);
        LocalDate date = LocalDate.parse(formattedDate, dateFormatter);
        DateTimeFormatter dateOutputFormatter = DateTimeFormatter.ofPattern("ddMMyyyy"); // Example format
        DateTimeFormatter timeOutputFormatter = DateTimeFormatter.ofPattern("HHmmss"); // Example format
        LocalTime time = LocalTime.parse(formattedTime, timeFormatter);
        String formattedDateString = date.format(dateOutputFormatter);
        String formattedTimeString = time.format(timeOutputFormatter);
        Integer dateInteger = Integer.parseInt(formattedDateString);
        Integer timeInteger = Integer.parseInt(formattedTimeString);
        Integer key = dateInteger + timeInteger;
        return key;
    }
}
