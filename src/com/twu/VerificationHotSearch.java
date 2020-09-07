package com.twu;

import java.util.*;
import java.util.jar.JarOutputStream;
/**
 *This is a class to judge whether a hot search already exists.
 * It defines a static function to query whether a hot search already exists and returns a Boolean
 */
public class VerificationHotSearch {
    /**
     * Verification:This is a static function to query whether hot search already exists.
     * @return Boolean If the hot search already exists, it returns true; if it does not, it returns false.
     * @param hotSearchName Hot search name added;
     * @param hotSearchList Store added hot searches;
     * @param hotSearchMap Store purchased hot search;
     * */
    public static boolean Verification(String hotSearchName, List<HotSearch> hotSearchList, LinkedHashMap<Integer,HotSearch> hotSearchMap){
        int listSize = hotSearchList.size();
        int mapSize = hotSearchMap.size();
        if (listSize==0&&mapSize==0){
            return false;
        }
        if (listSize!=0&&mapSize==0){
            Iterator<HotSearch> iter = hotSearchList.iterator();
            while (iter.hasNext()){
                HotSearch temp = iter.next();
                if (temp.getHotSearchName().equals(hotSearchName)){
                    return true;
                }
            }
        }
        if (listSize==0&&mapSize!=0){
            Iterator iterMap = hotSearchMap.entrySet().iterator();
            while (iterMap.hasNext()){
                Map.Entry entry = (Map.Entry) iterMap.next();
                if (entry.getValue() instanceof HotSearch){
                    HotSearch hotSearch = (HotSearch) entry.getValue();
                    if (hotSearch.getHotSearchName().equals(hotSearchName)){
                        return true;
                    }
                }

            }
        }
        if (listSize!=0&&mapSize!=0){
            Iterator<HotSearch> iter = hotSearchList.iterator();
            while (iter.hasNext()){
                HotSearch temp = iter.next();
                if (temp.getHotSearchName().equals(hotSearchName)){
                    return true;
                }
            }
            Iterator iterMap = hotSearchMap.entrySet().iterator();
            while (iterMap.hasNext()){
                Map.Entry entry = (Map.Entry) iterMap.next();
                if (entry.getValue() instanceof HotSearch){
                    HotSearch hotSearch = (HotSearch) entry.getValue();
                    if (hotSearch.getHotSearchName().equals(hotSearchName)){
                        return true;
                    }
                }
            }
        }
        return false;


    }
}
