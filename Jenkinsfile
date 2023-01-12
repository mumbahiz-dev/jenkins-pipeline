pipeline {
    agent none
    stages {
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