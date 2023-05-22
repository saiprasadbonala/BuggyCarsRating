# BuggyCarsRating
This is a BDD based cucumber automation framework built using Java & Selenium to test the 'Buggy Cars Rating' application

Tools/Technologies Used:
1. Selenium 4.8.3
2. Java (1.8 or above is preferred)
3. Cucumber 7.12.0
4. JUnit 4.13.2
5. Eclipse IDE 

Pre-Requisites:
1. Install 'Cucumber Eclipse Plugin' extension from Eclipse MarketPlace
2. JDK [Not required explicitly for latest Eclipse versions]
3. Maven [Not required explicitly for latest Eclipse versions]

Pre-Requisites/Setup:
1. Clone the git repository using the command "git clone https://github.com/saiprasadbonala/BuggyCarsRating.git"
2. Navigate to project root (where .git is available), run the command "git checkout -b [BRANCH_NAME]"
3. Run "git pull"
4. Open the project using Eclipse IDE
5. Install 'Cucumber Eclipse Plugin' extension from Eclipse MarketPlace
6. JDK (Not required explicitly for latest Eclipse versions)
7. Maven (Not required explicitly for latest Eclipse versions)
8. Build the project and make sure the build path for JRE is set to 1.8 or above
9. If observed any build issues, click on Project tab > Select 'Update Maven Project' option and perform the Step-3 again

How to Run tests and check results?
1. From 'src/test/java/', open TestRunner.java under runners package
2. Right click and run as 'JUnit test'
3. The scenarios listed in all the feature files will be triggered in sequential order
4. Test Results can be viewed under taget folder of the project (Eg: target/HTMLReports/report.html)

Note: Total of 15 scenarios are implement, out of which 13 passes and 2 fail with existing issues in the application. These 2 bugs are included as part of Bug Report document

