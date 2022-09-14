pipeline{

	agent any

	tools{
        maven 'Maven'
        jdk 'jdk 11'
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