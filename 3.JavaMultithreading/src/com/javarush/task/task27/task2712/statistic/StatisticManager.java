package com.javarush.task.task27.task2712.statistic;

import com.javarush.task.task27.task2712.kitchen.Cook;
import com.javarush.task.task27.task2712.statistic.event.CookedOrderEventDataRow;
import com.javarush.task.task27.task2712.statistic.event.EventDataRow;
import com.javarush.task.task27.task2712.statistic.event.EventType;
import com.javarush.task.task27.task2712.statistic.event.VideoSelectedEventDataRow;

import java.util.*;

public class StatisticManager {
    private static StatisticManager ourInstance = new StatisticManager();
    private StatisticStorage statisticStorage = new StatisticStorage();

    private class StatisticStorage{
        private Map<EventType, List<EventDataRow>> storage = new HashMap<>();

        public StatisticStorage() {
            EventType[] arrayEventTypes = EventType.values();
            for (int i = 0; i < arrayEventTypes.length; i++){
                storage.put(arrayEventTypes[i], new ArrayList<EventDataRow>());
            }
        }

        private void put(EventDataRow data)
        {
            storage.get(data.getType()).add(data);
        }

        public Map<EventType, List<EventDataRow>> getStorage() {
            return storage;
        }
    }

    public static StatisticManager getInstance() {
        return ourInstance;
    }

    private StatisticManager() {
    }

    public void register(EventDataRow data)
    {
        statisticStorage.put(data);
    }

    public Map<Date, Long> amountAdPerDay()
    {
        Map<Date, Long> resultMap = new TreeMap<>(Collections.reverseOrder());
        List<EventDataRow> eventDataRowList = statisticStorage.getStorage().get(EventType.SELECTED_VIDEOS);
        long amount;
        Date date;
        Calendar calendar;
        for (EventDataRow eventDataRow : eventDataRowList
             ) {
            VideoSelectedEventDataRow videoSelected = (VideoSelectedEventDataRow) eventDataRow;
            amount = videoSelected.getAmount();
            calendar = Calendar.getInstance();
            calendar.setTime(videoSelected.getDate());
            GregorianCalendar gregorianCalendar = new GregorianCalendar(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
            date = gregorianCalendar.getTime();
            if (resultMap.containsKey(date)) amount += resultMap.get(date);
            resultMap.put(date, amount);
        }

        return resultMap;
    }

    public Map<Date, Map<String, Integer>> timeCookWorkloading()
    {
        Map<Date, Map<String, Integer>> resultMap = new TreeMap<>(Collections.reverseOrder());
        List<EventDataRow> eventDataRowList = statisticStorage.getStorage().get(EventType.COOKED_ORDER);
        for (EventDataRow eventDataRow: eventDataRowList
             ) {
            CookedOrderEventDataRow cookedOrder = (CookedOrderEventDataRow) eventDataRow;
            Date date = cookedOrder.getDate();
            String cookName = cookedOrder.getCookName();
            int cookingTime = cookedOrder.getTime();
            boolean isDateExists = false;
            if (resultMap.isEmpty())
            {
                Map<String, Integer> cookMap = new TreeMap<>();
                cookMap.put(cookName, cookingTime);
                resultMap.put(date, cookMap);
            }
            else {
                for (Map.Entry<Date, Map<String, Integer>> entryResultMap: resultMap.entrySet()
                 ) {
                    if (entryResultMap.getKey().getDate() == date.getDate())
                    {
                        isDateExists = true;
                        if (entryResultMap.getValue().containsKey(cookName))
                        {
                            int tempCookingTime = entryResultMap.getValue().get(cookName) + cookingTime;
                            entryResultMap.getValue().replace(cookName, tempCookingTime);
                        }
                        else {
                            entryResultMap.getValue().put(cookName, cookingTime);
                        }
                    }
                }
            }
            if (!isDateExists)
            {
                Map<String, Integer> cookMap = new TreeMap<>();
                cookMap.put(cookName, cookingTime);
                resultMap.put(date, cookMap);
            }

        }
        return resultMap;
    }
}
