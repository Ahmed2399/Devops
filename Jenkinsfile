pipeline {
    agent any
    environment {
        // Define the SonarQube server configuration name
        SONARQUBE_SERVER = 'SonarQube'
    }

    stages {
        stage('Checkout') {
            steps {
                // Checkout your source code from your version control system
                checkout scm
            }
        }
        stage('Unit Tests') {
            steps {
                dir ('DevOps_Project-20231016T100739Z-001/DevOps_Project'){
                    sh 'mvn test'
                }
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
                // Set the PATH to include the directory where npm and node are installed
                def nodeBin = tool 'NodeJS' // Replace with the actual tool name
                def nodePath = "${nodeBin}/bin"
                env.PATH = "${nodePath}:${env.PATH}"
        
                // Navigate to the Angular project directory
                dir('DevOps_Project_Front-20231016T100741Z-001/DevOps_Project_Front') {
                    // Install project dependencies
                    sh 'npm install'
        
                    // Build the Angular application
                    sh 'npm run build'
                }
              }
            }
        }

                stage('Deploy to Nexus') {
                    steps {
                        dir('DevOps_Project-20231016T100739Z-001/DevOps_Project'){
                            sh 'mvn deploy'
                        }  
                                   
                    }
                }

        stage('SonarQube Analysis') {
            steps {
                    dir('DevOps_Project-20231016T100739Z-001/DevOps_Project') {
                      // Use Maven to build the application
                      sh 'mvn sonar:sonar -Dsonar.login=squ_ca01c13a30cd1f5079b299154c3d349eb238fa4d'
                         script {
                            // Set the SonarQube server configuration
                            def scannerHome = tool name: 'SonarScanner', type: 'hudson.plugins.sonar.SonarRunnerInstallation'
                            withSonarQubeEnv(SONARQUBE_SERVER) {
                                // Run the SonarScanner
                                sh "${scannerHome}/bin/sonar-scanner"
                            }
                        }
             
                    }
            }
        }
        stage('Grafana/prometheus') {
            steps {
                sh 'docker start aa06bbd2838f'
                sh 'docker start 832f8e6f3437'
            }
        }
        
    }
}
