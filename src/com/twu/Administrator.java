package com.twu;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;

public class Administrator {
    private String adminName;
    private String passWord;

    public Administrator() {
    }

    public Administrator(String adminName, String passWord) {
        this.adminName = adminName;
        this.passWord = passWord;
    }

    public String getAdminName() {
        return adminName;
    }

    @Override
    public String toString() {
        return "管理员"+getAdminName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Administrator that = (Administrator) o;
        return adminName.equals(that.adminName) &&
                passWord.equals(that.passWord);
    }
    //Add hot search
    public void adminAddHotSearch(String hotSearchName, LinkedList<HotSearch> hotSearchList){
        HotSearch hotSearch = new HotSearch(hotSearchName);
        hotSearchList.add(hotSearch);
        System.out.println("添加热搜成功");
    }
    //Add super hot search
    public void adminAddSuperHotSearch(String hotSearchName, LinkedList<HotSearch> hotSearchList){
        HotSearch hotSearch = new HotSearch(hotSearchName, 2);
        hotSearchList.add(hotSearch);
        System.out.println("添加超级热搜成功");
    }
    //View hot search rankings
    public void adminViewHotSearchRankings(LinkedList<HotSearch> rankingsList){
        AtomicInteger ranking= new AtomicInteger();
        rankingsList.stream().forEach(hotSearch->{
            ranking.getAndIncrement();
            System.out.println(ranking+" "+hotSearch.getHotSearchName()+" "+hotSearch.getSearchHeat());
        });
    }

}
