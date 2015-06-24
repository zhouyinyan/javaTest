#!/usr/bin/env bash
#通过此脚本实现从服务器拉代码,并使用tomcat-maven插件启动应用
#使用方法:
#启动参数: start gzt dev
#停止参数: stop gzt
#查看状态: status gzt

#判断当前目录下是否有svn本地rep,
#如果有,返回1,并且更新svn
#如果没有,则返回0
function chekSvnLocalRepExists(){
    exists=`ls -l |grep -e "$sysname$"`
    if [ -n "$exists" ]; then
            cd $sysname
            skipped=`svn up  |grep Skipped`
            if [ -n "$skipped" ]; then
                echo "项目:$sysname svn路径:`pwd` 更新失败"
                cd ..
                exit 0
            else
                echo "项目:$sysname svn路径:`pwd` 更新成功"
                cd ..
                suc_in_local='true';
                return;
            fi
    fi
}

function checkProjectInSvn(){
        read -p "请输入$sysname项目svn路径:" svn_url
        exists_url=`svn info $svn_url |grep UUID:`
        if [   -n "$exists_url" ]; then
            svn co $svn_url $sysname
        else
            echo "项目:$sysname svn路径[$svn_url]不存在"
            exit 0
        fi
}

function getpid(){
    pid=`ps -ef|grep -e "name=$sysname$" |grep -v grep |awk '{print $2}'`
}
function help(){
    echo "启动应用: $0 start gzt dev" >&2
    echo "关闭应用: $0 stop gzt " >&2
    echo "查看应用状态应用: $0 status gzt " >&2
}

function checkLog(){
	#检查日志目录是否存在
    echo  "日志目录:$logdir"
    if [ ! -d "$logdir" ]; then
        echo "创建日志目录"
        mkdir  -p "$logdir"
    fi
    #检查启动日志文件是否存在
    if [ -f  "$logfile" ]; then
        rm "$logfile"
    fi
}

#检查启动脚本是否有系统名
if [ x$2 != x ]; then
        sysname=$2
else
        help
        exit 0
fi
#reps=(svn://192.168.45.206/core/ svn://192.168.45.206/finance/ svn://192.168.45.206/prodevelop/)
sysrep=
sys_trunk=
suc_in_local=
logdir=/var/log/webapps/$sysname
logfile=$logdir/console.log
export MAVEN_OPTS="-Xms256M -Xmx512m -XX:PermSize=64m -XX:MaxPermSize=256m -XX:+PrintGCDateStamps -XX:+PrintGCDetails  -verbosegc -Xloggc:/var/log/webapps/$sysname/gc.log -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/var/log/webapps/$sysname/oom.hprof -Djava.awt.headless=true -Djava.net.preferIPv4Stack=true  $APP_JAVA_OPTS"

getpid



case $1 in
start)
    echo   "启动系统:$sysname"
    checkLog
    echo "控制台日志:$logfile"
    #检查启动脚本是否有启动环境变量
    if [ x$3 != x ]; then
        env=$3
        echo "环境变量:$env"
    else
        echo "请设置环境变量!"
        exit 0
    fi

    #检查进程是否存在
    if [ ! -z "$pid" ]; then
      echo "$sysname 已经在运行,进程id=$pid"
      "$0" stop $2
      sleep 3
    fi
    
    chekSvnLocalRepExists
    if [ "$suc_in_local" = "" ]; then
        checkProjectInSvn
    fi

    nohup mvn -f ./$sysname clean  tomcat7:run -Dspring.profiles.active=$env -Dsys.name=$sysname  > "$logfile" 2>&1 < /dev/null &

    getpid
    echo "$sysname 开始启动,进程id=$pid"

    sleep 2
    tail -100f $logfile
    ;;

stop)
    echo  "停止系统:$sysname"

    if [  -z "$pid" ]; then
        echo "$sysname 没有运行!"
    else
        echo "应用pid=$pid"
        kill $pid
        sleep 5;
        getpid
        if [  -z "$pid" ]; then
            echo "$sysname 已停止运行!"
        else
            echo "应用停止失败,强制关闭进程."
            kill -9 $pid
        fi
    fi
    ;;
status)
    if [ ! -z "$pid" ]; then
          echo "$sysname 已经在运行,进程id=$pid"
          jmap -heap $pid
          exit 0
    else
        echo "$sysname 没有运行!"
        exit 0
    fi
    ;;
*)
    help
esac
