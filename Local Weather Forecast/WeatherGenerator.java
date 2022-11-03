public class WeatherGenerator {

    /* Definitive variables */
    
    static final int WET = 1; 
    static final int DRY = 2;
    static final int[] numberOfDaysInMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    /* This method works under the assumption that under the same directory as WeatherGenerator.java, 
    there exists drywet.txt and wetwet.txt that contains probabilities of the next day being wet 
    with today being a dry/wet day */
    
    public static void populateArrays(double[][] drywet, double[][] wetwet) {

    StdIn.setFile("drywet.txt");

	for(int i=0; i < drywet.length; i++){

            for(int j=0; j<14; j++){

                drywet[i][j] = StdIn.readDouble();

            }
        }

	StdIn.setFile("wetwet.txt");

	for(int i=0; i < drywet.length; i++){

            for(int j=0; j<14; j++){

                wetwet[i][j] = StdIn.readDouble();

            }
        }
    }
    
    /* This method uses drywet and wetwet arrays populated by populateArrays, and longitude and latitude
    of the target location to populate drywetProbability and wetwetProbability with the 
    probability of dry/wet day is followed by a wet day each month at that location.
    In other words, extracting the probabilities of the location */
    
    public static void populateLocationProbabilities( double[] drywetProbability, double[] wetwetProbability, 
                                     double longitude, double latitude, 
                                     double[][] drywet, double[][] wetwet){

        for (int row = 0; row < drywet.length; row++) {

            if (drywet[row][0] == longitude && drywet[row][1] == latitude) {

                for (int i = 0; i < drywetProbability.length; i++) {

                    drywetProbability[i] = drywet[row][i+2];

                }
            }
        }

        for (int row = 0; row < wetwet.length; row++) {

            if (wetwet[row][0] == longitude && wetwet[row][1] == latitude) {

                for (int i = 0; i < wetwetProbability.length; i++) {

                    wetwetProbability[i] = wetwet[row][i+2];

                }
            }
        }
    }

    /* Given the number of days in a month, and probabilities of weather changing at a certain location, 
    the method returns the forecast for the month */

     public static int[] forecastGenerator(double drywetProbability, double wetwetProbability, int numberOfDays) {

        int arr[] = new int[numberOfDays];

        double firstDay = StdRandom.uniform();

        if (firstDay >= 0 && firstDay < 0.50) {

            firstDay = WET;
        }
        else if (firstDay >= 0.50 && firstDay < 1) {

            firstDay = DRY;
        }

        arr[0] = (int)firstDay;

        for (int i = 0; i < numberOfDays - 1; i++) {

            double prob = StdRandom.uniform();

            if (arr[i] == WET) {

                if (prob <= wetwetProbability) {

                    arr[i+1] = WET;

                } else {

                    arr[i+1] = DRY;
                }
            }

            else {

                if (prob <= drywetProbability) {
                    
                    arr[i+1] = WET;

                } else {

                    arr[i+1] = DRY;
                }
            }
        }

        return arr;
        
    }

    /* This method takes the number of locations that is stored in wetwet.txt and drywet.txt (the number of
    lines in each file), and takes in the month number (January is index 0, February is index 1... ),  
    and the longitude and the latitude of the location we want to make the prediction on, to return a specific 
    one month forecast influenced by concise parameters */
        
    public static int[] oneMonthForecast(int numberOfLocations, int month, double longitude, double latitude){

        double drywet[][] = new double[numberOfLocations][14];

        double wetwet[][] = new double[numberOfLocations][14];

        populateArrays(drywet, wetwet);

        double drywetProbability[] = new double[12];

        double wetwetProbability[] = new double[12];

        populateLocationProbabilities(drywetProbability, wetwetProbability, longitude, latitude, drywet, wetwet);

        int numberOfDays = numberOfDaysInMonth[month];
        
        int oneMonthForecastArr[] = forecastGenerator(drywetProbability[month], wetwetProbability[month], numberOfDays);
        
        return oneMonthForecastArr;

    }

    /* Returns the number of WET or DRY days in a forecast */
    
    public static int numberOfWetDryDays (int[] forecast, int mode) {
        
        int count = 0;

        for (int i = 0; i < forecast.length; i++) {
            
            if (forecast[i] == mode) {

                count++;
            }

        }

        return count; 
    }

    /* Find the longest number of consecutive mode (WET or DRY) days in forecast. */

    public static int lengthOfLongestSpell (int[] forecast, int mode) {

        int count = 0;

        int largest = 0;

        for (int i = 0; i < forecast.length; i++) {

            if (forecast[i] == mode) {

                count++;
            }

            else {
                count = 0;
            }

            if (largest < count) {

                largest = count;
            }
        }

        return largest;
    }

    /* Given the forecast of a month at certain location, this method finds the index of the
    first day of a 7 day period with the least amount of rain. If multiple exist, returns
    the earliest.*/

    public static int bestWeekToTravel(int[] forecast){
        
        int dryCounter = 0;

        int maxDryCounter = 0;

        int index = 0;

        for (int i = 0; i < forecast.length - 6; i++) {

            for (int x = 0; x < 7; x++) {

                if (forecast[i+x] == DRY) {

                    dryCounter++;

                }

            }

            if (maxDryCounter < dryCounter) {

                maxDryCounter = dryCounter;
                index = i;

            }

            dryCounter = 0;

        }

        return index;
    }

    /* Tests the methods defined as needed, completely customizable */
    public static void main (String[] args) {

        /* File dimensions */

        int numberOfRows = 4100; 

        int numberOfColumns = 14; 

        /* File format: longitude, latitude, 12 months of transition probabilities */

        double longitude = Double.parseDouble(args[0]);

        double latitude = Double.parseDouble(args[1]);

        int month = Integer.parseInt(args[2]);

        /* Generate */

        int[] forecast = oneMonthForecast( numberOfRows,  month,  longitude,  latitude );

        int drySpell = lengthOfLongestSpell(forecast, DRY);

        int wetSpell = lengthOfLongestSpell(forecast, WET);

        int bestWeek = bestWeekToTravel(forecast);

        StdOut.println("There are: " + forecast.length + " days in the forecast for month " + month + ".");

        StdOut.println("There are: " + drySpell + " days of dry spell.");

        StdOut.println("There are: " + wetSpell + " days of wet spell.");

        StdOut.println("The best week to travel starts on day: " + bestWeek + ".");

        for ( int i = 0; i < forecast.length; i++ ) {

            /* Ternary operator */

            String weather = (forecast[i] == WET) ? "Wet" : "Dry";  

            StdOut.println("Day: " + (i) + " is forecasted to be " + weather);
        }
    }
}

