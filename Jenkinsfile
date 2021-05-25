#!groovy​
pipeline {
    agent any

    stages {
        stage('Hello') {
            steps {
                echo 'Hello World'
            }
        }
        stage('attach to container') {
            steps {
                sh 'docker stop appContainer'
                sh 'docker rm appContainer'
                sh 'docker run -t -d --name appContainer -p 8081:8081 appimage /bin/bash'
            }
        }
        stage('build java') {
            steps {
                sh ' docker exec appContainer mvn -f ee_spring clean install -DskipTests '


            }
        }


    }
}
