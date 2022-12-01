**Overview**

The purpose of this assignment is to build an analysis model to explore scenarios when buying a new car. We will consider two types of vehicles on the market:

  -> Gasoline powered vehicles that operate solely using gasoline.
  -> Fully electric vehicles that operate solely on battery-stored power and use no gasoline. The batteries are charged by plugging in to an external power source.
  
Some of the main considerations that factor into the decision when looking for a car might include cost, convenience, driving experience, and/or environmental reasons.

To simplify our model assume leased cars. When leasing a car we only consider:
  
  -> The amount in dollars due at signing
  -> The number months for the entire lease 
  -> The lease monthly cost
  -> The mileage allowance per month
  -> The cost of the charger if the vehicle is an electrical car

We also consider the price of gas for gasoline-powered vehicles and electricity for electricity-powered vehicles. Because both gasoline and electricity prices vary from time to time, we will obtain this information from the user when we execute the program.

Additionally, in order to compute CO2 emissions, we will consider the following facts: 

  -> According to the EPA, there are 8.887 kg of CO2 released with the combustion of each gallon of gasoline (gas-powered cars).
                                  -> 998.4 pounds of CO2 are emitted per mWh on average across the United States (electricity-powered cars).
                                  
**Prerequisites**

Provided:
Fuel.java, where objects built from Fuel class contain information of:
  -> Fuel type
  -> kg of CO2 emitted from 1 gallon of gas or 1 kWh of electricity
  -> Miles can the car drive per gallon of gas or kWh of electricity 
  -> Cost of the charger
  
Lease.java, where objects built from Lease class contain information of:
  -> The total amount of cash that is due at the time a car lease contract is signed
  -> The length of the lease in month
  -> The monthly cost for the lease
  -> Mileage allowance per month during the lease
  
Vehicle.java, where objects built from Vehicle class contain information of:
  -> Name of the vehicl,
  -> A fuel object and a lease object described above

Vehicles.txt, which contains the information of vehicles. 
  -> If the user wants to add additional vehicles, he must follow the pre-defined format.
  
*StdIn, StdOut, StdRandom, are all assumed to be installed.*

The user must execute the program with vehicles.txt, followed by current oil-gas price, and current electricity-gas price.

**Assignment Goal**

Conclude the algorithm with LeaseCars.java, the primary basis that brings the program and all classes together. Consists of the primary methods:

createVehicle: This method creates and returns a Vehicle object with name, Lease, and Fuel properly populated based on the given string the string given to this method is each line of description in vehicles.txt.

computeCO2EmissionsAndCost: This method calculates and assigns CO2Emission, FuelCost, LeaseCost, of each vehicle.
