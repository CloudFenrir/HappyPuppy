# HappyPuppy
This is an excersice made for Apex Systems.

It is an automation project of an adopting puppies web page.

Requirements:
-Java SDK 1.8
-Apache Maven

Steps:

-Clone or download the code.

-Open the project and import the maven dependencies needed.

-You will find the test cases inside the java/Tests/PuppiesFlows.java file, feel free to run all of them.

-*important* if you get this error message when running any test case: TestNG by default disables loading DTD from unsecured Urls 

---- Just add the following line to your run configurations/VM options: -Dtestng.dtd.http=true

-There is also a file called txtng.xml which contains the test suite of every test case used for the excercise, feel free to use it as well.


I developed the tests flows using Java, Selenium and TestNG. I followed the Page Object Model design pattern and Page Factory.

It was a really fun excercise to do, I really hope you like the way I did it. :)
