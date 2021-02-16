<!--
Copyright (c) Dell Inc., or its subsidiaries. All Rights Reserved.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0
-->
# Pravega - Creating Your First Application

Learn how to create a Hello World Pravega app. This guide covers:
* Starting Pravega standalone
* Creating a Pravega Stream.
* Creating an Event Writer and write events into a Pravega Stream.
* Create an Event Reader and read events from the Pravega Stream.

 
# 1. Prerequisites
To complete this guide, you need:
* less than 15 minutes
* an IDE
* JDK 11+ installed with JAVA_HOME configured appropriately
* <details>
  <summary>Gradle 6.5.1+</summary>
  Installation : https://gradle.org/install/
  
  !! Verify Gradle is using the Java you expect. You can verify which JDK Gradle uses by running gradle --version.!!
</details>

# 2. Architecture
In this guide, we create a straightforward application that creates a stream on Pravega and writes an event into the Stream and reads back from it.

# 3. Solution
We recommend that you follow the instructions from Bootstrapping project and onwards to create the application step by step.
However, you can go right to the completed example.
Download an [archive]( https://github.com/pravega/pravega-samples/archive/v0.8.0.zip) or clone the git repository:

<details>
<summary>Command to clone the pravega-samples repo</summary>
<p>

```java
git clone https://github.com/pravega/pravega-samples.git

```

</p>
</details>  


The solution is located in the pravega-client-examples [directory]( https://github.com/pravega/pravega-samples/tree/master/pravega-client-examples/src/main/java/io/pravega/example/gettingstarted ).

# 4. Starting Pravega Standalone.
In standalone mode, the Pravega server is accessible from clients through the `localhost` interface only. Controller REST APIs, however, are accessible from remote hosts/machines.
You can launch a standalone mode server using either of the following options:

1. From source code

<details>
<summary>Commands to start standalone from source code</summary>
<p>

Checkout the source code:
```java
$ git clone https://github.com/pravega/pravega.git
$ cd pravega
```
Build the Pravega standalone mode distribution:

```java
./gradlew startStandalone
```

</p>
</details>

2. From installation package

<details>
<summary>Commands to start standalone from installation package</summary>
<p>
Download the Pravega release from the [GitHub Releases](https://github.com/pravega/pravega/releases).

```java
$ tar xfvz pravega-<version>.tgz
```
Download and extract either tarball or zip files. Follow the instructions provided for the tar files (same can be applied for zip file) to launch all the components of Pravega on your local machine.

Run Pravega Standalone:

```java
$ pravega-<version>/bin/pravega-standalone
```

</p>
</details>  


# 5. Bootstrapping the Project.

