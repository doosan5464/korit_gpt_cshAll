SHOW DATABASES;
use study;

select
	bt.book_id,
	bt.book_name,
	bt.author_id,
	bt.isbn,
	bt.category_id,
	bt.publisher_id,
	bt.book_img_url,

	at.author_id,
	at.author_name,

	ct.category_id,
	ct.category_name,

	pt.publisher_id,
	pt.publisher_name
from 
	book_tb bt
	left outer join author_tb at on at.author_id = bt.author_id
	left outer join category_tb ct on ct.category_id = bt.category_id
	left outer join publisher_tb pt on pt.publisher_id = bt.publisher_id;

