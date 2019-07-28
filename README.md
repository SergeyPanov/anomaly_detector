# Usage  
* Get `schema guru`:  
	*  `wget http://dl.bintray.com/snowplow/snowplow-generic/schema_guru_0.6.2.zip`  
	*  `unzip schema_guru_0.6.2.zip`  
*  `./schema-guru-0.6.2 schema path/to/some.json > path/where/to/store/json/schema.json` generate JSON-schema from input JSON file  
* `mvn clean install -DskipTests` compile without tests. Compilation should be executed from the folder with `pom.xml`  
* `java -jar target/anomaly-detector-1.0-SNAPSHOT-jar-with-dependencies.jar -s path/where/to/store/json/schema.json`