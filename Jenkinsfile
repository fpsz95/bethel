pipeline{

	agent any

	tools{
        maven 'Maven'
        jdk 'jdk 11'
	}

	stages {
		stage("test"){
			steps{
    			echo 'test stage'
			}
		}
		
		stage("build"){
			steps{
				echo 'build stage'
				echo "PATH is: ${env.PATH}"
				sh "mvn install"
			}
		}
		
		stage("deploy"){
			steps{
				echo 'deploy stage'
				sh "cp /root/.jenkins/workspace/bethel_master/target/bethel-0.0.1.3-SNAPSHOT.war /apache-tomcat-9.0.65/webapps/"
			}
		}

	}
}
