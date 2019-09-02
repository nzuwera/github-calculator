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
$ sudo chmod u+x /opt/apps/calculator/github-calculator.jar
```

## Test the application
```sh
$ cd /opt/apps/calculator
$ java -jar github-calculator.jar
```

## Install the application as a service
```sh
$ cd ~/github-calculator
$ sudo cp scripts/calculator.service /etc/systemd/system/calculator.service
$ cp scripts/calculator.sh /opt/apps/calculator/
$ sudo systemctl daemon-reload
$ sudo systemctl enable calculator.service
```

## Start the application
```sh
$ sudo systemctl start calculator
$ sudo systemctl status calculator
```

## Usage 
This Endpoint support the follow arithmetic operations:

| Operation | Supported Values |
| --------- | --------------- |
| Addition | ADD or add |
| Substration | SUBSTRACT or substract |
| Multiplication | MULTIPLY or multiply |
| Division | DIVIDE or divide |

### Request

The request for is build as follow:
> http://<HOST>:8080/{operation}/{numberA}/{numberB}
```sh
curl 'http://localhost:8080/calculator/ADD/1/1' -i -X GET 
```
### Response
```http
HTTP/1.1 200 OK
Content-Type: text/plain;charset=UTF-8
Content-Length: 9

1 + 1 = 2
```
