import static org.junit.Assert.*;

import java.io.IOException;
import java.util.stream.IntStream;

import javax.swing.JEditorPane;

import org.apache.commons.math3.stat.regression.OLSMultipleLinearRegression;
import org.junit.Assert;
import org.junit.Test;

import com.commbank.weathersim.algo.AlgoFactory;
import com.commbank.weathersim.algo.JAMAMultipleLinearRegression;
import com.commbank.weathersim.algo.service.Algorithm;
import com.commbank.weathersim.algo.service.impl.ApacheAlgoImpl;
import com.commbank.weathersim.algo.service.impl.JamaAlgorithmImpl;
import com.commbank.weathersim.constants.AppConstants;

public class AlgoTest {
	
	public static double[][] getX(){
		
		double[][] x = { {  1,  10,  20 },
                {  1,  20,  40 },
                {  1,  40,  15 },
                {  1,  80, 100 },
                {  1, 160,  23 },
                {  1, 200,  18 } };
		
		return x;

	}
	
	public static double[] getY(){
		double[] y = { 243, 483, 508, 1503, 1764, 2129 };
		return y;
	}
	
	public static double[] getYMismatch(){
		double[] y = { 243, 483, 508, 1503, 1764, 2129,3300 };
		return y;
	}
	@Test
	public void testDefaultAlgo() {
		Algorithm algo = AlgoFactory.getAlgoFactory("");
		Assert.assertTrue(algo instanceof JamaAlgorithmImpl);
	}
	
	@Test
	public void testAlgo1() {
		Algorithm algo = AlgoFactory.getAlgoFactory("jama");
		Assert.assertTrue(algo instanceof JamaAlgorithmImpl);
		
		algo = AlgoFactory.getAlgoFactory("asdf");
		Assert.assertTrue(algo == null);
		
	}
	
	@Test
	public void testAlgo2() {
		Algorithm algo = AlgoFactory.getAlgoFactory("apache");
		Assert.assertTrue(algo instanceof ApacheAlgoImpl);
	}
	
	
	@Test(expected = RuntimeException.class)
	public void JAMAtestAlgo1ResultsMismatch(){
		JAMAMultipleLinearRegression algorithm = new JAMAMultipleLinearRegression(getX(), getYMismatch());
	}
	
	@Test
	public void JAMAtestAlgo1Results(){
		JAMAMultipleLinearRegression algorithm = new JAMAMultipleLinearRegression(getX(), getY());
		IntStream.range(0, getX()[0].length)
		  .forEach(idx ->{
			  Assert.assertNotNull(Double.valueOf(algorithm.beta(idx)));
		  });
		Assert.assertNotNull(Double.valueOf(algorithm.R2()));
		
	}
	
	
	@Test(expected = RuntimeException.class)
	public void ApachetestAlgo1ResultsMismatch(){
		OLSMultipleLinearRegression algorithm = new OLSMultipleLinearRegression();
		algorithm.newSampleData(getYMismatch(),getX());
	}
	
	@Test
	public void ApachetestAlgo1Results(){
		OLSMultipleLinearRegression algorithm = new OLSMultipleLinearRegression();
		algorithm.newSampleData(getY(),getX());
		double[] result = algorithm.estimateRegressionParameters();
		Assert.assertTrue(result.length == getX()[0].length + 1);
	}
	
}
