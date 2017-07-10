Project Description:

Approach:(What I HAVE done in about 7 to 8 hours)
1. I have collected weather data for 10 different Australian cities(inside the resources folder) for the year 2016-2017 from the BOM website(http://www.bom.gov.au/climate/). Included the code to fetch it form BOM website.

2. Using this as a reference, I have converted it to a model accepted by JAMA library(http://introcs.cs.princeton.edu/java/97data/MultipleLinearRegression.java.html).

3. Additionally, I have also implemented the OLSMultipleRegression offered by Apache commons math3 library as I read that it gives better results.(It's about having fun isn't it :). Thought I'll play around with this as well)

4. We can play around with the algorithm choice by changing the algo-choice.txt. If we key in nothing, JAMA will be implemented from the AlgoFactory. If we key in Jama, JAMA will be implemented. And if we key in apache, then the commons library implementation will be implemented.

5. The approach is that we will be using pressure and humidity data as the x parameters(independent variables) to determine/forecast the temperature and pass it in to our JEMA implementation. The implementation will spit out the regression parameters and the constants that would be applied to forecast temperature. Similar implementation will be followed for Apache as well and the output produced by commons is also the a set of regression parameters and a constant. 

6. If historic values are available for pressure and humidity(previous year values), I am just taking it as it is and fitting it. 

7. If not, I am defaulting the year to 2016, finding out the average and standard deviation of the pressure and humidity values in the same month that needs to be forecasted, and then fitting a random value between average and average + standard deviation

	for example :- to fit pressure value in 2018-05, if pressure value mean in 2016-05 is 1000 and standard deviation is 20, the I am generating a random number between 1000 and 1020 to fit to pressure.
	Similarly, for humidity as well.

8. If in the worst case there are no values even in the year 2016, then I am just hardcoding random values.

9. Based on values obtained from humidity and pressure, I am forecasting the temperature value using the regression params returned by JAMA/apache algo.

10. For the longitude and latitude, I am just maintaining a map of city to location, which will fetch the same latitude/longitude/altitude for a given city.

11. I am forecasting for four days from the current date(including the current day). This is configurable in the AppConstants.

12. All basic test cases are written in src/test/java.

13. The flow starts from MainApp.java.

14. I also wrote a simple Statistics helper to help me with Variance and SD calculations.

15. Personally, I thought Apache Commons Math's implementation of multi linear regression is giving better results than JAMA library.(just compared it with actual data from forecast websites and commons seemed to be closer).


Further Scope(What I WOULD HAVE loved to do):
1. I could have considered location as one of the parameters to determine/forecast temperature. But my code is very configurable, so it can be done quite easily.
2. Could have considered 9am and 3 pm values from data obtained from BOM website, and took an average rather than just taking 9am values.
3. Could have found a better way to fit humidity and pressure although this works well.
4. Could have considered altitude above sea level as a parameter and seen how that works.
5. Could have written more test cases.

Instructions to run:

Softwares needed: Java 1.8+, maven 3.5

**This is a maven project, and hence all dependencies are maintained by maven. I have also used JDK1.8 completely.

1. Do a 'mvn clean install' from root folder.
2. To compile the project, run 'mvn compile' from the root folder(/path/to/ToyWeatherSimuilator-master).
3. To run the project from command line, type 'mvn exec:java' from the root folder
4. To run test cases from command line, type 'mvn test' from the root folder


Extra Instructions if needbe:
5. To run the project from eclipse, right click on project->Run as->Maven Test to run the test cases.


**Assumption is user already has java 1.8, and maven installed in the system(I am working on providing an executable jar with all dependencies contained).




Sample output will look like this:-(4 days from now, including the current day)

Darwin|-34.92,138.62,48|2017-07-10|RAIN|26.6|1014.6|77.0

Darwin|-34.92,138.62,48|2017-07-11|RAIN|26.79|1014.2|81.0

Darwin|-34.92,138.62,48|2017-07-12|SUNNY|26.74|1013.9|74.0

Darwin|-34.92,138.62,48|2017-07-13|RAIN|26.9|1014.0|84.0

Wollongong|-34.92,138.62,48|2017-07-10|RAIN|17.6|1025.2|84.0

Wollongong|-34.92,138.62,48|2017-07-11|SUNNY|19.21|1013.7|73.0

Wollongong|-34.92,138.62,48|2017-07-12|SUNNY|18.93|1008.0|45.0

Wollongong|-34.92,138.62,48|2017-07-13|SUNNY|17.47|1015.1|42.0

Brisbane|27.47,153.03,23|2017-07-10|SUNNY|19.06|1023.8|65.0

Brisbane|27.47,153.03,23|2017-07-11|RAIN|18.71|1022.2|87.0

Brisbane|27.47,153.03,23|2017-07-12|RAIN|20.19|1018.9|90.0

Brisbane|27.47,153.03,23|2017-07-13|SUNNY|21.33|1021.0|48.0

Geelong|-34.92,138.62,48|2017-07-10|RAIN|11.36|1019.6|95.0

Geelong|-34.92,138.62,48|2017-07-11|SUNNY|17.42|1000.5|71.0

Geelong|-34.92,138.62,48|2017-07-12|SUNNY|19.71|994.5|61.0

Geelong|-34.92,138.62,48|2017-07-13|RAIN|12.42|1014.8|92.0

Adelaide|-34.92,138.62,48|2017-07-10|SUNNY|14.08|1015.6|69.0

Adelaide|-34.92,138.62,48|2017-07-11|SUNNY|14.08|1015.6|69.0

Adelaide|-34.92,138.62,48|2017-07-12|SUNNY|14.08|1015.6|69.0

Adelaide|-34.92,138.62,48|2017-07-13|SUNNY|14.08|1015.6|69.0

Perth|-34.92,138.62,48|2017-07-10|RAIN|15.53|1014.1|85.0

Perth|-34.92,138.62,48|2017-07-11|RAIN|9.93|1022.6|99.0

Perth|-34.92,138.62,48|2017-07-12|RAIN|9.11|1030.5|86.0

Perth|-34.92,138.62,48|2017-07-13|RAIN|10.87|1030.3|76.0

Canberra|-34.92,138.62,48|2017-07-10|RAIN|7.76|1026.2|87.0

Canberra|-34.92,138.62,48|2017-07-11|RAIN|10.86|1014.3|88.0

Canberra|-34.92,138.62,48|2017-07-12|RAIN|12.65|1010.8|84.0

Canberra|-34.92,138.62,48|2017-07-13|RAIN|11.19|1016.8|83.0

Townsville|-34.92,138.62,48|2017-07-10|SUNNY|22.34|1019.9|74.0

Townsville|-34.92,138.62,48|2017-07-11|SUNNY|22.81|1019.3|72.0

Townsville|-34.92,138.62,48|2017-07-12|RAIN|23.12|1018.2|82.0

Townsville|-34.92,138.62,48|2017-07-13|RAIN|22.45|1019.6|76.0

Melbourne|-37.83,144.98,7|2017-07-10|RAIN|9.85|1020.1|97.0

Melbourne|-37.83,144.98,7|2017-07-11|SUNNY|17.32|1002.6|67.0

Melbourne|-37.83,144.98,7|2017-07-12|SUNNY|19.63|996.9|58.0

Melbourne|-37.83,144.98,7|2017-07-13|SUNNY|15.27|1015.6|67.0

Sydney|-33.86,151.21,39|2017-07-10|RAIN|14.91|1025.1|90.0

Sydney|-33.86,151.21,39|2017-07-11|SUNNY|18.78|1015.2|63.0

Sydney|-33.86,151.21,39|2017-07-12|SUNNY|20.85|1010.3|41.0

Sydney|-33.86,151.21,39|2017-07-13|SUNNY|18.45|1017.3|40.0

Hobart|-34.92,138.62,48|2017-07-10|RAIN|8.22|1022.7|97.0

Hobart|-34.92,138.62,48|2017-07-11|SUNNY|13.76|996.5|62.0

Hobart|-34.92,138.62,48|2017-07-12|SUNNY|13.24|988.1|72.0

Hobart|-34.92,138.62,48|2017-07-13|SUNNY|15.05|996.6|50.0
