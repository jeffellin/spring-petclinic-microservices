def apps = [
    'spring-petclinic-vets-service': [
        buildPipeline: 'ci/jenkins/pipelines/spring-boot-app.pipeline'
    ]
]


apps.each { name, appInfo ->


    pipelineJob(name) {
       description("Job to build '$name'. Generated by the Seed Job, please do not change !!!")
       environmentVariables(
            APP_NAME: name
       )
       definition {
            cps {
                script(readFileFromWorkspace(appInfo.buildPipeline))
                sandbox()
            }
        }        
        triggers {
          scm('* * * * *')    
        }
        properties{
            disableConcurrentBuilds()
        }
    }
}
       
