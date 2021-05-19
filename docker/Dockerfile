FROM ubuntu:16.04

RUN apt-get update &&           \
    apt-get install git -y &&   \
    apt-get install maven -y && \
    apt-get clean

# OPEN JDK 8 INSTALLATION WITHOUT TIMEZONE ETC

RUN apt-get update && \
        apt-get install -y openjdk-8-jdk && \
        apt-get install -y ant && \
        apt-get clean && \
        rm -rf /var/lib/apt/lists/* && \
        rm -rf /var/cache/oracle-jdk8-installer;

RUN apt-get update && \
        apt-get install -y ca-certificates-java && \
        apt-get clean && \
        update-ca-certificates -f && \
        rm -rf /var/lib/apt/lists/* && \
        rm -rf /var/cache/oracle-jdk8-installer;

# Setup JAVA_HOME, this is useful for docker commandline
ENV JAVA_HOME /usr/lib/jvm/java-8-openjdk-amd64/
RUN export JAVA_HOME


RUN git clone https://github.com/isicju/ee_spring.git && cd ee_spring && git checkout no_ui_tests


EXPOSE 8080
