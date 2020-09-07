package com.twu;

import java.util.*;

public class Main {

    public static void main(String[] args) {

        String control = "启动";
        int homePageOption = 3;
        List<HotSearch> hotSearchList = new LinkedList();
        List<HotSearch> rankingsList = new LinkedList<>();
        LinkedHashMap<Integer,HotSearch> hotSearchMap = new LinkedHashMap<>();
        while (control != "退出"){
            System.out.println("欢迎来到热搜排行榜，你是？");
            System.out.println("1.用户\n"+"2.管理员\n"+"3.退出");
            Scanner input = new Scanner(System.in);
            homePageOption = input.nextInt();
            switch (homePageOption){
                case 1:
                    System.out.println("请输入你的昵称");
                    Scanner userInput = new Scanner(System.in);
                    String userName = userInput.nextLine();
                    User user = new User(userName);
                    int userPageOption = 5;
                    String userControl = "进入";
                    while (userControl!="退出"){
                        System.out.println("你好,"+userName+"你可以：");
                        System.out.println("1.查看热搜排行榜\n"+"2.给热搜事件投票\n"+"3.购买热搜\n"+"4.添加热搜\n"+"5.退出");
                        userPageOption = input.nextInt();
                        switch (userPageOption){
                            case 1:
                                user.ViewHotSearchRankings(rankingsList);
                                break;
                            case 2:
                                if(user instanceof User){
                                    User userTemp = (User) user;
                                    userTemp.voteForHotSearch(hotSearchList, rankingsList, hotSearchMap);
                                }else {
                                    throw new RuntimeException("The type does not match and cannot be converted down");
                                }

                                break;
                            case 3:
                                user.hotShopping(hotSearchList, rankingsList, hotSearchMap);
                                break;
                            case 4:
                                user.AddHotSearch(hotSearchList,rankingsList, hotSearchMap);
                                break;
                            case 5:
                                userControl = "退出";
                                break;
                        }
                    }

                    break;
                case 2:
                    Administrator admin = new Administrator();
                    Scanner adminInput = new Scanner(System.in);
                    System.out.println("请输入您的昵称：");
                    String adminName = adminInput.nextLine();
                    System.out.println("请输入您的密码");
                    String  password = adminInput.nextLine();
                    if (admin.getAdminName().equals(adminName)&&admin.getPassWord().equals(password)){
                        int adminPageOption = 4;
                        String adminControl = "进入";
                        while (adminControl != "退出"){
                            System.out.println("你好,"+adminName+"你可以：");
                            System.out.println("1.查看热搜排行榜\n"+"2.添加热搜\n"+"3.添加超级热搜\n"+"4.退出");
                            userPageOption = input.nextInt();
                            switch (userPageOption){
                                case 1:
                                    admin.ViewHotSearchRankings(rankingsList);
                                    break;
                                case 2:
                                    admin.AddHotSearch(hotSearchList, rankingsList, hotSearchMap);
                                    break;
                                case 3:
                                    if (admin instanceof Administrator){
                                        Administrator adminTemp = (Administrator) admin;
                                        adminTemp.adminAddSuperHotSearch(hotSearchList, rankingsList, hotSearchMap);
                                    }else {
                                        throw new RuntimeException("The type does not match and cannot be converted down");
                                    }

                                    break;
                                case 4:
                                    adminControl = "退出";
                                    break;
                            }
                        }
                    } else {
                        System.out.println("您输入的管理员不存在或密码错误");
                    }
                    break;
                case 3:
                    control="退出";
                    break;
            }

        }

    }
}
