CREATE DATABASE dbharbor
	DEFAULT CHARACTER SET utf8
	DEFAULT COLLATE utf8_general_ci;
    
    USE dbharbor;
 
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
  	numotp bigint(8) null,
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
  	unique(txtverificationotp),
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
create index index_tbluser_isverificationotpused on tbluser(isverificationotpused);
create index index_tbluser_hasloggedin on tbluser(hasloggedin);
create index index_tbluser_isclientadmin on tbluser(isclientadmin);
create index index_tbluser_ismasteradmin on tbluser(ismasteradmin);
create index index_tbluser_fkprofilepicid on tbluser(fkprofilepicid);


 create table tbluseraddress(
	pkid bigserial not null,
	fkuserid bigint not null,
	txtaddress varchar(1000) default null,
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
	pkid bigint not null,
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
	fktimezoneid bigint not null,
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
  	constraint positive_fktimezoneid check (fktimezoneid > 0),
  	constraint foreign_fkcreateby foreign key(fkcreateby) references tbluser(pkid),
  	constraint foreign_fkupdateby foreign key(fkupdateby) references tbluser(pkid),
  	constraint foreign_fkactchangeby foreign key(fkactchangeby) references tbluser(pkid),
  	constraint foreign_fkarchiveby foreign key(fkarchiveby) references tbluser(pkid),
  	constraint foreign_fklogoid foreign key(fklogoid) references tblfile(pkid),
  	constraint foreign_fkcountryid foreign key(fkcountryid) references tblcountry(pkid),
  	constraint foreign_fkstateid foreign key(fkstateid) references tblstate(pkid),
  	constraint foreign_fktimezoneid foreign key(fktimezoneid) references tbltimezone(pkid),
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
create index index_tblclient_fktimezoneid on tblclient(fktimezoneid);
create index index_tblclient_txtapikey on tblclient(txtapikey);


create table tblclientbranch(
 	pkid bigserial not null,
 	lockversion bigint not null,
  	txtname varchar(300) not null,
  	txtcountrycode varchar(4) default null,
  	txtmobile varchar(15) default null,
  	fkclientid bigint not null,
  	txtaddress text default null,
  	txtpincode varchar(6) default null,
  	fkcountryid bigint default null,
  	fkstateid bigint default null,
  	fkcityid bigint default null,
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
  	constraint positive_lockversion check(lockversion >= 0),
  	constraint positive_fkcreateby check (fkcreateby > 0),
  	constraint positive_fkupdateby check (fkupdateby > 0),
  	constraint positive_fkactchangeby check (fkactchangeby > 0),
  	constraint positive_fkarchiveby check (fkarchiveby > 0),
  	constraint positive_fkcountryid check (fkcountryid > 0),
  	constraint positive_fkstateid check (fkstateid > 0),
  	constraint positive_fkcityid check (fkcityid > 0),
  	constraint positive_fkclientid check (fkclientid > 0),
  	constraint foreign_fkcreateby foreign key(fkcreateby) references tbluser(pkid),
  	constraint foreign_fkupdateby foreign key(fkupdateby) references tbluser(pkid),
  	constraint foreign_fkactchangeby foreign key(fkactchangeby) references tbluser(pkid),
  	constraint foreign_fkarchiveby foreign key(fkarchiveby) references tbluser(pkid),
  	constraint foreign_fkcountryid foreign key(fkcountryid) references tblcountry(pkid),
  	constraint foreign_fkstateid foreign key(fkstateid) references tblstate(pkid),
  	constraint foreign_fkclientid foreign key(fkclientid) references tblclient(pkid),
  	constraint foreign_fkcityid foreign key(fkcityid) references tblcity(pkid)
);
create index index_tblclientbranch_fkcreateby on tblclientbranch(fkcreateby);
create index index_tblclientbranch_fkupdateby on tblclientbranch(fkupdateby);
create index index_tblclientbranch_fkactchangeby on tblclientbranch(fkactchangeby);
create index index_tblclientbranch_fkarchiveby on tblclientbranch(fkarchiveby);
create index index_tblclientbranch_isarchive on tblclientbranch(isarchive);
create index index_tblclientbranch_isactive on tblclientbranch(isactive);
create index index_tblclientbranch_fkcountryid on tblclientbranch(fkcountryid);
create index index_tblclientbranch_fkstateid on tblclientbranch(fkstateid);
create index index_tblclientbranch_fkclientid on tblclientbranch(fkclientid);
create index index_tblclientbranch_fkcityid on tblclientbranch(fkcityid);

create table tblgroup(
	pkid bigserial not null,
  	txtname varchar(30) not null,
	primary key(pkid),
  	unique(txtname)
);


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



create table tblmodule(
 	pkid int(4) unsigned not null auto_increment,
 	txtname varchar(100) not null,
 	primary key (pkid),
 	unique key tblmodule_txtname (txtname)
)engine=innodb default charset=utf8;

insert into tblmodule values(1, 'User');

create table tblfile (
 	pkid int(10) unsigned not null auto_increment,
 	txtfileid varchar(64) not null,
 	txtname varchar(200) not null,
 	fkmoduleid int(10) unsigned not null,
 	dateupload int(10) not null,
 	ispublic bit(1) default 0,
 	primary key(pkid),
 	unique key tblfile_txtfileid (txtfileid),
 	key tblfile_dateupload (dateupload),
 	key tblfile_ispublic (ispublic),
 	constraint tblfile_fkmoduleid foreign key(fkmoduleid) references tblmodule(pkid)
) engine=innodb default charset=utf8;

 create table tblhospital(
 	pkid int(10) unsigned not null auto_increment,
 	lockversion int(10) unsigned not null,
    txtname varchar(200) not null,
  	txtemail varchar(100) null,
    txtcommunicationemail varchar(256) not null,
  	txtmobilenumber varchar(15) not null,
  	txtapikey varchar(100) not null,
  	txtwebsite varchar(2056) default null,
  	fklogoid int(10) unsigned not null,
    datecreate int(10) not null,
    dateupdate int(10) default null,
    isarchive bit(1) not null default 0,
    isactive bit(1) not null default 1,
    datearchive int(10) default null,
    fkcreateby int(10) unsigned default null,
    fkupdateby int(10) unsigned default null,
    fkactchangeby int(10) unsigned default null,
    fkarchiveby int(10) unsigned default null,
    dateactchange int(10) default null,
	primary key (pkid),
	unique key txtname (txtname),
	unique key txtemail (txtemail),
	key tblhospital_fkcreateby (fkcreateby),
 	key tblhospital_fkupdateby (fkupdateby),
 	key tblhospital_fkactchangeby (fkactchangeby),
	key tblhospital_fkarchiveby (fkarchiveby),
	key tblhospital_txtapikey (txtapikey),
	constraint tblhospital_fkcreateby foreign key (fkcreateby) references tbluser (pkid),
  	constraint tblhospital_fkupdateby foreign key (fkupdateby) references tbluser (pkid),
  	constraint tblhospital_fkactchangeby foreign key (fkactchangeby) references tbluser (pkid),
  	constraint tblhospital_fkarchiveby foreign key (fkarchiveby) references tbluser (pkid),
  	constraint tblhospital_fklogoid foreign key(fklogoid) references tblfile(pkid)
) engine=innodb default charset=utf8;

create table tblrole(
 	pkid int(10) unsigned not null auto_increment,
  	lockversion int(10) unsigned not null,
  	txtname varchar(30) not null,
  	txtdescription varchar(256) default null,
  	numbertotal int(10) not null default 0,
  	numbertotalverified int(10) not null default 0,
  	fkcreateby int(10) unsigned default null,
  	datecreate int(10) not null,
  	fkupdateby int(10) unsigned default null,
  	dateupdate int(10) default null,
  	isactive bit(1) not null default 0,
  	fkactchangeby int(10) unsigned default null,
  	dateactchange int(10) default null,
  	isarchive bit(1) not null default 0,
  	fkarchiveby int(10) unsigned default null,
  	datearchive int(10) default null,
	primary key(pkid),
  	key tblrole_fkcreateby(fkcreateby),
  	key tblrole_fkupdateby(fkupdateby),
  	key tblrole_fkactchangeby(fkactchangeby),
  	key tblrole_fkarchiveby(fkarchiveby),
  	key tblrole_isarchive(isarchive),
  	key tblrole_isactive(isactive),
  	constraint tblrole_fkcreateby foreign key(fkcreateby) references tbluser(pkid),
  	constraint tblrole_fkupdateby foreign key(fkupdateby) references tbluser(pkid),
  	constraint tblrole_fkactchangeby foreign key(fkactchangeby) references tbluser(pkid),
  	constraint tblrole_fkarchiveby foreign key(fkarchiveby) references tbluser(pkid)
)engine=innodb default charset=utf8;

insert into tblrole values (1, 0, 'admin', null, 0, 0, null, 1531724651, null, null, 1, null, null, 0, null, null);

alter table tblrole
add column fkhospitalid int(10) unsigned NULL;

alter table tblrole
add constraint tblrole_fkhospitalid
foreign key (fkhospitalid)
references tblhospital(pkid);

alter table tbluser
add column fkroleid int(10) unsigned NOT NULL;

alter table tbluser
add constraint tbluser_fkroleid
foreign key (fkroleid)
references tblrole(pkid);
 

insert into tblfile values(1,'36e011289251462aa719956006f556c5','IA.png',1,1545312410,true);

 insert into tbluser values
 (1,0,'Admin','Harbor','admin@harbor.com','91','9924538714','verificationtoken', 
 1,null,0,null,null,0,null,0,null,1555941210,null,null,1,null,null,0,null,null,1);

alter table tbluser
add column fkhospitalid int(10) unsigned NULL;

alter table tbluser
add constraint tbluser_fkhospitalid
foreign key (fkhospitalid)
references tblhospital(pkid);

create table tblusersession(
	pkid int(10) unsigned not null auto_increment,
	fkuserid int(10) unsigned not null,
	txtsession varchar(100) not null,
	datecreate int(10) not null,
	dateupdate int(10) not null,
	txtbrowser varchar(100) default null,
	txtoperatingsystem varchar(500) default null,
	txtipaddress varchar(50) default null,
	txtdevicecookie varchar(100) not null,
	datedevicecookie int(10) not null,
	istwofactorsession bit(1) not null default 0,
	isresetpasswordsession bit(1) not null default 0,
	primary key(pkid),
	unique key txtsession (txtsession),
	unique key txtdevicecookie (txtdevicecookie),
	key tblusersession_fkuserid (fkuserid),
	constraint positive_pkid check (pkid >= 0),
	constraint positive_fkuserid check (fkuserid >= 0),
  	constraint foreign_fkuserid foreign key(fkuserid) references tbluser(pkid),
  	constraint tblusersession_fkuserid foreign key (fkuserid) references tbluser (pkid)
) engine=innodb default charset=utf8;

create table tbluserpassword(
	pkid int(10) unsigned not null auto_increment,
	fkuserid int(10) unsigned not null,
	txtpassword varchar(1000) not null,
	datecreate int(10) not null,
	primary key(pkid),
	key tbluserpassword_fkuserid (fkuserid),
  	constraint tbluserpassword_fkuserid foreign key(fkuserid) references tbluser(pkid)
) engine=innodb default charset=utf8;

insert into tbluserpassword value(1,1,'$2a$11$igqMFfg7o0nwo.uW3egSKO4WNfddEoKe2jUYbYeNCNnHuyFqQF5LW',1555927228);



create table tbluserprofile(
	pkid int(10) unsigned not null auto_increment,
	fkuserid int(10) unsigned not null,
	fkprofilepicid int(10) unsigned not null,
	enumtitle int(5) default null,
	txttelephonenumber varchar(15) default null,
	txtaddress text default null,
	fkcityid int(10) unsigned default null,
	fkstateid int(10) unsigned default null,
	txtpincode varchar(6) default null,
	datebirth int(10) default null,
	iscomplete bit(1) default 0,
	txtwebsite varchar(200) default null,
	primary key(pkid),
	key tbluserprofile_fkprofilepicid (fkprofilepicid),
	key tbluserprofile_fkuserid (fkuserid),
	key tbluserprofile_enumtitle (enumtitle),
	key tbluserprofile_fkcityid (fkcityid),
	key tbluserprofile_iscomplete (iscomplete),
	constraint tbluserprofile_fkuserid foreign key(fkuserid) references tbluser(pkid),
	constraint tbluserprofile_fkprofilepicid foreign key(fkprofilepicid) references tblfile(pkid),
	constraint tbluserprofile_fkcityid foreign key(fkcityid) references tblcity(pkid),
	constraint tbluserprofile_fkstateid foreign key(fkstateid) references tblstate(pkid)
) engine=innodb default charset=utf8;


create table tblsettingtemplate(
	pkid int(10) unsigned not null auto_increment,
	txtkey varchar(100) not null,
	enumdatatype int(2) not null,
	primary key(pkid),
	unique key tblsettingtemplate_txtkey (txtkey)
) engine=innodb default charset=utf8;

create table tblsettingvalue(
	pkid int(10) unsigned not null auto_increment,
	txtvalue varchar(1000) not null,
	fkhospitalid int(10) unsigned not null,
	fksettingtemplateid int(10) unsigned not null,
	primary key(pkid),
	key tblsettingvalue_fkhospitalid (fkhospitalid),
	key tblsettingvalue_fksettingtemplateid (fksettingtemplateid),
  	constraint tblsettingvalue_fkhospitalid foreign key(fkhospitalid) references tblhospital(pkid),
  	constraint tblsettingvalue_fksettingtemplateid foreign key(fksettingtemplateid) references tblsettingtemplate(pkid)
)  engine=innodb default charset=utf8;

insert into tblsettingtemplate(txtkey, enumdatatype) values
('FILE_PATH',2),
('TEMP_FILE_PATH',2),
('HOSPITAL_WEBSITE',2),
('TWO_FACTOR_AUTHENTICATION_ENABLED',8),
('DEVICE_COOKIE_TIME_IN_SECONDS',1),
('SESSION_INACTIVE_TIME_IN_MINUTES',1),
('MAX_ALLOWED_DEVICE',1),
('DEFAULT_TIME_ZONE_ID',1),
('PUBLIC_FILE_PATH',2),
('RESET_PASSWORD_TOKEN_VALID_MINUTES',1),
('PASSWORD_USED_VALIDATION_ENABLED',8),
('MAX_PASSWORD_STORE_COUNT_PER_USER',1),
('RESET_PASSWORD_SESSION_VALID_MINUTES',1),
('HOSPITAL_ADMIN_URL',2),
('CAPTCHA_IMAGE_PATH',2);



create table tblemailaccount (
  pkid int(10) unsigned not null auto_increment,
  lockversion int(10) unsigned not null,
  txtname varchar(100) not null,
  txthost varchar(500) not null,
  intport int(10) null default 25,
  txtusername varchar(100) not null,
  txtpassword varchar(500)  not null,
  txtreplytoemail varchar(100)  not null,
  txtemailfrom varchar(500)  not null,
  intrateperhour int(10)  not null default 500,
  intupdaterateperhour int(10) not null default 500,
  daterateperhour int(10) default null,
  intrateperday int(10)  not null default 2000,
  intupdaterateperday int(10)  not null default 2000,
  daterateperday int(10) default null,
  enumauthmethod int(10) not null default 0/*0=plain, 1=demo, 2=cram md5*/,
  enumauthsecurity int(10) not null default 0/*0=none, 1=use ssl, 2=use tls*/,
  inttimeout int(10) not null default 60000,
  fkcreateby int(10) unsigned default null,
  datecreate int(10) not null,
  fkupdateby int(10) unsigned default null,
  dateupdate int(10) default null,
  isactive bit(1) default 1,
  fkactchangeby int(10) unsigned default null,
  dateactchange int(10) default null,
  isarchive bit(1) default 0,
  fkarchiveby int(10) unsigned default null,
  datearchive int(10) default null,
  fkhospitalid int(10) unsigned not null,
  primary key (pkid),
  key tblemailaccount_fkcreateby(fkcreateby),
  key tblemailaccount_fkupdateby(fkupdateby),
  key tblemailaccount_fkactchangeby(fkactchangeby),
  key tblemailaccount_fkarchiveby(fkarchiveby),
  key tblemailaccount_isarchive(isarchive),
  key tblemailaccount_isactive(isactive),
  key tblemailaccount_fkhospitalid (fkhospitalid),
  constraint tblemailaccount_fkcreateby foreign key(fkcreateby) references tbluser(pkid),
  constraint tblemailaccount_fkupdateby foreign key(fkupdateby) references tbluser(pkid),
  constraint tblemailaccount_fkactchangeby foreign key(fkactchangeby) references tbluser(pkid),
  constraint tblemailaccount_fkarchiveby foreign key(fkarchiveby) references tbluser(pkid),
  constraint tblemailaccount_fkhospitalid foreign key(fkhospitalid) references tblhospital(pkid)
) engine=innodb default charset=utf8;


create table tblemailcontent (
  pkid int(10) unsigned not null auto_increment,
  lockversion int(10) unsigned not null,
  fkemailaccountId int(10) unsigned not null,
  txtname varchar(100) not null,
  txtsubject varchar(1000) not null,
  txtcontent text not null,
  txtemailcc text default null,
  txtemailBcc text default null,
  fktriggerid int(10) unsigned not null,
  fkcreateby int(10) unsigned default null,
  datecreate int(10) not null,
  fkupdateby int(10) unsigned default null,
  dateupdate int(10) default null,
  isactive bit(1) default 1,
  fkactchangeby int(10) unsigned default null,
  dateactchange int(10) default null,
  isarchive bit(1) default 0,
  fkarchiveby int(10) unsigned default null,
  datearchive int(10) default null,
  fkhospitalid int(10) unsigned not null,
  primary key (pkid),
  key tblemailcontent_fkcreateby(fkcreateby),
  key tblemailcontent_fkupdateby(fkupdateby),
  key tblemailcontent_fkactchangeby(fkactchangeby),
  key tblemailcontent_fkarchiveby(fkarchiveby),
  key tblemailcontent_fkemailaccountid(fkemailaccountid),
  key tblemailcontent_isarchive(isarchive),
  key tblemailcontent_isactive(isactive),
  key tblemailcontent_fktriggerid(fktriggerid),
  key tblemailcontent_fkhospitalid (fkhospitalid),
  constraint tblemailcontent_fkcreateby foreign key(fkcreateby) references tbluser(pkid),
  constraint tblemailcontent_fkupdateby foreign key(fkupdateby) references tbluser(pkid),
  constraint tblemailcontent_fkactchangeby foreign key(fkactchangeby) references tbluser(pkid),
  constraint tblemailcontent_fkarchiveby foreign key(fkarchiveby) references tbluser(pkid),
  constraint tblemailcontent_fkemailaccountid foreign key(fkemailaccountid) references tblemailaccount(pkid),
  constraint tblemailcontent_fkhospitalid foreign key(fkhospitalid) references tblhospital(pkid)
) engine=innodb default charset=utf8;

create table tbltransactionemail (
  pkid int(10) unsigned not null auto_increment,
  lockversion int(10) unsigned not null,
  fkemailaccountid int(10) unsigned not null,
  txtemailto text not null,
  txtemailcc text,
  txtemailbcc text,
  txtsubject varchar(1000) not null,
  txtbody text not null,
  enumstatus int(3) not null default 0/*0=new, 1=inprocess, 2=failed, 3=sent*/,
  numberretrycount int(10) not null default 0,
  txtattachmentpath text,
  txterror text,
  datesend int(10) default null,
  datesent int(10) default null,
  fkcreateby int(10) unsigned default null,
  datecreate int(10) not null,
  fkupdateby int(10) unsigned default null,
  dateupdate int(10) default null,
  isactive bit(1) default 1,
  fkactchangeby int(10) unsigned default null,
  dateactchange int(10) default null,
  isarchive bit(1) default 0,
  fkarchiveby int(10) unsigned default null,
  datearchive int(10) default null,
  fkhospitalid int(10) unsigned not null,
  primary key (pkid),
  key tbltransactionemail_fkcreateby (fkcreateby),
  key tbltransactionemail_fkupdateby (fkupdateby),
  key tbltransactionemail_fkactchangeby (fkactchangeby),
  key tbltransactionemail_fkarchiveby (fkarchiveby),
  key tbltransactionemail_fkemailaccountid (fkemailaccountid),
  key tbltransactionemail_isarchive (isarchive),
  key tbltransactionemail_isactive (isactive),
  key tbltransactionemail_fkhospitalid (fkhospitalid),
  constraint tbltransactionemail_fkcreateby foreign key(fkcreateby) references tbluser(pkid),
  constraint tbltransactionemail_fkupdateby foreign key(fkupdateby) references tbluser(pkid),
  constraint tbltransactionemail_fkactchangeby foreign key(fkactchangeby) references tbluser(pkid),
  constraint tbltransactionemail_fkarchiveby foreign key(fkarchiveby) references tbluser(pkid),
  constraint tbltransactionemail_fkemailaccountid foreign key(fkemailaccountid) references tblemailaccount(pkid),
  constraint tbltransactionemail_fkhospitalid foreign key(fkhospitalid) references tblhospital(pkid)
)engine=innodb default charset=utf8;

create table tblemaildefaultcontent (
  pkid int(10) unsigned not null auto_increment,
  txtname varchar(100) not null,
  txtsubject varchar(1000) not null,
  txtcontent text not null,
  fktriggerid int(2) not null,
  primary key (pkid),
  key tblemaildefaultcontent_fktriggerid (fktriggerid)
) engine=innodb default charset=utf8;

create table tblgroup(
	pkid int(10) unsigned not null auto_increment,
  	txtname varchar(30) not null,
	primary key(pkid),
  	unique key tblgroup_txtname (txtname)
) engine=innodb default charset=utf8;


create table tblapp(
 	pkid int(3) not null,
 	txtname varchar(100) not null,
 	txtfeature text default null,
 	primary key (pkid),
 	unique key tblapp_txtname (txtname)
)engine=innodb default charset=utf8;

insert into tblapp(pkid,txtname) values(1,'Core');

create table tblrights(
 	pkid int(3) unsigned not null auto_increment,
 	txtname varchar(100) not null,
 	primary key (pkid),
 	unique key tblrights_txtname (txtname)
)engine=innodb default charset=utf8;

create table tblrolemoduleright(
	fkroleid int(10) unsigned not null,
	fkmoduleid int(10) unsigned not null,
	fkrightsid int(10) unsigned not null,
	primary key(fkroleid, fkmoduleid, fkrightsid),
	key tblrolemoduleright_fkroleid(fkroleid),
	key tblrolemoduleright_fkmoduleid(fkmoduleid),
	key tblrolemoduleright_fkrightsid(fkrightsid),
    constraint tblrolemoduleright_fkroleid foreign key(fkroleid) references tblrole(pkid),
  	constraint tblrolemoduleright_fkmoduleid foreign key(fkmoduleid) references tblmodule(pkid),
  	constraint tblrolemoduleright_fkrightsid foreign key(fkrightsid) references tblrights(pkid)
)engine=innodb default charset=utf8;

ALTER TABLE tblrolemoduleright
ADD FOREIGN KEY (fkroleid)
REFERENCES tblrole(pkid);

create table tblrolegroup(
  	fkgroupid int(10) unsigned not null,
  	fkroleid int(10) unsigned not null,
  	primary key(fkgroupid,fkroleid),
  	constraint positive_fkgroupid check (fkgroupid >= 0),
  	constraint positive_fkroleid check (fkroleid >= 0),
  	constraint foreign_fkgroupid foreign key(fkgroupid) references tblgroup(pkid),
  	constraint foreign_fkroleid foreign key(fkroleid) references tblrole(pkid)
)engine=innodb default charset=utf8;


create table tblalloweddomain (
  pkid int(10) unsigned not null auto_increment,
  txtdomain varchar(100) not null,
  primary key (pkid)
)engine=innodb default charset=utf8;


create table tbldepartment(
 	pkid int(10) unsigned not null auto_increment,
 	lockversion int(10) unsigned not null,
    txtname varchar(200) not null,
    txtlocation varchar(100) null,
  	fkhospitalid int(10) unsigned not null,
    datecreate int(10) not null,
    dateupdate int(10) default null,
    isarchive bit(1) not null default 0,
    isactive bit(1) not null default 1,
    datearchive int(10) default null,
    fkcreateby int(10) unsigned default null,
    fkupdateby int(10) unsigned default null,
    fkactchangeby int(10) unsigned default null,
    fkarchiveby int(10) unsigned default null,
    dateactchange int(10) default null,
	primary key (pkid),
	FULLTEXT(txtname),
	key tbldepartment_fkcreateby (fkcreateby),
 	key tbldepartment_fkupdateby (fkupdateby),
 	key tbldepartment_fkactchangeby (fkactchangeby),
	key tbldepartment_fkarchiveby (fkarchiveby),
	key tbldepartment_fkhospitalid (fkhospitalid),
	constraint tbldepartment_fkcreateby foreign key (fkcreateby) references tbluser (pkid),
  	constraint tbldepartment_fkupdateby foreign key (fkupdateby) references tbluser (pkid),
  	constraint tbldepartment_fkactchangeby foreign key (fkactchangeby) references tbluser (pkid),
  	constraint tbldepartment_fkarchiveby foreign key (fkarchiveby) references tbluser (pkid),
  	constraint tbldepartment_fkhospitalid foreign key (fkhospitalid) references tblhospital (pkid)
) engine=innodb default charset=utf8;

create table tblplan(
 	pkid int(10) unsigned not null auto_increment,
 	lockversion int(10) unsigned not null,
    txtname varchar(200) not null,
    numbermonth int(10) null,
  	numberdays int(10) null,
  	issellallowed bit(1) not null default 0,
    datecreate int(10) not null,
    dateupdate int(10) default null,
    isarchive bit(1) not null default 0,
    isactive bit(1) not null default 1,
    datearchive int(10) default null,
    fkcreateby int(10) unsigned default null,
    fkupdateby int(10) unsigned default null,
    fkactchangeby int(10) unsigned default null,
    fkarchiveby int(10) unsigned default null,
    dateactchange int(10) default null,
	primary key (pkid),
	unique key txtname (txtname),
	key tblplan_fkcreateby (fkcreateby),
 	key tblplan_fkupdateby (fkupdateby),
 	key tblplan_fkactchangeby (fkactchangeby),
	key tblplan_fkarchiveby (fkarchiveby),
	constraint tblplan_fkcreateby foreign key (fkcreateby) references tbluser (pkid),
  	constraint tblplan_fkupdateby foreign key (fkupdateby) references tbluser (pkid),
  	constraint tblplan_fkactchangeby foreign key (fkactchangeby) references tbluser (pkid),
  	constraint tblplan_fkarchiveby foreign key (fkarchiveby) references tbluser (pkid)
) engine=innodb default charset=utf8;


create table tbllicense(
 	pkid int(10) unsigned not null auto_increment,
    txtname varchar(200) not null,
	txtlicensekey varchar(100) not null,
	dateactivate int(10) not null,
	isactivate bit(1) not null default 0,
	datedeactivate int(10) default null,
	primary key (pkid),
	unique key txtlicensekey (txtlicensekey)
) engine=innodb default charset=utf8;


create table tblhospitalplan(
 	pkid int(10) unsigned not null auto_increment,
 	fkhospitalid int(10) unsigned not null,
 	fkplanid int(10) unsigned not null,
 	datecreate int(10) not null,
	primary key (pkid),
	key tblhospitalplan_fkhospitalid (fkhospitalid),
 	key tblhospitalplan_fkplanid (fkplanid),
  	constraint tblhospitalplan_fkhospitalid foreign key (fkhospitalid) references tblhospital (pkid),
  	constraint tblhospitalplan_fkplanid foreign key (fkplanid) references tblplan (pkid)
) engine=innodb default charset=utf8;

alter table tblhospital
add column fklicenseid int(10) unsigned NULL;

alter table tblhospital
add constraint tblhospital_fklicenseid
foreign key (fklicenseid)
references tbllicense(pkid);

alter table tblhospital
add column fkhospitalplanid int(10) unsigned NULL;

alter table tblhospital
add constraint tblhospital_fkhospitalplanid
foreign key (fkhospitalplanid)
references tblhospitalplan(pkid);

create table tblroom(
 	pkid int(10) unsigned not null auto_increment,
 	txtname varchar(200) null,
 	txtroomnumber varchar(100) not null,
 	fkhospitalid int(10) unsigned not null,
	primary key (pkid),
 	key tblroom_fkhospitalid (fkhospitalid),
  	constraint tblroom_fkhospitalid foreign key (fkhospitalid) references tblhospital (pkid)
) engine=innodb default charset=utf8;

create table tblservice(
 	pkid int(10) unsigned not null auto_increment,
 	lockversion int(10) unsigned not null,
    txtname varchar(200) not null,
  	fkhospitalid int(10) unsigned not null,
    datecreate int(10) not null,
    dateupdate int(10) default null,
    isarchive bit(1) not null default 0,
    isactive bit(1) not null default 1,
    datearchive int(10) default null,
    fkcreateby int(10) unsigned default null,
    fkupdateby int(10) unsigned default null,
    fkactchangeby int(10) unsigned default null,
    fkarchiveby int(10) unsigned default null,
    dateactchange int(10) default null,
	primary key (pkid),
	FULLTEXT(txtname),
	key tblservice_fkcreateby (fkcreateby),
 	key tblservice_fkupdateby (fkupdateby),
 	key tblservice_fkactchangeby (fkactchangeby),
	key tblservice_fkarchiveby (fkarchiveby),
	key tblservice_fkhospitalid (fkhospitalid),
	constraint tblservice_fkcreateby foreign key (fkcreateby) references tbluser (pkid),
  	constraint tblservice_fkupdateby foreign key (fkupdateby) references tbluser (pkid),
  	constraint tblservice_fkactchangeby foreign key (fkactchangeby) references tbluser (pkid),
  	constraint tblservice_fkarchiveby foreign key (fkarchiveby) references tbluser (pkid),
  	constraint tblservice_fkhospitalid foreign key (fkhospitalid) references tblhospital (pkid)
) engine=innodb default charset=utf8;



create table tbltariff(
 	pkid int(10) unsigned not null auto_increment,
 	lockversion int(10) unsigned not null,
    txtname varchar(200) not null,
    fkhospitalid int(10) unsigned not null,
	datecreate int(10) not null,
    dateupdate int(10) default null,
    isarchive bit(1) not null default 0,
    isactive bit(1) not null default 1,
    datearchive int(10) default null,
    fkcreateby int(10) unsigned default null,
    fkupdateby int(10) unsigned default null,
    fkactchangeby int(10) unsigned default null,
    fkarchiveby int(10) unsigned default null,
    dateactchange int(10) default null,
	primary key (pkid),
	FULLTEXT(txtname),
    key tbltariff_fkcreateby (fkcreateby),
 	key tbltariff_fkupdateby (fkupdateby),
 	key tbltariff_fkactchangeby (fkactchangeby),
	key tbltariff_fkarchiveby (fkarchiveby),
	key tbltariff_fkhospitalid (fkhospitalid),
	constraint tbltariff_fkcreateby foreign key (fkcreateby) references tbluser (pkid),
  	constraint tbltariff_fkupdateby foreign key (fkupdateby) references tbluser (pkid),
  	constraint tbltariff_fkactchangeby foreign key (fkactchangeby) references tbluser (pkid),
  	constraint tbltariff_fkarchiveby foreign key (fkarchiveby) references tbluser (pkid),
  	constraint tbltariff_fkhospitalid foreign key (fkhospitalid) references tblhospital (pkid)
) engine=innodb default charset=utf8;

create table tblcategory(
 	pkid int(10) unsigned not null auto_increment,
 	lockversion int(10) unsigned not null,
    txtname varchar(200) not null,
    fkhospitalid int(10) unsigned not null,
	datecreate int(10) not null,
    dateupdate int(10) default null,
    isarchive bit(1) not null default 0,
    isactive bit(1) not null default 1,
    datearchive int(10) default null,
    fkcreateby int(10) unsigned default null,
    fkupdateby int(10) unsigned default null,
    fkactchangeby int(10) unsigned default null,
    fkarchiveby int(10) unsigned default null,
    dateactchange int(10) default null,
    primary key (pkid),
    FULLTEXT(txtname),
    key tblcategory_fkcreateby (fkcreateby),
 	key tblcategory_fkupdateby (fkupdateby),
 	key tblcategory_fkactchangeby (fkactchangeby),
	key tblcategory_fkarchiveby (fkarchiveby),
	key tblcategory_fkhospitalid (fkhospitalid),
	constraint tblcategory_fkcreateby foreign key (fkcreateby) references tbluser (pkid),
  	constraint tblcategory_fkupdateby foreign key (fkupdateby) references tbluser (pkid),
  	constraint tblcategory_fkactchangeby foreign key (fkactchangeby) references tbluser (pkid),
  	constraint tblcategory_fkarchiveby foreign key (fkarchiveby) references tbluser (pkid),
  	constraint tblcategory_fkhospitalid foreign key (fkhospitalid) references tblhospital (pkid)
) engine=innodb default charset=utf8;

create table tblservicecategory(
	fkserviceid int(10) unsigned not null,
	fkcategoryid int(10) unsigned not null,
	primary key(fkserviceid, fkcategoryid),
  	constraint tblservicecategory_fkserviceid foreign key(fkserviceid) references tblservice(pkid),
  	constraint tblservicecategory_fkcategoryid foreign key(fkcategoryid) references tblcategory(pkid)
)engine=innodb default charset=utf8;


create table tbltariffrate(
 	pkid int(10) unsigned not null auto_increment,
    txtname varchar(200) null,
    fktariffid int(10) unsigned not null,
  	fkserviceid int(10) unsigned not null,
  	fkcategoryid int(10) unsigned not null,
  	fkdoctorid int(10) unsigned not null,
  	fkdepartmentid int(10) unsigned not null,
  	txtrate varchar(50) null,
  	ismandatory bit(1) not null default 1,
	primary key (pkid),
	key tbltariffrate_fkserviceid (fkserviceid),
	key tbltariffrate_fkdoctorid (fkdoctorid),
	key tbltariffrate_fkdepartmentid (fkdepartmentid),
	key tbltariffrate_fktariffid (fktariffid),
	key tbltariffrate_fkcategoryid (fkcategoryid),
	constraint tbltariffrate_fktariffid foreign key (fktariffid) references tbltariff (pkid),
	constraint tbltariffrate_fkcategoryid foreign key (fkcategoryid) references tblcategory (pkid),
  	constraint tbltariffrate_fkserviceid foreign key (fkserviceid) references tblservice (pkid),
  	constraint tbltariffrate_fkdoctorid foreign key (fkdoctorid) references tbluser (pkid),
  	constraint tbltariffrate_fkdepartmentid foreign key (fkdepartmentid) references tbldepartment (pkid)
) engine=innodb default charset=utf8;

create table tblqueue(
 	pkid int(10) unsigned not null auto_increment,
    fkroomid int(10) unsigned not null,
    fkdepartmentid int(10) unsigned not null,
    fkdoctorid int(10) unsigned not null,
    fkhospitalid int(10) unsigned not null,
    primary key (pkid),
	key tblqueue_fkroomid (fkroomid),
	key tblqueue_fkdepartmentid (fkdepartmentid),
	key tblqueue_fkdoctorid (fkdoctorid),
	key tblqueue_fkhospitalid (fkhospitalid),
  	constraint tblqueue_fkroomid foreign key (fkroomid) references tblroom (pkid),
  	constraint tblqueue_fkdepartmentid foreign key (fkdepartmentid) references tbldepartment (pkid),
  	constraint tblqueue_fkdoctorid foreign key (fkdoctorid) references tbluser (pkid),
  	constraint tblqueue_fkhospitalid foreign key (fkhospitalid) references tblhospital (pkid)
) engine=innodb default charset=utf8;


create table tblshift(
 	pkid int(10) unsigned not null auto_increment,
 	lockversion int(10) unsigned not null,
 	txtname varchar(200) not null,
 	txtstarttime varchar(20) not null,
 	txtendtime varchar(20) not null,
    fkhospitalid int(10) unsigned not null,
    datecreate int(10) not null,
    dateupdate int(10) default null,
    isarchive bit(1) not null default 0,
    isactive bit(1) not null default 1,
    datearchive int(10) default null,
    fkcreateby int(10) unsigned default null,
    fkupdateby int(10) unsigned default null,
    fkactchangeby int(10) unsigned default null,
    fkarchiveby int(10) unsigned default null,
    dateactchange int(10) default null,
    primary key (pkid),
    key tblshift_fkcreateby (fkcreateby),
 	key tblshift_fkupdateby (fkupdateby),
 	key tblshift_fkactchangeby (fkactchangeby),
	key tblshift_fkarchiveby (fkarchiveby),
	key tblshift_fkhospitalid (fkhospitalid),
	constraint tblshift_fkcreateby foreign key (fkcreateby) references tbluser (pkid),
  	constraint tblshift_fkupdateby foreign key (fkupdateby) references tbluser (pkid),
  	constraint tblshift_fkactchangeby foreign key (fkactchangeby) references tbluser (pkid),
  	constraint tblshift_fkarchiveby foreign key (fkarchiveby) references tbluser (pkid),
  	constraint tblshift_fkhospitalid foreign key (fkhospitalid) references tblhospital (pkid)
) engine=innodb default charset=utf8;


create table tbldoctorshift(
 	pkid int(10) unsigned not null auto_increment,
 	fkdoctorid int(10) unsigned not null,
 	fkshiftid int(10) unsigned not null,
 	dateassign int(10) not null,
    primary key (pkid),
	key tbldoctorshift_fkdoctorid (fkdoctorid),
	key tbldoctorshift_fkshiftid (fkshiftid),
  	constraint tbldoctorshift_fkdoctorid foreign key (fkdoctorid) references tbluser (pkid),
  	constraint tbldoctorshift_fkshiftid foreign key (fkshiftid) references tblshift (pkid)
) engine=innodb default charset=utf8;


create table tblsmsaccount (
  pkid int(10) unsigned not null auto_increment,
  lockversion int(10) unsigned not null,
  txtname varchar(100) not null,
  txtgroupid int(10) null default '0',
  txtrouteid int(10) null default '1',
  txtsenderid varchar(500) null default 'DEMOOS',
  txtsignature varchar(500) null,
  txtauthKey varchar(500) not null,
  txtcontenttype varchar(500) null default 'unicode',
  fkcreateby int(10) unsigned default null,
  datecreate int(10) not null,
  fkupdateby int(10) unsigned default null,
  dateupdate int(10) default null,
  isactive bit(1) default 1,
  fkactchangeby int(10) unsigned default null,
  dateactchange int(10) default null,
  isarchive bit(1) default 0,
  fkarchiveby int(10) unsigned default null,
  datearchive int(10) default null,
  primary key (pkid),
  key tblsmsaccount_fkcreateby(fkcreateby),
  key tblsmsaccount_fkupdateby(fkupdateby),
  key tblsmsaccount_fkactchangeby(fkactchangeby),
  key tblsmsaccount_fkarchiveby(fkarchiveby),
  key tblsmsaccount_isarchive(isarchive),
  key tblsmsaccount_isactive(isactive),
  constraint tblsmsaccount_fkcreateby foreign key(fkcreateby) references tbluser(pkid),
  constraint tblsmsaccount_fkupdateby foreign key(fkupdateby) references tbluser(pkid),
  constraint tblsmsaccount_fkactchangeby foreign key(fkactchangeby) references tbluser(pkid),
  constraint tblsmsaccount_fkarchiveby foreign key(fkarchiveby) references tbluser(pkid)
) engine=innodb default charset=utf8;

create table tblsmscontent (
  pkid int(10) unsigned not null auto_increment,
  lockversion int(10) unsigned not null,
  fksmsaccountid int(10) unsigned not null,
  txtname varchar(100) not null,
  txtsubject varchar(1000) not null,
  txtcontent text not null,
  fktriggerid int(10) unsigned not null,
  fkcreateby int(10) unsigned default null,
  datecreate int(10) not null,
  fkupdateby int(10) unsigned default null,
  dateupdate int(10) default null,
  isactive bit(1) default 1,
  fkactchangeby int(10) unsigned default null,
  dateactchange int(10) default null,
  isarchive bit(1) default 0,
  fkarchiveby int(10) unsigned default null,
  datearchive int(10) default null,
  fkhospitalid int(10) unsigned not null,
  primary key (pkid),
  key tblsmscontent_fkcreateby(fkcreateby),
  key tblsmscontent_fkupdateby(fkupdateby),
  key tblsmscontent_fkactchangeby(fkactchangeby),
  key tblsmscontent_fkarchiveby(fkarchiveby),
  key tblsmscontent_fksmsaccountid(fksmsaccountid),
  key tblsmscontent_isarchive(isarchive),
  key tblsmscontent_isactive(isactive),
  key tblsmscontent_fktriggerid(fktriggerid),
  key tblsmscontent_fkhospitalid (fkhospitalid),
  constraint tblsmscontent_fkcreateby foreign key(fkcreateby) references tbluser(pkid),
  constraint tblsmscontent_fkupdateby foreign key(fkupdateby) references tbluser(pkid),
  constraint tblsmscontent_fkactchangeby foreign key(fkactchangeby) references tbluser(pkid),
  constraint tblsmscontent_fkarchiveby foreign key(fkarchiveby) references tbluser(pkid),
  constraint tblsmscontent_fksmsaccountid foreign key(fksmsaccountid) references tblsmsaccount(pkid),
  constraint tblsmscontent_fkhospitalid foreign key(fkhospitalid) references tblhospital(pkid)
) engine=innodb default charset=utf8;

create table tblsmstransaction (
  pkid int(10) unsigned not null auto_increment,
  lockversion int(10) unsigned not null,
  fksmsaccountid int(10) unsigned not null,
  txtbody text not null,
  txtmobile varchar(15) not null,
  enumstatus int(3) not null default 0,/*0=new, 1=inprocess, 2=failed, 3=sent*/
  numberretrycount int(10) not null default 0,
  txterror text,
  datesend int(10) default null,
  datesent int(10) default null,
  fkcreateby int(10) unsigned default null,
  datecreate int(10) not null,
  fkupdateby int(10) unsigned default null,
  dateupdate int(10) default null,
  isactive bit(1) default 1,
  fkactchangeby int(10) unsigned default null,
  dateactchange int(10) default null,
  isarchive bit(1) default 0,
  fkarchiveby int(10) unsigned default null,
  datearchive int(10) default null,
  fkhospitalid int(10) unsigned not null,
  primary key (pkid),
  key tblsmstransaction_fkcreateby (fkcreateby),
  key tblsmstransaction_fkupdateby (fkupdateby),
  key tblsmstransaction_fkactchangeby (fkactchangeby),
  key tblsmstransaction_fkarchiveby (fkarchiveby),
  key tblsmstransaction_fksmsaccountid(fksmsaccountid),
  key tblsmstransaction_isarchive (isarchive),
  key tblsmstransaction_isactive (isactive),
  key tblsmstransaction_fkhospitalid (fkhospitalid),
  constraint tblsmstransaction_fkcreateby foreign key(fkcreateby) references tbluser(pkid),
  constraint tblsmstransaction_fkupdateby foreign key(fkupdateby) references tbluser(pkid),
  constraint tblsmstransaction_fkactchangeby foreign key(fkactchangeby) references tbluser(pkid),
  constraint tblsmstransaction_fkarchiveby foreign key(fkarchiveby) references tbluser(pkid),
  constraint tblsmstransaction_fksmsaccountid foreign key(fksmsaccountid) references tblsmsaccount(pkid),
  constraint tblsmstransaction_fkhospitalid foreign key(fkhospitalid) references tblhospital(pkid)
)engine=innodb default charset=utf8;

create table tblsmsdefaultcontent (
  pkid int(10) unsigned not null auto_increment,
  txtname varchar(100) not null,
  txtsubject varchar(1000) not null,
  txtcontent text not null,
  fktriggerid int(2) not null,
  primary key (pkid),
  key tblsmsdefaultcontent_fktriggerid (fktriggerid)
) engine=innodb default charset=utf8;


create table tblusersessionhistory (
  pkid int(10) unsigned not null auto_increment,
  fkuserid int(10) unsigned not null,
  datecreate int(10) not null,
  txtbrowser varchar(100) default null,
  txtipaddress varchar(50) default null,
  primary key (pkid),
  key tblusersessionhistory_fkuserid (fkuserid),
  constraint tblusersessionhistory_fkuserid foreign key(fkuserid) references tbluser(pkid)
) engine=innodb default charset=utf8;



drop table tbldoctorshift;

create table tblappointment (
  pkid int(10) unsigned not null auto_increment,
  lockversion int(10) unsigned not null,
  fkdoctorid int(10) unsigned not null,
  fkcreateby int(10) unsigned default null,
  datecreate int(10) not null,
  fkupdateby int(10) unsigned default null,
  dateupdate int(10) default null,
  isactive bit(1) default 1,
  fkactchangeby int(10) unsigned default null,
  dateactchange int(10) default null,
  isarchive bit(1) default 0,
  fkarchiveby int(10) unsigned default null,
  datearchive int(10) default null,
  fkhospitalid int(10) unsigned not null,
  primary key (pkid),
  key tblappointment_fkcreateby (fkcreateby),
  key tblappointment_fkupdateby (fkupdateby),
  key tblappointment_fkactchangeby (fkactchangeby),
  key tblappointment_fkarchiveby (fkarchiveby),
  key tblappointment_fkdoctorid(fkdoctorid),
  key tblappointment_isarchive (isarchive),
  key tblappointment_isactive (isactive),
  key tblappointment_fkhospitalid (fkhospitalid),
  constraint tblappointment_fkcreateby foreign key(fkcreateby) references tbluser(pkid),
  constraint tblappointment_fkupdateby foreign key(fkupdateby) references tbluser(pkid),
  constraint tblappointment_fkactchangeby foreign key(fkactchangeby) references tbluser(pkid),
  constraint tblappointment_fkarchiveby foreign key(fkarchiveby) references tbluser(pkid),
  constraint tblappointment_fkdoctorid foreign key(fkdoctorid) references tbluser(pkid),
  constraint tblappointment_fkhospitalid foreign key(fkhospitalid) references tblhospital(pkid)
)engine=innodb default charset=utf8;


create table tblappointmentlog (
  pkid int(10) unsigned not null auto_increment,
  fkappointmentid int(10) unsigned not null,
  fkshiftid int(10) unsigned not null,
  numdays int(10) unsigned not null,
  datestarteffective int(10) unsigned null,
  dateendeffective int(10) unsigned null,
  primary key (pkid),
  key tblappointmentlog_fkappointmentid (fkappointmentid),
  key tblappointmentlog_fkshiftid (fkshiftid),
  constraint tblappointmentlog_fkappointmentid foreign key(fkappointmentid) references tblappointment(pkid),
  constraint tblappointmentlog_fkshiftid foreign key(fkshiftid) references tblshift(pkid)
)engine=innodb default charset=utf8;






alter table tbluser add column txtemail varchar(100) null after txtusername;
alter table tbluser add unique key(txtemail);
alter table tbluser add unique key(txtmobile);

alter table tbluser add key tbluser_txtmobile(txtmobile);
alter table tbluser add key tbluser_txtemail(txtemail);
alter table tbluser add key tbluser_txtfirstname(txtfirstname);
alter table tbluser add key tbluser_txtlastname(txtlastname);
alter table tbluser add key tbluser_txtusername(txtusername);

alter table tbluser add FULLTEXT(txtemail);
alter table tbluser add FULLTEXT(txtfirstname,txtlastname,txtusername,txtemail);

alter table tbluser modify txtlastname varchar(200) null;


alter table tblhospital add column txtregistrationnumber varchar(200) null; /*this field not null true*/

alter table tblhospital drop column txtcommunicationemail;


/*
 * 10/06/2019
 * */

delete from tblsettingvalue where fksettingtemplateid=3;
delete from tblsettingtemplate where pkid=3;

drop table tblappointmentlog;
drop table tblappointment;


create table tblappointmentsetup (
  pkid int(10) unsigned not null auto_increment,
  lockversion int(10) unsigned not null,
  fkdoctorid int(10) unsigned not null,
  datestarteffective int(10) unsigned null,
  dateendeffective int(10) unsigned null,
  fkcreateby int(10) unsigned default null,
  datecreate int(10) not null,
  fkupdateby int(10) unsigned default null,
  dateupdate int(10) default null,
  isactive bit(1) default 1,
  fkactchangeby int(10) unsigned default null,
  dateactchange int(10) default null,
  isarchive bit(1) default 0,
  fkarchiveby int(10) unsigned default null,
  datearchive int(10) default null,
  fkhospitalid int(10) unsigned not null,
  primary key (pkid),
  key tblappointmentsetup_fkcreateby (fkcreateby),
  key tblappointmentsetup_fkupdateby (fkupdateby),
  key tblappointmentsetup_fkactchangeby (fkactchangeby),
  key tblappointmentsetup_fkarchiveby (fkarchiveby),
  key tblappointmentsetup_fkdoctorid(fkdoctorid),
  key tblappointmentsetup_isarchive (isarchive),
  key tblappointmentsetup_isactive (isactive),
  key tblappointmentsetup_fkhospitalid (fkhospitalid),
  constraint tblappointmentsetup_fkcreateby foreign key(fkcreateby) references tbluser(pkid),
  constraint tblappointmentsetup_fkupdateby foreign key(fkupdateby) references tbluser(pkid),
  constraint tblappointmentsetup_fkactchangeby foreign key(fkactchangeby) references tbluser(pkid),
  constraint tblappointmentsetup_fkarchiveby foreign key(fkarchiveby) references tbluser(pkid),
  constraint tblappointmentsetup_fkdoctorid foreign key(fkdoctorid) references tbluser(pkid),
  constraint tblappointmentsetup_fkhospitalid foreign key(fkhospitalid) references tblhospital(pkid)
)engine=innodb default charset=utf8;


create table tblappointmentdays (
  pkid int(10) unsigned not null auto_increment,
  fkappointmentsetupid int(10) unsigned not null,
  numdays int(2) unsigned not null,
  primary key (pkid),
  key tblappointmentdays_fkappointmentsetupid (fkappointmentsetupid),
  key tblappointmentdays_numdays (numdays),
  constraint tblappointmentdays_fkappointmentsetupid foreign key(fkappointmentsetupid) references tblappointmentsetup(pkid)
)engine=innodb default charset=utf8;


create table tblappointmentdayshift(
  	fkappointmentsetupdayid int(10) unsigned not null,
  	fkshiftid int(10) unsigned not null,
  	primary key(fkappointmentsetupdayid,fkshiftid),
  	constraint positive_fkappointmentsetupdayid check (fkappointmentsetupdayid >= 0),
  	constraint positive_fkshiftid check (fkshiftid >= 0),
  	constraint foreign_fkappointmentsetupdayid foreign key(fkappointmentsetupdayid) references tblappointmentdays(pkid),
  	constraint foreign_fkshiftid foreign key(fkshiftid) references tblshift(pkid)
)engine=innodb default charset=utf8;



alter table tbllicense modify txtname varchar(200) null;

alter table tblappointmentsetup add column isallday bit(1) not null default 1; 

drop table tblapp;

create table tblapp(
 	pkid int(3) unsigned not null auto_increment,
 	txtname varchar(100) not null,
 	txtfeature text default null,
 	primary key (pkid),
 	unique key tblapp_txtname (txtname)
)engine=innodb default charset=utf8;

alter table tblrole add fkappid int(10) unsigned null;

alter table tblrole
add constraint tblrole_fkappid
foreign key (fkappid)
references tblapp(pkid);

create table tbluserrole(
	fkuserid int(10) unsigned not null,
	fkroleid int(10) unsigned not null,
  	primary key(fkuserid, fkroleid),
  	constraint tbluserrole_fkuserid foreign key(fkuserid) references tbluser(pkid),
  	constraint tbluserrole_fkroleid foreign key(fkroleid) references tblrole(pkid)
)engine=innodb default charset=utf8;

create table tbldoctordepartment(
	fkdoctorid int(10) unsigned not null,
	fkdepartmentid int(10) unsigned not null,
  	primary key(fkdoctorid, fkdepartmentid),
  	constraint tbldoctordepartment_fkdoctorid foreign key(fkdoctorid) references tbluser(pkid),
  	constraint tbldoctordepartment_fkdepartmentid foreign key(fkdepartmentid) references tbldepartment(pkid)
)engine=innodb default charset=utf8;

alter table tbluser add column numotp int(6) null;
alter table tbluser add column datesendotp int(10) null;
alter table tbluser add column isotpused bit(1) not null default 0;


create table tblpatientdetails (
  pkid int(10) unsigned not null auto_increment,
  lockversion int(10) unsigned not null,
  txtfirstname varchar(200) not null,
  txtlastname varchar(200) not null,
  txtemail varchar(100) null,
  txtmobile varchar(15) not null,
  enumtitle int(5) default null,
  numage int(2) default null,
  txtaddress text default null,
  fkcityid int(10) unsigned default null,
  fkstateid int(10) unsigned default null,
  txtpincode varchar(6) default null,
  txtemergencycontactname varchar(200) null,
  txtemergencycontactmobile varchar(15) null,
  txtrelation varchar(50) null,
  txtotherinformation varchar(200) null,
  fkcreateby int(10) unsigned default null,
  datecreate int(10) not null,
  fkupdateby int(10) unsigned default null,
  dateupdate int(10) default null,
  isactive bit(1) default 1,
  fkactchangeby int(10) unsigned default null,
  dateactchange int(10) default null,
  isarchive bit(1) default 0,
  fkarchiveby int(10) unsigned default null,
  datearchive int(10) default null,
  fkhospitalid int(10) unsigned not null,
  FULLTEXT(txtfirstname,txtlastname,txtmobile,txtemail),
  FULLTEXT(txtfirstname),
  FULLTEXT(txtlastname),
  FULLTEXT(txtmobile),
  FULLTEXT(txtemail),
  primary key (pkid),
  key tblpatientdetails_fkcreateby (fkcreateby),
  key tblpatientdetails_fkupdateby (fkupdateby),
  key tblpatientdetails_fkactchangeby (fkactchangeby),
  key tblpatientdetails_fkarchiveby (fkarchiveby),
  key tblpatientdetails_isarchive (isarchive),
  key tblpatientdetails_isactive (isactive),
  key tblpatientdetails_fkhospitalid (fkhospitalid),
  key tblpatientdetails_fkcityid (fkcityid),
  key tblpatientdetails_fkstateid (fkstateid),
  key tblpatientdetails_txtfirstname (txtfirstname),
  key tblpatientdetails_txtlastname (txtlastname),
  key tblpatientdetails_txtmobile (txtmobile),
  constraint tblpatientdetails_fkcreateby foreign key(fkcreateby) references tbluser(pkid),
  constraint tblpatientdetails_fkupdateby foreign key(fkupdateby) references tbluser(pkid),
  constraint tblpatientdetails_fkactchangeby foreign key(fkactchangeby) references tbluser(pkid),
  constraint tblpatientdetails_fkarchiveby foreign key(fkarchiveby) references tbluser(pkid),
  constraint tblpatientdetails_fkcityid foreign key(fkcityid) references tblcity(pkid),
  constraint tblpatientdetails_fkstateid foreign key(fkstateid) references tblstate(pkid),
  constraint tblpatientdetails_fkhospitalid foreign key(fkhospitalid) references tblhospital(pkid)
)engine=innodb default charset=utf8;


create table tblappointmentbook (
  pkid int(10) unsigned not null auto_increment,
  lockversion int(10) unsigned not null,
  fkdoctorid int(10) unsigned not null,
  fkpatientid int(10) unsigned not null,
  fktariffid int(10) unsigned not null,
  txtregistrationnumber varchar(10) not null,
  dateappointment int(10) not null,
  fkshiftid int(10) unsigned not null,
  fkcreateby int(10) unsigned default null,
  datecreate int(10) not null,
  fkupdateby int(10) unsigned default null,
  dateupdate int(10) default null,
  isactive bit(1) default 1,
  fkactchangeby int(10) unsigned default null,
  dateactchange int(10) default null,
  isarchive bit(1) default 0,
  fkarchiveby int(10) unsigned default null,
  datearchive int(10) default null,
  fkhospitalid int(10) unsigned not null,
  primary key (pkid),
  key tblappointmentbook_fkcreateby (fkcreateby),
  key tblappointmentbook_fkupdateby (fkupdateby),
  key tblappointmentbook_fkactchangeby (fkactchangeby),
  key tblappointmentbook_fkarchiveby (fkarchiveby),
  key tblappointmentbook_isarchive (isarchive),
  key tblappointmentbook_isactive (isactive),
  key tblappointmentbook_fkhospitalid (fkhospitalid),
  key tblappointmentbook_fktariffid (fktariffid),
  key tblappointmentbook_fkpatientid (fkpatientid),
  key tblappointmentbook_fkdoctorid (fkdoctorid),
  key tblappointmentbook_fkshiftid (fkshiftid),
  constraint tblappointmentbook_fkcreateby foreign key(fkcreateby) references tbluser(pkid),
  constraint tblappointmentbook_fkupdateby foreign key(fkupdateby) references tbluser(pkid),
  constraint tblappointmentbook_fkactchangeby foreign key(fkactchangeby) references tbluser(pkid),
  constraint tblappointmentbook_fkarchiveby foreign key(fkarchiveby) references tbluser(pkid),
  constraint tblappointmentbook_fktariffid foreign key(fktariffid) references tbltariff(pkid),
  constraint tblappointmentbook_fkpatientid foreign key(fkpatientid) references tblpatientdetails(pkid),
  constraint tblappointmentbook_fkdoctorid foreign key(fkdoctorid) references tbluser(pkid),
  constraint tblappointmentbook_fkshiftid foreign key(fkshiftid) references tblshift(pkid),
  constraint tblappointmentbook_fkhospitalid foreign key(fkhospitalid) references tblhospital(pkid)
)engine=innodb default charset=utf8;



create table tblappointmentservice (
  pkid int(10) unsigned not null auto_increment,
  fkappointmentbookid int(10) unsigned not null,
  fktariffrateid int(10) unsigned not null,
  numcount int(10) unsigned not null default 1,
  primary key (pkid),
  key tblappointmentservice_fkappointmentbookid (fkappointmentbookid),
  key tblappointmentservice_fktariffrateid (fktariffrateid),
  constraint tblappointmentservice_fkappointmentbookid foreign key(fkappointmentbookid) references tblappointmentbook(pkid),
  constraint tblappointmentservice_fktariffrateid foreign key(fktariffrateid) references tbltariffrate(pkid)
)engine=innodb default charset=utf8;


create table tblappointmentqueue (
  pkid int(10) unsigned not null auto_increment,
  fkappointmentbookid int(10) unsigned not null,
  txtstatus int(2) unsigned not null default 0,
  timearrived int(10) unsigned null,
  numtoken int(5) null,
  primary key (pkid),
  key tblappointmentqueue_fkappointmentbookid (fkappointmentbookid),
  constraint tblappointmentqueue_fkappointmentbookid foreign key(fkappointmentbookid) references tblappointmentbook(pkid)
)engine=innodb default charset=utf8;


alter table tbluser add column ishospitaladmin bit(1) not null default 0;


alter table tblhospital add column completeadmindetails double default 0;


alter table tbltariffrate
add column fkhospitalid int(10) unsigned NULL;

alter table tbltariffrate
add constraint tbltariffrate_fkhospitalid
foreign key (fkhospitalid)
references tblhospital(pkid);

ALTER TABLE tbluser DROP foreign key tbluser_fkroleid;
alter table tbluser drop column fkroleid;

insert into tblsettingtemplate(txtkey, enumdatatype) values
('SLOT_DURATION',2),
('EACH_SLOT_PATIENT',2),
('RESET_PASSWORD_OTP_VALID_MINUTES',1);

/**SHOW INDEX FROM tbluser;**/


alter table tblpatientdetails
add column fkuserid int(10) unsigned NULL;

alter table tblpatientdetails
add constraint tblpatientdetails_fkuserid
foreign key (fkuserid)
references tbluser(pkid);

alter table tbluser add FULLTEXT(txtmobile);

/**
 * 10/07/2019
 */
create table tblinvoice (
  pkid int(10) unsigned not null auto_increment,
  fkappointmentbookid int(10) unsigned not null,
  numtotalamount double not null default 0,
  amountsgst double not null default 0,
  amountcgst double not null default 0,
  amountigst double not null default 0,
  numbertotaltaxamount double not null default 0,
  numdiscountamount double not null default 0,
  primary key (pkid),
  key tblinvoice_fkappointmentbookid (fkappointmentbookid),
  constraint tblinvoice_fkappointmentbookid foreign key(fkappointmentbookid) references tblappointmentbook(pkid)
)engine=innodb default charset=utf8;

alter table tblplan add column numberamount double not null default 0;

alter table tblhospital drop foreign key tblhospital_fklogoid;
alter table tblhospital modify fklogoid int(10) unsigned null;
 
alter table tblhospital
add constraint tblhospital_fklogoid
foreign key (fklogoid)
references tblfile(pkid);

alter table tblplan add key tblplan_txtname(txtname);
alter table tblplan add FULLTEXT(txtname);

alter table tblhospital add key tblhospital_txtname(txtname);
alter table tblhospital add key tblhospital_txtemail(txtemail);
alter table tblhospital add key tblhospital_txtmobilenumber(txtmobilenumber);

alter table tblhospital add FULLTEXT(txtname);
alter table tblhospital add FULLTEXT(txtemail);
alter table tblhospital add FULLTEXT(txtmobilenumber);

create table tblpaymenttransaction (
  pkid int(10) unsigned not null auto_increment,
  fkinvoiceid int(10) unsigned not null,
  fkhospitalid int(10) unsigned not null,
  datepayment int(10) not null,
  numberpaymenttype smallint not null,
  txttransactionnumber varchar(10) not null,
  primary key (pkid),
  unique key txttransactionnumber (txttransactionnumber),
  key tblpaymenttransaction_fkinvoiceid (fkinvoiceid),
  key tblpaymenttransaction_fkhospitalid (fkhospitalid),
  key tblpaymenttransaction_datepayment (datepayment),
  constraint tblpaymenttransaction_fkinvoiceid foreign key(fkinvoiceid) references tblinvoice(pkid),
  constraint tblpaymenttransaction_fkhospitalid foreign key(fkhospitalid) references tblhospital(pkid)
)engine=innodb default charset=utf8;


alter table tblhospital drop foreign key tblhospital_fkhospitalplanid;
alter table tblhospital drop column fkhospitalplanid;
drop table tblhospitalplan;

create table tblhospitalplanhistory (
  pkid int(10) unsigned not null auto_increment,
  fkhospitalid int(10) unsigned not null,
  fkplanid int(10) unsigned not null,
  datecreate int(10) not null,
  primary key (pkid),
  key tblhospitalplanhistory_fkhospitalid (fkhospitalid),
  key tblhospitalplanhistory_fkplanid (fkplanid),
  constraint tblhospitalplanhistory_fkhospitalid foreign key(fkhospitalid) references tblhospital(pkid),
  constraint tblhospitalplanhistory_fkplanid foreign key(fkplanid) references tblplan(pkid)
)engine=innodb default charset=utf8;

alter table tblhospital
add column fkplanid int(10) unsigned NULL;

alter table tblhospital
add constraint tblhospital_fkplanid
foreign key (fkplanid)
references tblplan(pkid);


alter table tblhospital
add column fkstateid int(10) unsigned NULL;

alter table tblhospital
add constraint tblhospital_fkstateid
foreign key (fkstateid)
references tblstate(pkid);

alter table tblhospital
add column fkcityid int(10) unsigned NULL;

alter table tblhospital
add constraint tblhospital_fkcityid
foreign key (fkcityid)
references tblcity(pkid);

alter table tblhospital
add column txtaddress text default null;

alter table tblhospital
add column txtpincode varchar(6) default null;

alter table tbluser drop column ishospitaladmin;

alter table tbltariff
add column isdefaulttariff bit(1) default 0;

alter table tblappointmentbook
add column iscancel bit(1) default 0;


alter table tblappointmentdays
add column numbermonth smallint default null;


alter table tblappointmentdays
add column numberdate json default null;

alter table tblappointmentdays modify numdays int(2) null;


create table tblleave (
  pkid int(10) unsigned not null auto_increment,
  fkdoctorid int(10) unsigned not null,
  fkhospitalid int(10) unsigned not null,
  datestart int(10) unsigned not null,
  dateend int(10) not null,
  dateleave json default null,
  datecreate int(10) not null,
  primary key (pkid),
  key tblleave_fkdoctorid (fkdoctorid),
  key tblleave_fkhospitalid (fkhospitalid),
  key tblleave_datestart (datestart),
  key tblleave_dateend (dateend),
  key tblleave_datecreate (datecreate),
  constraint tblleave_fkdoctorid foreign key(fkdoctorid) references tbluser(pkid),
  constraint tblleave_fkhospitalid foreign key(fkhospitalid) references tblhospital(pkid)
)engine=innodb default charset=utf8;


create table tblleaveshift(
  	fkleaveid int(10) unsigned not null,
  	fkshiftid int(10) unsigned not null,
  	primary key(fkleaveid,fkshiftid),
  	key tblleaveshift_fkleaveid (fkleaveid),
  	key tblleaveshift_fkshiftid (fkshiftid),
    constraint tblleaveshift_fkdoctorid foreign key(fkleaveid) references tblleave(pkid),
  constraint tblleaveshift_fkshiftid foreign key(fkshiftid) references tblshift(pkid)
)engine=innodb default charset=utf8;

/*14/08/2019*/

alter table tblhospital add column txtkioskid varchar(8) null;
create index index_tblhospital_txtkioskid on tblhospital(txtkioskid);

alter table tblusersession add column isotpsession bit(1) not null default 0;

alter table tblappointmentqueue
add column fkdoctorid int(10) unsigned NULL;

alter table tblappointmentqueue
add constraint tblappointmentqueue_fkdoctorid
foreign key (fkdoctorid)
references tbluser(pkid);

create index index_tblappointmentqueue_fkdoctorid on tblappointmentqueue(fkdoctorid);

/*19/08/2019  Hospital Mapping Change in user table*/

ALTER TABLE tblappointmentsetup CHANGE isallday isdayWise bit(1) not null;
ALTER TABLE tbluser DROP foreign key tbluser_fkhospitalid;
ALTER TABLE tbluser DROP column fkhospitalid;

create table tbluserhospital(
  	fkuserid int(10) unsigned not null,
  	fkhospitalid int(10) unsigned not null,
  	primary key(fkuserid,fkhospitalid),
  	key tbluserhospital_fkuserid (fkuserid),
  	key tbluserhospital_fkhospitalid (fkhospitalid),
    constraint tbluserhospital_fkuserid foreign key(fkuserid) references tbluser(pkid),
  constraint tbluserhospital_fkhospitalid foreign key(fkhospitalid) references tblhospital(pkid)
)engine=innodb default charset=utf8;

alter table tblusersession drop key txtdevicecookie;


alter table tblfile
add column fkhospitalid int(10) unsigned NULL;

alter table tblfile
add constraint tblfile_fkhospitalid
foreign key (fkhospitalid)
references tblhospital(pkid);

create index index_tblfile_fkhospitalid on tblfile(fkhospitalid);

alter table tblsmscontent modify column fkhospitalid int(10) unsigned null;
alter table tblsmstransaction modify column fkhospitalid int(10) unsigned null;

alter table tblappointmentqueue
add column fkshiftid int(10) unsigned NULL;

alter table tblappointmentqueue
add constraint tblappointmentqueue_fkshiftid
foreign key (fkshiftid)
references tblshift(pkid);

create index index_tblappointmentqueue_fkshiftid on tblappointmentqueue(fkshiftid);



create table tblreasonforvisit (
  pkid int(10) unsigned not null auto_increment,
  lockversion int(10) unsigned not null,
  txtname varchar(50) not null,
  txtdescription text null,
  fkdoctorid int(10) unsigned not null,
  fkhospitalid int(10) unsigned not null,
  fkcreateby int(10) unsigned default null,
  datecreate int(10) not null,
  fkupdateby int(10) unsigned default null,
  dateupdate int(10) default null,
  isactive bit(1) default 1,
  fkactchangeby int(10) unsigned default null,
  dateactchange int(10) default null,
  isarchive bit(1) default 0,
  fkarchiveby int(10) unsigned default null,
  datearchive int(10) default null,
  primary key (pkid),
  key tblreasonforvisit_fkcreateby (fkcreateby),
  key tblreasonforvisit_fkupdateby (fkupdateby),
  key tblreasonforvisit_fkactchangeby (fkactchangeby),
  key tblreasonforvisit_fkarchiveby (fkarchiveby),
  key tblreasonforvisit_isarchive (isarchive),
  key tblreasonforvisit_isactive (isactive),
  key tblreasonforvisit_fkhospitalid (fkhospitalid),
  key tblreasonforvisit_fkdoctorid (fkdoctorid),
  constraint tblreasonforvisit_fkcreateby foreign key(fkcreateby) references tbluser(pkid),
  constraint tblreasonforvisit_fkupdateby foreign key(fkupdateby) references tbluser(pkid),
  constraint tblreasonforvisit_fkactchangeby foreign key(fkactchangeby) references tbluser(pkid),
  constraint tblreasonforvisit_fkarchiveby foreign key(fkarchiveby) references tbluser(pkid),
  constraint tblreasonforvisit_fkdoctorid foreign key(fkdoctorid) references tbluser(pkid),
  constraint tblreasonforvisit_fkhospitalid foreign key(fkhospitalid) references tblhospital(pkid)
)engine=innodb default charset=utf8;


create table tblpatientreasonforvisit (
  pkid int(10) unsigned not null auto_increment,
  fkreasonforvisitid int(10) unsigned not null,
  fkpatientid int(10) unsigned not null,
  fkdoctorid int(10) unsigned not null,
  fkhospitalid int(10) unsigned not null,
  fkappointmentbookid int(10) unsigned not null,
  primary key (pkid),
  key tblpatientreasonforvisit_fkreasonforvisitid (fkreasonforvisitid),
  key tblpatientreasonforvisit_fkpatientid (fkpatientid),
  key tblpatientreasonforvisit_fkhospitalid (fkhospitalid),
  key tblpatientreasonforvisit_fkdoctorid (fkdoctorid),
  key tblpatientreasonforvisit_fkappointmentbookid (fkappointmentbookid),
  constraint tblpatientreasonforvisit_fkreasonforvisitid foreign key(fkreasonforvisitid) references tblreasonforvisit(pkid),
  constraint tblpatientreasonforvisit_fkpatientid foreign key(fkpatientid) references tblpatientdetails(pkid),
  constraint tblpatientreasonforvisit_fkdoctorid foreign key(fkdoctorid) references tbluser(pkid),
  constraint tblpatientreasonforvisit_fkhospitalid foreign key(fkhospitalid) references tblhospital(pkid),
  constraint tblpatientreasonforvisit_fkappointmentbookid foreign key(fkappointmentbookid) references tblappointmentbook(pkid)
)engine=innodb default charset=utf8;


create table tbltreatmenthistory (
  pkid int(10) unsigned not null auto_increment,
  lockversion int(10) unsigned not null,
  txtname varchar(50) not null,
  txtdescription text null,
  fkdoctorid int(10) unsigned not null,
  fkhospitalid int(10) unsigned not null,
  fkcreateby int(10) unsigned default null,
  datecreate int(10) not null,
  fkupdateby int(10) unsigned default null,
  dateupdate int(10) default null,
  isactive bit(1) default 1,
  fkactchangeby int(10) unsigned default null,
  dateactchange int(10) default null,
  isarchive bit(1) default 0,
  fkarchiveby int(10) unsigned default null,
  datearchive int(10) default null,
  primary key (pkid),
  key tbltreatmenthistory_fkcreateby (fkcreateby),
  key tbltreatmenthistory_fkupdateby (fkupdateby),
  key tbltreatmenthistory_fkactchangeby (fkactchangeby),
  key tbltreatmenthistory_fkarchiveby (fkarchiveby),
  key tbltreatmenthistory_isarchive (isarchive),
  key tbltreatmenthistory_isactive (isactive),
  key tbltreatmenthistory_fkhospitalid (fkhospitalid),
  key tbltreatmenthistory_fkdoctorid (fkdoctorid),
  constraint tbltreatmenthistory_fkcreateby foreign key(fkcreateby) references tbluser(pkid),
  constraint tbltreatmenthistory_fkupdateby foreign key(fkupdateby) references tbluser(pkid),
  constraint tbltreatmenthistory_fkactchangeby foreign key(fkactchangeby) references tbluser(pkid),
  constraint tbltreatmenthistory_fkarchiveby foreign key(fkarchiveby) references tbluser(pkid),
  constraint tbltreatmenthistory_fkdoctorid foreign key(fkdoctorid) references tbluser(pkid),
  constraint tbltreatmenthistory_fkhospitalid foreign key(fkhospitalid) references tblhospital(pkid)
)engine=innodb default charset=utf8;

create table tblpatienttreatmenthistory (
  pkid int(10) unsigned not null auto_increment,
  fktreatmenthistoryid int(10) unsigned not null,
  fkpatientid int(10) unsigned not null,
  fkdoctorid int(10) unsigned not null,
  fkhospitalid int(10) unsigned not null,
  primary key (pkid),
  key tblpatienttreatmenthistory_fktreatmenthistoryid (fktreatmenthistoryid),
  key tblpatienttreatmenthistory_fkpatientid (fkpatientid),
  key tblpatienttreatmenthistory_fkhospitalid (fkhospitalid),
  key tblpatienttreatmenthistory_fkdoctorid (fkdoctorid),
  constraint tblpatienttreatmenthistory_fktreatmenthistoryid foreign key(fktreatmenthistoryid) references tbltreatmenthistory(pkid),
  constraint tblpatienttreatmenthistory_fkpatientid foreign key(fkpatientid) references tblpatientdetails(pkid),
  constraint tblpatienttreatmenthistory_fkdoctorid foreign key(fkdoctorid) references tbluser(pkid),
  constraint tblpatienttreatmenthistory_fkhospitalid foreign key(fkhospitalid) references tblhospital(pkid)
)engine=innodb default charset=utf8;

create table tblpersonaldetail (
  pkid int(10) unsigned not null auto_increment,
  lockversion int(10) unsigned not null,
  txtname varchar(50) not null,
  txtdescription text null,
  fkdoctorid int(10) unsigned not null,
  fkhospitalid int(10) unsigned not null,
  fkcreateby int(10) unsigned default null,
  datecreate int(10) not null,
  fkupdateby int(10) unsigned default null,
  dateupdate int(10) default null,
  isactive bit(1) default 1,
  fkactchangeby int(10) unsigned default null,
  dateactchange int(10) default null,
  isarchive bit(1) default 0,
  fkarchiveby int(10) unsigned default null,
  datearchive int(10) default null,
  primary key (pkid),
  key tblpersonaldetail_fkcreateby (fkcreateby),
  key tblpersonaldetail_fkupdateby (fkupdateby),
  key tblpersonaldetail_fkactchangeby (fkactchangeby),
  key tblpersonaldetail_fkarchiveby (fkarchiveby),
  key tblpersonaldetail_isarchive (isarchive),
  key tblpersonaldetail_isactive (isactive),
  key tblpersonaldetail_fkhospitalid (fkhospitalid),
  key tblpersonaldetail_fkdoctorid (fkdoctorid),
  constraint tblpersonaldetail_fkcreateby foreign key(fkcreateby) references tbluser(pkid),
  constraint tblpersonaldetail_fkupdateby foreign key(fkupdateby) references tbluser(pkid),
  constraint tblpersonaldetail_fkactchangeby foreign key(fkactchangeby) references tbluser(pkid),
  constraint tblpersonaldetail_fkarchiveby foreign key(fkarchiveby) references tbluser(pkid),
  constraint tblpersonaldetail_fkdoctorid foreign key(fkdoctorid) references tbluser(pkid),
  constraint tblpersonaldetail_fkhospitalid foreign key(fkhospitalid) references tblhospital(pkid)
)engine=innodb default charset=utf8;


create table tblpatientpersonaldetails (
  pkid int(10) unsigned not null auto_increment,
  fkpersonaldetailid int(10) unsigned not null,
  fkpatientid int(10) unsigned not null,
  fkdoctorid int(10) unsigned not null,
  fkhospitalid int(10) unsigned not null,
  primary key (pkid),
  key tblpatientpersonaldetails_fkpersonaldetailid (fkpersonaldetailid),
  key tblpatientpersonaldetails_fkpatientid (fkpatientid),
  key tblpatientpersonaldetails_fkhospitalid (fkhospitalid),
  key tblpatientpersonaldetails_fkdoctorid (fkdoctorid),
  constraint tblpatientpersonaldetails_fkpersonaldetailid foreign key(fkpersonaldetailid) references tblpersonaldetail(pkid),
  constraint tblpatientpersonaldetails_fkpatientid foreign key(fkpatientid) references tblpatientdetails(pkid),
  constraint tblpatientpersonaldetails_fkdoctorid foreign key(fkdoctorid) references tbluser(pkid),
  constraint tblpatientpersonaldetails_fkhospitalid foreign key(fkhospitalid) references tblhospital(pkid)
)engine=innodb default charset=utf8;


create table tblexamination (
  pkid int(10) unsigned not null auto_increment,
  lockversion int(10) unsigned not null,
  txtname varchar(50) not null,
  txtdescription text null,
  fkdoctorid int(10) unsigned not null,
  fkhospitalid int(10) unsigned not null,
  fkcreateby int(10) unsigned default null,
  datecreate int(10) not null,
  fkupdateby int(10) unsigned default null,
  dateupdate int(10) default null,
  isactive bit(1) default 1,
  fkactchangeby int(10) unsigned default null,
  dateactchange int(10) default null,
  isarchive bit(1) default 0,
  fkarchiveby int(10) unsigned default null,
  datearchive int(10) default null,
  primary key (pkid),
  key tblexamination_fkcreateby (fkcreateby),
  key tblexamination_fkupdateby (fkupdateby),
  key tblexamination_fkactchangeby (fkactchangeby),
  key tblexamination_fkarchiveby (fkarchiveby),
  key tblexamination_isarchive (isarchive),
  key tblexamination_isactive (isactive),
  key tblexamination_fkhospitalid (fkhospitalid),
  key tblexamination_fkdoctorid (fkdoctorid),
  constraint tblexamination_fkcreateby foreign key(fkcreateby) references tbluser(pkid),
  constraint tblexamination_fkupdateby foreign key(fkupdateby) references tbluser(pkid),
  constraint tblexamination_fkactchangeby foreign key(fkactchangeby) references tbluser(pkid),
  constraint tblexamination_fkarchiveby foreign key(fkarchiveby) references tbluser(pkid),
  constraint tblexamination_fkdoctorid foreign key(fkdoctorid) references tbluser(pkid),
  constraint tblexamination_fkhospitalid foreign key(fkhospitalid) references tblhospital(pkid)
)engine=innodb default charset=utf8;


create table tblpatientexamination (
  pkid int(10) unsigned not null auto_increment,
  fkexaminationid int(10) unsigned not null,
  fkpatientid int(10) unsigned not null,
  fkdoctorid int(10) unsigned not null,
  fkhospitalid int(10) unsigned not null,
  fkappointmentbookid int(10) unsigned not null,
  primary key (pkid),
  key tblpatientexamination_fkexaminationid (fkexaminationid),
  key tblpatientexamination_fkpatientid (fkpatientid),
  key tblpatientexamination_fkhospitalid (fkhospitalid),
  key tblpatientexamination_fkdoctorid (fkdoctorid),
  key tblpatientexamination_fkappointmentbookid (fkappointmentbookid),
  constraint tblpatientexamination_fkexaminationid foreign key(fkexaminationid) references tblexamination(pkid),
  constraint tblpatientexamination_fkpatientid foreign key(fkpatientid) references tblpatientdetails(pkid),
  constraint tblpatientexamination_fkdoctorid foreign key(fkdoctorid) references tbluser(pkid),
  constraint tblpatientexamination_fkhospitalid foreign key(fkhospitalid) references tblhospital(pkid),
  constraint tblpatientexamination_fkappointmentbookid foreign key(fkappointmentbookid) references tblappointmentbook(pkid)
)engine=innodb default charset=utf8;


create table tbldiagnosis (
  pkid int(10) unsigned not null auto_increment,
  lockversion int(10) unsigned not null,
  txtname varchar(50) not null,
  txtdescription text null,
  fkdoctorid int(10) unsigned not null,
  fkhospitalid int(10) unsigned not null,
  fkcreateby int(10) unsigned default null,
  datecreate int(10) not null,
  fkupdateby int(10) unsigned default null,
  dateupdate int(10) default null,
  isactive bit(1) default 1,
  fkactchangeby int(10) unsigned default null,
  dateactchange int(10) default null,
  isarchive bit(1) default 0,
  fkarchiveby int(10) unsigned default null,
  datearchive int(10) default null,
  primary key (pkid),
  key tbldiagnosis_fkcreateby (fkcreateby),
  key tbldiagnosis_fkupdateby (fkupdateby),
  key tbldiagnosis_fkactchangeby (fkactchangeby),
  key tbldiagnosis_fkarchiveby (fkarchiveby),
  key tbldiagnosis_isarchive (isarchive),
  key tbldiagnosis_isactive (isactive),
  key tbldiagnosis_fkhospitalid (fkhospitalid),
  key tbldiagnosis_fkdoctorid (fkdoctorid),
  constraint tbldiagnosis_fkcreateby foreign key(fkcreateby) references tbluser(pkid),
  constraint tbldiagnosis_fkupdateby foreign key(fkupdateby) references tbluser(pkid),
  constraint tbldiagnosis_fkactchangeby foreign key(fkactchangeby) references tbluser(pkid),
  constraint tbldiagnosis_fkarchiveby foreign key(fkarchiveby) references tbluser(pkid),
  constraint tbldiagnosis_fkdoctorid foreign key(fkdoctorid) references tbluser(pkid),
  constraint tbldiagnosis_fkhospitalid foreign key(fkhospitalid) references tblhospital(pkid)
)engine=innodb default charset=utf8;


create table tblpatientdiagnosis (
  pkid int(10) unsigned not null auto_increment,
  fkdiagnosisid int(10) unsigned not null,
  fkpatientid int(10) unsigned not null,
  fkdoctorid int(10) unsigned not null,
  fkhospitalid int(10) unsigned not null,
  fkappointmentbookid int(10) unsigned not null,
  primary key (pkid),
  key tblpatientdiagnosis_fkdiagnosisid (fkdiagnosisid),
  key tblpatientdiagnosis_fkpatientid (fkpatientid),
  key tblpatientdiagnosis_fkhospitalid (fkhospitalid),
  key tblpatientdiagnosis_fkdoctorid (fkdoctorid),
  key tblpatientdiagnosis_fkappointmentbookid (fkappointmentbookid),
  constraint tblpatientdiagnosis_fkdiagnosisid foreign key(fkdiagnosisid) references tbldiagnosis(pkid),
  constraint tblpatientdiagnosis_fkpatientid foreign key(fkpatientid) references tblpatientdetails(pkid),
  constraint tblpatientdiagnosis_fkdoctorid foreign key(fkdoctorid) references tbluser(pkid),
  constraint tblpatientdiagnosis_fkhospitalid foreign key(fkhospitalid) references tblhospital(pkid),
  constraint tblpatientdiagnosis_fkappointmentbookid foreign key(fkappointmentbookid) references tblappointmentbook(pkid)
)engine=innodb default charset=utf8;


create table tblvital (
  pkid int(10) unsigned not null auto_increment,
  lockversion int(10) unsigned not null,
  txtname varchar(50) not null,
  txtunit varchar(10) null,
  fkdoctorid int(10) unsigned not null,
  fkhospitalid int(10) unsigned not null,
  fkcreateby int(10) unsigned default null,
  datecreate int(10) not null,
  fkupdateby int(10) unsigned default null,
  dateupdate int(10) default null,
  isactive bit(1) default 1,
  fkactchangeby int(10) unsigned default null,
  dateactchange int(10) default null,
  isarchive bit(1) default 0,
  fkarchiveby int(10) unsigned default null,
  datearchive int(10) default null,
  primary key (pkid),
  key tblvital_fkcreateby (fkcreateby),
  key tblvital_fkupdateby (fkupdateby),
  key tblvital_fkactchangeby (fkactchangeby),
  key tblvital_fkarchiveby (fkarchiveby),
  key tblvital_isarchive (isarchive),
  key tblvital_isactive (isactive),
  key tblvital_fkhospitalid (fkhospitalid),
  key tblvital_fkdoctorid (fkdoctorid),
  constraint tblvital_fkcreateby foreign key(fkcreateby) references tbluser(pkid),
  constraint tblvital_fkupdateby foreign key(fkupdateby) references tbluser(pkid),
  constraint tblvital_fkactchangeby foreign key(fkactchangeby) references tbluser(pkid),
  constraint tblvital_fkarchiveby foreign key(fkarchiveby) references tbluser(pkid),
  constraint tblvital_fkdoctorid foreign key(fkdoctorid) references tbluser(pkid),
  constraint tblvital_fkhospitalid foreign key(fkhospitalid) references tblhospital(pkid)
)engine=innodb default charset=utf8;


create table tblpatientvital (
  pkid int(10) unsigned not null auto_increment,
  fkvitalid int(10) unsigned not null,
  fkpatientid int(10) unsigned not null,
  txtvalue varchar(10) null,
  fkdoctorid int(10) unsigned not null,
  fkhospitalid int(10) unsigned not null,
  fkappointmentbookid int(10) unsigned not null,
  primary key (pkid),
  key tblpatientvital_fkvitalid (fkvitalid),
  key tblpatientvital_fkpatientid (fkpatientid),
  key tblpatientvital_fkhospitalid (fkhospitalid),
  key tblpatientvital_fkdoctorid (fkdoctorid),
  key tblpatientvital_fkappointmentbookid (fkappointmentbookid),
  constraint tblpatientvital_fkvitalid foreign key(fkvitalid) references tblvital(pkid),
  constraint tblpatientvital_fkpatientid foreign key(fkpatientid) references tblpatientdetails(pkid),
  constraint tblpatientvital_fkdoctorid foreign key(fkdoctorid) references tbluser(pkid),
  constraint tblpatientvital_fkhospitalid foreign key(fkhospitalid) references tblhospital(pkid),
  constraint tblpatientvital_fkappointmentbookid foreign key(fkappointmentbookid) references tblappointmentbook(pkid)
)engine=innodb default charset=utf8;



create table tblinvestigation (
  pkid int(10) unsigned not null auto_increment,
  lockversion int(10) unsigned not null,
  txtname varchar(50) not null,
  txtdescription text null,
  fkdoctorid int(10) unsigned not null,
  fkhospitalid int(10) unsigned not null,
  fkcreateby int(10) unsigned default null,
  datecreate int(10) not null,
  fkupdateby int(10) unsigned default null,
  dateupdate int(10) default null,
  isactive bit(1) default 1,
  fkactchangeby int(10) unsigned default null,
  dateactchange int(10) default null,
  isarchive bit(1) default 0,
  fkarchiveby int(10) unsigned default null,
  datearchive int(10) default null,
  primary key (pkid),
  key tblinvestigation_fkcreateby (fkcreateby),
  key tblinvestigation_fkupdateby (fkupdateby),
  key tblinvestigation_fkactchangeby (fkactchangeby),
  key tblinvestigation_fkarchiveby (fkarchiveby),
  key tblinvestigation_isarchive (isarchive),
  key tblinvestigation_isactive (isactive),
  key tblinvestigation_fkhospitalid (fkhospitalid),
  key tblinvestigation_fkdoctorid (fkdoctorid),
  constraint tblinvestigation_fkcreateby foreign key(fkcreateby) references tbluser(pkid),
  constraint tblinvestigation_fkupdateby foreign key(fkupdateby) references tbluser(pkid),
  constraint tblinvestigation_fkactchangeby foreign key(fkactchangeby) references tbluser(pkid),
  constraint tblinvestigation_fkarchiveby foreign key(fkarchiveby) references tbluser(pkid),
  constraint tblinvestigation_fkdoctorid foreign key(fkdoctorid) references tbluser(pkid),
  constraint tblinvestigation_fkhospitalid foreign key(fkhospitalid) references tblhospital(pkid)
)engine=innodb default charset=utf8;


create table tblpatientinvestigation (
  pkid int(10) unsigned not null auto_increment,
  fkinvestigationid int(10) unsigned not null,
  fkpatientid int(10) unsigned not null,
  txtvalue varchar(10) null,
  fkdoctorid int(10) unsigned not null,
  fkhospitalid int(10) unsigned not null,
  fkappointmentbookid int(10) unsigned not null,
  primary key (pkid),
  key tblpatientinvestigation_fkinvestigationid (fkinvestigationid),
  key tblpatientinvestigation_fkpatientid (fkpatientid),
  key tblpatientinvestigation_fkhospitalid (fkhospitalid),
  key tblpatientinvestigation_fkdoctorid (fkdoctorid),
  key tblpatientinvestigation_fkappointmentbookid (fkappointmentbookid),
  constraint tblpatientinvestigation_fkinvestigationid foreign key(fkinvestigationid) references tblinvestigation(pkid),
  constraint tblpatientinvestigation_fkpatientid foreign key(fkpatientid) references tblpatientdetails(pkid),
  constraint tblpatientinvestigation_fkdoctorid foreign key(fkdoctorid) references tbluser(pkid),
  constraint tblpatientinvestigation_fkhospitalid foreign key(fkhospitalid) references tblhospital(pkid),
  constraint tblpatientinvestigation_fkappointmentbookid foreign key(fkappointmentbookid) references tblappointmentbook(pkid)
)engine=innodb default charset=utf8;

create table tblhomecare (
  pkid int(10) unsigned not null auto_increment,
  lockversion int(10) unsigned not null,
  txtname varchar(50) not null,
  txtunit varchar(10) null,
  fkdoctorid int(10) unsigned not null,
  fkhospitalid int(10) unsigned not null,
  fkcreateby int(10) unsigned default null,
  datecreate int(10) not null,
  fkupdateby int(10) unsigned default null,
  dateupdate int(10) default null,
  isactive bit(1) default 1,
  fkactchangeby int(10) unsigned default null,
  dateactchange int(10) default null,
  isarchive bit(1) default 0,
  fkarchiveby int(10) unsigned default null,
  datearchive int(10) default null,
  primary key (pkid),
  key tblhomecare_fkcreateby (fkcreateby),
  key tblhomecare_fkupdateby (fkupdateby),
  key tblhomecare_fkactchangeby (fkactchangeby),
  key tblhomecare_fkarchiveby (fkarchiveby),
  key tblhomecare_isarchive (isarchive),
  key tblhomecare_isactive (isactive),
  key tblhomecare_fkhospitalid (fkhospitalid),
  key tblhomecare_fkdoctorid (fkdoctorid),
  constraint tblhomecare_fkcreateby foreign key(fkcreateby) references tbluser(pkid),
  constraint tblhomecare_fkupdateby foreign key(fkupdateby) references tbluser(pkid),
  constraint tblhomecare_fkactchangeby foreign key(fkactchangeby) references tbluser(pkid),
  constraint tblhomecare_fkarchiveby foreign key(fkarchiveby) references tbluser(pkid),
  constraint tblhomecare_fkdoctorid foreign key(fkdoctorid) references tbluser(pkid),
  constraint tblhomecare_fkhospitalid foreign key(fkhospitalid) references tblhospital(pkid)
)engine=innodb default charset=utf8;


create table tblpatienthomecare (
  pkid int(10) unsigned not null auto_increment,
  fkhomecareid int(10) unsigned not null,
  fkpatientid int(10) unsigned not null,
  txtvalue varchar(10) null,
  fkdoctorid int(10) unsigned not null,
  fkhospitalid int(10) unsigned not null,
  fkappointmentbookid int(10) unsigned not null,
  primary key (pkid),
  key tblpatienthomecare_fkhomecareid (fkhomecareid),
  key tblpatienthomecare_fkpatientid (fkpatientid),
  key tblpatienthomecare_fkhospitalid (fkhospitalid),
  key tblpatienthomecare_fkdoctorid (fkdoctorid),
  key tblpatienthomecare_fkappointmentbookid (fkappointmentbookid),
  constraint tblpatienthomecare_fkhomecareid foreign key(fkhomecareid) references tblhomecare(pkid),
  constraint tblpatienthomecare_fkpatientid foreign key(fkpatientid) references tblpatientdetails(pkid),
  constraint tblpatienthomecare_fkdoctorid foreign key(fkdoctorid) references tbluser(pkid),
  constraint tblpatienthomecare_fkhospitalid foreign key(fkhospitalid) references tblhospital(pkid),
  constraint tblpatienthomecare_fkappointmentbookid foreign key(fkappointmentbookid) references tblappointmentbook(pkid)
)engine=innodb default charset=utf8;

alter table tblpatienthomecare
add column txtspecialinstruction text null;


/*
 * 14/09/2019
 */

drop table tblservicecategory;
alter table tbltariffrate drop column txtname;

alter table tbltariffrate drop foreign key tbltariffrate_fkdepartmentid;
alter table tbltariffrate change column fkdepartmentid fkdepartmentid int(10) unsigned null ;
alter table tbltariffrate add constraint tbltariffrate_fkdepartmentid foreign key (fkdepartmentid) references tbldepartment(pkid);

alter table tbltariffrate drop foreign key tbltariffrate_fkdoctorid;
alter table tbltariffrate change column fkdoctorid fkdoctorid int(10) unsigned null ;
alter table tbltariffrate add constraint tbltariffrate_fkdoctorid foreign key (fkdoctorid) references tbluser(pkid);

alter table tbltariffrate drop foreign key tbltariffrate_fkhospitalid;
alter table tbltariffrate change column fkhospitalid fkhospitalid int(10) unsigned not null ;
alter table tbltariffrate add constraint tbltariffrate_fkhospitalid foreign key (fkhospitalid) references tblhospital(pkid);

/*
 * 17/09/2019
 * */

alter table tblshift add column numberstarthour varchar(3) not null after txtname;
alter table tblshift add column numberstartminute varchar(3) not null after numberstarthour;
alter table tblshift add column numberstartsecond varchar(3) not null after numberstartminute;

alter table tblshift add column numberendhour varchar(3) not null after numberstartsecond;
alter table tblshift add column numberendminute varchar(3) not null after numberendhour;
alter table tblshift add column numberendsecond varchar(3) not null after numberendminute;

alter table tblshift change column txtstarttime txtstarttime varchar(20) null;
alter table tblshift change column txtendtime txtendtime varchar(20) null;


/*
 * 18/09/2019
 * */
alter table tblappointmentbook add column dateshiftstart int(10) null after dateappointment;
alter table tblappointmentbook add key tblappointmentbook_dateshiftstart (dateshiftstart);
alter table tblappointmentbook add column dateshiftend int(10) null after dateshiftstart;
alter table tblappointmentbook add key tblappointmentbook_dateshiftend (dateshiftend);

alter table tblpatientreasonforvisit drop foreign key tblpatientreasonforvisit_fkreasonforvisitid;
alter table tblpatientreasonforvisit drop column fkreasonforvisitid;

alter table tblappointmentbook add column fkdepartmentid int(10) unsigned null after fktariffid;
alter table tblappointmentbook add key tblappointmentbook_fkdepartmentid (fkdepartmentid);
alter table tblappointmentbook add constraint tblappointmentbook_fkdepartmentid foreign key (fkdepartmentid) references tbldepartment(pkid);

alter table tblpatientreasonforvisit add column txtdescription text not null;
alter table tblpatientreasonforvisit add unique index tblpatientreasonforvisit_unique(fkpatientid, fkdoctorid, fkhospitalid, fkappointmentbookid);

alter table tblpatienttreatmenthistory add column txtdescription text not null;
alter table tblpatienttreatmenthistory add unique index tblpatienttreatmenthistory_unique(fkpatientid, fkdoctorid, fkhospitalid);
alter table tblpatienttreatmenthistory drop foreign key tblpatienttreatmenthistory_fktreatmenthistoryid;
alter table tblpatienttreatmenthistory drop column fktreatmenthistoryid;
delete from tblpatienttreatmenthistory;

alter table tblpatientexamination add column txtdescription text not null;
alter table tblpatientexamination add unique index tblpatientexamination_unique(fkpatientid, fkdoctorid, fkhospitalid, fkappointmentbookid);
alter table tblpatientexamination drop foreign key tblpatientexamination_fkexaminationid;
alter table tblpatientexamination drop column fkexaminationid;
delete from tblpatientexamination;

alter table tblpatientdiagnosis add column txtdescription text not null;
alter table tblpatientdiagnosis add unique index tblpatientdiagnosis_unique(fkpatientid, fkdoctorid, fkhospitalid, fkappointmentbookid);
alter table tblpatientdiagnosis drop foreign key tblpatientdiagnosis_fkdiagnosisid;
alter table tblpatientdiagnosis drop column fkdiagnosisid;
delete from tblpatientdiagnosis;

rename table tblpatientpersonaldetails to tblpatientpersonaldetail;
alter table tblpatientpersonaldetail add column txtdescription text not null;
alter table tblpatientpersonaldetail add unique index tblpatientpersonaldetail_unique(fkpatientid, fkdoctorid, fkhospitalid);
alter table tblpatientpersonaldetail drop foreign key tblpatientpersonaldetails_fkpersonaldetailid;
alter table tblpatientpersonaldetail drop column fkpersonaldetailid;
delete from tblpatientpersonaldetail;

alter table tblappointmentdays add column isnightshiftday bit(1) default 0;

alter table tblappointmentsetup drop column isdayWise;
alter table tblappointmentdays drop column numbermonth;
alter table tblappointmentdays drop column numberdate;

/*04/10/2019*/
alter table tblpatientvital drop foreign key tblpatientvital_fkvitalid;
alter table tblpatientvital drop column fkvitalid;
 
alter table tblpatientvital add column txtname varchar(50) null after fkpatientid;
alter table tblpatientvital add column txtunit varchar(10) null after txtname;

alter table tblvital drop column txtunit;

create table tblvitalfield (
  pkid int(10) unsigned not null auto_increment,
  fkvitalid int(10) unsigned not null,
  txtname varchar(50) not null,
  txtunit varchar(10) not null,
  primary key (pkid),
  key tblvitalfield_fkvitalid (fkvitalid),
  constraint tblvitalfield_fkvitalid foreign key(fkvitalid) references tblvital(pkid)
)engine=innodb default charset=utf8;

/*09/10/2019*/

drop table tblpatienthomecare;
drop table tblhomecare;

create table tblhomecare (
  pkid int(10) unsigned not null auto_increment,
  lockversion int(10) unsigned not null,
  txtname varchar(50) not null,
  fkdoctorid int(10) unsigned not null,
  fkhospitalid int(10) unsigned not null,
  fkcreateby int(10) unsigned default null,
  datecreate int(10) not null,
  fkupdateby int(10) unsigned default null,
  dateupdate int(10) default null,
  isactive bit(1) default 1,
  fkactchangeby int(10) unsigned default null,
  dateactchange int(10) default null,
  isarchive bit(1) default 0,
  fkarchiveby int(10) unsigned default null,
  datearchive int(10) default null,
  primary key (pkid),
  key tblhomecare_fkcreateby (fkcreateby),
  key tblhomecare_fkupdateby (fkupdateby),
  key tblhomecare_fkactchangeby (fkactchangeby),
  key tblhomecare_fkarchiveby (fkarchiveby),
  key tblhomecare_isarchive (isarchive),
  key tblhomecare_isactive (isactive),
  key tblhomecare_fkhospitalid (fkhospitalid),
  key tblhomecare_fkdoctorid (fkdoctorid),
  constraint tblhomecare_fkcreateby foreign key(fkcreateby) references tbluser(pkid),
  constraint tblhomecare_fkupdateby foreign key(fkupdateby) references tbluser(pkid),
  constraint tblhomecare_fkactchangeby foreign key(fkactchangeby) references tbluser(pkid),
  constraint tblhomecare_fkarchiveby foreign key(fkarchiveby) references tbluser(pkid),
  constraint tblhomecare_fkdoctorid foreign key(fkdoctorid) references tbluser(pkid),
  constraint tblhomecare_fkhospitalid foreign key(fkhospitalid) references tblhospital(pkid)
)engine=innodb default charset=utf8;


create table tblhomecarefield (
  pkid int(10) unsigned not null auto_increment,
  txtname varchar(50) not null,
  txtunit varchar(10) not null,
  fkhomecareid int(10) unsigned not null,
  primary key (pkid),
  key tblhomecarefield_fkhomecareid (fkhomecareid),
  constraint tblhomecarefield_fkhomecareid foreign key(fkhomecareid) references tblhomecare(pkid)
)engine=innodb default charset=utf8;

create table tblpatienthomecare(
  pkid int(10) unsigned not null auto_increment,
  txtname varchar(50) not null,
  txtunit varchar(10) not null,
  txtvalue varchar(10) null,
  fkpatientid int(10) unsigned not null,
  fkdoctorid int(10) unsigned not null,
  fkhospitalid int(10) unsigned not null,
  fkappointmentbookid int(10) unsigned not null,
  primary key (pkid),
  key tblpatienthomecare_fkpatientid (fkpatientid),
  key tblpatienthomecare_fkhospitalid (fkhospitalid),
  key tblpatienthomecare_fkdoctorid (fkdoctorid),
  key tblpatienthomecare_fkappointmentbookid (fkappointmentbookid),
  constraint tblpatienthomecare_fkpatientid foreign key(fkpatientid) references tblpatientdetails(pkid),
  constraint tblpatienthomecare_fkdoctorid foreign key(fkdoctorid) references tbluser(pkid),
  constraint tblpatienthomecare_fkhospitalid foreign key(fkhospitalid) references tblhospital(pkid),
  constraint tblpatienthomecare_fkappointmentbookid foreign key(fkappointmentbookid) references tblappointmentbook(pkid)
)engine=innodb default charset=utf8;

create table tblpatienthomecareinstruction(
  pkid int(10) unsigned not null auto_increment,
  txtspecialinstruction text not null,
  fkpatientid int(10) unsigned not null,
  fkdoctorid int(10) unsigned not null,
  fkhospitalid int(10) unsigned not null,
  fkappointmentbookid int(10) unsigned not null,
  primary key (pkid),
  key tblpatienthomecareinstruction_fkpatientid (fkpatientid),
  key tblpatienthomecareinstruction_fkhospitalid (fkhospitalid),
  key tblpatienthomecareinstruction_fkdoctorid (fkdoctorid),
  key tblpatienthomecareinstruction_fkappointmentbookid (fkappointmentbookid),
  constraint tblpatienthomecareinstruction_fkpatientid foreign key(fkpatientid) references tblpatientdetails(pkid),
  constraint tblpatienthomecareinstruction_fkdoctorid foreign key(fkdoctorid) references tbluser(pkid),
  constraint tblpatienthomecareinstruction_fkhospitalid foreign key(fkhospitalid) references tblhospital(pkid),
  constraint tblpatienthomecareinstruction_fkappointmentbookid foreign key(fkappointmentbookid) references tblappointmentbook(pkid)
)engine=innodb default charset=utf8;

/*10/10/2019*/
drop table tblpatientinvestigation;
drop table tblinvestigation;

create table tblinvestigation (
  pkid int(10) unsigned not null auto_increment,
  lockversion int(10) unsigned not null,
  txttemplatename varchar(50) null,
  txtname varchar(50) not null,
  numberlevel smallint not null,
  fkrootid int(10) unsigned null,
  fkdoctorid int(10) unsigned not null,
  fkhospitalid int(10) unsigned not null,
  fkcreateby int(10) unsigned default null,
  datecreate int(10) not null,
  fkupdateby int(10) unsigned default null,
  dateupdate int(10) default null,
  isactive bit(1) default 1,
  fkactchangeby int(10) unsigned default null,
  dateactchange int(10) default null,
  isarchive bit(1) default 0,
  fkarchiveby int(10) unsigned default null,
  datearchive int(10) default null,
  primary key (pkid),
  key tblinvestigation_fkcreateby (fkcreateby),
  key tblinvestigation_fkupdateby (fkupdateby),
  key tblinvestigation_fkactchangeby (fkactchangeby),
  key tblinvestigation_fkarchiveby (fkarchiveby),
  key tblinvestigation_isarchive (isarchive),
  key tblinvestigation_isactive (isactive),
  key tblinvestigation_fkhospitalid (fkhospitalid),
  key tblinvestigation_fkdoctorid (fkdoctorid),
  key tblinvestigation_fkrootid (fkrootid),
  constraint tblinvestigation_fkcreateby foreign key(fkcreateby) references tbluser(pkid),
  constraint tblinvestigation_fkupdateby foreign key(fkupdateby) references tbluser(pkid),
  constraint tblinvestigation_fkactchangeby foreign key(fkactchangeby) references tbluser(pkid),
  constraint tblinvestigation_fkarchiveby foreign key(fkarchiveby) references tbluser(pkid),
  constraint tblinvestigation_fkdoctorid foreign key(fkdoctorid) references tbluser(pkid),
   constraint tblinvestigation_fkrootid foreign key(fkrootid) references tblinvestigation(pkid),
  constraint tblinvestigation_fkhospitalid foreign key(fkhospitalid) references tblhospital(pkid)
)engine=innodb default charset=utf8;

create table tblinvestigationparameter (
  pkid int(10) unsigned not null auto_increment,
  fkinvestigationid int(10) unsigned not null,
  txtparametername varchar(50) not null,
  txtparameterunit varchar(10) not null,
  txtparameterrange varchar(50) not null,
  primary key (pkid),
  key tblinvestigationparameter_fkinvestigationid (fkinvestigationid),
  constraint tblinvestigationparameter_fkinvestigationid foreign key(fkinvestigationid) references tblinvestigation(pkid)
)engine=innodb default charset=utf8;


create table tblpatientinvestigation(
  pkid int(10) unsigned not null auto_increment,
  fkinvestigationparameterid int(10) unsigned not null,
  txtvalue varchar(50) not null,
  fkpatientid int(10) unsigned not null,
  fkdoctorid int(10) unsigned not null,
  fkhospitalid int(10) unsigned not null,
  fkappointmentbookid int(10) unsigned not null,
  primary key (pkid),
  key tblpatientinvestigation_fkpatientid (fkpatientid),
  key tblpatientinvestigation_fkhospitalid (fkhospitalid),
  key tblpatientinvestigation_fkdoctorid (fkdoctorid),
  key tblpatientinvestigation_fkappointmentbookid (fkappointmentbookid),
  key tblpatientinvestigation_fkinvestigationparameterid (fkinvestigationparameterid),
  constraint tblpatientinvestigation_fkpatientid foreign key(fkpatientid) references tblpatientdetails(pkid),
  constraint tblpatientinvestigation_fkdoctorid foreign key(fkdoctorid) references tbluser(pkid),
  constraint tblpatientinvestigation_fkhospitalid foreign key(fkhospitalid) references tblhospital(pkid),
  constraint tblpatientinvestigation_fkinvestigationparameterid foreign key(fkinvestigationparameterid) references tblinvestigationparameter(pkid),
  constraint tblpatientinvestigation_fkappointmentbookid foreign key(fkappointmentbookid) references tblappointmentbook(pkid)
)engine=innodb default charset=utf8;


/*11/10/2019*/

drop table tblpatientvital;
drop table tblvitalfield;
drop table tblvital;

create table tblvital (
  pkid int(10) unsigned not null auto_increment,
  lockversion int(10) unsigned not null,
  txtname varchar(50) not null,
  txttemplate json not null,
  fkdoctorid int(10) unsigned not null,
  fkhospitalid int(10) unsigned not null,
  fkcreateby int(10) unsigned default null,
  datecreate int(10) not null,
  fkupdateby int(10) unsigned default null,
  dateupdate int(10) default null,
  isactive bit(1) default 1,
  fkactchangeby int(10) unsigned default null,
  dateactchange int(10) default null,
  isarchive bit(1) default 0,
  fkarchiveby int(10) unsigned default null,
  datearchive int(10) default null,
  primary key (pkid),
  unique key txtname (txtname),
  key tblvital_fkcreateby (fkcreateby),
  key tblvital_fkupdateby (fkupdateby),
  key tblvital_fkactchangeby (fkactchangeby),
  key tblvital_fkarchiveby (fkarchiveby),
  key tblvital_isarchive (isarchive),
  key tblvital_isactive (isactive),
  key tblvital_fkhospitalid (fkhospitalid),
  key tblvital_fkdoctorid (fkdoctorid),
  constraint tblvital_fkcreateby foreign key(fkcreateby) references tbluser(pkid),
  constraint tblvital_fkupdateby foreign key(fkupdateby) references tbluser(pkid),
  constraint tblvital_fkactchangeby foreign key(fkactchangeby) references tbluser(pkid),
  constraint tblvital_fkarchiveby foreign key(fkarchiveby) references tbluser(pkid),
  constraint tblvital_fkdoctorid foreign key(fkdoctorid) references tbluser(pkid),
  constraint tblvital_fkhospitalid foreign key(fkhospitalid) references tblhospital(pkid)
)engine=innodb default charset=utf8;


create table tblpatientvital (
  pkid int(10) unsigned not null auto_increment,
  lockversion int(10) unsigned not null,
  txtname varchar(50) not null,
  txtunit varchar(10) not null,
  txtvalue varchar(10) not null,
  fkpatientid int(10) unsigned not null,
  fkdoctorid int(10) unsigned not null,
  fkhospitalid int(10) unsigned not null,
  fkappointmentbookid int(10) unsigned not null,
  fkcreateby int(10) unsigned default null,
  datecreate int(10) not null,
  fkupdateby int(10) unsigned default null,
  dateupdate int(10) default null,
  isactive bit(1) default 1,
  fkactchangeby int(10) unsigned default null,
  dateactchange int(10) default null,
  isarchive bit(1) default 0,
  fkarchiveby int(10) unsigned default null,
  datearchive int(10) default null,
  primary key (pkid),
  key tblpatientvital_fkcreateby (fkcreateby),
  key tblpatientvital_fkupdateby (fkupdateby),
  key tblpatientvital_fkactchangeby (fkactchangeby),
  key tblpatientvital_fkarchiveby (fkarchiveby),
  key tblpatientvital_isarchive (isarchive),
  key tblpatientvital_isactive (isactive),
  key tblpatientvital_fkhospitalid (fkhospitalid),
  key tblpatientvital_fkdoctorid (fkdoctorid),
  key tblpatientvital_fkpatientid (fkpatientid),
  key tblpatientvital_fkappointmentbookid (fkappointmentbookid),
  constraint tblpatientvital_fkcreateby foreign key(fkcreateby) references tbluser(pkid),
  constraint tblpatientvital_fkupdateby foreign key(fkupdateby) references tbluser(pkid),
  constraint tblpatientvital_fkactchangeby foreign key(fkactchangeby) references tbluser(pkid),
  constraint tblpatientvital_fkarchiveby foreign key(fkarchiveby) references tbluser(pkid),
  constraint tblpatientvital_fkdoctorid foreign key(fkdoctorid) references tbluser(pkid),
  constraint tblpatientvital_fkhospitalid foreign key(fkhospitalid) references tblhospital(pkid),
  constraint tblpatientvital_fkpatientid foreign key(fkpatientid) references tblpatientdetails(pkid),
  constraint tblpatientvital_fkappointmentbookid foreign key(fkappointmentbookid) references tblappointmentbook(pkid)
)engine=innodb default charset=utf8;


/*12/10/2019*/

drop table tblpatienthomecare;
drop table tblhomecarefield;
drop table tblhomecare;

create table tblhomecare (
  pkid int(10) unsigned not null auto_increment,
  lockversion int(10) unsigned not null,
  txtname varchar(50) not null,
  txttemplate json not null,
  fkdoctorid int(10) unsigned not null,
  fkhospitalid int(10) unsigned not null,
  fkcreateby int(10) unsigned default null,
  datecreate int(10) not null,
  fkupdateby int(10) unsigned default null,
  dateupdate int(10) default null,
  isactive bit(1) default 1,
  fkactchangeby int(10) unsigned default null,
  dateactchange int(10) default null,
  isarchive bit(1) default 0,
  fkarchiveby int(10) unsigned default null,
  datearchive int(10) default null,
  primary key (pkid),
  unique key txtname (txtname),
  key tblhomecare_fkcreateby (fkcreateby),
  key tblhomecare_fkupdateby (fkupdateby),
  key tblhomecare_fkactchangeby (fkactchangeby),
  key tblhomecare_fkarchiveby (fkarchiveby),
  key tblhomecare_isarchive (isarchive),
  key tblhomecare_isactive (isactive),
  key tblhomecare_fkhospitalid (fkhospitalid),
  key tblhomecare_fkdoctorid (fkdoctorid),
  constraint tblhomecare_fkcreateby foreign key(fkcreateby) references tbluser(pkid),
  constraint tblhomecare_fkupdateby foreign key(fkupdateby) references tbluser(pkid),
  constraint tblhomecare_fkactchangeby foreign key(fkactchangeby) references tbluser(pkid),
  constraint tblhomecare_fkarchiveby foreign key(fkarchiveby) references tbluser(pkid),
  constraint tblhomecare_fkdoctorid foreign key(fkdoctorid) references tbluser(pkid),
  constraint tblhomecare_fkhospitalid foreign key(fkhospitalid) references tblhospital(pkid)
)engine=innodb default charset=utf8;

create table tblpatienthomecare (
  pkid int(10) unsigned not null auto_increment,
  lockversion int(10) unsigned not null,
  txtname varchar(50) not null,
  txtunit varchar(10) not null,
  txtvalue varchar(10) not null,
  fkpatientid int(10) unsigned not null,
  fkdoctorid int(10) unsigned not null,
  fkhospitalid int(10) unsigned not null,
  fkappointmentbookid int(10) unsigned not null,
  fkcreateby int(10) unsigned default null,
  datecreate int(10) not null,
  fkupdateby int(10) unsigned default null,
  dateupdate int(10) default null,
  isactive bit(1) default 1,
  fkactchangeby int(10) unsigned default null,
  dateactchange int(10) default null,
  isarchive bit(1) default 0,
  fkarchiveby int(10) unsigned default null,
  datearchive int(10) default null,
  primary key (pkid),
  key tblpatienthomecare_fkcreateby (fkcreateby),
  key tblpatienthomecare_fkupdateby (fkupdateby),
  key tblpatienthomecare_fkactchangeby (fkactchangeby),
  key tblpatienthomecare_fkarchiveby (fkarchiveby),
  key tblpatienthomecare_isarchive (isarchive),
  key tblpatienthomecare_isactive (isactive),
  key tblpatienthomecare_fkhospitalid (fkhospitalid),
  key tblpatienthomecare_fkdoctorid (fkdoctorid),
  key tblpatienthomecare_fkpatientid (fkpatientid),
  key tblpatienthomecare_fkappointmentbookid (fkappointmentbookid),
  constraint tblpatienthomecare_fkcreateby foreign key(fkcreateby) references tbluser(pkid),
  constraint tblpatienthomecare_fkupdateby foreign key(fkupdateby) references tbluser(pkid),
  constraint tblpatienthomecare_fkactchangeby foreign key(fkactchangeby) references tbluser(pkid),
  constraint tblpatienthomecare_fkarchiveby foreign key(fkarchiveby) references tbluser(pkid),
  constraint tblpatienthomecare_fkdoctorid foreign key(fkdoctorid) references tbluser(pkid),
  constraint tblpatienthomecare_fkhospitalid foreign key(fkhospitalid) references tblhospital(pkid),
  constraint tblpatienthomecare_fkpatientid foreign key(fkpatientid) references tblpatientdetails(pkid),
  constraint tblpatienthomecare_fkappointmentbookid foreign key(fkappointmentbookid) references tblappointmentbook(pkid)
)engine=innodb default charset=utf8;

drop table tblpatientinvestigation;
drop table tblinvestigationparameter;
drop table tblinvestigation;

create table tblinvestigation (
  pkid int(10) unsigned not null auto_increment,
  lockversion int(10) unsigned not null,
  txtname varchar(50) not null,
  txttemplate json not null,
  fkdoctorid int(10) unsigned not null,
  fkhospitalid int(10) unsigned not null,
  fkcreateby int(10) unsigned default null,
  datecreate int(10) not null,
  fkupdateby int(10) unsigned default null,
  dateupdate int(10) default null,
  isactive bit(1) default 1,
  fkactchangeby int(10) unsigned default null,
  dateactchange int(10) default null,
  isarchive bit(1) default 0,
  fkarchiveby int(10) unsigned default null,
  datearchive int(10) default null,
  primary key (pkid),
  unique key txtname (txtname),
  key tblinvestigation_fkcreateby (fkcreateby),
  key tblinvestigation_fkupdateby (fkupdateby),
  key tblinvestigation_fkactchangeby (fkactchangeby),
  key tblinvestigation_fkarchiveby (fkarchiveby),
  key tblinvestigation_isarchive (isarchive),
  key tblinvestigation_isactive (isactive),
  key tblinvestigation_fkhospitalid (fkhospitalid),
  key tblinvestigation_fkdoctorid (fkdoctorid),
  constraint tblinvestigation_fkcreateby foreign key(fkcreateby) references tbluser(pkid),
  constraint tblinvestigation_fkupdateby foreign key(fkupdateby) references tbluser(pkid),
  constraint tblinvestigation_fkactchangeby foreign key(fkactchangeby) references tbluser(pkid),
  constraint tblinvestigation_fkarchiveby foreign key(fkarchiveby) references tbluser(pkid),
  constraint tblinvestigation_fkdoctorid foreign key(fkdoctorid) references tbluser(pkid),
  constraint tblinvestigation_fkhospitalid foreign key(fkhospitalid) references tblhospital(pkid)
)engine=innodb default charset=utf8;


create table tblpatientinvestigation (
  pkid int(10) unsigned not null auto_increment,
  lockversion int(10) unsigned not null,
  txtname varchar(50) not null,
  numberlevel smallint not null,
  fkrootpatientinvestigationid int(10) unsigned null,
  fkpatientid int(10) unsigned not null,
  fkappointmentbookid int(10) unsigned not null,
  fkdoctorid int(10) unsigned not null,
  fkhospitalid int(10) unsigned not null,
  fkcreateby int(10) unsigned default null,
  datecreate int(10) not null,
  fkupdateby int(10) unsigned default null,
  dateupdate int(10) default null,
  isactive bit(1) default 1,
  fkactchangeby int(10) unsigned default null,
  dateactchange int(10) default null,
  isarchive bit(1) default 0,
  fkarchiveby int(10) unsigned default null,
  datearchive int(10) default null,
  primary key (pkid),
  key tblpatientinvestigation_fkcreateby (fkcreateby),
  key tblpatientinvestigation_fkupdateby (fkupdateby),
  key tblpatientinvestigation_fkactchangeby (fkactchangeby),
  key tblpatientinvestigation_fkarchiveby (fkarchiveby),
  key tblpatientinvestigation_isarchive (isarchive),
  key tblpatientinvestigation_isactive (isactive),
  key tblpatientinvestigation_fkhospitalid (fkhospitalid),
  key tblpatientinvestigation_fkdoctorid (fkdoctorid),
  key tblpatientinvestigation_fkrootpatientinvestigationid (fkrootpatientinvestigationid),
  key tblpatientinvestigation_fkpatientid (fkpatientid),
  key tblpatientinvestigation_fkappointmentbookid (fkappointmentbookid),
  constraint tblpatientinvestigation_fkcreateby foreign key(fkcreateby) references tbluser(pkid),
  constraint tblpatientinvestigation_fkupdateby foreign key(fkupdateby) references tbluser(pkid),
  constraint tblpatientinvestigation_fkactchangeby foreign key(fkactchangeby) references tbluser(pkid),
  constraint tblpatientinvestigation_fkarchiveby foreign key(fkarchiveby) references tbluser(pkid),
  constraint tblpatientinvestigation_fkdoctorid foreign key(fkdoctorid) references tbluser(pkid),
  constraint tblpatientinvestigation_fkrootpatientinvestigationid foreign key(fkrootpatientinvestigationid) references tblpatientinvestigation(pkid),
  constraint tblpatientinvestigation_fkpatientid foreign key(fkpatientid) references tblpatientdetails(pkid),
  constraint tblpatientinvestigation_fkappointmentbookid foreign key(fkappointmentbookid) references tblappointmentbook(pkid),
  constraint tblpatientinvestigation_fkhospitalid foreign key(fkhospitalid) references tblhospital(pkid)
)engine=innodb default charset=utf8;

create table tblinvestigationparameter (
  pkid int(10) unsigned not null auto_increment,
  fkpatientinvestigationid int(10) unsigned not null,
  txtparametername varchar(50) not null,
  txtparameterunit varchar(10) not null,
  txtparameterrange varchar(50) not null,
  txtvalue varchar(10) null, 
  primary key (pkid),
  key tblinvestigationparameter_fkpatientinvestigationid (fkpatientinvestigationid),
  constraint tblinvestigationparameter_fkpatientinvestigationid foreign key(fkpatientinvestigationid) references tblpatientinvestigation(pkid)
)engine=innodb default charset=utf8;

/*16/10/2019*/
create table tblpatienttreatmentplan (
  pkid int(10) unsigned not null auto_increment,
  lockversion int(10) unsigned not null,
  txtname varchar(50) not null,
  txtdescription text not null,
  fkpatientid int(10) unsigned not null,
  fkdoctorid int(10) unsigned not null,
  fkhospitalid int(10) unsigned not null,
  fkappointmentbookid int(10) unsigned not null,
  fkcreateby int(10) unsigned default null,
  datecreate int(10) not null,
  fkupdateby int(10) unsigned default null,
  dateupdate int(10) default null,
  isactive bit(1) default 1,
  fkactchangeby int(10) unsigned default null,
  dateactchange int(10) default null,
  isarchive bit(1) default 0,
  fkarchiveby int(10) unsigned default null,
  datearchive int(10) default null,
  primary key (pkid),
  key tblpatienttreatmentplan_fkcreateby (fkcreateby),
  key tblpatienttreatmentplan_fkupdateby (fkupdateby),
  key tblpatienttreatmentplan_fkactchangeby (fkactchangeby),
  key tblpatienttreatmentplan_fkarchiveby (fkarchiveby),
  key tblpatienttreatmentplan_isarchive (isarchive),
  key tblpatienttreatmentplan_isactive (isactive),
  key tblpatienttreatmentplan_fkhospitalid (fkhospitalid),
  key tblpatienttreatmentplan_fkdoctorid (fkdoctorid),
  key tblpatienttreatmentplan_fkpatientid (fkpatientid),
  key tblpatienttreatmentplan_fkappointmentbookid (fkappointmentbookid),
  constraint tblpatienttreatmentplan_fkcreateby foreign key(fkcreateby) references tbluser(pkid),
  constraint tblpatienttreatmentplan_fkupdateby foreign key(fkupdateby) references tbluser(pkid),
  constraint tblpatienttreatmentplan_fkactchangeby foreign key(fkactchangeby) references tbluser(pkid),
  constraint tblpatienttreatmentplan_fkarchiveby foreign key(fkarchiveby) references tbluser(pkid),
  constraint tblpatienttreatmentplan_fkdoctorid foreign key(fkdoctorid) references tbluser(pkid),
  constraint tblpatienttreatmentplan_fkhospitalid foreign key(fkhospitalid) references tblhospital(pkid),
  constraint tblpatienttreatmentplan_fkpatientid foreign key(fkpatientid) references tblpatientdetails(pkid),
  constraint tblpatienttreatmentplan_fkappointmentbookid foreign key(fkappointmentbookid) references tblappointmentbook(pkid)
)engine=innodb default charset=utf8;

create table tbltreatmentplan (
  pkid int(10) unsigned not null auto_increment,
  lockversion int(10) unsigned not null,
  txtname varchar(50) not null,
  txttemplate json not null,
  fkdoctorid int(10) unsigned not null,
  fkhospitalid int(10) unsigned not null,
  fkcreateby int(10) unsigned default null,
  datecreate int(10) not null,
  fkupdateby int(10) unsigned default null,
  dateupdate int(10) default null,
  isactive bit(1) default 1,
  fkactchangeby int(10) unsigned default null,
  dateactchange int(10) default null,
  isarchive bit(1) default 0,
  fkarchiveby int(10) unsigned default null,
  datearchive int(10) default null,
  primary key (pkid),
  key tbltreatmentplan_fkcreateby (fkcreateby),
  key tbltreatmentplan_fkupdateby (fkupdateby),
  key tbltreatmentplan_fkactchangeby (fkactchangeby),
  key tbltreatmentplan_fkarchiveby (fkarchiveby),
  key tbltreatmentplan_isarchive (isarchive),
  key tbltreatmentplan_isactive (isactive),
  key tbltreatmentplan_fkhospitalid (fkhospitalid),
  key tbltreatmentplan_fkdoctorid (fkdoctorid),
  constraint tbltreatmentplan_fkcreateby foreign key(fkcreateby) references tbluser(pkid),
  constraint tbltreatmentplan_fkupdateby foreign key(fkupdateby) references tbluser(pkid),
  constraint tbltreatmentplan_fkactchangeby foreign key(fkactchangeby) references tbluser(pkid),
  constraint tbltreatmentplan_fkarchiveby foreign key(fkarchiveby) references tbluser(pkid),
  constraint tbltreatmentplan_fkdoctorid foreign key(fkdoctorid) references tbluser(pkid),
  constraint tbltreatmentplan_fkhospitalid foreign key(fkhospitalid) references tblhospital(pkid)
)engine=innodb default charset=utf8;
/*17/10/2019*/

alter table tblinvoice add column numberpaymentstatus smallint null;

alter table tblpaymenttransaction add column numberamount double not null default 0 after fkinvoiceid;
alter table tblpaymenttransaction add column txtcutomeremail varchar(100) null after numberamount;
alter table tblpaymenttransaction add column txtcustomerphone varchar(15) not null;

/*22/10/2019*/
alter table tblpatienttreatmentplan add column txtdays varchar(50) null after txtdescription;

drop table tblleaveshift;
drop table tblleave;

create table tblleave (
  pkid int(10) unsigned not null auto_increment,
  lockversion int(10) unsigned not null,
  datestart int(10) not null,
  dateend int(10) not null,
  iscancel bit(1) default 0,
  isfullday bit(1) not null,
  fkdoctorid int(10) unsigned not null,
  fkhospitalid int(10) unsigned not null,
  fkcreateby int(10) unsigned default null,
  datecreate int(10) not null,
  fkupdateby int(10) unsigned default null,
  dateupdate int(10) default null,
  isactive bit(1) default 1,
  fkactchangeby int(10) unsigned default null,
  dateactchange int(10) default null,
  isarchive bit(1) default 0,
  fkarchiveby int(10) unsigned default null,
  datearchive int(10) default null,
  primary key (pkid),
  key tblleave_fkcreateby (fkcreateby),
  key tblleave_fkupdateby (fkupdateby),
  key tblleave_fkactchangeby (fkactchangeby),
  key tblleave_fkarchiveby (fkarchiveby),
  key tblleave_isarchive (isarchive),
  key tblleave_isactive (isactive),
  key tblleave_fkhospitalid (fkhospitalid),
  key tblleave_fkdoctorid (fkdoctorid),
  key tblleave_datestart (datestart),
  key tblleave_dateend (dateend),
  constraint tblleave_fkcreateby foreign key(fkcreateby) references tbluser(pkid),
  constraint tblleave_fkupdateby foreign key(fkupdateby) references tbluser(pkid),
  constraint tblleave_fkactchangeby foreign key(fkactchangeby) references tbluser(pkid),
  constraint tblleave_fkarchiveby foreign key(fkarchiveby) references tbluser(pkid),
  constraint tblleave_fkdoctorid foreign key(fkdoctorid) references tbluser(pkid),
  constraint tblleave_fkhospitalid foreign key(fkhospitalid) references tblhospital(pkid)
)engine=innodb default charset=utf8;

create table tblleaveshift(
  	fkleaveid int(10) unsigned not null,
  	fkshiftid int(10) unsigned not null,
  	primary key(fkleaveid,fkshiftid),
  	key tblleaveshift_fkleaveid (fkleaveid),
  	key tblleaveshift_fkshiftid (fkshiftid),
    constraint tblleaveshift_fkdoctorid foreign key(fkleaveid) references tblleave(pkid),
  constraint tblleaveshift_fkshiftid foreign key(fkshiftid) references tblshift(pkid)
)engine=innodb default charset=utf8;

/*
 * 31/10/2019
 */
drop table tblinvestigationparameter;
drop table tblpatientinvestigation;
drop table tblinvestigation;

create table tblinvestigation (
  pkid int(10) unsigned not null auto_increment,
  lockversion int(10) unsigned not null,
  txtname varchar(50) not null,
  fkdoctorid int(10) unsigned not null,
  fkhospitalid int(10) unsigned not null,
  fkcreateby int(10) unsigned default null,
  datecreate int(10) not null,
  fkupdateby int(10) unsigned default null,
  dateupdate int(10) default null,
  isactive bit(1) default 1,
  fkactchangeby int(10) unsigned default null,
  dateactchange int(10) default null,
  isarchive bit(1) default 0,
  fkarchiveby int(10) unsigned default null,
  datearchive int(10) default null,
  primary key (pkid),
  unique key txtname (txtname, fkdoctorid, fkhospitalid),
  key tblinvestigation_txtname (txtname),
  key tblinvestigation_fkcreateby (fkcreateby),
  key tblinvestigation_fkupdateby (fkupdateby),
  key tblinvestigation_fkactchangeby (fkactchangeby),
  key tblinvestigation_fkarchiveby (fkarchiveby),
  key tblinvestigation_isarchive (isarchive),
  key tblinvestigation_isactive (isactive),
  key tblinvestigation_fkhospitalid (fkhospitalid),
  key tblinvestigation_fkdoctorid (fkdoctorid),
  constraint tblinvestigation_fkcreateby foreign key(fkcreateby) references tbluser(pkid),
  constraint tblinvestigation_fkupdateby foreign key(fkupdateby) references tbluser(pkid),
  constraint tblinvestigation_fkactchangeby foreign key(fkactchangeby) references tbluser(pkid),
  constraint tblinvestigation_fkarchiveby foreign key(fkarchiveby) references tbluser(pkid),
  constraint tblinvestigation_fkdoctorid foreign key(fkdoctorid) references tbluser(pkid),
  constraint tblinvestigation_fkhospitalid foreign key(fkhospitalid) references tblhospital(pkid)
)engine=innodb default charset=utf8;

create table tblchildinvestigation (
  pkid int(10) unsigned not null auto_increment,
  lockversion int(10) unsigned not null,
  txtname varchar(50) not null,
  txtunit varchar(10) null,
  txtrange varchar(50) null,
  fkinvestigationid int(10) unsigned not null,
  fkchildrootinvestigationid int(10) unsigned null,
  fkdoctorid int(10) unsigned not null,
  fkhospitalid int(10) unsigned not null,
  fkcreateby int(10) unsigned default null,
  datecreate int(10) not null,
  fkupdateby int(10) unsigned default null,
  dateupdate int(10) default null,
  isactive bit(1) default 1,
  fkactchangeby int(10) unsigned default null,
  dateactchange int(10) default null,
  isarchive bit(1) default 0,
  fkarchiveby int(10) unsigned default null,
  datearchive int(10) default null,
  primary key (pkid),
  unique key txtname (txtname, fkdoctorid, fkhospitalid,fkinvestigationid),
  key tblchildinvestigation_fkcreateby (fkcreateby),
  key tblchildinvestigation_fkupdateby (fkupdateby),
  key tblchildinvestigation_fkactchangeby (fkactchangeby),
  key tblchildinvestigation_fkarchiveby (fkarchiveby),
  key tblchildinvestigation_isarchive (isarchive),
  key tblchildinvestigation_isactive (isactive),
  key tblchildinvestigation_fkhospitalid (fkhospitalid),
  key tblchildinvestigation_fkdoctorid (fkdoctorid),
  key tblchildinvestigation_fkinvestigationid (fkinvestigationid),
  key tblchildinvestigation_fkchildrootinvestigationid (fkchildrootinvestigationid),
  constraint tblchildinvestigation_fkcreateby foreign key(fkcreateby) references tbluser(pkid),
  constraint tblchildinvestigation_fkupdateby foreign key(fkupdateby) references tbluser(pkid),
  constraint tblchildinvestigation_fkactchangeby foreign key(fkactchangeby) references tbluser(pkid),
  constraint tblchildinvestigation_fkarchiveby foreign key(fkarchiveby) references tbluser(pkid),
  constraint tblchildinvestigation_fkdoctorid foreign key(fkdoctorid) references tbluser(pkid),
  constraint tblchildinvestigation_fkinvestigationid foreign key(fkinvestigationid) references tblinvestigation(pkid),
  constraint tblchildinvestigation_fkchildrootinvestigationid foreign key(fkchildrootinvestigationid) references tblchildinvestigation(pkid),
  constraint tblchildinvestigation_fkhospitalid foreign key(fkhospitalid) references tblhospital(pkid)
)engine=innodb default charset=utf8;

create table tblpatientinvestigation (
  pkid int(10) unsigned not null auto_increment,
  txtvalue varchar(10) null,
  fkchildinvestigationid int(10) unsigned null,
  fkinvestigationid int(10) unsigned not null,
  fkpatientid int(10) unsigned not null,
  fkappointmentbookid int(10) unsigned not null,
  fkdoctorid int(10) unsigned not null,
  fkhospitalid int(10) unsigned not null,
  primary key (pkid),
  unique key(fkinvestigationid, fkpatientid, fkappointmentbookid, fkdoctorid, fkhospitalid),
  key tblpatientinvestigation_fkchildinvestigationid (fkchildinvestigationid),
  key tblpatientinvestigation_fkinvestigationid (fkinvestigationid),
  key tblpatientinvestigation_fkpatientid (fkpatientid),
  key tblpatientinvestigation_fkappointmentbookid (fkappointmentbookid),
  key tblpatientinvestigation_fkdoctorid (fkdoctorid),
  key tblpatientinvestigation_fkhospitalid (fkhospitalid),
  constraint tblpatientinvestigation_fkchildinvestigationid foreign key(fkchildinvestigationid) references tblchildinvestigation(pkid),
  constraint tblpatientinvestigation_fkinvestigationid foreign key(fkinvestigationid) references tblinvestigation(pkid),
  constraint tblpatientinvestigation_fkpatientid foreign key(fkpatientid) references tblpatientdetails(pkid),
  constraint tblpatientinvestigation_fkappointmentbookid foreign key(fkappointmentbookid) references tblappointmentbook(pkid),
  constraint tblpatientinvestigation_fkdoctorid foreign key(fkdoctorid) references tbluser(pkid),
  constraint tblpatientinvestigation_fkhospitalid foreign key(fkhospitalid) references tblhospital(pkid)
)engine=innodb default charset=utf8;

alter table tblchildinvestigation add column isparameter bit(1) default 0 after txtrange;
alter table tblchildinvestigation change column txtunit  txtunit varchar(10) null;
alter table tblchildinvestigation change column txtrange  txtrange varchar(50) null;

create table tblpatienthospital(
  	fkpatientid int(10) unsigned not null,
  	fkhospitalid int(10) unsigned not null,
  	primary key(fkpatientid,fkhospitalid),
  	key tblpatienthospital_fkpatientid (fkpatientid),
  	key tblpatienthospital_fkhospitalid (fkhospitalid),
    constraint tblpatienthospital_fkpatientid foreign key(fkpatientid) references tblpatientdetails(pkid),
  constraint tblpatienthospital_fkhospitalid foreign key(fkhospitalid) references tblhospital(pkid)
)engine=innodb default charset=utf8;

alter table tblpatientdetails drop foreign key tblpatientdetails_fkhospitalid;
alter table tblpatientdetails drop column fkhospitalid;

alter table tblappointmentbook add column numberstatus smallint null;
UPDATE tblappointmentbook tapp
        INNER JOIN tblappointmentqueue tapq 
             ON tapq.fkappointmentbookid = tapp.pkid
SET tapp.numberstatus = tapq.txtstatus;
alter table tblappointmentbook change column numberstatus numberstatus smallint not null;
alter table tblappointmentqueue drop column txtstatus;


/*Note  Data Migration of appointment which have null shift time and doctor null*/
alter table tblappointmentqueue change column fkdoctorid fkdoctorid int(10) unsigned not null;
alter table tblappointmentqueue change column fkshiftid fkshiftid int(10) unsigned not null;
alter table tblappointmentqueue change column timearrived timearrived int(10) unsigned not null;
alter table tblappointmentqueue change column numtoken numtoken int(5) not null;
/*live executed----->14/11/2019*/

/*
 * 25/11/2019
 */
alter table tblplan add column numberplantype smallint null after issellallowed;
alter table tblhospital add unique key(txtregistrationnumber);
alter table tbldepartment add unique key(txtname,fkhospitalid);

alter table tblroom add unique key(txtroomnumber,fkhospitalid);
alter table tblservice add unique key(txtname,fkhospitalid);
alter table tblcategory add unique key(txtname,fkhospitalid);
alter table tbltariff add unique key(txtname,fkhospitalid);
alter table tblshift add unique key(txtname,fkhospitalid);

alter table tblhospitalplanhistory add column numberamount double not null;
/*Note : Migration of plan amount and End Date of Plan*/
/*
 * 27/11/2019
 * */
alter table tblhospitalplanhistory add column txtordernumber varchar(8) not null;
alter table tblhospitalplanhistory add column txtkartpayid varchar(10) null;
alter table tblhospitalplanhistory add column txtpaymentstatus smallint not null default 0;
alter table tblhospitalplanhistory add column txtfailedreason varchar(200) null;

alter table tblhospitalplanhistory add column dateend int(10) not null after datecreate;
alter table tblhospital add column txtmerchantid int(10) null;
/*live executed----->29/11/2019*/

alter table tblappointmentservice add numberamount double null;

update tblappointmentservice tbaps left join tbltariffrate tbr on tbr.pkid=tbaps.fktariffrateid 
set tbaps.numberamount=(tbr.txtrate*tbaps.numcount);

alter table tblappointmentservice change column numberamount numberamount double not null;

alter table tblinvoice change column numtotalamount numberbillamount double not null default 0;
alter table tblinvoice change column numdiscountamount numberdiscountamount double not null default 0;
alter table tblinvoice add column numberpendingamount double not null default 0;
alter table tblinvoice add column datecreate int(10) null;

alter table tbluser add FULLTEXT(txtfirstname,txtlastname,txtemail,txtmobile);

/*live executed----->04/12/2019*/

alter table tblinvoice drop column amountsgst;
alter table tblinvoice drop column amountcgst;
alter table tblinvoice drop column amountigst;
alter table tblinvoice drop column numbertotaltaxamount;
/*live executed----->10/12/2019*/


alter table tblpatientdetails change column txtmobile txtmobile varchar(15) null;

alter table tblinvoice add column numberpartiallypaidamount double not null default 0;
alter table tblinvoice add column txtorderid varchar(8) null;

create table tblfailedpaymenttransaction (
  pkid int(10) unsigned not null auto_increment,
  fkinvoiceid int(10) unsigned not null,
  fkhospitalid int(10) unsigned not null,
  datepayment int(10) not null,
  numberamount double not null default 0,
  txtcutomeremail varchar(100) null,
  txtcustomerphone varchar(15) not null,
  txttransactionnumber varchar(10) not null,
  primary key (pkid),
  unique key txttransactionnumber (txttransactionnumber),
  key tblfailedpaymenttransaction_fkinvoiceid (fkinvoiceid),
  key tblfailedpaymenttransaction_fkhospitalid (fkhospitalid),
  key tblfailedpaymenttransaction_datepayment (datepayment),
  constraint tblfailedpaymenttransaction_fkinvoiceid foreign key(fkinvoiceid) references tblinvoice(pkid),
  constraint tblfailedpaymenttransaction_fkhospitalid foreign key(fkhospitalid) references tblhospital(pkid)
)engine=innodb default charset=utf8;
/*live executed----->13/10/2019*/

alter table tbltariffrate add unique key(fktariffid,fkserviceid,fkcategoryid,fkdoctorid,fkdepartmentid,fkhospitalid);

alter table tblchildinvestigation add column numberlevel smallint null after isparameter;
update tblchildinvestigation set numberlevel=2 where fkchildrootinvestigationid is not null;
update tblchildinvestigation set numberlevel=1 where fkchildrootinvestigationid is null;
/*local executed*/

alter table tblchildinvestigation change column numberlevel numberlevel smallint not null;
/*live executed----->20/12/2019*/

alter table tblleave add column ispause boolean default false after isfullday;
alter table tblleave change column ispause ispause boolean not null;
/*live executed----->28/12/2019*/


alter table tblpatientinvestigation drop key fkinvestigationid;
alter table tblpatientinvestigation add unique key(fkinvestigationid,fkchildinvestigationid, fkpatientid, fkappointmentbookid, fkdoctorid, fkhospitalid);

alter table tblleave drop column ispause;
alter table tblleave add column numberpause smallint not null default 0 after isfullday;
/*live executed----->30/12/2019*/

alter table tblpatientdetails add column isfamilymember boolean not null default false after txtmobile;
update tblpatientdetails set isfamilymember=true where txtmobile is null;

 
 create table tblappointmentremindersmstransaction (
  pkid int(10) unsigned not null auto_increment,
  lockversion int(10) unsigned not null,
  fksmsaccountid int(10) unsigned not null,
  fkappointmentbookid int(10) unsigned not null,
  txtbody text not null,
  txtmobile varchar(15) not null,
  enumstatus int(3) not null default 0,/*0=new, 1=inprocess, 2=failed, 3=sent*/
  numberretrycount int(10) not null default 0,
  isbeforefourhourcontent boolean not null,
  txterror text,
  datesend int(10) default null,
  datesent int(10) default null,
  fkcreateby int(10) unsigned default null,
  datecreate int(10) not null,
  fkupdateby int(10) unsigned default null,
  dateupdate int(10) default null,
  isactive bit(1) default 1,
  fkactchangeby int(10) unsigned default null,
  dateactchange int(10) default null,
  isarchive bit(1) default 0,
  fkarchiveby int(10) unsigned default null,
  datearchive int(10) default null,
  fkhospitalid int(10) unsigned not null,
  primary key (pkid),
  key tblappointmentremindersmstransaction_fkcreateby (fkcreateby),
  key tblappointmentremindersmstransaction_fkupdateby (fkupdateby),
  key tblappointmentremindersmstransaction_fkactchangeby (fkactchangeby),
  key tblappointmentremindersmstransaction_fkarchiveby (fkarchiveby),
  key tblappointmentremindersmstransaction_fksmsaccountid(fksmsaccountid),
  key tblappointmentremindersmstransaction_isarchive (isarchive),
  key tblappointmentremindersmstransaction_isactive (isactive),
  key tblappointmentremindersmstransaction_fkhospitalid (fkhospitalid),
  key tblappointmentremindersmstransaction_fkappointmentbookid (fkappointmentbookid),
  key tblappointmentremindersmstransaction_isbeforefourhourcontent (isbeforefourhourcontent),
  constraint tblappointmentremindersmstransaction_fkcreateby foreign key(fkcreateby) references tbluser(pkid),
  constraint tblappointmentremindersmstransaction_fkupdateby foreign key(fkupdateby) references tbluser(pkid),
  constraint tblappointmentremindersmstransaction_fkactchangeby foreign key(fkactchangeby) references tbluser(pkid),
  constraint tblappointmentremindersmstransaction_fkarchiveby foreign key(fkarchiveby) references tbluser(pkid),
  constraint tblappointmentremindersmstransaction_fksmsaccountid foreign key(fksmsaccountid) references tblsmsaccount(pkid),
  constraint tblappointmentremindersmstransaction_fkappointmentbookid foreign key(fkappointmentbookid) references tblappointmentbook(pkid),
  constraint tblappointmentremindersmstransaction_fkhospitalid foreign key(fkhospitalid) references tblhospital(pkid)
)engine=innodb default charset=utf8;
 
alter table tblshift drop column txtstarttime;
alter table tblshift drop column txtendtime;

