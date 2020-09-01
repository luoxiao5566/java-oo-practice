package com.twu;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class User {
    private String userName;
    private int defaultVotes=10;

    public User() {
    }

    public User(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public int getDefaultVotes() {
        return defaultVotes;
    }

    public void setDefaultVotes(int defaultVotes) {
        this.defaultVotes = defaultVotes;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", defaultVotes=" + defaultVotes +
                '}';
    }
    //Add hot search
    public void userAddHotSearch(LinkedList<HotSearch> hotSearchList,LinkedList<HotSearch> rankingsList,LinkedHashMap<Integer,HotSearch> hotSearchMap){
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
    //Vote for hot search
    public void voteForHotSearch(LinkedList<HotSearch> hotSearchList, LinkedList<HotSearch> rankingsList,LinkedHashMap<Integer,HotSearch> hotSearchMap){
        String hotSearchName;
        int value = this.defaultVotes;
        Scanner input = new Scanner(System.in);
        System.out.println("请输入你要投票的热搜名称：");
        String userHotSearchName = input.nextLine();
        boolean result = VerificationHotSearch.Verification(userHotSearchName, hotSearchList, hotSearchMap);
        while (!result){
            System.out.println("没有"+userHotSearchName+"这个热搜，请重新输入或者输入quit退出");
            String againHotSearchName = input.nextLine();
            if (againHotSearchName.equals("quit") ){
                return;
            }
            result=VerificationHotSearch.Verification(againHotSearchName, hotSearchList, hotSearchMap);
            if (result){
                userHotSearchName = againHotSearchName;
            }
        }
        System.out.println("请输入你的票数：  （你目前还有："+value+"票)");

        int userVotes =input.nextInt();
        while (userVotes<1||userVotes>value){
            System.out.println("你输入的票数有误，请重新输入（你目前还有："+value+"票)，或者输入0退出");
            int againUserVotes = input.nextInt();
            if (againUserVotes == 0){
                return;
            }
            userVotes=againUserVotes;
        }

        boolean queryResults = false;
        Iterator<HotSearch> iter = hotSearchList.iterator();
        while (iter.hasNext()){
            HotSearch temp = iter.next();
            if (temp.getHotSearchName().equals(userHotSearchName)){
                temp.setSearchHeat(userVotes);
                this.setDefaultVotes(value-userVotes);
                System.out.println("投票成功");
                HotSearchSorting.hotSearchSorting(hotSearchList, rankingsList, hotSearchMap);
                queryResults = true;
                break;
            }
        }
        if (queryResults == false){
            Iterator iterMap = hotSearchMap.entrySet().iterator();
            while (iterMap.hasNext()){
                Map.Entry entry = (Map.Entry) iterMap.next();
                if (entry.getValue() instanceof HotSearch){
                    HotSearch hotSearch = (HotSearch) entry.getValue();
                    if (hotSearch.getHotSearchName().equals(userHotSearchName)){
                        hotSearch.setSearchHeat(userVotes);
                        this.setDefaultVotes(value-userVotes);
                        System.out.println("投票成功");
                    }
                }

            }
        }
    }

    //Buy hot search for events
    public void hotShopping(LinkedList<HotSearch> hotSearchList, LinkedList<HotSearch> rankingsList,LinkedHashMap<Integer,HotSearch> hotSearchMap){
        String hotSearchName;
        Scanner input = new Scanner(System.in);
        System.out.println("请输入你要购买的热搜名称：");
        String userHotSearchName = input.nextLine();
        boolean result = VerificationHotSearch.Verification(userHotSearchName, hotSearchList, hotSearchMap);
        while (!result){
            System.out.println("没有"+userHotSearchName+"这个热搜，请重新输入或者输入quit退出");
            String againHotSearchName = input.nextLine();
            if (againHotSearchName.equals("quit") ){
                return;
            }
            result=VerificationHotSearch.Verification(againHotSearchName, hotSearchList, hotSearchMap);
            if (result){
                userHotSearchName = againHotSearchName;
            }
        }

        System.out.println("请输入你要购买的排名：");
        int ranking =input.nextInt();
        System.out.println("请输入你要购买的热搜金额：");
        int cost =input.nextInt();
        boolean checkRanking = hotSearchMap.containsKey(ranking);
        int currentValue=0;
        if (checkRanking){
            HotSearch tempMapObject = hotSearchMap.get(ranking);
            if (tempMapObject.getHotSearchName().equals(userHotSearchName)&&tempMapObject.getHotSearchPrice()<cost){
                System.out.println("购买成功");
                tempMapObject.setHotSearchPrice(cost);
                return;
            }
            if (tempMapObject.getHotSearchName().equals(userHotSearchName)&&tempMapObject.getHotSearchPrice()>=cost){
                System.out.println("购买失败");
                return;
            }
            if (!tempMapObject.getHotSearchName().equals(userHotSearchName)){
                currentValue = tempMapObject.getHotSearchPrice();
                Iterator<HotSearch> iter = hotSearchList.iterator();
                while (iter.hasNext()){
                    HotSearch tempListObject = iter.next();
                    if (tempListObject.getHotSearchName().equals(userHotSearchName)&&currentValue<cost){
                        System.out.println("购买成功");
                        hotSearchMap.remove(ranking);
                        hotSearchMap.put(ranking, tempListObject);
                        iter.remove();
                        HotSearchSorting.hotSearchSorting(hotSearchList, rankingsList, hotSearchMap);
                        return;
                    }
                    if (tempListObject.getHotSearchName().equals(userHotSearchName)&&currentValue>=cost){
                        System.out.println("购买失败");
                        return;
                    }
                }
            }
        }
        if (!checkRanking){
            Iterator<HotSearch> iter = hotSearchList.iterator();
            while (iter.hasNext()){
                HotSearch tempListValue = iter.next();
                if (tempListValue.getHotSearchName().equals(userHotSearchName)){
                    System.out.println("购买成功");
                    hotSearchMap.put(ranking, tempListValue);
                    iter.remove();
                    HotSearchSorting.hotSearchSorting(hotSearchList, rankingsList, hotSearchMap);
                    return;
                }
            }
        }
    }

    //View hot search rankings
    public void userViewHotSearchRankings(LinkedList<HotSearch> rankingsList){
        AtomicInteger ranking= new AtomicInteger();
        rankingsList.stream().forEach(hotSearch->{
            ranking.getAndIncrement();
            System.out.println(ranking+" "+hotSearch.getHotSearchName()+" "+hotSearch.getSearchHeat());
        });
    }

}
