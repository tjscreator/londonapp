All properties/config files to be used as a reference before deploying a project in new environment.

1. Create a folder inside /var/log/{project_name}/ folder named as "gc"
   Java Garbage collection related logs will be stored here.

2. Create a folder inside /var/log/{project_name}/ folder named as "heapdump"
   Java memory heapdump will be stored here if any memory related issue occurs.

3. Log file folder for any project.
   /var/log/{project.name}/
   All logs file will be stored here.
   Log files back up are taken on day to day basis. 
 
4. setenv.sh / setenv.bat ( sh for linux / bat for windows)
   - This file is used to set system properties which can be used to specify log file path
   - This file contains jvm related configuration parameters like (xmx, xms, xss, gc configuration etc.)
   - Location of this file must be ${catalina-base}/bin/{filename}
   - Log4j2.xml file path base on tomcat directory.
   
5. db.properties file
   - This file is used to connect with database
   - Location of this file must be ${catalina-base}/conf/{projectname}/db.properties
   
6. email.executor.properties file   
   - This file is used to configure thread pool for email.(Transaction/Notification/Marketing)
   - Location of this file must be ${catalina-base}/conf/{projectname}/email.executor.properties
   
7. email.scheduler.properties file   
   - This file is used to configure Email scheduler run time & other things.
   - Location of this file must be ${catalina-base}/conf/{projectname}/email.scheduler.properties   