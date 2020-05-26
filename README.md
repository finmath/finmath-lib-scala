finmath-lib scala
==========

****************************************

**Convenient method aliases and implicit classes for finmath lib.**

****************************************

## Introduction

A main objective of this project is to build a bridge from
a [Scala](https://scala-lang.org) project
to [finmath-lib](https://finmath.net/finmath-lib/)
to allow for cleaner, more concise Scala code when using finmath-lib.

For example, since Scala allows for method names being symbols like ``+`` or ``-`` instead of
``add`` and ``sub``, by importing

	net.finmath.scala.stochastic.RandomVariableImplicits._

you may write Scala code like

		val underlying = initialValue * exp(drift + diffusion)

instead of

		var underlying = diffusion.add(drift).exp().mult(initialValue);

where ``diffusion`` is an object of type `net.finmath.stochastic.RandomVariable`.



Distribution
-------------------------------------

finmath-lib-scala-extensions is distributed through the central Maven repository. It's coordinates are:

    <groupId>net.finmath</groupId>
    <artifactId>finmath-lib-scala-extensions</artifactId>


References
-------

* [finmath lib Project documentation](http://finmath.net/finmath-lib/)
provides the documentation of the library api.
* [finmath lib API documentation](http://finmath.net/finmath-lib/apidocs/)
provides the documentation of the library api.
* [finmath.net special topics](http://www.finmath.net/topics)
cover some selected topics with demo spreadsheets and uml diagrams.
Some topics come with additional documentations (technical papers).


License
-------

The code of "finmath lib", "finmath experiments" and "finmath lib cuda extensions" and "finmath lib plot extensions" and "finmath lib scala extensions" (packages
`net.finmath.*`) are distributed under the [Apache License version
2.0](http://www.apache.org/licenses/LICENSE-2.0.html), unless otherwise explicitly stated.
