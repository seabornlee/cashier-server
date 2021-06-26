#!/usr/bin/env groovy

pipeline {
    agent any

    options {
        disableConcurrentBuilds()
        buildDiscarder(logRotator(numToKeepStr: "10"))
    }

    stages {
        stage('Build') {
            agent {
                docker {
                    image 'maven:3.8.1-jdk-8'
                    args '-v $HOME/.m2:/root/.m2'
                }
            }
            steps {
                script {
                    try {
                        sh """
                            java -version
                            mvn clean package 
                            mvn sonar:sonar
                        """
                    } catch (err) {
                        throw err
                    } finally {
                        junit allowEmptyResults: true, testResults: '**/build/**/TEST-*.xml'
                    }
                }
            }
        }
    }
}
