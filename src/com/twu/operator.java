package com.twu;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * An abstract class is defined to reduce the difficulty of interface implementation
 * The HotSearchOperation interface has been rewritten
 */

public abstract class operator implements HotSearchOperation{
    @Override
    public void AddHotSearch(List<HotSearch> hotSearchList, List<HotSearch> rankingsList, LinkedHashMap<Integer, HotSearch> hotSearchMap) {
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

    @Override
    public void voteForHotSearch(List<HotSearch> hotSearchList, List<HotSearch> rankingsList, LinkedHashMap<Integer, HotSearch> hotSearchMap) {
    }

    @Override
    public void hotShopping(List<HotSearch> hotSearchList, List<HotSearch> rankingsList, LinkedHashMap<Integer, HotSearch> hotSearchMap) {
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

    @Override
    public void ViewHotSearchRankings(List<HotSearch> rankingsList) {
        AtomicInteger ranking= new AtomicInteger();
        rankingsList.stream().forEach(hotSearch->{
            ranking.getAndIncrement();
            System.out.println(ranking+" "+hotSearch.getHotSearchName()+" "+hotSearch.getSearchHeat());
        });
    }
}
