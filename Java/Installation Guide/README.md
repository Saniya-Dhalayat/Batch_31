SOP – Java Installation Guide (Local Setup / Dev Environment)
1. Purpose

This SOP defines the standard procedure to install and configure Java (OpenJDK) on a developer’s workstation or server.
The goal is to ensure consistent Java versions across all environments supporting OT-MICROSERVICES such as salary-api, employee-api, and other Java-based services.

2. Scope

This document applies to:

Developers setting up local environments (Ubuntu / Debian / Amazon Linux / Windows WSL2)

DevOps engineers configuring build servers (Jenkins, CodeBuild, etc.)

Any environment running Spring Boot, Maven, or Gradle applications within OT-MICROSERVICES.

3. Pre-Requisites
Requirement	Description
OS	Ubuntu 22.04 / Debian 12 / Amazon Linux 2
Privileges	sudo access required
Network	Internet connection to download packages
Tools	curl, wget, or apt package manager
4. System Requirements
Component	Minimum	Recommended
CPU	2 cores	4+ cores
RAM	4 GB	8 GB
Disk	2 GB free	5 GB free
Java Version	OpenJDK 17	OpenJDK 21
5. Procedure
Step 1: Update System Packages
sudo apt update && sudo apt upgrade -y

Step 2: Install OpenJDK
Option A: Install OpenJDK 17 (Default for OT-MICROSERVICES)
sudo apt install -y openjdk-17-jdk

Option B: Install OpenJDK 21 (Optional – for new projects)
sudo apt install -y openjdk-21-jdk

Step 3: Verify Java Installation
java -version


Expected Output:

openjdk version "17.0.10" 2025-01-15
OpenJDK Runtime Environment (build 17.0.10+7-Ubuntu-0ubuntu122.04)
OpenJDK 64-Bit Server VM (build 17.0.10+7-Ubuntu-0ubuntu122.04, mixed mode)

Step 4: Set JAVA_HOME

Find your Java installation path:

sudo update-alternatives --config java


Then set JAVA_HOME in the environment file:

sudo nano /etc/environment


Add or update the following line:

JAVA_HOME="/usr/lib/jvm/java-17-openjdk-amd64"


Reload environment variables:

source /etc/environment
echo $JAVA_HOME

Step 5: Configure Java Alternatives (if multiple versions exist)
sudo update-alternatives --config java


Select the desired version (e.g., OpenJDK 17).
You can check with:

readlink -f $(which java)

Step 6: Validate Setup with Maven or Gradle
javac -version
mvn -v
gradle -v


If all commands execute successfully, your Java environment is correctly configured.

7. Verification Commands
Check	Command
Java version	java -version
Compiler version	javac -version
Java path	which java
JAVA_HOME	echo $JAVA_HOME

8. Troubleshooting
Issue	Cause	Solution
java: command not found	Java not installed or PATH missing	Reinstall Java and check $PATH
Wrong Java version used	Multiple JDKs installed	Run sudo update-alternatives --config java
Maven build fails	JAVA_HOME not set	Export JAVA_HOME in ~/.bashrc or /etc/environment
Jenkins job fails	Jenkins not restarted after Java change	Restart Jenkins and reconfigure global JDK
Permission denied	Non-sudo user installing Java	Use sudo for installation

9. Rollback Procedure

To remove Java:

sudo apt remove --purge -y openjdk-* && sudo apt autoremove -y
sudo rm -rf /usr/lib/jvm/java-*

10. References

OpenJDK Official Downloads

Ubuntu Java Docs

