def toKubernetes(tagToDeploy, namespace, deploymentName) {
  sh("sed -i.bak 's#BUILD_TAG#${tagToDeploy}#' ./deploy/${namespace}/*.yml")


 container('kubectl') {
    sh("kubectl --namespace=production apply -f deploy/production/")
  }

  
}

def kubectl(namespace, command) {
  container('kubectl') {
    sh("kubectl --namespace=${namespace} ${command}")
  }
}

def rollback(deploymentName) {
  kubectl("rollout undo deployment/${deploymentName}")
}

return this;