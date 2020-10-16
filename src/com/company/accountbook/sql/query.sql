create table report (
	report_id int not null auto_increment primary key,
    is_income boolean,
    payment_method varchar(20),
    category varchar(50),
    price int,
    content varchar(100),
    account_book_name varchar(20),
    day int,
    month int,
    year int
);