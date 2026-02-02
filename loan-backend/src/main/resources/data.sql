Insert into Customer(name,dob,cibil) values 
		('Devender','2000-9-23',850),
		('Manu','1999-9-23',810);
Insert into Loan(customer_id,type,date,total,interest_rate,emi,status) values 
		(1,'PERSONAL_LOAN','2018-7-26',600000,12,20000,'ACTIVE'),
		(1,'HOME_LOAN','2019-7-26',5000000,8.5,35000,'ACTIVE'),
		(1,'CAR_LOAN','2020-7-26',300000,11,18000,'ACTIVE'),
		(2,'PERSONAL_LOAN','2014-7-26',200000,12.5,22000,'CLOSED'),
		(2,'PERSONAL_LOAN','2015-7-26',100000,10.99,18000,'CLOSED'),
		(2,'CAR_LOAN','2016-7-26',800000,11.5,20000,'CLOSED'),
		(2,'CAR_LOAN','2017-7-26',700000,11.2,15000,'ACTIVE');