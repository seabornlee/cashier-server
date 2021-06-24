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
                    image 'gradle:6.9.0-jdk8'
                    args '-v $HOME/.gradle:/home/gradle/.gradle -v /var/run/docker.sock:/var/run/docker.sock --group-add 992'
                }
            }
            steps {
                script {
                    try {
                        sh """
                            java -version
                            gradle clean build
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
