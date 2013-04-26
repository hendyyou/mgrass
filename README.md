mgrasscrm
========

Open Source Java based CRM Mobile Version

How to configure the environment:
(1)Deploy Grass CRM

(2)Config the datasource in Grass/WebContent/WEB-INF/config/spring/applicationContext.xml, in line 16, if postgres
database is installed in other server, you can change the value of url here.

(3)In eclipse, go to mgrass project, execute "ant deploy" to deploy project to jboss server, then start jboss server.

(4)In browser, input URL:localhost:8080/mgrass, will open the login page, the default user name is "admin", password is empty, then you can login the Grass CRM Mobile version system.