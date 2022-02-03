<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false" pageEncoding="UTF-8"%>
<html>
<head>
	<meta charset="UTF-8">
	<title>Home</title>
</head>
<script
		src="https://code.jquery.com/jquery-3.4.1.js"
		integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
		crossorigin="anonymous"></script>
<script type="text/javascript">

	$.fn.mergeClassRowspan = function (colIdx) {
		return this.each(function () {
			var that;
			$('tr', this).each(function (row) {
				$('td:eq(' + colIdx + ')', this).filter(':visible').each(function (col) {

					if ($(this).attr('class') == $(that).attr('class')) {
						rowspan = $(that).attr("rowspan") || 1;
						rowspan = Number(rowspan) + 1;

						$(that).attr("rowspan", rowspan);

						// do your action for the colspan cell here
						$(this).hide();

						//$(this).remove();
						// do your action for the old cell here

					} else {
						that = this;
					}

					// set the that if not already set
					that = (that == null) ? this : that;
				});
			});
		});
	};

	$(function(){
		$('table tbody').mergeClassRowspan(1);
		$('table tbody').mergeClassRowspan(2);
	});
</script>
<body>

<table border="1" bordercolor="blue" width ="500" height="300" align = "center" style="table-layout:fixed;">
	<thead>
	<tr>
		<th>번호</th>
		<th>학년</th>
		<th>과목</th>
		<th>교재</th>
	</tr>
	</thead>
	<tbody>
	<tr>
		<td>1</td>
		<td class="grade_1">1학년</td>
		<td class="sub_ko">국어</td>
		<td>국어교재1</td>
	</tr>
	<tr>
		<td>1</td>
		<td class="grade_1">1학년</td>
		<td class="sub_ko">국어</td>
		<td>국어교재2</td>
	</tr>
	<tr>
		<td>1</td>
		<td class="grade_1">1학년</td>
		<td class="sub_en">영어</td>
		<td>영어교재1</td>
	</tr>
	<tr>
		<td>1</td>
		<td class="grade_1">1학년</td>
		<td class="sub_so">사회</td>
		<td>사회교재1</td>
	</tr>
	<tr>
		<td>1</td>
		<td class="grade_2">2학년</td>
		<td class="sub_ko">국어</td>
		<td>국어교재1</td>
	</tr>
	<tr>
		<td>1</td>
		<td class="grade_2">2학년</td>
		<td class="sub_en">영어</td>
		<td>영어교재1</td>
	</tr>
	</tbody>
</table>


<button onclick="location.href='/testSearch'">test</button>
<button onclick="location.href='/search'">조회</button>
<button onclick="location.href='/insertFood'">등록</button>

</body>
</html>
