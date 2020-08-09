insert into tblemaildefaultcontent(txtname,txtsubject,txtcontent,fktriggerid)
	values('User Create','User Create','<p>Dear ${name},</p>
<p>Please find below your login credentials:</p>
<p><strong>Login Id : ${email}</strong></p>
<p><strong>Password : ${password}</strong></p>
<p>&nbsp;</p>',1);

insert into tblemaildefaultcontent(txtname,txtsubject,txtcontent,fktriggerid)
	values('Reset Password','Reset Password','<p>Dear ${name},<p>As requested, Please click on below link to reset your password.
</p><p><strong>Reset Password :&nbsp;${resetpasswordlink}</strong></p><p>&nbsp;</p>',2);