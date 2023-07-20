pipeline {
  agent any

  tools {
    jdk '11'
    dockerTool 'docker'
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

    stage("Create and push docker image") {
      steps{
        script {
          dockerImage = docker.build("timcicd/gitops:${env.BUILD_ID}", "--build-arg JAR_FILE=build/libs/simple-app-0.0.1-SNAPSHOT.jar .")
          docker.withRegistry( 'https://registry.hub.docker.com', 'dockercredentials') {
            dockerImage.push()
          }
        }
        // Ohne Plugin
        //   withCredentials([usernamePassword(credentialsId: 'dockcred', usernameVariable: 'DOCKER_USERNAME', passwordVariable: 'DOCKER_PASSWORD')]) {
        //   sh "docker login -u ${DOCKER_USERNAME} -p ${DOCKER_PASSWORD}"
        //   sh "docker build -t timcicd/gitops:${env.BUILD_ID} --build-arg JAR_FILE=build/libs/simple-app-0.0.1-SNAPSHOT.jar ."
        //   sh "docker push timcicd/gitops:${env.BUILD_ID}"
        // }
      }
    }
  }
}