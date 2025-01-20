select 
	row_number() over(order by `id`) as num,
	name_tb.* -- 그냥 *이 아니라 union한 테이블.*
from
	(select
		course_id as `id`,
        course_name as `name`
     from
		course_tb
     union
     select
		major_id,
		major_name
     from
		major_tb
    ) as name_tb;
    
    
    
    
    
select 
	rank() over(order by `id`) as num,
	name_tb.* 
from
	(select
		course_id as `id`,
        course_name as `name`
     from
		course_tb
     union
     select
		major_id,
		major_name
     from
		major_tb
    ) as name_tb;
    
    
    
    
    
select 
	dense_rank() over(order by `id`) as num,
	name_tb.* 
from
	(select
		course_id as `id`,
        course_name as `name`
     from
		course_tb
     union
     select
		major_id,
		major_name
     from
		major_tb
    ) as name_tb;
    
    
    
    
    
    
    




# where 와일드 카드(like)
select
	*
from
	instructor_tb
where
	instructor_name like '_교_';

-- % : 아무거나, _ : 한 글자
-- %교% : 앞 뒤 상관없이 교 찾음, %교 : 교로 끝나는, 교% : 교로 시작하는 
-- _교_ : 교 앞 뒤로 한글자만 있어야 찾음 
    
    
    
    
    
    
    
insert into 
	category_tb
select distinct
	0,	-- 자동으로 autoincrements
    카테고리
from
	books;
    
# author(저자), publisher(출판사)를 만들어서 정규화 해보시오

insert into 
	author_tb
select distinct
	0,	
    저자명
from
	books;
    
    
insert into 
	publisher_tb
select distinct
	0,	
    출판사
from
	books;
    
    
    
    
    
# 그룹함수 group by
select
	도서명,
    저자명,
    isbn,
    표지url
from
	books
group by -- 중복 제거
	도서명,
    저자명,
    isbn,
    표지url;
    