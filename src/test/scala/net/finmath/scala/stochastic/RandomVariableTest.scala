package net.finmath.scala.stochastic

import net.finmath.montecarlo.RandomVariableFromDoubleArray
import net.finmath.scala.stochastic.RandomVariableImplicits._
import org.junit.{Assert, Test}

@Test
class RandomVariableTest {

	@Test
	def testExpectation(): Unit = {

		val tolerance = 1.0E-15
		val randomVariable = new RandomVariableFromDoubleArray(0.0, Array(-1.0,0.0,1.0) )
		val result = expectation(randomVariable)

		Assert.assertEquals("expectation", 0.0, result.doubleValue, tolerance)
	}

	@Test
	def testExpectation2(): Unit = {

		val tolerance = 1.0E-15
		val randomVariable = new RandomVariableFromDoubleArray(0.0, Array(-1.0,0.0,1.0,2.0) )
		val result = expectation(randomVariable)

		Assert.assertEquals("expectation", 0.5, result.doubleValue, tolerance)
	}

	@Test
	def testVariance(): Unit = {

		val tolerance = 1.0E-15
		val randomVariable = new RandomVariableFromDoubleArray(0.0, Array(-1.0,0.0,1.0) )
		val result = variance(randomVariable)

		Assert.assertEquals("variance", 2.0/3.0, result.doubleValue, tolerance)
	}

	@Test
	def testCovariance(): Unit = {

		val tolerance = 1.0E-15
		val randomVariable1 = new RandomVariableFromDoubleArray(0.0, Array(-1.0,0.0,1.0) )
		val randomVariable2 = new RandomVariableFromDoubleArray(0.0, Array(1.0,0.0,-1.0) )
		val result = covariance(randomVariable1, randomVariable2)

		Assert.assertEquals("covariance", -2.0/3.0, result.doubleValue, tolerance)
	}

	@Test
	def testCovariance2(): Unit = {

		val tolerance = 1.0E-15
		val randomVariable1 = new RandomVariableFromDoubleArray(0.0, Array(-1.0,0.0,1.0) )
		val randomVariable2 = new RandomVariableFromDoubleArray(0.0, Array(0.0,1.0,2.0) )
		val result = covariance(randomVariable1, randomVariable2)

		Assert.assertEquals("covariance", 2.0/3.0, result.doubleValue, tolerance)
	}
}
