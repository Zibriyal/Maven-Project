

---

## 1. Create Maven Project in Eclipse

**Step:**

* Open Eclipse → *File* → *New* → *Maven Project*
* Choose “Create a simple project (skip archetype)” (or select a suitable archetype)
* Set **Group Id** (e.g. `mypkg`), **Artifact Id** (e.g. `gfg-batch36`)
* Finish so that Eclipse generates the standard Maven folder structure

---

## 2. Add `MyCalc.java` to the project

**Step:**

* In `src/main/java`, create package `mypkg`

* Inside that, add `MyCalc.java` with the following initial content:

  ```java
  package mypkg;

  public class MyCalc {
      public int sum(int a, int b) {
          return a + b;
      }

      public static void main(String[] args) {
          MyCalc calc = new MyCalc();
          System.out.println("sum is " + calc.sum(20, 10));
      }
  }
  ```

* Save the file

**Screenshot:**

![alt text](<Screenshot (84)-1.png>)

---

## 3. Run as Java Application in Eclipse

**Step:**

* Right-click `MyCalc.java` → *Run As* → *Java Application*
* Check console output: it should show

  ```
  sum is 30
  ```

**Screenshot:**

![alt text](<Screenshot (85)-1.png>)

---
**Step:**
* Run as Maven Install

* Source code
![alt text](<Screenshot (86)-1.png>)

* Build
![alt text](<Screenshot (87)-1.png>)

* Run Build in CMD
* cd c:\User\imran\eclipse-workspace\gfgbatch36\taget
* java -jar gfg-batch36-0.0.1-SNAPSHOT.jar
* error - not recognizing in which class main function present
---
## 4. Add Maven plugin configuration in `pom.xml`

**Step:**

* Open `pom.xml`

* Under `<build><plugins>`, add the `maven-jar-plugin` configuration so that your JAR has the proper `Main-Class` in its manifest. For example:

  ```xml
  <build>
    <plugins>
      <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-jar-plugin</artifactId>
        <version>3.1.0</version>
        <configuration>
          <archive>
            <manifest>
              <mainClass>mypkg.MyCalc</mainClass>
            </manifest>
          </archive>
        </configuration>
      </plugin>
    </plugins>
  </build>
  ```

* Save `pom.xml`

**Screenshot:**

![alt text](<Screenshot (88)-1.png>)

---

## 5. Run “Maven install” in Eclipse

**Step:**

* In Eclipse, right-click the project → *Run As* → *Maven install*
* This performs `compile`, `package`, etc., and builds the JAR into `target/`

**Screenshot:**

![alt text](<Screenshot (89)-1.png>)

---

## 6. Locate JAR in `target/` directory

**Step:**

* In your project folder or via Eclipse, expand the `target/` directory
* You should see a file like `gfg-calc.jar` (or with your version)

---

## 7. Run the JAR via command line (CMD / Terminal)

**Step:**

* Open a terminal or command prompt

* Navigate to `target/` folder:

  ```
  cd c:\User\imran\eclipse-workspace\gfgbatch36\target
  ```

* Run:

  ```
  java -jar gfg-calc.jar
  ```

* You should see:

  ```
  sum is 30
  ```

* If you get **“no main manifest attribute”** or “Could not find or load main class”, then the manifest is missing or malformed.

**Screenshot:**

*![alt text](<Screenshot (90)-1.png>)*

---

## 8. Add more methods (`diff`) to `MyCalc.java`

**Step:**

