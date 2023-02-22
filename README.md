# Automation1
FirstAutomationProgect
INSTRUCTIONS:

In this assignment you will be automating a test case for signing up and logging into a web application.
Create a new maven project in IntelliJ.
Implement the steps below in @Test annotated method.
Once finished, push the project to your own repository and share the repository link in a given repoLink.txt file and submit. (You should not write any code here in Replit)


STEPS:
1. Navigate to http://duotify.us-east-2.elasticbeanstalk.com/register.php
2. Verify the the title is "Welcome to Duotify!"
3. Click on Signup here
4. Fill out the form with the required info using Faker class 
5. Click on Sign up
6. Once logged in to the application, verify that the URL is:
http://duotify.us-east-2.elasticbeanstalk.com/browse.php?
7. In the left navigation bar, verify that your first and last name is the same the first and last name that you used when signing up. (use getText() method to extract the text of the element)
8. Click on the first and last name on the left navigation bar and verify the first and last name on the main window is correct and then click logout.
9. Verify that you are logged out by verifying the URL is:
http://duotify.us-east-2.elasticbeanstalk.com/register.php
10. Login using the same username and password when you signed up.
11. Verify successful login by verifying that the home page contains the text "You Might Also Like".
12. Log out once again and verify that you are logged out.

