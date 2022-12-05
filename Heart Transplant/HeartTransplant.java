public class HeartTransplant {

    // Patient array
    private Patient[] patients;

    // SurvivabilityByAge array
    private SurvivabilityByAge survivabilityByAge;

    // SurvivabilityByCause array
    private SurvivabilityByCause survivabilityByCause;

    // Default constructor
    public HeartTransplant() {
        patients = null;
        survivabilityByAge = null;
        survivabilityByCause = null;
    }

    // Returns patients
    public Patient[] getPatients() {
        return patients;
     } 

    // Returns survivabilityByAge
    public SurvivabilityByAge getSurvivabilityByAge() {
        return survivabilityByAge;
    }

    // Returns survivabilityByCause
    public SurvivabilityByCause getSurvivabilityByCause() {
        return survivabilityByCause;
    }

    /*  1) Initialize the instance variable patients array with numberOfLines length.
        2) Reads from the command line data file. Each line refers to one Patient, all values are integers. */
    public void readPatients (int numberOfLines) {
        
        patients = new Patient[numberOfLines];

        for (int i = 0; i < numberOfLines; i++) {

            int id = StdIn.readInt();
            int ethnicity = StdIn.readInt();
            int gender = StdIn.readInt();
            int age = StdIn.readInt();
            int cause = StdIn.readInt();
            int urgency = StdIn.readInt();
            int stateOfHealth = StdIn.readInt();

            patients[i] = new Patient(id, ethnicity, gender, age, cause, urgency, stateOfHealth);

        }
    }

    /*   1) Initialize the instance variable survivabilityByAge with a new survivabilityByAge object. 
         2) Reads from the command line file to populate the object. Each line refers to one survivability rate by age. */
    public void readSurvivabilityByAge (int numberOfLines) {
        
        survivabilityByAge = new SurvivabilityByAge();

        for (int i = 0; i < numberOfLines; i++) {

            int age = StdIn.readInt();
            int year = StdIn.readInt();
            double rate = StdIn.readDouble();
            survivabilityByAge.addData(age, year, rate);

        }

    }

    /*   1) Initialize the instance variable survivabilityByCause with a new survivabilityByCause object.
         2) Reads from the command line file to populate the object. Each line refers to one survivability rate by cause. */
    public void readSurvivabilityByCause (int numberOfLines) {
        
        survivabilityByCause = new SurvivabilityByCause();

        for (int i = 0; i < numberOfLines; i++) {

            int causes = StdIn.readInt();
            int years = StdIn.readInt();
            double rates = StdIn.readDouble();
            survivabilityByCause.addData(causes, years, rates);

        }
    }
    
    // Returns a Patient array containing the patients, from the patients array, that have age above the parameter age.
    public Patient[] getPatientsWithAgeAbove(int age) {

        int count = 0;
        for (int i = 0; i < patients.length; i++) {
            if (patients[i].getAge() >= age) {
                count++;
            } 
        }

        Patient certainAgePatients[] = new Patient[count];

        int count2 = 0;
        for (int i = 0; i < patients.length; i++) {
            if (patients[i].getAge() >= age) {
                certainAgePatients[count2] = patients[i];
                count2++;
            }
        }
        
        return certainAgePatients;
    }

    // Returns a Patient array containing the patients, from the patients array that have the heart condition cause equal to the parameter cause.
    public Patient[] getPatientsByHeartConditionCause(int cause) {

        int count = 0;
        for (int i = 0; i < patients.length; i++) {
            if (patients[i].getCause() == cause) {
                count++;
            } 
        }

        Patient certainCausePatients[] = new Patient[count];

        int count2 = 0;
        for (int i = 0; i < patients.length; i++) {
            if (patients[i].getCause() == cause) {
                certainCausePatients[count2] = patients[i];
                count2++;
            } 
        }

        return certainCausePatients;
    }

    // Returns a Patient array containing patients, from the patients array, that have the state of health equal to the parameter state.
    public Patient[] getPatientsByUrgency(int urgency) {

        int count = 0;
        for (int i = 0; i < patients.length; i++) {
            if (patients[i].getUrgency() == urgency) {
                count++;
            } 
        }

        Patient certainUrgencyPatients[] = new Patient[count];

        int count2 = 0;
        for (int i = 0; i < patients.length; i++) {
            if (patients[i].getUrgency() == urgency) {
                certainUrgencyPatients[count2] = patients[i];
                count2++;
            } 
        }

        return certainUrgencyPatients;
    }

    /* 1.  Assume there is a heart available for transplantation surgery. Also assume that the heart is of the same blood type as the patients on the patients array.
       2.  This method finds the Patient to be the recepient of this heart.
       3.  The method returns a Patient from the patients array with the highest potential for survivability after the transplant.
       4.  Assume the patient returned by this method will receive a heart, herefore the Patient will no longer need a heart.
       * (In my opinion, years with disorder no more than one in those who can donate, setting up my personal hierarchy of patients). */ 
    public Patient getPatientForTransplant() {

        int max1 = -1;
        int index1 = -1;
        double maxsRate = 0.0;

        for (int i = 0; i < patients.length; i++) {

            double sRate = ((survivabilityByAge.getRate(patients[i].getAge(), 1) + (survivabilityByCause.getRate(patients[i].getAge(), 1)))) / 2;
            
            if (max1 < patients[i].getUrgency() && patients[i].getNeedHeart() == true && maxsRate < sRate) {

                max1 = patients[i].getUrgency();
                maxsRate = sRate;
                index1 = i;
            }  
        }
        patients[index1].setNeedHeart(false);
        return patients[index1];  
    }	
}
