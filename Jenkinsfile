pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                withGradle(){
                sh './gradlew build -x test'
                sh 'docker compose up -d'
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





