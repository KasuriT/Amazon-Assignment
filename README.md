# Amazon-Assignment
**Purpose**
This framework for automated tests is the implementation of a practical software assignment to one of the company's tester positions.
Amazon web pages are automated by writing test suites using selenium-webdriver and TestNg as testing framework

**The following key modules/pages are automated:**
- Start the selected Browser
- Search Product
- Sort Product 
- Select Product
- Title Verification
Key test cases(total 5) are written for each module and test suites created positive and test cases.
A state-transition flow of test-cases are designed and run like a user searching a product from the Amazon site.
For failed test cases it will take a screen-shot as well at the point of failure.

**Scenario O1: Start the selected Bowser**

  Given Navigated to the Amazon home screen "https://www.amazon.com/"

  And I click the URL "https://www.amazon.com/"

  And I click on the enter key

  And I navigate to the Amazon home screen

**Scenario O2: Search the Product**

  And I click on the "Search" button typing the product name as "Nikon"

  And I click on the enter key

  And Screen navigate to the "Nikon" product screen

  Then It display the list of Nikon products

**Scenario O3: Sort the product category as "Price: High to Low"**

  And I click on the "Sort by: Featured" button
  
  And I select the "Price: High to Low" category from the drop down menu
  
  And I click on the "Price: High to Low" category from the drop down menu
  
  Then It display the list of Nikon product which is sorted according to "High - Low" category 
  
**Scenario O4: Select the second product from the List**
  
  And I select & click on the second product from the available list.
  
  And screen navigated to the selected product
  
  Then Display the detail of selected product
  
**Scenario O5: Verify that the product topic/header contains text “Nikon D3X”**
 
  And verify that the product topic/header contains text "Nikon D3X"
  
  And It display the test result (Whether header contains text "Nikon D3X" or header does not contains text "Nikon D3X" )

  Then display the test result.
  
**Technology:**
- Tool: Selenium Webdriver
- Language: Java
- Testing Framework : TestNG

**Prerequisite:**
- Need to install jdk 11
- Configure Environment variable for jdk 11
- Clone this project and unzip it
- Open the project folder
- Let the project build successfully
- Click on "Terminal" and run the automation scripts
