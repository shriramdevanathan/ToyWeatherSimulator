package com.commbank.weathersim.datamodel;

public class RegressionEstimateAttributes {
	/**
	 * @author Shriram
	 * corresponds to whatever we consider as linear relationships.
	 * In my case, I am assuming humidity, rainfall, pressure to have a 
	 * linear relationship with tempeature.
	 */
	double reg1;
	double reg2;
	double reg3;
	double reg4;
	public double getReg1() {
		return reg1;
	}
	public void setReg1(double reg1) {
		this.reg1 = reg1;
	}
	public double getReg2() {
		return reg2;
	}
	public void setReg2(double reg2) {
		this.reg2 = reg2;
	}
	public double getReg3() {
		return reg3;
	}
	public void setReg3(double reg3) {
		this.reg3 = reg3;
	}
	public double getReg4() {
		return reg4;
	}
	public void setReg4(double reg4) {
		this.reg4 = reg4;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Param 1 -> " + reg1 + "Param 2 -> " + reg2 + "Param 3 -> " + reg3 + "Param 4 -> " + reg4 ;
	}
}
