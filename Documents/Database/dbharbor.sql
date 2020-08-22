

create table tblmodule(
 	pkid bigserial not null,
 	txtname varchar(100) not null,
 	primary key (pkid),
 	unique(txtname)
);

create table tblfile (
 	pkid bigserial not null,
 	txtfileid VARCHAR(64) not null,
 	txtname VARCHAR(200) not null,
 	fkmoduleid smallint not null,
 	dateupload bigint not null,
 	ispublic boolean not null default false,
 	primary key(pkid),
 	unique (txtfileid),
 	constraint positive_fkmoduleid check (fkmoduleid > 0),
 	constraint positive_pkid check(pkid > 0),
 	constraint fk_fkmoduleid foreign key(fkmoduleid) references tblmodule(pkid)
	
);
create index index_tblfile_dateupload on tblfile(dateupload);
create index index_tblfile_ispublic on tblfile(ispublic);
create index index_tblfile_fkmoduleid on tblfile(fkmoduleid);

create table tbluser(
	pkid bigserial not null,
  	lockversion bigint not null,
  	txtname varchar(200) not null,
  	txtemail varchar(200) default null,
  	txtcountrycode varchar(4) default null,
  	txtmobile varchar(15) not null,
  	txtverificationtoken varchar(64) not null,
  	isverificationtokenused boolean not null default false,
  	txtresetpasswordtoken varchar(64) default null,
  	isresetpasswordtokenused boolean not null default false,
  	dateresetpassword bigint default null,
  	txttwofactortoken varchar(16) default null,
  	istwofactortokenused boolean not null default false,
  	datetwofactortoken bigint default null, 
  	hasloggedin boolean not null default false,
  	numotp bigint null,
  	isotpused boolean not null default false,
	datesendotp bigint null,
	isclientadmin boolean not null default false,
	ismasteradmin boolean not null default false,
	fkprofilepicid bigint default null,
	fkcreateby bigint default null,
  	datecreate bigint not null,
  	fkupdateby bigint default null,
  	dateupdate bigint default null,
  	isactive boolean not null default false,
  	fkactchangeby bigint default null,
  	dateactchange bigint default null,
  	isarchive boolean not null default false,
  	fkarchiveby bigint default null,
  	datearchive bigint default null,
  	primary key(pkid),
  	unique(txtemail),
  	unique(txtmobile),
  	unique(txtverificationtoken),
  	unique(txtresetpasswordtoken),
  	constraint positive_pkid check(pkid > 0),
  	constraint positive_lockversion check(lockversion >= 0),
  	constraint positive_fkcreateby check (fkcreateby > 0),
  	constraint positive_fkupdateby check (fkupdateby > 0),
  	constraint positive_fkactchangeby check (fkactchangeby > 0),
  	constraint positive_fkarchiveby check (fkarchiveby > 0),
  	constraint positive_fkprofilepicid check (fkprofilepicid > 0),
  	constraint foreign_fkcreateby foreign key(fkcreateby) references tbluser(pkid),
  	constraint foreign_fkupdateby foreign key(fkupdateby) references tbluser(pkid),
  	constraint foreign_fkactchangeby foreign key(fkactchangeby) references tbluser(pkid),
  	constraint foreign_fkarchiveby foreign key(fkarchiveby) references tbluser(pkid),
  	constraint foreign_fkprofilepicid foreign key(fkprofilepicid) references tblfile(pkid)
);

create index index_tbluser_isverificationtokenused on tbluser(isverificationtokenused);
create index index_tbluser_isresetpasswordtokenused on tbluser(isresetpasswordtokenused);
create index index_tbluser_istwofactortokenused on tbluser(istwofactortokenused);
create index index_tbluser_fkcreateby on tbluser(fkcreateby);
create index index_tbluser_fkupdateby on tbluser(fkupdateby);
create index index_tbluser_fkactchangeby on tbluser(fkactchangeby);
create index index_tbluser_fkarchiveby on tbluser(fkarchiveby);
create index index_tbluser_isarchive on tbluser(isarchive);
create index index_tbluser_isactive on tbluser(isactive);
create index index_tbluser_txtemail on tbluser(txtemail);
create index index_tbluser_txtmobile on tbluser(txtmobile);
create index index_tbluser_hasloggedin on tbluser(hasloggedin);
create index index_tbluser_isclientadmin on tbluser(isclientadmin);
create index index_tbluser_ismasteradmin on tbluser(ismasteradmin);
create index index_tbluser_fkprofilepicid on tbluser(fkprofilepicid);


 create table tbluseraddress(
	pkid bigserial not null,
	fkuserid bigint not null,
	txtaddress text default null,
	txtpincode varchar(6) default null,
	fkcityid bigint not null,
	fkstateid bigint not null,
	fkcountryid bigint not null,
  	primary key(pkid),
  	constraint positive_pkid check(pkid > 0),
  	constraint positive_fkcountryid check (fkcountryid > 0),
  	constraint positive_fkstateid check (fkstateid > 0),
  	constraint positive_fkcityid check (fkcityid > 0),
  	constraint foreign_fkcountryid foreign key(fkcountryid) references tblcountry(pkid),
  	constraint foreign_fkstateid foreign key(fkstateid) references tblstate(pkid),
  	constraint foreign_fkcityid foreign key(fkcityid) references tblcity(pkid)
);

