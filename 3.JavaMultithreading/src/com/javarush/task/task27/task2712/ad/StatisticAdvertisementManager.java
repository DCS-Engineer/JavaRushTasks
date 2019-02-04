package com.javarush.task.task27.task2712.ad;

import java.util.ArrayList;
import java.util.List;

public class StatisticAdvertisementManager {
    private static StatisticAdvertisementManager ourInstance = new StatisticAdvertisementManager();
    private static AdvertisementStorage advertisementStorage = AdvertisementStorage.getInstance();

    public static StatisticAdvertisementManager getInstance() {
        return ourInstance;
    }

    private StatisticAdvertisementManager() {
    }

    public static AdvertisementStorage getAdvertisementStorage() {
        return advertisementStorage;
    }

    public List<Advertisement> getActiveVideos(){
        List<Advertisement> advertisementList = new ArrayList<>();
        for (Advertisement item: getAdvertisementStorage().list()
             ) {
            if (item.getHits() > 0) advertisementList.add(item);
        }
        return advertisementList;
    }

    public List<Advertisement> getArchiveVideos(){
        List<Advertisement> advertisementList = new ArrayList<>();
        for (Advertisement item: getAdvertisementStorage().list()
             ) {
            if (item.getHits() == 0) advertisementList.add(item);
        }
        return advertisementList;
    }
}
