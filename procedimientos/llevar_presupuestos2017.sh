#!/bin/bash
cp *.sh presupuestos2017/procedimientos
rm -R presupuestos2017/*.*~
cd presupuestos2017
git config --global user.email "googolplex@lcompras.biz"
git config --global user.name "googol plex"
git add -A
git commit -a -m "presupuestos version 9"
git push -u origin master
