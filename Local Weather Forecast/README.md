**Overview**

A weather generator produces a “synthetic” time series of weather data for a location based on the statistical characteristics of observed weather at that location. You can think of a weather generator as being a simulator of future weather based on observed past weather. A time series is a collection of observations generated sequentially through time. The special feature of a time series is that successive observations are usually expected to be dependent, in the case of forecasting a day’s weather depends on the previous day’s weather.      

The goal of this assignment will be to see how computation is used in predicting long-range climate patterns. 

Weather and climate rely on probability. Probability and statistics are an integral part of day-to-day weather forecasting, because models of real-world phenomena must take into account randomness. Randomness or uncertainty might imply a lack of predictability, but it does not necessarily imply a lack of knowledge or understanding.

Weather data depends on both the time of year and the location. This means that the probabilities used in the simulation need to be associated with both a location and a time of year. Typically, these models couple an atmospheric model with an ocean model, but more recent versions, the so-called Earth system models, incorporate more components, including land use, sea, and land ice, etc. The models can be used to predict future precipitation patterns and transition probabilities that are based on these forecasts rather than past data.

**Prerequisites**

The individual running this simulation must supply the program with three arguments when running it: the longitude, the latitude, and the month.

_StdIn, StdOut, StdRandom, are all assumed to be installed._

wetwet.txt and drywet.txt are locally dependent on the program and are filled to present the longitude and latitude of weather probabilities for certain months from the 1970-2000s.

**Assignment Goal**

Simplify predictions to just whether measurable precipitation will fall from the sky. If there is a measurable precipitation, we call it a "wet" day. Otherwise, we call it a "dry" day. Simplifications on our next day predictions are also based on the previous day's weather, the probability of the weather changing from dry/wet to wet of that location in that month (weather data text files), and finally a random number.

The following methods are programmed:

[populateLocationProbabilities:] which populates two arrays with  the weather data of a certain location.
[forecastGenerator:] which predicts the future precipitation pattern for one month.
[oneMonthForcast:] which uses previous methods to prepare the data and predict the weather for a month.
[numberOfWetDryDays:] which finds the number of wet or dry days in a given month’s forecast.
[lengthOfLongestWetDrySpell:] which finds the longest wet or dry spell in a given month’s forecast.
[bestWeekToTravel:] which finds the 7-day period with the most amount of dry days.

_More comments occur in the WeatherForecast.java_
