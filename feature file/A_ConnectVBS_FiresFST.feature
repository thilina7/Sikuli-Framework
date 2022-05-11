Feature: Connect VBS and Fires FST

  
  Scenario: Open FireFST
    Given User search on the FireFST icon on the Desktop
    And User double click on the FireFST icon on the desktop
    #Then User should see the FireFST application open successfully
    And User go back to the desktop to open the VBS Application

  
  Scenario: Open VBS
    Given User search on the VBS icon on the Desktop
    And User double click on the VBS icon on the desktop
    Then User should see the VBS application open successfully
    And User click on the "Login" button to go to the "Main Menu"
    Then User should see the Main Menu items
    And User click on the "Networking" button to go to view all the networks
    Then User should see all the networks in this menu
    And User should click on the "Host Session"
    And User should click "New" button to go to "Host Server screen"
    Then User should see the "Host server" screen

  
  Scenario: Open HSDS Mission
    And User select the mission which already created "HSDS Only Mission"
    And User should click "Start" button to host the Mission
    Then User should see the Player Selection screen
    And User click on the ok button select the Player
    Then User should see the Start Mission screen
    And User click on the Mission start ok button

  
  Scenario: Open FST Mission
    And User select the FST mission which already created "FST Only Mission"
      | Tag Name | path            |
      | FSTOnly  | FSTonly_Mission |
    And User should click "Start" button to host the Mission
    Then User should see the FST Player Selection screen
      | Tag Name  | path   |
      | FST_Unit1 | FST_R1 |
    And User click on the ok button select the Player
    Then User should see the Start Mission screen
    And User click on the Mission start ok button

 
  Scenario: Going back to the FST Application
    And User click on the FST icon from the Taskbar
      | Tag Name         | path             |
      | FST_TASKBAR_icon | FST_TASKBAR_icon |
