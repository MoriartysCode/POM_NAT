pipeline{
    agent any
    stages{
        
        stage("Build"){
            
            steps{
                echo("Build the project")
            }
            
        }
        
        stage("Deploy to Dev"){
            
            steps{
                echo("Deploying to DEV")
            }
            
        }
        stage("Deploy to QA"){
            
            steps{
                echo("Deploying to QA")
            }
            
        }
        
        stage("Run Regression Automated Test Scripts in QA"){
            
            steps{
                echo("Run Regression Automated Test Scripts in QA")
            }
            
        }
        
        stage("Deploy to Stage"){
            
            steps{
                echo("Deploying on Stage")
            }
            
        }
        
        stage("Run Sanity Automated Test Scripts on Stage"){
            
            steps{
                echo("Run Sanity Automated Test Scripts on Stage")
            }
            
        }
        
        stage("Deploy to Production"){
            
            steps{
                echo("Deploying on Production")
            }
            
        }
           
  
    }
}