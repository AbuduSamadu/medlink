pipeline {
    environment {
        QODANA_TOKEN = credentials('qodana-token')
    }
    agent {
        docker {
            args '''
                -v "${WORKSPACE}":/data/project
                --entrypoint=""
                '''
            image 'jetbrains/qodana-jvm'
        }
    }
    stages {
        stage('Qodana') {
            when {
                branch 'main'
                branch 'feature/repo'
            }
            steps {
                sh '''qodana --baseline=qodana.sarif.json'''
            }
        }
    }
}