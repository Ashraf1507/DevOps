pipeline {
  agent any
  stages {
    stage('Checkout Code') {
      steps {
        git(url: 'https://github.com/Ashraf1507/DevOps', branch: 'main')
      }
    }

    stage('Test') {
      steps {
        sh 'ls -la'
      }
    }

    stage('ClasseTest') {
      steps {
        sh 'mvn test -Dtest=CourseTest'
      }
    }

  }
}