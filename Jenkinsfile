pipeline {
    agent none
    environment {
        AUTHOR = "Liqoo Mumbahiz Alchuluq"
        EMAIL = "liqoo.dev@gmail.com"
    }

    stages {
        stage("Prepare"){
            environment{
                APP = credentials("mumbahiz_rahasia")
            }
            agent {
                node {
                    label "linux && java11"
                }
            }
            steps {
                echo ("Author : ${AUTHOR}")
                echo ("Email : ${EMAIL}")
                echo ("Start Job : ${env.JOB_NAME}")
                echo ("Start build : ${env.BUILD_NUMBER}")
                echo ("Branch Name : ${env.BRANCH_NAME}")
                echo ("APP User : ${APP_USR}")
                sh('echo "APP Password: ${APP_PSW}" > "rahasia.txt"')
            }
        }
        stage("Build"){
            agent {
                node {
                    label "linux && java11"
                }
            }
            steps {
                script {
                    for(int i = 0; i <= 10; i++){
                        echo("Script ${i}")
                    }
                }
                echo ("Start build")
                sh("./mvnw clean compile test-compile")
                echo ("Finish Build")
            }
        }
        stage("Test"){
            agent {
                node {
                    label "linux && java11"
                }
            }
            steps {
                script{
                    def data = [
                        "firstName" : "Liqoo",
                        "lastName" : "Mumbahiz"
                    ]
                    writeJSON(file: "data.json", json: data)
                }
                echo ("Start Test")
                sh("./mvnw test")
                echo ("Finish Test")
            }
        }
        stage("Deploy"){
            agent {
                node {
                    label "linux && java11"
                }
            }
            steps {
                echo ("Hello Deploy")
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