create index index_tbluseraddress_fkcountryid on tbluseraddress(fkcountryid);
create index index_tbluseraddress_fkstateid on tbluseraddress(fkstateid);
create index index_tbluseraddress_fkcityid on tbluseraddress(fkcityid);
create index index_tbluseraddress_fkuserid on tbluseraddress(fkuserid);
 
create table tbltimezone(
	pkid bigserial not null,
	txttimezone varchar(50) not null,
	primary key(pkid),
	unique(txttimezone)
);
insert into tbltimezone(txttimezone) values
('Africa/Abidjan'),('Africa/Accra'),('Africa/Addis_Ababa'),('Africa/Algiers'),('Africa/Asmara'),('Africa/Asmera'),('Africa/Bamako'),('Africa/Bangui'),
('Africa/Banjul'),('Africa/Bissau'),('Africa/Blantyre'),('Africa/Brazzaville'),('Africa/Bujumbura'),('Africa/Cairo'),('Africa/Casablanca'),('Africa/Ceuta'),
('Africa/Conakry'),('Africa/Dakar'),('Africa/Dar_es_Salaam'),('Africa/Djibouti'),('Africa/Douala'),('Africa/El_Aaiun'),('Africa/Freetown'),('Africa/Gaborone'),
('Africa/Harare'),('Africa/Johannesburg'),('Africa/Juba'),('Africa/Kampala'),('Africa/Khartoum'),('Africa/Kigali'),('Africa/Kinshasa'),('Africa/Lagos'),
('Africa/Libreville'),('Africa/Lome'),('Africa/Luanda'),('Africa/Lubumbashi'),('Africa/Lusaka'),('Africa/Malabo'),('Africa/Maputo'),('Africa/Maseru'),
('Africa/Mbabane'),('Africa/Mogadishu'),('Africa/Monrovia'),('Africa/Nairobi'),('Africa/Ndjamena'),('Africa/Niamey'),('Africa/Nouakchott'),('Africa/Ouagadougou'),
('Africa/Porto-Novo'),('Africa/Sao_Tome'),('Africa/Timbuktu'),('Africa/Tripoli'),('Africa/Tunis'),('Africa/Windhoek'),('America/Adak'),('America/Anchorage'),
('America/Anguilla'),('America/Antigua'),('America/Araguaina'),('America/Argentina/Buenos_Aires'),('America/Argentina/Catamarca'),('America/Argentina/ComodRivadavia')
,('America/Argentina/Cordoba'),('America/Argentina/Jujuy'),('America/Argentina/La_Rioja'),('America/Argentina/Mendoza'),('America/Argentina/Rio_Gallegos'),
('America/Argentina/Salta'),('America/Argentina/San_Juan'),('America/Argentina/San_Luis'),('America/Argentina/Tucuman'),('America/Argentina/Ushuaia'),
('America/Aruba'),('America/Asuncion'),('America/Atikokan'),('America/Atka'),('America/Bahia'),('America/Bahia_Banderas'),('America/Barbados'),('America/Belem'),
('America/Belize'),('America/Blanc-Sablon'),('America/Boa_Vista'),('America/Bogota'),('America/Boise'),('America/Buenos_Aires'),('America/Cambridge_Bay'),
('America/Campo_Grande'),('America/Cancun'),('America/Caracas'),('America/Catamarca'),('America/Cayenne'),('America/Cayman'),('America/Chicago'),('America/Chihuahua'),('America/Coral_Harbour'),('America/Cordoba'),('America/Costa_Rica'),('America/Creston'),('America/Cuiaba'),('America/Curacao'),('America/Danmarkshavn'),('America/Dawson'),('America/Dawson_Creek'),('America/Denver'),('America/Detroit'),('America/Dominica'),('America/Edmonton'),('America/Eirunepe'),('America/El_Salvador'),('America/Ensenada'),('America/Fort_Nelson'),('America/Fort_Wayne'),('America/Fortaleza'),('America/Glace_Bay'),('America/Godthab'),('America/Goose_Bay'),('America/Grand_Turk'),('America/Grenada'),('America/Guadeloupe'),('America/Guatemala'),('America/Guayaquil'),('America/Guyana'),('America/Halifax'),('America/Havana'),('America/Hermosillo'),('America/Indiana/Indianapolis'),('America/Indiana/Knox'),('America/Indiana/Marengo'),('America/Indiana/Petersburg'),('America/Indiana/Tell_City'),('America/Indiana/Vevay'),('America/Indiana/Vincennes'),('America/Indiana/Winamac'),('America/Indianapolis'),('America/Inuvik'),('America/Iqaluit'),('America/Jamaica'),('America/Jujuy'),('America/Juneau'),('America/Kentucky/Louisville'),('America/Kentucky/Monticello'),('America/Knox_IN'),('America/Kralendijk'),('America/La_Paz'),('America/Lima'),('America/Los_Angeles'),('America/Louisville'),('America/Lower_Princes'),('America/Maceio'),('America/Managua'),('America/Manaus'),('America/Marigot'),('America/Martinique'),('America/Matamoros'),('America/Mazatlan'),('America/Mendoza'),('America/Menominee'),('America/Merida'),('America/Metlakatla'),('America/Mexico_City'),('America/Miquelon'),('America/Moncton'),('America/Monterrey'),('America/Montevideo'),('America/Montreal'),('America/Montserrat'),('America/Nassau'),('America/New_York'),('America/Nipigon'),('America/Nome'),('America/Noronha'),('America/North_Dakota/Beulah'),('America/North_Dakota/Center'),('America/North_Dakota/New_Salem'),('America/Ojinaga'),('America/Panama'),('America/Pangnirtung'),('America/Paramaribo'),('America/Phoenix'),('America/Port-au-Prince'),('America/Port_of_Spain'),('America/Porto_Acre'),('America/Porto_Velho'),('America/Puerto_Rico'),('America/Punta_Arenas'),('America/Rainy_River'),('America/Rankin_Inlet'),('America/Recife'),('America/Regina'),('America/Resolute'),('America/Rio_Branco'),('America/Rosario'),('America/Santa_Isabel'),('America/Santarem'),('America/Santiago'),('America/Santo_Domingo'),('America/Sao_Paulo'),('America/Scoresbysund'),('America/Shiprock'),('America/Sitka'),('America/St_Barthelemy'),('America/St_Johns'),('America/St_Kitts'),('America/St_Lucia'),('America/St_Thomas'),('America/St_Vincent'),('America/Swift_Current'),('America/Tegucigalpa'),('America/Thule'),('America/Thunder_Bay'),('America/Tijuana'),('America/Toronto'),('America/Tortola'),('America/Vancouver'),('America/Virgin'),('America/Whitehorse'),('America/Winnipeg'),('America/Yakutat'),('America/Yellowknife'),('Antarctica/Casey'),('Antarctica/Davis'),('Antarctica/DumontDUrville'),('Antarctica/Macquarie'),('Antarctica/Mawson'),('Antarctica/McMurdo'),('Antarctica/Palmer'),('Antarctica/Rothera'),('Antarctica/South_Pole'),('Antarctica/Syowa'),('Antarctica/Troll'),('Antarctica/Vostok'),('Arctic/Longyearbyen'),('Asia/Aden'),('Asia/Almaty'),('Asia/Amman'),('Asia/Anadyr'),('Asia/Aqtau'),('Asia/Aqtobe'),('Asia/Ashgabat'),('Asia/Ashkhabad'),('Asia/Atyrau'),('Asia/Baghdad'),('Asia/Bahrain'),('Asia/Baku'),('Asia/Bangkok'),('Asia/Barnaul'),('Asia/Beirut'),('Asia/Bishkek'),('Asia/Brunei'),('Asia/Calcutta'),('Asia/Chita'),('Asia/Choibalsan'),('Asia/Chongqing'),('Asia/Chungking'),('Asia/Colombo'),('Asia/Dacca'),('Asia/Damascus'),('Asia/Dhaka'),('Asia/Dili'),('Asia/Dubai'),('Asia/Dushanbe'),('Asia/Famagusta'),('Asia/Gaza'),('Asia/Harbin'),('Asia/Hebron'),('Asia/Ho_Chi_Minh'),('Asia/Hong_Kong'),('Asia/Hovd'),('Asia/Irkutsk'),('Asia/Istanbul'),('Asia/Jakarta'),('Asia/Jayapura'),('Asia/Jerusalem'),('Asia/Kabul'),('Asia/Kamchatka'),('Asia/Karachi'),('Asia/Kashgar'),('Asia/Kathmandu'),('Asia/Katmandu'),('Asia/Khandyga'),('Asia/Kolkata'),('Asia/Krasnoyarsk'),('Asia/Kuala_Lumpur'),('Asia/Kuching'),('Asia/Kuwait'),('Asia/Macao'),('Asia/Macau'),('Asia/Magadan'),('Asia/Makassar'),('Asia/Manila'),('Asia/Muscat'),('Asia/Nicosia'),('Asia/Novokuznetsk'),('Asia/Novosibirsk'),('Asia/Omsk'),('Asia/Oral'),('Asia/Phnom_Penh'),('Asia/Pontianak'),('Asia/Pyongyang'),('Asia/Qatar'),('Asia/Qyzylorda'),('Asia/Rangoon'),('Asia/Riyadh'),('Asia/Saigon'),('Asia/Sakhalin'),('Asia/Samarkand'),('Asia/Seoul'),('Asia/Shanghai'),('Asia/Singapore'),('Asia/Srednekolymsk'),('Asia/Taipei'),('Asia/Tashkent'),('Asia/Tbilisi'),('Asia/Tehran'),('Asia/Tel_Aviv'),('Asia/Thimbu'),('Asia/Thimphu'),('Asia/Tokyo'),('Asia/Tomsk'),('Asia/Ujung_Pandang'),('Asia/Ulaanbaatar'),('Asia/Ulan_Bator'),('Asia/Urumqi'),('Asia/Ust-Nera'),('Asia/Vientiane'),('Asia/Vladivostok'),('Asia/Yakutsk'),('Asia/Yangon'),('Asia/Yekaterinburg'),('Asia/Yerevan'),('Atlantic/Azores'),('Atlantic/Bermuda'),('Atlantic/Canary'),('Atlantic/Cape_Verde'),('Atlantic/Faeroe'),('Atlantic/Faroe'),('Atlantic/Jan_Mayen'),('Atlantic/Madeira'),('Atlantic/Reykjavik'),('Atlantic/South_Georgia'),('Atlantic/St_Helena'),('Atlantic/Stanley'),('Australia/ACT'),('Australia/Adelaide'),('Australia/Brisbane'),('Australia/Broken_Hill'),('Australia/Canberra'),('Australia/Currie'),('Australia/Darwin'),('Australia/Eucla'),('Australia/Hobart'),('Australia/LHI'),('Australia/Lindeman'),('Australia/Lord_Howe'),('Australia/Melbourne'),('Australia/NSW'),('Australia/North'),('Australia/Perth'),('Australia/Queensland'),('Australia/South'),('Australia/Sydney'),('Australia/Tasmania'),('Australia/Victoria'),('Australia/West'),('Australia/Yancowinna'),('Brazil/Acre'),('Brazil/DeNoronha'),('Brazil/East'),('Brazil/West'),('CET'),('CST6CDT'),('Canada/Atlantic'),('Canada/Central'),('Canada/East-Saskatchewan'),('Canada/Eastern'),('Canada/Mountain'),('Canada/Newfoundland'),('Canada/Pacific'),('Canada/Saskatchewan'),('Canada/Yukon'),('Chile/Continental'),('Chile/EasterIsland'),('Cuba'),('EET'),('EST5EDT'),('Egypt'),('Eire'),('Etc/GMT'),('Etc/GMT+0'),('Etc/GMT+1'),('Etc/GMT+10'),('Etc/GMT+11'),('Etc/GMT+12'),('Etc/GMT+2'),('Etc/GMT+3'),('Etc/GMT+4'),('Etc/GMT+5'),('Etc/GMT+6'),('Etc/GMT+7'),('Etc/GMT+8'),('Etc/GMT+9'),('Etc/GMT-0'),('Etc/GMT-1'),('Etc/GMT-10'),('Etc/GMT-11'),('Etc/GMT-12'),('Etc/GMT-13'),('Etc/GMT-14'),('Etc/GMT-2'),('Etc/GMT-3'),('Etc/GMT-4'),('Etc/GMT-5'),('Etc/GMT-6'),('Etc/GMT-7'),('Etc/GMT-8'),('Etc/GMT-9'),('Etc/GMT0'),('Etc/Greenwich'),('Etc/UCT'),('Etc/UTC'),('Etc/Universal'),('Etc/Zulu'),('Europe/Amsterdam'),('Europe/Andorra'),('Europe/Astrakhan'),('Europe/Athens'),('Europe/Belfast'),('Europe/Belgrade'),('Europe/Berlin'),('Europe/Bratislava'),('Europe/Brussels'),('Europe/Bucharest'),('Europe/Budapest'),('Europe/Busingen'),('Europe/Chisinau'),('Europe/Copenhagen'),('Europe/Dublin'),('Europe/Gibraltar'),('Europe/Guernsey'),('Europe/Helsinki'),('Europe/Isle_of_Man'),('Europe/Istanbul'),('Europe/Jersey'),('Europe/Kaliningrad'),('Europe/Kiev'),('Europe/Kirov'),('Europe/Lisbon'),('Europe/Ljubljana'),('Europe/London'),('Europe/Luxembourg'),('Europe/Madrid'),('Europe/Malta'),('Europe/Mariehamn'),('Europe/Minsk'),('Europe/Monaco'),('Europe/Moscow'),('Europe/Nicosia'),('Europe/Oslo'),('Europe/Paris'),('Europe/Podgorica'),('Europe/Prague'),('Europe/Riga'),('Europe/Rome'),('Europe/Samara'),('Europe/San_Marino'),('Europe/Sarajevo'),('Europe/Saratov'),('Europe/Simferopol'),('Europe/Skopje'),('Europe/Sofia'),('Europe/Stockholm'),('Europe/Tallinn'),('Europe/Tirane'),('Europe/Tiraspol'),('Europe/Ulyanovsk'),('Europe/Uzhgorod'),('Europe/Vaduz'),('Europe/Vatican'),('Europe/Vienna'),('Europe/Vilnius'),('Europe/Volgograd'),('Europe/Warsaw'),('Europe/Zagreb'),('Europe/Zaporozhye'),('Europe/Zurich'),('GB'),('GB-Eire'),('GMT'),('GMT0'),('Greenwich'),('Hongkong'),('Iceland'),('Indian/Antananarivo'),('Indian/Chagos'),('Indian/Christmas'),('Indian/Cocos'),('Indian/Comoro'),('Indian/Kerguelen'),('Indian/Mahe'),('Indian/Maldives'),('Indian/Mauritius'),('Indian/Mayotte'),('Indian/Reunion'),('Iran'),('Israel'),('Jamaica'),('Japan'),('Kwajalein'),('Libya'),('MET'),('MST7MDT'),('Mexico/BajaNorte'),('Mexico/BajaSur'),('Mexico/General'),('NZ'),('NZ-CHAT'),('Navajo'),('PRC'),('PST8PDT'),('Pacific/Apia'),('Pacific/Auckland'),('Pacific/Bougainville'),('Pacific/Chatham'),('Pacific/Chuuk'),('Pacific/Easter'),('Pacific/Efate'),('Pacific/Enderbury'),('Pacific/Fakaofo'),('Pacific/Fiji'),('Pacific/Funafuti'),('Pacific/Galapagos'),('Pacific/Gambier'),('Pacific/Guadalcanal'),('Pacific/Guam'),('Pacific/Honolulu'),('Pacific/Johnston'),('Pacific/Kiritimati'),('Pacific/Kosrae'),('Pacific/Kwajalein'),('Pacific/Majuro'),('Pacific/Marquesas'),('Pacific/Midway'),('Pacific/Nauru'),('Pacific/Niue'),('Pacific/Norfolk'),('Pacific/Noumea'),('Pacific/Pago_Pago'),('Pacific/Palau'),('Pacific/Pitcairn'),('Pacific/Pohnpei'),('Pacific/Ponape'),('Pacific/Port_Moresby'),('Pacific/Rarotonga'),('Pacific/Saipan'),('Pacific/Samoa'),('Pacific/Tahiti'),('Pacific/Tarawa'),('Pacific/Tongatapu'),('Pacific/Truk'),('Pacific/Wake'),('Pacific/Wallis'),('Pacific/Yap'),('Poland'),('Portugal'),('ROK'),('Singapore'),('SystemV/AST4'),('SystemV/AST4ADT'),('SystemV/CST6'),('SystemV/CST6CDT'),('SystemV/EST5'),('SystemV/EST5EDT'),('SystemV/HST10'),('SystemV/MST7'),('SystemV/MST7MDT'),('SystemV/PST8'),('SystemV/PST8PDT'),('SystemV/YST9'),('SystemV/YST9YDT'),('Turkey'),('UCT'),('US/Alaska'),('US/Aleutian'),('US/Arizona'),('US/Central'),('US/East-Indiana'),('US/Eastern'),('US/Hawaii'),('US/Indiana-Starke'),('US/Michigan'),('US/Mountain'),('US/Pacific'),('US/Pacific-New'),('US/Samoa'),('UTC'),('Universal'),('W-SU'),('WET'),('Zulu');


