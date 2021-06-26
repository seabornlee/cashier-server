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
                    args '-v $HOME/.m2:/root/.m2 -v /var/run/docker.sock:/var/run/docker.sock --group-add 992'
                }
            }
            steps {
                script {
                    try {
                        sh """
                            java -version
                            mvn clean package 
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
