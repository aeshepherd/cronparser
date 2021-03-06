# cronparser

Compiles, tests and packages cronparser code. 
Resultant jar is then added to a docker image

Cronparser is packaged as an executable JAR with a dependency on the lib/groovy.jar

Suppress file globs (`set -f` on linux) before execution if using naked asterisks

To execute cronparser from within the docker image generated
```
localhost>docker run -t -i aeshepherd/cron-parser /bin/bash
# cd cronparser/
# set -f
# java -jar CronParser-1.0.jar 1 1 * * * /bin/grep -i  /tmp/txt
minute          1
hour            1
day of month    1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31
month           1 2 3 4 5 6 7 8 9 10 11 12
day of week     1 2 3 4 5 6 7
command         /bin/grep -i /tmp/txt
#
```

Alternatively, the built image can be downloaded from Docker Hub
```
> docker pull aeshepherd/cron-parser:latest
```

As a final option the code can be compiled, tested, packaged and then run
```
> mvn package
[INFO] Scanning for projects...
[INFO]
[INFO] ------------------------------------------------------------------------
[INFO] Building CronParser 1.0
[INFO] ------------------------------------------------------------------------
<snip />
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time: 12.175 s
[INFO] Finished at: 2018-07-05T00:22:34+01:00
[INFO] Final Memory: 42M/377M
[INFO] ------------------------------------------------------------------------
> java -jar ./target/CronParser-1.0.jar 1 1 * * * /bin/find -i fred.txt
minute          1
hour            1
day of month    1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31
month           1 2 3 4 5 6 7 8 9 10 11 12
day of week     1 2 3 4 5 6 7
command         /bin/find -i fred.txt
>
