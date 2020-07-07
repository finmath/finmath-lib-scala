package net.finmath.scala.stochastic

import net.finmath.stochastic.RandomVariable

/** Convenient method aliases for the interface <code>net.finmath.stochastic.RandomVariable</code>.
 *
 * @see net.finmath.stochastic.RandomVariable
 */
object RandomVariableImplicits {

  /*
   * Unary operators
   */

  /** Exponential of a RandomVariable
   *
   * Applies x -> x.exp() to the argument and returns the result.
   *
   * @see <code>net.finmath.stochastic.RandomVariable#exp()</code>
   *
   * @param value The argument value.
   * @return The exponential of the argument.
   */
  def exp(value: RandomVariable): RandomVariable = value.exp()

  /** Logarithm (base e) of a RandomVariable
   *
   * Applies x -> x.log() to the argument and returns the result.
   *
   * @param value The argument value.
   * @return The logarithm of the argument.
   */
  def log(value: RandomVariable): RandomVariable = value.log()

  /** The expectation (average) of a RandomVariable
   *
   * Applies x -> x.expectation() to the argument and returns the result.
   *
   * @param value The argument value.
   * @return The expectation of the argument.
   */
  def expectation(value: RandomVariable): RandomVariable = value.expectation()

  /** The variance of a RandomVariable
   *
   * Applies x -> x.variance() to the argument and returns the result.
   *
   * @param value The argument value.
   * @return The variance of the argument.
   */
  def variance(value: RandomVariable): RandomVariable = value.variance()

  /*
   * Binary operators
   */

  /** The covariance of two RandomVariable-s
   *
   * Applies x -> x.covariance(y) to the argument and returns the result.
   *
   * @param x The argument value x.
   * @param y The argument value y.
   * @return The covariance of the argument.
   */
  def covariance(x: RandomVariable, y: RandomVariable): RandomVariable = x.covariance(y)

  /** The maximum of a RandomVariable and a Double
   *
   * Applies x -> x.floor(y) to the argument and returns the result.
   *
   * @param left The argument value x.
   * @param right The argument value y.
   * @return The random variable consisting of the pathwise maximum of the arguments.
   */
  def max(left: RandomVariable, right: Double): RandomVariable = left.floor(right)

  def min(v: RandomVariable, value: Double): RandomVariable = v.cap(value)

  def max(v: RandomVariable, value: RandomVariable): RandomVariable = v.floor(value)

  def min(v: RandomVariable, value: RandomVariable): RandomVariable = v.cap(value)

  implicit class RandomVariableOps(val value: RandomVariable) extends AnyVal {
    def +(v: Double): RandomVariable = value.add(v)

    def -(v: Double): RandomVariable = value.sub(v)

    def *(v: Double): RandomVariable = value.mult(v)

    def /(v: Double): RandomVariable = value.div(v)

    def +(v: RandomVariable): RandomVariable = value.add(v)

    def -(v: RandomVariable): RandomVariable = value.sub(v)

    def *(v: RandomVariable): RandomVariable = value.mult(v)

    def /(v: RandomVariable): RandomVariable = value.div(v)
  }

  implicit class DoubleOps(val value: Double) extends AnyVal {
    def +(v: RandomVariable): RandomVariable = v.add(value)

    def -(v: RandomVariable): RandomVariable = v.sub(value)

    def *(v: RandomVariable): RandomVariable = v.mult(value)

    def /(v: RandomVariable): RandomVariable = v.mult(value)
  }

}
