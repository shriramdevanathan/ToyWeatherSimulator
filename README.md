Project Description:

Approach:(What I HAVE done in about 7 to 8 hours)
1. I have collected weather data for 10 different Australian cities(inside the resources folder) for the year 2016-2017 from the BOM website(http://www.bom.gov.au/climate/). Included the code to fetch it form BOM website.

2. Using this as a reference, I have converted it to a model accepted by JAMA library(http://introcs.cs.princeton.edu/java/97data/MultipleLinearRegression.java.html).

3. Additionally, I have also implemented the OLSMultipleRegression offered by Apache commons math3 library.

4. We can play around with the algorithm choice by changing the algo-choice.txt. If we key in nothing, JAMA will be implemented from the AlgoFactory. If we key in Jama, JAMA will be implemented. And if we key in apache, then the commons library implementation will be implemented.

5. The approach is that we will be using pressure and humidity data as the x parameters(independent variables) to determine/forecast the temperature and pass it in to our JEMA implementation. The implementation will spit out the regression parameters and the constants that would be applied to forecast temperature. Similar implementation will be followed for Apache as well and the output produced by commons is also the a set of regression parameters and a constant. 

6. If historic values are available for pressure and humidity(previous year values), I am just taking it as it is and fitting it. 

7. If not, I am defaulting the year to 2016, finding out the average and standard deviation of the pressure and humidity values in the same month that needs to be forecasted, and then fitting a random value between average and average + standard deviation

	for example :- to fit pressure value in 2017-05, if pressure value mean in 2016-05 is 1000 and standard deviation is 20, the I am generating a random number between 1000 and 1020 to fit to pressure. 
	Similarly, humidity.

8. If in the worst case there are no values even in the year 2016, then I am just hardcoding random values.

9. Based on values obtained from humidity and pressure, I am forecasting the temperature value using the regression params returned by JAMA/apache algo.

10. For the longitude and latitude, I am just maintaining a map of city to location, which will fetch the same latitude/longitude/altitude for a given city.

11. I am forecasting for four days from the current date(including the current day). This is configurable in the AppConstants.

12. All basic test cases are documented.

13. The flow starts from MainApp.java.

14. I also wrote a simple Statistics helper to help me with Variance and SD calculations.


Further Scope(What I WOULD HAVE loved to do):
1. I could have considered location as one of the parameters to determine/forecast temperature. But my code is very configurable, so it can be done quite easily.
2. Could have considered 9am and 3 pm values from data obtained from BOM website, and took an average rather than just taking 9am values.
3. Could have found a better way to fit humidity and pressure.
4. Could have considered altitude above sea level as a parameter and seen how that works.


Instructions to run:
	Software needed: Java 1.8, maven 3.5
1. This is a maven project, and hence all dependencies are maintained by maven. I have also used JDK1.8 completely. So do a 'mvn clean install' from root folder.
2. To compile the project, run 'mvn compile' from the root folder.
3. To run the project from command line, type 'mvn exec:java' from the root folder
Extra Instructions if needbe:
4. To run the project from eclipse, right click on project->Run as->Maven Test to run the test cases.