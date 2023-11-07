pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                // Checkout your source code from your version control system
                checkout scm
            }
        }
        stage('Build Backend') {
            steps {
                // Change to the Spring Boot project directory
                dir('DevOps_Project-20231016T100739Z-001/DevOps_Project') {
                    // Use Maven to build the application
                    sh 'mvn clean package'
                }
            }
        }

      stage('Build Angular Frontend') {
            steps {
                script {
                    sh 'npm install -g @angular/cli'
    
            }
          }
      }
    }
}
