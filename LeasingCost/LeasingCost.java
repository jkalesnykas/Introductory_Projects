public class LeasingCost {

     /* This method creates an array of Vehicles objects from the given file name */

	public static Vehicle[] createAllVehicles(String filename) {

        StdIn.setFile(filename);

        int numberOfCars = Integer.parseInt(StdIn.readLine());

        Vehicle[] vehicles = new Vehicle[numberOfCars];

        for (int i = 0; i < numberOfCars; i++) {

        	String line = StdIn.readLine();

            vehicles[i] = createVehicle(line);

        }

        return vehicles;

    }

    /* This method calculates the CO2 emission given several parameters */

	public static double computeCO2(double numberOfMonth, double usage, double mileageAllowance, double co2PerUnit ){

		double miles = numberOfMonth * mileageAllowance;

		return miles/usage*co2PerUnit;


    }

    /* This method calculates the cost the fuel during the lease given several parameters */

	public static double computeFuelCost(double numberOfMonth, double usage, double mileageAllowance, double fuelPrice){

    	double miles = numberOfMonth * mileageAllowance;

        double cost = miles/usage * fuelPrice;

    	return cost;

    }

    /* This method calculates the cost of lease */

	public static double computeLeaseCost(double dueAtSigning, int numberOfMonths, double monthlyCost){

        return dueAtSigning + numberOfMonths * monthlyCost;

    }

    /* This method creates and returns an Vehicle object with name, Lease, and Fuel properly populated based on the given string */

	public static Vehicle createVehicle(String description){

        // Name set-up

        String name= (description.substring(description.indexOf("name:") + 5)).
                        substring(0, (description.substring(description.indexOf("name:") + 5)).indexOf(";"));

        // Fuel set-up
        
        String temp4 = (description.substring(description.indexOf("type:") + 5)).
                        substring(0, (description.substring(description.indexOf("type:") + 5)).indexOf(";"));

        String temp6 = (description.substring(description.indexOf("mile/unit:") + 10)).
                        substring(0, (description.substring(description.indexOf("mile/unit:") + 10)).indexOf(";"));

        Fuel fuel = new Fuel(Double.parseDouble(temp6));

        if (!(temp4.equals("gas"))) {

            String temp8 = (description.substring(description.indexOf("mile/unit:") + 10)).
                            substring(0, (description.substring(description.indexOf("mile/unit:") + 10)).indexOf(";"));

            String temp10 = (description.substring(description.indexOf("charger:") + 8)).
                            substring(0, (description.substring(description.indexOf("charger:") + 8)).indexOf(";"));

            fuel = new Fuel(Double.parseDouble(temp8), Double.parseDouble(temp10));

        }

        // Lease set-up
        
        String temp12 = (description.substring(description.indexOf("due:") + 4)).
                        substring(0, (description.substring(description.indexOf("due:") + 4)).indexOf(";"));

        String temp14 = (description.substring(description.indexOf("length:") + 7)).
                        substring(0, (description.substring(description.indexOf("length:") + 7)).indexOf(";"));

        String temp16 = (description.substring(description.indexOf("monthly:") + 8)).
                        substring(0, (description.substring(description.indexOf("monthly:") + 8)).indexOf(";"));

        String temp18 = (description.substring(description.indexOf("allowance:") + 10)).
                        substring(0, (description.substring(description.indexOf("allowance:") + 10)).indexOf(";"));

        Lease lease = new Lease(Double.parseDouble(temp12), Integer.parseInt(temp14), Double.parseDouble(temp16), Integer.parseInt(temp18));
        
        // Create a new object based on previous statements

	    return new Vehicle(name, fuel, lease);

	}

    /* The method calculates and assign CO2Emission, FuelCost, leastCost, of each vehicle. */

	public static void computeCO2EmissionsAndCost( Vehicle[] vehicles, double gasPrice, double electricityPrice ){
     
        // CO2 emissions for gas-type vehicles

        for (int i = 0; i < vehicles.length; i++) {

            if (vehicles[i].getFuel().getType() == Fuel.GAS) {

                vehicles[i].setCO2Emission(computeCO2(vehicles[i].getLease().getLeaseLength(), 

                                                      vehicles[i].getFuel().getUsage(), 

                                                      vehicles[i].getLease().getMileageAllowance(),

                                                      Fuel.CO2EMITTED_GASCOMBUSTION));

                vehicles[i].setFuelCost(computeFuelCost(vehicles[i].getLease().getLeaseLength(), 

                                                       vehicles[i].getFuel().getUsage(), 

                                                       vehicles[i].getLease().getMileageAllowance(),

                                                       gasPrice));

                double leaseCost = computeLeaseCost(vehicles[i].getLease().getDueAtSigning(), vehicles[i].getLease().getLeaseLength(), 

                                                    vehicles[i].getLease().getMonthlyCost());

                vehicles[i].setTotalCost(leaseCost + computeFuelCost(vehicles[i].getLease().getLeaseLength(), 

                                         vehicles[i].getFuel().getUsage(), 

                                         vehicles[i].getLease().getMileageAllowance(),

                                         gasPrice));

                
                
            }

            // CO2 emissions for electric vehicles

            else if (vehicles[i].getFuel().getType() == Fuel.ELECTRIC) {
                
                vehicles[i].setCO2Emission(computeCO2(vehicles[i].getLease().getLeaseLength(), 

                                                      vehicles[i].getFuel().getUsage(), 

                                                      vehicles[i].getLease().getMileageAllowance(),

                                                      Fuel.CO2EMITTED_ELECTRICITYCOMBUSTION));

                vehicles[i].setFuelCost(computeFuelCost(vehicles[i].getLease().getLeaseLength(), 

                                                        vehicles[i].getFuel().getUsage(), 

                                                        vehicles[i].getLease().getMileageAllowance(),
                                                        
                                                        electricityPrice));

                double leaseCost = computeLeaseCost(vehicles[i].getLease().getDueAtSigning(), vehicles[i].getLease().getLeaseLength(), 

                                                    vehicles[i].getLease().getMonthlyCost());

                vehicles[i].setTotalCost(leaseCost + (computeFuelCost(vehicles[i].getLease().getLeaseLength(), 

                                         vehicles[i].getFuel().getUsage(), 

                                         vehicles[i].getLease().getMileageAllowance(),

                                         electricityPrice)) + vehicles[i].getFuel().getCharger());

            }

        }
        
    }

    /*
       How to compile: javac LeasingCost.java

       How to run: java LeasingCost vehicles.txt (gas price) (electricity price) 
    */

    public static void main (String[] args) {
        
    String filename = args[0];

    double gasPrice = Double.parseDouble(args[1]);

		double electricityPrice = Double.parseDouble(args[2]);

		Vehicle[] vehicles = createAllVehicles(filename); 

		computeCO2EmissionsAndCost(vehicles, gasPrice, electricityPrice);

		for ( Vehicle v : vehicles ) 

            System.out.println(v.toString());
            
    }
}
