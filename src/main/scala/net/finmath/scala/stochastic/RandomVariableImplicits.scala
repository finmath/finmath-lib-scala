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

  /** The average (expectation) of a RandomVariable
   *
   * Applies x -> x.average() to the argument and returns the result.
   *
   * @param value The argument value.
   * @return The logarithm of the argument.
   */
  def expectation(value: RandomVariable): RandomVariable = value.average()

  /*
   * Binary operators
   */
  def max(v: RandomVariable, value: Double): RandomVariable = v.floor(value)

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
