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

# OPEN JDK 8 Certificates
RUN apt-get update && \
        apt-get install -y ca-certificates-java && \
        apt-get clean && \
        update-ca-certificates -f && \
        rm -rf /var/lib/apt/lists/* && \
        rm -rf /var/cache/oracle-jdk8-installer;

# Setup JAVA_HOME, this is useful for docker commandline
ENV JAVA_HOME /usr/lib/jvm/java-8-openjdk-amd64/
RUN export JAVA_HOME


#Setup for phantom js driver (WHAT A SHAME)
RUN apt-get update &&  apt-get install -y build-essential chrpath libssl-dev libxft-dev libfreetype6 libfreetype6-dev libfontconfig1 libfontconfig1-dev && \
 apt-get install -y wget && \
 wget https://bitbucket.org/ariya/phantomjs/downloads/phantomjs-2.1.1-linux-x86_64.tar.bz2 && \
 tar xvjf phantomjs-2.1.1-linux-x86_64.tar.bz2 -C /usr/local/share/ && \
 ln -sf /usr/local/share/phantomjs-2.1.1-linux-x86_64/bin/phantomjs /usr/local/bin && \
 chmod 755 /usr/local/bin/phantomjs

#Fetching project
RUN git clone https://github.com/isicju/ee_spring.git && cd ee_spring && git fetch && git checkout ui_test_active

#Fixing problem with Java ceritiface issue with Mail sending. As alternative - java 14 is required.
RUN rm -rf /etc/java-8-openjdk/security/java.security && cp /ee_spring/java.security /etc/java-8-openjdk/security/java.security

EXPOSE 8080
