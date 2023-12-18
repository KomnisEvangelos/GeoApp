# Release notes ()
## Feats
Create testfile

feat: added test file
feat: Improve data handling and registration process

-A message is displayed after the user is successfully logged off.
-Checks for null fields when displaykng user's data.
-Clears the user's data after successful registration.
-Removed unnecessary comments.

feat: Updating Profile View Logic.

-Removed code with dummy data.
-In the RegisterActivity, we're getting user input and using it to populate the RegularUser Instance.
-In the ProfileFragment, we're fetching the RegularUser Instance and using its data to update the UI.
-This correctly displays the user's data after they have registered.
-HOWEVER, if the user logs out, and signs back in, only the email is displayed.
-The reason we're losing user data after logging out and signing in again is because the RegularUser instance gets reset, but we're only setting the email and password when the user logs in, adn not the other fields.
 -Therefore, when the user logs out and signs in again, the RegularUser instance only has the email and password, but not the other fields.
 -This is a TEMPORARY SOLUTION. The full functionality of retrieving all user data from Firebase will be implemented in a future commit once Firebase is integrated into the project.

feat: Implement logout functionality and button.
- Added logout functionality to RegularUser class.
- Created a button in ProfileFragment to trigger logout.
- Updated the logout button click listener to redirect user to SignInActivity after logout.

feat: Initial layout and dummy code for basic profile view.


## Bugfixes
fix: user data to realtime database #59

fix: regex bug on register screen #58


---
