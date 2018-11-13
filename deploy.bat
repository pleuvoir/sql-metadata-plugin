@echo on
@echo =============================================================
@echo $                                                           $
@echo $                      sql-metadata-plugin                  $
@echo $                                                           $
@echo =============================================================
@echo.
@echo off

@title sql-metadata-plugin deploy
@color 0a

rem  Please execute command in local directory.

call mvn clean deploy -DskipTests -P release

pause