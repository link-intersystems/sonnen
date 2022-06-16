#!/bin/bash

function start(){
	docker-compose pull
	docker-compose up -d
}


function stop(){
	docker-compose down
}

case "$1" in
    start)   start;;
    stop)    stop ;;
    restart) stop; start ;;
    *) echo "usage: $0 start|stop|restart" >&2
       exit 1
       ;;
esac
