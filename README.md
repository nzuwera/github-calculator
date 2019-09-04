[![CircleCI](https://circleci.com/gh/nzuwera/github-calculator.svg?style=svg)](https://circleci.com/gh/nzuwera/github-calculator) [![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=nzuwera_github-calculator&metric=alert_status)](https://sonarcloud.io/dashboard?id=nzuwera_github-calculator)
# calculator
Spring Rest Docs tutorial

## Dependency installation

```sh 
$ apt-get install -y default-jdk
$ apt-get install -y maven
```

## Get application application
```sh
$ git clone https://github.com/nzuwera/github-calculator.git
```

## Building the application
```sh 
$ cd github-calculator
$ mvn clean package
$ mkdir -p /opt/apps/calculator
$ chown -R $USER:$USER /opt/apps/calculator
$ cp target/github-calculator-1.0.jar /opt/apps/calculator/github-calculator-1.0.jar
$ chmod u+x /opt/apps/calculator/github-calculator-1.0.jar
```

## Test the application
```sh
$ cd /opt/apps/calculator
$ java -jar github-calculator-1.0.jar
```

## Install the application as a service
```sh
$ cd ~/github-calculator
$ cp scripts/calculator.service /etc/systemd/system/calculator.service
$ cp scripts/calculator.sh /opt/apps/calculator/
$ systemctl daemon-reload
$ systemctl enable calculator.service
```

## Start the application
```sh
$ systemctl start calculator
$ systemctl status calculator
```

## Usage 
This Endpoint support the follow arithmetic operations:

| Operation | Supported Values |
| --------- | --------------- |
| Addition | A |
| Substration | S |
| Multiplication | M |
| Division | D |

### Request

The request for is build as follow:
> http://{HOSTNAME_OR_IP}:8080/{operand}/{numberA}/{numberB}
```sh
curl 'http://localhost:8080/calculator/A/1/1' -i -X GET 
```
### Response
```http
HTTP/1.1 200 OK
Content-Type: text/plain;charset=UTF-8
Content-Length: 9

1 + 1 = 2
```
