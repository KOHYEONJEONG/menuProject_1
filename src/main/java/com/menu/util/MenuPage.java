package com.menu.util;

import com.menu.controller.HomeController;
import com.menu.vo.DayMeal;
import com.menu.vo.MealDivision;
import com.menu.vo.MenuRecipe;
import com.menu.vo.WeekMenuTable;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MenuPage {
    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
    //jsp에 보낼 때 뭐가 필요할지 생각해보기.

    ArrayList<WeekMenuTable> weekMenuTableList = new ArrayList<>();//페이징한 WeekMenuTable수
    WeekMenuTable weekMenuTable = new WeekMenuTable();

    public MenuPage(WeekMenuTable weekMenuTable) {
        this.weekMenuTable = weekMenuTable;
        paging();
    }

    public WeekMenuTable getWeekMenuTable() {
        return weekMenuTable;
    }

    public void setWeekMenuTable(WeekMenuTable weekMenuTable) {
        this.weekMenuTable = weekMenuTable;
    }

    public ArrayList<WeekMenuTable> getWeekMenuTableList() {
        return weekMenuTableList;
    }

    public void setWeekMenuTableList(ArrayList<WeekMenuTable> weekMenuTableList) {
        this.weekMenuTableList = weekMenuTableList;
    }

    public ArrayList<WeekMenuTable> paging() {
        //최대 페이지 구하고 나서
        //totalPage가 잘못됐다고 했유ㅠㅠ
        //할당을하고 - 페이지 수만큼
        //페이징- 나머지값, 객체 수 증가

        int page = 1;//페이지
        int totalPage = 0;
        String[] mealString = new String[]{"조식", "중식", "석식", "간식"};//확인-테스트
        String[] dayString = new String[]{"월", "화", "수", "목", "금", "토", "일"};//확인-테스트

        WeekMenuTable weekMenuTable2 = new WeekMenuTable();
        int pageBlock = 20;//보여질 범위

        int cntB = 0;

        for (MealDivision md : weekMenuTable.getMdList()) {//4번(조식,중식,석식,간식)
            ArrayList<Integer> arr = new ArrayList<>();

            int i = 0;//확인하려고(테스트)

            //월-일 : 최대값(size) 구하려고
            for (DayMeal dayMeal : md.getDayMealList()) {//각 식사구분마다 7(월-일)번씩 돈다.
                logger.info(mealString[cntB] + "-" + dayString[i] + "요일별 재료 사이즈: " + dayMeal.getMenuRecipeList().size());
                i++;
                arr.add(dayMeal.getMenuRecipeList().size());//(음식:재료명)
            }

            int max = Collections.max(arr);//각 식사구분에 요일 리스트중 최대길이를 찾음

            int maxLine = 0;
            for (DayMeal dayMeal : md.getDayMealList()) {//월-일요일까지

                //max가 있어 조식에  그럼 이제 나머지 사이즈를 구해서 max에 나머지 + 20개씩 만들어서 수를 구해.
                //if(dayMeal.getMenuRecipeList().size() == max){
                    maxLine = maxLine - dayMeal.getMenuRecipeList().size();
                //}
                //dayMeal.getMenuRecipeList().size()
                //조식,중식,석식,간식에 총 길이를 구해서, 나눠야할듯, max 값을 구해서 그중 가장 큰값을 찾아서

            }
            logger.info(mealString[cntB] + " - 최대값 : " + max);

            //page += max / pageBlock;//페이지
            page += maxLine / pageBlock;//페이지

           /* if (max % pageBlock > 0) { //20보다 적은 행들도 출력해야해서
                page++;
            }*/
            //행 사이즈를 모두 확인한후 page 수 구하기

            //여기도 뭔가 잘못됐다구... 이렇게 totalPage를 만들게되면 안됨. 필요하지 않은 페이지가 늘어남

            logger.info(mealString[cntB] + " - page 수 : " + page);

            //totalPage += page; //수정해야함
            logger.info("page : " + page);
            cntB++;//확인하려고 만듬(테스트)
        }//md * 4

        for(int u=0; u<page+1; u++){
            WeekMenuTable weekMenuTableFor = new WeekMenuTable();
            weekMenuTableFor.getMdList().add(new MealDivision());
            weekMenuTableFor.getMdList().add(new MealDivision());
            weekMenuTableFor.getMdList().add(new MealDivision());
            weekMenuTableFor.getMdList().add(new MealDivision());

            weekMenuTableList.add(weekMenuTableFor); //이렇게 써야함.
        }

        int nowPage = 0;//현재페이지를 갖는 페이지변수 필요 21~40( BLOCK*1)
        int startIndx=0;//시작 인덱스

        for(int i=0; i<page; i++){
            WeekMenuTable weekMenuTable = new WeekMenuTable();//계속 생성해야함.

            //나머지 행들은 다음페이지에서 출력.(시작 수를 구해야함.)

             for (MealDivision md : weekMenuTable.getMdList()) {//한 페이지
                MealDivision mealDivisionNew = new MealDivision();

                int j = 0;//월-일 인덱스(위치중요)

                for (DayMeal dayMeal : md.getDayMealList()) {
                    DayMeal dayMealNew = new DayMeal();//레시피 리스트

                    //이제 시작값을 구해야한다.(k 값을 조건문을 만들어서 넣어줘야한다) - 없으면 0부터 시작되게 해야할듯
                    int remainderIndex=0;

                    //20개 이상 row면 페이지수*20 시작 인덱스
                    startIndx = remainderIndex==pageBlock?0:remainderIndex*(nowPage+1);

                    logger.info("dayMeal : "+dayMeal.getMenuRecipeList().size());
                    for (int k= startIndx; k < Math.min(dayMeal.getMenuRecipeList().size(), pageBlock); k++) {
                        dayMealNew.getMenuRecipeList().add(new MenuRecipe(dayMeal.getMenuRecipeList().get(k).getFoodName(), dayMeal.getMenuRecipeList().get(k).getIngredientsName()));
                        System.out.println(dayMeal.getMenuRecipeList().get(k).getFoodName()+" : "+ dayMeal.getMenuRecipeList().get(k).getIngredientsName());
                        remainderIndex++;
                    }

                    mealDivisionNew.getDayMealList().set(j, dayMealNew);//set(인덱스,값) 사용. - add() 사용하면 옆으로 늘어남.
                    j++;//월-일 인덱스

                    weekMenuTable.getMdList().add(mealDivisionNew);//조식,중식,석식,간식(한페이지)

                    if(dayMeal.getMenuRecipeList().size()>(nowPage+1)*pageBlock){
                        nowPage++;//현재페이지를 확인하기 위해서 (ex) 2*20 <-- 인덱스값에다가 더 하려고)
                        //다음 페이지에 남은 row를 넘기려고.
                        break;
                    }

                    if(nowPage > totalPage){
                        break;
                    }

                }//DayMeal
            }//md * 4
            weekMenuTableList.set(nowPage,weekMenuTable);//weekMenuTable이 여러개 생기니까 list에 담아줌.
        }//page 수 만큼 weekMenuTable2 생성

        return weekMenuTableList;
        }//큰 for문


        
    }

