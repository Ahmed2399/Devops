pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                // Check out the source code from your repository
                checkout scm
            }
        }

        stage('Install Dependencies') {
            steps {
                // Use Node.js and npm installed on the Jenkins agent
                sh 'npm install'
            }
        }

        stage('Build Angular App') {
            steps {
                // Build the Angular app
                sh 'npm run build'
            }
        }
    }

    post {
        success {
            // Define post-build actions, if needed
            // For example, you can archive the build artifacts
            archiveArtifacts(allowEmptyArchive: true, artifacts: 'dist/**')
        }
    }
}
