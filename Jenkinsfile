pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            steps {
                // Build your Spring Boot application using Maven with custom logging properties
                sh 'mvn clean install -Djava.util.logging.config.file=src/test/resources/logging.properties'
            }
        }

        stage('Unit Tests') {
            steps {
                // Run unit tests using Maven with custom logging properties
                sh 'mvn test -Djava.util.logging.config.file=src/test/resources/logging.properties'
            }
        }

        // Add more stages for deployment, reporting, etc.
    }
}
