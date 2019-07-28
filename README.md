# Usage
1. Get `schema guru`:
    1.1 `wget http://dl.bintray.com/snowplow/snowplow-generic/schema_guru_0.6.2.zip`
    1.2 `unzip schema_guru_0.6.2.zip`
2. `./schema-guru-0.6.2 schema path/to/some.json > path/where/to/store/json/schema.json` generate JSON-schema from input JSON file
3. `mvn clean install -DskipTests` compile without tests. Compilation should be executed from the folder with `pom.xml`
4. `java -jar target/anomaly-detector-1.0-SNAPSHOT-jar-with-dependencies.jar -s path/where/to/store/json/schema.json`