create table tblclient(
 	pkid bigserial not null,
  	lockversion bigint not null,
  	txtname varchar(300) not null,
  	txtcountrycode varchar(4) default null,
  	txtmobile varchar(15) default null,
  	fklogoid bigint default null,
  	txtaddress text default null,
  	txtpincode varchar(6) default null,
  	fkcountryid bigint default null,
  	fkstateid bigint default null,
  	fkcityid bigint default null,
	txtapikey varchar(30) not null,
  	fkcreateby bigint default null,
  	datecreate bigint not null,
  	fkupdateby bigint default null,
  	dateupdate bigint default null,
  	isactive boolean not null default true,
  	fkactchangeby bigint default null,
  	dateactchange bigint default null,
  	isarchive boolean not null default false,
  	fkarchiveby bigint default null,
  	datearchive bigint default null,
	primary key(pkid),
  	unique(txtname),
  	unique(txtapikey),
  	constraint positive_pkid check(pkid > 0),
  	constraint positive_lockversion check(lockversion >= 0),
  	constraint positive_fklogoid check (fklogoid > 0),
  	constraint positive_fkcreateby check (fkcreateby > 0),
  	constraint positive_fkupdateby check (fkupdateby > 0),
  	constraint positive_fkactchangeby check (fkactchangeby > 0),
  	constraint positive_fkarchiveby check (fkarchiveby > 0),
  	constraint positive_fkcountryid check (fkcountryid > 0),
  	constraint positive_fkstateid check (fkstateid > 0),
  	constraint positive_fkcityid check (fkcityid > 0),
  	constraint foreign_fkcreateby foreign key(fkcreateby) references tbluser(pkid),
  	constraint foreign_fkupdateby foreign key(fkupdateby) references tbluser(pkid),
  	constraint foreign_fkactchangeby foreign key(fkactchangeby) references tbluser(pkid),
  	constraint foreign_fkarchiveby foreign key(fkarchiveby) references tbluser(pkid),
  	constraint foreign_fklogoid foreign key(fklogoid) references tblfile(pkid),
  	constraint foreign_fkcountryid foreign key(fkcountryid) references tblcountry(pkid),
  	constraint foreign_fkstateid foreign key(fkstateid) references tblstate(pkid),
  	constraint foreign_fkcityid foreign key(fkcityid) references tblcity(pkid)
);
create index index_tblclient_fkcreateby on tblclient(fkcreateby);
create index index_tblclient_fkupdateby on tblclient(fkupdateby);
create index index_tblclient_fkactchangeby on tblclient(fkactchangeby);
create index index_tblclient_fkarchiveby on tblclient(fkarchiveby);
create index index_tblclient_isarchive on tblclient(isarchive);
create index index_tblclient_isactive on tblclient(isactive);
create index index_tblclient_fklogoid on tblclient(fklogoid);
create index index_tblclient_fkcountryid on tblclient(fkcountryid);
create index index_tblclient_fkstateid on tblclient(fkstateid);
create index index_tblclient_fkcityid on tblclient(fkcityid);
create index index_tblclient_txtapikey on tblclient(txtapikey);

