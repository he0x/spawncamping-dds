## Data-Driven Spark (spawncamping-dds) [![Build Status](https://travis-ci.org/FRosner/spawncamping-dds.svg?branch=master)](https://travis-ci.org/FRosner/spawncamping-dds)
### Description

This library provides a comprehensible and simple interface for quick data exploration based on
[Apache Spark](https://spark.apache.org/) and [D3.js/SVG](http://d3js.org/). The target audience is
data scientists who miss functions like `summary()` and `plot()` from [R](http://www.r-project.org/)
when working on the cluster with the Spark REPL. It does not offer a fully flexible plotting mechanism like [ggplot2](http://ggplot2.org/) but focuses on giving you quick insights into your data.

### Usage

1. Add spawncamping-dds jar to Spark classpath

    ```sh
    ./bin/spark-shell --jars spawncamping-dds-<ddsVersion>_<scalaVersion>.jar
    ```
2. Import core functions

    ```scala
    import de.frosner.dds.core.DDS._
    ```

3. Start the web server + user interface

    ```scala
    start()
    ```

4. Explore your data

    ```scala
    // create dummy transaction data and push to the cluster
    case class Transaction(customer: String, day: String, amount: Double)
    val transactions = sc.parallelize(List(
      Transaction("Frank", "Monday", 50.2),
      Transaction("Frank", "Tuesday", 102.50),
      Transaction("Milos", "Monday", 73.24),
      Transaction("Rick", "Wednesday", 9.99)
    ))

    // look at a nice overview of transactions
    show(transactions)

    // view distribution of transaction amount
    histogram(transactions.map(_.amount), buckets = List(0,50,100,200))

    // compare summed amount of your customers
    groupAndPie(transactions.map(t => (t.customer, t.amount)))(_ + _)
    ```

5. Stop the server once you are done

    ```scala
    stop()
    ```

See the [User Guide](https://github.com/FRosner/spawncamping-dds/wiki/User-Guide) for a detailed explanation of the provided functionality.

### Get Data-Driven Spark

You can either grab the [latest release artifact](https://github.com/FRosner/spawncamping-dds/releases), use the most recent [SNAPSHOT](http://spawncamping-dds-snapshots.s3-website-us-east-1.amazonaws.com/) or build from source (`sbt build`). Data-Driven Spark is currently developed and built against Spark 1.2, but can be run against other compatible/minor releases. It can be cross built against Scala version 2.10 and 2.11, depending on which version was used to build your Spark.

### Contribution

Any contribution, e.g. in form of feature requests, comments, code reviews, pull requests are very welcome. Pull requests will be reviewed before they are merged and it makes sense to coordinate with one of the main committers before starting to work on something big.

Please follow the general code style convention of Scala. It is advised to stick to the formatting / code style of the surrounding code when making changes to existing files. Reformatting should be done in separate commits.

All (most of the) code committed should be covered by some automated unit tests. All existing tests need to pass before committing changes.

### Authors

- [Frank Rosner](https://github.com/FRosner) (Creator)
- [Milos Krstajic](https://github.com/milosk) (Contributor)
- [Rick Moritz](https://github.com/RPCMoritz) (Contributor)

### Licensing

This project is licensed under the Apache License Version 2.0. For details please see the file called LICENSE.

### Included Libraries

| Library       | License        |
| ------------  | -------------- |
| [spray](http://spray.io/) | Apache 2 |
| [scalaj-http](https://github.com/scalaj/scalaj-http) | Apache 2 |
| [D3.js](http://d3js.org/) | Custom |
| [C3.js](http://c3js.org/) | MIT |
| [Parallel Coordinates](https://github.com/syntagmatic/parallel-coordinates) | Custom |
| [jQuery](http://jquery.com/) | Custom (MITish) |
| [SlickGrid](https://github.com/mleibman/SlickGrid) | MIT |
| [Chroma.js](https://github.com/gka/chroma.js) | BSD |
| [Underscore.js](http://underscorejs.org/) | MIT |
