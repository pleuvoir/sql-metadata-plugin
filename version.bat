@echo on
@echo =============================================================
@echo $                                                           $
@echo $                      sql-metadata-plugin                  $
@echo $                                                           $
@echo =============================================================
@echo.
@echo off

@title redis-plugin version update
@color 0a

rem  Please execute command in local directory.

call mvn -N versions:update-child-modules
call mvn versions:set -DnewVersion=1.0.3
call mvn versions:commit

pause