create table tblapp(
 	pkid smallint not null,
 	txtname varchar(100) not null,
 	txtfeature text default null,
 	primary key (pkid),
 	unique(txtname)
);

insert into tblapp(pkid,txtname) values(1,'Master Admin'),(2,'Client'),(3,'End User');


create table tblgroup(
	pkid smallint not null,
  	txtname varchar(30) not null,
	primary key(pkid),
  	unique(txtname),
  	constraint positive_pkid check(pkid > 0)
);

insert into tblgroup(pkid,txtname) values(1,'Master Admin'),(2,'Client Admin'),(3,'End User');


create table tblrole(
 	pkid bigserial not null,
  	lockversion bigint not null,
  	txtname varchar(30) not null,
  	txtdescription varchar(256) default null,
  	numbertotal bigint not null default 0,
  	numbertotalverified bigint not null default 0,
  	fkgroupid bigint not null,
  	fkappid bigint not null,
  	fkcreateby bigint default null,
  	datecreate bigint not null,
  	fkupdateby bigint default null,
  	dateupdate bigint default null,
  	isactive boolean not null default true,
  	fkactchangeby bigint default null,
  	dateactchange bigint default null,
  	isarchive boolean not null default false,
  	fkarchiveby bigint default null,
  	datearchive bigint default null,
	primary key(pkid),
	unique(txtname),
  	constraint positive_lockversion check(lockversion >= 0),
  	constraint positive_fkcreateby check (fkcreateby > 0),
  	constraint positive_fkupdateby check (fkupdateby > 0),
  	constraint positive_fkactchangeby check (fkactchangeby > 0),
  	constraint positive_fkarchiveby check (fkarchiveby > 0),
  	constraint positive_fkgroupid check (fkgroupid > 0),
  	constraint positive_fkappid check (fkappid > 0),
  	constraint foreign_fkcreateby foreign key(fkcreateby) references tbluser(pkid),
  	constraint foreign_fkupdateby foreign key(fkupdateby) references tbluser(pkid),
  	constraint foreign_fkactchangeby foreign key(fkactchangeby) references tbluser(pkid),
  	constraint foreign_fkarchiveby foreign key(fkarchiveby) references tbluser(pkid),
  	constraint foreign_fkgroupid foreign key(fkgroupid) references tblgroup(pkid),
  	constraint foreign_fkappid foreign key(fkappid) references tblapp(pkid)
);
create index index_tblrole_fkcreateby on tblrole(fkcreateby);
create index index_tblrole_fkupdateby on tblrole(fkupdateby);
create index index_tblrole_fkactchangeby on tblrole(fkactchangeby);
create index index_tblrole_fkarchiveby on tblrole(fkarchiveby);
create index index_tblrole_isarchive on tblrole(isarchive);
create index index_tblrole_isactive on tblrole(isactive);
create index index_tblrole_fkgroupid on tblrole(fkgroupid);
create index index_tblrole_fkappid on tblrole(fkappid);
create table tblrights(
 	pkid smallint not null,
 	txtname varchar(100) not null,
 	primary key (pkid),
 	unique (txtname),
 	constraint positive_pkid check(pkid > 0)
);

