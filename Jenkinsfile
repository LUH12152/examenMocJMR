pipeline{
agent any

    tools{
        jdk 'jdk-21'
        maven 'Maven3.9.11'
    }

    environment{
        VERSION_BACK = "1.1.1"
    }

    stages{
        stage('Checkout proyecto'){
                    steps{
                        git branch: 'master',
                            url: 'https://github.com/LUH12152/examenMocJMR.git'
                       }
                    }
        stage("Build"){
            steps{
                bat "mvn clean compile"
            }
        }
        stage("Test"){
            steps{
                bat "mvn test"
            }
        }
       stage("Package"){
            steps{
                bat "mvn package"
            }
       }
    }
}