# LoanSim — Loan Approval Simulator

Spring Boot (REST) + vanilla HTML/CSS/JS (+ Chart.js).  
Simulates a loan decision with a simple scorecard, persists submissions in an in-memory H2 database, and shows an amortization chart.


## 🚀 Quick Start

### Requirements
- **JDK 17+** (Project uses Gradle wrapper; you don’t need Gradle installed)
- Internet access (loads Chart.js via CDN)

### Run it
```bash
# 1) Clone
git clone https://github.com/KevinLanahan/loan-sim.git
cd loan-sim

# 2) Start the app (macOS/Linux)
./gradlew bootRun

#    On Windows (PowerShell):
.\gradlew.bat bootRun
#    On Windows (cmd.exe):
gradlew.bat bootRun
