#!/bin/bash
cp *.sh presupuestos2017/procedimientos
rm -R presupuestos2017/*.*~
cp -R /home/anaramos/openxava-5.4.1/workspace/Presupuestos/* presupuestos2017/presupuestos_version_anaramos2016
cp -R /home/anaramos/openxava-5.4.1/tomcat/conf/context.xml presupuestos2017/tomcatfeliz/
cp -R /home/anaramos/openxava-5.4.1/tomcat/conf/server.xml presupuestos2017/tomcatfeliz/
cp -R /home/anaramos/openxava-5.4.1/tomcat/conf/web.xml presupuestos2017/tomcatfeliz/
cd presupuestos2017
git config --global user.email "googolplex@lcompras.biz"
git config --global user.name "googol plex"
git add -A
git commit -a -m "presupuestos version 9 - agrego el informe"
git push -u origin master
