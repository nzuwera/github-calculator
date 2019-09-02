[![CircleCI](https://circleci.com/gh/nzuwera/github-calculator.svg?style=svg)](https://circleci.com/gh/nzuwera/github-calculator) [![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=nzuwera_github-calculator&metric=alert_status)](https://sonarcloud.io/dashboard?id=nzuwera_github-calculator)
# calculator
Spring Rest Docs tutorial

## Dependency installation

```sh 
$ sudo apt-get install -y default-jdk
$ sudo apt-get install -y maven
```

## Get application application
```sh
$ git clone https://github.com/nzuwera/github-calculator.git
```

## Building the application
```sh 
$ cd github-calculator
$ mvn clean package
$ sudo mkdir -p /opt/apps/calculator
$ sudo chown -R $USER:$USER /opt/apps/calculator
$ mv target/github-calculator.jar /opt/apps/calculator/github-calculator.jar
```
