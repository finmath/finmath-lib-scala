package net.finmath.scala.stochastic

import net.finmath.functions.AnalyticFormulas
import net.finmath.montecarlo.{BrownianMotionFromRandomNumberGenerator, RandomVariableFromDoubleArray}
import net.finmath.randomnumbers.MersenneTwister
import net.finmath.time.TimeDiscretizationFromArray
import net.finmath.scala.stochastic.RandomVariableImplicits._
import org.junit.{Assert, Test}

@Test
class MonteCarloBlackScholesTest {

	/*
	 * Parameters for the Monte-Carlo method.
	 */
	private val seed = 343: Int
	private val numberOfSamples = 20000000 // 2 * 10^7

	/*
	 * Parameters for the product: European option
	 */
	private val optionMaturity = 2.0
	private val strike = 112.0

	/*
	 * Parameters for the model: Black Scholes model
	 */
	private val initialValue = 100.0
	private val riskFreeRate = 0.05
	private val volatility = 0.30

	@Test
	def testValuation(): Unit = {

		val value = getOptionValueUsingRandomVariables

		val valueAnalytic = AnalyticFormulas.blackScholesOptionValue(initialValue, riskFreeRate, volatility, optionMaturity, strike)

		val error = value - valueAnalytic
		val tolerance = initialValue / Math.sqrt(numberOfSamples);

		System.out.println("value: " + value + "\t" + "error: " + error);

		Assert.assertEquals("valuation", valueAnalytic, value, tolerance)
	}

	def getOptionValueUsingRandomVariables: Double = {
		// Uniform random number generator
		val randomNumberGenerator = new MersenneTwister(seed)

		// Brownian motion (with a singe time step)
		val brownianMotion = new BrownianMotionFromRandomNumberGenerator(
			new TimeDiscretizationFromArray(0.0, optionMaturity),
			1, numberOfSamples, randomNumberGenerator)

		// Model
		val underlying = initialValue * exp(
			riskFreeRate * optionMaturity - 0.5 * volatility * volatility * optionMaturity
				+ volatility * brownianMotion.getBrownianIncrement(0.0, 0)
		)

		// Product
		val payoff = max(underlying - strike, 0.0)

		// Valuation
		val value = expectation(payoff * Math.exp(-riskFreeRate * optionMaturity)).doubleValue

		return value
	}
}
