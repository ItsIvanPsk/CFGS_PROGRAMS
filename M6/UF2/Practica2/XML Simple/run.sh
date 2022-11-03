#!/bin/bash

reset
rm database.db
rm -f ./bin/*.* 

javac @hibernate.argfile -d ./bin/ *.java
java @hibernate.argfile Main