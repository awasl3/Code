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

    stage("Quality Gate") {
      steps {
        timeout(time: 20, unit: 'MINUTES') {
          // Parameter indicates whether to set pipeline to UNSTABLE if Quality Gate fails
          // true = set pipeline to UNSTABLE, false = don't
          waitForQualityGate abortPipeline: true
        }
      }
    }
  }
}