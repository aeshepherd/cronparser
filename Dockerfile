FROM java:latest

ARG JAR_FILE

COPY target/${JAR_FILE} /cronparser/
COPY target/lib/* /cronparser/lib/
