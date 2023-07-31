# Delta Capita - Shopping
Provides basic shopping cart pricing logic where prices can be tailored using different pricing strategies.

### Pricing Strategies
Pricing strategies are implemented using the `com.deltacapita.shopping.PriceStrategy` interface.  Out of the box, there are 3 pricing strategies defined:

| Name                   | Description                                                                                               |
|------------------------|-----------------------------------------------------------------------------------------------------------|
| `FLAT`                 | Performs a basic quantity x price calculation.                                                            |
| `BUY_ONE_GET_ONE_FREE` | Divides the quantity in two, then multiplies by price; any odd items are changed at full price.           |
| `THREE_FOR_TWO`        | Divides the quantity in three, then multiplies by double the price; any odd items are changed at full price. |

## Build
This project builds with [Maven](https://maven.apache.org/index.html), typically using the `mvn clean install` command.

### Artifacts
In addition to the usual code jar, the source is also included in a source jar, as are the tests, all of which will be pushed to the Maven repo when using the `install` or `deploy` 
commands when running [Maven](https://maven.apache.org/index.html).

### Tests
The tests are split into two categories - unit tests that act on a single class, which are bound to the 
[Maven](https://maven.apache.org/guides/introduction/introduction-to-the-lifecycle.html) `test` phase and integration tests that wire components together, which is bound to the
[Maven](https://maven.apache.org/guides/introduction/introduction-to-the-lifecycle.html) `integration-test` phase.

### Coverage
Also included is a [JaCoCo](https://www.jacoco.org/jacoco/trunk/doc/maven.html) coverage report, which can be found in the `target/site/jacoco` directory after a build.