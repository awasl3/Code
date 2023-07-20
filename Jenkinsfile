pipeline {
  agent any

  tools {
    jdk '11'
  }

  stages {
    stage('build and test') {
      steps {
        withSonarQubeEnv('sonarqube') {
          sh "./gradlew build"
          sh "./gradlew test jacocoTestReport"
          sh "./gradlew build sonarqube -Dsonar.projectKey=ciops"
        }  
      }
    }
  }
}