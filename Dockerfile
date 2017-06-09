


MAINTAINER Etienne Cooijmans <etiennecooijmans@gmail.com>

FROM payara/server-full:latest

COPY /target/slackernews-1.0-SNAPSHOT.war /home/etienne/payara41/glassfish/domains/domain1/autodeploy

