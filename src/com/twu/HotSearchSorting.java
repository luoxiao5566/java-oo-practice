package com.twu;

import java.util.*;

public class HotSearchSorting {
    public static void hotSearchSorting(LinkedList<HotSearch> hotSearchList, LinkedList<HotSearch> rankingsList, LinkedHashMap<Integer,HotSearch> hotSearchMap){
        rankingsList.clear();
        for (HotSearch hotSearch:hotSearchList) {
            rankingsList.add(hotSearch);
        }
        Collections.sort(rankingsList, new Comparator<HotSearch>() {
            @Override
            public int compare(HotSearch o1, HotSearch o2) {
                return o2.getSearchHeat()-o1.getSearchHeat();
            }
        });
        Iterator iterMap = hotSearchMap.entrySet().iterator();
        while (iterMap.hasNext()){
            Map.Entry entry = (Map.Entry) iterMap.next();
            int index=0;
            if (entry.getKey() instanceof Integer){
                index = (Integer) entry.getKey();
            }
            if (entry.getValue() instanceof HotSearch){
                HotSearch cur = (HotSearch) entry.getValue();
                rankingsList.add(index-1,cur);
            }

        }
    }

}
