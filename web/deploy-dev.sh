#!/bin/bash
BUILD_PATH=$(ls /home/ubuntu/app/build/nftgram-web-dev/*.jar)
JAR_NAME=$(basename $BUILD_PATH)
echo "> build 파일명: $JAR_NAME"

echo "> build 파일 복사"
DEPLOY_PATH=/home/ubuntu/app/deploy/nftgram-web-dev/
cp $BUILD_PATH $DEPLOY_PATH

echo "> nftgram-web-0.0.1.jar 교체"
CP_JAR_PATH=$DEPLOY_PATH$JAR_NAME
APPLICATION_JAR_NAME=nftgram-web-0.0.1-SNAPSHOT.jar
APPLICATION_JAR=$DEPLOY_PATH$APPLICATION_JAR_NAME

ln -Tfs $CP_JAR_PATH $APPLICATION_JAR

echo "> 현재 실행중인 애플리케이션 pid 확인"
CURRENT_PID=$(pgrep -f $APPLICATION_JAR_NAME)

if [ -z $CURRENT_PID ]
then
  echo "> 현재 구동중인 애플리케이션이 없으므로 종료하지 않습니다."
else
  echo "> kill -9 $CURRENT_PID"
  kill -9 $CURRENT_PID
  sleep 5
fi

nohup java -XX:MaxMetaspaceSize=512m -XX:MetaspaceSize=256m -jar -Duser.timezone=Asia/Seoul $APPLICATION_JAR --spring.profiles.active=dev --server.port=7160 1> /dev/null 2>&1 &