create table tblrolemoduleright(
	fkroleid bigint not null,
	fkmoduleid bigint not null,
	fkrightsid bigint not null,
	primary key(fkroleid, fkmoduleid, fkrightsid),
	constraint positive_fkroleid check (fkroleid > 0),
	constraint positive_fkmoduleid check (fkmoduleid > 0),
	constraint positive_fkrightsid check (fkrightsid > 0),
  	constraint foreign_fkroleid foreign key(fkroleid) references tblrole(pkid),
  	constraint foreign_fkmoduleid foreign key(fkmoduleid) references tblmodule(pkid),
  	constraint foreign_fkrightsid foreign key(fkrightsid) references tblrights(pkid)
);
create index index_tblrolemoduleright_fkroleid on tblrolemoduleright(fkroleid);
create index index_tblrolemoduleright_fkmoduleid on tblrolemoduleright(fkmoduleid);
create index index_tblrolemoduleright_fkrightsid on tblrolemoduleright(fkrightsid);





create table tblusersession(
	pkid bigserial not null,
	fkuserid bigint not null,
	txtsession varchar(100) not null,
	datecreate bigint not null,
	dateupdate bigint not null,
	txtbrowser varchar(100) default null,
	txtoperatingsystem varchar(500) default null,
	txtipaddress varchar(50) default null,
	txtdevicecookie varchar(100) not null,
	datedevicecookie bigint not null,
	istwofactorsession boolean not null default false,
	isresetpasswordsession boolean not null default false,
	isverifyotpsession boolean not null default false,
	primary key(pkid),
	unique (txtsession),
	constraint positive_pkid check (pkid > 0),
	constraint positive_fkuserid check (fkuserid > 0),
	constraint positive_datecreate check (datecreate > 0),
	constraint positive_dateupdate check (dateupdate > 0),
	constraint positive_datedevicecookie check (datedevicecookie > 0),
  	constraint foreign_fkuserid foreign key(fkuserid) references tbluser(pkid)
);

