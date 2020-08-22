 USE dbharbor;
 
insert into tblrole(lockversion,txtname,fkgroupid,fkappid,datecreate,isactive) 
values(0,'Master Admin',1,1,1598120311,true),(0,'Client Admin',2,2,1598120311,true),(0,'End User',3,3,1598120311,true);
 
insert into tblrolemoduleright values(1,1,1),(1,1,2),(1,1,3),(1,1,4),(1,1,5),(1,1,6);
insert into tblrolemoduleright values(1,2,1),(1,2,2),(1,2,3),(1,2,4),(1,2,5),(1,2,6);
insert into tblrolemoduleright values(1,3,2),(1,3,3),(1,3,6);
insert into tblrolemoduleright values(1,5,1),(1,5,2),(1,5,3),(1,5,4),(1,5,5),(1,5,6);
insert into tblrolemoduleright values(1,6,1),(1,6,2),(1,6,3),(1,6,4),(1,6,5),(1,6,6);
insert into tblrolemoduleright values(1,7,1),(1,7,2),(1,7,3),(1,7,4),(1,7,5),(1,7,6);
insert into tblrolemoduleright values(1,9,1),(1,9,2),(1,9,3),(1,9,4),(1,9,5),(1,9,6);

insert into tblrolemoduleright values(3,8,1),(3,8,2),(3,8,3),(3,8,4),(3,8,5),(3,8,6);

insert into tblrolemoduleright values(1,20,2),(1,20,6);
insert into tblrolemoduleright values(1,21,2),(1,21,6);



/*
	below three script run after start the server
*/
insert into tblrolegroup values(1,1);
insert into tbluserrole values(1,1);
update tblrole set fkappid=4 where pkid=1;

/*Local execute */
/* live execute */

