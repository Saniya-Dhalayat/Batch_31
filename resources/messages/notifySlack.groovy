def call(String channel, String message) {
    slackSend channel: "#${channel}",
              message: message,
              color: 'good'
}
