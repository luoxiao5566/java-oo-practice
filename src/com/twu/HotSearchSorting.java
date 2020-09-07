package com.twu;

import java.util.*;

/**
 * This is a sort of hot search class, defines a static function to sort the hot search.
 * */

public class HotSearchSorting {
    /**
     * hotSearchSorting:Is a static function to sort hot searches.
     * The result of sorting is stored in rankingsList.
     * @param hotSearchList Store added hot searches;
     * @param rankingsList Hot search ranking after storage sorting;
     * @param hotSearchMap Store purchased hot search;
     * */
    public static void hotSearchSorting(List<HotSearch> hotSearchList, List<HotSearch> rankingsList, LinkedHashMap<Integer,HotSearch> hotSearchMap){
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
                if(index > rankingsList.size()-1){
                    rankingsList.add(cur);
                }else {
                    rankingsList.add(index-1,cur);
                }


            }

        }
    }

}
