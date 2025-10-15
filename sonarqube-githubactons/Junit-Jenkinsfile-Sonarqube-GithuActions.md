
---

# 📘 MyCalc Java Project – CI/CD with Maven, Jenkins, JUnit, SonarQube & GitHub Actions

This project demonstrates how to create a simple Java calculator application and automate its build, test, and deployment using Maven, Jenkins, SonarQube, GitHub Actions, and Docker.

---

## ✅ Prerequisites

* Java (JDK 11 or later)
* Eclipse IDE
* Apache Maven
* Git & GitHub account
* Jenkins installed
* SonarQube running (e.g., on `localhost:9000`)
* Docker installed
* GitHub Actions enabled on your repo

---

## 🧮 Step 1: Create Java Project in Eclipse

* Open Eclipse IDE
* Create a new Maven Project
* Set project name as: `gfg-batch36`
* Create a package: `mypkg`
* Add a class named `MyCalc.java`

```java
package mypkg;

public class MyCalc {
    public int sum(int a, int b) {
        return a + b;
    }

    public int diff(int a, int b) {
        return a - b;
    }

    public int mul(int a, int b) {
        return a * b;
    }

    public int div(int a, int b) {
        return a / b;
    }

    public static void main(String[] args) {
        MyCalc calc = new MyCalc();
        System.out.println("Sum is " + calc.sum(20, 10));
        System.out.println("Diff is " + calc.diff(20, 10));
        System.out.println("Mul is " + calc.mul(20, 10));
        System.out.println("Div is " + calc.div(20, 10));
    }
}
```

## ⚙️ Step 2: Run & Package with Maven

* Right-click project → Run As → Maven Install
* Go to `target` folder and check `.jar` file
* Try running the JAR from the command line:

```bash
cd target
java -jar gfg-batch36.jar
```
---

## 🧪 Step 3: Add JUnit Tests

* Create a new class `MyCalcTest.java` in `src/test/java/mypkg`
* Write test methods using JUnit:

```java
package mypkg;

import static org.junit.Assert.*;
import org.junit.Test;

public class MyCalcTest {
    MyCalc calc = new MyCalc();

    @Test
    public void test() {
        assertEquals(70, calc.sum(40, 30));
        assertEquals(10, calc.diff(40, 30));
    }
}
```

* Add JUnit dependency to `pom.xml`:

```xml
<dependency>
    <groupId>junit</groupId>
    <artifactId>junit</artifactId>
    <version>4.13.2</version>
    <scope>test</scope>
</dependency>
```

* Run `Junit test`

![alt text](<Screenshot (113)-1.png>)

---

## 🔧 Step 4: Create Jenkins Pipeline (Jenkinsfile)

* In your project root, create a file named `Jenkinsfile`
* Add the following pipeline code:

```groovy
pipeline {
    agent any
    tools {
        maven 'MAVEN_HOME'
    }
    stages {
        stage('Welcome Stage') {
            steps {
                echo 'Welcome to Jenkins Pipeline'
            }
        }
        stage('Clean Stage') {
            steps {
                bat 'mvn clean'
            }
        }
        stage('Test Stage') {
            steps {
                bat 'mvn test'
            }
        }
        stage('Build Stage') {
            steps {
                bat 'mvn install'
            }
        }
        stage('Build Success') {
            steps {
                echo 'Build Successful'
            }
        }
    }
}
```

![alt text](<Screenshot (115)-1.png>)

* Push Jenkinsfile to github

![alt text](<Screenshot (116)-1.png>)
![alt text](<Screenshot (117)-1.png>)

---

## 🛠️ Step 5: Configure Jenkins Job

* Open Jenkins → New Item → GFG-PIPELINE-PRO → Select **Pipeline** → OK
* In pipeline config, select “Pipeline script from SCM”
* Add your GitHub repo URL and `Jenkinsfile` path
* Save & Click **Build Now**

![alt text](<Screenshot (118)-1.png>)

---

## 🔄 Step 6: Enable Auto Build (Poll SCM)

