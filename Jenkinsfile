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
                // Example: mvn clean install
                sh 'mvn clean install'
            }
        }

        stage('Unit Tests') {
            steps {
                // Run unit tests using Maven
                // Example: mvn test
                sh 'mvn test'
            }
        }

        // Add more stages for deployment, reporting, etc.
    }
}
