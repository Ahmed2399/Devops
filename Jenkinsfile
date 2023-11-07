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
                // Build your Spring Boot application using Maven
                sh 'mvn clean install --illegal-access=permit'
            }
        }

        stage('Unit Tests') {
            steps {
                // Run unit tests using Maven with the --illegal-access flag
                sh 'mvn test --illegal-access=permit'
            }
        }

        // Add more stages for deployment, reporting, etc.
    }
}
