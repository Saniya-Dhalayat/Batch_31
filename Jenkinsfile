pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                echo 'Building the application...'
                // Example: sh 'mvn compile' or go build or npm build
            }
        }

        stage('Test') {
            steps {
                echo 'Running tests...'
                // Example: sh 'mvn test' or go test ./... or pytest
            }
        }

        stage('Package') {
            steps {
                echo 'Packaging the application...'
                // Example: sh 'mvn package' or zip binary
            }
        }
    }
}
