Feature: 6.1.2 Fires and Effects using AMC as the Method of Fire and Control

  @dontRun
  Scenario: Fire a mission
    Given user should be in the Instructor control panel to Automate the "6.1" Testcase
      | Tag Name | path | Value              | Action | OffSetValue |
      | ICP      | icp  | 24S VJ 13277 88461 | sclick |         0,0 |
    And User should click on the clear button to "clear all the fields" clear all the fields
      | Tag Name | path  | Value              | Action | OffSetValue |
      | clear    | clear | 24S VJ 13277 88461 | sclick |         0,0 |
    And User should be fill the data on the "Battery, FO, Mission type and Gun to fire" on the panel
      | Tag Name      | path          | Value              | Action | OffSetValue |
      | Battery       | Battery       | 24S VJ 13277 88461 | sclick |        40,0 |
      |            20 |            20 | 24S VJ 13277 88461 | sclick |         0,0 |
      | FO            | FO            | 24S VJ 13277 88461 | sclick |         0,0 |
      | MissionType   | MissionType   | 24S VJ 13277 88461 | sclick |         0,0 |
      | FireforEffect | FireforEffect | 24S VJ 13277 88461 | sclick |         0,0 |
      | Guns2         | Guns2         | 24S VJ 13277 88461 | sclick |         0,0 |
      | Guns3         | Guns3         | 24S VJ 13277 88461 | sclick |         0,0 |
      | Guns4         | Guns4         | 24S VJ 13277 88461 | sclick |         0,0 |
    And User should Select "Danger close" from the target location grid types
      | Tag Name    | path        | Value              | Action | OffSetValue |
      | Grid        | Grid        | 24S VJ 13277 88461 | sclick |         0,0 |
      | DangerClose | DangerClose | 24S VJ 13277 88461 | sclick |         0,0 |
    And User should enter the "Grid location, Altitude and Direction" on the target location section
      | Tag Name       | path           | Value              | Action   | OffSetValue | Similarity Check |
      | GridLocation   | GridLocation   | 24S VJ 13277 88461 | textbox  |         0,0 |             0.90 |
      | Altitude       | Altitude       | 24S VJ 13277 88461 | sclick   |         0,0 |             0.90 |
      | Direction      | Direction      |                 90 | textbox  |        40,0 |             0.90 |
      | TargetLocation | TargetLocation |                 90 | sclick   |         0,0 |             0.90 |
      | MethodOfFire   | MethodOfFire   |                 90 | pagedown |         0,0 |             0.90 |
    And User should fill the Target description "section size, Type and Status"
      | Tag Name          | path              | Value              | Action        | OffSetValue | Similarity Check |
      | Size              | Size              | 24S VJ 13277 88461 | sclick        |         0,0 |             0.95 |
      | Squad             | Squad             | 24S VJ 13277 88461 | sclick        |         0,0 |             0.90 |
      | Type              | Type              | 24S VJ 13277 88461 | sclick        |         0,0 |             0.95 |
      | Infantry          | Infantry          | 24S VJ 13277 88461 | sclick        |         0,0 |             0.90 |
      | Status            | Status            |                 90 | click_similar |        40,0 |             0.80 |
      | InOpen            | InOpen            | 24S VJ 13277 88461 | sclick        |         0,0 |             0.90 |
      | TargetDescription | TargetDescription |                 90 | sclick        |         0,0 |             0.90 |
    #| ICP      | D:\\SeleniumTesting\\E25Loyal\\cucumber\\ScreenShots\\6.1\\MethodOfFire.png      |                 90 | pagedown      |         0,0 |             0.90 |
    And User should Ammo/Fuse combination section "Ammo/Fuse and Tick on number per rounds and specofied rounds"
      | Tag Name   | path       | Value              | Action | OffSetValue | Similarity Check |
      | AmmoFuse   | AmmoFuse   | 24S VJ 13277 88461 | sclick |        40,0 |             0.95 |
      | HE         | HE         | 24S VJ 13277 88461 | sclick |         0,0 |             0.90 |
      | NoPerRound | NoPerRound | 24S VJ 13277 88461 | sclick |       -50,0 |             0.95 |
    And User select the "Distribution types" Distribution types
      | Tag Name         | path             | Value              | Action | OffSetValue | Similarity Check |
      | ArrowDown        | ArrowDown        | 24S VJ 13277 88461 | sclick |         0,0 |             0.90 |
      | DistributionType | DistributionType | 24S VJ 13277 88461 | sclick |         0,0 |             0.90 |
      | Linear           | Linear           |                 90 | sclick |        40,0 |             0.95 |
    And User select a "Method of Fire and control" Method of Fire and control
      | Tag Name     | path         | Value              | Action | OffSetValue | Similarity Check |
      | MethodOfFire | MethodOfFire | 24S VJ 13277 88461 | sclick |         0,0 |             0.90 |
      | AtMyCommand  | AtMyCommand  | 24S VJ 13277 88461 | sclick |         0,0 |             0.90 |
    And User click on validate Execute button "clicking validate and Execute buttons"
      | Tag Name  | path      | Value              | Action | OffSetValue | Similarity Check |
      | ArrowUp   | ArrowUp   | 24S VJ 13277 88461 | sclick |         0,0 |             0.90 |
      | Validate  | Validate  | 24S VJ 13277 88461 | sclick |         0,0 |             0.90 |
      | Execute   | Execute   | 24S VJ 13277 88461 | sclick |         0,0 |             0.90 |
      | ArrowDown | ArrowDown | 24S VJ 13277 88461 | sclick |         0,0 |             0.90 |
    And User open Gun panel
      | Tag Name | path | Value | Action | OffSetValue | Similarity Check |
    #| ICP      | D:\\SeleniumTesting\\E25Loyal\\cucumber\\ScreenShots\\6.1\\MethodOfFire.png | 24S VJ 13277 88461 | sclick |         0,0 |             0.90 |
    And User Click on Fire button "Fire button" to fire the mission
      | Tag Name | path | Value              | Action | OffSetValue | Similarity Check |
      | Fire     | Fire | 24S VJ 13277 88461 | sclick |         0,0 |             0.90 |
    Then Analyse the CFF Projectile panel
      | Tag Name | path | Value | Action | OffSetValue | Similarity Check |
      #| ICP      | D:\\SeleniumTesting\\E25Loyal\\cucumber\\ScreenShots\\6.1\\MethodOfFire.png | 24S VJ 13277 88461 | sclick |         0,0 |             0.90 |
