package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.ad.Advertisement;
import com.javarush.task.task27.task2712.ad.StatisticAdvertisementManager;
import com.javarush.task.task27.task2712.statistic.StatisticManager;

import java.text.SimpleDateFormat;
import java.util.*;

public class DirectorTablet {
    public void printAdvertisementProfit()
    {
        Map<Date, Long> advertisementProfitMap = StatisticManager.getInstance().amountAdPerDay();
        long totalAmount = 0;
        long amountPerDay;
        for (Map.Entry<Date, Long> entry: advertisementProfitMap.entrySet()
             ) {
            amountPerDay = entry.getValue();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
            ConsoleHelper.writeMessage(String.format(simpleDateFormat.format(entry.getKey()) + " - %1$.2f", amountPerDay / 100.00));
            totalAmount += amountPerDay;
        }
        ConsoleHelper.writeMessage(String.format("Total - %1$.2f", totalAmount / 100.00));
    }

    public void printCookWorkloading()
    {
        Map<Date, Map<String, Integer>> cookWorkloadingMap = StatisticManager.getInstance().timeCookWorkloading();
        for (Map.Entry<Date, Map<String, Integer>> entryWorkloadingMap: cookWorkloadingMap.entrySet()
             ) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
            ConsoleHelper.writeMessage(simpleDateFormat.format(entryWorkloadingMap.getKey()));
            Map<String, Integer> cookMap = entryWorkloadingMap.getValue();
            for (Map.Entry<String, Integer> entryCookMap: cookMap.entrySet()
                 ) {
                if (entryCookMap.getValue() > 0) {
                    ConsoleHelper.writeMessage(entryCookMap.getKey() + " - " + entryCookMap.getValue() + " min");
                }
            }
            ConsoleHelper.writeMessage("");
        }
    }

    public void printActiveVideoSet()
    {
        List<Advertisement> advertisementList = StatisticAdvertisementManager.getInstance().getActiveVideos();
        Collections.sort(advertisementList, new Comparator<Advertisement>() {
            @Override
            public int compare(Advertisement o1, Advertisement o2) {
                if (o1.getName().compareToIgnoreCase(o2.getName()) > 0) return 1;
                else if (o1.getName().compareToIgnoreCase(o2.getName()) < 0) return -1;
                else return 0;
            }
        });
        for (Advertisement item: advertisementList
             ) {
            ConsoleHelper.writeMessage(String.format("%1$s - %2$d", item.getName(), item.getHits()));
        }
    }

    public void printArchivedVideoSet()
    {
        List<Advertisement> advertisementList = StatisticAdvertisementManager.getInstance().getArchiveVideos();
        Collections.sort(advertisementList, new Comparator<Advertisement>() {
            @Override
            public int compare(Advertisement o1, Advertisement o2) {
                if (o1.getName().compareToIgnoreCase(o2.getName()) > 0) return 1;
                else if (o1.getName().compareToIgnoreCase(o2.getName()) < 0) return -1;
                else return 0;
            }
        });
        for (Advertisement item: advertisementList
        ) {
            ConsoleHelper.writeMessage(String.format("%1$s", item.getName()));
        }
    }
}
