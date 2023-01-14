pipeline {
    agent none
    environment {
        AUTHOR = "Liqoo Mumbahiz Alchuluq"
        EMAIL = "liqoo.dev@gmail.com"
    }

    parameters {
        string(name: "NAME", defaultValue: 'GUEST', description: 'What is your name ?')
        text(name: "DESCRIPTION", defaultValue: '', description: 'Tell me about you')
        boolenParam(name: "DEPLOY", defaultValue: false, description: 'Need to deploy ?')
        choice(name: "SOCIAL_MEDIA", choices: ['Instagram', 'Facebook', 'Twitter'], description: 'Which social media')
        password(name: "SECRET", defaultValue: '', description: 'Encryt Key')
    }
    options {
        disableConcurrentBuilds()
        timeout(time: 10, unit: 'MINUTES')
    }

    stages {
        stage("Parameter"){
            agent {
                node {
                    label "linux && java11"
                }
            }
            steps {
                echo ("Hello ${params.NAME}")
                echo ("Your description is ${params.DESCRIPTION}")
                echo ("Your social media is ${params.SOCIAL_MEDIA}")
                echo ("Need to deploy L  ${params.DEPLOY}")
                echo ("Your secret is ${params.SECRET}")

            }
        }

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