create index index_tblusersession_fkuserid on tblusersession(fkuserid);
create index index_tblusersession_datecreate on tblusersession(datecreate);
create index index_tblusersession_dateupdate on tblusersession(dateupdate);
create index index_tblusersession_txtbrowser on tblusersession(txtbrowser);
create index index_tblusersession_txtoperatingsystem on tblusersession(txtoperatingsystem);
create index index_tblusersession_datedevicecookie on tblusersession(datedevicecookie);
create index index_tblusersession_istwofactorsession on tblusersession(istwofactorsession);
create index index_tblusersession_isresetpasswordsession on tblusersession(isresetpasswordsession);
create index index_tblusersession_isverifyotpsession on tblusersession(isverifyotpsession);


create table tbluserpassword(
	pkid bigserial not null,
	fkuserid bigint not null,
	txtpassword varchar(1000) not null,
	datecreate bigint not null,
	primary key(pkid),
	constraint positive_pkid check (pkid > 0),
	constraint positive_fkuserid check (fkuserid > 0),
  	constraint foreign_fkuserid foreign key(fkuserid) references tbluser(pkid)
);
create index index_tbluserpassword_fkuserid on tbluserpassword(fkuserid);
create index index_tbluserpassword_datecreate on tbluserpassword(datecreate);

