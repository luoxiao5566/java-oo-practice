package com.twu;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
  /**
   * This is an administrator class。
   * The default user name is: admin
   * The default password is: 123
   * */
public class Administrator extends operator implements HotSearchOperation {
    //The default user name and password.
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
    //Add super hot search
    public void adminAddSuperHotSearch(List<HotSearch> hotSearchList, List<HotSearch> rankingsList, LinkedHashMap<Integer,HotSearch> hotSearchMap){
        System.out.println("请输入你要添加的超级热搜事件的名字：");
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
}
