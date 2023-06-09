# BuggyCarsRating
This is a BDD based cucumber automation framework built using Java & Selenium to test the 'Buggy Cars Rating' application

# Tools/Technologies Used:
1. Selenium 4.8.3
2. Java (1.8 or above is preferred)
3. Cucumber 7.12.0
4. JUnit 4.13.2
5. Eclipse IDE 

# Pre-Requisites/Setup:
1. Clone the git repository using the command "git clone https://github.com/saiprasadbonala/BuggyCarsRating.git"
2. Open the project using Eclipse IDE
3. Install 'Cucumber Eclipse Plugin' extension from Eclipse MarketPlace
4. JDK (Not required explicitly for latest Eclipse versions)
5. Maven (Not required explicitly for latest Eclipse versions)
6. Build the project and make sure the build path for JRE is set to 1.8 or above
7. If observed any build issues, click on Project tab > Select 'Update Maven Project' option and perform the Step-6 again

# How to Run tests and check results?
1. From 'src/test/java/', open TestRunner.java under runners package
2. Right click and run as 'JUnit test'
3. The scenarios listed in all the feature files will be triggered in sequential order
4. Test Results can be viewed under taget folder of the project (Eg: target/HTMLReports/report.html)

# Results: 
Total of 15 scenarios are automated, out of which 13 tests passed and 2 tests failed with existing issues in the application. These 2 issues are included as part of Bug Report document

