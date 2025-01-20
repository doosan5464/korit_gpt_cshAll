insert into data2_tb
values
(default, '111'),
(default, '222'),
(default, '333'),
(default, '444'),
(default, '555');

select
	*
from
	data2_tb;




insert into data1_tb
values
(default, 'aaa', 1),
(default, 'bbb', 1),
(default, 'ccc', 2),
(default, 'ddd', 3),
(default, 'eee', 4),
(default, 'fff', 5),
(default, 'ggg', 5);


insert into data1_tb
values
(default, 'hhh', 6),
(default, 'iii', 7);


select
	*
from
	data1_tb
    left outer join data2_tb on data2_tb.data2_id = data1_tb.data2_id;
    
-- inner vs left outer 
-- : inner은 조건이 충족되는 애들만. left outer은 왼쪽에 있는 테이블은 다 출력. 그 다음 값 테이블은 조건에 없으면 NULL 

select
	*
from
	data1_tb
    inner join data2_tb on data2_tb.data2_id = data1_tb.data2_id;
    
    
update
	data1_tb
set
	data2_id = 10
where
	data1_id in (5,7);
    
select
	*
from
	data1_tb;
    
    

