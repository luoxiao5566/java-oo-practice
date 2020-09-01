package com.twu;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class Administrator {
    private String adminName = "admin";
    private String passWord = "123";

    public Administrator() {
    }

    public Administrator(String adminName) {
        this.adminName = adminName;
    }

    public Administrator(String adminName, String passWord) {
        this.adminName = adminName;
        this.passWord = passWord;
    }

    public String getAdminName() {
        return adminName;
    }

    public String getPassWord() {
        return passWord;
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
    public void adminAddHotSearch(LinkedList<HotSearch> hotSearchList,LinkedList<HotSearch> rankingsList,LinkedHashMap<Integer,HotSearch> hotSearchMap){
        System.out.println("请输入你要添加的热搜时间的名字：");
        Scanner input = new Scanner(System.in);
        String hotSearchName = input.nextLine();
        boolean result = VerificationHotSearch.Verification(hotSearchName, hotSearchList, hotSearchMap);
        while (result){
            System.out.println("你输入的这个热搜已经存在，请重新输入或者输入quit退出");
            String againHotSearchName = input.nextLine();
            if (againHotSearchName.equals("quit")){
                return;
            }
            result=VerificationHotSearch.Verification(againHotSearchName, hotSearchList, hotSearchMap);
            if (!result){
                hotSearchName = againHotSearchName;
            }
        }
        HotSearch hotSearch = new HotSearch(hotSearchName);
        hotSearchList.add(hotSearch);
        HotSearchSorting.hotSearchSorting(hotSearchList, rankingsList, hotSearchMap);
        System.out.println("添加热搜成功");
    }
    //Add super hot search
    public void adminAddSuperHotSearch(LinkedList<HotSearch> hotSearchList, LinkedList<HotSearch> rankingsList,LinkedHashMap<Integer,HotSearch> hotSearchMap){
        System.out.println("请输入你要添加的超级热搜时间的名字：");
        Scanner input = new Scanner(System.in);
        String hotSearchName = input.nextLine();
        boolean result = VerificationHotSearch.Verification(hotSearchName, hotSearchList, hotSearchMap);
        while (result){
            System.out.println("你输入的这个热搜已经存在，请重新输入或者输入quit退出");
            String againHotSearchName = input.nextLine();
            if (againHotSearchName.equals("quit")){
                return;
            }
            result=VerificationHotSearch.Verification(againHotSearchName, hotSearchList, hotSearchMap);
            if (!result){
                hotSearchName = againHotSearchName;
            }
        }
        HotSearch hotSearch = new HotSearch(hotSearchName, 2);
        hotSearchList.add(hotSearch);
        HotSearchSorting.hotSearchSorting(hotSearchList, rankingsList, hotSearchMap);
        System.out.println("添加热搜成功");

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
