package net.finmath.scala.stochastic

import net.finmath.functions.AnalyticFormulas
import net.finmath.montecarlo.BrownianMotionFromRandomNumberGenerator
import net.finmath.randomnumbers.MersenneTwister
import net.finmath.time.TimeDiscretizationFromArray
import net.finmath.scala.stochastic.RandomVariableImplicits._
import org.junit.{Assert, Test}

@Test
class MonteCarloBlackScholesTest {

	/*
	 * Parameters for the Monte-Carlo method.
	 */
	private val seed = 3216: Int
	private val numberOfSamples = 2000000 // 2 * 10^6

	/*
	 * Parameters for the product: European option
	 */
	private val optionMaturity = 1.0
	private val strike = 105.0

	/*
	 * Parameters for the model: Black Scholes model
	 */
	private val initialValue = 100.0
	private val riskFreeRate = 0.05
	private val volatility = 0.20

	@Test
	def testValuation(): Unit = {

		val value = getOptionValueUsingRandomVariables

		val valueAnalytic = AnalyticFormulas.blackScholesOptionValue(initialValue, riskFreeRate, volatility, optionMaturity, strike)

		val error = value - valueAnalytic
		val tolerance = initialValue / Math.sqrt(numberOfSamples)

		System.out.println("value: " + value + "\t" + "error: " + error)

		Assert.assertEquals("valuation", valueAnalytic, value, tolerance)
	}

	def getOptionValueUsingRandomVariables: Double = {
		// Uniform random number generator
		val randomNumberGenerator = new MersenneTwister(seed)

		val timeDiscretization = new TimeDiscretizationFromArray(0.0, optionMaturity)

		// Brownian motion (with a singe time step)
		val brownianMotion = new BrownianMotionFromRandomNumberGenerator(
			timeDiscretization, 1, numberOfSamples, randomNumberGenerator)

		// Model
		val drift = riskFreeRate * optionMaturity - 0.5 * volatility * volatility * optionMaturity
		val diffusion = volatility * brownianMotion.getBrownianIncrement(0.0, 0)
		val underlying = initialValue * exp(drift + diffusion)

		// Product
		val payoff = max(underlying - strike, 0.0)

		// Valuation
		val value = expectation(payoff * Math.exp(-riskFreeRate * optionMaturity))

		value.doubleValue
	}

	def getOptionValueUsingRandomVariablesJava: Double = {
		// Uniform random number generator
		val mersenne = new MersenneTwister(seed)
		val bm = new BrownianMotionFromRandomNumberGenerator(new TimeDiscretizationFromArray(0.0, optionMaturity), 1, numberOfSamples.asInstanceOf[Int], mersenne)
		val drift = riskFreeRate * optionMaturity - 0.5 * volatility * volatility * optionMaturity
		val diffusion = bm.getBrownianIncrement(0.0, 0).mult(volatility)
		val underlying = diffusion.add(drift).exp.mult(initialValue)
		val payoff = underlying.sub(strike).floor(0.0)
		val value = payoff.mult(Math.exp(-riskFreeRate * optionMaturity)).getAverage
		System.out.println(value)
		value
	}
}
