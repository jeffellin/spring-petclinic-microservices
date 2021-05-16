def env = [
    'stage': [
        buildPipeline: 'ci/jenkins/pipelines/tbs-update-env.groovy'
    ]
]

env.each { name, appInfo ->

    folder(name)
    pipelineJob(name+"/tbs-update") {
       description("Job to build '$name'. Generated by the Seed Job, please do not change !!!")
       environmentVariables(
            ENV_NAME:  name
       )
       definition {
            cps {
                script(readFileFromWorkspace(appInfo.buildPipeline))
                sandbox()
            }
        }        
        properties{
            disableConcurrentBuilds()
        }
    }
}
       
