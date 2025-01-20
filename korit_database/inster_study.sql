insert into instructor_tb
	-- (instructor_id, instructor_name) 순서 괄호 생략 가능 --    
values
	(default, "김교수"),
	(default, "박교수"),
	(default, "이교수");
    
    
insert into course_tb
values
	(default, '데이터베이스론'),
	(default, '알고리즘'),
	(default, '운영체제'),
	(default, '자료구조');
    
    
insert into course_register_tb
values
	(default, 1, 2, '월', '2025-01-07', '2025-06-06', 2),
	(default, 1, 2, '목', '2025-01-07', '2025-06-06', 2),
	(default, 1, 3, '화', '2025-01-07', '2025-06-06', 2),
	(default, 1, 3, '수', '2025-01-07', '2025-06-06', 2),
	(default, 2, 1, '월', '2025-03-07', '2025-08-06', 1),
	(default, 2, 1, '수', '2025-03-07', '2025-08-06', 1),
	(default, 3, 1, '화', '2025-02-07', '2025-07-06', 1),
	(default, 3, 1, '금', '2025-02-07', '2025-07-06', 1),
	(default, 4, 3, '수', '2025-03-07', '2025-08-06', 2),
	(default, 4, 3, '금', '2025-03-07', '2025-08-06', 2);
    
    
insert into major_tb
values
	(default, '컴퓨터공학과'),
	(default, '소프트웨어공학과');
