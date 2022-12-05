**Overview**

A heart transplant is a surgical procedure that replaces the person’s heart with a donor heart. A person may require a heart transplant for several reasons including congenital, arterial and muscle diseases or for unforeseen reasons such as accidents or viral infections.

The donor heart is matched to the recipient by blood type. Additional variables are also used to decide which recipient receives a heart if there are not enough hearts available for all recipients waiting for a transplant. The variables included in the decision may include the recipient state of health, cause of the heart condition and the urgency of the transplant.

The primary goal of this project is to write a HeartTransplant class that has the ability to select the patient, from a pool of patients, with the highest potential to survive the longest after the heart transplant surgery. Ability to use the HeartTransplantDriver to test the methods within the HeartTransplant class.

**Prerequisites**

Provided: HeartTransplantDriver.java: this is the program you should compile and run. It builds a HeartTransplant object and calls instance methods on it. No need to make changes to it, but you are welcome to change it if you want. It is for testing purposes only.

SurvivabilityByAge.java: Stores information about the survivability of patients, by age, after n years post heart transplant surgery.

SurvivabilityByCause.java: Stores information about the survivability of patients, by heart condition cause, after n years post heart transplant surgery.

Patient.java: Stores patient information. Contain constants representing patient codes (health condition code, gender code, urgency code, ethnicity code…)

Data.txt: Contains patient information, as well as survivability by age and cause rates. This file should be piped when executing HeartTransplantDriver. Person:
The first line of this section has an integer that refers to the number of persons in the file. The file has one person per line in the following format: *PersonID Ethinicity Gender Age Cause Urgency StateOfHealth*. Survivability by age: The first line of this section has an integer that refers to the number of survivability by age rates in the file. The file has one rate per line in the following format: *Age YearsPostTransplant Rate*. Ssurvivability by heart condition cause: The first line of this section has an integer that refers to the number of survivability by cause rates in the file. The file has one rate per line in the following format:
*Cause YearsPostTransplant Rate*. 

How to run the driver: 

Compile: *javac HeartTransplantDriver.java*
Execute: *java HeartTransplantDriver < data.txt* (may vary across terminals and OS')

HeartTransplant.java: The file that was written and completed in order to conclusively create a hierarchy of patients of importance to be ranked for heart surgery.

*StdIn, StdOut, StdRandom, are all assumed to be installed.*  

Assignment Goal

Conclude the algorithm with HeartTransplant.java, which is designated as an empty file. This file is to be filled up with a multitude of methods, such as getPatients(), getSurvivabilityByAge(), readPatients() ... getPatientForTransplant(), etc. Overall, once again as stated in the objective previously, the primary goal of this project is to write a HeartTransplant class that has the ability to select the patient, from a pool of patients, with the highest potential to survive the longest after the heart transplant surgery.

*Please note that although this class was created by Justas Kalesnykas (I, the author), all other classes were collaboratively created with: Ana Paul Centeno, as well as Haolin (Daniel) Jin.*

Furthermore information can be found in the designated files.
