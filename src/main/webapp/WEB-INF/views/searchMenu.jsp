<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>메인화면</title>

    <style>
        #intro {
            border:1px solid #5D5D5D;
            position: absolute;
            width: 700px;
            height: 150px;
            top: 50%;
            left: 50%;
            padding: 5px;
            margin: -100px 0px 0px -330px;
        }
    </style>

    <script
            src="https://code.jquery.com/jquery-3.4.1.js"
            integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
            crossorigin="anonymous">
    </script>

    <script type="text/javascript">
        /*true,false로 배열에담아 모두 true면 전송.*/
        //var inval_Arr = new Array(4).fill(false);

        /*마지막요일 체크박스 체크하면, 입력이 안되는데 자동으로 시작일 기준으로 주간 보여지게 하기*/
        function checkStatus(){
            var endDate = $("#endDateId");
            var isCheckBox = $('#checkBoxId').is(":checked");
            if(!isCheckBox){
                endDate.attr("disabled",false);
                return false;
            }else{
                endDate.attr("disabled",true); //체크되어있으면
                return true;
            }
        };

        /*유효성검사 : 마지막날짜 체크박스 해제되어있는경우, 시작날짜 기준으로 그 주까지만 보여지게 만들기.
        /*startDate=2021-12-29&endDate=2022-01-26*/
        function checkStartDate(){
            var start =  $('#startDateId').val();//string
            var endResult ="";
            var sDate = new Date(start);
            var result = checkStatus();

            if(result==true){ // 체크박스가 눌러져있으면 true
                var sunday = sunDayFunction(sDate.getDay());//체크박스 눌르면 알아서 마지막 요일 계산되게 만들기 위해서

                //(년-월-일)
                var year= sDate.getFullYear();//number
                var month=(1+sDate.getMonth());
                month = month>=10? month:'0'+month;
                var day = sDate.getDate()+sunday; //그 주까지 보여지게 변경해야할듯....
                day = day>=10?day:'0'+day;//삼항연산자
                endResult = year+'-'+month+'-'+day;

                $('#endDateId').val(endResult);
            }

        };

        /*시작날짜를 기준으로 현재 요일에 일요일기간을 구해서 보내주기*/
         function sunDayFunction(startDay){
            var sunday = 0;

            if(startDay == 0){
                sunday =0;
            }else if(startDay == 1){
                sunday = 6;
            }else if(startDay == 2){
                sunday = 5;
            }else if(startDay == 3){
                sunday = 4;
            }else if(startDay == 4){
                sunday = 3;
            }else if(startDay == 5){
                sunday = 2;
            }else if(startDay == 6){
                sunday = 1;
            }

            return sunday;
         }

         function checkEndDate(){
             var start =  $('#startDateId').val();//값
             if (start === ""){
                 alert("시작날짜를 입력해주세요");
                 $('#startDateId').focus();
                 $('#endDateId').val("");
                return;
             }


             var sDate = new Date(start);//object
             //alert(sDate);
             //이번주 범위 구하려고
             //var monday = getWeekMondayDate(sDate);//해당주에 월요일,string
             var sunday = getWeekSunDayDate(sDate);//해당주에 일요일,string

             var sundayToDate = new Date(sunday);// string to object

             var end =  $('#endDateId').val();//값
             var eDate = new Date(end);//object
``

             /* //년-월-일 구하기
             var endResult ="";
             var dateDif = sunDayFunction(sDate.getDay());//시작날짜와 일요일까지 차이기간
             var year= sDate.getFullYear();
             var month=(1+sDate.getMonth());
             month = month>=10? month:'0'+month;
             var day = sDate.getDate(); //그 주까지 보여지게 변경해야할듯....
             day = day>=10?day:'0'+day;//삼항연산자
             endResult = year+'-'+month+'-'+day;

             $('#endDateId').val(endResult);*/

             if(sDate>eDate){//&& eDate>=changeDate
                 alert("시작날짜보다 앞을 선택할 수 없음");
                 $('#endDateId').val("");
                 /*주간을 벗어나면 자동으로 마지막 선택해줌*/
             }

             if (eDate>sundayToDate){//수정
                 alert("이번주 일요일날짜까지만 선택할 수 있음");
                 //$('#endDateId').val("");
             }
         }

        function getWeekMondayDate(d) {//해당일에 월요일 구하기
            var paramDate = new Date(d);

            var day = paramDate.getDay();
            var diff = paramDate.getDate() - day + (day == 0 ? -6 : 1);
            return new Date(paramDate.setDate(diff)).toISOString().substring(0, 10);
            //console.log(new Date(paramDate.setDate(diff)).toISOString().substring(0, 10));
        }

        function getWeekSunDayDate(d) {//해당일에 일요일 구하기
            var paramDate = new Date(d);

            var day = paramDate.getDay();
            //var diff = paramDate.getDate() - day + (day == 0 ? -7 : 0);
            var diff = paramDate.getDate() - day + (day == 0 ? -6 : 1)+6;
            return new Date(paramDate.setDate(diff)).toISOString().substring(0, 10);
            //console.log(new Date(paramDate.setDate(diff)).toISOString().substring(0, 10));
        }


         isPositive = function(num) {
         //음수면 false, 양수면 true
            return num >= 0;
        };



        function check_form(){//전송
            //해제 안하면 마지막날이 전송이 안됨.
            $("input[name=endDate]").attr("disabled", false);
            document.menuForm.submit();//전송


            <!--만약 기간이 둘다 null이면 현재날짜와 마지막날짜를 알아서 세팅-->


          /*  var form =document.menuForm;

            //false 하나로 있을시 제출못함.
            var validAll = true;

            for(var i=0; i<inval_Arr.length; i++){
                if(inval_Arr[i] == false){
                    validAll = false;;
                }
            }

            if(validAll){//유효성 모두 통과
                document.menuForm.submit();//전송
            }else{
                alert("기간을 다시 확인해주세요.");
            }*/
        }//check_form
    </script>

</head>
<body>

<div align="center" id="intro">
<h3>식단구성표</h3>
<h5 style="color: red;">원하시는 식당, 식사구분, 기간을 선택해주세요.</h5>
<form action="/printMenu.do" name="menuForm" method="get">
    식당:
    <select name="restaurantName" id="restaurantNameId" required>
        <c:forEach items="${menuCodeVo}" var="code">
            <c:set value="${code.codeId}" var="codeId"/>
                <c:if test="${fn:substring(codeId,0,1) eq 'R'}">
                    <option value="${code.codeNm}">${code.codeNm}</option>
                </c:if>
        </c:forEach>
    </select>

    식사구분:
    <select name="mealName" id="mealNameId" required>
        <c:forEach items="${menuCodeVo}" var="code">
            <c:set value="${code.codeId}" var="codeId"/>
                <c:if test="${fn:substring(codeId,0,1) eq 'M'}">
                    <option value="${code.codeNm}">${code.codeNm}</option>
               </c:if>
        </c:forEach>

    </select>

    기간:
    <input type="date" name="startDate" id="startDateId" oninput="checkStartDate()" required>
    ~
    <input type="date" name="endDate" id="endDateId" oninput="checkEndDate()" required>
    <input type="checkbox" id="checkBoxId" oninput="checkStatus()">

    <input type="button" value="전송" onclick="check_form()">
</form>
</div>
</body>
</html>
