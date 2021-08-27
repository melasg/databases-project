#!/bin/bash
                # startPostgreSQL.sh
folder=/tmp/$USER
export PGDATA=$folder/myDB/data
export PGSOCKETS=$folder/myDB/sockets
echo "$folder"
echo "Now deleting tmp folder"
rm -rf "$folder"
mkdir -p "$folder"/myDB/data/sockets
sleep 1
initdb
sleep 1
export PGPORT=1025
pg_ctl -o "-c unix_socket_directories=$PGSOCKETS -p $PGPORT" -D "$PGDATA" -l "$folder"/logfile start
                # Lab 10 practice step after running createPostgreSQL.sh
cp *.txt /tmp/$USERNAME/myDB/data/
# psql -h /tmp/$LOGNAME/sockets $DB_NAME | echo '\q'
psql -hp localhost $PORT $USERNAME_DB < code/sql/create.sql
                # createPostgreSQL.sh
cd ../../
cd code/ || return
echo "Creating db named "$USER"_DB"
createdb -h localhost -p $PGPORT $USER"_DB"
pg_ctl status
echo "Start copying .csv files -> database data"
sleep 1
cp code/data/*.csv /tmp/$USER/myDB/data/.
echo "initialize tables"
sleep 1
psql -h localhost -p $PGPORT $USER"_DB" < code/sql/create.sql
                # compile.sh
export JAVA_HOME= **relative path to jdk**
export PATH=$JAVA_HOME/bin:$PATH
CLASSPATH=/code/java/bin/
export CLASSPATH=$CLASSPATH:$PWD/postgresql-42.1.4.jar
# javac code/java/src/MechanicShop.java
rm -rf code/java/bin/*.class
javac -cp ".;code/java/lib/postgresql-42.1.4.jar;" code/java/src/MechanicShop.java -d bin/
# java MechanicShop $USER"_DB" $PGPORT $USER
                # run.sh
DBNAME=$1
PORT=$2
USER=$3
java -cp code/java/lib/*:bin/ MechanicShop $DBNAME $PORT $USER