create table tblsetting(
	txtkey varchar(100) not null,
	txtvalue varchar(100) not null,
	enumdatatype int not null,
	primary key(txtkey),
	constraint positive_enumdatatype check(enumdatatype > 0)
);

insert into tblsetting(txtkey, txtvalue, enumdatatype) values
('FILE_PATH','/harbor-api', 2),
('TWO_FACTOR_AUTHENTICATION_ENABLED','0', 8),
('DEVICE_COOKIE_TIME_IN_SECONDS','31536000', 1),
('SESSION_INACTIVE_TIME_IN_MINUTES','60', 1),
('MAX_ALLOWED_DEVICE','10',1),
('DEFAULT_TIME_ZONE_ID','282',1),
('RESET_PASSWORD_TOKEN_VALID_MINUTES','1440', 1),
('PASSWORD_USED_VALIDATION_ENABLED','1', 8),
('MAX_PASSWORD_STORE_COUNT_PER_USER','5', 1),
('RESET_PASSWORD_SESSION_VALID_MINUTES','30', 1),
('CAPTCHA_IMAGE_PATH','/spaceez-api/captcha', 2);

create table tblemailaccount (
  pkid bigserial not null,
  lockversion bigint not null,
  fkclientid bigint default null,
  txtname varchar(100) not null,
  txthost varchar(500) not null,
  intport bigint null default 25,
  txtusername varchar(100) not null,
  txtpassword varchar(500)  not null,
  txtreplytoemail varchar(100)  not null,
  txtemailfrom varchar(500)  not null,
  intrateperhour bigint  default null,
  intupdaterateperhour bigint default null,
  intrateperday bigint  default null,
  intupdaterateperday bigint  default null,
  enumauthmethod smallint not null default 0/*0=plain, 1=demo, 2=cram md5*/,
  enumauthsecurity smallint not null default 0/*0=none, 1=use ssl, 2=use tls*/,
  inttimeout bigint not null default 60000,
  fkcreateby bigint not null,
  datecreate bigint not null,
  fkupdateby bigint default null,
  dateupdate bigint default null,
  isactive boolean not null default true,
  fkactchangeby bigint default null,
  dateactchange bigint default null,
  isarchive boolean not null default false,
  fkarchiveby bigint default null,
  datearchive bigint default null,
  primary key (pkid),
  constraint positive_pkid check(pkid > 0),
  constraint positive_lockversion check(lockversion >= 0),
  constraint positive_fkcreateby check (fkcreateby > 0),
  constraint positive_fkupdateby check (fkupdateby > 0),
  constraint positive_fkactchangeby check (fkactchangeby > 0),
  constraint positive_fkarchiveby check (fkarchiveby > 0),
  constraint positive_enumauthmethod check (enumauthmethod >= 0),
  constraint positive_enumauthsecurity check (enumauthsecurity >= 0),
  constraint foreign_fkcreateby foreign key(fkcreateby) references tbluser(pkid),
  constraint foreign_fkupdateby foreign key(fkupdateby) references tbluser(pkid),
  constraint foreign_fkactchangeby foreign key(fkactchangeby) references tbluser(pkid),
  constraint foreign_fkarchiveby foreign key(fkarchiveby) references tbluser(pkid),
  constraint foreign_fkclientid foreign key(fkclientid) references tblclient(pkid)
);

create index index_tblemailaccount_fkcreateby on tblemailaccount(fkcreateby);
create index index_tblemailaccount_fkupdateby on tblemailaccount(fkupdateby);
create index index_tblemailaccount_fkactchangeby on tblemailaccount(fkactchangeby);
create index index_tblemailaccount_fkarchiveby on tblemailaccount(fkarchiveby);
create index index_tblemailaccount_isarchive on tblemailaccount(isarchive);
create index index_tblemailaccount_isactive on tblemailaccount(isactive);
create index index_tblemailaccount_fkclientid on tblemailaccount(fkclientid);


