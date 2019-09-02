#!/bin/bash

#Variables
HOME_DIR=/home/ubuntu
APP_DIR=/opt/apps/calculator
# Run this script with sudo

# Dependency

cd $HOME_DIR

apt-get install -y default-jdk
apt-get install -y maven

git clone https://github.com/nzuwera/github-calculator.git

cd $HOME_DIR/github-calculator
mvn clean package
mkdir -p $APP_DIR
chown -R $USER:$USER $APP_DIR
cp target/github-calculator-1.0.jar $APP_DIR/github-calculator-1.0.jar
chmod u+x $APP_DIR/github-calculator-1.0.jar


cp $HOME_DIR/github-calculator/scripts/calculator.service /etc/systemd/system/calculator.service
cp $HOME_DIR/github-calculator/scripts/scripts/calculator.sh $APP_DIR
systemctl daemon-reload
systemctl enable calculator.service


sudo systemctl start calculator
sudo systemctl status calculator