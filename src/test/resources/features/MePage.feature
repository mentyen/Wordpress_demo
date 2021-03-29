@WORDPRESS_SMOKE_MEPAGE
Feature: Title of your feature
  I want to use this template for my feature file

  Background: 
    Given A workbook named "TestData" and sheet named "mepage" is read

  @tag1
  Scenario: Master Bar >>> elements validation
    Given Verify all elements populating on Master Bar

  @tag2
  Scenario: My Profile >>> details functionality
    Given User set first name
    And User set last name
    And User set Public display name
    And User set About me
    And User tap Save profile details button
    Then Verify entered records populates on account profile page

  @tag3
  Scenario: My Profile >>> photo update functionality
    Given User tap Click to change photo
    And User set file path and tap open
    And User tap Change My Photo
    Then Verify You successfully uploaded a new profile photo text populates on My Profile page

  @tag4
  Scenario: Sidebar menu link validation >>> Account Settings
    Given User tap Account Settings
    Then Verify user lands on Account settings page

  @tag5
  Scenario: Sidebar menu link validation >>> Purchases
    Given User tap Purchases
    Then Verify user lands on Purchases page

  @tag6
  Scenario: Sidebar menu link validation >>> Security
    Given User tap Security
    Then Verify user lands on Security page

  @tag7
  Scenario: Sidebar menu link validation >>> Privacy
    Given User tap Privacy
    Then Verify user lands on Privacy page

  @tag8
  Scenario: Sidebar menu link validation >>> Manage Blogs
    Given User tap Manage Blogs
    Then Verify user lands on Manage Blogs page

  @tag9
  Scenario: Sidebar menu link validation >>> Notification Settings
    Given User tap Notification Settings
    Then Verify user lands on Notification Settings page

  @tag10
  Scenario: Sidebar menu link validation >>> Blocked Sites
    Given User tap Blocked Sites
    Then Verify user lands on Blocked Sites page

  @tag11
  Scenario: Sidebar menu link validation >>> Get Apps
    Given User tap Get Apps
    Then Verify user lands on Get Apps page

  @tag12
  Scenario: Broken links validation
    Then Validate all broken links
