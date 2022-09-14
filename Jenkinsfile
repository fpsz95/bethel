pipeline{

	agent any

	tools{
        maven 'Maven'
	}

	environment{
        PATH = "C:\\Program Files\\Java\\jdk-11.0.16.1\\bin;C:\\Program Files\\apache-maven-3.6.3\\bin;C:\\Windows\\System32;C:\\Program Files\\Amazon\\AWSCLIV2\\";
    }

	stages {
		stage("build"){
			steps{
				echo 'build stage'
				echo "PATH is: ${env.PATH}"
				bat "mvn install"
			}
		}

		stage("test"){
			steps{
    			echo 'test stage'
			}
		}

		stage("deploy"){
			steps{
				echo 'deploy stage'
			}
		}

	}
}