package com.menu.service;

import com.menu.controller.HomeController;
import com.menu.dao.IMenuDao;
import com.menu.util.MenuPage;
import com.menu.vo.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class MenuServiceImpl implements IMenuService {

    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    IMenuDao menuDao;

    @Override
    public ArrayList<MenuDBVO> selectMenuListTest() {//테스트
        return menuDao.selectMenuListTest();
    }

    @Override
    public ArrayList<MenuDBVO> selectMenuList(SearchData vo) {//메뉴 불러오기.
        return menuDao.selectMenuList(vo);
    }

    @Override
    public ArrayList<MenuCodeVo> selectCode() {//searchPrint.jsp에 식사구분, 식당이 포함된 공통코드테이블 넣어주려고.
        return menuDao.selectCode();
    }

    public WeekMenuTable getTable(SearchData searchData, List<MenuDBVO> vo){
         //조식(월-일)
        MealDivision breakFast = new MealDivision();//순서2
        //중식(월-일)
        MealDivision lunch = new MealDivision();//순서2
        //석식(월-일)
        MealDivision dinner = new MealDivision();//순서2
        //간식(월-일)
        MealDivision snack = new MealDivision();//순서2

        WeekMenuTable weekMenuTable = new WeekMenuTable(searchData.getRestaurantName(),searchData.getStartDate(),searchData.getEndDate());//순서3

        //날짜 포맷
        DateFormat input = new SimpleDateFormat("E");

        for(MenuDBVO menuDBVO : vo){
            //logger.info("-------------------" + menuDBVO.getYmd());
            Date date = menuDBVO.getYmd();
            String toStringYmd = input.format(date);

            if(menuDBVO.getMealNm().equals("조식")){
                //logger.info("조식------------------------------");
                //월-일
                dayDivision(toStringYmd,breakFast,menuDBVO);

            }else if(menuDBVO.getMealNm().equals("중식")){
                //logger.info("중식------------------------------");
                //월-일
                dayDivision(toStringYmd,lunch,menuDBVO);

            }else if(menuDBVO.getMealNm().equals("석식")){
                //logger.info("석식------------------------------");
                //월-일
                dayDivision(toStringYmd,dinner,menuDBVO);

            }else if(menuDBVO.getMealNm().equals("간식")){
                //logger.info("간식------------------------------");
                //월-일
                dayDivision(toStringYmd,snack,menuDBVO);
            }
        }//forEach

        weekMenuTable.getMdList().add(breakFast);
        weekMenuTable.getMdList().add(lunch);
        weekMenuTable.getMdList().add(dinner);
        weekMenuTable.getMdList().add(snack);

        return weekMenuTable;
    }


    public void dayDivision(String toStringYmd,MealDivision mealDivision, MenuDBVO menuDBVO){
        if(toStringYmd.equals("월")) {
            //DayMealList는 객체가 생성되면 6개방이 생성되므로
            mealDivision.getDayMealList().get(0).getMenuRecipeList().add(new MenuRecipe(menuDBVO.getFoodNm(),menuDBVO.getIngredientsNm()));
        }else if(toStringYmd.equals("화")){
            mealDivision.getDayMealList().get(1).getMenuRecipeList().add(new MenuRecipe(menuDBVO.getFoodNm(),menuDBVO.getIngredientsNm()));
        }else if(toStringYmd.equals("수")) {
            mealDivision.getDayMealList().get(2).getMenuRecipeList().add(new MenuRecipe(menuDBVO.getFoodNm(),menuDBVO.getIngredientsNm()));
        }else if(toStringYmd.equals("목")) {
            mealDivision.getDayMealList().get(3).getMenuRecipeList().add(new MenuRecipe(menuDBVO.getFoodNm(),menuDBVO.getIngredientsNm()));
        } else if(toStringYmd.equals("금")) {
            mealDivision.getDayMealList().get(4).getMenuRecipeList().add(new MenuRecipe(menuDBVO.getFoodNm(),menuDBVO.getIngredientsNm()));
        }else if(toStringYmd.equals("토")) {
            mealDivision.getDayMealList().get(5).getMenuRecipeList().add(new MenuRecipe(menuDBVO.getFoodNm(),menuDBVO.getIngredientsNm()));
        }else if(toStringYmd.equals("일")) {
            mealDivision.getDayMealList().get(6).getMenuRecipeList().add(new MenuRecipe(menuDBVO.getFoodNm(),menuDBVO.getIngredientsNm()));
        }
    }

  /*  public MenuPage paging(WeekMenuTable weekMenuTable){//페이징
        WeekMenuTable wt = new WeekMenuTable();
        MenuPage mp = new MenuPage(); //한페이지당 내용을 작성해서 jsp에 보냄.

        ArrayList<MealDivision> breakFastList= new ArrayList<>();// 조식 리스트
        ArrayList<MealDivision> lunchList= new ArrayList<>(); //  점심 리스트
        ArrayList<MealDivision> dinnerList= new ArrayList<>();// 저녁 리스트
        ArrayList<MealDivision> snackList= new ArrayList<>();// 간식 리스트


        int countList = 20;//화면에 출력할 페이지 수
        int count = 0;

        //조식,중식,석식,간식 List<MealDivision> mdList
        //식사구분당 요일별로 LIST를 가지고 있다 +(음식,재료)
        //해당 식사구분에 요일들중 가장 큰 값을 구해야한다.
        //조식에 월 = 20개, 화= 20개, 수 = 30개 이렇게
        //=> 큰값/20 = 페이지 수, 큰값/20 = 나머지
        //arr[0].leagth : 행 길이

        //가장큰수 구하기
    *//*   for(int i=0; i<6; i++){//월-일
           for(int j=0; j<wt.getMdList().get(0).getRecipeListMon().size();i++){
               ++count;
           }
       }

       logger.info("count : "+String.valueOf(count));*//*

        //조식(weekMenuTable.getMdList().get(0))
        int monBrackCnt = weekMenuTable.getMdList().get(0).getRecipeListMon().size();
        int tueBrackCnt = weekMenuTable.getMdList().get(0).getRecipeListTue().size();
        int webBrackCnt = weekMenuTable.getMdList().get(0).getRecipeListWed().size();
        int thuBrackCnt = weekMenuTable.getMdList().get(0).getRecipeListThu().size();
        int friBrackCnt = weekMenuTable.getMdList().get(0).getRecipeListFri().size();
        int sunBrackCnt = weekMenuTable.getMdList().get(0).getRecipeListSat().size();
        int satBrackCnt = weekMenuTable.getMdList().get(0).getRecipeListSun().size();

        int[] arrayCnt = new int[]{monBrackCnt,tueBrackCnt,webBrackCnt,thuBrackCnt,friBrackCnt,sunBrackCnt,satBrackCnt};
        int max = arrayCnt[0]; //최대값(임시)
        //조식 가장 큰수 구하기
        for(int i=0; i<arrayCnt.length; i++){
            if(max<arrayCnt[i]){
                max = arrayCnt[i];
            }
        }
        logger.info("조식 max : "+max +" 페이지 : "+max/countList + ", 나머지 : "+max%countList);

        int page = max/countList;
        int remainder = max%countList;//이게 포인트임...


        //logger.info(String.valueOf(weekMenuTable.getMdList().get(0).getRecipeListFri().subList(0,20)));

        //logger.info("조식 - 월 : "+monBrackCnt+"화 : "+tueBrackCnt+"수 : "+webBrackCnt+"목 : "+thuBrackCnt+"금 : "+friBrackCnt+"토 : "+satBrackCnt+"일: "+sunBrackCnt);

        //logger.info("paging테스트 : "+weekMenuTable.getMdList().get(0).getRecipeListMon().size());

       //logger.info(String.valueOf(weekMenuTable.getMdList().size()));//4

        //logger.info("paging테스트 : "+weekMenuTable.getMdList().get(0).getRecipeListMon().get(0).getFoodName());

        //mp.getWeekMenuTableList().add(); //한페이지당 출력할 성형한 list들을 넣고


       *//* wt.getMdList().add();
        mp.getWeekMenuTableList().add(breakFastList);*//*

        return mp;
    }*/
}
