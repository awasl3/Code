pipeline {
  agent any

  tools {
    jdk '11'
    dockerTool 'docker'
  }

  stages {
    stage('test') {
      steps {
        withSonarQubeEnv('sonarqube') {
          sh "./gradlew build"
          sh "./gradlew test jacocoTestReport"
          sh "./gradlew build sonarqube -Dsonar.projectKey=ciops"
        }  
      }
    }

    stage("Create docker image") {
      steps{
        withCredentials([usernamePassword(credentialsId: 'docker-credentials', usernameVariable: 'DOCKER_USERNAME', passwordVariable: 'DOCKER_PASSWORD')]) {
          sh "docker login -u ${DOCKER_USERNAME} -p ${DOCKER_PASSWORD}"
          sh "docker build -t timcicd/gitops:${env.BUILD_ID} --build-arg JAR_FILE=build/libs/simple-app-0.0.1-SNAPSHOT.jar ."
          sh "docker push timcicd/gitops:${env.BUILD_ID}"
        }
    //Auch möglich mit plugin
        //script {
          //dockerImage = docker.build("timcicd/gitops:${env.BUILD_ID}", "--build-arg JAR_FILE=build/libs/demo1-0.0.1-SNAPSHOT.jar .")
          //docker.withRegistry( 'https://registry.hub.docker.com', 'dockcred') {
          //dockerImage.push()
          //}
        //}
      }
    }

    stage("Deploy") {
      steps {
        withKubeConfig([credentialsId: 'kubeconfig']) {
          sh 'curl -LO "https://storage.googleapis.com/kubernetes-release/release/v1.26.6/bin/linux/amd64/kubectl"'  
          sh 'chmod u+x ./kubectl'  
          sh "./kubectl set image deployment/ciops ciops=timcicd/gitops:${env.BUILD_ID}"
        }
      }
    }
  }
}