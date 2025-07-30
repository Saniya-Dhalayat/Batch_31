def call(String configFile) {
    node {
    def conf = evaluate(readTrusted('config/prod.groovy'))

    stage("Clone Repo") {
        git branch: 'main', url: 'https://github.com/Saniya-Dhalayat/Batch_31.git'
    }

    if (conf.KEEP_APPROVAL_STAGE.toBoolean()) {
        stage("Manual Approval") {
            input message: "Do you want to proceed with deployment to ${conf.ENVIRONMENT}?"
        }
    }

    stage("Run Ansible Playbook") {
        sh """
            cd ${conf.CODE_BASE_PATH}
            ansible-playbook Assignment5/site.yml -i Assignment5/inventory.ini --extra-vars "env=${conf.ENVIRONMENT}"
        """
    }

    stage("Send Slack Notification") {
        //notifySlack(conf.SLACK_CHANNEL_NAME, conf.ACTION_MESSAGE)
        withCredentials([string(credentialsId: 'slack-webhook', variable: 'SLACK_WEBHOOK_URL')]) {
                def message = "Deployment to *${conf.ENVIRONMENT}* completed: ${conf.ACTION_MESSAGE}"
                def payload = """{"text": "${message}"}"""
                sh """
                    curl -X POST -H 'Content-type: application/json' \
                    --data '${payload}' "${SLACK_WEBHOOK_URL}"
                """
    }
       stage('Artifacts') {
        archiveArtifacts artifacts: '\'**/*.jar\'', fingerprint: true, followSymlinks: false, onlyIfSuccessful: true
       }
}          
}
}
