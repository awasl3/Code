pipeline {
  agent any

  tools {
    jdk '11'
  }

  stages {
    stage('test') {
      steps {
        sh "./gradlew build"
      }
    }
  }
}