package net.finmath.scala.stochastic

import net.finmath.stochastic.RandomVariable

/** Convenient method aliases for the interface <code>net.finmath.stochastic.RandomVariable</code>.
 *
 */
object RandomVariableImplicits {

  /*
   * Unary operators
   */

  /** Exponential of a RandomVariable
   *
   * @param v The argument value.
   * @return The exponential of the argument.
   */
  def exp(v: RandomVariable): RandomVariable = v.exp()

  /** Logarithm (base e) of a RandomVariable */
  def log(v: RandomVariable): RandomVariable = v.log()

  def expectation(v: RandomVariable): RandomVariable = v.average()

  /*
   * Binary operators
   */
  def max(v: RandomVariable, value: Double): RandomVariable = v.floor(value);

  def min(v: RandomVariable, value: Double): RandomVariable = v.cap(value);

  def max(v: RandomVariable, value: RandomVariable): RandomVariable = v.floor(value);

  def min(v: RandomVariable, value: RandomVariable): RandomVariable = v.cap(value);

  implicit class RandomVariableOps(val value: RandomVariable) extends AnyVal {
    def +(v: Double): RandomVariable = value.add(v)

    def -(v: Double): RandomVariable = value.sub(v)

    def *(v: Double): RandomVariable = value.mult(v)

    def /(v: Double): RandomVariable = value.mult(v)

    def +(v: RandomVariable): RandomVariable = value.add(v)

    def -(v: RandomVariable): RandomVariable = value.sub(v)

    def *(v: RandomVariable): RandomVariable = value.mult(v)

    def /(v: RandomVariable): RandomVariable = value.mult(v)
  }

  implicit class DoubleOps(val value: Double) extends AnyVal {
    def +(v: RandomVariable): RandomVariable = v.add(value)

    def -(v: RandomVariable): RandomVariable = v.sub(value)

    def *(v: RandomVariable): RandomVariable = v.mult(value)

    def /(v: RandomVariable): RandomVariable = v.mult(value)
  }

}
