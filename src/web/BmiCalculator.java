package web;

public class BmiCalculator {
    private double dbHeight;
    private double dbWeight;
    private double bmi;

    

	public BmiCalculator(double dbHeight, double dbWeight) {
		super();
		this.dbHeight = dbHeight;
		this.dbWeight = dbWeight;
	}

	

	public double getDbHeight() {
		return dbHeight;
	}



	public void setDbHeight(double dbHeight) {
		this.dbHeight = dbHeight;
	}



	public double getDbWeight() {
		return dbWeight;
	}



	public void setDbWeight(double dbWeight) {
		this.dbWeight = dbWeight;
	}



	public double getBMI(){
        bmi = dbWeight / Math.pow((dbHeight / 100), 2);
        return bmi;
    }

//    public BmiType getBmiType(double bmi){
//        if (bmi < 18.5){
//            return BmiType.Underweight;
//        }else if (18.5 <= bmi && bmi <25){
//            return BmiType.NormalWeight;
//        }else if (25 <= bmi && bmi < 30){
//            return BmiType.Overweight;
//        }else if(bmi >= 30){
//            return BmiType.Obese;
//        }
//        return null;
//    }

//    @Override
//    public String toString() {
//        return "Height: " + strHeight + "\n" +
//                "Weight: " + strWeight + "\n" +
//                "BMI Value= " + getBMI() + "\n" ;
//                getBmiType(getBMI());
//    }
}
