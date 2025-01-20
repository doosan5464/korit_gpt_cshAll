# row_number(), rank() 에서의 partition by

select -- partition by : 지정된 컬럼을 기준으로 데이터를 나눔
	row_number() over(partition by category_id order by author_id), -- category_id를 기준으로 row_number 하게 됨
    book_tb.*
from
	book_tb
where
	book_name like '%가%';
    
    
# 흐름제어
# CASE, IF, IFNULL, NULLIF
select
	case -- case when then else end: 조건문 when 중에 참인게 있으면 then 출력, 없으면 else 출력
		when 5 > 5 then '5보다 큽니다' 
		when 5 > 4 then '4보다 큽니다' 
		when 5 > 3 then '3보다 큽니다' -- 여긴 출력이 안되는데 
        else '5보다 작습니다' 
	end,
    if(10 > 5, '5보다 큽니다', '5보다 작습니다');
    
    
update
	book_tb
set
	category_id = null
where
	category_id between 40 and 49;
    
    
select
	*,
    if(trim(isbn) = '', 'O', 'X') as oAndX, -- trim() : 양 끝에 공백 제거 함수
	case
		when category_id < 100 then '0 ~ 99'
		when category_id < 200 then '100 ~ 199'
		when category_id < 300 then '200 ~ 299'
		else '300 ~'
    end as scope,
    ifnull(category_id, '40') as ifnull, -- 탐색한 열이 null이면 값으로 만듦
    nullif(category_id, 37) as nullif -- 값과 같으면 null로 만듦
from
	book_tb;
    
    

    
    
update
	book_tb
set
	category_id = case 
		when category_id % 3 = 0 then 3000
        when category_id % 2 = 0 then 2000
    end;
    
    
select
	*
from
	book_tb;
    
    
select
	*
from
	book_tb
where
	category_id is not null; -- null값이 아니라면
    
    
update
	book_tb
set
	category_id = ifnull(category_id, 4000);
    
    
# view 테이블
create view book_view as (
	select
	bt.book_id,
    bt.book_name,
    bt.isbn,
    bt.author_id,
    `at`.author_id as at_author_id,
    `at`.author_name,
    bt.publisher_id,
    pt.publisher_id as pt_publisher_id,
    pt.publisher_name,
    bt.category_id,
    ct.category_id as ct_category_id,
    ct.category_name,
    bt.book_img_url
from
	book_tb bt
    left outer join author_tb `at` on `at`.author_name = bt.author_id
    left outer join category_tb ct on ct.category_name = bt.category_id
    left outer join publisher_tb pt on pt.publisher_name = bt.publisher_id
);


select
	*
from
	book_view;
    

# with 공통 테이블 표현식(common table expression, cte)
set @searchData = '불멸'; -- set @변수명 = '' : 변수 생성

with 
	publisher_count_cte as ( -- 어디 저장되지는 않고 select에 임시로 사용됨. 서브쿼리 대신 사용
		select
			publisher_id,
			count(publisher_id) as publisher_count
		from
			book_tb
		group by
			publisher_id
	),
	author_count_cte as ( -- 어디 저장되지는 않고 select에 임시로 사용됨. 서브쿼리 대신 사용
		select
			author_id,
			count(author_id) as author_count
		from
			book_tb
		group by
			author_id
	)
    
    
select
	*
from
	book_tb bt
    left outer join publisher_count_cte pcc on(pcc.publisher_id = bt.publisher_id)
    left outer join author_count_cte acc on(acc.author_id = bt.author_id)
where
	bt.book_name like concat('%', @searchData, '%');
 
 
# lpad : 왼쪽에 특정문자를 원하는 자리수만큼 채워서 반환, rpad는 반대
select
	lpad('123', 5, 0);

# 날짜 관련. now()
select
	year(now()),
    month(now()),
    month('2022-08-18'), -- 8 출력
	day(now()),
    hour(now()),
    minute(now()),
    second(now());
    
select
	date_format(now(), '%Y년 %m월 %d일');
    
delete
from
	book_tb
where
	book_id = 1;
    