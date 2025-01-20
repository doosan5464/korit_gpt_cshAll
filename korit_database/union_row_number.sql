# Select

select
	*
from
	student_tb;
    
    
select
	student_id,
    student_name
from
	student_tb;    
    
# where 조건
select
	*
from
	student_tb
where
	-- student_year = 3  or student_year = 4
    -- student_year > 2 and student_year < 5;
    -- student_year >= 3 and student_year <= 4;
    -- student_year in(3,4);
    student_id between 2 and 4;
    

select 
10 as num1,
20 as num2,
30 as num3
union all--
select 
10 as num1,
20 as num2,
'이름' as name;


select
	2 as id,
    '월' as day
union all
select
	3 as id,
    '수' as day;
    
    
select
	student_name
from
	student_tb
union
select
	instructor_name
from
	instructor_tb;
    
-- 1 2 3 4 ...를 주려면?

select
	row_number() over(order by student_name) as num, -- row_number() : 각 행에 연속적인 숫자를 부여, over(oreder by ~~~) : 정렬하고자 하는 속성. 
	student_name
from
	student_tb;


select
	*
from
	student_tb
order by -- order by : 정렬(asc 기본 오름차), 속성 뒤에 desc를 붙이면 내림차로 정렬
	student_year desc,
    student_name;



-- union에서의 row_number() over(oredr by) 는?

select
	row_number() over(order by name) as 'index', -- index는 키워드라 ' '처리
	name
from -- 이렇게 하면 합친 테이블을 하나의 테이블로 보고, as name으로 줬던 별칭도 하나로 묶인다
	(select
		student_name as name
	from
		student_tb
	union
	select
		instructor_name
	from
		instructor_tb
    ) as name_tb; -- 지금은 안쓰지만 나중에 이 합친 테이블을 참조할 수 있기때문에 미리 as


    
