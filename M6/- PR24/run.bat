rem run with: .\run.bat

cls

del /F /Q .\database.db
del /F /Q .\bin\*.*

javac @hibernate.argfilewin -d .\bin\ *.java
java --add-opens=java.base/java.nio=ALL-UNNAMED --add-opens=java.base/java.lang=ALL-UNNAMED --enable-preview -XX:+ShowCodeDetailsInExceptionMessages @hibernate.argfilewin PR230Main