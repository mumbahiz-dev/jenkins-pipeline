pipeline {
    agent {
        node {
            label "linux && java11"
        }
    }
    stages {
        stage("Hello"){
            steps {
                echo ("Hello Pipeline")
            }
        }
    }
    post {
        always {
            echo ("Always say hello !")
        }
        success {
            echo ("Yeay Success!")
        }
        failure {
            echo ("Oh no, failure!")
        }
        cleanup{
            echo ("Don't care success or error")
        }
    }
}