select                   -- select인 이유 : 물리적 데이터 저장 공간?
	*					 -- from - where - select 
from 
	course_register_tb
where					 -- where : 조건문, '' 
	instructor_id = (select		-- 서브쿼리
						instructor_id
					from
						instructor_tb
					where
						instructor_name = '박교수'
					);
    
    
select
	course_register_id,
	course_id as courseid,
    instructor_id,
    (select 
		instructor_name 
    from 
		instructor_tb 
    where 
		instructor_tb.instructor_id = course_register_tb.instructor_id) as instructor_name
        
from
	course_register_tb;

    
    
