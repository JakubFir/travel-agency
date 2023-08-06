pipeline {

    agent any

    stages {
        stage('Build') {
            steps {
                withGradle(){
                sh 'chmod +x gradlew'
                sh './gradlew build'
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