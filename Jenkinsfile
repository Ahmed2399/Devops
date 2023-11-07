pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                // Checkout your source code from your version control system
                checkout scm
            }
        }

        stage('Build') {
            steps {
                script {
                    // Set the MAVEN_HOME environment variable
                    def mvnHome = tool name: 'Maven', type: 'hudson.tasks.Maven$MavenInstallation'
                    def mvn = "${mvnHome}/bin/mvn"

                    // Clean and install your project
                    sh "${mvn} clean install"
                }
            }
        }

        stage('Unit Tests') {
            steps {
                script {
                    // Set the MAVEN_HOME environment variable
                    def mvnHome = tool name: 'Maven', type: 'hudson.tasks.Maven$MavenInstallation'
                    def mvn = "${mvnHome}/bin/mvn"

                    // Run unit tests
                    sh "${mvn} test"
                }
            }
        }
    }

    post {
        always {
            // Archive test results and other artifacts if needed
            junit '**/target/surefire-reports/TEST-*.xml'
        }
    }
}
