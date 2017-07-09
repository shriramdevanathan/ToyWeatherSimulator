package com.commbank.weathersim.algo;

import com.commbank.weathersim.algo.service.Algorithm;
import com.commbank.weathersim.algo.service.impl.ApacheAlgoImpl;
import com.commbank.weathersim.algo.service.impl.JamaAlgorithmImpl;

public class AlgoFactory {
	public static Algorithm getAlgoFactory(String algoChoice){
		switch(algoChoice.toLowerCase()){
			case "" : return new JamaAlgorithmImpl();
			case "jama" : return new JamaAlgorithmImpl();
			case "apache" : return new ApacheAlgoImpl();
			default : return null;
		}
	}
}