* Modify `MyCalc.java` to:

  ```java
  package mypkg;

  public class MyCalc {
      public int sum(int a, int b) {
          return a + b;
      }
      public int diff(int a, int b) {
          return a - b;
      }
      
      public static void main(String[] args) {
          MyCalc calc = new MyCalc();
          System.out.println("sum is " + calc.sum(20, 10));
          System.out.println("Diff is " + calc.diff(20, 10));
        
      }
  }
---

## 9. Rebuild with Maven install & run the JAR again

**Step:**

* In Eclipse: *Run As → Maven install*
* Then in terminal again run `java -jar ...`
* The output should now be:

  ```
  sum is 30  
  Diff is 10  

  ```

## 10. Push project to GitHub

**Step:**

* Create a new GitHub repository (e.g. `Maven-Project`)

* In your local project folder:

  ```bash
  git init
  git add .
  git commit -m "Initial commit with MyCalc"
  git remote add origin <github_repo_url>
  git push -u origin master
  ```

* Your project (with `pom.xml`, `src/`, `README.md`) should now be on GitHub

**Screenshot:**

*(![alt text](<Screenshot (91)-1.png>))*

---

## 11. Create Jenkins job for the project

**Step:**

* Open Jenkins → *New Item*
* Enter a name (e.g. `MAVEN-GFG-PROJECT`)
* Choose **Maven Project** (with Maven build steps)
* Click *OK*

**Screenshot:**

*(![alt text](<Screenshot (92)-1.png>))*

---

## 12. Configure Git & build steps in Jenkins

**Step:**

* In job configuration:

  * Under *Source Code Management* → select Git, enter your GitHub repo URL
  * Under *Build* section:

    * If Maven Project: put goals, e.g. `clean install`

---

## 13. Trigger Build Now & check console output

**Step:**

* In your Jenkins job, click **Build Now**
* Observe in *Build History* that a new build (#1) appears
* Click on the build number → *Console Output*
* You should see logs of Maven build, success, and artifact created

**Screenshot:**

![alt text](<Screenshot (93)-1.png>)
![alt text](<Screenshot (95)-1.png>)

---

## 14. Copy the built JAR & run locally (from Jenkins workspace)

**Step:**

* Jenkins stores workspace artifacts under `JENKINS_HOME/workspace/MAVEN-GFG-PROJECT`
* Under `workspace/MAVEN-GFG-PROJECT/target/` find the JAR built by Jenkins
* Copy that JAR (via SCP, share or local copy) to a machine where you can run it
* Run: `java -jar GFG-CLAC.JAR`
* Verify output (sum, diff)

**Screenshot:**

![alt text](<Screenshot (96)-1.png>)
![alt text](<Screenshot (97)-1.png>)

---
## 14A. Manual approach-Add more methods (`Mul`) to `MyCalc.java`

**Step:**

![alt text](<Screenshot (98)-1.png>)

* Push to Github and Triger maven through Build in Jenkins

![alt text](<Screenshot (99)-1.png>)
![alt text](<Screenshot (102)-1.png>)

* Run in CMD

![alt text](<Screenshot (101)-1.png>)

---
## 15. Enable Poll SCM (cron style) in Jenkins Automatic Approach

**Step:**

* In the job configuration → scroll to *Build Triggers*
* Check **Poll SCM**
* Enter schedule, e.g. `* * * * *` (every minute)
* Save the job

This makes Jenkins poll GitHub automatically for changes and trigger build when new commits arrive.

**Screenshot:**

![alt text](<Screenshot (103)-1.png>)

---

## 16. Add new Div Method → commit & push → observe automation


**Step:**

* In your code (e.g. add another Div method), save

![alt text](<Screenshot (104)-1.png>)

![alt text](<Screenshot (105)-1.png>)

![alt text](<Screenshot (106)-1.png>)

* Wait (Jenkins poll interval) → Jenkins should detect change, start a build
* Check console output of new build → successful build
* Copy new JAR and run it to verify updated behavior

**Screenshot:**

![alt text](<Screenshot (107)-1.png>)


![alt text](<Screenshot (108)-1.png>)

---

## ✅ Conclusion

This project helped me understand the complete **Java + Maven + Jenkins** workflow in a real-world, end-to-end scenario.

Starting from writing a simple Java class `MyCalc` with basic arithmetic methods, I explored how to:

* Structure a **Maven** project in **Eclipse**
* Use **Maven lifecycle** commands to build and package code into executable JAR files
* Fix and configure the `pom.xml` with plugins like `maven-jar-plugin` to define a `Main-Class`
* Run Java programs directly from the **command line** using `java -jar`
* Set up **version control** by pushing the project to **GitHub**
* Automate builds using **Jenkins** by connecting it to a GitHub repository
* Use **Poll SCM (cron jobs)** to automatically trigger builds on new commits

---

## 📘 About the Project

**Project Name:** MyCalc
**Folder Name:** gfg-batch36
**Language:** Java
**Tools Used:** Eclipse, Maven, Git, GitHub, Jenkins
**Project Type:** Console-based Arithmetic Calculator

**Functionality Implemented:**

* `sum(int a, int b)`
* `diff(int a, int b)`
* `mul(int a, int b)`
* `div(int a, int b)`

Each function was added incrementally, with builds and automation tested after every step.

---

## 🧰 Tools & Technologies Learned

| Tool / Tech        | What I Learned                                                    |
| ------------------ | ----------------------------------------------------------------- |
| **Java**           | Writing and running simple programs, using `main()`               |
| **Eclipse**        | Creating Maven projects, writing/debugging code                   |
| **Maven**          | Building, packaging, managing dependencies, setting main class    |
| **Command Line**   | Running JAR files, navigating file system                         |
| **Git & GitHub**   | Version control, pushing code, syncing with Jenkins               |
| **Jenkins**        | Creating jobs, triggering builds on push, using cron jobs         |
| **CI/CD Concepts** | Automating build pipelines from GitHub → Jenkins → Build artifact |

---

## 🌟 Key Takeaways

* A working **CI/CD pipeline** can greatly streamline software development and deployment.
* **Automating repetitive tasks** (like building/testing on every code change) improves reliability and efficiency.
* Even a simple Java program becomes powerful when integrated with modern DevOps tools.
* **Version control + Build automation + Scheduling** are core pillars of any professional software project.

---