* In Jenkins job → Configure → Check "Poll SCM"
* Add this schedule: `* * * * *` (runs every minute)
* Save

![alt text](<Screenshot (119)-1.png>)

* Update Jenkinsfile and push to github
* Automatically build will start

![alt text](<Screenshot (123)-1.png>)

---

## 🧪 Step 7: Add SonarQube Static Code Analysis

* Add SonarQube plugin to `pom.xml`:

```xml
<plugin>
  <groupId>org.sonarsource.scanner.maven</groupId>
  <artifactId>sonar-maven-plugin</artifactId>
  <version>3.9.1.2155</version>
</plugin>
```

* Add Sonar stage to Jenkinsfile:

```groovy
stage('SonarQube Analysis') {
    steps {
        bat 'mvn sonar:sonar'
    }
}
```

* Ensure SonarQube is running at `localhost:9000`
* Commit and push changes to GitHub
* Jenkins will trigger build and run analysis


---

## ✅ Step 8: Create SonarQube Quality Gate

* In SonarQube → Create a quality gate `GFG-QG`
* Add conditions:

  * Bugs > 5 → Fail
  * Code Smells > 5 → Fail
* Assign this quality gate to your project

## Check Code in sonarqube

![alt text](<Screenshot (136)-1.png>)

![alt text](<Screenshot (131)-1.png>)

![alt text](<Screenshot (132)-1.png>)

![alt text](<Screenshot (134)-1.png>)

* Now add errors & bugs in code 

![alt text](<Screenshot (130)-1.png>)

![alt text](<Screenshot (139)-1.png>)

![alt text](<Screenshot (140)-1.png>)

---

## 🚀 Step 9: Set Up GitHub Actions (CI Pipeline)

* Create file: `.github/workflows/maven.yml`
* Add workflow:

```yaml
name: Java CI with Maven

on:
  push:
    branches: [ main ]

jobs:
  build:
    runs-on: ubuntu-latest

    steps:
    - uses: actions/checkout@v2

    - name: Set up JDK 11
      uses: actions/setup-java@v2
      with:
        java-version: '11'

    - name: Build with Maven
      run: mvn clean install

    - name: Run Tests
      run: mvn test
```

![alt text](<Screenshot (142)-1.png>)

---

## 🟢 Step 10: Observe GitHub Actions Run

* Push code to GitHub
* Go to **Actions** tab
* Check build logs and status

![alt text](<Screenshot (143)-1.png>)

![alt text](<Screenshot (146)-1.png>)

* Now Correct code in Mycalc.jar file.
* Push to Github 
* Automatically build will start

![alt text](<Screenshot (147)-1.png>)

![alt text](<Screenshot (149)-1.png>)

---

## 🐳 Step 11: Add Docker Support

* Create a `Dockerfile`:

```dockerfile
FROM openjdk:11
COPY target/gfg-calc.jar /gfg-calc.jar
ENTRYPOINT ["java", "-jar", "/gfg-calc.jar"]
```

* Update GitHub Actions workflow to build and push Docker image:

```yaml
 - uses: mr-smithers-excellent/docker-build-push@v6
      name: Build & push Docker image
      with:
        image: zibriyal/gfgactions
        registry: docker.io
        username: ${{ secrets.DOCKER_USERNAME }}
        password: ${{ secrets.DOCKER_PASSWORD }}

- name: Push image
  run: docker push zibriyal/gfgactions
---

## 🧪 Step 12: Run Docker Image

* Pull and run the Docker image:

```bash
docker pull zibriyal/gfgactions
docker run zibriyal/gfgactions

---

## 📌 Conclusion

This project helped understand the complete CI/CD process for a Java Maven project:

* ✅ Code written in Java using Eclipse
* ✅ Managed builds and dependencies using Maven
* ✅ Automated tests using JUnit
* ✅ Created Jenkins pipelines and automated builds
* ✅ Performed static analysis with SonarQube
* ✅ Built GitHub Actions workflows
* ✅ Containerized the application using Docker

---
