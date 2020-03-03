Feature: 6.2.1 Validating SEAD engagement times panel (CAS integration for SEAD missions)

  @dontRun
  Scenario: Open FireFST
    Given User search on the FireFST icon on the Desktop
    And User double click on the FireFST icon on the desktop
    #Then User should see the FireFST application open successfully
    And User go back to the desktop to open the VBS Application

  @dontRun
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

  @dontRun
  Scenario: Open HSDS Mission
    And User select the mission which already created "HSDS Only Mission"
    And User should click "Start" button to host the Mission
    Then User should see the Player Selection screen
    And User click on the ok button select the Player
    Then User should see the Start Mission screen
    And User click on the Mission start ok button

  @dontRun
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

  @dontRun
  Scenario: Going back to the FST Application
    And User click on the FST icon from the Taskbar
      | Tag Name         | path             |
      | FST_TASKBAR_icon | FST_TASKBAR_icon |

  ################ Need to Run A_ConnectVBS_FiresFST.feature before fun this test scenario ###################
  @dontRun
  Scenario: Validating SEAD engagement times panel
    Given user should be in the Instructor control panel to Automate the "6.2.1" Testcase
      | Tag Name | path | Value              | Action | OffSetValue |  |
      | ICP      | icp  | 24S VJ 13277 88461 | sclick |         0,0 |  |
    And User should click on the clear button to "clear all the fields" clear all the fields
      | Tag Name | path  | Value | Action | OffSetValue |
      | clear    | clear |       | sclick |         0,0 |
    And User should click on the clear button to "clear all the fields" clear all the fields
      | Tag Name | path  | Value              | Action | OffSetValue |
      | clear    | clear | 24S VJ 13277 88461 | sclick |         0,0 |
    And User should be fill the data on the "Battery, FO, Mission type and Gun to fire" on the panel
      | Tag Name    | path        | Value              | Action | OffSetValue |
      | Battery     | Battery     | 24S VJ 13277 88461 | sclick |        40,0 |
      |          20 |          20 | 24S VJ 13277 88461 | sclick |         0,0 |
      | FO          | FO          | 24S VJ 13277 88461 | sclick |         0,0 |
      | R2          | R2          |                    | sclick |         0,0 |
      | MissionType | MissionType | 24S VJ 13277 88461 | sclick |         0,0 |
      | SEAD        | SEAD        | 24S VJ 13277 88461 | sclick |         0,0 |
      | Guns1       | Guns1       | 24S VJ 13277 88461 | sclick |         0,0 |
      | Guns3       | Guns3       | 24S VJ 13277 88461 | sclick |         0,0 |
    And User should enter the "Grid location, Altitude and Direction" on the target location section
      | Tag Name    | path        | Value    | Action  | OffSetValue | Similarity Check |
      | Suppression | Suppression | 10888554 | textbox |       100,0 |             0.90 |
      | Mark        | Mark        | 10868553 | textbox |       100,0 |             0.90 |
    And User should fill the Target description "section size, Type and Status"
      | Tag Name          | path              | Value              | Action        | OffSetValue | Similarity Check |
      | Size              | Size              | 24S VJ 13277 88461 | sclick        |         0,0 |             0.95 |
      | Squad             | Squad             | 24S VJ 13277 88461 | sclick        |         0,0 |             0.90 |
      | Modifier          | Modifier          |                    | sclick        |         0,0 |             0.90 |
      | Reinforced        | Reinforced        |                    | sclick        |         0,0 |             0.90 |
      | Type              | Type              | 24S VJ 13277 88461 | sclick        |         0,0 |             0.95 |
      | Infantry          | Infantry          | 24S VJ 13277 88461 | sclick        |         0,0 |             0.90 |
      | Modifier2         | Modifier2         | 24S VJ 13277 88461 | sclick        |         0,0 |             0.90 |
      | SmallArms         | SmallArms         | 24S VJ 13277 88461 | sclick        |         0,0 |             0.90 |
      | Status            | Status            |                 90 | click_similar |        40,0 |             0.80 |
      | InOpen            | InOpen            | 24S VJ 13277 88461 | sclick        |         0,0 |             0.90 |
      | TargetDescription | TargetDescription |                 90 | sclick        |         0,0 |             0.90 |
      | pagedown          | wp                | 24S VJ 13277 88461 | pagedown      |         0,0 |             0.95 |
    And User should Ammo/Fuse combination section "Ammo/Fuse and Tick on number per rounds and specofied rounds"
      | Tag Name         | path             | Value              | Action   | OffSetValue | Similarity Check |
      | Supperssion_Ammo | Supperssion_Ammo | 24S VJ 13277 88461 | sclick   |       100,0 |             0.95 |
      | HE               | HE               | 24S VJ 13277 88461 | sclick   |         0,0 |             0.90 |
      | wp               | wp               | 24S VJ 13277 88461 | sclick   |         0,0 |             0.95 |
      | pagedown         | wp               | 24S VJ 13277 88461 | pagedown |         0,0 |             0.95 |
    And User select a "Method of Fire and control" Method of Fire and control
      | Tag Name      | path          | Value | Action  | OffSetValue | Similarity Check |
      | Continuous    | Continuous    |       | sclick  |         0,0 |             0.90 |
      | NonStandard   | NonStandard   |       | sclick  |         0,0 |             0.90 |
      | Supperssion   | Supperssion   |       | sclick  |       -50,0 |             0.90 |
      | Add           | Add           |       | sclick  |         0,0 |             0.90 |
      | SEAD_ok       | SEAD_ok       |       | sclick  |         0,0 |             0.90 |
      | re_edit       | re_edit       |       | sclick  |         0,0 |             0.90 |
      | Supperssion_1 | Supperssion_1 |       | sclick  |       -50,0 |             0.90 |
      | EndTime       | EndTime       |     2 | textbox |        60,0 |             0.90 |
      | MarkStart     | MarkStart     |     1 | textbox |       120,0 |             0.90 |
      | SEAD_ok       | SEAD_ok       |       | sclick  |         0,0 |             0.90 |
      | proceed       | proceed       |       | sclick  |         0,0 |             0.90 |
      | CASTOT        | CASTOT        |    10 | textbox |         0,0 |             0.90 |
      | ArrowUp       | ArrowUp       |    10 | sclick  |         0,0 |             0.90 |
    #| Validate      | Validate      |    10 | sclick  |         0,0 |             0.90 |
    #| Execute       | Execute       |    10 | sclick  |         0,0 |             0.90 |
    #| ArrowDown     | ArrowDown     |    10 | sclick  |         0,0 |             0.90 |
    And User should click on the " valid and execute button" valid and execute buttons
      | Tag Name | path      | Value | Action | OffSetValue | Similarity Check |
      | Validate | Validate1 |       | sclick |         0,0 |             0.80 |
      | Execute  | Execute1  |       | sclick |         0,0 |             0.80 |
    And User should click on the "End of mission" to end the mission
      | Tag Name     | path         | Value | Action | OffSetValue | Similarity Check |
      #| ArrowUp      | ArrowUp      |       | sclick |         0,0 |             0.90 |
      | EndOfMission | EndOfMission |       | sclick |         0,0 |             0.90 |
    And User should click on the clear button to "clear all the fields" clear all the fields
      | Tag Name | path  | Value | Action | OffSetValue |
      | clear    | clear |       | sclick |         0,0 |
