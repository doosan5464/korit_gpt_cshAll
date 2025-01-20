insert into 
	student_tb 
values
	(default, '최석현', 3, '남', 1),
	(default, '백진우', 4, '남', 1),
	(default, '박소율', 3, '여', 2),
	(default, '정현영', 4, '여', 1);
    

insert into 
	course_registering_tb
values
	(default, 1, 1),
	(default, 1, 2),
	(default, 1, 3),
	(default, 1, 4),
	(default, 2, 2),
	(default, 2, 4),
	(default, 3, 1),
	(default, 3, 3),
	(default, 4, 1),
	(default, 4, 2),
	(default, 5, 1),
	(default, 5, 4),
	(default, 6, 1),
	(default, 6, 2),
	(default, 6, 3);
	

    

select 
	(select
		(select
			course_name
		 from
			course_tb as ct -- 여기선 원래대로 select의 필드에 맞게 그 table을 가져옴
		 where
			ct.course_id = crit.course_id -- 밖에 있는 crit을 의존
		)
	from
		course_registering_information_tb as crit -- 내부 서브쿼리가 외부 서브쿼리의 테이블(crit)을 참조하고 있으므로 이 구조가 동작하려면, 내부 서브쿼리는 반드시 외부 쿼리와 상관관계를 가져야 하고, 외부 쿼리에서 crit을 from 절에서 사용하고 있어야 함
    where
		crit.course_registering_information_id = crt.course_registering_information_id -- 밖에 있는 crt를 의존
	) as course_name -- 그냥 속성 이쁘게 만드는
from
	course_registering_tb as crt -- 그래서 밖에 있는 from이 as crt를 선언하려면 여기서 course_registering_tb를 써야 한다
    -- 상관 서브쿼리 : select문과 from의 테이블이 같지 않아도 된다
    -- 안쪽부터 볼게 아니라 바깥쪽부터 보면 from에서 crt를 지정하고 그 다음 외부 서브쿼리에 있는 where에 crt를 쓰면 그 외부 서브쿼리와 상관관계가 된다. 
    -- 또 그 외부 서브쿼리의 from에 있는 crit을 내부에 있는 서브쿼리의 where에 사용함으로써 또 다시 상관관계가 유지가 된다
    
where
	student_id = (select
					student_id
                  from
					student_tb
                  where
					student_name = '백진우');
    