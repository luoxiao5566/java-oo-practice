package com.twu;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
/**
 * This is a user class
 * */
public class User extends operator implements HotSearchOperation {
    //userName
    private String userName;
    //Default number of votes
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

    @Override
    public void voteForHotSearch(List<HotSearch> hotSearchList, List<HotSearch> rankingsList, LinkedHashMap<Integer, HotSearch> hotSearchMap) {
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

}
