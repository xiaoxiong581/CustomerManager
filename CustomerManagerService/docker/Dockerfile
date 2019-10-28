FROM 10.0.2.15:5000/xiaoxiong581/base:1.0

MAINTAINER zhixiong15172@163.com

EXPOSE 19080 19081

ADD CustomerManager-1.0.0-SNAPSHOT.tar.gz /opt
ADD entrypoint.sh /opt

RUN find /opt -name "*.sh" | xargs dos2unix

ENTRYPOINT ["/opt/entrypoint.sh"]