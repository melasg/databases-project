* Setup your PostgreSQL environment
    * Create a data and socket folder under your environment, which will contain Postgre data files and socket information:
    * Initialize the database environment:
        ```bash
        initdb
        ```
* Start the PostgreSQL database server
* Create your database (Replace `$DB NAME` with your database name)
    ```postgre
    createdb -h /dir/$LOGNAME/sockets $DBNAME
    ```
* Start the interactive environment
    ```postgre
    createdb -h /dir/$LOGNAME/sockets $DBNAME
    psql -h /dir/$LOGNAME/sockets $DBNAME
    ```
* The Postgre prompt like “`mydb=#`” will show up to accept your SQL commands, where “`mydb`” is the database name you specified in `$DB NAME`. Use “`\q`” to quit the interactive environment. You can also pipeline a `SQL` script into the interactive environment to execute it in bulk:
    ```postgre
    psql -h /dir/$LOGNAME/sockets $DBNAME < your script . sql
    ```
* SQL Administrator Cook List Show all databases in your Postgre instance: (`cd` into your `$LOGNAME` folder)
    ```bash
    cd /dir/$LOGNAME
    mkdir test
    mkdir test/data
    mkdir sockets
    export PGDATA=/tmp/$LOGNAME/test/data
    ```
    ```postgre
    pg ctl -o ”-c unix socket directories=/dir/$LOGNAME/sockets” -D $PGDATA -l /dir/$LOGNAME/logfile start
    mydb=# SELECT datname FROM pg database ;
    ```
 * Show all tables viewable to you:
    ```postgre
    mydb=# SELECT table name
    mydb=#FROM information schema.tables
    mydb=# WHERE table schema=’public ’ ;
    ```
* Stop the database instance
    ```postgre
    pg ctl -o ” c unix socket directories=/tmp/$LOGNAME/sockets” -D /tmp/$LOGNAME/test/data stop
    ```