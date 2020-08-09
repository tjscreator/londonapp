insert into tblsmsaccount(lockversion,txtname,txtgroupid,txtrouteid,txtsenderid,txtsignature,txtcontenttype,txtauthKey,datecreate)
	values(0,'Harbor','0','1','DEMOOS','signature','english','ab956559dc4549e6cc875bb9f27750',1558072244);

insert into tblsmsdefaultcontent(txtname,txtsubject,txtcontent,fktriggerid)
	values('User Create','User Create','Dear ${name},
Hello Greeting from Harbor
Please find below your login credentials:
Login Id : ${username}
Password : ${password}',1);


insert into tblsmsdefaultcontent(txtname,txtsubject,txtcontent,fktriggerid)
	values('Reset Otp','Reset Otp','Dear ${name},
your OTP for reset password is ${otp}.',2);


insert into tblsmsdefaultcontent(txtname,txtsubject,txtcontent,fktriggerid)
	values('Hospital Create','Hospital Create','Dear ${name},
Hello Greeting from Harbor
Please find below your login credentials:
Login Id : ${username}
Password : ${password}',3);


insert into tblsmsdefaultcontent(txtname,txtsubject,txtcontent,fktriggerid)
	values('Login Otp','Login Otp','Dear ${name},
Hello Greeting from Harbor
Your one time password is ${otp}',4);


delete from tblsmscontent where fktriggerid=2;
delete from tblsmscontent where fktriggerid=3;

/*Default sms content of reset otp for harbor*/
insert into tblsmscontent(lockversion,fksmsaccountid,txtname,txtsubject,txtcontent,fktriggerid,datecreate,isactive,isarchive)
values(0,1,'Harbor','Reset Otp','Dear ${name},
Hello Greeting from Harbor
Your one time password is ${otp}',2,1566022851,true,false);

insert into tblsmscontent(lockversion,fksmsaccountid,txtname,txtsubject,txtcontent,fktriggerid,datecreate,isactive,isarchive)
values(0,1,'Harbor','Hospital Create','Dear ${name},
Hello Greeting from Harbor
Please find below your login credentials:
Login Id : ${username}
Password : ${password}',3,1566022851,true,false);


alter table tblsmscontent drop column txtname;


update tblsmscontent set txtsubject='Other User Reset Otp',
txtcontent='Dear ${name},
your OTP for changing the password is ${otp}.' where fktriggerid=2;

insert into tblsmscontent(lockversion,fksmsaccountid,txtsubject,txtcontent,fktriggerid,datecreate,isactive,isarchive)
values(0,1,'Hospital Admin User Reset Otp','Dear ${name},
 your OTP for changing the admin password is ${otp}.',5,1566022851,true,false);
 
 
 insert into tblsmsdefaultcontent(txtname,txtsubject,txtcontent,fktriggerid)
	values('Hospital Admin User Reset Otp','Hospital Admin User Reset Otp','Dear ${name},
 your OTP for changing the admin password is ${otp}.',5);

 
 
 insert into tblsmsdefaultcontent(txtname,txtsubject,txtcontent,fktriggerid)
	values('Doctor Confirmation OTP','Doctor Confirmation','Dear ${name},
You are add in new ${hospitalname} Hospital
your confirmation OTP is ${otp}.',6);

insert into tblsmscontent(lockversion,fksmsaccountid,txtsubject,txtcontent,fktriggerid,datecreate,isactive,isarchive)
values(0,1,'Doctor Confirmation OTP','Doctor Confirmation','Dear ${name},
You are add in new ${hospitalname} Hospital
your confirmation OTP is ${otp}.',6,1566022851,true,false);


insert into tblsmscontent(lockversion,fksmsaccountid,txtsubject,txtcontent,fktriggerid,datecreate,isactive,isarchive)
values(0,1,'Hospital Plan Payment','Dear ${name}, Plan payment failed of ${hospitalname}',7,1574843042,true,false);

 
/* live execute */
/*Local execute */