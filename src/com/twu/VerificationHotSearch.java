package com.twu;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.jar.JarOutputStream;

public class VerificationHotSearch {
    public static boolean Verification(String hotSearchName,LinkedList<HotSearch> hotSearchList, LinkedHashMap<Integer,HotSearch> hotSearchMap){
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
