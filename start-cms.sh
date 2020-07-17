#!/usr/bin/env bash
# JMX settings
if [[ -z "$KAFKA_JMX_OPTS" ]]; then
  KAFKA_JMX_OPTS="-Dcom.sun.management.jmxremote -Dcom.sun.management.jmxremote.authenticate=false  -Dcom.sun.management.jmxremote.ssl=false"
fi

CONSOLE_OUTPUT_FILE="EmployeesServer.out"
JMX_PORT=4899

# JMX port to use
if [[  ${JMX_PORT} ]]; then
  KAFKA_JMX_OPTS="$KAFKA_JMX_OPTS -Dcom.sun.management.jmxremote.port=$JMX_PORT"
fi

JVM_OPTS="-XX:+UseConcMarkSweepGC -XX:+PrintGCDetails -XX:+PrintGCDateStamps -XX:+PrintGCTimeStamps -Xloggc:gc.log"


nohup java -server ${KAFKA_JMX_OPTS} ${JVM_OPTS} -jar empserver-0.0.1-SNAPSHOT.jar  > "$CONSOLE_OUTPUT_FILE" 2>&1 < /dev/null &
