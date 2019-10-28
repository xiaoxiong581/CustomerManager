#!/bin/bash

mkdir -p /var/log/xiaoxiong581/CustomerManager

sh /opt/SpringBoot/start.sh > /var/log/xiaoxiong581/CustomerManager/init.log && tail -F /var/log/xiaoxiong581/CustomerManager/init.log