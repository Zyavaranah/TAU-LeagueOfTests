Scenario:  Champions.java found

Given user is on Github profile page
When user clicks the TAU-LeagueOfTests link
Then the element league-of-tests is displayed
When user clicks the league-of-tests link
Then the element src is displayed
When user clicks the src link
Then the element main/java/org/leagueoftests is displayed
When user clicks the main/java/org/leagueoftests link
Then the element app is displayed
When user clicks the app link
Then the element Champions.java is displayed


Scenario:  Champions.java not found

Given user is on Github profile page
When user clicks the TAU-LeagueOfTests link
Then the element league-of-tests is displayed
When user clicks the league-of-tests link
Then the element src is displayed
When user clicks the src link
Then the element main/java/org/leagueoftests is displayed
When user clicks the main/java/org/leagueoftests link
Then the element Champions.java is not displayed