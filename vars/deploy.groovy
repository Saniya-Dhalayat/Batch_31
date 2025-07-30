def call(String configFile) {
    def conf = loadConfig(config/prod.groovy)

    stage("Clone Repo") {
        git url: 'https://github.com/Saniya-Dhalayat/Batch_31.git'
    }

    if (conf.KEEP_APPROVAL_STAGE.toBoolean()) {
        stage("Manual Approval") {
            input message: "Do you want to proceed with deployment to ${conf.ENVIRONMENT}?"
        }
    }

    stage("Run Ansible Playbook") {
        sh """
            cd ${conf.CODE_BASE_PATH}
            ansible-playbook site.yml -i inventory --extra-vars "env=${conf.ENVIRONMENT}"
        """
    }

    stage("Send Slack Notification") {
        notifySlack(conf.SLACK_CHANNEL_NAME, conf.ACTION_MESSAGE)
    }
}
