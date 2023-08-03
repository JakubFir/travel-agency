pipeline {
    agent any
    stages {
        stage("Initialize") {
            steps {
                script {
                    def dockerHome = tool 'docker'
                    env.PATH = "${dockerHome}/bin:${env.PATH}"
                }
            }
        }
        stage('Build') {
            steps {
                withGradle(){
                sh 'docker version'
                }
            }
        }
        stage('Test') {
            steps {
                echo 'testing'
            }
        }
        stage('Deploy') {
            steps {
                echo 'mvn deploy'
            }
        }
    }
}