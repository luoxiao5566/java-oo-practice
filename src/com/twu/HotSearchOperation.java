package com.twu;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
/**
 * This is a hot search operation interface, which defines the operation of users and administrators on hot search。
 */

public interface HotSearchOperation {
    /**
     *AddHotSearch：This is a function to add hot searchm
     * @param hotSearchList Store added hot searches;
     * @param rankingsList Hot search ranking after storage sorting;
     * @param hotSearchMap Store purchased hot search;
     */
    public void AddHotSearch(List<HotSearch> hotSearchList, List<HotSearch> rankingsList, LinkedHashMap<Integer,HotSearch> hotSearchMap);
    /**
     * voteForHotSearch：This is a function to vote for hot search；
     */
    public void voteForHotSearch(List<HotSearch> hotSearchList, List<HotSearch> rankingsList,LinkedHashMap<Integer,HotSearch> hotSearchMap);
    /**
     * hotShopping：This is a purchase hot search function；
     */
    public void hotShopping(List<HotSearch> hotSearchList, List<HotSearch> rankingsList,LinkedHashMap<Integer,HotSearch> hotSearchMap);
    /**
    * ViewHotSearchRankings：This is a function to query the hot search ranking。
    */
    public void ViewHotSearchRankings(List<HotSearch> rankingsList);
}
