


insert into tblrole(lockversion,txtname,fkgroupid,fkappid,datecreate,isactive) 
values(0,'Master Admin',1,1,1598120311,true),(0,'Client Admin',2,2,1598120311,true),(0,'End User',3,3,1598120311,true);
 

insert into tbluser
(lockversion,txtname,txtemail,txtcountrycode,txtmobile,txtverificationtoken,
 ismasteradmin,isactive,datecreate)
values
(0,'Master Admin','master@admin.com','+91','8798596858','46e243cf396447b5942ca10d09e62d8c',
true,true,1598161158);
/* For Live
insert into tbluserrole values(1,1) 

insert into tbluserpassword(fkuserid,txtpassword,datecreate)
values(1,'$2a$11$OgZl0bV6NodIe3rj24bLYuctjSmgSIoIUJBwJKwsOF52shAKnPrwm',1598161158);


insert into tblemailaccount 
(lockversion,txtname,txthost,intport,txtusername,txtpassword,txtreplytoemail,txtemailfrom,fkcreateby,datecreate)

values(0,'Test Account','Email Account',23,'test','test','test@gmail.com','test@gmail.com',1,1598188036)
*/


insert into tbluserrole values(8,3);

insert into tbluserpassword(fkuserid,txtpassword,datecreate)
values(8,'$2a$11$OgZl0bV6NodIe3rj24bLYuctjSmgSIoIUJBwJKwsOF52shAKnPrwm',1598161158)


insert into tblemailaccount 
(lockversion,txtname,txthost,intport,txtusername,txtpassword,txtreplytoemail,txtemailfrom,fkcreateby,datecreate)

values(0,'Test Account','Email Account',23,'test','test','test@gmail.com','test@gmail.com',8,1598188036)



insert into tblemailcontent
(lockversion,fkemailaccountid,txtname,txtsubject,txtcontent,fktriggerid,isactive,datecreate)
values(0,6,'Client create by admin','Client create by admin','${username},${email},${password},${url},${client}',
	  1,true,1598188340);
	  
	  insert into tblemailcontent
(lockversion,fkemailaccountid,txtname,txtsubject,txtcontent,fktriggerid,isactive,datecreate)
values(0,6,'Reset password','Reset password','${username},${url}',
	  2,true,1598188340)
