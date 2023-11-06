pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build and Test') {
            steps {
                sh 'mvn clean test'  // Use 'gradle test' if you're using Gradle
            }
        }

        // Add more stages for deployment, reporting, etc.
    }
}