create table tblemailcontent (
  pkid bigserial not null,
  lockversion bigint  not null,
  fkemailaccountId bigint  not null,
  txtname varchar(100) not null,
  txtsubject varchar(1000) not null,
  txtcontent text not null,
  txtemailcc text default null,
  txtemailBcc text default null,
  fktriggerid bigint not null,
  fkcreateby bigint default null,
  datecreate bigint not null,
  fkupdateby bigint default null,
  dateupdate bigint default null,
  isactive boolean not null default true,
  fkactchangeby bigint default null,
  dateactchange bigint default null,
  isarchive boolean not null default false,
  fkarchiveby bigint default null,
  datearchive bigint default null,
  fkclientid bigint default null,
  primary key (pkid),
  unique(fkclientid, fktriggerid),
  constraint positive_pkid check(pkid > 0),
  constraint positive_lockversion check(lockversion >= 0),
  constraint positive_fkcreateby check (fkcreateby > 0),
  constraint positive_fkupdateby check (fkupdateby > 0),
  constraint positive_fkactchangeby check (fkactchangeby > 0),
  constraint positive_fkarchiveby check (fkarchiveby > 0),
  constraint positive_fkemailaccountid check(fkemailaccountid > 0),
  constraint positive_fkclientid check(fkclientid > 0),
  constraint foreign_fkcreateby foreign key(fkcreateby) references tbluser(pkid),
  constraint foreign_fkupdateby foreign key(fkupdateby) references tbluser(pkid),
  constraint foreign_fkactchangeby foreign key(fkactchangeby) references tbluser(pkid),
  constraint foreign_fkarchiveby foreign key(fkarchiveby) references tbluser(pkid),
  constraint foreign_fkemailaccountid foreign key(fkemailaccountid) references tblemailaccount(pkid),
  constraint foreign_fkclientid foreign key(fkclientid) references tblclient(pkid)
);

create index index_tblemailcontent_fkcreateby on tblemailcontent(fkcreateby);
create index index_tblemailcontent_fkupdateby on tblemailcontent(fkupdateby);
create index index_tblemailcontent_fkactchangeby on tblemailcontent(fkactchangeby);
create index index_tblemailcontent_fkarchiveby on tblemailcontent(fkarchiveby);
create index index_tblemailcontent_fkemailaccountid on tblemailcontent(fkemailaccountid);
create index index_tblemailcontent_isarchive on tblemailcontent(isarchive);
create index index_tblemailcontent_isactive on tblemailcontent(isactive);
create index index_tblemailcontent_fktriggerid on tblemailcontent(fktriggerid);
create index index_tblemailcontent_fkclientid on tblemailcontent(fkclientid);

create table tbltransactionemail (
  	pkid bigserial not null,
  	lockversion bigint not null,
  	fkemailaccountid bigint  not null,
  	txtemailto text not null,
  	txtemailcc text,
  	txtemailbcc text,
  	txtsubject varchar(1000) not null,
  	txtbody text not null,
  	enumstatus smallint not null default 0/*0=new, 1=inprocess, 2=failed, 3=sent*/,
  	numberretrycount bigint not null default 0,
  	txtattachmentpath text,
  	txterror text,
  	datesend bigint default null,
  	datesent bigint default null,
  	primary key (pkid),
  	constraint positive_pkid check(pkid > 0),
  	constraint positive_lockversion check(lockversion >= 0),
  	constraint positive_enumstatus check (enumstatus >= 0),
  	constraint positive_fkemailaccountid check(fkemailaccountid > 0),
  	constraint foreign_fkemailaccountid foreign key(fkemailaccountid) references tblemailaccount(pkid)
);

create index index_tbltransactionemail_fkemailaccountid on tbltransactionemail(fkemailaccountid);
create index index_tbltransactionemail_enumstatus on tbltransactionemail(enumstatus);
create index index_tbltransactionemail_numberretrycount on tbltransactionemail(numberretrycount);
create index index_tbltransactionemail_datesend on tbltransactionemail(datesend);


create table tbluserrole(
	fkuserid bigint not null,
	fkroleid bigint not null,
  	primary key(fkuserid, fkroleid),
  	constraint positive_fkuserid check (fkuserid > 0),
  	constraint positive_fkroleid check (fkroleid > 0),
  	constraint foreign_fkuserid foreign key(fkuserid) references tbluser(pkid),
  	constraint foreign_fkroleid foreign key(fkroleid) references tblrole(pkid)
);
create index index_tbluserrole_fkuserid on tbluserrole(fkuserid);
create index index_tbluserrole_fkroleid on tbluserrole(fkroleid);


create table tbluserclient(
  	fkuserid bigint not null,
  	fkclientid bigint not null,
  	primary key(fkuserid,fkclientid),
   	constraint positive_fkuserid check (fkuserid > 0),
  	constraint positive_fkclientid check (fkclientid > 0),
  	constraint foreign_fkuserid foreign key(fkuserid) references tbluser(pkid),
  	constraint foreign_fkclientid foreign key(fkclientid) references tblclient(pkid)
);
create index index_tbluserclient_fkuserid on tbluserclient(fkuserid);
create index index_tbluserclient_fkclientid on tbluserclient(fkclientid);

/**
 * 22/08/2020
 */
ALTER TABLE tblclient ALTER COLUMN txtapikey TYPE varchar(64);