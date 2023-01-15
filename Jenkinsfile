pipeline {
    agent none
    environment {
        AUTHOR = "Liqoo Mumbahiz Alchuluq"
        EMAIL = "liqoo.dev@gmail.com"
    }

    //triggers {
        //cron("*/5 * * * *")
        //pollSCM("*/5 * * * *")
        //upstream(upstreamProjects: 'job1,job2', threshold: hudson.model.Result.SUCCESS)
    //}

    parameters {
        string(name: "NAME", defaultValue: 'GUEST', description: 'What is your name ?')
        text(name: "DESCRIPTION", defaultValue: '', description: 'Tell me about you')
        booleanParam(name: "DEPLOY", defaultValue: false, description: 'Need to deploy ?')
        choice(name: "SOCIAL_MEDIA", choices: ['Instagram', 'Facebook', 'Twitter'], description: 'Which social media')
        password(name: "SECRET", defaultValue: '', description: 'Encryt Key')
    }
    options {
        disableConcurrentBuilds()
        timeout(time: 10, unit: 'MINUTES')
    }

    stages {
        stage("OS Setup"){
            matrix {
                axes {
                    axis {
                        name "OS"
                        values "linux", "windows", "mac"
                    }
                    axis {
                        name 'ARC'
                        values '32', '64'
                    }
                }

                excludes {
                    exclude {
                        axis {
                            name "OS"
                            values "mac"
                        }
                        axis {
                            name "ARC"
                            values "32"
                        }
                    }
                }

                stages{
                    stage("OS SETUP"){
                        agent {
                            node {
                                label "linux && java11"
                            }
                        }
                        steps {
                            echo ("Setup ${OS} ${ARC}")
                        }
                    }
                }
            }
         }
        stage("Preparation"){
            failFast true
            parallel {
                stage("Prepare Java") {
                    agent {
                        node {
                            label "linux && java11"
                        }
                    }
                    steps {
                        echo "Prepare Java"
                    }
                }
                stage("Prepare Maven") {
                    agent {
                        node {
                            label "linux && java11"
                        }
                    }
                    steps {
                        echo "Prepare Maven"
                    }
                }
            }

        }

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
            input {
                message "Can we deploy ?"
                ok "Yes, of course."
                submitter "super_admin"
                parameters{
                    choice(name: 'TARGET_ENV', choices: ['DEV', 'STAG', "PROD"], description: 'we will deploy to ?')
                }
            }
            agent {
                node {
                    label "linux && java11"
                }
            }
            steps {
                echo ("Hello Deploy")
            }
        }
        stage("Release"){
            when {
                expression {
                    return params.DEPLOY
                }
            }
            agent {
                node {
                    label "linux && java11"
                }
            }
            steps {
                withCredentials([usernamePassword(
                    credentialsId: "mumbahiz_rahasia"
                    usernameVariable: "USER"
                    passwordVariable: "PASSWORD"
                )]){
                    sh('echo "Release it with -u $USER -p $PASSWORD" > "rahasia.txt"')
                }
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