package com.twu;
import java.util.*;
public class HotSearch {
    private String hotSearchName;
    private int searchHeat=0;
    private int heatWeight=1;
    private int hotSearchPrice=0;
    public HotSearch() {
    }

    public HotSearch(String hotSearchName) {
        this.hotSearchName = hotSearchName;
    }

    public HotSearch(String hotSearchName, int heatWeight) {
        this.hotSearchName = hotSearchName;
        this.heatWeight = heatWeight;
    }

    public int getSearchHeat() {
        return searchHeat;
    }
    //设置热搜热度
    public void setSearchHeat(int searchHeat) {
        this.searchHeat = searchHeat*heatWeight;
    }

    public int getHotSearchPrice() {
        return hotSearchPrice;
    }
    //设置热搜价钱
    public void setHotSearchPrice(int hotSearchPrice) {
        this.hotSearchPrice = hotSearchPrice;
    }

    public String getHotSearchName() {
        return hotSearchName;
    }

    public int getHeatWeight() {
        return heatWeight;
    }

    @Override
    public String toString() {
        return "HotSearch{" +
                "hotSearchName='" + hotSearchName + '\'' +
                ", searchHeat=" + searchHeat +
                ", heatWeight=" + heatWeight +
                ", hotSearchPrice=" + hotSearchPrice +
                '}';
    }
}
