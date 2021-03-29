@WORDPRESS_SMOKE_MEPAGE 
Feature: Login Page Functionality

@login
Scenario: Login functionality
Given User launch Application URL
And User set username
And User set password
And User click login button
Then Verify user lands on home page

