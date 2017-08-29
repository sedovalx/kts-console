### Simple example of embedding the kotlin script engine into a standalone console application.

#### Building
`./gradlew shadowJar`

You get the app jar plus shadowed dependencies in the `build/libs` folder. The `kotlin-script-util` 
jar can't be merged into the fat jar as the kotlin script engine factory looks for it in the 
classpath.  

#### Running

`java -Dkotlin.compiler.jar=kotlin-compiler-1.1.4-2.jar -jar hub-console-1.0-SNAPSHOT-all.jar`
